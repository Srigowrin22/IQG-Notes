package training.iqgateway.view.backing;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import training.iqgateway.model.VehicleOffenceEO;
import training.iqgateway.services.ClerkSessionEJBLocal;

public class PendingOffence {
    private String searchedRegistrationID;
        private List<VehicleOffenceEO> allVehicleOffence;
        private Map<Long, Boolean> selected = new LinkedHashMap<Long, Boolean>(); // Key: vehicleOffenceId, Value: selected
        private BigDecimal totalPenalty = BigDecimal.ZERO;
        private boolean showPaymentCard = false;
        private String message;

        // --- EJB Lookup (adapt JNDI as per your app) ---
        private ClerkSessionEJBLocal getSessionBean() {
            try {
                javax.naming.InitialContext ctx = new javax.naming.InitialContext();
                Object lookupObject = ctx.lookup("java:comp/env/ejb/local/ClerkSessionEJB");
                return (ClerkSessionEJBLocal)lookupObject;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        // --- Search Action ---
        public String search_action() {
            message = null;
            showPaymentCard = false;
            totalPenalty = BigDecimal.ZERO;
            selected.clear();
            if (searchedRegistrationID == null || searchedRegistrationID.trim().isEmpty()) {
                addMessage("Please enter a Registration ID.");
                allVehicleOffence = null;
                return null;
            }
            try {
                allVehicleOffence = getSessionBean().findVehicleOffenceByStatus(searchedRegistrationID.trim(), 0L);
                if (allVehicleOffence == null || allVehicleOffence.isEmpty()) {
                    addMessage("No pending offences found.");
                } else {
                    for (VehicleOffenceEO offence : allVehicleOffence) {
                        selected.put(offence.getVehicleOffenceId(), Boolean.FALSE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                addMessage("Error during search: " + e.getMessage());
                allVehicleOffence = null;
            }
            return null;
        }

        // --- Show Payment Card ---
        public String showPaymentCard_action() {
            List<Long> selectedIds = getSelectedOffenceIds();
            if (selectedIds.isEmpty()) {
                addMessage("No offences selected to clear!");
                showPaymentCard = false;
                return null;
            }
            totalPenalty = BigDecimal.ZERO;
            try {
                for (Long vehOffId : selectedIds) {
                    VehicleOffenceEO offence = getSessionBean().findVehicleOffenceByID(vehOffId);
                    if (offence != null && offence.getOffenceTypesEO() != null && offence.getOffenceTypesEO().getPenaltyAmt() != null) {
                        totalPenalty = totalPenalty.add(BigDecimal.valueOf(offence.getOffenceTypesEO().getPenaltyAmt()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                addMessage("Error calculating total penalty: " + e.getMessage());
                showPaymentCard = false;
                return null;
            }
            showPaymentCard = true;
            return null;
        }

        // --- Pay Action ---
        public String pay_action() {
            List<Long> selectedIds = getSelectedOffenceIds();
            if (selectedIds.isEmpty()) {
                addMessage("No offences selected for payment!");
                showPaymentCard = false;
                return null;
            }
            int paidCount = 0;
            try {
                for (Long vehOffId : selectedIds) {
                    VehicleOffenceEO offence = getSessionBean().findVehicleOffenceByID(vehOffId);
                    if (offence != null && offence.getStatus() != null && offence.getStatus() == 0L) {
                        getSessionBean().removeVehicleOffenceEO(offence);
                        paidCount++;
                    }
                }
                addMessage("Cleared " + paidCount + " offence(s) successfully!");
                // Refresh list
                allVehicleOffence = getSessionBean().findVehicleOffenceByStatus(
                    searchedRegistrationID != null ? searchedRegistrationID.trim() : "", 0L);
                selected.clear();
                if (allVehicleOffence != null) {
                    for (VehicleOffenceEO offence : allVehicleOffence) {
                        selected.put(offence.getVehicleOffenceId(), Boolean.FALSE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                addMessage("Error during payment: " + e.getMessage());
            }
            totalPenalty = BigDecimal.ZERO;
            showPaymentCard = false;
            return null;
        }

        // --- Cancel Payment ---
        public String cancelPayment_action() {
            totalPenalty = BigDecimal.ZERO;
            showPaymentCard = false;
            return null;
        }

        // --- Utility: Get selected offence IDs ---
        private List<Long> getSelectedOffenceIds() {
            List<Long> selectedIds = new ArrayList<Long>();
            for (Map.Entry<Long, Boolean> entry : selected.entrySet()) {
                if (Boolean.TRUE.equals(entry.getValue())) {
                    selectedIds.add(entry.getKey());
                }
            }
            return selectedIds;
        }

        // --- Utility: Add FacesMessage and set message property ---
        private void addMessage(String msg) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
            this.message = msg;
        }

        // --- Getters and Setters ---
        public String getSearchedRegistrationID() { return searchedRegistrationID; }
        public void setSearchedRegistrationID(String searchedRegistrationID) { this.searchedRegistrationID = searchedRegistrationID; }
        public List<VehicleOffenceEO> getAllVehicleOffence() { return allVehicleOffence; }
        public void setAllVehicleOffence(List<VehicleOffenceEO> allVehicleOffence) { this.allVehicleOffence = allVehicleOffence; }
        public Map<Long, Boolean> getSelected() { return selected; }
        public void setSelected(Map<Long, Boolean> selected) { this.selected = selected; }
        public BigDecimal getTotalPenalty() { return totalPenalty; }
        public void setTotalPenalty(BigDecimal totalPenalty) { this.totalPenalty = totalPenalty; }
        public boolean isShowPaymentCard() { return showPaymentCard; }
        public void setShowPaymentCard(boolean showPaymentCard) { this.showPaymentCard = showPaymentCard; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
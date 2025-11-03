package training.iqgateway.view.backing;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.model.OwnerEO;
import training.iqgateway.model.VehicleOffenceEO;
import training.iqgateway.services.OwnerSessionEJBLocal;

public class ClearOffenceOwner {
    private String selectedRegistrationId; // Set from OwnerVehicle page/session
    private List<VehicleOffenceEO> allVehicleOffence;
    private Map<Long, Boolean> selected = new LinkedHashMap<Long, Boolean>();
    private BigDecimal totalPenalty = BigDecimal.ZERO;
    private boolean showPaymentCard = false;
    private String message;

    private OwnerSessionEJBLocal sessionBean;

    // --- Lazy EJB lookup ---

    private OwnerSessionEJBLocal getSessionBean() throws NamingException {
        if (sessionBean == null) {
            InitialContext ctx = new InitialContext();
            sessionBean =
                    (OwnerSessionEJBLocal)ctx.lookup("java:comp/env/ejb/local/OwnerSessionEJB");
        }
        return sessionBean;
    }
    
    //Fetch the selected Registration ID
    
    public List<VehicleOffenceEO> getOffencesForRegistration(String registrationId) {
        if (registrationId == null || registrationId.trim().isEmpty()) {
            return new ArrayList<VehicleOffenceEO>();
        }
        try {
            return getSessionBean().findVehicleOffenceByStatus(registrationId.trim(), 0L);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<VehicleOffenceEO>();
        }
    }

    // --- Load pending offences for selected registration ---

    public void loadPendingOffences() {
        message = null;
        showPaymentCard = false;
        totalPenalty = BigDecimal.ZERO;
        selected.clear();
        if (selectedRegistrationId == null ||
            selectedRegistrationId.trim().isEmpty()) {
            addMessage("Registration ID not set.");
            allVehicleOffence = null;
            return;
        }
        try {
            allVehicleOffence =
                    getSessionBean().findVehicleOffenceByStatus(selectedRegistrationId.trim(),
                                                                0L);
            if (allVehicleOffence == null || allVehicleOffence.isEmpty()) {
                addMessage("No pending offences found.");
            } else {
                for (VehicleOffenceEO offence : allVehicleOffence) {
                    selected.put(offence.getVehicleOffenceId(), Boolean.FALSE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            addMessage("Error loading offences: " + e.getMessage());
            allVehicleOffence = null;
        }
    }

    // --- Show payment card for selected offences ---

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
                VehicleOffenceEO offence =
                    getSessionBean().findVehicleOffenceByID(vehOffId);
                if (offence != null && offence.getOffenceTypesEO() != null &&
                    offence.getOffenceTypesEO().getPenaltyAmt() != null) {
                    totalPenalty =
                            totalPenalty.add(BigDecimal.valueOf(offence.getOffenceTypesEO().getPenaltyAmt()));
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

    // --- Pay selected offences ---

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
                VehicleOffenceEO offence =
                    getSessionBean().findVehicleOffenceByID(vehOffId);
                if (offence != null && offence.getStatus() != null &&
                    offence.getStatus() == 0L) {
                    getSessionBean().removeVehicleOffenceEO(vehOffId);
                    paidCount++;
                }
            }
            addMessage("Cleared " + paidCount + " offence(s) successfully!");
            // Refresh list
            allVehicleOffence =
                    getSessionBean().findVehicleOffenceByStatus(selectedRegistrationId !=
                                                                null ?
                                                                selectedRegistrationId.trim() :
                                                                "", 0L);
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

    // --- Cancel payment ---

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
        FacesContext.getCurrentInstance().addMessage(null,
                                                     new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                      msg,
                                                                      null));
        this.message = msg;
    }

    // --- Getters and Setters ---

    public String getSelectedRegistrationId() {
        return selectedRegistrationId;
    }

    public void setSelectedRegistrationId(String selectedRegistrationId) {
        this.selectedRegistrationId = selectedRegistrationId;
        // Optionally auto-load offences when registration changes
        loadPendingOffences();
    }

    public List<VehicleOffenceEO> getAllVehicleOffence() {
        return allVehicleOffence;
    }

    public void setAllVehicleOffence(List<VehicleOffenceEO> allVehicleOffence) {
        this.allVehicleOffence = allVehicleOffence;
    }

    public Map<Long, Boolean> getSelected() {
        return selected;
    }

    public void setSelected(Map<Long, Boolean> selected) {
        this.selected = selected;
    }

    public BigDecimal getTotalPenalty() {
        return totalPenalty;
    }

    public void setTotalPenalty(BigDecimal totalPenalty) {
        this.totalPenalty = totalPenalty;
    }

    public boolean isShowPaymentCard() {
        return showPaymentCard;
    }

    public void setShowPaymentCard(boolean showPaymentCard) {
        this.showPaymentCard = showPaymentCard;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package training.iqgateway.view.backing;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.model.VehicleOffenceEO;
import training.iqgateway.services.ClerkSessionEJBLocal;


public class ClearOffence {
    private String searchRegistrationID;
    private Long vehicleOffenceId;
    private List<VehicleOffenceEO> vehOffenceList =
        new ArrayList<VehicleOffenceEO>();
    private List<Long> selectedOffenceIds = new ArrayList<Long>();

    private boolean selectAll;
    private boolean showCard = true; // To control visibility of card in JSF
    private int totalPenalty;

    // Lookup EJB session bean

    public ClerkSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        Object lookupObject =
            ic.lookup("java:comp/env/ejb/local/ClerkSessionEJB");
        return (ClerkSessionEJBLocal)lookupObject;
    }

    public void setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
        if (vehOffenceList != null) {
            selectedOffenceIds.clear();
            if (selectAll) {
                for (VehicleOffenceEO o : vehOffenceList) {
                    selectedOffenceIds.add(o.getVehicleOffenceId());
                }
            }
            calculateTotalPenalty();
        }
    }

    // Helper for JSF hidden input binding (comma-separated string)

    public String getSelectedOffenceIdsAsString() {
        if (selectedOffenceIds == null || selectedOffenceIds.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Long id : selectedOffenceIds) {
            if (sb.length() > 0)
                sb.append(",");
            sb.append(id);
        }
        return sb.toString();
    }

    public void setSelectedOffenceIdsAsString(String selectedIdsStr) {
        selectedOffenceIds.clear();
        if (selectedIdsStr != null && !selectedIdsStr.trim().isEmpty()) {
            String[] parts = selectedIdsStr.split(",");
            for (String part : parts) {
                try {
                    selectedOffenceIds.add(Long.parseLong(part));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        calculateTotalPenalty();
    }

    // Search offences by Registration ID

    public void searchOffences() {
        try {
            vehOffenceList =
                    getSessionBean().findVehicleOffenceByRegisID(searchRegistrationID);
            selectedOffenceIds.clear();
            selectAll = false;
            calculateTotalPenalty();
            showCard = true; // Show card after search
        } catch (Exception e) {
            e.printStackTrace();
            vehOffenceList = new ArrayList<VehicleOffenceEO>();
            selectedOffenceIds.clear();
            totalPenalty = 0;
        }
    }

    // Calculate total penalty of selected offences

    public void calculateTotalPenalty() {
        int total = 0;
        if (vehOffenceList != null && selectedOffenceIds != null) {
            for (VehicleOffenceEO o : vehOffenceList) {
                if (selectedOffenceIds.contains(o.getVehicleOffenceId())) {
                    total += o.getOffenceTypesEO().getPenaltyAmt().intValue();
                }
            }
        }
        totalPenalty = total;
    }

    // Remove selected offences

    public void removeSelectedOffences() {
        if (selectedOffenceIds == null || selectedOffenceIds.isEmpty()) {
            return; // Nothing selected
        }

        try {
            for (Long id : selectedOffenceIds) {
                VehicleOffenceEO toRemove = null;
                for (VehicleOffenceEO o : vehOffenceList) {
                    if (o.getVehicleOffenceId().equals(id)) {
                        toRemove = o;
                        break;
                    }
                }
                if (toRemove != null) {
                    getSessionBean().removeVehicleOffenceEO(toRemove);
                }
            }

            // Remove from local list
            List<VehicleOffenceEO> newList = new ArrayList<VehicleOffenceEO>();
            for (VehicleOffenceEO o : vehOffenceList) {
                if (!selectedOffenceIds.contains(o.getVehicleOffenceId())) {
                    newList.add(o);
                }
            }
            vehOffenceList = newList;

            selectedOffenceIds.clear();
            selectAll = false;
            calculateTotalPenalty();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cancel action to hide the card

    public void cancel() {
        showCard = false;
        // Optionally clear selections and list
        selectedOffenceIds.clear();
        vehOffenceList.clear();
        totalPenalty = 0;
    }

    public Long getVehicleOffenceId() {
        return vehicleOffenceId;
    }

    public void setVehicleOffenceId(Long vehicleOffenceId) {
        this.vehicleOffenceId = vehicleOffenceId;
    }

    public String getSearchRegistrationID() {
        return searchRegistrationID;
    }

    public void setSearchRegistrationID(String searchRegistrationID) {
        this.searchRegistrationID = searchRegistrationID;
    }

    public List<VehicleOffenceEO> getOffenceList() {
        return vehOffenceList;
    }

    public void setOffenceList(List<VehicleOffenceEO> vehOffenceList) {
        this.vehOffenceList = vehOffenceList;
    }

    public List<Long> getSelectedOffenceIds() {
        return selectedOffenceIds;
    }

    public void setSelectedOffenceIds(List<Long> selectedOffenceIds) {
        this.selectedOffenceIds = selectedOffenceIds;
    }

    public boolean isSelectAll() {
        return selectAll;
    }


    public int getTotalPenalty() {
        return totalPenalty;
    }

    public boolean isSelectedOffencesEmpty() {
        return selectedOffenceIds == null || selectedOffenceIds.isEmpty();
    }

    public boolean isShowCard() {
        return showCard;
    }

    public void setShowCard(boolean showCard) {
        this.showCard = showCard;
    }
}

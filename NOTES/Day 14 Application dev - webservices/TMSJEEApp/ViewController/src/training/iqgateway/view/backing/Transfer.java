package training.iqgateway.view.backing;

import javax.faces.model.SelectItem;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.context.FacesContext;

import training.iqgateway.model.AdminEO;
import training.iqgateway.model.OwnerEO;
import training.iqgateway.model.RegistrationEO;
import training.iqgateway.model.VehicleOffenceEO;
import training.iqgateway.services.RTOSessionEJBLocal;
import training.iqgateway.utils.Format;

public class Transfer {

    private Format formatUtil = new Format();

    // UI state flags
    private boolean showCard = false;
    private boolean transferMode = false;
    private boolean ownerCardMode = false;
    private boolean clearOffenceMode = false;

    // Messages
    private String message;

    // Search field
    private String searchRegistrationId;

    // Current registration for card
    private RegistrationEO currentRegistration;
    private String currentRegistrationDateStr;

    // Pending offences for this registration
    private List<VehicleOffenceEO> pendingOffenceList;

    // Owner dropdown for transfer
    private List<SelectItem> ownerSelectItems;
    private String selectedOwnerAadhar;

    // Owner creation card
    private OwnerEO newOwner;

    // Maintain admin object (simulate login/session)
    private AdminEO currentAdmin =
        (AdminEO)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInAdmin");


    // Total pending offence amount
    private int totalOffenceAmount = 0;

    // --- EJB Lookup ---
    public RTOSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        return (RTOSessionEJBLocal)ic.lookup("java:comp/env/ejb/local/RTOSessionEJB");
    }

    // --- Search Registration ---
    public void searchRegistration() {
        clearModes();
        message = null;
        currentRegistration = null;
        currentRegistrationDateStr = null;
        pendingOffenceList = null;
        totalOffenceAmount = 0;
        if (searchRegistrationId == null || searchRegistrationId.trim().isEmpty()) {
            message = "Please enter a Registration ID to search.";
            return;
        }
        String validationMsg = formatUtil.vadilateRegistrationID(searchRegistrationId.trim());
        if (validationMsg != null) {
            message = validationMsg;
            return;
        }
        try {
            RegistrationEO reg = getSessionBean().findRegistrationByID(searchRegistrationId.trim());
            if (reg == null) {
                message = "No registration found for ID: " + searchRegistrationId;
                return;
            }
            currentRegistration = reg;
            currentRegistrationDateStr = (reg.getRegistrationDate() != null)
                    ? new SimpleDateFormat("yyyy-MM-dd").format(reg.getRegistrationDate())
                    : null;
            // Load pending offences (status = 0)
            pendingOffenceList = getSessionBean().findVehicleOffenceByStatus(searchRegistrationId.trim(), 0L);
            // Calculate total amount
            totalOffenceAmount = 0;
            if (pendingOffenceList != null) {
                for (VehicleOffenceEO vo : pendingOffenceList) {
                    if (vo.getOffenceTypesEO() != null && vo.getOffenceTypesEO().getPenaltyAmt() != null) {
                        totalOffenceAmount += vo.getOffenceTypesEO().getPenaltyAmt();
                    }
                }
            }
            showCard = true;
        } catch (Exception e) {
            message = "Error during search: " + e.getMessage();
        }
    }

    // --- Show Clear Offence Card ---
    public String showClearOffenceCard() {
        clearOffenceMode = true;
        showCard = false;
        transferMode = false;
        ownerCardMode = false;
        // totalOffenceAmount already calculated in searchRegistration
        return null;
    }

    // --- Cancel Clear Offence ---
    public String cancelClearOffence() {
        clearOffenceMode = false;
        showCard = true;
        return null;
    }

    // --- Pay Offence ---
    public String payOffence() {
        message = null;
        try {
            if (pendingOffenceList != null) {
                for (VehicleOffenceEO vo : pendingOffenceList) {
                    getSessionBean().removeVehicleOffenceEO(vo);
                }
            }
            // Refresh pendingOffenceList and totalOffenceAmount
            pendingOffenceList = getSessionBean().findVehicleOffenceByStatus(
                currentRegistration.getRegistrationId(), 0L);
            totalOffenceAmount = 0;
            if (pendingOffenceList != null) {
                for (VehicleOffenceEO vo : pendingOffenceList) {
                    if (vo.getOffenceTypesEO() != null && vo.getOffenceTypesEO().getPenaltyAmt() != null) {
                        totalOffenceAmount += vo.getOffenceTypesEO().getPenaltyAmt();
                    }
                }
            }
            message = "All pending offences cleared!";
        } catch (Exception e) {
            message = "Error clearing offences: " + e.getMessage();
        }
        clearOffenceMode = false;
        showCard = true;
        return null;
    }

    // --- Show Transfer Card ---
    public String showTransferCard() {
        transferMode = true;
        ownerCardMode = false;
        clearOffenceMode = false;
        selectedOwnerAadhar = null;
        ownerSelectItems = null; // Reload owner combo
        showCard = false;
        return null;
    }

    // --- Cancel Card ---
    public String cancelCard() {
        showCard = false;
        transferMode = false;
        ownerCardMode = false;
        clearOffenceMode = false;
        message = null;
        currentRegistration = null;
        currentRegistrationDateStr = null;
        pendingOffenceList = null;
        searchRegistrationId = null;
        selectedOwnerAadhar = null;
        ownerSelectItems = null;
        totalOffenceAmount = 0;
        return null;
    }

    // --- Cancel Transfer ---
    public String cancelTransfer() {
        transferMode = false;
        ownerCardMode = false;
        clearOffenceMode = false;
        selectedOwnerAadhar = null;
        showCard = true;
        return null;
    }

    // --- Owner Dropdown for Transfer ---
    public List<SelectItem> getOwnerSelectItems() throws NamingException {
        if (ownerSelectItems == null) {
            ownerSelectItems = new ArrayList<SelectItem>();
            List<OwnerEO> owners = getSessionBean().getOwnerEOFindAll();
            String currentOwnerAadhar =
                (currentRegistration != null && currentRegistration.getOwnerEO() != null)
                ? currentRegistration.getOwnerEO().getOwnerAadhar() : null;
            for (OwnerEO o : owners) {
                // Exclude the current owner from the dropdown
                if (currentOwnerAadhar == null || !currentOwnerAadhar.equals(o.getOwnerAadhar())) {
                    String label = o.getOwnerName() + " - " + o.getOwnerAadhar();
                    ownerSelectItems.add(new SelectItem(o.getOwnerAadhar(), label));
                }
            }
        }
        return ownerSelectItems;
    }

    public String getSelectedOwnerAadhar() { return selectedOwnerAadhar; }
    public void setSelectedOwnerAadhar(String selectedOwnerAadhar) { this.selectedOwnerAadhar = selectedOwnerAadhar; }

    // --- Show Owner Creation Card ---
    public String showCreateOwnerCard() {
        ownerCardMode = true;
        newOwner = new OwnerEO();
        return null;
    }

    // --- Cancel Owner Add ---
    public String cancelOwnerAdd() {
        ownerCardMode = false;
        newOwner = null;
        return null;
    }

    // --- Save New Owner ---
    public String saveNewOwner() {
        message = null;
        if (newOwner == null) {
            message = "No owner data to save.";
            return null;
        }
        String validationMsg = formatUtil.validateAadhar(newOwner.getOwnerAadhar());
        if (validationMsg != null) {
            message = validationMsg;
            return null;
        }
        validationMsg = formatUtil.validatePan(newOwner.getPanCard());
        if (validationMsg != null) {
            message = validationMsg;
            return null;
        }
        validationMsg = formatUtil.validatePhone(newOwner.getPhone());
        if (validationMsg != null) {
            message = validationMsg;
            return null;
        }
        try {
            if (getSessionBean().findOwnerByAadhar(newOwner.getOwnerAadhar()) != null) {
                message = "Owner with this Aadhar already exists!";
                return null;
            }
            // Set password as owner name
            newOwner.setPassword(newOwner.getOwnerName());
            getSessionBean().persistOwnerEO(newOwner);
            message = "Owner added successfully!";
            ownerCardMode = false;
            ownerSelectItems = null; // reload combo on next render
        } catch (Exception e) {
            message = "Error adding owner: " + e.getMessage();
        }
        return null;
    }

    // --- Save Transfer ---
    public String saveTransfer() {
        message = null;
        if (currentRegistration == null) {
            message = "No registration selected for transfer.";
            return null;
        }
        if (selectedOwnerAadhar == null || selectedOwnerAadhar.trim().isEmpty()) {
            message = "New owner must be selected.";
            return null;
        }
        try {
            OwnerEO selectedOwner = getSessionBean().findOwnerByAadhar(selectedOwnerAadhar.trim());
            if (selectedOwner == null) {
                message = "Selected owner not found!";
                return null;
            }
            // Set new owner
            currentRegistration.setOwnerEO(selectedOwner);
            // Set registrar as current admin's designation ID (simulate login)
            if (currentAdmin != null && currentAdmin.getDesignationId() != null) {
                currentRegistration.setRegistrar(currentAdmin.getDesignationId());
            } else {
                currentRegistration.setRegistrar("ANYM01");
            }
            // Call EJB merge and handle its return message
            String result = getSessionBean().mergeRegistrationEO(currentRegistration);
            message = result;
            if ("Successfully updated".equals(result)) {
                // Reload the updated registration from DB
                RegistrationEO updated = getSessionBean().findRegistrationByID(currentRegistration.getRegistrationId());
                currentRegistration = updated;
                currentRegistrationDateStr = (updated.getRegistrationDate() != null)
                        ? new SimpleDateFormat("yyyy-MM-dd").format(updated.getRegistrationDate())
                        : null;
                // Reload offences as well
                pendingOffenceList = getSessionBean().findVehicleOffenceByStatus(currentRegistration.getRegistrationId(), 0L);
                transferMode = false; // Hide transfer card
                ownerCardMode = false;
                showCard = true; // Show details card
                selectedOwnerAadhar = null;
                ownerSelectItems = null;
            } else {
                // Error: stay in transfer mode
                transferMode = true;
            }
        } catch (Exception e) {
            message = "Error transferring vehicle: " + e.getMessage();
            transferMode = true;
        }
        return null;
    }

    // --- Utility: Clear all modes ---
    private void clearModes() {
        showCard = false;
        transferMode = false;
        ownerCardMode = false;
        clearOffenceMode = false;
        message = null;
        selectedOwnerAadhar = null;
        ownerSelectItems = null;
        totalOffenceAmount = 0;
    }

    // --- Getters and Setters ---
    public boolean isShowCard() { return showCard; }
    public boolean isTransferMode() { return transferMode; }
    public boolean isOwnerCardMode() { return ownerCardMode; }
    public boolean isClearOffenceMode() { return clearOffenceMode; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getSearchRegistrationId() { return searchRegistrationId; }
    public void setSearchRegistrationId(String searchRegistrationId) { this.searchRegistrationId = searchRegistrationId; }
    public RegistrationEO getCurrentRegistration() { return currentRegistration; }
    public void setCurrentRegistration(RegistrationEO currentRegistration) { this.currentRegistration = currentRegistration; }
    public String getCurrentRegistrationDateStr() { return currentRegistrationDateStr; }
    public void setCurrentRegistrationDateStr(String currentRegistrationDateStr) { this.currentRegistrationDateStr = currentRegistrationDateStr; }
    public OwnerEO getNewOwner() { return newOwner; }
    public void setNewOwner(OwnerEO newOwner) { this.newOwner = newOwner; }
    public List<VehicleOffenceEO> getPendingOffenceList() { return pendingOffenceList; }
    public AdminEO getCurrentAdmin() { return currentAdmin; }
    public void setCurrentAdmin(AdminEO currentAdmin) { this.currentAdmin = currentAdmin; }
    public int getTotalOffenceAmount() { return totalOffenceAmount; }
}

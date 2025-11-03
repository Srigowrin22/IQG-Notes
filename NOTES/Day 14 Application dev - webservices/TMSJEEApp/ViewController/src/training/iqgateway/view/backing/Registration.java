package training.iqgateway.view.backing;


import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import training.iqgateway.model.OwnerEO;
import training.iqgateway.model.RegistrationEO;
import training.iqgateway.model.VehicleEO;
import training.iqgateway.services.RTOSessionEJBLocal;
import training.iqgateway.utils.Format;

public class Registration {

    private Format formatUtil = new Format();

    // UI state flags
    private boolean addMode = false;
    private boolean updateMode = false;
    private boolean viewAllMode = false;
    private boolean searchMode = false;
    private boolean editMode = false;
    private boolean ownerCardMode = false; // For owner creation card

    // Messages
    private String message;

    // Search field
    private String searchRegistrationId;

    // Registration list for table
    private List<RegistrationEO> registrationList;

    // Current registration for card (search/update)
    private RegistrationEO currentRegistration;
    private String currentRegistrationDateStr; // For JSF binding

    // New registration for add
    private RegistrationEO newRegistration;
    private String newRegistrationDateStr; // For JSF binding

    // Vehicle dropdown for add
    private List<SelectItem> vehicleSelectItems;
    private Long selectedVehicleId;

    // Owner dropdown for add
    private List<SelectItem> ownerSelectItems;
    private String selectedOwnerAadhar;

    // Owner creation card
    private OwnerEO newOwner;

    // --- EJB Lookup ---

    public RTOSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        return (RTOSessionEJBLocal)ic.lookup("java:comp/env/ejb/local/RTOSessionEJB");
    }

    // --- Vehicle Dropdown for Add ---

    public List<SelectItem> getVehicleSelectItems() throws NamingException {
        if (vehicleSelectItems == null) {
            vehicleSelectItems = new ArrayList<SelectItem>();
            // Fetch all vehicles
            List<VehicleEO> vehicles = getSessionBean().getVehicleEOFindAll();
            // Fetch all registered vehicle IDs
            List<RegistrationEO> registrations =
                getSessionBean().getRegistrationEOFindAll();
            java.util.Set<Long> registeredVehicleIds =
                new java.util.HashSet<Long>();
            for (RegistrationEO reg : registrations) {
                if (reg.getVehicleEO() != null &&
                    reg.getVehicleEO().getVehicleId() != null) {
                    registeredVehicleIds.add(reg.getVehicleEO().getVehicleId());
                }
            }
            // Only add vehicles not already registered
            for (VehicleEO v : vehicles) {
                if (!registeredVehicleIds.contains(v.getVehicleId())) {
                    String label =
                        v.getVehicleId() + " - " + v.getVehicleModel() +
                        " - " + v.getVehicleBrand() + " - " + v.getColor();
                    vehicleSelectItems.add(new SelectItem(v.getVehicleId(),
                                                          label));
                }
            }
        }
        return vehicleSelectItems;
    }


    // --- Owner Dropdown for Add ---

    public List<SelectItem> getOwnerSelectItems() throws NamingException {
        if (ownerSelectItems == null) {
            ownerSelectItems = new ArrayList<SelectItem>();
            List<OwnerEO> owners = getSessionBean().getOwnerEOFindAll();
            for (OwnerEO o : owners) {
                String label = o.getOwnerName() + " - " + o.getOwnerAadhar();
                ownerSelectItems.add(new SelectItem(o.getOwnerAadhar(),
                                                    label));
            }
        }
        return ownerSelectItems;
    }

    public String getSelectedOwnerAadhar() {
        return selectedOwnerAadhar;
    }

    public void setSelectedOwnerAadhar(String selectedOwnerAadhar) {
        this.selectedOwnerAadhar = selectedOwnerAadhar;
    }

    public Long getSelectedVehicleId() {
        return selectedVehicleId;
    }

    public void setSelectedVehicleId(Long selectedVehicleId) {
        this.selectedVehicleId = selectedVehicleId;
    }

    // --- Add Button logic: only enabled if no result found ---

    public boolean isAddEnabled() {
        return (searchRegistrationId != null &&
                !searchRegistrationId.trim().isEmpty()) && !searchMode &&
            !updateMode && !addMode;
    }

    // --- Should Card be shown? ---

    public boolean isShowCard() {
        return addMode || searchMode || updateMode || editMode;
    }

    // --- Search Registration ---

    public void searchRegistration() {
        clearModes();
        message = null;
        currentRegistration = null;
        currentRegistrationDateStr = null;
        if (searchRegistrationId == null ||
            searchRegistrationId.trim().isEmpty()) {
            message = "Please enter a Registration ID to search.";
            return;
        }
        String validationMsg =
            formatUtil.vadilateRegistrationID(searchRegistrationId.trim());
        if (validationMsg != null) {
            message = validationMsg;
            return;
        }
        try {
            RegistrationEO reg =
                getSessionBean().findRegistrationByID(searchRegistrationId.trim());
            if (reg == null) {
                message =
                        "No registration found for ID: " + searchRegistrationId;
                // DO NOT set searchMode/addMode/updateMode here!
                return;
            }
            currentRegistration = reg;
            currentRegistrationDateStr =
                    (reg.getRegistrationDate() != null) ? new SimpleDateFormat("yyyy-MM-dd").format(reg.getRegistrationDate()) :
                    null;
            searchMode = true;
            editMode = false;
        } catch (Exception e) {
            message = "Error during search: " + e.getMessage();
        }
    }

    // --- Show Add Card ---

    public void showAddCard() {
        clearModes();
        addMode = true;
        newRegistration = new RegistrationEO();
        newRegistration.setRegistrationId(searchRegistrationId); // Pre-fill and make read-only in JSF
        newRegistrationDateStr = formatUtil.getDate();
        selectedVehicleId = null;
        selectedOwnerAadhar = null;
    }

    // --- Show All Registrations ---

    public void showAllRegistrations() {
        clearModes();
        try {
            registrationList = getSessionBean().getRegistrationEOFindAll();
            viewAllMode = true;
            searchRegistrationId = null; // Clear search field
        } catch (Exception e) {
            registrationList = new ArrayList<RegistrationEO>();
            message = "Error loading registrations: " + e.getMessage();
            searchRegistrationId = null; // Clear search field
        }
    }

    // --- Prepare Update from Table ---

    public void prepareUpdateFromTable() {
        clearModes();
        updateMode = true;
        editMode = false;
        // currentRegistration is set by f:setPropertyActionListener in JSF
        if (currentRegistration != null) {
            currentRegistrationDateStr =
                    (currentRegistration.getRegistrationDate() != null) ?
                    new SimpleDateFormat("yyyy-MM-dd").format(currentRegistration.getRegistrationDate()) :
                    null;
        }
    }

    // --- Enable Edit Mode (from search/update card) ---

    public void enableEditMode() {
        editMode = true;
    }

    // --- Cancel Card (hides card) ---

    public void cancelCard() {
        clearModes();
        currentRegistration = null;
        newRegistration = null;
        currentRegistrationDateStr = null;
        newRegistrationDateStr = null;
        searchRegistrationId = null;
    }

    // --- Cancel Edit (goes back to read-only card) ---

    public void cancelEdit() {
        editMode = false;
        // Reload registration from DB to discard changes
        if (currentRegistration != null &&
            currentRegistration.getRegistrationId() != null) {
            try {
                RegistrationEO reg =
                    getSessionBean().findRegistrationByID(currentRegistration.getRegistrationId());
                if (reg != null) {
                    currentRegistration = reg;
                    currentRegistrationDateStr =
                            (reg.getRegistrationDate() != null) ?
                            new SimpleDateFormat("yyyy-MM-dd").format(reg.getRegistrationDate()) :
                            null;
                }
            } catch (Exception e) {
                message = "Error reloading registration: " + e.getMessage();
            }
        }
        searchRegistrationId = null;
    }

    // --- Cancel Add ---

    public void cancelAdd() {
        addMode = false;
        newRegistration = null;
        newRegistrationDateStr = null;
        selectedVehicleId = null;
        selectedOwnerAadhar = null;
        searchRegistrationId = null;
    }

    // --- Show Owner Creation Card ---

    public void showCreateOwnerCard() {
        ownerCardMode = true;
        newOwner = new OwnerEO();
    }

    // --- Cancel Owner Add ---

    public void cancelOwnerAdd() {
        ownerCardMode = false;
        newOwner = null;
    }

    // --- Save New Owner ---

    public void saveNewOwner() {
        message = null;
        if (newOwner == null) {
            message = "No owner data to save.";
            return;
        }
        String validationMsg =
            formatUtil.validateAadhar(newOwner.getOwnerAadhar());
        if (validationMsg != null) {
            message = validationMsg;
            return;
        }
        validationMsg = formatUtil.validatePan(newOwner.getPanCard());
        if (validationMsg != null) {
            message = validationMsg;
            return;
        }
        validationMsg = formatUtil.validatePhone(newOwner.getPhone());
        if (validationMsg != null) {
            message = validationMsg;
            return;
        }
        try {
            if (getSessionBean().findOwnerByAadhar(newOwner.getOwnerAadhar()) !=
                null) {
                message = "Owner with this Aadhar already exists!";
                return;
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
    }

    // --- Save New Registration ---

    public void saveNewRegistration() {
        message = null;
        if (newRegistration == null) {
            message = "No registration data to save.";
            return;
        }
        String validationMsg =
            formatUtil.vadilateRegistrationID(newRegistration.getRegistrationId());
        if (validationMsg != null) {
            message = validationMsg;
            return;
        }
        if (selectedVehicleId == null) {
            message = "Vehicle must be selected.";
            return;
        }
        try {
            VehicleEO selectedVehicle =
                getSessionBean().findVehicleByID(selectedVehicleId);
            newRegistration.setVehicleEO(selectedVehicle);
        } catch (Exception e) {
            message = "Error fetching vehicle: " + e.getMessage();
            return;
        }
        if (selectedOwnerAadhar == null ||
            selectedOwnerAadhar.trim().isEmpty()) {
            message = "Owner must be selected.";
            return;
        }
        try {
            OwnerEO selectedOwner =
                getSessionBean().findOwnerByAadhar(selectedOwnerAadhar);
            newRegistration.setOwnerEO(selectedOwner);
        } catch (Exception e) {
            message = "Error fetching owner: " + e.getMessage();
            return;
        }
        if (newRegistrationDateStr != null &&
            !newRegistrationDateStr.trim().isEmpty()) {
            try {
                Timestamp ts =
                    formatUtil.convertStringToTimestamp(newRegistrationDateStr);
                newRegistration.setRegistrationDate(ts);
            } catch (ParseException e) {
                message = "Invalid date format! Use yyyy-MM-dd.";
                return;
            }
        }
        try {
            if (getSessionBean().findRegistrationByID(newRegistration.getRegistrationId()) !=
                null) {
                message = "Registration ID already exists!";
                return;
            }
            getSessionBean().persistRegistrationEO(newRegistration);
            message = "Registration added successfully!";
            addMode = false;
            vehicleSelectItems = null;
            showAllRegistrations();
        } catch (Exception e) {
            message = "Error adding registration: " + e.getMessage();
        }
    }

    // --- Save Update ---

    public void saveUpdate() {
        message = null;
        if (currentRegistration == null) {
            message = "No registration selected.";
            return;
        }
        if (currentRegistrationDateStr != null &&
            !currentRegistrationDateStr.trim().isEmpty()) {
            try {
                Timestamp ts =
                    formatUtil.convertStringToTimestamp(currentRegistrationDateStr);
                currentRegistration.setRegistrationDate(ts);
            } catch (ParseException e) {
                message = "Invalid date format! Use yyyy-MM-dd.";
                return;
            }
        }
        try {
            getSessionBean().mergeRegistrationEO(currentRegistration);
            message = "Registration updated successfully!";
            editMode = false;
            showAllRegistrations();
        } catch (Exception e) {
            message = "Error updating registration: " + e.getMessage();
        }
    }

    // --- Utility: Clear all modes ---

    private void clearModes() {
        addMode = false;
        updateMode = false;
        viewAllMode = false;
        searchMode = false;
        editMode = false;
        ownerCardMode = false;
    }

    // --- Getters and Setters ---

    public boolean isAddMode() {
        return addMode;
    }

    public boolean isUpdateMode() {
        return updateMode;
    }

    public boolean isViewAllMode() {
        return viewAllMode;
    }

    public boolean isSearchMode() {
        return searchMode;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public boolean isOwnerCardMode() {
        return ownerCardMode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSearchRegistrationId() {
        return searchRegistrationId;
    }

    public void setSearchRegistrationId(String searchRegistrationId) {
        this.searchRegistrationId = searchRegistrationId;
    }

    public List<RegistrationEO> getRegistrationList() {
        if (registrationList == null) {
            try {
                registrationList = getSessionBean().getRegistrationEOFindAll();
            } catch (Exception e) {
                registrationList = new ArrayList<RegistrationEO>();
                message = "Error loading registrations: " + e.getMessage();
            }
        }
        return registrationList;
    }

    public RegistrationEO getCurrentRegistration() {
        return currentRegistration;
    }

    public void setCurrentRegistration(RegistrationEO currentRegistration) {
        this.currentRegistration = currentRegistration;
    }

    public RegistrationEO getNewRegistration() {
        return newRegistration;
    }

    public void setNewRegistration(RegistrationEO newRegistration) {
        this.newRegistration = newRegistration;
    }

    public String getCurrentRegistrationDateStr() {
        return currentRegistrationDateStr;
    }

    public void setCurrentRegistrationDateStr(String currentRegistrationDateStr) {
        this.currentRegistrationDateStr = currentRegistrationDateStr;
    }

    public String getNewRegistrationDateStr() {
        return newRegistrationDateStr;
    }

    public void setNewRegistrationDateStr(String newRegistrationDateStr) {
        this.newRegistrationDateStr = newRegistrationDateStr;
    }

    public OwnerEO getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(OwnerEO newOwner) {
        this.newOwner = newOwner;
    }
}


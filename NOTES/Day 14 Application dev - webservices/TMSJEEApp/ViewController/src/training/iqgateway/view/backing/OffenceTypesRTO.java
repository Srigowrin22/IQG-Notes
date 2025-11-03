package training.iqgateway.view.backing;

import training.iqgateway.model.OffenceTypesEO;
import training.iqgateway.services.RTOSessionEJBLocal;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.ArrayList;
import java.util.List;

import training.iqgateway.utils.Format;

public class OffenceTypesRTO {

    private List<OffenceTypesEO> offenceTypeList;
    private OffenceTypesEO selectedOffenceType;
    private boolean addMode = false;
    private boolean updateMode = false;
    private boolean deleteMode = false;
    private String newOffenceType;
    private Long newPenaltyAmt;
    private String newVehicleType;
    private String updateOffenceType;
    private Long updatePenaltyAmt;
    private String updateVehicleType;
    private String message;

    Format format = new Format();

    // EJB Lookup

    public RTOSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        return (RTOSessionEJBLocal)ic.lookup("java:comp/env/ejb/local/RTOSessionEJB");
    }

    // Load all offence types

    public List<OffenceTypesEO> getOffenceTypeList() {
        if (offenceTypeList == null)
            reloadOffenceTypes();
        return offenceTypeList;
    }

    private void reloadOffenceTypes() {
        try {
            offenceTypeList = getSessionBean().getOffenceTypesEOFindAll();
        } catch (Exception e) {
            offenceTypeList = new ArrayList<OffenceTypesEO>();
            message = "Error loading offence types";
            e.printStackTrace();
        }
    }

    // Add

    public void showAddCard() {
        addMode = true;
        updateMode = false;
        deleteMode = false;
        newOffenceType = "";
        newPenaltyAmt = null;
        newVehicleType = "";
        message = null;
    }

    public void saveNewOffenceType() {
        try {
            // Validation
            if (newOffenceType == null || newOffenceType.trim().isEmpty()) {
                message = "Offence type is required!";
                return;
            }
            if (!format.isOnlyLetters(newOffenceType)) {
                message =
                        "Offence type should contain only alphabets and be at least 3 characters.";
                return;
            }
            if (newPenaltyAmt == null || newPenaltyAmt <= 0) {
                message = "Penalty amount is required and must be positive!";
                return;
            }
            if (newVehicleType == null || newVehicleType.trim().isEmpty()) {
                message = "Vehicle type is required!";
                return;
            }
            if (!format.isOnlyLetters(newVehicleType)) {
                message =
                        "Vehicle type should contain only alphabets and be at least 3 characters.";
                return;
            }
            // Check if offence type + vehicle type already exists
            if (getSessionBean().findOffenceByTypeAndVehicle(newOffenceType.trim().toUpperCase(),
                                                             newVehicleType.trim().toUpperCase()) !=
                null) {
                message =
                        "This offence type for the vehicle type already exists!";
                return;
            }

            OffenceTypesEO newOffence = new OffenceTypesEO();
            newOffence.setOffenceType(newOffenceType.trim());
            newOffence.setPenaltyAmt(newPenaltyAmt);
            newOffence.setVehicleType(newVehicleType.trim());
            getSessionBean().persistOffenceTypesEO(newOffence);
            message = "Offence type added successfully!";
            addMode = false;
            newOffenceType = "";
            newPenaltyAmt = null;
            newVehicleType = "";
            reloadOffenceTypes();
        } catch (Exception e) {
            message = "Error adding offence type";
            e.printStackTrace();
        }
    }

    public void cancelAdd() {
        addMode = false;
        newOffenceType = "";
        newPenaltyAmt = null;
        newVehicleType = "";
        message = null;
    }

    // Update

    public void prepareUpdate() {
        if (selectedOffenceType != null) {
            updateOffenceType = selectedOffenceType.getOffenceType();
            updatePenaltyAmt = selectedOffenceType.getPenaltyAmt();
            updateVehicleType = selectedOffenceType.getVehicleType();
            updateMode = true;
            addMode = false;
            deleteMode = false;
            message = null;
        }
    }

    public void saveUpdate() {
        try {
            if (selectedOffenceType != null && updateOffenceType != null &&
                !updateOffenceType.trim().isEmpty() &&
                updatePenaltyAmt != null && updatePenaltyAmt > 0 &&
                updateVehicleType != null &&
                !updateVehicleType.trim().isEmpty()) {

                // Check for duplicate (excluding the current selected offence type)
                OffenceTypesEO existing =
                    getSessionBean().findOffenceByTypeAndVehicle(updateOffenceType.trim().toUpperCase(),
                                                                 updateVehicleType.trim().toUpperCase());
                if (existing != null &&
                    !existing.getOffenceId().equals(selectedOffenceType.getOffenceId())) {
                    message =
                            "Another offence type with this name and vehicle type already exists!";
                    return;
                }

                selectedOffenceType.setOffenceType(updateOffenceType.trim());
                selectedOffenceType.setPenaltyAmt(updatePenaltyAmt);
                selectedOffenceType.setVehicleType(updateVehicleType.trim());
                getSessionBean().mergeOffenceTypeEO(selectedOffenceType);
                message = "Offence type updated successfully!";
                updateMode = false;
                updateOffenceType = "";
                updatePenaltyAmt = null;
                updateVehicleType = "";
                reloadOffenceTypes();
            } else {
                message =
                        "All fields are required and penalty must be positive!";
            }
        } catch (Exception e) {
            message = "Error updating offence type"; 
            e.printStackTrace();
        }
    }

    public void cancelUpdate() {
        updateMode = false;
        updateOffenceType = "";
        updatePenaltyAmt = null;
        updateVehicleType = "";
        message = null;
    }

    // Delete

    public void prepareDelete() {
        deleteMode = true;
        updateMode = false;
        addMode = false;
        message = null;
    }

    public void delete() {
        try {
            if (selectedOffenceType != null) {
                Long deletedId = selectedOffenceType.getOffenceId();
                boolean deleted =
                    getSessionBean().removeOffenceTypeEO(deletedId.intValue());
                if (deleted) {
                    message =
                            "Offence type with ID " + deletedId + " deleted successfully!";
                    deleteMode = false;
                    reloadOffenceTypes();
                } else {
                    message =
                            "Offence type with ID " + deletedId + " not found!";
                }
            } else {
                message = "No offence type selected for deletion!";
            }
        } catch (Exception e) {
            message = "Error deleting offence type";
            e.printStackTrace();
        }


    }

    public void cancelDelete() {
        deleteMode = false;
        message = null;
    }

    // Getters and setters for all fields as needed...
    
    public void setOffenceTypeList(List<OffenceTypesEO> offenceTypeList) {
        this.offenceTypeList = offenceTypeList;
    }

    public void setSelectedOffenceType(OffenceTypesEO selectedOffenceType) {
        this.selectedOffenceType = selectedOffenceType;
    }

    public OffenceTypesEO getSelectedOffenceType() {
        return selectedOffenceType;
    }

    public void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }

    public boolean isAddMode() {
        return addMode;
    }

    public void setUpdateMode(boolean updateMode) {
        this.updateMode = updateMode;
    }

    public boolean isUpdateMode() {
        return updateMode;
    }

    public void setDeleteMode(boolean deleteMode) {
        this.deleteMode = deleteMode;
    }

    public boolean isDeleteMode() {
        return deleteMode;
    }

    public void setNewOffenceType(String newOffenceType) {
        this.newOffenceType = newOffenceType;
    }

    public String getNewOffenceType() {
        return newOffenceType;
    }

    public void setNewPenaltyAmt(Long newPenaltyAmt) {
        this.newPenaltyAmt = newPenaltyAmt;
    }

    public Long getNewPenaltyAmt() {
        return newPenaltyAmt;
    }

    public void setNewVehicleType(String newVehicleType) {
        this.newVehicleType = newVehicleType;
    }

    public String getNewVehicleType() {
        return newVehicleType;
    }

    public void setUpdateOffenceType(String updateOffenceType) {
        this.updateOffenceType = updateOffenceType;
    }

    public String getUpdateOffenceType() {
        return updateOffenceType;
    }

    public void setUpdatePenaltyAmt(Long updatePenaltyAmt) {
        this.updatePenaltyAmt = updatePenaltyAmt;
    }

    public Long getUpdatePenaltyAmt() {
        return updatePenaltyAmt;
    }

    public void setUpdateVehicleType(String updateVehicleType) {
        this.updateVehicleType = updateVehicleType;
    }

    public String getUpdateVehicleType() {
        return updateVehicleType;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Format getFormat() {
        return format;
    }
}

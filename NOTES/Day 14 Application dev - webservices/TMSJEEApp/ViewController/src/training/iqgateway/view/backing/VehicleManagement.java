package training.iqgateway.view.backing;

import java.sql.Timestamp;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import training.iqgateway.model.VehicleEO;
import training.iqgateway.services.RTOSessionEJBLocal;
import training.iqgateway.utils.Format;

public class VehicleManagement {


    private Format formatUtil = new Format();

    // List of vehicles (simulate DB)
    private List<VehicleEO> vehicleList;

    // Selected vehicle for update/delete
    private VehicleEO selectedVehicle;

    // Flags for UI modes
    private boolean addMode = false;
    private boolean updateMode = false;
    private boolean deleteMode = false;

    // New vehicle fields for Add form
    private Long newVehicleId;
    private String newVehicleBrand;
    private String newVehicleModel;
    private String newVehicleType;
    private String newColor;
    private String newFuelType;
    private String newManufactureDateStr; // String to accept user input
    private Long newNoOfExhaust;

    // Update vehicle fields
    private Long updateVehicleId;
    private String updateVehicleBrand;
    private String updateVehicleModel;
    private String updateVehicleType;
    private String updateColor;
    private String updateFuelType;
    private String updateManufactureDateStr;
    private Long updateNoOfExhaust;

    // Message for UI feedback
    private String message;

    public RTOSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        return (RTOSessionEJBLocal)ic.lookup("java:comp/env/ejb/local/RTOSessionEJB");
    }
    
    public List<VehicleEO> getVehicleList() {
        if (vehicleList == null)
            refreshVehicleList();
        return vehicleList;
    }

    private void refreshVehicleList() {
        try {
            vehicleList = getSessionBean().getVehicleEOFindAll();
        } catch (Exception e) {
            vehicleList = new ArrayList<VehicleEO>();
            message = "Error loading vehicles: " + e.getMessage();
        }
    }

    // --- Show Add Card ---

    public void showAddCard() {
        clearNewVehicleFields();
        addMode = true;
        updateMode = false;
        deleteMode = false;
        message = null;
    }

    // --- Cancel Add ---

    public void cancelAdd() {
        addMode = false;
        clearNewVehicleFields();
        message = null;
    }

    // --- Save New Vehicle ---

    public void saveNewVehicle() {
        message = null;
        if (!validateNewVehicle())
            return;

        try {
            Timestamp manufactureDate = parseDate(newManufactureDateStr);

            // Check duplicate
            if (getSessionBean().findVehicleByID(newVehicleId) != null) {
                message = "Vehicle ID already exists!";
                return;
            }

            VehicleEO v = new VehicleEO();
            v.setVehicleId(newVehicleId);
            v.setVehicleBrand(newVehicleBrand);
            v.setVehicleModel(newVehicleModel);
            v.setVehicleType(newVehicleType);
            v.setColor(newColor);
            v.setFuelType(newFuelType);
            v.setManufactureDate(manufactureDate);
            v.setNoOfExhaust(newNoOfExhaust);

            getSessionBean().persistVehicleEO(v);
            refreshVehicleList();

            message = "Vehicle added successfully!";
            addMode = false;
            clearNewVehicleFields();
        } catch (ParseException e) {
            message = "Invalid manufacture date format! Use yyyy-MM-dd.";
        } catch (Exception e) {
            message = "Error";
            e.printStackTrace();
            return;
        }
    }

    // --- Prepare Update ---

    public void prepareUpdate() {
        if (selectedVehicle == null) {
            message = "Select a vehicle to update.";
            return;
        }
        addMode = false;
        updateMode = true;
        deleteMode = false;
        message = null;

        updateVehicleId = selectedVehicle.getVehicleId();
        updateVehicleBrand = selectedVehicle.getVehicleBrand();
        updateVehicleModel = selectedVehicle.getVehicleModel();
        updateVehicleType = selectedVehicle.getVehicleType();
        updateColor = selectedVehicle.getColor();
        updateFuelType = selectedVehicle.getFuelType();
        updateManufactureDateStr =
                formatUtil.toDate(selectedVehicle.getManufactureDate());
        updateNoOfExhaust = selectedVehicle.getNoOfExhaust();
    }

    // --- Cancel Update ---

    public void cancelUpdate() {
        updateMode = false;
        clearUpdateVehicleFields();
        message = null;
    }

    // --- Save Update ---

    public void saveUpdate() {
        message = null;
        if (!validateUpdateVehicle())
            return;

        try {
            Timestamp manufactureDate = parseDate(updateManufactureDateStr);

            VehicleEO v = getSessionBean().findVehicleByID(updateVehicleId);
            if (v == null) {
                message = "Vehicle not found!";
                return;
            }

            v.setVehicleBrand(updateVehicleBrand);
            v.setVehicleModel(updateVehicleModel);
            v.setVehicleType(updateVehicleType);
            v.setColor(updateColor);
            v.setFuelType(updateFuelType);
            v.setManufactureDate(manufactureDate);
            v.setNoOfExhaust(updateNoOfExhaust);

            getSessionBean().mergeVehicleEO(v);
            refreshVehicleList();

            message = "Vehicle updated successfully!";
            updateMode = false;
            clearUpdateVehicleFields();
        } catch (ParseException e) {
            message = "Invalid manufacture date format! Use yyyy-MM-dd.";
        } catch (Exception e) {
            message = "Error!";
            return;
        }
    }

    // --- Prepare Delete ---

    public void prepareDelete() {
        if (selectedVehicle == null) {
            message = "Select a vehicle to delete.";
            return;
        }
        addMode = false;
        updateMode = false;
        deleteMode = true;
        message = null;
    }

    // --- Cancel Delete ---

    public void cancelDelete() {
        deleteMode = false;
        message = null;
    }

    // --- Delete Vehicle ---

    public void delete() {
        if (selectedVehicle == null) {
            message = "No vehicle selected for deletion.";
            return;
        }
        try {
            getSessionBean().removeVehicleEO(selectedVehicle.getVehicleId());
            message = "Vehicle deleted successfully!";
        } catch (Exception e) {
            message = "Error Deleting!";
            e.printStackTrace();
        }
        refreshVehicleList();
        deleteMode = false;
        selectedVehicle = null;
    }

    // --- Validation for Add ---

    private boolean validateNewVehicle() {
        if (newVehicleId == null) {
            message = "Vehicle ID is required.";
            return false;
        }
        if (isNullOrEmpty(newVehicleBrand)) {
            message = "Vehicle brand is required.";
            return false;
        }
        if (isNullOrEmpty(newVehicleModel)) {
            message = "Vehicle model is required.";
            return false;
        }
        if (isNullOrEmpty(newVehicleType)) {
            message = "Vehicle type is required.";
            return false;
        }
        if (newManufactureDateStr != null &&
            !newManufactureDateStr.trim().isEmpty()) {
            try {
                parseDate(newManufactureDateStr);
            } catch (ParseException e) {
                message = "Invalid manufacture date format! Use yyyy-MM-dd.";
                //                addMessage(message, FacesMessage.SEVERITY_ERROR);
                return false;
            }
        }
        return true;
    }

    // --- Validation for Update ---

    private boolean validateUpdateVehicle() {
        if (updateVehicleId == null) {
            message = "Vehicle ID is required.";
            return false;
        }
        if (isNullOrEmpty(updateVehicleBrand)) {
            message = "Vehicle brand is required.";
            return false;
        }
        if (isNullOrEmpty(updateVehicleModel)) {
            message = "Vehicle model is required.";
            return false;
        }
        if (isNullOrEmpty(updateVehicleType)) {
            message = "Vehicle type is required.";
            return false;
        }
        if (updateManufactureDateStr != null &&
            !updateManufactureDateStr.trim().isEmpty()) {
            try {
                parseDate(updateManufactureDateStr);
            } catch (ParseException e) {
                message = "Invalid manufacture date format! Use yyyy-MM-dd.";
                //                addMessage(message, FacesMessage.SEVERITY_ERROR);
                return false;
            }
        }
        return true;
    }

    // --- Utility: Parse date string to Timestamp ---

    private Timestamp parseDate(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        return formatUtil.convertStringToTimestamp(dateStr);
    }

    // --- Utility: Add FacesMessage ---

    //    private void addMessage(String msg, FacesMessage.Severity severity) {
    //        FacesContext.getCurrentInstance().addMessage(null,
    //                                                     new FacesMessage(severity,
    //                                                                      msg,
    //                                                                      null));
    //    }

    // --- Utility: Clear new vehicle fields ---

    private void clearNewVehicleFields() {
        newVehicleId = null;
        newVehicleBrand = null;
        newVehicleModel = null;
        newVehicleType = null;
        newColor = null;
        newFuelType = null;
        newManufactureDateStr = null;
        newNoOfExhaust = null;
    }

    // --- Utility: Clear update vehicle fields ---

    private void clearUpdateVehicleFields() {
        updateVehicleId = null;
        updateVehicleBrand = null;
        updateVehicleModel = null;
        updateVehicleType = null;
        updateColor = null;
        updateFuelType = null;
        updateManufactureDateStr = null;
        updateNoOfExhaust = null;
    }

    // --- Utility: Check null or empty ---

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    // --- Getters and Setters ---

    public void setVehicleList(List<VehicleEO> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public VehicleEO getSelectedVehicle() {
        return selectedVehicle;
    }

    public void setSelectedVehicle(VehicleEO selectedVehicle) {
        this.selectedVehicle = selectedVehicle;
    }

    public boolean isAddMode() {
        return addMode;
    }

    public void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }

    public boolean isUpdateMode() {
        return updateMode;
    }

    public void setUpdateMode(boolean updateMode) {
        this.updateMode = updateMode;
    }

    public boolean isDeleteMode() {
        return deleteMode;
    }

    public void setDeleteMode(boolean deleteMode) {
        this.deleteMode = deleteMode;
    }

    public Long getNewVehicleId() {
        return newVehicleId;
    }

    public void setNewVehicleId(Long newVehicleId) {
        this.newVehicleId = newVehicleId;
    }

    public String getNewVehicleBrand() {
        return newVehicleBrand;
    }

    public void setNewVehicleBrand(String newVehicleBrand) {
        this.newVehicleBrand = newVehicleBrand;
    }

    public String getNewVehicleModel() {
        return newVehicleModel;
    }

    public void setNewVehicleModel(String newVehicleModel) {
        this.newVehicleModel = newVehicleModel;
    }

    public String getNewVehicleType() {
        return newVehicleType;
    }

    public void setNewVehicleType(String newVehicleType) {
        this.newVehicleType = newVehicleType;
    }

    public String getNewColor() {
        return newColor;
    }

    public void setNewColor(String newColor) {
        this.newColor = newColor;
    }

    public String getNewFuelType() {
        return newFuelType;
    }

    public void setNewFuelType(String newFuelType) {
        this.newFuelType = newFuelType;
    }

    public String getNewManufactureDateStr() {
        return newManufactureDateStr;
    }

    public void setNewManufactureDateStr(String newManufactureDateStr) {
        this.newManufactureDateStr = newManufactureDateStr;
    }

    public Long getNewNoOfExhaust() {
        return newNoOfExhaust;
    }

    public void setNewNoOfExhaust(Long newNoOfExhaust) {
        this.newNoOfExhaust = newNoOfExhaust;
    }

    public Long getUpdateVehicleId() {
        return updateVehicleId;
    }

    public void setUpdateVehicleId(Long updateVehicleId) {
        this.updateVehicleId = updateVehicleId;
    }

    public String getUpdateVehicleBrand() {
        return updateVehicleBrand;
    }

    public void setUpdateVehicleBrand(String updateVehicleBrand) {
        this.updateVehicleBrand = updateVehicleBrand;
    }

    public String getUpdateVehicleModel() {
        return updateVehicleModel;
    }

    public void setUpdateVehicleModel(String updateVehicleModel) {
        this.updateVehicleModel = updateVehicleModel;
    }

    public String getUpdateVehicleType() {
        return updateVehicleType;
    }

    public void setUpdateVehicleType(String updateVehicleType) {
        this.updateVehicleType = updateVehicleType;
    }

    public String getUpdateColor() {
        return updateColor;
    }

    public void setUpdateColor(String updateColor) {
        this.updateColor = updateColor;
    }

    public String getUpdateFuelType() {
        return updateFuelType;
    }

    public void setUpdateFuelType(String updateFuelType) {
        this.updateFuelType = updateFuelType;
    }

    public String getUpdateManufactureDateStr() {
        return updateManufactureDateStr;
    }

    public void setUpdateManufactureDateStr(String updateManufactureDateStr) {
        this.updateManufactureDateStr = updateManufactureDateStr;
    }

    public Long getUpdateNoOfExhaust() {
        return updateNoOfExhaust;
    }

    public void setUpdateNoOfExhaust(Long updateNoOfExhaust) {
        this.updateNoOfExhaust = updateNoOfExhaust;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


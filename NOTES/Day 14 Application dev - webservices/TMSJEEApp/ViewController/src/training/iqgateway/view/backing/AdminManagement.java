package training.iqgateway.view.backing;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.model.AdminEO;
import training.iqgateway.model.RoleEO;
import training.iqgateway.services.AdminSessionEJBLocal;
import training.iqgateway.utils.Format;

public class AdminManagement implements Serializable {

    private String desigId;
    private String selectedRoleName; // For combo box
    private String name;
    private String phone;
    private String gender;
    private String aadhar;
    private String hiredate;
    private boolean showCard;
    private boolean addMode;
    private boolean editMode;
    private String message;
    private List<SelectItem> roleItems;
    private Long selectedRoleID;

    private AdminEO adminEO; // The current admin being viewed/edited

    // --- EJB Lookup ---

    public AdminSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        Object lookupObject =
            ic.lookup("java:comp/env/ejb/local/AdminSessionEJB");
        return (AdminSessionEJBLocal)lookupObject;
    }

    public List<SelectItem> getRoleItems() throws NamingException {
        if (roleItems == null) {
            roleItems = new ArrayList<SelectItem>();
            for (RoleEO role : getSessionBean().getRoleEOFindAll()) {
                roleItems.add(new SelectItem(role.getRoleId(),
                                             role.getRoleName()));
            }
        }
        return roleItems;
    }

    // --- Search for admin by Designation ID ---

    public void search() {
        try {
            adminEO = getSessionBean().findAdminByDesigID(desigId);
            if (adminEO != null) {
                populateFieldsFromAdminEO(adminEO);
                // Set selectedRoleName based on adminEO's role
                RoleEO foundRole = adminEO.getRoleEO();
                selectedRoleName =
                        (foundRole != null) ? foundRole.getRoleName() : null;
                showCard = true;
                addMode = false;
                editMode = false;
                message = null;
            } else {
                message = "Admin not found!";
                showCard = false;
            }
        } catch (Exception e) {
            message = "Error during search: " + e.getMessage();
            showCard = false;
        }
    }

    // --- Add new admin (show empty card) ---

    public void add() {
        System.out.println("Add button clicked");
        resetFields();
        showCard = true;
        addMode = true;
        editMode = true;
        message = null;
        System.out.println(desigId+" "+roleItems+" "+hiredate);
    }

    // --- Enable edit mode for existing admin ---

    public void enableEdit() {
        System.out.println("Edit button clicked!");
        this.editMode = true;
        this.addMode = false;
        message = null;

    }

    // --- Update existing admin ---
//
//    public void update() {
//        try {
//            adminEO = getSessionBean().findAdminByDesigID(desigId);
//            if (adminEO == null) {
//                message = "No admin loaded to update!";
//                return;
//            }
//            // Only update editable fields
//            adminEO.setName(name);
//            adminEO.setPhone(Long.parseLong(phone));
//            adminEO.setGender(gender);
//            adminEO.setAadhar(aadhar);
//            adminEO.setHireDate(Timestamp.valueOf(hiredate));
//
//            // Role and desigId are not editable in edit mode
//
//            if (getSessionBean().mergeAdminEO(adminEO) != null) {
//                editMode = false;
//                message = "Admin updated successfully!";
//            }
//        } catch (Exception e) {
//            message = "Update error";
//            e.printStackTrace();
//        }
//    }

public void update() {
    try {
        adminEO = getSessionBean().findAdminByDesigID(desigId);
        if (adminEO == null) {
            message = "No admin loaded to update!";
            return;
        }

        // Validate name (only letters, min 3 chars)
        if (name == null || !name.matches("[a-zA-Z]{3,}")) {
            message = "Name must contain at least 3 letters and only alphabets.";
            return;
        }

        // Validate Aadhar (12 digits, spaces allowed)
        if (aadhar == null || !aadhar.matches("^(?:\\d\\s*){12}$")) {
            message = "Aadhar number must contain exactly 12 digits; spaces allowed.";
            return;
        }

        // Validate phone (numeric, exactly 10 digits)
        long phoneLong;
        try {
            phoneLong = Long.parseLong(phone);
        } catch (NumberFormatException e) {
            message = "Phone must be numeric.";
            return;
        }
        if (!phone.matches("\\d{10}")) {
            message = "Phone number must be exactly 10 digits.";
            return;
        }

        // Validate hiredate format (yyyy-MM-dd HH:mm:ss)
        Timestamp hireDate;
        try {
            hireDate = Timestamp.valueOf(hiredate);
        } catch (IllegalArgumentException e) {
            message = "Invalid hire date format! Expected format: yyyy-MM-dd HH:mm:ss";
            return;
        }

        // Update editable fields
        adminEO.setName(name);
        adminEO.setPhone(phoneLong);
        adminEO.setGender(gender);
        adminEO.setAadhar(aadhar);
        adminEO.setHireDate(hireDate);

        // Role and desigId are not editable in edit mode

        if (getSessionBean().mergeAdminEO(adminEO) != null) {
            editMode = false;
            message = "Admin updated successfully!";
        }
    } catch (Exception e) {
        message = "Update error";
        e.printStackTrace();
    }
}

    // --- Delete admin ---

    public void delete() {
        try {
            adminEO = getSessionBean().findAdminByDesigID(desigId);
            if (adminEO == null) {
                message = "No admin loaded to delete!";
                return;
            }
            if (getSessionBean().removeAdminEO(adminEO)) {
                showCard = false;
                message = "Admin deleted successfully!";
            }
        } catch (Exception e) {
            message = "Delete error" ;
            e.printStackTrace();
        }
    }

    // --- Save new admin ---

//    public void save() {
//        try {
//            System.out.println("Selected Role ID: " + selectedRoleID);
//            RoleEO roleEO = getSessionBean().findRoleByRoleID(selectedRoleID);
//            System.out.println("Found RoleEO: " + roleEO);
//
//            // Validate phone number format
//            long phoneLong = Long.parseLong(phone);
//            System.out.println("Phone (parsed): " + phoneLong);
//
//            // Validate hire date format
//            Timestamp hireDate = Timestamp.valueOf(hiredate);
//            System.out.println("Hire Date (parsed): " + hireDate);
//
//            AdminEO newAdmin = new AdminEO();
//            newAdmin.setName(name);
//            newAdmin.setPhone(phoneLong);
//            newAdmin.setGender(gender);
//            newAdmin.setAadhar(aadhar);
//            newAdmin.setHireDate(hireDate);
//            newAdmin.setRoleEO(roleEO);
//
//            System.out.println("Attempting to persist AdminEO: " + newAdmin);
//            newAdmin = getSessionBean().persistAdminEO(newAdmin);
//            message = "Admin added successfully! Designation ID:" + newAdmin.getDesignationId();
//            showCard = false;
//        } catch (NumberFormatException e) {
//            message = "Invalid phone number format!";
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            message = "Invalid hire date format! Expected format: yyyy-MM-dd HH:mm:ss";
//            e.printStackTrace();
//        } catch (Exception e) {
//            message = "Error during add";
//            e.printStackTrace();
//        }
//    }
    
    private Format formatUtil = new Format();

    public void save() {
    try {
        // Validate name: only letters, min length 3
        if (name == null || !formatUtil.isOnlyLetters(name)) {
            message = "Name must contain at least 3 letters and only alphabets.";
            return;
        }

        // Validate Aadhar
        String aadharValidationMsg = formatUtil.validateAadhar(aadhar);
        if (aadharValidationMsg != null) {
            message = aadharValidationMsg;
            return;
        }

        // Validate phone (string to long + format)
        long phoneLong;
        try {
            phoneLong = Long.parseLong(phone);
        } catch (NumberFormatException e) {
            message = "Phone must be numeric.";
            return;
        }
        String phoneValidationMsg = formatUtil.validatePhone(phoneLong);
        if (phoneValidationMsg != null) {
            message = phoneValidationMsg;
            return;
        }

        // Validate hiredate format (string to Timestamp)
        Timestamp hireDate;
        try {
            hireDate = Timestamp.valueOf(hiredate);
        } catch (IllegalArgumentException e) {
            message = "Invalid hire date format! Expected format: yyyy-MM-dd HH:mm:ss";
            return;
        }

        // Lookup RoleEO
        RoleEO roleEO = getSessionBean().findRoleByRoleID(selectedRoleID);
        if (roleEO == null) {
            message = "Selected Role not found.";
            return;
        }

        // Create and persist AdminEO
        AdminEO newAdmin = new AdminEO();
        newAdmin.setName(name);
        newAdmin.setPhone(phoneLong);
        newAdmin.setGender(gender);
        newAdmin.setAadhar(aadhar);
        newAdmin.setHireDate(hireDate);
        newAdmin.setRoleEO(roleEO);

        newAdmin = getSessionBean().persistAdminEO(newAdmin);

        message = "Admin added successfully! Designation ID: " + newAdmin.getDesignationId();
        showCard = false;

    } catch (Exception e) {
        message = "Error during add: " + e.getMessage();
        e.printStackTrace();
    }
}


    // --- Cancel action (hide card) ---

    public void cancel() {
        System.out.println("Cancel clicked");
        showCard = false;
        addMode = false;
        editMode = false;
        message = null;
        adminEO = null;
    }

    // --- Helper: Populate fields from AdminEO ---

    private void populateFieldsFromAdminEO(AdminEO admin) {
        desigId = admin.getDesignationId();
        name = admin.getName();
        phone = admin.getPhone().toString();
        gender = admin.getGender();
        aadhar = admin.getAadhar();
        hiredate = admin.getHireDate().toString();
    }

    // --- Helper: Reset all fields for Add ---

    private void resetFields() {
        desigId = "";
        selectedRoleName = "";
        name = "";
        phone = "";
        gender = "";
        aadhar = "";
        hiredate = "";
        adminEO = null;
    }

    // --- Getters and Setters ---

    public String getDesigId() {
        return desigId;
    }

    public void setDesigId(String desigId) {
        this.desigId = desigId;
    }

    public String getSelectedRoleName() {
        return selectedRoleName;
    }

    public void setSelectedRoleName(String selectedRoleName) {
        this.selectedRoleName = selectedRoleName;
    }

    public Long getSelectedRoleID() {
        return selectedRoleID;
    }

    public void setSelectedRoleID(Long selectedRoleID) {
        this.selectedRoleID = selectedRoleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public boolean isShowCard() {
        return showCard;
    }

    public void setShowCard(boolean showCard) {
        this.showCard = showCard;
    }

    public boolean isAddMode() {
        return addMode;
    }

    public void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


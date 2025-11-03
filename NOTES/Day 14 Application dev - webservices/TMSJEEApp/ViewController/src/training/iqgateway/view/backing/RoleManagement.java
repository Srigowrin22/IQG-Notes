package training.iqgateway.view.backing;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlForm;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.model.RoleEO;
import training.iqgateway.services.AdminSessionEJBLocal;
import training.iqgateway.utils.Format;

public class RoleManagement implements Serializable {

    private HtmlForm form1;
    private List<RoleEO> roleList;
    private RoleEO selectedRole;
    private boolean addMode = false;
    private boolean updateMode = false;
    private boolean deleteMode = false;
    private String newRoleName;
    private String updateRoleName;
    private String message;
    
    Format format = new Format();

    // EJB Lookup

    public AdminSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        return (AdminSessionEJBLocal)ic.lookup("java:comp/env/ejb/local/AdminSessionEJB");
    }

    // Load all roles

    public List<RoleEO> getRoleList() {
        if (roleList == null)
            reloadRoles();
        return roleList;
    }

    private void reloadRoles() {
        try {
            roleList = getSessionBean().getRoleEOFindAll();
        } catch (Exception e) {
            roleList = new ArrayList();
            message = "Error loading roles: " + e.getMessage();
        }
    }

    // Add

    public void showAddCard() {
        addMode = true;
        updateMode = false;
        deleteMode = false;
        newRoleName = "";
        message = null;
    }

    public void saveNewRole() {
        try {
            if (newRoleName == null || newRoleName.trim().isEmpty()) {
                message = "Role name is required!";
                return;
            }
            if(!format.isOnlyLetters(newRoleName)){
                message = "Role should contain only aplhabets [a-zA-Z]{3,}";
                return;
            }
            if(getSessionBean().findRoleByRoleName(newRoleName.toUpperCase())!=null){
                message = "Role already exists check the table!";
                return;
            }
            
            RoleEO newRole = new RoleEO();
            newRole.setRoleName(newRoleName.trim());
            getSessionBean().persistRoleEO(newRole);
            message = "Role added successfully!";
            addMode = false;
            newRoleName = "";
            reloadRoles();
        } catch (Exception e) {
            message = "Error adding role";
            e.printStackTrace();
                
        }
    }

    public void cancelAdd() {
        addMode = false;
        newRoleName = "";
        message = null;
    }

    // Update

    public void prepareUpdate() {
        if (selectedRole != null) {
            updateRoleName = selectedRole.getRoleName();
            updateMode = true;
            addMode = false;
            deleteMode = false;
            message = null;
        }
    }

    public void saveUpdate() {
        try {
            if (selectedRole != null && updateRoleName != null &&
                !updateRoleName.trim().isEmpty()) {
                selectedRole.setRoleName(updateRoleName.trim());
                getSessionBean().mergeRoleEO(selectedRole);
                message = "Role updated successfully!";
                updateMode = false;
                updateRoleName = "";
                reloadRoles();
            } else {
                message = "Role name is required!";
            }
        } catch (Exception e) {
            message = "Error updating role: " + e.getMessage();
        }
    }

    public void cancelUpdate() {
        updateMode = false;
        updateRoleName = "";
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
            if (selectedRole != null) {
                getSessionBean().removeRoleEO(selectedRole);
                message = "Role deleted successfully!";
                deleteMode = false;
                reloadRoles();
            } else {
                message = "No role selected for deletion!";
            }
        } catch (Exception e) {
            message = "Error deleting role: " + e.getMessage();
        }
    }

    public void cancelDelete() {
        deleteMode = false;
        message = null;
    }

    // Getters and setters

    public RoleEO getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(RoleEO selectedRole) {
        this.selectedRole = selectedRole;
    }

    public boolean isAddMode() {
        return addMode;
    }

    public boolean isUpdateMode() {
        return updateMode;
    }

    public boolean isDeleteMode() {
        return deleteMode;
    }

    public String getNewRoleName() {
        return newRoleName;
    }

    public void setNewRoleName(String newRoleName) {
        this.newRoleName = newRoleName;
    }

    public String getUpdateRoleName() {
        return updateRoleName;
    }

    public void setUpdateRoleName(String updateRoleName) {
        this.updateRoleName = updateRoleName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setForm1(HtmlForm form1) {
        this.form1 = form1;
    }

    public HtmlForm getForm1() {
        return form1;
    }
}

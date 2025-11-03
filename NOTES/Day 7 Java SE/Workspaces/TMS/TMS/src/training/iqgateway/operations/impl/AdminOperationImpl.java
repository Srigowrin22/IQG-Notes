package training.iqgateway.operations.impl;


import java.util.ArrayList;
import java.util.List;

import training.iqgateway.dao.AdminDAO;
import training.iqgateway.dao.RoleDAO;
import training.iqgateway.entities.AdminEO;
import training.iqgateway.entities.RoleEO;
import training.iqgateway.factory.GenericDAOFactory;
import training.iqgateway.operations.AdminOperation;

public class AdminOperationImpl implements AdminOperation {
    
    AdminDAO adminOpRef = GenericDAOFactory.createAdminDAO();
    RoleDAO roleOpRef = GenericDAOFactory.createRoleDAO();

    public String addAdmin(AdminEO adminEO) {
        
        if (adminEO.getRoleID() == null || adminEO.getName() == null ||
            adminEO.getAadhar() == null) {
            return "Error : Incomplete fields";
        }
        if (adminOpRef.findAdminByAadhar(adminEO.getAadhar()) != null) {
            return "Admin already exists";
        }
        Integer check = adminOpRef.insertAdmin(adminEO);
        return check == 1 ? "Succesfully added" : "Failed to add";
    }

    public String modifyAdmin(AdminEO adminEO) {
        if (adminEO.getName() == null ||
            adminEO.getDesignationID() == null ||
            adminEO.getAadhar() == null) {
            return "Error : Incomplete fields";
        }
        Integer check = adminOpRef.updateAdmin(adminEO);
        return check == 1 ? "Succesfully updated" : "Failed to update";
    }
    
    public String authorize(AdminEO adminEO) {
        String desigID = adminEO.getDesignationID();
        String pass = adminEO.getPassword();
        if (adminEO.getDesignationID() == null ||
            adminEO.getAadhar() == null || 
            adminEO.getPassword() == null) {
            return "Error : Incomplete fields";
        }
        Integer check = adminOpRef.authorizeAdmin(desigID, pass);
        return check == 1 ? "Succesfully authorized" : "Failed to authorize";
    }

    public String removeAdmin(AdminEO adminEO) {
        Integer check = adminOpRef.deleteAdmin(adminEO.getDesignationID());
        return check != 0 ? "Succesfully deleted" : "Failed to delete";
    }

    public AdminEO findAdminByDesigID(String designationID) {
        AdminEO adminEO = adminOpRef.findAdminByDesigID(designationID);
        return adminEO != null ? adminEO : null;
    }
    
    public AdminEO findAdminByAadhar(String aadhar) {
        AdminEO adminEO = adminOpRef.findAdminByAadhar(aadhar);
        return adminEO != null ? adminEO : null;
    }

    public List<AdminEO> listAllAdmin() {
        List<AdminEO> adminEOList = adminOpRef.findAllAdmins();
        return adminEOList;
    }

    public List<AdminEO> findAdminByRoleID(Integer roleID) {
        List<AdminEO> adminEOList = adminOpRef.findAdminByRoleID(roleID);
        return adminEOList;
    }
    
    public List<AdminEO> findAdminByName(String name) {
        List<AdminEO> adminEOList = adminOpRef.findAdminByName(name);
        return adminEOList;
    }
    
    public String addRole (RoleEO roleEO){
        if(roleEO.getRoleName() == null) return "Error : Incomplete fields";
        List<RoleEO> role = new ArrayList<RoleEO>();
        role = roleOpRef.findRoleByRoleName(roleEO.getRoleName());             
        if(role.size() != 0) return "Role already exists";
        Integer check = roleOpRef.insertRole(roleEO);
        return check == 1 ? "Succesfully added" : "Failed to add";
    }
    
    public String modifyRole (RoleEO roleEO){
        if(roleEO.getRoleName() == null) return "Error : Incomplete fields";
        Integer check = roleOpRef.updateRole(roleEO);
        return check == 1 ? "Succesfully updated" : "Failed to update";
    }
    
    public String removeRole (Integer roleID){
        Integer check = roleOpRef.deleteRole(roleID);
        return check != 0 ? "Succesfully deleted" : "Failed to delete";
    }

    public RoleEO findRoleByRoleID (Integer roleID){
        RoleEO roleEO = roleOpRef.findRoleByRoleID(roleID);
        return roleEO;
    }
    
    public List<RoleEO> findRoleByRoleName (String roleName){
        List<RoleEO> roleEOList = roleOpRef.findRoleByRoleName(roleName);
        return roleEOList;
    }
    
    public List<RoleEO> findAllRoles(){
        List<RoleEO> roleEOList = roleOpRef.findAllRoles();
        return roleEOList;
    }
    
    public boolean login(Object admEO) {
        AdminEO adminEO = null;
        if(admEO instanceof AdminEO) {
            adminEO = (AdminEO)admEO; 
        }
        if(adminEO.getName().equals("") 
           || adminEO.getDesignationID().equals("")
           || adminEO.getPassword().equals("")){
            System.out.println("Error: Incomplete Fields");
            return false;
        }
        AdminEO user = adminOpRef.findAdminByDesigID(adminEO.getDesignationID());
        if(user!= null){
            if (user.getName().equals(adminEO.getName()) &&
                user.getPassword().equals(adminEO.getPassword()) &&
                user.getSignup()==1){
                return true;
            }
        }
        return false;
    }

    public boolean logout(Object adminEO) {
        return false;
    }
}

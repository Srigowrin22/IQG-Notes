package training.iqgateway.model;

import java.util.List;

import javax.ejb.Remote;

import training.iqgateway.entities.AdminEO;
import training.iqgateway.entities.RoleEO;

@Remote
public interface AdminEJB {
    
    public String addAdmin(AdminEO adminEO);

    public String modifyAdmin(AdminEO adminEO);
    
    public String authorize(AdminEO adminEO);

    public String removeAdmin(AdminEO adminEO); 
    
    public AdminEO findAdminByDesigID(String designationID);
    
    public AdminEO findAdminByAadhar (String aadhar);
    
    public List<AdminEO> listAllAdmin();
    
    public List<AdminEO> findAdminByRoleID(Integer roleID);
    
    public List<AdminEO> findAdminByName (String name);
    
    public String addRole (RoleEO roleEO);
    
    public String modifyRole (RoleEO roleEO);
    
    public String removeRole (Integer roleID);

    public RoleEO findRoleByRoleID (Integer roleID);
    
    public List<RoleEO> findRoleByRoleName (String roleName);
    
    public List<RoleEO> findAllRoles();
    
}

package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.AdminEO;
import training.iqgateway.entities.RoleEO;

public interface AdminDAO {
    
    Integer insertAdmin (AdminEO adminEO);
    
    Integer updateAdmin (AdminEO adminEO);
    
    Integer authorizeAdmin (String designationID, String password);
    
    Integer deleteAdmin (String designationID);

    AdminEO findAdminByDesigID(String designationID);
    
    AdminEO findAdminByAadhar (String aadhar);
    
    List<AdminEO> findAllAdmins();
    
    List<AdminEO> findAdminByRoleID(Integer roleID);
    
    List<AdminEO> findAdminByName (String name);
       
}

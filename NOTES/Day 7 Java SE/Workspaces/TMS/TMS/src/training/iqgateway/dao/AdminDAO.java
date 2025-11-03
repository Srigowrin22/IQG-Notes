package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.AdminEO;

public interface AdminDAO {
    
    int insertAdmin (AdminEO adminEO);
    
    int updateAdmin (AdminEO adminEO);
    
    int authorizeAdmin (String designationID, String password);
    
    int deleteAdmin (String designationID);

    AdminEO findAdminByDesigID(String designationID);
    
    AdminEO findAdminByAadhar (String aadhar);
    
    List<AdminEO> findAllAdmins();
    
    List<AdminEO> findAdminByRoleID(Integer roleID);
    
    List<AdminEO> findAdminByName (String name);
       
}

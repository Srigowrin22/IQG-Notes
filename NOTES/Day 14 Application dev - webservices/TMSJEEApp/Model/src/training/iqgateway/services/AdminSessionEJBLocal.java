package training.iqgateway.services;

import java.util.List;

import javax.ejb.Local;

import training.iqgateway.model.AdminEO;
import training.iqgateway.model.RoleEO;

@Local
public interface AdminSessionEJBLocal {

    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    // AdminEO CRUD and business methods

    AdminEO persistAdminEO(AdminEO adminEO);

    AdminEO mergeAdminEO(AdminEO adminEO);

    Boolean removeAdminEO(AdminEO adminEO);

    int authorizeAdmin(String designationId, String password,
                       String newPassword);

    List<AdminEO> getAdminEOFindAll();

    AdminEO findAdminByDesigID(String designationId);

    AdminEO findAdminByAadhar(String aadhar);

    List<AdminEO> findAdminByRoleID(Long roleId);

    List<AdminEO> findAdminByName(String name);

    // RoleEO CRUD and business methods

    RoleEO persistRoleEO(RoleEO roleEO);

    RoleEO mergeRoleEO(RoleEO roleEO);

    Boolean removeRoleEO(RoleEO roleEO);

    List<RoleEO> getRoleEOFindAll();

    RoleEO findRoleByRoleID(Long roleId);

    RoleEO findRoleByRoleName(String roleName);

    Boolean login(AdminEO adminEO);
}

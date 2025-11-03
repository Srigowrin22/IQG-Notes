package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.RoleEO;

public interface RoleDAO {

    int insertRole (RoleEO roleEO);
    
    int countRoles ();
    
    int updateRole (RoleEO roleEO);
    
    int deleteRole (Integer roleID);

    RoleEO findRoleByRoleID (Integer roleID);
    
    List<RoleEO> findRoleByRoleName (String roleName);
    
    List<RoleEO> findAllRoles();
    
}

package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.RoleEO;

public interface RoleDAO {

	Integer insertRole (RoleEO roleEO);
    
	Integer countRoles ();
    
	Integer updateRole (RoleEO roleEO);
    
	Integer deleteRole (Integer roleID);

    RoleEO findRoleByRoleID (Integer roleID);
    
    List<RoleEO> findRoleByRoleName (String roleName);
    
    List<RoleEO> findAllRoles();
    
}

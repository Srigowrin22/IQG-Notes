package training.iqgateway.service;

import java.util.List;

import training.iqgateway.dto.RolesDTO;
import training.iqgateway.entities.RoleEO;

public interface RolesService {

	String persistRoleEO(RoleEO roleEO);

	String mergeRoleEO(RoleEO roleEO);

	Boolean removeRoleEO(RoleEO roleEO);

	List<RolesDTO> getRoleEOFindAll();

	RoleEO findRoleByRoleID(Long roleId);

	RoleEO findRoleByRoleName(String roleName);
}

package training.iqgateway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import training.iqgateway.dto.RolesDTO;
import training.iqgateway.entities.RoleEO;
import training.iqgateway.repository.RolesRepository;
import training.iqgateway.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService{
	
	@Autowired
	private RolesRepository rolesRepositoryRef;
	
	public String persistRoleEO(RoleEO roleEO) {
		rolesRepositoryRef.save(roleEO);
		return "Success";
	}

	public String mergeRoleEO(RoleEO roleEO) {
		rolesRepositoryRef.save(roleEO);
		return "Updated";
	}

	public Boolean removeRoleEO(RoleEO roleEO) {
		rolesRepositoryRef.delete(roleEO);
		return true;
	}

	public List<RolesDTO> getRoleEOFindAll() {
		List<RoleEO> roles = rolesRepositoryRef.findAll();
		List<RolesDTO> rolesDTO = new ArrayList<RolesDTO>();
		for (RoleEO role : roles) {
			RolesDTO dto = new RolesDTO(role.getRoleId(), role.getRoleName());
			rolesDTO.add(dto);
		}
		return rolesDTO;
	}

	public RoleEO findRoleByRoleID(Long roleId) {
		return rolesRepositoryRef.findById(roleId).orElse(null);
	}

	public RoleEO findRoleByRoleName(String roleName) {
		return rolesRepositoryRef.findByroleName(roleName);
	}
}

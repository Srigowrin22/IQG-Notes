package training.iqgateway.service;

import java.util.List;

import training.iqgateway.dto.AdminDTO;
import training.iqgateway.entities.AdminEO;

public interface AdminService {

	AdminDTO persistAdminEO(AdminEO adminEO);

	String mergeAdminEO(AdminEO adminEO);

	Boolean removeAdminEO(AdminEO adminEO);

	int authorizeAdmin(String designationId, String password, String newPassword);

	List<AdminDTO> getAdminEOFindAll();

	AdminDTO findAdminByDesigID(String designationId);

	AdminDTO findAdminByAadhar(String aadhar);

	List<AdminDTO> findAdminByRoleID(Long roleId);

	List<AdminDTO> findAdminByName(String name);

	Boolean login(AdminEO adminEO);

}

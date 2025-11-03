package training.iqgateway.dto;

public class RolesDTO {
	private Long roleId;
	private String roleName;

	public RolesDTO() {
		super();
	}

	public RolesDTO(Long roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}

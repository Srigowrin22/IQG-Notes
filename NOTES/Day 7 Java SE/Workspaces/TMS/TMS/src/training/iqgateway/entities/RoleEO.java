package training.iqgateway.entities;

public class RoleEO {
    
    private Integer roleID;
    private String roleName;
    
    public RoleEO() {
        super();
    }

    public RoleEO(Integer roleID, String roleName) {
        super();
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
    
    @Override
    public String toString() {
        return "Roles Details [Role ID: " + this.roleID +
            "\t" + "Role Name: " + this.roleName + "]\n";
    }
}

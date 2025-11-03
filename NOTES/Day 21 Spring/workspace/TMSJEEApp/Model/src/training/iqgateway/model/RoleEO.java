package training.iqgateway.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "RoleEO.findAll",
                             query = "select o from RoleEO o") })
@Table(name = "ROLE")
public class RoleEO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "SEQ_ROLE", allocationSize = 1)
    @Column(name = "ROLE_ID", nullable = false)
    private Long roleId;
    @Column(name = "ROLE_NAME", nullable = false, length = 100)
    private String roleName;
    @OneToMany(mappedBy = "roleEO")
    private List<AdminEO> adminEOList;

    public RoleEO() {
    }

    public RoleEO(Long roleId, String roleName) {
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

    public List<AdminEO> getAdminEOList() {
        return adminEOList;
    }

    public void setAdminEOList(List<AdminEO> adminEOList) {
        this.adminEOList = adminEOList;
    }

    public AdminEO addAdminEO(AdminEO adminEO) {
        getAdminEOList().add(adminEO);
        adminEO.setRoleEO(this);
        return adminEO;
    }

    public AdminEO removeAdminEO(AdminEO adminEO) {
        getAdminEOList().remove(adminEO);
        adminEO.setRoleEO(null);
        return adminEO;
    }
}

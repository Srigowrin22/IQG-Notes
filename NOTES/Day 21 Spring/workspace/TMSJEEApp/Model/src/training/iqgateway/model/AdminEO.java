package training.iqgateway.model;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "AdminEO.findAll",
                             query = "select o from AdminEO o") })
@Table(name = "ADMIN")
public class AdminEO implements Serializable {
    @Column(nullable = false, unique = true, length = 50)
    private String aadhar;
    @Id
    @Column(name = "DESIGNATION_ID", nullable = false, length = 50)
    private String designationId;
    @Column(length = 2)
    private String gender;
    @Column(name = "HIRE_DATE")
    private Timestamp hireDate;
    @Column(unique = true)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(length = 50)
    private String password;
    private Long phone;
    private Long signup;
    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private RoleEO roleEO;

    public AdminEO() {
    }

    public AdminEO(String aadhar, String designationId, String gender,
                   Timestamp hireDate, Long id, String name, String password,
                   Long phone, RoleEO roleEO, Long signup) {
        this.aadhar = aadhar;
        this.designationId = designationId;
        this.gender = gender;
        this.hireDate = hireDate;
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.roleEO = roleEO;
        this.signup = signup;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getDesignationId() {
        return designationId;
    }

    public void setDesignationId(String designationId) {
        this.designationId = designationId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getHireDate() {
        return hireDate;
    }

    public void setHireDate(Timestamp hireDate) {
        this.hireDate = hireDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }


    public Long getSignup() {
        return signup;
    }

    public void setSignup(Long signup) {
        this.signup = signup;
    }

    public RoleEO getRoleEO() {
        return roleEO;
    }

    public void setRoleEO(RoleEO roleEO) {
        this.roleEO = roleEO;
    }
}

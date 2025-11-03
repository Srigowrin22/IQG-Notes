package training.iqgateway.entities;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "Admin")
public class AdminEO {

	@Id
	@Column(name = "designation_id", length = 50)
	private String designation_id;
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
    @SequenceGenerator(name = "admin_seq", sequenceName = "seq_admin", allocationSize = 1)
	@Column(name = "ID", unique = true)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private RoleEO role_id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(columnDefinition = "varchar2(2) default 'NA'")
	private String gender;

	@Column(unique = true, nullable = false, length = 50)
	private String aadhar;

	private Long phone;

	@Column(columnDefinition = "VARCHAR2(50) DEFAULT 'adm123'")
	private String password;

	@Column(name = "hire_date", columnDefinition = "DATE DEFAULT SYSDATE")
	private Date hire_date;

	@Column(columnDefinition = "NUMBER(1) DEFAULT 0")
	private Integer signup;

	public AdminEO() {
	}

	public AdminEO(Integer id, RoleEO roleID, String designationID, String name, String gender, String aadhar,
			Long phone, String password, Date hireDate, Integer signup) {
		this.id = id;
		this.role_id = roleID;
		this.designation_id = designationID;
		this.name = name;
		this.gender = gender;
		this.aadhar = aadhar;
		this.phone = phone;
		this.password = password;
		this.hire_date = hireDate;
		this.signup = signup;
	}
	
	public AdminEO(RoleEO roleID, String designationID, String name, String gender, String aadhar,
			Long phone, String password, Date hireDate, Integer signup) {
		this.role_id = roleID;
		this.designation_id = designationID;
		this.name = name;
		this.gender = gender;
		this.aadhar = aadhar;
		this.phone = phone;
		this.password = password;
		this.hire_date = hireDate;
		this.signup = signup;
	}

	// Getters and Setters (same as original, unchanged)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleEO getRole_id() {
		return role_id;
	}

	public void setRole_id(RoleEO roleID) {
		this.role_id = roleID;
	}

	public String getDesignationID() {
		return designation_id;
	}

	public void setDesignationID(String designationID) {
		this.designation_id = designationID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getHireDate() {
		return hire_date;
	}

	public void setHireDate(Date hireDate) {
		this.hire_date = hireDate;
	}

	public Integer getSignup() {
		return signup;
	}

	public void setSignup(Integer signup) {
		this.signup = signup;
	}

	@Override
	public String toString() {
		return "AdminEO [" + "id=" + id + ", "
				+ "designation_id='" + designation_id + "'" 
				+ ", name='" + name + "'"
				+ ", gender='" + gender + "'" 
				+ ", aadhar='" + aadhar + "'" 
				+ ", phone=" + phone 
				+ ", password='"
				+ password + "'" 
				+ ", hire_date=" + hire_date 
				+ ", signup=" + signup 
				+ ", role_id=" + (role_id != null ? role_id.getRole_id() + " (" + role_id.getRole_name() + ")" : "null") + "]";
	}

}

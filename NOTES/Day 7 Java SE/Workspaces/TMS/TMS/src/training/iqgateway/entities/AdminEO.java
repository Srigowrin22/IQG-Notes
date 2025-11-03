package training.iqgateway.entities;

import java.sql.Date;

/**
 * Entity object representing an Admin user in the system.
 */
public class AdminEO {

    /**
     * Unique identifier for the admin.
     */
    private Integer id;

    /**
     * Role entity associated with the admin.
     */
    private RoleEO roleID;

    /**
     * Designation ID of the admin.
     */
    private String designationID;

    /**
     * Name of the admin.
     */
    private String name;

    /**
     * Gender of the admin.
     */
    private String gender;

    /**
     * Aadhar number of the admin.
     */
    private String aadhar;

    /**
     * Phone number of the admin.
     */
    private Long phone;

    /**
     * Password for the admin account.
     */
    private String password;

    /**
     * Hire date of the admin.
     */
    private Date hireDate;

    /**
     * Signup status of the admin.
     */
    private Integer signup;

    /**
     * Default constructor for AdminEO.
     */
    public AdminEO() {
        super();
    }

    /**
     * Parameterized constructor for AdminEO.
     *
     * @param id             Admin ID
     * @param roleID         Role entity
     * @param designationID  Designation ID
     * @param name           Name of the admin
     * @param gender         Gender of the admin
     * @param aadhar         Aadhar number
     * @param phone          Phone number
     * @param password       Password
     * @param hireDate       Hire date
     * @param signup         Signup status
     */
    public AdminEO(Integer id, RoleEO roleID, String designationID,
                   String name, String gender, String aadhar, Long phone, String password, Date hireDate,
                   Integer signup) {
        super();
        this.id = id;
        this.roleID = roleID;
        this.designationID = designationID;
        this.name = name;
        this.gender = gender;
        this.aadhar = aadhar;
        this.phone = phone;
        this.password = password;
        this.hireDate = hireDate;
        this.signup = signup;
    }

    /**
     * Sets the admin ID.
     * @param id Admin ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the admin ID.
     * @return Admin ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the role entity.
     * @param roleID Role entity
     */
    public void setRoleID(RoleEO roleID) {
        this.roleID = roleID;
    }

    /**
     * Returns the role entity.
     * @return Role entity
     */
    public RoleEO getRoleID() {
        return roleID;
    }

    /**
     * Sets the designation ID.
     * @param designationID Designation ID
     */
    public void setDesignationID(String designationID) {
        this.designationID = designationID;
    }

    /**
     * Returns the designation ID.
     * @return Designation ID
     */
    public String getDesignationID() {
        return designationID;
    }

    /**
     * Sets the name of the admin.
     * @param name Name of the admin
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the admin.
     * @return Name of the admin
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the gender of the admin.
     * @param gender Gender of the admin
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns the gender of the admin.
     * @return Gender of the admin
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the Aadhar number.
     * @param aadhar Aadhar number
     */
    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    /**
     * Returns the Aadhar number.
     * @return Aadhar number
     */
    public String getAadhar() {
        return aadhar;
    }

    /**
     * Sets the phone number.
     * @param phone Phone number
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    /**
     * Returns the phone number.
     * @return Phone number
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * Sets the password.
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the password.
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the hire date.
     * @param hireDate Hire date
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * Returns the hire date.
     * @return Hire date
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * Sets the signup status.
     * @param signup Signup status
     */
    public void setSignup(Integer signup) {
        this.signup = signup;
    }

    /**
     * Returns the signup status.
     * @return Signup status
     */
    public Integer getSignup() {
        return signup;
    }

    /**
     * Returns a string representation of the admin details.
     * @return String of admin details
     */
    @Override
    public String toString() {
        return "Admin Details [Role ID: " + (this.roleID != null ? this.roleID.getRoleID() : "null") + "\t" +
               "Designation ID: " + this.designationID + "\t" +
               "Name: " + this.name + "\t" +
               "Gender: " + this.gender + "\t" +
               "Aadhar: " + this.aadhar + "\t" +
               "Phone: " + this.phone + "\t" +
               "Hire Date: " + this.hireDate + "]\n";
    }
}

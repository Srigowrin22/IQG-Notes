package training.iqgateway.entities;

/**
 * Entity object representing a vehicle owner in the system.
 * Maps to the Owner table in the database.
 */
public class OwnerEO {
    /**
     * Unique identifier for the owner.
     */
    private Integer ownerID;

    /**
     * Name of the owner.
     */
    private String ownerName;

    /**
     * Gender of the owner.
     */
    private String gender;

    /**
     * Aadhar number of the owner (Primary Key).
     */
    private String ownerAadhar;

    /**
     * PAN card number of the owner.
     */
    private String pancard;

    /**
     * Phone number of the owner.
     */
    private Long phone;

    /**
     * Address of the owner.
     */
    private String address;

    /**
     * Password for the owner's account.
     */
    private String password;

    /**
     * Default constructor for OwnerEO.
     */
    public OwnerEO() {
        super();
    }

    /**
     * Parameterized constructor for OwnerEO.
     * 
     * @param ownerID     Unique identifier for the owner
     * @param ownerName   Name of the owner
     * @param gender      Gender of the owner
     * @param ownerAadhar Aadhar number of the owner
     * @param pancard     PAN card number of the owner
     * @param phone       Phone number of the owner
     * @param address     Address of the owner
     * @param password    Password for the owner's account
     */
    public OwnerEO(Integer ownerID, String ownerName, String gender, String ownerAadhar,
                   String pancard, Long phone, String address, String password) {
        super();
        this.ownerID = ownerID;
        this.ownerName = ownerName;
        this.gender = gender;
        this.ownerAadhar = ownerAadhar;
        this.pancard = pancard;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    /**
     * Sets the owner ID.
     * @param ownerID Unique identifier for the owner
     */
    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }

    /**
     * Returns the owner ID.
     * @return Owner ID
     */
    public Integer getOwnerID() {
        return ownerID;
    }

    /**
     * Sets the owner's name.
     * @param ownerName Name of the owner
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Returns the owner's name.
     * @return Owner's name
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets the gender of the owner.
     * @param gender Gender of the owner
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns the gender of the owner.
     * @return Gender of the owner
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the owner's Aadhar number.
     * @param ownerAadhar Aadhar number of the owner
     */
    public void setOwnerAadhar(String ownerAadhar) {
        this.ownerAadhar = ownerAadhar;
    }

    /**
     * Returns the owner's Aadhar number.
     * @return Aadhar number of the owner
     */
    public String getOwnerAadhar() {
        return ownerAadhar;
    }

    /**
     * Sets the owner's PAN card number.
     * @param pancard PAN card number of the owner
     */
    public void setPancard(String pancard) {
        this.pancard = pancard;
    }

    /**
     * Returns the owner's PAN card number.
     * @return PAN card number of the owner
     */
    public String getPancard() {
        return pancard;
    }

    /**
     * Sets the owner's phone number.
     * @param phone Phone number of the owner
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    /**
     * Returns the owner's phone number.
     * @return Phone number of the owner
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * Sets the owner's address.
     * @param address Address of the owner
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the owner's address.
     * @return Address of the owner
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the owner's password.
     * @param password Password for the owner's account
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the owner's password.
     * @return Password for the owner's account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns a string representation of the owner details.
     * @return String of owner details.
     */
    @Override
    public String toString() {
        return "Owner Details [Name: " + this.ownerName + "\t" +
             "Gender: " + this.gender + "\t" +
             "Aadhar: " + this.ownerAadhar + "\t" +
             "PAN: " + this.pancard + "\t" +
             "Phone: " + this.phone + "\t" +
             "Address: " + this.address + "]\n";
    }
}

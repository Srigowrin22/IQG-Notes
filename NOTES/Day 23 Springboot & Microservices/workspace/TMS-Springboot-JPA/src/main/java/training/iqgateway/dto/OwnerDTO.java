package training.iqgateway.dto;

public class OwnerDTO {

    private Long ownerId;
    private String ownerName;
    private String gender;
    private String ownerAadhar;
    private String panCard;
    private Long phone;
    private String address;
    private String password;

    public OwnerDTO() {
        // default constructor
    }

    public OwnerDTO(Long ownerId, String ownerName, String gender, String ownerAadhar, String panCard, Long phone, String address, String password) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.gender = gender;
        this.ownerAadhar = ownerAadhar;
        this.panCard = panCard;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOwnerAadhar() {
        return ownerAadhar;
    }

    public void setOwnerAadhar(String ownerAadhar) {
        this.ownerAadhar = ownerAadhar;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

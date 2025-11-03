package training.iqgateway.entities;

import javax.persistence.*;

@Entity
@Table(name = "Owner")
public class OwnerEO {

    @Column(name = "owner_id")
    private Integer owner_id;

    @Column(name = "owner_name", nullable = false, length = 100)
    private String owner_name;

    @Column(name = "gender", columnDefinition = "VARCHAR2(2) DEFAULT 'NA'")
    private String gender;

    @Id
    @Column(name = "owner_aadhar", length = 50)
    private String owner_aadhar;

    @Column(name = "pan_card", length = 20)
    private String pan_card;

    @Column(name = "phone", nullable = false)
    private Long phone;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    public OwnerEO() {
        super();
    }

    public OwnerEO(Integer owner_id, String owner_name, String gender, String owner_aadhar, String pan_card, Long phone,
                   String address, String password) {
        super();
        this.owner_id = owner_id;
        this.owner_name = owner_name;
        this.gender = gender;
        this.owner_aadhar = owner_aadhar;
        this.pan_card = pan_card;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOwner_aadhar() {
        return owner_aadhar;
    }

    public void setOwner_aadhar(String owner_aadhar) {
        this.owner_aadhar = owner_aadhar;
    }

    public String getPan_card() {
        return pan_card;
    }

    public void setPan_card(String pan_card) {
        this.pan_card = pan_card;
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

    @Override
    public String toString() {
        return "OwnerEO [owner_id=" + owner_id
                + ", owner_name=" + owner_name
                + ", gender=" + gender
                + ", owner_aadhar=" + owner_aadhar
                + ", pan_card=" + pan_card
                + ", phone=" + phone
                + ", address=" + address
                + ", password=" + password + "]";
    }
}

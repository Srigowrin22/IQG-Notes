package training.iqgateway.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "OwnerEO.findAll",
                             query = "select o from OwnerEO o") })
@Table(name = "OWNER")
public class OwnerEO implements Serializable {
    @Column(nullable = false, length = 200)
    private String address;
    @Column(length = 2)
    private String gender;
    @Id
    @Column(name = "OWNER_AADHAR", nullable = false, length = 50)
    private String ownerAadhar;
    @Column(name = "OWNER_ID")
    private Long ownerId;
    @Column(name = "OWNER_NAME", nullable = false, length = 100)
    private String ownerName;
    @Column(name = "PAN_CARD", length = 20)
    private String panCard;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(nullable = false)
    private Long phone;
    @OneToMany(mappedBy = "ownerEO")
    private List<RegistrationEO> registrationEOList;

    public OwnerEO() {
    }

    public OwnerEO(String address, String gender, String ownerAadhar,
                   Long ownerId, String ownerName, String panCard,
                   String password, Long phone) {
        this.address = address;
        this.gender = gender;
        this.ownerAadhar = ownerAadhar;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.panCard = panCard;
        this.password = password;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
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

    public List<RegistrationEO> getRegistrationEOList() {
        return registrationEOList;
    }

    public void setRegistrationEOList(List<RegistrationEO> registrationEOList) {
        this.registrationEOList = registrationEOList;
    }

    public RegistrationEO addRegistrationEO(RegistrationEO registrationEO) {
        getRegistrationEOList().add(registrationEO);
        registrationEO.setOwnerEO(this);
        return registrationEO;
    }

    public RegistrationEO removeRegistrationEO(RegistrationEO registrationEO) {
        getRegistrationEOList().remove(registrationEO);
        registrationEO.setOwnerEO(null);
        return registrationEO;
    }
}

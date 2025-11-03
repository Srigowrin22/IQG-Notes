package training.iqgateway.model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "RegistrationEO.findAll",
                             query = "select o from RegistrationEO o") })
@Table(name = "REGISTRATION")
public class RegistrationEO implements Serializable {
    @Column(nullable = false, length = 100)
    private String location;
    @Column(length = 20)
    private String registrar;
    @Column(name = "REGISTRATION_DATE")
    private Timestamp registrationDate;
    @Id
    @Column(name = "REGISTRATION_ID", nullable = false, length = 50)
    private String registrationId;
    @OneToMany(mappedBy = "registrationEO")
    private List<VehicleOffenceEO> vehicleOffenceEOList;
    @ManyToOne
    @JoinColumn(name = "VEHICLE_ID")
    private VehicleEO vehicleEO;
    @ManyToOne
    @JoinColumn(name = "OWNER_AADHAR")
    private OwnerEO ownerEO;

    public RegistrationEO() {
    }

    public RegistrationEO(String location, OwnerEO ownerEO, String registrar,
                          Timestamp registrationDate, String registrationId,
                          VehicleEO vehicleEO) {
        this.location = location;
        this.ownerEO = ownerEO;
        this.registrar = registrar;
        this.registrationDate = registrationDate;
        this.registrationId = registrationId;
        this.vehicleEO = vehicleEO;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }


    public List<VehicleOffenceEO> getVehicleOffenceEOList() {
        return vehicleOffenceEOList;
    }

    public void setVehicleOffenceEOList(List<VehicleOffenceEO> vehicleOffenceEOList) {
        this.vehicleOffenceEOList = vehicleOffenceEOList;
    }

    public VehicleOffenceEO addVehicleOffenceEO(VehicleOffenceEO vehicleOffenceEO) {
        getVehicleOffenceEOList().add(vehicleOffenceEO);
        vehicleOffenceEO.setRegistrationEO(this);
        return vehicleOffenceEO;
    }

    public VehicleOffenceEO removeVehicleOffenceEO(VehicleOffenceEO vehicleOffenceEO) {
        getVehicleOffenceEOList().remove(vehicleOffenceEO);
        vehicleOffenceEO.setRegistrationEO(null);
        return vehicleOffenceEO;
    }

    public VehicleEO getVehicleEO() {
        return vehicleEO;
    }

    public void setVehicleEO(VehicleEO vehicleEO) {
        this.vehicleEO = vehicleEO;
    }

    public OwnerEO getOwnerEO() {
        return ownerEO;
    }

    public void setOwnerEO(OwnerEO ownerEO) {
        this.ownerEO = ownerEO;
    }
}

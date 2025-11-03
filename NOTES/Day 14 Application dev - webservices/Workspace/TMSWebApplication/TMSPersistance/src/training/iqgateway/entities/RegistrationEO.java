package training.iqgateway.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Registration")
public class RegistrationEO {

    @Id
    @Column(name = "registration_id", length = 50)
    private String registration_id;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @Column(name = "registration_date", columnDefinition = "DATE DEFAULT SYSDATE")
    private Date registration_date;

    @Column(name = "registrar", length = 20)
    private String registrar;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEO vehicle_id;

    @ManyToOne
    @JoinColumn(name = "owner_aadhar", nullable = false)
    private OwnerEO owner_aadhar;

    public RegistrationEO() {}

    public RegistrationEO(String registration_id, String location, Date registration_date,
                          String registrar, VehicleEO vehicle_id, OwnerEO owner_aadhar) {
        this.registration_id = registration_id;
        this.location = location;
        this.registration_date = registration_date;
        this.registrar = registrar;
        this.vehicle_id = vehicle_id;
        this.owner_aadhar = owner_aadhar;
    }

    public String getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(String registration_id) {
        this.registration_id = registration_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    public VehicleEO getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(VehicleEO vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public OwnerEO getOwner_aadhar() {
        return owner_aadhar;
    }

    public void setOwner_aadhar(OwnerEO owner_aadhar) {
        this.owner_aadhar = owner_aadhar;
    }

    @Override
    public String toString() {
        return "RegistrationEO [registration_id=" + registration_id
                + ", location=" + location
                + ", registration_date=" + registration_date
                + ", registrar=" + registrar
                + ", vehicle_id=" + (vehicle_id != null ? vehicle_id.getVehicle_id() : "null")
                + ", owner_aadhar=" + (owner_aadhar != null ? owner_aadhar.getOwner_aadhar() : "null")
                + "]";
    }
}

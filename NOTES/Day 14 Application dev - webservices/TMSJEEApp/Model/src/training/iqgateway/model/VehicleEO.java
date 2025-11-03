package training.iqgateway.model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "VehicleEO.findAll",
                             query = "select o from VehicleEO o") })
@Table(name = "VEHICLE")
public class VehicleEO implements Serializable {
    @Column(length = 20)
    private String color;
    @Column(name = "FUEL_TYPE", length = 10)
    private String fuelType;
    @Column(name = "MANUFACTURE_DATE")
    private Timestamp manufactureDate;
    @Column(name = "NO_OF_EXHAUST")
    private Long noOfExhaust;
    @Column(name = "VEHICLE_BRAND", nullable = false, length = 50)
    private String vehicleBrand;
    @Id
    @Column(name = "VEHICLE_ID", nullable = false)
    private Long vehicleId;
    @Column(name = "VEHICLE_MODEL", nullable = false, length = 50)
    private String vehicleModel;
    @Column(name = "VEHICLE_TYPE", nullable = false, length = 50)
    private String vehicleType;
    @OneToMany(mappedBy = "vehicleEO")
    private List<RegistrationEO> registrationEOList;

    public VehicleEO() {
    }

    public VehicleEO(String color, String fuelType, Timestamp manufactureDate,
                     Long noOfExhaust, String vehicleBrand, Long vehicleId,
                     String vehicleModel, String vehicleType) {
        this.color = color;
        this.fuelType = fuelType;
        this.manufactureDate = manufactureDate;
        this.noOfExhaust = noOfExhaust;
        this.vehicleBrand = vehicleBrand;
        this.vehicleId = vehicleId;
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Timestamp getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Timestamp manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Long getNoOfExhaust() {
        return noOfExhaust;
    }

    public void setNoOfExhaust(Long noOfExhaust) {
        this.noOfExhaust = noOfExhaust;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<RegistrationEO> getRegistrationEOList() {
        return registrationEOList;
    }

    public void setRegistrationEOList(List<RegistrationEO> registrationEOList) {
        this.registrationEOList = registrationEOList;
    }

    public RegistrationEO addRegistrationEO(RegistrationEO registrationEO) {
        getRegistrationEOList().add(registrationEO);
        registrationEO.setVehicleEO(this);
        return registrationEO;
    }

    public RegistrationEO removeRegistrationEO(RegistrationEO registrationEO) {
        getRegistrationEOList().remove(registrationEO);
        registrationEO.setVehicleEO(null);
        return registrationEO;
    }
}

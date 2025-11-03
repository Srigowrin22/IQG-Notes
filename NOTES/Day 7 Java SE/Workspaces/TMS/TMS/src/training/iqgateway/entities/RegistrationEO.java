package training.iqgateway.entities;

import java.sql.Date;

/**
 * Entity object representing a vehicle registration in the system.
 * Maps to the Registration table in the database.
 */
public class RegistrationEO {

    /**
     * Unique registration ID (Primary Key).
     */
    private String registrationID;

    /**
     * Location where the registration took place.
     */
    private String location;

    /**
     * Date of registration.
     */
    private Date registrationDate;

    /**
     * Name of the registrar.
     */
    private String registrar;

    /**
     * Vehicle associated with this registration.
     */
    private VehicleEO vehicleID;

    /**
     * Owner associated with this registration.
     */
    private OwnerEO ownerAadhar;

    /**
     * Default constructor for RegistrationEO.
     */
    public RegistrationEO() {
        super();
    }

    /**
     * Parameterized constructor for RegistrationEO.
     *
     * @param registrationID   Unique registration ID
     * @param location         Registration location
     * @param registrationDate Date of registration
     * @param registrar        Name of registrar
     * @param vehicleID        Vehicle entity object
     * @param ownerAadhar      Owner entity object
     */
    public RegistrationEO(String registrationID, String location, Date registrationDate,
                          String registrar, VehicleEO vehicleID, OwnerEO ownerAadhar) {
        super();
        this.registrationID = registrationID;
        this.location = location;
        this.registrationDate = registrationDate;
        this.registrar = registrar;
        this.vehicleID = vehicleID;
        this.ownerAadhar = ownerAadhar;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    public String getRegistrar() {
        return registrar;
    }

    public void setVehicleID(VehicleEO vehicleID) {
        this.vehicleID = vehicleID;
    }

    public VehicleEO getVehicleID() {
        return vehicleID;
    }

    public void setOwnerAadhar(OwnerEO ownerAadhar) {
        this.ownerAadhar = ownerAadhar;
    }

    public OwnerEO getOwnerAadhar() {
        return ownerAadhar;
    }

    /**
     * Returns a string representation of the registration details.
     * @return String of registration details.
     */
    @Override
    public String toString() {
        return "\nRegistration Details [Registration ID: " + this.registrationID + "\t" +
               "Registration Date: " + this.registrationDate + "\t" +
               "Location: " + this.location + "\t" +
               "Registrar: " + this.registrar + "\t" +
               "Owner Aadhar: " + (this.ownerAadhar != null ? this.ownerAadhar.getOwnerAadhar() : "null") + "\t" +
               "Vehicle ID: " + (this.vehicleID != null ? this.vehicleID.getVehicleID() : "null") + "]\n";
    }
}

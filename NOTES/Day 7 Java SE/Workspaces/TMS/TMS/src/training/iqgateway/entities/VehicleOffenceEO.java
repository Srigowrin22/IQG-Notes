package training.iqgateway.entities;

import java.sql.Date;
import java.sql.Time;

/**
 * Entity representing a vehicle offence record in the system.
 * Maps to the Vehicle_Offence table in the database.
 */
public class VehicleOffenceEO {

    /** Unique identifier for the vehicle offence. */
    private Integer vehicleOffenceID;

    /** Offence type (foreign key as object). */
    private OffenceTypesEO offenceID;

    /** Registration (foreign key as object). */
    private RegistrationEO registrationID;

    /** Location where the offence occurred. */
    private String location;

    /** Date of the offence. */
    private Date date;

    /** Time of the offence. */
    private Time time;

    /** Status of the offence (0=not paid, 1=paid). */
    private Integer status;

    /** Reporter of the offence. */
    private String reporter;

    /** First proof file (image, video, etc., as byte array). */
    private byte[] proof1;

    /** Second proof file (image, video, etc., as byte array). */
    private byte[] proof2;

    /** Default constructor. */
    public VehicleOffenceEO() {
        super();
    }

    /**
     * Full parameterized constructor for VehicleOffenceEO.
     * @param vehicleOffenceID Unique vehicle offence ID
     * @param offenceID        Offence type object
     * @param registrationID   Registration object
     * @param location         Offence location
     * @param date             Offence date
     * @param time             Offence time
     * @param status           Status (0/1)
     * @param reporter         Reporter name
     * @param proof1           First proof file (byte array)
     * @param proof2           Second proof file (byte array)
     */
    public VehicleOffenceEO(Integer vehicleOffenceID,
                            OffenceTypesEO offenceID,
                            RegistrationEO registrationID,
                            String location,
                            Date date,
                            Time time,
                            Integer status,
                            String reporter,
                            byte[] proof1,
                            byte[] proof2) {
        super();
        this.vehicleOffenceID = vehicleOffenceID;
        this.offenceID = offenceID;
        this.registrationID = registrationID;
        this.location = location;
        this.date = date;
        this.time = time;
        this.status = status;
        this.reporter = reporter;
        this.proof1 = proof1;
        this.proof2 = proof2;
    }

    public Integer getVehicleOffenceID() {
        return vehicleOffenceID;
    }

    public void setVehicleOffenceID(Integer vehicleOffenceID) {
        this.vehicleOffenceID = vehicleOffenceID;
    }

    public OffenceTypesEO getOffenceID() {
        return offenceID;
    }

    public void setOffenceID(OffenceTypesEO offenceID) {
        this.offenceID = offenceID;
    }

    public RegistrationEO getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(RegistrationEO registrationID) {
        this.registrationID = registrationID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public byte[] getProof1() {
        return proof1;
    }

    public void setProof1(byte[] proof1) {
        this.proof1 = proof1;
    }

    public byte[] getProof2() {
        return proof2;
    }

    public void setProof2(byte[] proof2) {
        this.proof2 = proof2;
    }

    /**
     * Returns a string representation of the vehicle offence.
     * @return String of vehicle offence details.
     */
    @Override
    public String toString() {
        return "VehicleOffenceEO [vehicleOffenceID=" + vehicleOffenceID +
               ", offenceID=" + (offenceID != null ? offenceID.getOffenceID() : "null") +
               ", registrationID=" + (registrationID != null ? registrationID.getRegistrationID() : "null") +
               ", location=" + location +
               ", date=" + date +
               ", time=" + time +
               ", status=" + (status == 1 ? "Paid" : "Not Paid") +
               ", reporter=" + reporter +
               ", proof1=" + (proof1 != null ? "Available" : "Not Available") +
               ", proof2=" + (proof2 != null ? "Available" : "Not Available") +
               "]\n";
    }
}

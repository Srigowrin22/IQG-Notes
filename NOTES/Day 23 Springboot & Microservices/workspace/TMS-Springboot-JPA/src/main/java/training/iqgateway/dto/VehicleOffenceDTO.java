package training.iqgateway.dto;

import java.util.Date;

public class VehicleOffenceDTO {

    private Long vehicleOffenceId;
    private Long offenceTypeId;      // Reference to OffenceTypesEO.offenceId
    private String offenceTypeName;  // Optional: for convenience
    private String registrationId;   // Reference to RegistrationEO.registrationId
    private String location;
    private Date offenceDate;
    private String time;
    private Integer status;
    private String reporter;
    private byte[] proof1;
    private byte[] proof2;

    public VehicleOffenceDTO() {
        // default constructor
    }

    public VehicleOffenceDTO(Long vehicleOffenceId, Long offenceTypeId, String offenceTypeName, String registrationId,
                             String location, Date offenceDate, String time, Integer status, String reporter,
                             byte[] proof1, byte[] proof2) {
        this.vehicleOffenceId = vehicleOffenceId;
        this.offenceTypeId = offenceTypeId;
        this.offenceTypeName = offenceTypeName;
        this.registrationId = registrationId;
        this.location = location;
        this.offenceDate = offenceDate;
        this.time = time;
        this.status = status;
        this.reporter = reporter;
        this.proof1 = proof1;
        this.proof2 = proof2;
    }

    // Getters and setters

    public Long getVehicleOffenceId() {
        return vehicleOffenceId;
    }

    public void setVehicleOffenceId(Long vehicleOffenceId) {
        this.vehicleOffenceId = vehicleOffenceId;
    }

    public Long getOffenceTypeId() {
        return offenceTypeId;
    }

    public void setOffenceTypeId(Long offenceTypeId) {
        this.offenceTypeId = offenceTypeId;
    }

    public String getOffenceTypeName() {
        return offenceTypeName;
    }

    public void setOffenceTypeName(String offenceTypeName) {
        this.offenceTypeName = offenceTypeName;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getOffenceDate() {
        return offenceDate;
    }

    public void setOffenceDate(Date offenceDate) {
        this.offenceDate = offenceDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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
}

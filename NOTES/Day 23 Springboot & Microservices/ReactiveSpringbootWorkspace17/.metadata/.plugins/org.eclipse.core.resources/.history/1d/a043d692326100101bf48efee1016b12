package training.iqgateway.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "VEHICLE_OFFENCE")
public class VehicleOffenceEO {

    @Id
    @Column(name = "VEHICLE_OFFENCE_ID")
    private Long vehicleOffenceId;

    // Many vehicle offences belong to one offence type
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "OFFENCE_ID", nullable = false)
    private OffenceTypesEO offenceType;

    // Many vehicle offences belong to one registration
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "REGISTRATION_ID", referencedColumnName = "REGISTRATION_ID", nullable = false)
    private RegistrationEO registration;

    @Column(name = "LOCATION", length = 100, nullable = false)
    private String location;

    @Temporal(TemporalType.DATE)
    @Column(name = "OFFENCE_DATE")
    private Date offenceDate;

    @Column(name = "TIME", length = 20)
    private String time;

    @Column(name = "STATUS")
    private Integer status = 0;

    @Column(name = "REPORTER", length = 50)
    private String reporter;

    @Lob
    @Column(name = "PROOF1")
    private byte[] proof1;

    @Lob
    @Column(name = "PROOF2")
    private byte[] proof2;

    public VehicleOffenceEO() {
        this.offenceDate = new Date();
    }

    // Getters and setters

    public Long getVehicleOffenceId() {
        return vehicleOffenceId;
    }
    public void setVehicleOffenceId(Long vehicleOffenceId) {
        this.vehicleOffenceId = vehicleOffenceId;
    }
    public OffenceTypesEO getOffenceType() {
        return offenceType;
    }
    public void setOffenceType(OffenceTypesEO offenceType) {
        this.offenceType = offenceType;
    }
    public RegistrationEO getRegistration() {
        return registration;
    }
    public void setRegistration(RegistrationEO registration) {
        this.registration = registration;
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

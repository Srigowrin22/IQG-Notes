package training.iqgateway.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "Vehicle_Offence")
public class VehicleOffenceEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veh_off_seq")
    @SequenceGenerator(name = "veh_off_seq", sequenceName = "SEQ_VEHOFF", allocationSize = 1)
    @Column(name = "vehicle_offence_id")
    private Integer vehicle_offence_id;

    @ManyToOne
    @JoinColumn(name = "offence_id", nullable = false)
    private OffenceTypesEO offence_id;

    @ManyToOne
    @JoinColumn(name = "registration_id", nullable = false)
    private RegistrationEO registration_id;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @Column(name = "offence_date", columnDefinition = "DATE DEFAULT SYSDATE")
    private Date offence_date;

    @Column(name = "time", length = 20)
    private String time;

    @Column(name = "status", columnDefinition = "NUMBER(1) DEFAULT 0")
    private Integer status;

    @Column(name = "reporter", length = 50)
    private String reporter;

    @Lob
    @Column(name = "proof1")
    private byte[] proof1;

    @Lob
    @Column(name = "proof2")
    private byte[] proof2;

    public VehicleOffenceEO() {
        super();
    }

    public VehicleOffenceEO(Integer vehicle_offence_id,
                            OffenceTypesEO offence_id,
                            RegistrationEO registration_id,
                            String location,
                            Date offence_date,
                            String time,
                            Integer status,
                            String reporter,
                            byte[] proof1,
                            byte[] proof2) {
        super();
        this.vehicle_offence_id = vehicle_offence_id;
        this.offence_id = offence_id;
        this.registration_id = registration_id;
        this.location = location;
        this.offence_date = offence_date;
        this.time = time;
        this.status = status;
        this.reporter = reporter;
        this.proof1 = proof1;
        this.proof2 = proof2;
    }

    public Integer getVehicle_offence_id() {
        return vehicle_offence_id;
    }

    public void setVehicle_offence_id(Integer vehicle_offence_id) {
        this.vehicle_offence_id = vehicle_offence_id;
    }

    public OffenceTypesEO getOffence_id() {
        return offence_id;
    }

    public void setOffence_id(OffenceTypesEO offence_id) {
        this.offence_id = offence_id;
    }

    public RegistrationEO getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(RegistrationEO registration_id) {
        this.registration_id = registration_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getOffence_date() {
        return offence_date;
    }

    public void setOffence_date(Date offence_date) {
        this.offence_date = offence_date;
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

    @Override
    public String toString() {
        return "VehicleOffenceEO [vehicle_offence_id=" + vehicle_offence_id
                + ", offence_id=" + (offence_id != null ? offence_id.getOffence_id() : "null")
                + ", registration_id=" + (registration_id != null ? registration_id.getRegistration_id() : "null")
                + ", location=" + location
                + ", offence_date=" + offence_date
                + ", time=" + time
                + ", status=" + (status != null && status == 1 ? "Paid" : "Not Paid")
                + ", reporter=" + reporter
                + ", proof1=" + (proof1 != null ? "Available" : "Not Available")
                + ", proof2=" + (proof2 != null ? "Available" : "Not Available")
                + "]";
    }
}

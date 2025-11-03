package training.iqgateway.model;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "VehicleOffenceEO.findAll",
                             query = "select o from VehicleOffenceEO o") })
@Table(name = "VEHICLE_OFFENCE")
public class VehicleOffenceEO implements Serializable {
    @Column(nullable = false, length = 100)
    private String location;
    @Column(name = "OFFENCE_DATE")
    private Timestamp offenceDate;
    private byte[] proof1;
    private byte[] proof2;
    @Column(length = 50)
    private String reporter;
    private Long status = 0L;
    @Column(length = 20)
    private String time;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "vehicle_offence_seq_gen")
    @SequenceGenerator(name = "vehicle_offence_seq_gen",
                       sequenceName = "SEQ_VEHOFF", allocationSize = 1)
    @Column(name = "VEHICLE_OFFENCE_ID", nullable = false)
    private Long vehicleOffenceId;
    @ManyToOne
    @JoinColumn(name = "REGISTRATION_ID")
    private RegistrationEO registrationEO;
    @ManyToOne
    @JoinColumn(name = "OFFENCE_ID")
    private OffenceTypesEO offenceTypesEO;

    public VehicleOffenceEO() {
    }

    public VehicleOffenceEO(String location, Timestamp offenceDate,
                            OffenceTypesEO offenceTypesEO,
                            RegistrationEO registrationEO, String reporter,
                            Long status, String time, Long vehicleOffenceId) {
        this.location = location;
        this.offenceDate = offenceDate;
        this.offenceTypesEO = offenceTypesEO;
        this.registrationEO = registrationEO;
        this.reporter = reporter;
        this.status = status;
        this.time = time;
        this.vehicleOffenceId = vehicleOffenceId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getOffenceDate() {
        return offenceDate;
    }

    public void setOffenceDate(Timestamp offenceDate) {
        this.offenceDate = offenceDate;
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


    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getVehicleOffenceId() {
        return vehicleOffenceId;
    }

    public void setVehicleOffenceId(Long vehicleOffenceId) {
        this.vehicleOffenceId = vehicleOffenceId;
    }

    public RegistrationEO getRegistrationEO() {
        return registrationEO;
    }

    public void setRegistrationEO(RegistrationEO registrationEO) {
        this.registrationEO = registrationEO;
    }

    public OffenceTypesEO getOffenceTypesEO() {
        return offenceTypesEO;
    }

    public void setOffenceTypesEO(OffenceTypesEO offenceTypesEO) {
        this.offenceTypesEO = offenceTypesEO;
    }
}

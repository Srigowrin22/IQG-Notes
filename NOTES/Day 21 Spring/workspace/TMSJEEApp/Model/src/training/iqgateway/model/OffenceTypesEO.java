package training.iqgateway.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "OffenceTypesEO.findAll",
                             query = "select o from OffenceTypesEO o") })
@Table(name = "OFFENCE_TYPES")
public class OffenceTypesEO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "offtype_seq_gen")
    @SequenceGenerator(name = "offtype_seq_gen", sequenceName = "SEQ_OFFTYPE",
                       allocationSize = 1)
    @Column(name = "OFFENCE_ID", nullable = false)
    private Long offenceId;
    @Column(name = "OFFENCE_TYPE", nullable = false, length = 200)
    private String offenceType;
    @Column(name = "PENALTY_AMT")
    private Long penaltyAmt;
    @Column(name = "VEHICLE_TYPE", nullable = false, length = 50)
    private String vehicleType;
    @OneToMany(mappedBy = "offenceTypesEO")
    private List<VehicleOffenceEO> vehicleOffenceEOList;

    public OffenceTypesEO() {
    }

    public OffenceTypesEO(Long offenceId, String offenceType, Long penaltyAmt,
                          String vehicleType) {
        this.offenceId = offenceId;
        this.offenceType = offenceType;
        this.penaltyAmt = penaltyAmt;
        this.vehicleType = vehicleType;
    }

    public Long getOffenceId() {
        return offenceId;
    }

    public void setOffenceId(Long offenceId) {
        this.offenceId = offenceId;
    }

    public String getOffenceType() {
        return offenceType;
    }

    public void setOffenceType(String offenceType) {
        this.offenceType = offenceType;
    }

    public Long getPenaltyAmt() {
        return penaltyAmt;
    }

    public void setPenaltyAmt(Long penaltyAmt) {
        this.penaltyAmt = penaltyAmt;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<VehicleOffenceEO> getVehicleOffenceEOList() {
        return vehicleOffenceEOList;
    }

    public void setVehicleOffenceEOList(List<VehicleOffenceEO> vehicleOffenceEOList) {
        this.vehicleOffenceEOList = vehicleOffenceEOList;
    }

    public VehicleOffenceEO addVehicleOffenceEO(VehicleOffenceEO vehicleOffenceEO) {
        getVehicleOffenceEOList().add(vehicleOffenceEO);
        vehicleOffenceEO.setOffenceTypesEO(this);
        return vehicleOffenceEO;
    }

    public VehicleOffenceEO removeVehicleOffenceEO(VehicleOffenceEO vehicleOffenceEO) {
        getVehicleOffenceEOList().remove(vehicleOffenceEO);
        vehicleOffenceEO.setOffenceTypesEO(null);
        return vehicleOffenceEO;
    }
}

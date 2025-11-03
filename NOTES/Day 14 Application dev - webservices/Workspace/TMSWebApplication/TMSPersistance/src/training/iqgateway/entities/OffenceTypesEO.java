package training.iqgateway.entities;

import javax.persistence.*;

@Entity
@Table(name = "Offence_Types")
public class OffenceTypesEO {

    @Id
    @Column(name = "offence_id")
    private Integer offence_id;

    @Column(name = "offence_type", nullable = false, length = 200)
    private String offence_type;

    @Column(name = "vehicle_type", nullable = false, length = 50)
    private String vehicle_type;

    @Column(name = "penalty_amt", columnDefinition = "INTEGER DEFAULT 100")
    private Integer penalty_amt;

    public OffenceTypesEO() {
        super();
    }

    public OffenceTypesEO(Integer offence_id, String offence_type, String vehicle_type, Integer penalty_amt) {
        super();
        this.offence_id = offence_id;
        this.offence_type = offence_type;
        this.vehicle_type = vehicle_type;
        this.penalty_amt = penalty_amt;
    }

    public Integer getOffence_id() {
        return offence_id;
    }

    public void setOffence_id(Integer offence_id) {
        this.offence_id = offence_id;
    }

    public String getOffence_type() {
        return offence_type;
    }

    public void setOffence_type(String offence_type) {
        this.offence_type = offence_type;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public Integer getPenalty_amt() {
        return penalty_amt;
    }

    public void setPenalty_amt(Integer penalty_amt) {
        this.penalty_amt = penalty_amt;
    }

    @Override
    public String toString() {
        return "OffenceTypesEO [offence_id=" + offence_id
                + ", offence_type=" + offence_type
                + ", vehicle_type=" + vehicle_type
                + ", penalty_amt=" + penalty_amt + "]";
    }
}

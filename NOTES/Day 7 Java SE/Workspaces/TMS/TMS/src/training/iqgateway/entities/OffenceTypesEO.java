package training.iqgateway.entities;

/**
 * Entity object representing an offence type in the system.
 * Maps to the offence types table in the database.
 */
public class OffenceTypesEO {

    /**
     * Unique identifier for the offence type.
     */
    private Integer offenceID;

    /**
     * Description or name of the offence type.
     */
    private String offenceType;

    /**
     * Type of vehicle this offence applies to.
     */
    private String vehicleType;

    /**
     * Penalty amount associated with this offence.
     */
    private Integer penaltyAmt;

    /**
     * Default constructor for OffenceTypesEO.
     */
    public OffenceTypesEO() {
        super();
    }

    /**
     * Parameterized constructor for OffenceTypesEO.
     *
     * @param offenceID   Unique identifier for the offence type
     * @param offenceType Description or name of the offence
     * @param vehicleType Type of vehicle
     * @param penaltyAmt  Penalty amount for the offence
     */
    public OffenceTypesEO(Integer offenceID, String offenceType,
                          String vehicleType, Integer penaltyAmt) {
        super();
        this.offenceID = offenceID;
        this.offenceType = offenceType;
        this.vehicleType = vehicleType;
        this.penaltyAmt = penaltyAmt;
    }

    /**
     * Sets the offence ID.
     * @param offenceID Unique identifier for the offence
     */
    public void setOffenceID(Integer offenceID) {
        this.offenceID = offenceID;
    }

    /**
     * Returns the offence ID.
     * @return Offence ID
     */
    public Integer getOffenceID() {
        return offenceID;
    }

    /**
     * Sets the offence type.
     * @param offenceType Description or name of the offence
     */
    public void setOffenceType(String offenceType) {
        this.offenceType = offenceType;
    }

    /**
     * Returns the offence type.
     * @return Offence type
     */
    public String getOffenceType() {
        return offenceType;
    }

    /**
     * Sets the vehicle type.
     * @param vehicleType Type of vehicle
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Returns the vehicle type.
     * @return Vehicle type
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Sets the penalty amount.
     * @param penaltyAmt Penalty amount for the offence
     */
    public void setPenaltyAmt(Integer penaltyAmt) {
        this.penaltyAmt = penaltyAmt;
    }

    /**
     * Returns the penalty amount.
     * @return Penalty amount
     */
    public Integer getPenaltyAmt() {
        return penaltyAmt;
    }

    /**
     * Returns a string representation of the offence type.
     * @return String of offence type details.
     */
    @Override
    public String toString() {
        return "OFFENCE TYPE [ID: " + this.offenceID + "\t" + 
               "Type: " + this.offenceType + "\t" + 
               "Vehicle Type: " + this.vehicleType + "\t" + 
               "Amount to be paid: " + this.penaltyAmt + "]\n";
    }
}

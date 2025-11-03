package training.iqgateway.entities;

public class OffenceTypesEO {

	private Integer offenceID;

	private String offenceType;

	private String vehicleType;

	private Integer penaltyAmt;

	public OffenceTypesEO() {
		super();
	}

	public OffenceTypesEO(Integer offenceID, String offenceType, String vehicleType, Integer penaltyAmt) {
		super();
		this.offenceID = offenceID;
		this.offenceType = offenceType;
		this.vehicleType = vehicleType;
		this.penaltyAmt = penaltyAmt;
	}

	public Integer getOffenceID() {
		return offenceID;
	}

	public void setOffenceID(Integer offenceID) {
		this.offenceID = offenceID;
	}

	public String getOffenceType() {
		return offenceType;
	}

	public void setOffenceType(String offenceType) {
		this.offenceType = offenceType;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Integer getPenaltyAmt() {
		return penaltyAmt;
	}

	public void setPenaltyAmt(Integer penaltyAmt) {
		this.penaltyAmt = penaltyAmt;
	}

	@Override
	public String toString() {
		return "OffenceEO [offenceID=" + offenceID + ", offenceType=" + offenceType + ", vehicleType=" + vehicleType
				+ ", penaltyAmt=" + penaltyAmt + "]";
	}

}

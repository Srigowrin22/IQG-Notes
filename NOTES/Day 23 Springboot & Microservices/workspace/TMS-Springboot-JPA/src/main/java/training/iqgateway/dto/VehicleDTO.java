package training.iqgateway.dto;

import java.util.Date;

public class VehicleDTO {

    private Long vehicleId;
    private String vehicleBrand;
    private String vehicleModel;
    private String vehicleType;
    private String fuelType;
    private Integer noOfExhaust;
    private String color;
    private Date manufactureDate;

    public VehicleDTO() {
        // default constructor
    }

    public VehicleDTO(Long vehicleId, String vehicleBrand, String vehicleModel, String vehicleType,
                      String fuelType, Integer noOfExhaust, String color, Date manufactureDate) {
        this.vehicleId = vehicleId;
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
        this.noOfExhaust = noOfExhaust;
        this.color = color;
        this.manufactureDate = manufactureDate;
    }

    // Getters and setters

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getNoOfExhaust() {
        return noOfExhaust;
    }

    public void setNoOfExhaust(Integer noOfExhaust) {
        this.noOfExhaust = noOfExhaust;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

}

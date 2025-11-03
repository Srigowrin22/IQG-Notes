package training.iqgateway.entities;

import java.sql.Date;

/**
 * Entity object representing a vehicle in the system.
 * Maps to the Vehicle table in the database.
 */
public class VehicleEO {

    /**
     * Unique identifier for the vehicle.
     */
    private Integer vehicleID;

    /**
     * Brand of the vehicle.
     */
    private String vehicleBrand;

    /**
     * Model of the vehicle.
     */
    private String vehicleModel;

    /**
     * Type of the vehicle (e.g., Car, Bike).
     */
    private String vehicleType;

    /**
     * Fuel type of the vehicle (e.g., Petrol, Diesel).
     */
    private String fuelType;

    /**
     * Number of exhausts in the vehicle.
     */
    private Integer noOfExhaust;

    /**
     * Color of the vehicle.
     */
    private String color;

    /**
     * Manufacture date of the vehicle.
     */
    private Date manufactureDate;

    /**
     * Default constructor for VehicleEO.
     */
    public VehicleEO() {
        super();
    }

    /**
     * Parameterized constructor for VehicleEO.
     * 
     * @param vehicleID        Unique identifier for the vehicle
     * @param vehicleBrand     Brand of the vehicle
     * @param vehicleModel     Model of the vehicle
     * @param vehicleType      Type of the vehicle
     * @param fuelType         Fuel type of the vehicle
     * @param noOfExhaust      Number of exhausts
     * @param color            Color of the vehicle
     * @param manufactureDate  Manufacture date of the vehicle
     */
    public VehicleEO(Integer vehicleID, String vehicleBrand, String vehicleModel, String vehicleType,
                     String fuelType, Integer noOfExhaust, String color, Date manufactureDate) {
        super();
        this.vehicleID = vehicleID;
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
        this.noOfExhaust = noOfExhaust;
        this.color = color;
        this.manufactureDate = manufactureDate;
    }

    public void setVehicleID(Integer vehicleID) {
        this.vehicleID = vehicleID;
    }

    public Integer getVehicleID() {
        return vehicleID;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setNoOfExhaust(Integer noOfExhaust) {
        this.noOfExhaust = noOfExhaust;
    }

    public Integer getNoOfExhaust() {
        return noOfExhaust;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    /**
     * Returns a string representation of the vehicle details.
     * @return String of vehicle details.
     */
    @Override
    public String toString() {
        return "\nVehicle Details [Vehicle ID: " + this.vehicleID + "\t" +
               "Brand: " + this.vehicleBrand + "\t" +
               "Model: " + this.vehicleModel + "\t" +
               "Type: " + this.vehicleType + "\t" +
               "Fuel: " + this.fuelType + "\t" +
               "Exhausts: " + this.noOfExhaust + "\t" +
               "Color: " + this.color + "\t" +
               "Manufactured Date: " + this.manufactureDate + "]\n";
    }
}

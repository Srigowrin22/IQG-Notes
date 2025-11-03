package training.iqgateway.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Vehicle")
public class VehicleEO {

    @Id
    @Column(name = "vehicle_id")
    private Integer vehicle_id;

    @Column(name = "vehicle_brand", nullable = false, length = 50)
    private String vehicle_brand;

    @Column(name = "vehicle_model", nullable = false, length = 50)
    private String vehicle_model;

    @Column(name = "vehicle_type", nullable = false, length = 50)
    private String vehicle_type;

    @Column(name = "fuel_type", length = 10, columnDefinition = "VARCHAR2(10) DEFAULT 'Petrol'")
    private String fuel_type;

    @Column(name = "no_of_exhaust", columnDefinition = "INTEGER DEFAULT 1")
    private Integer no_of_exhaust;

    @Column(name = "color", length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'White'")
    private String color;

    @Column(name = "manufacture_date", columnDefinition = "DATE DEFAULT SYSDATE")
    private Date manufacture_date;

    public VehicleEO() {
        super();
    }

    public VehicleEO(Integer vehicle_id, String vehicle_brand, String vehicle_model, String vehicle_type,
                     String fuel_type, Integer no_of_exhaust, String color, Date manufacture_date) {
        super();
        this.vehicle_id = vehicle_id;
        this.vehicle_brand = vehicle_brand;
        this.vehicle_model = vehicle_model;
        this.vehicle_type = vehicle_type;
        this.fuel_type = fuel_type;
        this.no_of_exhaust = no_of_exhaust;
        this.color = color;
        this.manufacture_date = manufacture_date;
    }

    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_brand() {
        return vehicle_brand;
    }

    public void setVehicle_brand(String vehicle_brand) {
        this.vehicle_brand = vehicle_brand;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public Integer getNo_of_exhaust() {
        return no_of_exhaust;
    }

    public void setNo_of_exhaust(Integer no_of_exhaust) {
        this.no_of_exhaust = no_of_exhaust;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getManufacture_date() {
        return manufacture_date;
    }

    public void setManufacture_date(Date manufacture_date) {
        this.manufacture_date = manufacture_date;
    }

    @Override
    public String toString() {
        return "VehicleEO [vehicle_id=" + vehicle_id
                + ", vehicle_brand=" + vehicle_brand
                + ", vehicle_model=" + vehicle_model
                + ", vehicle_type=" + vehicle_type
                + ", fuel_type=" + fuel_type
                + ", no_of_exhaust=" + no_of_exhaust
                + ", color=" + color
                + ", manufacture_date=" + manufacture_date
                + "]";
    }
}

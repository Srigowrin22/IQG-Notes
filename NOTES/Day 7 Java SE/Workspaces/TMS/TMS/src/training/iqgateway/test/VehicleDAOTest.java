package training.iqgateway.test;

import java.util.List;

import training.iqgateway.dao.VehicleDAO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.factory.GenericDAOFactory;

public class VehicleDAOTest {
    
    private VehicleDAO vehicleDAORef = GenericDAOFactory.createVehicleDAO();
    
    public void testInsertVehicle() {
//        VehicleEO  vehicleEO = new VehicleEO(1, "Bike", "DIO", "8SG",  java.sql.Date.valueOf("2012-08-11"));
//        VehicleEO  vehicleEO = new VehicleEO(2, "CAR", "BMW", "2NG",  java.sql.Date.valueOf("2019-02-08"));
//        VehicleEO  vehicleEO = new VehicleEO(3, "CAR", "Benz", "yorn",  java.sql.Date.valueOf("2019-03-23"));
//        VehicleEO  vehicleEO = new VehicleEO(4, "Bike", "KTM", "200CC",  java.sql.Date.valueOf("2019-03-12"));
//        VehicleEO  vehicleEO = new VehicleEO(5, "CAR", "Porsche", "911",  java.sql.Date.valueOf("2035-06-22"));
//        VehicleEO  vehicleEO = new VehicleEO(6, "CAR", "Porsche", "911",  java.sql.Date.valueOf("2035-06-22"));
        VehicleEO  vehicleEO = new VehicleEO(13, "Hyundai", "SE", "CAR", "Deisel", 1, "Grey",  java.sql.Date.valueOf("2019-08-12"));
        
        System.out.println("INSERT VEHICLE OPERATION");
        int result = vehicleDAORef.insertVehicle(vehicleEO);
        System.out.println(vehicleEO);
        if(result == 1){
            System.out.println("Record inserted successfully to Vehicle Table ...");
        }else{
            System.out.println("Failed to insert record into Vehicles Table :(");
        }   
    }
    
    public void testUpdateVehicle(){
        System.out.println("\nUPDATE VEHICLE OPERATION");
        VehicleEO  vehicleEO = new VehicleEO(13, "Honda", "SE", "CAR", "Deisel", 1, "Pink",  java.sql.Date.valueOf("2019-08-12"));
        int result = vehicleDAORef.updateVehicle(vehicleEO);
        System.out.println(vehicleEO);
        if(result == 1){
            System.out.println("Record UPDATED successfully to Vehicle Table ...");
        }else{
            System.out.println("Failed to UPDATE record into Vehicle Table :(");
        }  
    }
    
    public void testDeleteVehicle(){
        System.out.println("\nDELETE VEHICLE OPERATION");
        int vehicleID = 13;
        int result = vehicleDAORef.deleteVehicle(vehicleID);
        System.out.println("Vehcle ID: " + vehicleID);
        if(result == 1){
            System.out.println("Record DELETED successfully...");
        }else{
            System.out.println("Failed to DELETE record  of " + vehicleID +  " :(");
        }  
    }
    
    public void testFindVehicleByID(){
        System.out.println("\nFIND VEHICLE BY VEHICLE_ID OPERATION");
        int vehicleID = 12;
        VehicleEO vehicleEO = vehicleDAORef.findVehicleByID(vehicleID);
        System.out.println("Vehcle ID: " + vehicleID);
        if(vehicleEO != null ){
            System.out.println("Records FOUND successfully...");
            System.out.println(vehicleEO);
        }else{
            System.out.println("Failed to FIND record of " + vehicleID +  " :(");
        } 
    }
    
    public void testFindAllVehicles(){
        System.out.println("\nFIND ALL VEHICLE OPERATION");
        List<VehicleEO> vehicleEOList = vehicleDAORef.findAllVehicles();
        if(vehicleEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(vehicleEOList);
        }else{
            System.out.println("Failed to FIND records from Vehicle Table :(");
        } 
    }
    
    public void  testFindVehicleByVehicleType(){
        System.out.println("\nFIND VEHICLE BY VEHICLE_TYPE OPERATION");
        String vehicleType = "Car";
        List<VehicleEO> vehicleEOList = vehicleDAORef.findVehicleByType(vehicleType);
        System.out.println("Vehcle Type: " + vehicleType);
        if(vehicleEOList.size()>0){
            System.out.println("Records FOUND successfully...");
            System.out.println(vehicleEOList);
        }else{
            System.out.println("Failed to FIND records from Vehicle Table :(");
        } 
    }
    
    public static void main(String[] args) {
        System.out.println("-----------VEHICLE TABLE OPERATION-------------");
        VehicleDAOTest vehDAOTest = new VehicleDAOTest();
//        vehDAOTest.testInsertVehicle();
//        vehDAOTest.testUpdateVehicle();
//        vehDAOTest.testDeleteVehicle();
//        vehDAOTest.testFindVehicleByID();
//        vehDAOTest.testFindAllVehicles();
//        vehDAOTest.testFindVehicleByVehicleType();
    }
}

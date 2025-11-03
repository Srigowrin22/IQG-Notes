package training.iqgateway.test;

import java.io.File;
import java.io.FileInputStream;

import java.sql.Time;

import java.util.List;

import training.iqgateway.dao.VehicleOffenceDAO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.entities.VehicleOffenceEO;
import training.iqgateway.factory.GenericDAOFactory;


public class VehicleOffenceDAOTest {

    private VehicleOffenceDAO vehicleOffenceDAORef = GenericDAOFactory.createVehicleOffenceDAO();

    public void testInsertVehicleOffence() {
        VehicleOffenceEO vehOffEO = null;
        try {
            String filePath =
                "C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Pictures\\Screenshots\\Screenshot (1).png";
            File file = new File(filePath);
            byte[] bytes = new byte[(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytes);
            fis.close();
            
            OffenceTypesEO offenceID = new OffenceTypesEO();
            offenceID.setOffenceID(3);
            RegistrationEO registrationID = new RegistrationEO();
            registrationID.setRegistrationID("KA02KA0881");
            
//            vehOffEO = new VehicleOffenceEO(1, offenceID, registrationID, "2nd cross, 10th Main, Vijaynagar", java.sql.Date.valueOf("2023-01-15"), 0, bytes);
//            vehOffEO = new VehicleOffenceEO(2, offenceID, registrationID, "11th Main, MG Road", java.sql.Date.valueOf("2023-01-15"), 0, bytes);
//            vehOffEO = new VehicleOffenceEO(3, offenceID, registrationID, "Tolgate Signal", java.sql.Date.valueOf("2023-01-15"), 0, bytes);
//            vehOffEO = new VehicleOffenceEO(4, offenceID, registrationID, "191, Rajajinagar main road", java.sql.Date.valueOf("2023-01-15"), 0, bytes);
//            vehOffEO = new VehicleOffenceEO(5, offenceID, registrationID, "12, Mahalaxshmi Layout metro Underpass", java.sql.Date.valueOf("2023-01-15"), 0, bytes);
//            vehOffEO = new VehicleOffenceEO(6, offenceID, registrationID, "2nd cross Main, MG Road", java.sql.Date.valueOf("2023-01-15"), 0, bytes);
            vehOffEO = new VehicleOffenceEO(null, offenceID, registrationID, "Tolgate Signal", java.sql.Date.valueOf("2023-01-19"), java.sql.Time.valueOf("14:30:00"), 0, "CLK01", bytes, bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("INSERT VEHICLE OFFENCE");
        int result = vehicleOffenceDAORef.insertVehicleOffence(vehOffEO);
        System.out.println(vehOffEO);
        if (result == 1) {
            System.out.println("Record inserted successfully to Vehicle Offence...");
        } else {
            System.out.println("Failed to insert record into Vehicle Offence :(");
        }
    }
    public void testUpdateVehicleOffence(){
        VehicleOffenceEO vehOffEO = null;
        try {
            String filePath =
                "C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Pictures\\Screenshots\\Screenshot (1).png";
            File file = new File(filePath);
            byte[] bytes = new byte[(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytes);
            fis.close();
            
            System.out.println("\nUPDATE VEHICLE OFFENCE");
            OffenceTypesEO offenceID = new OffenceTypesEO();
            offenceID.setOffenceID(2);
            RegistrationEO registrationID = new RegistrationEO();
            registrationID.setRegistrationID("KA02KA0881");
            
            vehOffEO = new VehicleOffenceEO(11, offenceID, registrationID, "Tolgate market", java.sql.Date.valueOf("2023-01-19"), java.sql.Time.valueOf("14:33:00"), 0, "CLK01", bytes, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int result = vehicleOffenceDAORef.updateVehicleOffence(vehOffEO);
        System.out.println(vehOffEO);
        if (result == 1) {
            System.out.println("Record Updated successfully to Vehicle Offence...");
        } else {
            System.out.println("Failed to update record into Vehicle Offence :(");
        }
    }
    
    public void testClearVehicleOffence(){
        
        OffenceTypesEO offenceID = new OffenceTypesEO();
        offenceID.setOffenceID(2);
        RegistrationEO registrationID = new RegistrationEO();
        registrationID.setRegistrationID("KA02KA0881");
        
        System.out.println("\nCLEAR VEHICLE OFFENCE");
        VehicleOffenceEO vehOffEO = new VehicleOffenceEO(11, offenceID, registrationID, "Tolgate Signal", java.sql.Date.valueOf("2023-01-19"), java.sql.Time.valueOf("14:39:00"), 0, "CLK01", null, null);
        int result = vehicleOffenceDAORef.clearVehicleOffence(vehOffEO);
        if (result == 1) {
            vehOffEO.setStatus(1);
            System.out.println(vehOffEO);
            System.out.println("Record updated successfully to Vehicle Offence... OFFENCE CLEARED");
        } else {
            System.out.println("Failed to update record into Vehicle Offence! PAYMENT DENIED :(");
        }
    }
    
    public void testDeleteVehicleOffence(){
        int vehicleOffenceID = 11;
        System.out.println("\nDELETE VEHICLE OFFENCE");
        int result = vehicleOffenceDAORef.deleteVehicleOffence(vehicleOffenceID);
        System.out.println("Vehicle Offence ID: " + vehicleOffenceID);
        if(result == 1){
            System.out.println("Record DELETED successfully...");
        }else{
            System.out.println("Failed to DELETE record  of " + vehicleOffenceID +  " :(");
        }  
    }
    
    public void testFindVehicleOffenceByID(){
        System.out.println("\nFIND VEHICLE OFFENCE BY VEHICLE_OFFENCE_ID");
        int vehicleOffenceID = 24;
        VehicleOffenceEO vehOffEO = vehicleOffenceDAORef.findVehicleOffenceByID(vehicleOffenceID);
        System.out.println("Vehicle Offence ID: " + vehicleOffenceID);
        if(vehOffEO != null ){
            System.out.println("Records FOUND successfully...");
            System.out.println(vehOffEO);
        }else{
            System.out.println("Failed to FIND record of " + vehicleOffenceID +  " :(");
        } 
    }
    
    public void testFindAllVehicleOffence(){
        System.out.println("\nFIND ALL VEHICLE OFFENCE");
        List<VehicleOffenceEO> vehOffEOList = vehicleOffenceDAORef.findAllVehicleOffence();
        if(vehOffEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(vehOffEOList);
        }else{
            System.out.println("Failed to FIND records from VehicleOffence Table :(");
        } 
    }
    
    public void testFindAllVehicleOffenceByRegisID(){
        System.out.println("\nFIND VEHICLE OFFENCE BY REGISTRATION_ID");
        String registrationID = "KA02KA0881";
        System.out.println("Registration ID: " + registrationID);
        List<VehicleOffenceEO> vehOffEOList = vehicleOffenceDAORef.findVehicleOffenceByRegisID(registrationID);
        if(vehOffEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(vehOffEOList);
        }else{
            System.out.println("Failed to FIND records from VehicleOffence Table :(");
        } 
    }
    
    public void testFindAllVehicleOffenceByStatus(){
        String registrationID = "KA02KA0818";
        int status = 0;
        System.out.println("Registration ID: " + registrationID + " Status: " + status);
        System.out.println("\nFIND VEHICLE OFFENCE BY REGISTRATION_ID AND STATUS");
        List<VehicleOffenceEO> vehOffEOList = vehicleOffenceDAORef.findVehicleOffenceByStatus(registrationID, status);
        if(vehOffEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(vehOffEOList);
        }else{
            System.out.println("Failed to FIND records from VehicleOffence Table :(");
        } 
    }
     
    public static void main(String[] args) {
        VehicleOffenceDAOTest vehOffDAOTest = new VehicleOffenceDAOTest();
        System.out.println("----------VEHICLE OFFENCE TABLE OPERATION-------------");
//        vehOffDAOTest.testInsertVehicleOffence();
//        vehOffDAOTest.testUpdateVehicleOffence();
//        vehOffDAOTest.testClearVehicleOffence();
//        vehOffDAOTest.testDeleteVehicleOffence();
//        vehOffDAOTest.testFindVehicleOffenceByID();
//        vehOffDAOTest.testFindAllVehicleOffence();
//        vehOffDAOTest.testFindAllVehicleOffenceByRegisID();
//        vehOffDAOTest.testFindAllVehicleOffenceByStatus();
    }
}

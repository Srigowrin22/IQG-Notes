package training.iqgateway.test;

import java.util.List;

import training.iqgateway.dao.RegistrationDAO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.factory.GenericDAOFactory;

public class RegistrationDAOTest {
    
    private RegistrationDAO registrationDAORef = GenericDAOFactory.createRegistrationDAO();
    
    public void testInsertRegistration() {
        System.out.println("INSERT REGISTRATION OPERATION");
        VehicleEO vehicleID = new VehicleEO();
        vehicleID.setVehicleID(12);
        OwnerEO ownerAadhar = new OwnerEO();
        ownerAadhar.setOwnerAadhar("9798 9876 6585");
                                                   
        RegistrationEO regisEO = new RegistrationEO(
            "KA02KA5076",
            "Karnataka, B: Koramangala",
            java.sql.Date.valueOf("2023-07-25"),
            "RTO01",
            vehicleID,
            ownerAadhar
        );
        int result = registrationDAORef.insertRegistration(regisEO);
        System.out.println(regisEO);
        if(result == 1){
            System.out.println("Record inserted successfully to Registration...");
        }else{
            System.out.println("Failed to insert record into Registration :(");
        }
    }
    
    public void testUpdateRegistration(){
        System.out.println("\nUPDATE REGISTRATION OPERATION");
        VehicleEO vehicleID = new VehicleEO();
        vehicleID.setVehicleID(12);
        OwnerEO ownerAadhar = new OwnerEO();
        ownerAadhar.setOwnerAadhar("9798 9876 6585");
        
        RegistrationEO regisEO = new RegistrationEO(
            "KA02KA5076",
            "Karnataka, B: Koramangala",
            java.sql.Date.valueOf("2020-07-25"),
            "RTO02",
            vehicleID,
            ownerAadhar
        );
        int result = registrationDAORef.updateRegistration(regisEO);
        System.out.println(regisEO);
        if(result == 1){
            System.out.println("Record UPDATED successfully in Registration...");
        }else{
            System.out.println("Failed to UPDATE record in Registration :(");
        }  
    }
    
    public void testDeleteRegistration(){
        System.out.println("\nDELETE REGISTRATION OPERATION");
        String regisID = "MH02AB5678";
        int result = registrationDAORef.deleteRegistration(regisID);
        System.out.println("Registration id: " + regisID);
        if(result == 1){
            System.out.println("Record DELETED successfully...");
        }else{
            System.out.println("Failed to DELETE record of " + regisID +  " :(");
        }  
    }
    
    public void testFindRegistrationByID(){
        System.out.println("\nFIND REGISTRATION BY REGISTRATION_ID OPERATION");
        String regisID = "KA02KA0818";
        RegistrationEO regisEO = registrationDAORef.findRegistrationByID(regisID);
        System.out.println("Registration id: " + regisID);
        if(regisEO != null ){
            System.out.println("Records FOUND successfully...");
            System.out.println(regisEO);
        }else{
            System.out.println("Failed to FIND record of " + regisID +  " :(");
        } 
    }
    
    public void testFindAllRegistrationsByAadhar(){
        System.out.println("\nFIND ALL REGISTRATION BY AADHAR OPERATION");
        String aadhar = "8363 2334 4322";
        List<RegistrationEO> regisEOList = registrationDAORef.findRegistrationByAadhar(aadhar);
        if(regisEOList != null && regisEOList.size() > 0){            
            System.out.println("Records FOUND successfully...");
            for(RegistrationEO regis : regisEOList) {
                System.out.println(regis);
            }
        }else{
            System.out.println("Failed to FIND records from Registration Table :(");
        } 
    }

    public void testFindAllRegistrations(){
        System.out.println("\nFIND ALL REGISTRATION OPERATION");
        List<RegistrationEO> regisEOList = registrationDAORef.findAllRegistrations();
        if(regisEOList != null && regisEOList.size() > 0){            
            System.out.println("Records FOUND successfully...");
            for(RegistrationEO regis : regisEOList) {
                System.out.println(regis);
            }
        }else{
            System.out.println("Failed to FIND records from Registration Table :(");
        } 
    }
        
    public static void main(String[] args) {
        System.out.println("------------REGISTRATION TABLE OPERATION----------------");
        RegistrationDAOTest regisDAOTest = new RegistrationDAOTest();
//        regisDAOTest.testInsertRegistration();
//        regisDAOTest.testUpdateRegistration();
//        regisDAOTest.testDeleteRegistration();
//        regisDAOTest.testFindRegistrationByID();
//        regisDAOTest.testFindAllRegistrationsByAadhar();
//        regisDAOTest.testFindAllRegistrations();
    }
}

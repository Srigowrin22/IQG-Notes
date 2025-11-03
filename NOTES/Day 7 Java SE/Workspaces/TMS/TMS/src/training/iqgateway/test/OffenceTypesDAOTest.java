package training.iqgateway.test;

import java.util.List;

import training.iqgateway.dao.OffenceTypesDAO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.factory.GenericDAOFactory;

public class OffenceTypesDAOTest {
    
    private OffenceTypesDAO offenceTypesDAORef = GenericDAOFactory.createOffenceTypesDAO();   

    public void testInsertOffenceTypes() {
//        OffenceTypesEO  offenceTypesEO = new OffenceTypesEO(1, "SIGNAL SKIPP", "CAR", 2000);
//        OffenceTypesEO  offenceTypesEO = new OffenceTypesEO(2, "SIGNAL SKIPPED", "BIKE", 1000);
//        OffenceTypesEO  offenceTypesEO = new OffenceTypesEO(3, "DRINK AND DRIVE", "CAR", 5000);
//        OffenceTypesEO  offenceTypesEO = new OffenceTypesEO(4, "DRINK AND DRIVE", "BIKE", 3500);
//        OffenceTypesEO  offenceTypesEO = new OffenceTypesEO(5, "OVERSPEEDING", "CAR", 3800);
//        OffenceTypesEO  offenceTypesEO = new OffenceTypesEO(6, "OVERSPEEDING", "BIKE", 2800);
//        OffenceTypesEO  offenceTypesEO = new OffenceTypesEO(7, "OVERSPEEDING", "BIKE", 2800);
        OffenceTypesEO  offenceTypesEO = new OffenceTypesEO(null, "NO HELMET", "BIKE", 3500);
        
        System.out.println("INSERT OFFENCE_TYPE OPERATION");
        int result = offenceTypesDAORef.insertOffenceType(offenceTypesEO);
        System.out.println(offenceTypesEO);
        if(result == 1){
            System.out.println("Record inserted successfully to Offence Types...");
        }else{
            System.out.println("Failed to insert record into Offence Types :(");
        }   
    }
    
    public void testUpdateOffenceTypes() {
        System.out.println("\nUPDATE OFFENCE_TYPE OPERATION");
        OffenceTypesEO  offenceTypesEO = new OffenceTypesEO(8, "CO-PASSENGER NOT WEARING HELMET", "BIKE", 2500);
        int result = offenceTypesDAORef.updateOffenceType(offenceTypesEO);
        System.out.println(offenceTypesEO);
        if(result == 1){
            System.out.println("Record updated successfully to Offence Types...");
        }else{
            System.out.println("Failed to update record into Offence Types :(");
        }   
    }
    
    public void testDeleteOffenceTypes() {
        System.out.println("\nDELETE OFFENCE_TYPE OPERATION");
        int offenceID = 8;
        int result = offenceTypesDAORef.deleteOffenceType(offenceID);
        System.out.println("Offence ID: " + offenceID);
        if(result == 1){
            System.out.println("Record deleted successfully from Offence Types...");
        }else{
            System.out.println("Failed to delete record into Offence Types :(");
        }   
    }
    public void testFindOffenceTypesByID(){
        System.out.println("\nFIND OFFENCE_TYPE BY ID OPERATION");
        int offenceID = 1;
        OffenceTypesEO offenceTypesEO = offenceTypesDAORef.findOffenceTypeByID(offenceID);
        System.out.println("Offence ID: " + offenceID);
        if(offenceTypesEO != null ){
            System.out.println("Records FOUND successfully...");
            System.out.println(offenceTypesEO);
        }else{
            System.out.println("Failed to FIND record of " + offenceID +  " :(");
        } 
    }
    
    public void testFindAllOffenceTypes(){
        System.out.println("\nFIND ALL OFFENCE_TYPE OPERATION");
        List<OffenceTypesEO> OffenceTypesEOList = offenceTypesDAORef.findAllOffenceType();
        if(OffenceTypesEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(OffenceTypesEOList);
        }else{
            System.out.println("Failed to FIND records from Departments Table :(");
        } 
    }
    
    public void testFindOffenceTypesByVehicleType(){
        System.out.println("\nFIND OFFENCE_TYPE BY VEHICLE_TYPE OPERATION");
        String vehicleType = "CAR";
        List<OffenceTypesEO> OffenceTypesEOList = offenceTypesDAORef.findOffenceByVehicleType(vehicleType);
        System.out.println("Vehicle Type: " + vehicleType);
        if(OffenceTypesEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(OffenceTypesEOList);
        }else{
            System.out.println("Failed to FIND records from Departments Table :(");
        } 
    }
    
    public void testFindOffenceTypesByOffenceName(){
        System.out.println("\nFIND OFFENCE_TYPE BY OFFENCE_NAME OPERATION");
        String offenceType = "SIG%";
        List<OffenceTypesEO> OffenceTypesEOList = offenceTypesDAORef.findOffenceByOffenceType(offenceType);
        System.out.println("Offence Type: " + offenceType);
        if(OffenceTypesEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(OffenceTypesEOList);
        }else{
            System.out.println("Failed to FIND records from Departments Table :(");
        } 
    }
    
    public static void main(String[] args) {
        System.out.println("---------OFFENCE_TYPE TABLE OPERATIONS------------");
        OffenceTypesDAOTest offenceTypesDAOTest = new OffenceTypesDAOTest();
        offenceTypesDAOTest.testInsertOffenceTypes();
        offenceTypesDAOTest.testUpdateOffenceTypes();
        offenceTypesDAOTest.testDeleteOffenceTypes();
        offenceTypesDAOTest.testFindOffenceTypesByID();
        offenceTypesDAOTest.testFindAllOffenceTypes();
        offenceTypesDAOTest.testFindOffenceTypesByVehicleType();
        offenceTypesDAOTest.testFindOffenceTypesByOffenceName();
    }  
}

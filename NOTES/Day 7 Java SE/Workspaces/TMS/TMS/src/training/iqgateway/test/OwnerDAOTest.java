package training.iqgateway.test;

import java.util.List;

import training.iqgateway.dao.OwnerDAO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.factory.GenericDAOFactory;

public class OwnerDAOTest {
    
    private OwnerDAO ownerDAORef = GenericDAOFactory.createOwnerDAO();
    
    public void testInsertOwner() {
//        OwnerEO  ownerEO = new OwnerEO(1, "Rohan R", "6786 5345 7644", "roh987", 987654321, "ChandraLayout, Bangalore");
//        OwnerEO  ownerEO = new OwnerEO(2, "Rakesh D", "9474 4653 2312", "rak898", 776345667, "Vijaynagar, Bangalore");
//        OwnerEO  ownerEO = new OwnerEO(3, "Smitha G", "8363 2334 4322", "smi980", 887632323, "Indrangar, Bangalore");
//        OwnerEO  ownerEO = new OwnerEO(4, "Santhosh A", "9463 2345 1233", "san998",  823332322, "MG Road, Bangalore");
//        OwnerEO  ownerEO = new OwnerEO(5, "Vinothini", "9352 4342 4212", "vin878",  823763232, "BTM Layout, Bangalore");
//        OwnerEO  ownerEO = new OwnerEO(6, "Nagesh V", "9052 8763 8763", "nag808",  948005028, "Chandra Layout, Bangalore");
//        OwnerEO  ownerEO = new OwnerEO(7, "Gowri N", "6627 0274 2623", "buz226",  914106008, "RR nagar, Bangalore");
        OwnerEO  ownerEO = new OwnerEO(null, "Geetha", "F", "9352 4764 4212", null, 778434544l, "BTM Layout, Bangalore", "get879");

        System.out.println("INSERT OWNER OPERATION");
        int result = ownerDAORef.insertOwner(ownerEO);
        System.out.println(ownerEO);
        if(result == 1){
            System.out.println("Record inserted successfully to Owner...");
        }else{
            System.out.println("Failed to insert record into Owner :(");
        }   
    }
    
    public void testUpdateOwner() {
        System.out.println("\nUPDATE OWNER OPERATION");
        OwnerEO  ownerEO = new OwnerEO(null, "GeethaShri", "F", "9352 4764 4212", null, 778434544l, "BTM Layout, Bangalore", "get879");
        int result = ownerDAORef.updateOwner(ownerEO);
        System.out.println(ownerEO);
        if(result == 1){
            System.out.println("Record updated successfully to Owner...");
        }else{
            System.out.println("Failed to update record into Owner :(");
        }   
    }
    
    public void testDeleteOwner() {
        System.out.println("\nDELETE OWNER OPERATION");
        String ownerAadhar = "9352 4244 4212";
        String password = "har985";
        int result = ownerDAORef.deleteOwner(ownerAadhar, password);
        System.out.println("Aadhar: " + ownerAadhar + " Password: " + password);
        if(result == 1){
            System.out.println("Record DELETED successfully...");
        }else{
            System.out.println("Failed to DELETE record  of " + ownerAadhar +  " :(");
        }   
    }
    
    public void testFindOwnertByAadhar(){
        System.out.println("\nFIND OWNER BY AADHAR OPERATION");
        String ownerAadhar = "6786 5345 7644";
        OwnerEO ownerEO = ownerDAORef.findOwnerByAadhar(ownerAadhar);
        System.out.println("Aadhar: " + ownerAadhar);
        if(ownerEO != null ){
            System.out.println("Records FOUND successfully...");
            System.out.println(ownerEO);
        }else{
            System.out.println("Failed to FIND record of " + ownerAadhar +  " :(");
        } 
    }
    
    public void testFindAllOwners(){
        System.out.println("\nFIND ALL OWNER OPERATION");
        List<OwnerEO> ownerEOList = ownerDAORef.findAllOwners();
        if(ownerEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(ownerEOList);
        }else{
            System.out.println("Failed to FIND records from Departments Table :(");
        } 
    }
    
    public void testFindOwnersByName(){
        System.out.println("\nFIND OWNER BY NAME OPERATION");
        String ownerName = "R%";
        List<OwnerEO> ownerEOList = ownerDAORef.findOwnerByName(ownerName);
        System.out.println("Owner Name: " + ownerName);
        if(ownerEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(ownerEOList);
        }else{
            System.out.println("Failed to FIND records from Departments Table :(");
        } 
    }
   
    public static void main(String[] args) {
        System.out.println("------------OWNER TABLE OPERATION------------");
        OwnerDAOTest ownerDAOTest = new OwnerDAOTest();
//        ownerDAOTest.testInsertOwner();
//        ownerDAOTest.testUpdateOwner();
//        ownerDAOTest.testDeleteOwner();
//        ownerDAOTest.testFindOwnertByAadhar();
//        ownerDAOTest.testFindAllOwners();
//        ownerDAOTest.testFindOwnersByName();      
    }
}

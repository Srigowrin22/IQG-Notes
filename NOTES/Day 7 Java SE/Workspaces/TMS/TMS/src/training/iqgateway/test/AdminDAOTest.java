package training.iqgateway.test;

import java.text.ParseException;

import java.util.Date;

import java.text.SimpleDateFormat;

import java.util.List;

import training.iqgateway.dao.AdminDAO;
import training.iqgateway.entities.AdminEO;
import training.iqgateway.entities.RoleEO;
import training.iqgateway.factory.GenericDAOFactory;

public class AdminDAOTest {
    
    private AdminDAO AdminDAORef = GenericDAOFactory.createAdminDAO();   
    
    public void testInsertAdmin() {
        RoleEO roleID = new RoleEO();
        roleID.setRoleID(1);
        
//        AdminEO  adminEO = new AdminEO(1, roleID, "RTO01", "Avi", "9887 6577 8784", "adm123", java.sql.Date.valueOf("2023-01-15"), 0 );
//        AdminEO  adminEO = new AdminEO(2, roleID, "PLC01", "Chaitanya", "8366 6547 8384", "adm123", java.sql.Date.valueOf("2023-01-15"), 0 );
//        AdminEO  adminEO = new AdminEO(3, roleID, "CLK01", "Nadiya", "6466 6237 7632", "adm123", java.sql.Date.valueOf("2023-01-15"), 0 );
//        AdminEO  adminEO = new AdminEO(4, roleID, "PLC02", "Surrya", "9823 8763 7832", "adm123", java.sql.Date.valueOf("2023-01-15"), 0 );
        AdminEO  adminEO = new AdminEO(null, roleID, null, "John", "M", "6465 8768 0636",787654334l, null, null, 0);

        int result = AdminDAORef.insertAdmin(adminEO);
        System.out.println("INSERT ADMIN OPERATION");
        System.out.println(adminEO);
        if(result == 1){
            System.out.println("Record inserted successfully to Admin...");
        }else{
            System.out.println("Failed to insert record into Admin :(");
        }   
    }
    
    public void testUpdateAdmin(){
        
        AdminEO  adminEO = new AdminEO(null, null, "RTO06", "Johny", "M", "8768 8768 0636",787654334l, null, null, 0);
        int result = AdminDAORef.updateAdmin(adminEO);
        System.out.println("\nUPDATE ADMIN OPERATION");
        System.out.println(adminEO);
        if(result == 1){
            System.out.println("Record updated successfully to Admin...");
        }else{
            System.out.println("Failed to update record into Admin :(");
        }  
    }
    
    public void testAthorizeAdmin(){
        String desigID = "RTO06";
        String password = "adm";
        int result = AdminDAORef.authorizeAdmin(desigID, password);
        System.out.println("\nAUTHORIZE ADMIN OPERATION");
        System.out.println("Designation ID: " + desigID);
        if(result == 1){
            System.out.println("Record updated successfully to Authoize Admin...");
        }else{
            System.out.println("Failed to update record into Authoize Admin :(");
        }  
    }
    
    public void testDeleteAdmin(){
        String desigID = "RTO06";
        int result = AdminDAORef.deleteAdmin(desigID);
        System.out.println("\nADMIN DELETE OPERATION");
        System.out.println("Designation ID: " + desigID);
        if(result == 1){
            System.out.println("Record deleted successfully to Admin...");
        }else{
            System.out.println("Failed to deleted record into Admin :(");
        }  
    }
    
    public void testFindAdminByDesigID(){
        String desigID = "RTO06";
        AdminEO adminEO = AdminDAORef.findAdminByDesigID(desigID);
        System.out.println("\nFIND ADMIN BY DESIGNATION_ID OPERATION");
        System.out.println("Designation ID: " + desigID);
        if(adminEO != null ){
            System.out.println("Records FOUND successfully...");
            System.out.println(adminEO);
        }else{
            System.out.println("Failed to FIND record of " + desigID +  " :(");
        } 
    }
    
    public void testFindAdminByAadhar(){
        String aadhar = "6465 8768 0636";
        AdminEO adminEO = AdminDAORef.findAdminByAadhar(aadhar);
        System.out.println("\nFIND ADMIN BY AADHAR OPERATION");
        System.out.println("Aadhar: " + aadhar);
        if(adminEO != null ){
            System.out.println("Records FOUND successfully...");
            System.out.println(adminEO);
        }else{
            System.out.println("Failed to FIND record of " + aadhar +  " :(");
        } 
    }
    
    public void testFindAllAdmin(){
        List<AdminEO> adminEOList = AdminDAORef.findAllAdmins();
        System.out.println("\nFIND ALL ADMIN OPERATION");
        if(adminEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(adminEOList);
        }else{
            System.out.println("Failed to FIND records from Admin Table :(");
        } 
    }
    
    public void testFindAdminByRoleID(){
        int roleID = 2;
        List<AdminEO> adminEOList = AdminDAORef.findAdminByRoleID(roleID);
        System.out.println("\nFIND ADMIN BY ROLE_ID OPERATION");
        System.out.println("RoleID: " + roleID);
        if(adminEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(adminEOList);
        }else{
            System.out.println("Failed to FIND records from Admin Table :(");
        } 
    }
    
    public void testFindAdminByName(){
        String name = "Sur%";
        List<AdminEO> adminEOList = AdminDAORef.findAdminByName(name);
        System.out.println("\nFIND ADMIN BY ADMIN_NAME OPERATION");
        System.out.println("Name: " + name);
        if(adminEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(adminEOList);
        }else{
            System.out.println("Failed to FIND records from Admin Table :(");
        } 
    }
        
    public static void main(String[] args) {
        System.out.println("---------ADMIN TABLE OPERATIONS----------");
        AdminDAOTest adminDAOTest = new AdminDAOTest();
//        adminDAOTest.testInsertAdmin();
//        adminDAOTest.testUpdateAdmin();
//        adminDAOTest.testAthorizeAdmin();
//        adminDAOTest.testDeleteAdmin();
//        adminDAOTest.testFindAdminByDesigID();
//        adminDAOTest.testFindAdminByAadhar();
//        adminDAOTest.testFindAllAdmin();
//        adminDAOTest.testFindAdminByRoleID();
//        adminDAOTest.testFindAdminByName();  
    }
}

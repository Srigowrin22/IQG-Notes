package training.iqgateway.test;

import java.util.List;

import training.iqgateway.dao.RoleDAO;
import training.iqgateway.entities.RoleEO;
import training.iqgateway.factory.GenericDAOFactory;

public class RoleDAOTest {
    private static RoleDAO roleDAORef = GenericDAOFactory.createRoleDAO() ;

    public void testInsertRole(){
//        RoleEO roleEO = new RoleEO(1, "RTO");
//        RoleEO roleEO = new RoleEO(2, "POLICE");
//        RoleEO roleEO = new RoleEO(3, "CLERK");
        RoleEO roleEO = new RoleEO(4, "ADMIN");

        int result = roleDAORef.insertRole(roleEO);
        System.out.println("INSERT ROLE OPERATION:");
        System.out.println(roleEO);
        if(result == 1){
            System.out.println("Record inserted successfully to Role Table...");
        }else{
            System.out.println("Failed to insert record into Role Table :(");
        }   
    }
    
    public void testUpdateRole(){
        RoleEO roleEO = new RoleEO(4, "ADMIN");
        int result = roleDAORef.updateRole(roleEO);
        System.out.println("\nUPDATE ROLE OPERATION:");
        System.out.println(roleEO);
        if(result == 1){
            System.out.println("Record UPDATED successfully to Role...");
        }else{
            System.out.println("Failed to UPDATE record into Role :(");
        }  
    }
    
    public void testDeleteDepartment(){
        int roleID = 4;
        int result = roleDAORef.deleteRole(roleID);
        System.out.println("\nDELETE ROLE OPERATION:");
        System.out.println("RoleID: " + roleID);
        if(result == 1){
            System.out.println("Record DELETED successfully...");
        }else{
            System.out.println("Failed to DELETE record  of " + roleID +  " :(");
        }  
    }
    
    public void testFindRoleByID(){
        int roleID = 2;
        RoleEO roleEO = roleDAORef.findRoleByRoleID(roleID);
        System.out.println("\nFIND ROLE BY ROLE_ID OPERATION:");
        System.out.println("RoleID: " + roleID);
        if(roleEO != null ){
            System.out.println("Records FOUND successfully...");
            System.out.println(roleEO);
        }else{
            System.out.println("Failed to FIND record of " + roleID +  " :(");
        } 
    }
    
    public void testFindAllRoles(){
        List<RoleEO> roleEOList = roleDAORef.findAllRoles();
        System.out.println("\nFIND ALL ROLE OPERATION:");
        if(roleEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(roleEOList);
        }else{
            System.out.println("Failed to FIND records from Departments Table :(");
        } 
    }
    
    public void testFindRoleByName(){
        String roleName = "POL%";
        List<RoleEO> roleEOList = roleDAORef.findRoleByRoleName(roleName);
        System.out.println("\nFIND ROLE BY ROLE_NAME OPERATION:");
        System.out.println("RoleName: " + roleName);
        if(roleEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(roleEOList);
        }else{
            System.out.println("Failed to FIND records from Departments Table :(");
        } 
    }
    
//    public void testCountRoles(){
//    
//    }
    
    public static void main(String[] args) {
        RoleDAOTest roleDAOTest = new RoleDAOTest();
        System.out.println("-----------ROLE TABLE OPERATIONS----------");
//        roleDAOTest.testInsertRole();
//        roleDAOTest.testUpdateRole();
//        roleDAOTest.testDeleteDepartment();
//        roleDAOTest.testFindRoleByID();
//        roleDAOTest.testFindAllRoles();
//        roleDAOTest.testFindRoleByName();
        
    }
}

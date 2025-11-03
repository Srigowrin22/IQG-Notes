package training.iqgateway.test;

import java.util.List;

import training.iqgateway.dao.DepartmentDAO;
import training.iqgateway.dao.impl.DepartmentDAOImpl;
import training.iqgateway.entities.DepartmentEO;
import training.iqgateway.factory.DepartmentDAOFactory;

public class DepartmentDAOTest {
    
    private DepartmentDAO departmentDAORef = DepartmentDAOFactory.createDepartmentDAO();   
    
    public void testInsertDepartment() {
        DepartmentEO  deptEO = new DepartmentEO(292, "Learning & Development", 100, 1700);
        int result = departmentDAORef.insertDepartment(deptEO);
        if(result == 1){
            System.out.println("Record inserted successfully to Departments...");
        }else{
            System.out.println("Failed to insert record into Departments :(");
        }   
    }
    
    public void testUpdateDepartment(){
        DepartmentEO deptEO = new DepartmentEO(292, "LEARNING JDBC", 110, 1700);
        int result = departmentDAORef.updateDepartment(deptEO);
        if(result == 1){
            System.out.println("Record UPDATED successfully to Departments...");
        }else{
            System.out.println("Failed to UPDATE record into Departments :(");
        }  
    }
    
    public void testDeleteDepartment(){
        int deptID = 292;
        int result = departmentDAORef.deleteDepartment(deptID);
        if(result == 1){
            System.out.println("Record DELETED successfully...");
        }else{
            System.out.println("Failed to DELETE record  of " + deptID +  " :(");
        }  
    }
    
    public void testFindDepartmentByID(){
        int deptID = 290;
        DepartmentEO deptEO = departmentDAORef.findDepartmentByID(deptID);
        if(deptEO != null ){
            System.out.println("Records FOUND successfully...");
            System.out.println(deptEO);
        }else{
            System.out.println("Failed to FIND record of " + deptID +  " :(");
        } 
    }
    
    public void testFindAllDepartments(){
        List<DepartmentEO> deptEOList = departmentDAORef.findAllDepartments();
        if(deptEOList.size()>0){            
            System.out.println("Records FOUND successfully...");
            System.out.println(deptEOList);
        }else{
            System.out.println("Failed to FIND records from Departments Table :(");
        } 
    }
    
    public void  testFindDepartmentByManagerID(){
        int mgrID = 100;
        List<DepartmentEO> deptEOList = departmentDAORef.findDepartmentByManagerID(mgrID);
        if(deptEOList.size()>0){
            System.out.println("Records FOUND successfully...");
            System.out.println(deptEOList);
        }else{
            System.out.println("Failed to FIND records from Departments Table :(");
        } 
    }
    
    public void  testFindLocationByManagerID(){
        int locID = 1700;
        List<DepartmentEO> deptEOList = departmentDAORef.findDepartmentByLocationID(locID);
        if(deptEOList.size()>0){
            System.out.println("Records FOUND successfully...");
            System.out.println(deptEOList);
        }else{
            System.out.println("Failed to FIND records from Departments Table :(");
        } 
    }


    public static void main(String[] args) {
        DepartmentDAOTest deptTest = new DepartmentDAOTest();
//        deptTest.testInsertDepartment();
//        deptTest.testUpdateDepartment();
//        deptTest.testDeleteDepartment();
//        deptTest.testFindDepartmentByID();
//        deptTest.testFindAllDepartments();
//        deptTest.testFindDepartmentByManagerID();
//        deptTest.testFindLocationByManagerID();
        
    }
}

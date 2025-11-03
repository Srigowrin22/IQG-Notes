package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.DepartmentEO;

public interface DepartmentDAO {
    
    int insertDepartment (DepartmentEO deptEO);
    
    int updateDepartment (DepartmentEO deptEO);
    
    int deleteDepartment (Integer deptID);

    DepartmentEO findDepartmentByID(Integer deptID);
    
    List<DepartmentEO> findAllDepartments();
    
    List<DepartmentEO> findDepartmentByManagerID(Integer mgrID);
    
    List<DepartmentEO> findDepartmentByLocationID (Integer locID);
    
}

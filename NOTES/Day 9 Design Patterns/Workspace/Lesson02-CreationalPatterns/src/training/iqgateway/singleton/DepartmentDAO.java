/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package training.iqgateway.singleton;

import java.util.List;

/**
 *
 * @author srigowri.n
 */
public interface DepartmentDAO {
    public abstract Integer insertDepartment(DepartmentEO deptEO);
    public abstract Integer updateDepartment(DepartmentEO deptEO);
    public abstract Integer deleteDepartment(Integer deptID);
    public abstract DepartmentEO findDepartmentByID(Integer deptID);
    public abstract List<DepartmentEO> getAllDepartment();   
}

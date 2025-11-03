package training.iqgateway.business;

import java.util.List;

import training.iqgateway.entity.DepartmentEO;

public interface AdminOperations {
	
	public Integer addDepartment(DepartmentEO deptEO);

	public List<DepartmentEO> fetchAllDepartments();

	public List<DepartmentEO> fetchDepartmentsBasedonLocation(Integer locationId);

}
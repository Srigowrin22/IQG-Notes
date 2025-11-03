package training.iqgateway;

import java.util.List;

public interface DepartmentService {
	
	public List<DepartmentEO> getAllDepartments();
	
	public void addDepartment(DepartmentEO deptEORef);
}

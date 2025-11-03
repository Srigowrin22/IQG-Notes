package training.iqgateway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepositoryRef;

	public List<DepartmentEO> getAllDepartments() {
		List<DepartmentEO> returnedDepartmentList = new ArrayList<DepartmentEO>();
		departmentRepositoryRef.findAll().forEach(returnedDepartmentList::add);
		return returnedDepartmentList;
	}

	public void addDepartment(DepartmentEO deptEORef) {
		departmentRepositoryRef.save(deptEORef);
	}

}

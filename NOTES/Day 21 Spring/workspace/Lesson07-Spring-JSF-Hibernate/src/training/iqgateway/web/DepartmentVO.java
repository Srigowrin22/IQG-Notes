package training.iqgateway.web;

import training.iqgateway.business.AdminOperations;
import training.iqgateway.entity.DepartmentEO;


public class DepartmentVO {

	private Integer deptId;

	private String deptName;

	private Integer mgrId;

	private Integer locId;

	private AdminOperations adminOperRef;

	public DepartmentVO() {

		// TODO Auto-generated constructor stub

	}

	public DepartmentVO(AdminOperations adminOperRef) {

		super();

		this.adminOperRef = adminOperRef;

	}

	public AdminOperations getAdminOperRef() {

		return adminOperRef;

	}
 
	public void setAdminOperRef(AdminOperations adminOperRef) {

		this.adminOperRef = adminOperRef;

	}
 
	public Integer getDeptId() {

		return deptId;

	}
 
	public void setDeptId(Integer deptId) {

		this.deptId = deptId;

	}
 
	public String getDeptName() {

		return deptName;

	}
 
	public void setDeptName(String deptName) {

		this.deptName = deptName;

	}
 
	public Integer getMgrId() {

		return mgrId;

	}
 
	public void setMgrId(Integer mgrId) {

		this.mgrId = mgrId;

	}
 
	public Integer getLocId() {

		return locId;

	}
 
	public void setLocId(Integer locId) {

		this.locId = locId;

	}
 
	public String navigate() {

		// Create an EO

		DepartmentEO deptEORef = new DepartmentEO();

		deptEORef.setDepartmentId(this.deptId);

		deptEORef.setDepartmentName(this.deptName);

		deptEORef.setManagerId(this.mgrId);

		deptEORef.setLocationId(this.locId);

		// Invoke Business Method

		Integer result = adminOperRef.addDepartment(deptEORef);

		if (result != null) {

			return "success";

		} 

		else {

			return "failure";

		}

	}

}

 
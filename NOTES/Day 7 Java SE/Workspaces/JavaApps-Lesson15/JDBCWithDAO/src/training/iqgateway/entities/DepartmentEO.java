package training.iqgateway.entities;

public class DepartmentEO {
    private Integer departmentID;
    private String departmentName;
    private Integer managerID;
    private Integer locationID;

    public DepartmentEO() {
        super();
    }

    public DepartmentEO(Integer departmentID, String departmentName, Integer managerID, Integer locationID) {
        super();
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.managerID = managerID;
        this.locationID = locationID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setManagerID(Integer ManagerID) {
        this.managerID = ManagerID;
    }

    public Integer getManagerID() {
        return managerID;
    }

    @Override
    public String toString() {
        return "Department Details [Department ID: " + this.departmentID +
            "\t" + "Department Name: " + this.departmentName + "\t" +
             "Manager ID: " + this.managerID + "\t" + 
             "Location ID: " + this.locationID + "]\n";
    }
}

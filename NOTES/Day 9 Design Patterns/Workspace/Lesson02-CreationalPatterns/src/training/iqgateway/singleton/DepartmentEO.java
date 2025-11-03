/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.singleton;

/**
 *
 * @author srigowri.n
 */
public class DepartmentEO {
    private Integer deptID;
    private String deptName;
    private Integer mgrID;
    private Integer locID;

    public DepartmentEO() {
    }

    public DepartmentEO(Integer deptID, String deptName, Integer mgrID, Integer locID) {
        this.deptID = deptID;
        this.deptName = deptName;
        this.mgrID = mgrID;
        this.locID = locID;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public String getDeptName() {
        return deptName;
    }

    public Integer getMgrID() {
        return mgrID;
    }

    public Integer getLocID() {
        return locID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setMgrID(Integer mgrID) {
        this.mgrID = mgrID;
    }

    public void setLocID(Integer locID) {
        this.locID = locID;
    }
}

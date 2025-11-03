package training.iqgateway.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import training.iqgateway.dao.DepartmentDAO;
import training.iqgateway.entities.DBUtils;
import training.iqgateway.entities.DepartmentEO;

public class DepartmentDAOImpl implements DepartmentDAO {

    private String SQLInsertStatement =
        "INSERT INTO DEPARTMENTS VALUES(?,?,?,?)";

    private String SQLUpdateStatement =
        "UPDATE DEPARTMENTS SET DEPARTMENT_NAME = ?, MANAGER_ID = ?, LOCATION_ID = ?" +
        "WHERE DEPARTMENT_ID = ?";

    private String SQLDeleteStatement =
        "DELETE FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?";

    private String SQLSelectStatementByID =
        "SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?";

    private String SQLSelectAllStatement = "SELECT * FROM DEPARTMENTS";

    private String SQLSelectStatementByManagerID =
        "SELECT * FROM DEPARTMENTS WHERE MANAGER_ID = ?";

    private String SQLSelectStatementByLocationID =
        "SELECT * FROM DEPARTMENTS WHERE LOCATION_ID = ?";

    public int insertDepartment(DepartmentEO deptEO) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLInsertStatement);
            pstat.setInt(1, deptEO.getDepartmentID());
            pstat.setString(2, deptEO.getDepartmentName());
            pstat.setInt(3, deptEO.getManagerID());
            pstat.setInt(4, deptEO.getLocationID());

            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    public int updateDepartment(DepartmentEO deptEO) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLUpdateStatement);
            pstat.setString(1, deptEO.getDepartmentName());
            pstat.setInt(2, deptEO.getManagerID());
            pstat.setInt(3, deptEO.getLocationID());
            pstat.setInt(4, deptEO.getDepartmentID());

            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    public int deleteDepartment(Integer deptID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLDeleteStatement);
            pstat.setInt(1, deptID);
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    public DepartmentEO findDepartmentByID(Integer deptID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;

        DepartmentEO deptEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByID);
            pstat.setInt(1, deptID);

            rs = pstat.executeQuery();
            while (rs.next()) {
                deptEO = new DepartmentEO();
                deptEO.setDepartmentID(rs.getInt(1));
                deptEO.setDepartmentName(rs.getString(2));
                deptEO.setManagerID(rs.getInt(3));
                deptEO.setLocationID(rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return deptEO;
    }

    public List<DepartmentEO> findAllDepartments() {
        Connection con = DBUtils.obtainConnection();
        Statement stat = null;
        ResultSet rs = null;

        List<DepartmentEO> deptEOList = new ArrayList<DepartmentEO>();
//        DepartmentEO deptEO = new DepartmentEO();

        try {
            stat = con.createStatement();
            rs = stat.executeQuery(SQLSelectAllStatement);
            while (rs.next()) {
//                deptEO.setDepartmentID(rs.getInt(1));
//                deptEO.setDepartmentName(rs.getString(2));
//                deptEO.setManagerID(rs.getInt(3));
//                deptEO.setLocationID(rs.getInt(4));

                int deptID = rs.getInt(1);
                String deptName = rs.getString(2);
                int mgrID = rs.getInt(3);
                int locID = rs.getInt(4);
                DepartmentEO deptEO = new DepartmentEO(deptID, deptName, mgrID, locID);
                deptEOList.add(deptEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(stat);
        DBUtils.close(con);
        return deptEOList;
    }

    public List<DepartmentEO> findDepartmentByManagerID(Integer mgrID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;

        List<DepartmentEO> deptEOList = new ArrayList<DepartmentEO>();
        DepartmentEO deptEO = null;

        try {
            pstat = con.prepareStatement(SQLSelectStatementByManagerID);
            pstat.setInt(1, mgrID);
            rs = pstat.executeQuery();
            while (rs.next()) { 
                deptEO = new DepartmentEO();
                deptEO.setDepartmentID(rs.getInt(1));
                deptEO.setDepartmentName(rs.getString(2));
                deptEO.setManagerID(rs.getInt(3));
                deptEO.setLocationID(rs.getInt(4));
                deptEOList.add(deptEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return deptEOList;
    }

    public List<DepartmentEO> findDepartmentByLocationID(Integer locID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;

        List<DepartmentEO> deptEOList = new ArrayList<DepartmentEO>();
        DepartmentEO deptEO;

        try {
            pstat = con.prepareStatement(SQLSelectStatementByLocationID);
            pstat.setInt(1, locID);
            rs = pstat.executeQuery();
            while (rs.next()) {
                deptEO = new DepartmentEO();
                deptEO.setDepartmentID(rs.getInt(1));
                deptEO.setDepartmentName(rs.getString(2));
                deptEO.setManagerID(rs.getInt(3));
                deptEO.setLocationID(rs.getInt(4));
                deptEOList.add(deptEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return deptEOList;
    }
}


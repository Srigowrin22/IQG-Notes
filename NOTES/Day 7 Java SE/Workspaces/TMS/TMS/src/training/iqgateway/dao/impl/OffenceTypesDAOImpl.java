package training.iqgateway.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import training.iqgateway.dao.OffenceTypesDAO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.utils.DBUtils;

/**
 * DAO implementation for OffenceTypes operations.
 */
public class OffenceTypesDAOImpl implements OffenceTypesDAO {
    private String SQLInsertStatement = 
        "INSERT INTO OFFENCE_TYPES VALUES (?,?,?,?)";
    
    private String SQLUpdateStatement = 
        "UPDATE OFFENCE_TYPES SET OFFENCE_TYPE = ?, VEHICLE_TYPE = ?, PENALTY_AMT = ? WHERE OFFENCE_ID = ?";
    
    private String SQLDeleteStatement = 
        "DELETE FROM OFFENCE_TYPES WHERE OFFENCE_ID = ?";
    
    private String SQLSelectStatementByID = 
        "SELECT * FROM OFFENCE_TYPES WHERE OFFENCE_ID = ?";
    
    private String SQLSelectAllStatement= 
        "SELECT * FROM OFFENCE_TYPES";
    
    private String SQLSelectStatementByVehicleType = 
        "SELECT * FROM OFFENCE_TYPES WHERE VEHICLE_TYPE = ?";
    
    private String SQLSelectStatementByOffenceType = 
        "SELECT * FROM OFFENCE_TYPES WHERE OFFENCE_TYPE LIKE ?";

    /**
     * Inserts a new offence type into the database.
     * @param offenceEO OffenceTypesEO object
     * @return number of records inserted
     */
    public int insertOffenceType(OffenceTypesEO offenceEO) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLInsertStatement);
            int nextID = getNextOffenceTypeID();
            offenceEO.setOffenceID(nextID);
            
            pstat.setInt(1, offenceEO.getOffenceID());
            pstat.setString(2, offenceEO.getOffenceType());
            pstat.setString(3, offenceEO.getVehicleType());
            pstat.setInt(4, offenceEO.getPenaltyAmt());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Gets the next OffenceTypes ID (max + 1).
     * @return next available offence_id
     */
    private int getNextOffenceTypeID(){
        String sql = "SELECT NVL(MAX(OFFENCE_ID), 0) + 1 FROM OFFENCE_TYPES";
        Connection con = DBUtils.obtainConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * Updates an existing offence type in the database.
     * @param offenceEO OffenceTypesEO object
     * @return number of records updated
     */
    public int updateOffenceType(OffenceTypesEO offenceEO) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLUpdateStatement);
            pstat.setString(1, offenceEO.getOffenceType());
            pstat.setString(2, offenceEO.getVehicleType());
            pstat.setInt(3, offenceEO.getPenaltyAmt());
            pstat.setInt(4, offenceEO.getOffenceID());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Deletes an offence type by its ID.
     * @param offenceID Offence ID
     * @return number of records deleted
     */
    public int deleteOffenceType(Integer offenceID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLDeleteStatement);
            pstat.setInt(1, offenceID);
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Finds an offence type by its ID.
     * @param offenceID Offence ID
     * @return OffenceTypesEO object or null if not found
     */
    public OffenceTypesEO findOffenceTypeByID(Integer offenceID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        OffenceTypesEO offenceEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByID);
            pstat.setInt(1, offenceID);
            rs = pstat.executeQuery();
            while (rs.next()) {
                offenceEO = new OffenceTypesEO();
                offenceEO.setOffenceID(rs.getInt(1));
                offenceEO.setOffenceType(rs.getString(2));
                offenceEO.setVehicleType(rs.getString(3));
                offenceEO.setPenaltyAmt(rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return offenceEO;
    }

    /**
     * Returns a list of all offence types in the database.
     * @return list of OffenceTypesEO objects
     */
    public List<OffenceTypesEO> findAllOffenceType() {
        Connection con = DBUtils.obtainConnection();
        Statement stat = null;
        ResultSet rs = null;
        List<OffenceTypesEO> offenceList = new ArrayList<OffenceTypesEO>();
        OffenceTypesEO offenceEO = null;
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(SQLSelectAllStatement);
            while (rs.next()) {
                offenceEO = new OffenceTypesEO();
                offenceEO.setOffenceID(rs.getInt(1));
                offenceEO.setOffenceType(rs.getString(2));
                offenceEO.setVehicleType(rs.getString(3));
                offenceEO.setPenaltyAmt(rs.getInt(4));
                offenceList.add(offenceEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(stat);
        DBUtils.close(con);
        return offenceList;
    }

    /**
     * Finds offence types by vehicle type.
     * @param vehicleType Vehicle type
     * @return list of OffenceTypesEO objects
     */
    public List<OffenceTypesEO> findOffenceByVehicleType(String vehicleType) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<OffenceTypesEO> offenceList = new ArrayList<OffenceTypesEO>();
        OffenceTypesEO offenceEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByVehicleType);
            pstat.setString(1, vehicleType);
            rs = pstat.executeQuery();
            while (rs.next()) {
                offenceEO = new OffenceTypesEO();
                offenceEO.setOffenceID(rs.getInt(1));
                offenceEO.setOffenceType(rs.getString(2));
                offenceEO.setVehicleType(rs.getString(3));
                offenceEO.setPenaltyAmt(rs.getInt(4));
                offenceList.add(offenceEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return offenceList;
    }

    /**
     * Finds offence types by offence type using LIKE query.
     * @param offenceType Offence type pattern
     * @return list of OffenceTypesEO objects
     */
    public List<OffenceTypesEO> findOffenceByOffenceType(String offenceType) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<OffenceTypesEO> offenceList = new ArrayList<OffenceTypesEO>();
        OffenceTypesEO offenceEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByOffenceType);
            pstat.setString(1, offenceType);
            rs = pstat.executeQuery();
            while (rs.next()) {
                offenceEO = new OffenceTypesEO();
                offenceEO.setOffenceID(rs.getInt(1));
                offenceEO.setOffenceType(rs.getString(2));
                offenceEO.setVehicleType(rs.getString(3));
                offenceEO.setPenaltyAmt(rs.getInt(4));
                offenceList.add(offenceEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return offenceList;
    }
}

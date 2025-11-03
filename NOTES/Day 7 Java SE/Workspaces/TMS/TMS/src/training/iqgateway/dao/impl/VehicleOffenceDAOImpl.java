package training.iqgateway.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import training.iqgateway.dao.VehicleOffenceDAO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.entities.VehicleOffenceEO;
import training.iqgateway.utils.DBUtils;


/**
 * DAO implementation for VehicleOffence operations.
 */
public class VehicleOffenceDAOImpl implements VehicleOffenceDAO {

    private String SQLInsertStatement =
        "INSERT INTO VEHICLE_OFFENCE VALUES (seq_vehOff.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private String SQLUpdateStatement =
        "UPDATE VEHICLE_OFFENCE SET OFFENCE_ID = ?, REGISTRATION_ID = ?, LOCATION = ?, OFFENCE_DATE = ?, TIME = ?, STATUS = ?, REPORTER = ?, PROOF1 = ?, PROOF2 = ? WHERE VEHICLE_OFFENCE_ID = ?";

    private String SQLClearStatement =
        "UPDATE VEHICLE_OFFENCE SET STATUS = 1, PROOF1 = NULL, PROOF2 = NULL WHERE VEHICLE_OFFENCE_ID = ? AND REGISTRATION_ID = ?";

    private String SQLDeleteStatement =
        "DELETE FROM VEHICLE_OFFENCE WHERE VEHICLE_OFFENCE_ID = ?";

    private String SQLSelectStatementByID =
        "SELECT * FROM VEHICLE_OFFENCE WHERE VEHICLE_OFFENCE_ID = ?";

    private String SQLSelectAllStatement = 
        "SELECT * FROM VEHICLE_OFFENCE";

    private String SQLSelectStatementByRegisID =
        "SELECT * FROM VEHICLE_OFFENCE WHERE REGISTRATION_ID = ?";


    private String SQLSelectStatementByStatus =
        "SELECT * FROM VEHICLE_OFFENCE WHERE REGISTRATION_ID = ? AND STATUS = ?";

    /**
     * Inserts a new vehicle offence record.
     */
     public int insertVehicleOffence(VehicleOffenceEO vehOffEO) {
              
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            // Calculate next ID
            int nextID = getNextVehicleOffenceID(con);
            vehOffEO.setVehicleOffenceID(nextID);
            pstat = con.prepareStatement(SQLInsertStatement);
            pstat.setInt(1, vehOffEO.getOffenceID().getOffenceID());
            pstat.setString(2, vehOffEO.getRegistrationID().getRegistrationID());
            pstat.setString(3, vehOffEO.getLocation());
            pstat.setDate(4, vehOffEO.getDate());
            
            // Only this line is changed: Convert Time to String for VARCHAR2 column
             java.sql.Time time = vehOffEO.getTime();
             String timeString = null;
             if (time != null) {
                 timeString = new java.text.SimpleDateFormat("hh:mm a").format(time);
             }
             pstat.setString(5, timeString);
            
            pstat.setInt(6, vehOffEO.getStatus() != null ? vehOffEO.getStatus() : 0);
            pstat.setString(7, vehOffEO.getReporter());
            pstat.setBytes(8, vehOffEO.getProof1());
            pstat.setBytes(9, vehOffEO.getProof2());
            count = pstat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }
    
    /**
     * Gets the next Vehicle Offence ID (max + 1).
     * @return next available vehicle_offence_id
     */
     private int getNextVehicleOffenceID(Connection con) throws SQLException {
         String sql = "SELECT NVL(MAX(VEHICLE_OFFENCE_ID), 0) + 1 FROM VEHICLE_OFFENCE";
         Statement stat = con.createStatement();
         ResultSet rs = stat.executeQuery(sql);
         try {
             if (rs.next()) {
                 return rs.getInt(1);
             }
         }catch(Exception e){
             e.printStackTrace();
         }
         return 1; // Default to 1 if table is empty
     }

    /**
     * Updates an existing vehicle offence record.
     */
    public int updateVehicleOffence(VehicleOffenceEO vehOffEO) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLUpdateStatement);
            pstat.setInt(1, vehOffEO.getOffenceID().getOffenceID());
            pstat.setString(2, vehOffEO.getRegistrationID().getRegistrationID());
            pstat.setString(3, vehOffEO.getLocation());
            pstat.setDate(4, vehOffEO.getDate());
            pstat.setTime(5, vehOffEO.getTime());
            pstat.setInt(6, vehOffEO.getStatus() != null ? vehOffEO.getStatus() : 0);
            pstat.setString(7, vehOffEO.getReporter());
            pstat.setBytes(8, vehOffEO.getProof1());
            pstat.setBytes(9, vehOffEO.getProof2());
            pstat.setInt(10, vehOffEO.getVehicleOffenceID());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Clears the offence by setting status to 1 and removing proofs.
     */
    public int clearVehicleOffence(VehicleOffenceEO vehOffEO) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLClearStatement);
            pstat.setInt(1, vehOffEO.getVehicleOffenceID());
            pstat.setString(2, vehOffEO.getRegistrationID().getRegistrationID());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Deletes a vehicle offence by its ID.
     */
    public int deleteVehicleOffence(Integer vehicleOffenceID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLDeleteStatement);
            pstat.setInt(1, vehicleOffenceID);
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Finds a vehicle offence by its ID.
     */
    public VehicleOffenceEO findVehicleOffenceByID(Integer vehicleOffenceID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        VehicleOffenceEO vehOffEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByID);
            pstat.setInt(1, vehicleOffenceID);
            rs = pstat.executeQuery();
            if (rs.next()) {
                vehOffEO = mapResultSetToEO(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return vehOffEO;
    }

    /**
     * Returns all vehicle offences.
     */
    public List<VehicleOffenceEO> findAllVehicleOffence() {
        Connection con = DBUtils.obtainConnection();
        Statement stat = null;
        ResultSet rs = null;
        List<VehicleOffenceEO> vehOffList = new ArrayList<VehicleOffenceEO>();
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(SQLSelectAllStatement);
            while (rs.next()) {
                vehOffList.add(mapResultSetToEO(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(stat);
        DBUtils.close(con);
        return vehOffList;
    }

    /**
     * Finds vehicle offences by registration ID.
     */
    public List<VehicleOffenceEO> findVehicleOffenceByRegisID(String registrationID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<VehicleOffenceEO> vehOffList = new ArrayList<VehicleOffenceEO>();
        try {
            pstat = con.prepareStatement(SQLSelectStatementByRegisID);
            pstat.setString(1, registrationID);
            rs = pstat.executeQuery();
            while (rs.next()) {
                vehOffList.add(mapResultSetToEO(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return vehOffList;
    }

    /**
     * Finds vehicle offences by registration ID and status.
     */
    public List<VehicleOffenceEO> findVehicleOffenceByStatus(String registrationID, Integer status) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<VehicleOffenceEO> vehOffList = new ArrayList<VehicleOffenceEO>();
        try {
            pstat = con.prepareStatement(SQLSelectStatementByStatus);
            pstat.setString(1, registrationID);
            pstat.setInt(2, status);
            rs = pstat.executeQuery();
            while (rs.next()) {
                vehOffList.add(mapResultSetToEO(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return vehOffList;
    }

    /**
     * Utility method to map ResultSet to VehicleOffenceEO object.
     */
     public VehicleOffenceEO mapResultSetToEO(ResultSet rs) throws SQLException {
         VehicleOffenceEO vehOffEO = new VehicleOffenceEO();

         vehOffEO.setVehicleOffenceID(rs.getInt("VEHICLE_OFFENCE_ID"));

         OffenceTypesEO offenceID = new OffenceTypesEO();
         offenceID.setOffenceID(rs.getInt("OFFENCE_ID"));
         vehOffEO.setOffenceID(offenceID);

         RegistrationEO registrationID = new RegistrationEO();
         registrationID.setRegistrationID(rs.getString("REGISTRATION_ID"));
         vehOffEO.setRegistrationID(registrationID);

         vehOffEO.setLocation(rs.getString("LOCATION"));
         vehOffEO.setDate(rs.getDate("OFFENCE_DATE"));

         // Inline conversion from String to Time
         String timeStr = rs.getString("TIME");
         java.sql.Time sqlTime = null;
         if (timeStr != null && !timeStr.trim().isEmpty()) {
             try {
                 java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm a");
                 java.util.Date parsed = sdf.parse(timeStr);
                 sqlTime = new java.sql.Time(parsed.getTime());
             } catch (Exception e) {
                 System.err.println("Invalid time format in DB: '" + timeStr + "', setting as null.");
                 sqlTime = null;
             }
         }
         vehOffEO.setTime(sqlTime);

         vehOffEO.setStatus(rs.getInt("STATUS"));
         vehOffEO.setReporter(rs.getString("REPORTER"));

         // Fetch BLOB as byte[] for proof1 and proof2
         vehOffEO.setProof1(rs.getBytes("PROOF1")); // BLOB -> byte[]
         vehOffEO.setProof2(rs.getBytes("PROOF2")); // BLOB -> byte[]

         return vehOffEO;
     }
}













//package training.iqgateway.dao.impl;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import java.sql.Statement;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import training.iqgateway.dao.VehicleOffenceDAO;
//import training.iqgateway.entities.OffenceTypesEO;
//import training.iqgateway.entities.RegistrationEO;
//import training.iqgateway.entities.VehicleOffenceEO;
//import training.iqgateway.utils.DBUtils;
//
//public class VehicleOffenceDAOImpl implements VehicleOffenceDAO {
//
//    private String SQLInsertStatement =
//        "INSERT INTO VEHICLE_OFFENCE VALUES (?,?,?,?,?,?,?)";
//
//    private String SQLUpdateStatement =
//        "UPDATE VEHICLE_OFFENCE SET OFFENCE_ID = ?, REGISTRATION_ID = ?, LOCATION = ?, OFFENCE_DATE = ?, TIME = ?, STATUS = ?, REPORTER = ?,PROOF1 = ?, PROOF2 = ? WHERE VEHICLE_OFFENCE_ID = ? ";
//
//    private String SQLClearStatement =
//        "UPDATE VEHICLE_OFFENCE SET STATUS = 1, PROOF1 = NULL, PROOF2 = NULL WHERE VEHICLE_OFFENCE_ID = ? AND REGISTRATION_ID = ?";
//
//    private String SQLDeleteStatement =
//        "DELETE FROM VEHICLE_OFFENCE WHERE VEHICLE_OFFENCE_ID = ?";
//
//    private String SQLSelectStatementByID =
//        "SELECT * FROM VEHICLE_OFFENCE WHERE VEHICLE_OFFENCE_ID = ?";
//
//    private String SQLSelectAllStatement = 
//        "SELECT * FROM VEHICLE_OFFENCE";
//
//    private String SQLSelectStatementByRegisID =
//        "SELECT * FROM VEHICLE_OFFENCE WHERE REGISTRATION_ID = ?";
//
//    private String SQLSelectStatementByStatus =
//        "SELECT * FROM VEHICLE_OFFENCE WHERE REGISTRATION_ID = ? AND STATUS = ?";
//
//    public int insertVehicleOffence(VehicleOffenceEO vehOffEO) {
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        int count = 0;
//
//        try {            
//            pstat = con.prepareStatement(SQLInsertStatement);
//            // byte[] bytes = vehOffEO.getProof().getBytes();  
//            pstat.setInt(1, vehOffEO.getVehicleOffenceID());            
//            pstat.setInt(2, vehOffEO.getOffenceID().getOffenceID());
//            pstat.setString(3, vehOffEO.getRegistrationID().getRegistrationID());
//            pstat.setString(4, vehOffEO.getLocation());
//            pstat.setDate(5, vehOffEO.getDate());
//            pstat.setInt(6, 0);
//            // Commented By Santhosh 
//            pstat.setBytes(7, vehOffEO.getProof2());
//
//            count = pstat.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return count;
//    }
//
//
//    public int updateVehicleOffence(VehicleOffenceEO vehOffEO) {
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        int count = 0;
//
//        try {
//            pstat = con.prepareStatement(SQLUpdateStatement);
//            pstat.setInt(1, vehOffEO.getOffenceID().getOffenceID());
//            pstat.setString(2, vehOffEO.getRegistrationID().getRegistrationID());
//            pstat.setString(3, vehOffEO.getLocation());
//            pstat.setDate(4, vehOffEO.getDate());
//            pstat.setInt(5, vehOffEO.getStatus());
//            pstat.setBytes(6, vehOffEO.getProof2());
//            pstat.setInt(7, vehOffEO.getVehicleOffenceID());
//
//            count = pstat.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return count;
//    }
//
//    public int clearVehicleOffence(VehicleOffenceEO vehOffEO) {
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        int count = 0;
//
//        try {
//            pstat = con.prepareStatement(SQLClearStatement);
//            pstat.setInt(1, vehOffEO.getVehicleOffenceID());
//            pstat.setString(2, vehOffEO.getRegistrationID().getRegistrationID());
//
//            count = pstat.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return count;
//    }
//
//    public int deleteVehicleOffence(Integer vehicleOffenceID) {
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        int count = 0;
//
//        try {
//            pstat = con.prepareStatement(SQLDeleteStatement);
//            pstat.setInt(1, vehicleOffenceID);
//            count = pstat.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return count;
//    }
//
//    public VehicleOffenceEO findVehicleOffenceByID(Integer vehicleOffenceID) {
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//
//        VehicleOffenceEO vehOffEO = null;
//
//        try {
//            pstat = con.prepareStatement(SQLSelectStatementByID);
//            pstat.setInt(1, vehicleOffenceID);
//            rs = pstat.executeQuery();
//
//            while (rs.next()) {
//                vehOffEO = new VehicleOffenceEO();
//                vehOffEO.setVehicleOffenceID(rs.getInt(1));
//                
//                OffenceTypesEO offenceID = new OffenceTypesEO();
//                offenceID.setOffenceID(rs.getInt(2));
//                RegistrationEO registrationID = new RegistrationEO();
//                registrationID.setRegistrationID(rs.getString(3));
//                
//                vehOffEO.setOffenceID(offenceID);
//                vehOffEO.setRegistrationID(registrationID);
//                vehOffEO.setLocation(rs.getString(4));
//                vehOffEO.setDate(rs.getDate(5));
//                vehOffEO.setStatus(rs.getInt(6));
//                vehOffEO.setProof2(rs.getBytes(7));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return vehOffEO;
//    }
//
//    public List<VehicleOffenceEO> findAllVehicleOffence() {
//        Connection con = DBUtils.obtainConnection();
//        Statement stat = null;
//        ResultSet rs = null;
//
//        List<VehicleOffenceEO> vehOffList = new ArrayList<VehicleOffenceEO>();
//        VehicleOffenceEO vehOffEO = null;
//
//        try {
//            stat = con.createStatement();
//            rs = stat.executeQuery(SQLSelectAllStatement);
//            while (rs.next()) {
//                vehOffEO = new VehicleOffenceEO();
//                vehOffEO.setVehicleOffenceID(rs.getInt(1));
//                
//                OffenceTypesEO offenceID = new OffenceTypesEO();
//                offenceID.setOffenceID(rs.getInt(2));
//                RegistrationEO registrationID = new RegistrationEO();
//                registrationID.setRegistrationID(rs.getString(3));
//                
//                vehOffEO.setOffenceID(offenceID);
//                vehOffEO.setRegistrationID(registrationID);
//                vehOffEO.setLocation(rs.getString(4));
//                vehOffEO.setDate(rs.getDate(5));
//                vehOffEO.setStatus(rs.getInt(6));
//                vehOffEO.setProof2(rs.getBytes(7));
//                vehOffList.add(vehOffEO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(stat);
//        DBUtils.close(con);
//        return vehOffList;
//    }
//
//    public List<VehicleOffenceEO> findVehicleOffenceByRegisID(String registrationID) {
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//
//        List<VehicleOffenceEO> vehOffList = new ArrayList<VehicleOffenceEO>();
//        VehicleOffenceEO vehOffEO = null;
//
//        try {
//            pstat = con.prepareStatement(SQLSelectStatementByRegisID);
//            pstat.setString(1, registrationID);
//            rs = pstat.executeQuery();
//            while (rs.next()) {
//                vehOffEO = new VehicleOffenceEO();
//                vehOffEO.setVehicleOffenceID(rs.getInt(1));
//                
//                OffenceTypesEO offenceID = new OffenceTypesEO();
//                offenceID.setOffenceID(rs.getInt(2));
//                RegistrationEO registrationId = new RegistrationEO();
//                registrationId.setRegistrationID(rs.getString(3));
//                
//                vehOffEO.setOffenceID(offenceID);
//                vehOffEO.setRegistrationID(registrationId);
//                vehOffEO.setLocation(rs.getString(4));
//                vehOffEO.setDate(rs.getDate(5));
//                vehOffEO.setStatus(rs.getInt(6));
//                vehOffEO.setProof2(rs.getBytes(7));
//                vehOffList.add(vehOffEO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return vehOffList;
//
//    }
//
//    public List<VehicleOffenceEO> findVehicleOffenceByStatus(String registrationID,
//                                                             Integer status) {
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//
//        List<VehicleOffenceEO> vehOffList = new ArrayList<VehicleOffenceEO>();
//        VehicleOffenceEO vehOffEO = null;
//
//        try {
//            pstat = con.prepareStatement(SQLSelectStatementByStatus);
//            pstat.setString(1, registrationID);
//            pstat.setInt(2, status);
//            rs = pstat.executeQuery();
//            while (rs.next()) {
//                vehOffEO = new VehicleOffenceEO();
//                vehOffEO.setVehicleOffenceID(rs.getInt(1));
//                
//                OffenceTypesEO offenceID = new OffenceTypesEO();
//                offenceID.setOffenceID(rs.getInt(2));
//                RegistrationEO registrationId = new RegistrationEO();
//                registrationId.setRegistrationID(rs.getString(3));
//                
//                vehOffEO.setOffenceID(offenceID);
//                vehOffEO.setRegistrationID(registrationId);
//                vehOffEO.setLocation(rs.getString(4));
//                vehOffEO.setDate(rs.getDate(5));
//                vehOffEO.setStatus(rs.getInt(6));
//                vehOffEO.setProof2(rs.getBytes(7));
//                vehOffList.add(vehOffEO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return vehOffList;
//    }
//}

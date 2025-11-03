package training.iqgateway.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import training.iqgateway.dao.RegistrationDAO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.utils.DBUtils;

/**
 * DAO implementation for registration operations.
 */
public class RegistrationDAOImpl implements RegistrationDAO {

    // All columns in table order
    private String SQLInsertStatement =
        "INSERT INTO REGISTRATION VALUES (?, ?, ?, ?, ?, ?)";

    private String SQLUpdateStatement =
        "UPDATE REGISTRATION SET LOCATION = ?, REGISTRATION_DATE = ?, REGISTRAR = ?, VEHICLE_ID = ?, OWNER_AADHAR = ? WHERE REGISTRATION_ID = ?";

    private String SQLDeleteStatement =
        "DELETE FROM REGISTRATION WHERE REGISTRATION_ID = ?";

    private String SQLSelectByIDStatement =
        "SELECT * FROM REGISTRATION WHERE REGISTRATION_ID = ?";
    
    private String SQLSelectByVehIDStatement =
        "SELECT * FROM REGISTRATION WHERE VEHICLE_ID = ? ";

    private String SQLSelectByAadharStatement =
        "SELECT * FROM REGISTRATION WHERE OWNER_AADHAR = ?";

    private String SQLSelectAllStatement =
        "SELECT * FROM REGISTRATION";

    /**
     * Inserts a new registration record into the database.
     */
    public int insertRegistration(RegistrationEO regisEO) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLInsertStatement);
            pstat.setString(1, regisEO.getRegistrationID());
            pstat.setString(2, regisEO.getLocation());
            pstat.setDate(3, regisEO.getRegistrationDate());
            pstat.setString(4, regisEO.getRegistrar());
            pstat.setInt(5, regisEO.getVehicleID().getVehicleID());
            pstat.setString(6, regisEO.getOwnerAadhar().getOwnerAadhar());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Updates an existing registration record.
     */
    public int updateRegistration(RegistrationEO regisEO) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLUpdateStatement);
            pstat.setString(1, regisEO.getLocation());
            pstat.setDate(2, regisEO.getRegistrationDate());
            pstat.setString(3, regisEO.getRegistrar());
            pstat.setInt(4, regisEO.getVehicleID().getVehicleID());
            pstat.setString(5, regisEO.getOwnerAadhar().getOwnerAadhar());
            pstat.setString(6, regisEO.getRegistrationID());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Deletes a registration by registration ID.
     */
    public int deleteRegistration(String regisID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLDeleteStatement);
            pstat.setString(1, regisID);
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Finds a registration by registration ID.
     */
    public RegistrationEO findRegistrationByID(String regisID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;

        RegistrationEO regisEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectByIDStatement);
            pstat.setString(1, regisID);
            rs = pstat.executeQuery();

            if (rs.next()) {
                regisEO = new RegistrationEO();
                regisEO.setRegistrationID(rs.getString("REGISTRATION_ID"));
                regisEO.setLocation(rs.getString("LOCATION"));
                regisEO.setRegistrationDate(rs.getDate("REGISTRATION_DATE"));
                regisEO.setRegistrar(rs.getString("REGISTRAR"));

                VehicleEO vehicle = new VehicleEO();
                vehicle.setVehicleID(rs.getInt("VEHICLE_ID"));
                regisEO.setVehicleID(vehicle);

                OwnerEO owner = new OwnerEO();
                owner.setOwnerAadhar(rs.getString("OWNER_AADHAR"));
                regisEO.setOwnerAadhar(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return regisEO;
    }
    
    /**
     * Finds registrations for a given vehicleID.
     */
    public RegistrationEO findRegistrationByVehID(Integer vehID){
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;

        RegistrationEO regisEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectByVehIDStatement);
            pstat.setInt(1, vehID);
            rs = pstat.executeQuery();

            if (rs.next()) {
                regisEO = new RegistrationEO();
                regisEO.setRegistrationID(rs.getString("REGISTRATION_ID"));
                regisEO.setLocation(rs.getString("LOCATION"));
                regisEO.setRegistrationDate(rs.getDate("REGISTRATION_DATE"));
                regisEO.setRegistrar(rs.getString("REGISTRAR"));

                VehicleEO vehicle = new VehicleEO();
                vehicle.setVehicleID(rs.getInt("VEHICLE_ID"));
                regisEO.setVehicleID(vehicle);

                OwnerEO owner = new OwnerEO();
                owner.setOwnerAadhar(rs.getString("OWNER_AADHAR"));
                regisEO.setOwnerAadhar(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return regisEO;
    }


    /**
     * Finds all registrations for a given owner Aadhar.
     */
    public List<RegistrationEO> findRegistrationByAadhar(String aadhar) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;

        List<RegistrationEO> regisEOList = new ArrayList<RegistrationEO>();
        try {
            pstat = con.prepareStatement(SQLSelectByAadharStatement);
            pstat.setString(1, aadhar);
            rs = pstat.executeQuery();

            while (rs.next()) {
                RegistrationEO regisEO = new RegistrationEO();
                regisEO.setRegistrationID(rs.getString("REGISTRATION_ID"));
                regisEO.setLocation(rs.getString("LOCATION"));
                regisEO.setRegistrationDate(rs.getDate("REGISTRATION_DATE"));
                regisEO.setRegistrar(rs.getString("REGISTRAR"));

                VehicleEO vehicle = new VehicleEO();
                vehicle.setVehicleID(rs.getInt("VEHICLE_ID"));
                regisEO.setVehicleID(vehicle);

                OwnerEO owner = new OwnerEO();
                owner.setOwnerAadhar(rs.getString("OWNER_AADHAR"));
                regisEO.setOwnerAadhar(owner);

                regisEOList.add(regisEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return regisEOList;
    }

    /**
     * Returns all registrations in the database.
     */
    public List<RegistrationEO> findAllRegistrations() {
        Connection con = DBUtils.obtainConnection();
        Statement stat = null;
        ResultSet rs = null;

        List<RegistrationEO> regisEOList = new ArrayList<RegistrationEO>();
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(SQLSelectAllStatement);
            while (rs.next()) {
                RegistrationEO regisEO = new RegistrationEO();
                regisEO.setRegistrationID(rs.getString("REGISTRATION_ID"));
                regisEO.setLocation(rs.getString("LOCATION"));
                regisEO.setRegistrationDate(rs.getDate("REGISTRATION_DATE"));
                regisEO.setRegistrar(rs.getString("REGISTRAR"));

                VehicleEO vehicle = new VehicleEO();
                vehicle.setVehicleID(rs.getInt("VEHICLE_ID"));
                regisEO.setVehicleID(vehicle);

                OwnerEO owner = new OwnerEO();
                owner.setOwnerAadhar(rs.getString("OWNER_AADHAR"));
                regisEO.setOwnerAadhar(owner);

                regisEOList.add(regisEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(stat);
        DBUtils.close(con);
        return regisEOList;
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
//import training.iqgateway.dao.RegistrationDAO;
//import training.iqgateway.entities.OwnerEO;
//import training.iqgateway.entities.RegistrationEO;
//import training.iqgateway.entities.VehicleEO;
//import training.iqgateway.utils.DBUtils;
//
//public class RegistrationDAOImpl implements RegistrationDAO{
//    
//    private String SQLInsertStatement = 
//        "INSERT INTO REGISTRATION VALUES (?,?,?,?,?)";
//    
//    private String SQLUpdateStatement = 
//        "UPDATE REGISTRATION SET REGISTRATION_ID = ?, LOCATION = ?, REGISTRATION_DATE = ?, OWNER_AADHAR = ? WHERE VEHICLE_ID = ? ";
//    
//    private String SQLDeleteStatement = 
//        "DELETE FROM REGISTRATION WHERE REGISTRATION_ID = ?";
//    
//    private String SQLSelectByIDStatement = 
//        "SELECT * FROM REGISTRATION WHERE REGISTRATION_ID = ?";
//    
//    private String SQLSelectByAadharStatement = 
//        "SELECT * FROM REGISTRATION WHERE OWNER_AADHAR = ?";
//    
//    private String SQLSelectAllStatement = 
//        "SELECT * FROM REGISTRATION";
//    
//    private String SQLSelectVehicleByIDStatement = 
//        "SELECT * FROM VEHICLE JOIN REGISTRATION USING (VEHICLE_ID)";
//    
//    private String SQLSelectOwnerByIDStatement = 
//        "SELECT * FROM OWNER JOIN REGISTRATION USING (OWNER_AADHAR)";
//
//    public int insertRegistration (RegistrationEO regisEO){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        int count = 0;
//        try {
//            pstat = con.prepareStatement(SQLInsertStatement);
//            pstat.setString(1, regisEO.getRegistrationID());
//            pstat.setString(2, regisEO.getLocation());
//            pstat.setDate(3, regisEO.getRegistrationDate());       
//            pstat.setInt(4, regisEO.getVehicleID().getVehicleID());
//            pstat.setString(5, regisEO.getOwnerAadhar().getOwnerAadhar());
//            count = pstat.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return count;
//    }
//    
//    public int updateRegistration (RegistrationEO regisEO){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        int count = 0;
//        try {
//            pstat = con.prepareStatement(SQLUpdateStatement);
//            pstat.setString(1, regisEO.getRegistrationID());
//            pstat.setString(2, regisEO.getLocation());
//            pstat.setDate(3, regisEO.getRegistrationDate());
//            pstat.setString(4, regisEO.getOwnerAadhar().getOwnerAadhar());
//            pstat.setInt(5, regisEO.getVehicleID().getVehicleID());
//            count = pstat.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return count;
//    }
//    
//    public int deleteRegistration (String regisID){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        int count = 0;
//        try {
//            pstat = con.prepareStatement(SQLDeleteStatement);
//            pstat.setString(1, regisID);
//            count = pstat.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return count;
//    }
//
//    public RegistrationEO findRegistrationByID(String regisID){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//
//        RegistrationEO regisEO = null;
//        try {
//            pstat = con.prepareStatement(SQLSelectByIDStatement);
//            pstat.setString(1, regisID);
//            rs = pstat.executeQuery();
//            
//            while (rs.next()) {
//                regisEO = new RegistrationEO();
//                regisEO.setRegistrationID(rs.getString(1));
//                regisEO.setLocation(rs.getString(2));
//                regisEO.setRegistrationDate(rs.getDate(3));
//                
//                VehicleEO vehicleID = new VehicleEO();
//                vehicleID.setVehicleID(rs.getInt(4));
//                regisEO.setVehicleID(vehicleID);
//                
//                OwnerEO ownerAadhar = new OwnerEO();
//                ownerAadhar.setOwnerAadhar(rs.getString(5));
//                regisEO.setOwnerAadhar(ownerAadhar);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return regisEO;
//    }
//    
//    public List<RegistrationEO> findRegistrationByAadhar(String aadhar) {
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//
//        List<RegistrationEO> regisEOList = new ArrayList<RegistrationEO>();
//        RegistrationEO regisEO = null;
//        
//        try {
//            pstat = con.prepareStatement(SQLSelectByAadharStatement);
//            pstat.setString(1, aadhar);
//            rs = pstat.executeQuery();
//            
//            while (rs.next()) {
//                regisEO = new RegistrationEO();
//                regisEO.setRegistrationID(rs.getString(1));
//                regisEO.setLocation(rs.getString(2));
//                regisEO.setRegistrationDate(rs.getDate(3));
//                
//                VehicleEO vehicleID = new VehicleEO();
//                vehicleID.setVehicleID(rs.getInt(4));
//                regisEO.setVehicleID(vehicleID);
//                
//                OwnerEO ownerAadhar = new OwnerEO();
//                ownerAadhar.setOwnerAadhar(rs.getString(5));
//                regisEO.setOwnerAadhar(ownerAadhar);
//                
//                regisEOList.add(regisEO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return regisEOList;
//    }
//    
//    public List<RegistrationEO> findAllRegistrations (){
//        Connection con = DBUtils.obtainConnection();
//        Statement stat = null;
//        ResultSet rs = null;
//
//        List<RegistrationEO> regisEOList = new ArrayList<RegistrationEO>();
//        RegistrationEO regisEO = null;
//        
//        try {
//            stat = con.createStatement();
//            rs = stat.executeQuery(SQLSelectAllStatement);
//            while (rs.next()) {
//                regisEO = new RegistrationEO();
//                regisEO.setRegistrationID(rs.getString(1));
//                regisEO.setLocation(rs.getString(2));        
//                regisEO.setRegistrationDate(rs.getDate(3)); 
//                
//                VehicleEO vehicleID = new VehicleEO();
//                vehicleID.setVehicleID(rs.getInt(4));
//                regisEO.setVehicleID(vehicleID);
//                
//                OwnerEO ownerAadhar = new OwnerEO();
//                ownerAadhar.setOwnerAadhar(rs.getString(5));
//                regisEO.setOwnerAadhar(ownerAadhar); 
//                
//                regisEOList.add(regisEO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(stat);
//        DBUtils.close(con);
//        return regisEOList;
//    }
//    
//    public VehicleEO findVehicleByRegisID (String regisID){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//
//        List<VehicleEO> vehicleList = new ArrayList<VehicleEO>();
//        VehicleEO vehicleEO = null;
//        
//        try {
//            pstat = con.prepareStatement(SQLSelectVehicleByIDStatement);
//            pstat.setString(1, regisID);
//
//            rs = pstat.executeQuery();
//            while (rs.next()) {
//                vehicleEO = new VehicleEO();
//                vehicleEO.setVehicleID(rs.getInt(1));
//                vehicleEO.setVehicleType(rs.getString(2));
//                vehicleEO.setVehicleBrand(rs.getString(3));
//                vehicleEO.setVehicleModel(rs.getString(4));
//                vehicleEO.setManufactureDate(rs.getDate(5));
//                vehicleList.add(vehicleEO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return vehicleEO;
//    }
//    
//    public OwnerEO findOwnerByRegisID (String regisID){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//
//        List<OwnerEO> ownerList = new ArrayList<OwnerEO>();
//        OwnerEO ownerEO = null;
//        
//        try {
//            pstat = con.prepareStatement(SQLSelectOwnerByIDStatement);
//            pstat.setString(1, regisID);
//
//            rs = pstat.executeQuery();
//            while (rs.next()) {
//                ownerEO = new OwnerEO();
//                ownerEO.setOwnerID(rs.getInt(1));
//                ownerEO.setOwnerName(rs.getString(2));
//                ownerEO.setOwnerAadhar(rs.getString(3));
//                ownerEO.setPhone(rs.getLong(4));
//                ownerEO.setAddress(rs.getString(5));
//                ownerList.add(ownerEO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return ownerEO;
//    }
//}

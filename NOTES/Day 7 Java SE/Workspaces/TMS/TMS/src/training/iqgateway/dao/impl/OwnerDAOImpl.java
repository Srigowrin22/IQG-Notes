package training.iqgateway.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import training.iqgateway.dao.OwnerDAO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.utils.DBUtils;

public class OwnerDAOImpl implements OwnerDAO {

    private String SQLInsertStatement =
        "INSERT INTO OWNER (owner_id, owner_name, owner_aadhar, phone, address, password) VALUES (seq_owner.nextval,?,?,?,?,?)";

    private String SQLUpdateStatement =
        "UPDATE OWNER SET OWNER_NAME = ?, GENDER = ?, PAN_CARD = ?, PHONE = ?, ADDRESS = ?, PASSWORD = ? WHERE OWNER_AADHAR = ?";

    private String SQLDeleteStatement =
        "DELETE FROM OWNER WHERE OWNER_AADHAR = ? AND PASSWORD = ?";

    private String SQLSelectStatementByAadhar =
        "SELECT * FROM OWNER WHERE OWNER_AADHAR = ?";

    private String SQLSelectAllStatement = "SELECT * FROM OWNER";

    private String SQLSelectStatementByName =
        "SELECT * FROM OWNER WHERE OWNER_NAME LIKE ?";

    /**
     * Inserts a new Owner record into the database.
     */
    public int insertOwner(OwnerEO ownerEO) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLInsertStatement);
            pstat.setString(1, ownerEO.getOwnerName());
            pstat.setString(2, ownerEO.getOwnerAadhar());
            if (ownerEO.getPhone() != null) {
                pstat.setLong(3, ownerEO.getPhone());
            } else {
                pstat.setNull(3, java.sql.Types.NUMERIC);
            }
            pstat.setString(4, ownerEO.getAddress());
            pstat.setString(5, ownerEO.getPassword());

            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Updates an existing Owner record in the database.
     */
    public int updateOwner(OwnerEO ownerEO) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByAadhar);
            pstat.setString(1, ownerEO.getOwnerAadhar());

            rs = pstat.executeQuery();
            String pan = null;
            if (rs.next()) {
                pan = rs.getString("PAN_CARD");
            }
            
            pstat = con.prepareStatement(SQLUpdateStatement);
            pstat.setString(1, ownerEO.getOwnerName());
            pstat.setString(2, ownerEO.getGender());
            if (pan == null || pan.length()<10 || pan == "") {
                pstat.setString(3, ownerEO.getPancard());
            }else{
                pstat.setString(3, pan);
            }
            pstat.setLong(4, ownerEO.getPhone());
            pstat.setString(5, ownerEO.getAddress());
            pstat.setString(6, ownerEO.getPassword());
            pstat.setString(7, ownerEO.getOwnerAadhar());

            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Deletes an Owner record by Aadhar and password.
     */
    public int deleteOwner(String ownerAadhar, String password) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLDeleteStatement);
            pstat.setString(1, ownerAadhar);
            pstat.setString(2, password);
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Finds an Owner by Aadhar number.
     */
    public OwnerEO findOwnerByAadhar(String ownerAadhar) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;

        OwnerEO ownerEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByAadhar);
            pstat.setString(1, ownerAadhar);

            rs = pstat.executeQuery();
            while (rs.next()) {
                ownerEO = new OwnerEO();
                ownerEO.setOwnerID(rs.getInt("OWNER_ID"));
                ownerEO.setOwnerName(rs.getString("OWNER_NAME"));
                ownerEO.setGender(rs.getString("GENDER"));
                ownerEO.setOwnerAadhar(rs.getString("OWNER_AADHAR"));
                ownerEO.setPancard(rs.getString("PAN_CARD"));
                long phoneVal = rs.getLong("PHONE");
                if (rs.wasNull())
                    ownerEO.setPhone(null);
                else
                    ownerEO.setPhone(phoneVal);
                ownerEO.setAddress(rs.getString("ADDRESS"));
                ownerEO.setPassword(rs.getString("PASSWORD"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return ownerEO;
    }

    /**
     * Returns a list of all Owners in the database.
     */
    public List<OwnerEO> findAllOwners() {
        Connection con = DBUtils.obtainConnection();
        Statement stat = null;
        ResultSet rs = null;

        List<OwnerEO> ownerList = new ArrayList<OwnerEO>();
        OwnerEO ownerEO = null;

        try {
            stat = con.createStatement();
            rs = stat.executeQuery(SQLSelectAllStatement);
            while (rs.next()) {
                ownerEO = new OwnerEO();
                ownerEO.setOwnerID(rs.getInt("OWNER_ID"));
                ownerEO.setOwnerName(rs.getString("OWNER_NAME"));
                ownerEO.setGender(rs.getString("GENDER"));
                ownerEO.setOwnerAadhar(rs.getString("OWNER_AADHAR"));
                ownerEO.setPancard(rs.getString("PAN_CARD"));
                long phoneVal = rs.getLong("PHONE");
                if (rs.wasNull())
                    ownerEO.setPhone(null);
                else
                    ownerEO.setPhone(phoneVal);
                ownerEO.setAddress(rs.getString("ADDRESS"));
                ownerEO.setPassword(rs.getString("PASSWORD"));
                ownerList.add(ownerEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(stat);
        DBUtils.close(con);
        return ownerList;
    }

    /**
     * Finds Owners by name (supports LIKE queries).
     */
    public List<OwnerEO> findOwnerByName(String ownerName) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;

        List<OwnerEO> ownerList = new ArrayList<OwnerEO>();
        OwnerEO ownerEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByName);
            pstat.setString(1, ownerName);
            rs = pstat.executeQuery();

            while (rs.next()) {
                ownerEO = new OwnerEO();
                ownerEO.setOwnerID(rs.getInt("OWNER_ID"));
                ownerEO.setOwnerName(rs.getString("OWNER_NAME"));
                ownerEO.setGender(rs.getString("GENDER"));
                ownerEO.setOwnerAadhar(rs.getString("OWNER_AADHAR"));
                ownerEO.setPancard(rs.getString("PAN_CARD"));
                long phoneVal = rs.getLong("PHONE");
                if (rs.wasNull())
                    ownerEO.setPhone(null);
                else
                    ownerEO.setPhone(phoneVal);
                ownerEO.setAddress(rs.getString("ADDRESS"));
                ownerEO.setPassword(rs.getString("PASSWORD"));
                ownerList.add(ownerEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return ownerList;
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
//import training.iqgateway.dao.OwnerDAO;
//import training.iqgateway.entities.OwnerEO;
//import training.iqgateway.entities.RegistrationEO;
//import training.iqgateway.utils.DBUtils;
//
//public class OwnerDAOImpl implements OwnerDAO{
//    
//    private String SQLInsertStatement = 
//        "INSERT INTO OWNER VALUES (seq_owner.nextval,?,?,?,?,?)";
//    
//    private String SQLUpdateStatement = 
//        "UPDATE OWNER SET OWNER_NAME = ?, PHONE = ?, GENDER = ? PHONE = ?, ADDRESS = ?, PASSWORD = ? WHERE OWNER_AADHAR = ? ";
//    
//    private String SQLDeleteStatement =
//        "DELETE FROM OWNER WHERE OWNER_AADHAR = ? AND PASSWORD = ?";
//    
//    private String SQLSelectStatementByAadhar = 
//        "SELECT * FROM  OWNER WHERE OWNER_AADHAR = ?";
//    
//    private String SQLSelectAllStatement = 
//        "SELECT * FROM OWNER";
//    
//    private String SQLSelectStatementByName = 
//        "SELECT * FROM OWNER WHERE OWNER_NAME LIKE ?";
//    
//    public int insertOwner (OwnerEO ownerEO){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        int count = 0;
//        try {
//            pstat = con.prepareStatement(SQLInsertStatement);
//            pstat.setString(1, ownerEO.getOwnerName());
//            pstat.setString(2, ownerEO.getOwnerAadhar());
//            pstat.setString(3, ownerEO.getPassword());            
//            pstat.setInt(4, ownerEO.getPhone());
//            pstat.setString(5, ownerEO.getAddress());
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
//    public int updateOwner (OwnerEO ownerEO){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        int count = 0;
//        try {
//            pstat = con.prepareStatement(SQLUpdateStatement);
//            pstat.setString(1, ownerEO.getOwnerName());
//            pstat.setInt(2, ownerEO.getPhone());
//            pstat.setString(3, ownerEO.getAddress());
//            pstat.setString(4, ownerEO.getPassword());
//            pstat.setString(5, ownerEO.getOwnerAadhar());
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
//    public int deleteOwner (String ownerAadhar, String password){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        int count = 0;
//        try {
//            pstat = con.prepareStatement(SQLDeleteStatement);
//            pstat.setString(1, ownerAadhar);
//            pstat.setString(2, password);
//            count = pstat.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return count;
//    }
//    
//    public OwnerEO findOwnerByAadhar(String ownerAadhar){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//
//        OwnerEO ownerEO = null;
//        try {
//            pstat = con.prepareStatement(SQLSelectStatementByAadhar);
//            pstat.setString(1, ownerAadhar);
//
//            rs = pstat.executeQuery();
//            while (rs.next()) {
//                ownerEO = new OwnerEO();
//                ownerEO.setOwnerID(rs.getInt(1));
//                ownerEO.setOwnerName(rs.getString(2));
//                ownerEO.setOwnerAadhar(rs.getString(3));
//                ownerEO.setPassword(rs.getString(4));
//                ownerEO.setPhone(rs.getInt(5));
//                ownerEO.setAddress(rs.getString(6));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return ownerEO;
//    }
//    
//    public List<OwnerEO> findAllOwners (){
//        Connection con = DBUtils.obtainConnection();
//        Statement stat = null;
//        ResultSet rs = null;
//
//        List<OwnerEO> ownerList = new ArrayList();
//        OwnerEO ownerEO = null;
//        
//        try {
//            stat = con.createStatement();
//            rs = stat.executeQuery(SQLSelectAllStatement);
//            while (rs.next()) {
//                ownerEO = new OwnerEO();
//                ownerEO.setOwnerID(rs.getInt(1));
//                ownerEO.setOwnerName(rs.getString(2));
//                ownerEO.setOwnerAadhar(rs.getString(3));
//                ownerEO.setPhone(rs.getInt(5));
//                ownerEO.setAddress(rs.getString(6));
//                ownerList.add(ownerEO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(stat);
//        DBUtils.close(con);
//        return ownerList;
//    }
//    
//    public List<OwnerEO> findOwnerByName (String ownerName){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//
//        List<OwnerEO> ownerList = new ArrayList();
//        OwnerEO ownerEO = null;
//        try {
//            pstat = con.prepareStatement(SQLSelectStatementByName);
//            pstat.setString(1, ownerName);
//            rs = pstat.executeQuery();
//            
//            while (rs.next()) {
//                ownerEO = new OwnerEO();
//                ownerEO.setOwnerID(rs.getInt(1));
//                ownerEO.setOwnerName(rs.getString(2));
//                ownerEO.setOwnerAadhar(rs.getString(3));
//                ownerEO.setPhone(rs.getInt(5));
//                ownerEO.setAddress(rs.getString(6));
//                ownerList.add(ownerEO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return ownerList;
//    }
//}

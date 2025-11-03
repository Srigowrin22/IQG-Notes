package training.iqgateway.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import training.iqgateway.dao.AdminDAO;
import training.iqgateway.entities.AdminEO;
import training.iqgateway.entities.RoleEO;
import training.iqgateway.utils.DBUtils;

/**
 * Implementation of AdminDAO for managing Admin entities in the database.
 */
public class AdminDAOImpl implements AdminDAO {

    private String SQLInsertStatement =
        "INSERT INTO ADMIN (ID, ROLE_ID, DESIGNATION_ID, NAME, GENDER, AADHAR, PHONE, PASSWORD, HIRE_DATE, SIGNUP) " +
        "VALUES (seq_admin.nextval, ?,?,?,?,?,?,?,?,?)";

    private String SQLUpdateStatement =
        "UPDATE ADMIN SET NAME = ?, GENDER = ?, PHONE = ?, PASSWORD = ? WHERE DESIGNATION_ID = ?";

    private String SQLAuthorizeStatement =
        "UPDATE ADMIN SET SIGNUP = 1 WHERE PASSWORD = ? AND DESIGNATION_ID = ?";

    private String SQLDeleteStatement =
        "DELETE FROM ADMIN WHERE DESIGNATION_ID = ?";

    private String SQLSelectStatementByDesigID =
        "SELECT * FROM ADMIN WHERE DESIGNATION_ID = ?";

    private String SQLSelectStatementByAadhar =
        "SELECT * FROM ADMIN WHERE AADHAR = ?";

    private String SQLSelectAllStatement =
        "SELECT * FROM ADMIN";

    private String SQLSelectStatementByRoleID =
        "SELECT * FROM ADMIN WHERE ROLE_ID = ?";

    private String SQLSelectStatementByName =
        "SELECT * FROM ADMIN WHERE NAME LIKE ?";

     /**
      * Inserts a new Admin record into the database.
      * Automatically generates the next designation ID (e.g., CLK06) based on the role if not provided.
      * @param adminEO The Admin entity object to insert.
      * @return Number of records inserted (should be 1 if successful).
      */
     public int insertAdmin(AdminEO adminEO) {
         Connection con = DBUtils.obtainConnection();
         PreparedStatement pstat = null;
         int count = 0;

         try {
             // Determine the prefix based on role ID
             String prefix = null;
             if (adminEO.getRoleID().getRoleID() == 3) prefix = "CLK";
             else if (adminEO.getRoleID().getRoleID() == 2) prefix = "PLC";
             else if (adminEO.getRoleID().getRoleID() == 1) prefix = "RTO";

             // Generate designation ID if not provided
             String designationID = adminEO.getDesignationID();
             if (designationID == null || designationID.trim().isEmpty()) {
                 designationID = getNextDesignationID(con, prefix);
                 adminEO.setDesignationID(designationID);
             }

             pstat = con.prepareStatement(SQLInsertStatement);
             pstat.setInt(1, adminEO.getRoleID().getRoleID());
             pstat.setString(2, designationID);
             pstat.setString(3, adminEO.getName());
             pstat.setString(4, adminEO.getGender());
             pstat.setString(5, adminEO.getAadhar());
             pstat.setLong(6, adminEO.getPhone());
             pstat.setString(7, adminEO.getPassword());
             pstat.setDate(8, adminEO.getHireDate());
             pstat.setInt(9, adminEO.getSignup());

             count = pstat.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         DBUtils.close(pstat);
         DBUtils.close(con);
         return count;
     }

     /**
      * Generates the next designation ID for the given role prefix.
      * For example, if prefix is "CLK", and existing are CLK01, CLK02, CLK05, returns "CLK06".
      * @param con Active database connection
      * @param prefix The prefix for the designation ID (e.g., "CLK", "PLC", "RTO")
      * @return The next designation ID (e.g., "CLK06")
      * @throws SQLException If a database access error occurs
      */
     private String getNextDesignationID(Connection con, String prefix) throws SQLException {
         String sql = "SELECT MAX(TO_NUMBER(SUBSTR(DESIGNATION_ID, LENGTH(?) + 1))) AS MAX_NUM " +
                      "FROM ADMIN WHERE DESIGNATION_ID LIKE ?";
         PreparedStatement pst = con.prepareStatement(sql);
         pst.setString(1, prefix);
         pst.setString(2, prefix + "%");
         ResultSet rs = pst.executeQuery();
         int nextNum = 1;
         if (rs.next()) {
             int maxNum = rs.getInt("MAX_NUM");
             if (!rs.wasNull()) {
                 nextNum = maxNum + 1;
             }
         }
         rs.close();
         pst.close();
         // Always two digits, e.g., CLK01, CLK12
         return prefix + String.format("%02d", nextNum);
     }

    /**
     * Updates an existing Admin record in the database.
     * @param adminEO The Admin entity object with updated values.
     * @return Number of records updated.
     */
    public int updateAdmin(AdminEO adminEO) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        
        try {
            pstat = con.prepareStatement(SQLUpdateStatement);
            pstat.setString(1, adminEO.getName());
            pstat.setString(2, adminEO.getGender());
            pstat.setLong(3, adminEO.getPhone());
            pstat.setString(4, adminEO.getPassword());
            pstat.setString(5, adminEO.getDesignationID());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Authorizes an admin by updating the signup status.
     * @param designationID The designation ID of the admin.
     * @param password The password of the admin.
     * @return Number of records updated.
     */
    public int authorizeAdmin(String designationID, String password) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;

        try {
            pstat = con.prepareStatement(SQLAuthorizeStatement);
            pstat.setString(1, password);
            pstat.setString(2, designationID);

            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Deletes an admin record by designation ID.
     * @param designationID The designation ID of the admin.
     * @return Number of records deleted.
     */
    public int deleteAdmin(String designationID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLDeleteStatement);
            pstat.setString(1, designationID);
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Finds an admin by designation ID.
     * @param designationID The designation ID to search for.
     * @return The AdminEO object if found, otherwise null.
     */
    public AdminEO findAdminByDesigID(String designationID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;

        AdminEO adminEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByDesigID);
            pstat.setString(1, designationID);
            rs = pstat.executeQuery();

            while (rs.next()) {
                adminEO = new AdminEO();
                adminEO.setId(rs.getInt("ID"));

                RoleEO roleID = new RoleEO();
                roleID.setRoleID(rs.getInt("ROLE_ID"));
                adminEO.setRoleID(roleID);

                adminEO.setDesignationID(rs.getString("DESIGNATION_ID"));
                adminEO.setName(rs.getString("NAME"));
                adminEO.setGender(rs.getString("GENDER"));
                adminEO.setAadhar(rs.getString("AADHAR"));
                adminEO.setPhone(rs.getLong("PHONE"));
                adminEO.setPassword(rs.getString("PASSWORD"));
                adminEO.setHireDate(rs.getDate("HIRE_DATE"));
                adminEO.setSignup(rs.getInt("SIGNUP"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return adminEO;
    }

    /**
     * Finds an admin by Aadhar number.
     * @param aadhar The Aadhar number to search for.
     * @return The AdminEO object if found, otherwise null.
     */
    public AdminEO findAdminByAadhar(String aadhar) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        AdminEO adminEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByAadhar);
            pstat.setString(1, aadhar);
            rs = pstat.executeQuery();

            while (rs.next()) {
                adminEO = new AdminEO();
                adminEO.setId(rs.getInt("ID"));

                RoleEO roleID = new RoleEO();
                roleID.setRoleID(rs.getInt("ROLE_ID"));
                adminEO.setRoleID(roleID);

                adminEO.setDesignationID(rs.getString("DESIGNATION_ID"));
                adminEO.setName(rs.getString("NAME"));
                adminEO.setGender(rs.getString("GENDER"));
                adminEO.setAadhar(rs.getString("AADHAR"));
                adminEO.setPhone(rs.getLong("PHONE"));
                adminEO.setPassword(rs.getString("PASSWORD"));
                adminEO.setHireDate(rs.getDate("HIRE_DATE"));
                adminEO.setSignup(rs.getInt("SIGNUP"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return adminEO;
    }

    /**
     * Retrieves all admins from the database.
     * @return List of AdminEO objects.
     */
    public List<AdminEO> findAllAdmins() {
        Connection con = DBUtils.obtainConnection();
        Statement stat = null;
        ResultSet rs = null;

        List<AdminEO> adminList = new ArrayList<AdminEO>();
        AdminEO adminEO = null;

        try {
            stat = con.createStatement();
            rs = stat.executeQuery(SQLSelectAllStatement);
            while (rs.next()) {
                adminEO = new AdminEO();
                adminEO.setId(rs.getInt("ID"));

                RoleEO roleID = new RoleEO();
                roleID.setRoleID(rs.getInt("ROLE_ID"));
                adminEO.setRoleID(roleID);

                adminEO.setDesignationID(rs.getString("DESIGNATION_ID"));
                adminEO.setName(rs.getString("NAME"));
                adminEO.setGender(rs.getString("GENDER"));
                adminEO.setAadhar(rs.getString("AADHAR"));
                adminEO.setPhone(rs.getLong("PHONE"));
                adminEO.setPassword(rs.getString("PASSWORD"));
                adminEO.setHireDate(rs.getDate("HIRE_DATE"));
                adminEO.setSignup(rs.getInt("SIGNUP"));
                adminList.add(adminEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(stat);
        DBUtils.close(con);
        return adminList;
    }

    /**
     * Finds admins by role ID.
     * @param roleID The role ID to search for.
     * @return List of AdminEO objects with the specified role ID.
     */
    public List<AdminEO> findAdminByRoleID(Integer roleID) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;

        List<AdminEO> adminList = new ArrayList<AdminEO>();
        AdminEO adminEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByRoleID);
            pstat.setInt(1, roleID);
            rs = pstat.executeQuery();

            while (rs.next()) {
                adminEO = new AdminEO();
                adminEO.setId(rs.getInt("ID"));

                RoleEO role = new RoleEO();
                role.setRoleID(rs.getInt("ROLE_ID"));
                adminEO.setRoleID(role);

                adminEO.setDesignationID(rs.getString("DESIGNATION_ID"));
                adminEO.setName(rs.getString("NAME"));
                adminEO.setGender(rs.getString("GENDER"));
                adminEO.setAadhar(rs.getString("AADHAR"));
                adminEO.setPhone(rs.getLong("PHONE"));
                adminEO.setPassword(rs.getString("PASSWORD"));
                adminEO.setHireDate(rs.getDate("HIRE_DATE"));
                adminEO.setSignup(rs.getInt("SIGNUP"));
                adminList.add(adminEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return adminList;
    }

    /**
     * Finds admins by name (supports LIKE queries).
     * @param name The name or pattern to search for.
     * @return List of AdminEO objects matching the name.
     */
    public List<AdminEO> findAdminByName(String name) {
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<AdminEO> adminList = new ArrayList<AdminEO>();
        AdminEO adminEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByName);
            pstat.setString(1, name);
            rs = pstat.executeQuery();

            while (rs.next()) {
                adminEO = new AdminEO();
                adminEO.setId(rs.getInt("ID"));

                RoleEO roleID = new RoleEO();
                roleID.setRoleID(rs.getInt("ROLE_ID"));
                adminEO.setRoleID(roleID);

                adminEO.setDesignationID(rs.getString("DESIGNATION_ID"));
                adminEO.setName(rs.getString("NAME"));
                adminEO.setGender(rs.getString("GENDER"));
                adminEO.setAadhar(rs.getString("AADHAR"));
                adminEO.setPhone(rs.getLong("PHONE"));
                adminEO.setPassword(rs.getString("PASSWORD"));
                adminEO.setHireDate(rs.getDate("HIRE_DATE"));
                adminEO.setSignup(rs.getInt("SIGNUP"));
                adminList.add(adminEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return adminList;
    }
}
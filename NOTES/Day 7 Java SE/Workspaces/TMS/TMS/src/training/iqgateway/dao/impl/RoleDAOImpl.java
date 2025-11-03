package training.iqgateway.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import training.iqgateway.dao.RoleDAO;
import training.iqgateway.entities.RoleEO;
import training.iqgateway.utils.DBUtils;

/**
 * DAO implementation for Role operations.
 */
public class RoleDAOImpl implements RoleDAO {
    
    private String SQLCountStatement = 
        "SELECT COUNT(*) FROM ROLE";
    
    private String SQLInsertStatement = 
        "INSERT INTO ROLE VALUES (?, ?)";
    
    private String SQLUpdateStatement = 
        "UPDATE ROLE SET ROLE_NAME = ? WHERE ROLE_ID = ?";
    
    private String SQLDeleteStatement =
        "DELETE FROM ROLE WHERE ROLE_ID = ?";
    
    private String SQLSelectStatementByID = 
        "SELECT * FROM ROLE WHERE ROLE_ID = ?";

    private String SQLSelectAllStatement = 
        "SELECT * FROM ROLE";
    
    private String SQLSelectStatementByName = 
        "SELECT * FROM ROLE WHERE ROLE_NAME LIKE ?";

    /**
     * Returns the count of roles in the database.
     * @return number of roles
     */
    public int countRoles(){
        Connection con = DBUtils.obtainConnection();
        Statement stat = null;
        ResultSet rs = null;
        int count = 0;
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(SQLCountStatement);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(stat);
        DBUtils.close(con);
        return count;
    }
    
    /**
     * Inserts a new role into the database.
     * @param roleEO Role entity object
     * @return number of records inserted
     */
    public int insertRole (RoleEO roleEO){
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLInsertStatement);
            pstat.setInt(1, roleEO.getRoleID());
            pstat.setString(2, roleEO.getRoleName());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }
    
    /**
     * Updates an existing role in the database.
     * @param roleEO Role entity object
     * @return number of records updated
     */
    public int updateRole (RoleEO roleEO){
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLUpdateStatement);
            pstat.setString(1, roleEO.getRoleName());
            pstat.setInt(2, roleEO.getRoleID());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }
    
    /**
     * Deletes a role by its ID.
     * @param roleID Role ID
     * @return number of records deleted
     */
    public int deleteRole (Integer roleID){
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;
        try {
            pstat = con.prepareStatement(SQLDeleteStatement);
            pstat.setInt(1, roleID);
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(pstat);
        DBUtils.close(con);
        return count;
    }

    /**
     * Finds a role by its ID.
     * @param roleID Role ID
     * @return RoleEO object or null if not found
     */
    public RoleEO findRoleByRoleID (Integer roleID){
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;

        RoleEO roleEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByID);
            pstat.setInt(1, roleID);
            rs = pstat.executeQuery();
            
            while (rs.next()) {
                roleEO = new RoleEO();
                roleEO.setRoleID(rs.getInt(1));
                roleEO.setRoleName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return roleEO;
    }
    
    /**
     * Returns a list of all roles in the database.
     * @return list of RoleEO objects
     */
    public List<RoleEO> findAllRoles(){
        Connection con = DBUtils.obtainConnection();
        Statement stat = null;
        ResultSet rs = null;

        List<RoleEO> roleList = new ArrayList<RoleEO>();
        RoleEO roleEO = null;
        
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(SQLSelectAllStatement);
            while (rs.next()) {
                roleEO = new RoleEO();
                roleEO.setRoleID(rs.getInt(1));
                roleEO.setRoleName(rs.getString(2));           
                roleList.add(roleEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(stat);
        DBUtils.close(con);
        return roleList;
    }
    
    /**
     * Finds roles by name using LIKE query.
     * @param roleName Role name pattern
     * @return list of RoleEO objects
     */
    public List<RoleEO> findRoleByRoleName (String roleName){
        Connection con = DBUtils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        
        List<RoleEO> roleList = new ArrayList<RoleEO>();
        RoleEO roleEO = null;
        
        try {
            pstat = con.prepareStatement(SQLSelectStatementByName);
            pstat.setString(1, roleName);
            rs = pstat.executeQuery();
            
            while (rs.next()) {
                roleEO = new RoleEO();
                roleEO.setRoleID(rs.getInt(1));
                roleEO.setRoleName(rs.getString(2));
                roleList.add(roleEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(rs);
        DBUtils.close(pstat);
        DBUtils.close(con);
        return roleList;
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
//import training.iqgateway.dao.RoleDAO;
//import training.iqgateway.entities.RoleEO;
//import training.iqgateway.utils.DBUtils;
//
//public class RoleDAOImpl implements RoleDAO {
//    
//    private String SQLCountStatement = 
//        "SELECT COUNT(*) FROM ROLE";
//    
//    private String SQLInsertStatement = 
//        "INSERT INTO ROLE VALUES (?, ?)";
//    
//    private String SQLUpdateStatement = 
//        "UPDATE ROLE SET ROLE_NAME = ? WHERE ROLE_ID = ?";
//    
//    private String SQLDeleteStatement =
//        "DELETE FROM ROLE WHERE ROLE_ID = ?";
//    
//    private String SQLSelectStatementByID = 
//        "SELECT * FROM ROLE WHERE ROLE_ID = ?";
//
//    private String SQLSelectAllStatement = 
//        "SELECT * FROM ROLE";
//    
//    private String SQLSelectStatementByName = 
//        "SELECT * FROM ROLE WHERE ROLE_NAME LIKE ?";
//    
//    public int countRoles(){
//        Connection con = DBUtils.obtainConnection();
//        Statement stat = null;
//        ResultSet rs = null;
//        int count = 0;
//        try {
//            stat = con.createStatement();
//            rs = stat.executeQuery(SQLCountStatement);
//            if (rs.next()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(stat);
//        DBUtils.close(con);
//        return count;
//    }
//    
//    public int insertRole (RoleEO roleEO){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        int count = 0;
//        try {
//            pstat = con.prepareStatement(SQLInsertStatement);
//            pstat.setInt(1, roleEO.getRoleID());
//            pstat.setString(2, roleEO.getRoleName());
//            count = pstat.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return count;
//    }
//    
//    public int updateRole (RoleEO roleEO){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        int count = 0;
//        try {
//            pstat = con.prepareStatement(SQLUpdateStatement);
//            pstat.setString(1, roleEO.getRoleName());
//            pstat.setInt(2, roleEO.getRoleID());
//            count = pstat.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return count;
//    }
//    
//    public int deleteRole (Integer roleID){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        int count = 0;
//        try {
//            pstat = con.prepareStatement(SQLDeleteStatement);
//            pstat.setInt(1, roleID);
//            count = pstat.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return count;
//    }
//
//    public RoleEO findRoleByRoleID (Integer roleID){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//
//        RoleEO roleEO = null;
//        try {
//            pstat = con.prepareStatement(SQLSelectStatementByID);
//            pstat.setInt(1, roleID);
//            rs = pstat.executeQuery();
//            
//            while (rs.next()) {
//                roleEO = new RoleEO();
//                roleEO.setRoleID(rs.getInt(1));
//                roleEO.setRoleName(rs.getString(2));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return roleEO;
//    }
//    
//    public List<RoleEO> findAllRoles(){
//        Connection con = DBUtils.obtainConnection();
//        Statement stat = null;
//        ResultSet rs = null;
//
//        List<RoleEO> roleList = new ArrayList<RoleEO>();
//        RoleEO roleEO = null;
//        
//        try {
//            stat = con.createStatement();
//            rs = stat.executeQuery(SQLSelectAllStatement);
//            while (rs.next()) {
//                roleEO = new RoleEO();
//                roleEO.setRoleID(rs.getInt(1));
//                roleEO.setRoleName(rs.getString(2));           
//                roleList.add(roleEO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(stat);
//        DBUtils.close(con);
//        return roleList;
//    }
//    
//    public List<RoleEO> findRoleByRoleName (String roleName){
//        Connection con = DBUtils.obtainConnection();
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//        
//        List<RoleEO> roleList = new ArrayList<RoleEO>();
//        RoleEO roleEO = null;
//        
//        try {
//            pstat = con.prepareStatement(SQLSelectStatementByName);
//            pstat.setString(1, roleName);
//            rs = pstat.executeQuery();
//            
//            while (rs.next()) {
//                roleEO = new RoleEO();
//                roleEO.setRoleID(rs.getInt(1));
//                roleEO.setRoleName(rs.getString(2));
//                roleList.add(roleEO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DBUtils.close(rs);
//        DBUtils.close(pstat);
//        DBUtils.close(con);
//        return roleList;
//    }  
//}

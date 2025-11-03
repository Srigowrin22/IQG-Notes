package training.iqgateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{
    
    private String SQLInsertStatement =
        "INSERT INTO CUSTOMER VALUES(?,?)";

    private String SQLUpdateStatement =
        "UPDATE CUSTOMER SET NAME = ? WHERE ID = ?";
    
    private String SQLDeleteStatement = 
        "DELETE FROM CUSTOMER WHERE ID = ?";
    
    private String SQLSelectStatementByID = 
        "SELECT * FROM CUSTOMER WHERE ID = ?";
    
    private String SQLSelectAllStatement = 
        "SELECT * FROM CUSTOMER";
    
    private String SQLSelectStatementByName = 
        "SELECT * FROM CUSTOMER WHERE NAME = ?";
    
    
    public int insertCustomer (CustomerEO custEO){
        Connection con = Utils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;     
        try {
            pstat = con.prepareStatement(SQLInsertStatement);
            pstat.setInt(1, custEO.getId());
            pstat.setString(2, custEO.getName());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Utils.close(pstat);
        Utils.close(con);
        return count;      
    }
    
    public int updateCustomer (CustomerEO custEO){
        Connection con = Utils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;     
        try {
            pstat = con.prepareStatement(SQLDeleteStatement);
            pstat.setString(1, custEO.getName());
            pstat.setInt(2, custEO.getId());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Utils.close(pstat);
        Utils.close(con);
        return count;      
    }
        
    public int deleteCustomer (Integer custID){
        Connection con = Utils.obtainConnection();
        PreparedStatement pstat = null;
        int count = 0;     
        try {
            pstat = con.prepareStatement(SQLUpdateStatement);
            pstat.setInt(1, custID);
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Utils.close(pstat);
        Utils.close(con);
        return count;      
    }

    public CustomerEO findCustomerByID(Integer custID){
        Connection con = Utils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        CustomerEO custEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByID);
            pstat.setInt(1, custID);
            rs = pstat.executeQuery();
            while (rs.next()) {
                custEO = new CustomerEO();
                custEO.setId(rs.getInt(1));
                custEO.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Utils.close(rs);
        Utils.close(pstat);
        Utils.close(con);
        return custEO;
    }
        
    public List<CustomerEO> findAllCustomers(){
        Connection con = Utils.obtainConnection();
        Statement stat = null;
        ResultSet rs = null;
        List<CustomerEO> custList = new ArrayList<CustomerEO>();
        CustomerEO custEO = null;
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(SQLSelectAllStatement);
            while (rs.next()) {
                custEO = new CustomerEO();
                custEO.setId(rs.getInt(1));
                custEO.setName(rs.getString(2));  
                custList.add(custEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Utils.close(rs);
        Utils.close(stat);
        Utils.close(con);
        return custList;
    }
        
    public List<CustomerEO> findCustomerByName (String custName){
        Connection con = Utils.obtainConnection();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<CustomerEO> custList = new ArrayList<CustomerEO>();
        CustomerEO custEO = null;
        try {
            pstat = con.prepareStatement(SQLSelectStatementByName);
            pstat.setString(1, custName);
            rs = pstat.executeQuery();
            
            while (rs.next()) {
                custEO = new CustomerEO();
                custEO.setId(rs.getInt(1));
                custEO.setName(rs.getString(2));  
                custList.add(custEO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Utils.close(rs);
        Utils.close(pstat);
        Utils.close(con);
        return custList;
    }
}

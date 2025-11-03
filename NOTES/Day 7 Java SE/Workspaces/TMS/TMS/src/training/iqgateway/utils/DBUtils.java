package training.iqgateway.utils;

import java.sql.*;

public class DBUtils {
    private static Connection con = null;
    private static Statement stat = null;
    private static PreparedStatement pstat = null;
    private static ResultSet rs = null;
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USERNAME = "TMS";
    private static final String PASSWORD = "TMS";

    public static Connection obtainConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            System.out.println("Driver Ready!! :)");

            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            System.out.println("Database connection is successful,..!");

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: !!" + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }

    public static void close(Statement givenStat) {
        stat = givenStat;
        try {
            stat.close();
        } catch (SQLException SQLE) {
            SQLE.printStackTrace();
        }
    }

    public static void close(PreparedStatement givenPStat) {
        pstat = givenPStat;
        try {
            pstat.close();
        } catch (SQLException SQLE) {
            SQLE.printStackTrace();
        }
    }

    public static void close(ResultSet givenRs) {
        rs = givenRs;
        try {
            rs.close();
        } catch (SQLException SQLE) {
            SQLE.printStackTrace();
        }
    }

    public static void close(Connection givenConn) {
        con = givenConn;
        try {
            con.close();
        } catch (SQLException SQLE) {
            SQLE.printStackTrace();
        }
    }
}

package training.iqgateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utils {
    private static Connection con = null;
    private static Statement stat = null;
    private static PreparedStatement pstat = null;
    private static ResultSet rs = null;
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    public static Connection obtainConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
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

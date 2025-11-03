package training.iqgateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Demonstrates basic JDBC operations with Oracle Database.
 *
 * <p>This class provides a complete example of:
 * <ul>
 *   <li>Establishing a database connection</li>
 *   <li>Executing SQL queries</li>
 *   <li>Processing result sets</li>
 *   <li>Proper resource cleanup</li>
 * </ul>
 *
 * <p><b>Database Requirements:</b>
 * <ul>
 *   <li>Oracle Database with HR schema</li>
 *   <li>Oracle JDBC driver in classpath</li>
 * </ul>
 *
 * @author IQ Gateway Training
 * @version 1.0
 * @see java.sql.Connection
 * @see java.sql.Statement
 * @see java.sql.ResultSet
 */
public class JDBCSample {

    /**
     * Main entry point for the JDBC demonstration program.
     *
     * <p><b>Execution Flow:</b>
     * <ol>
     *   <li>Loads Oracle JDBC driver</li>
     *   <li>Establishes connection to Oracle database</li>
     *   <li>Creates statement and executes query</li>
     *   <li>Processes and displays DEPARTMENT records</li>
     *   <li>Cleans up resources in finally block</li>
     * </ol>
     *
     * <p><b>Connection Details:</b>
     * <ul>
     *   <li>URL: jdbc:oracle:thin:@localhost:1521:XE</li>
     *   <li>Username: HR</li>
     *   <li>Password: HR</li>
     * </ul>
     *
     * <p><b>Error Handling:</b>
     * <ul>
     *   <li>ClassNotFoundException - If JDBC driver not found</li>
     *   <li>SQLException - For any database errors</li>
     * </ul>
     *
     * @param args Command line arguments (not used)
     * @throws SQLException If any database access error occurs
     *
     * @see java.lang.Class#forName(String)
     * @see java.sql.DriverManager#getConnection(String, String, String)
     * @see java.sql.Statement#executeQuery(String)
     */
    public static void main(String[] args) throws SQLException {
        // Database connection parameters
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "HR";
        String password = "HR";

        // JDBC objects
        Connection con = null;
        Statement state = null;
        ResultSet res = null;

        try {
            // Stage 1: Connect
            // a) Load the JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Ready!! :)");

            // b) Connect to database
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection is successful,..!");

            // Stage 2: Query
            // a) Create Statement
            state = con.createStatement();

            // b) Execute the statement
            String query = "SELECT * FROM DEPARTMENTS";
            res = state.executeQuery(query);
            System.out.println("Query executed successfully");

            // Stage 3: Process Results
            System.out.println("\nDepartment Records:");
            System.out.println("ID\tName\t\tLocation\tManager");
            System.out.println("------------------------------------------------");

            while (res.next()) {
                int id = res.getInt("Department_id");
                String name = res.getString(2);
                int loc = res.getInt(3);
                int mgrId = res.getInt("Manager_id");

                System.out.printf("%-5d\t%-15s\t%-8d\t%d%n", id, name, loc,
                                  (mgrId == 0 ? -1 : mgrId));
            }

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Stage 4: Cleanup
            if (res != null)
                res.close();
            if (state != null)
                state.close();
            if (con != null)
                con.close();
            System.out.println("\nResources released successfully");
        }
    }
}

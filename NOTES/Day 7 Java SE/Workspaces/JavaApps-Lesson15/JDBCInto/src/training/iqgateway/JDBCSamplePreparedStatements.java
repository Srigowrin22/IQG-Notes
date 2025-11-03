package training.iqgateway;

import java.sql.*;

import java.util.Scanner;

public class JDBCSamplePreparedStatements {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "HR";
        String password = "HR";

        Connection con = null;
        Statement state = null;
        ResultSet res = null;
        PreparedStatement p = null;

        // Exception Handlers for RunTimeException
        try {
            // Stage 1: Connect
            // a) Load the JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Ready!! :)");

            // b) Connect to database
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection is successful,..!");

            // Stage 2: Query
            // a) Query to DB Statement
            String query = "SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?";

            // b) Creatin of the statements
            p = con.prepareStatement(query);

            // c) Supply values for ? placeholder
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the department id: ");
            int id = sc.nextInt();
            p.setInt(1, id);

            System.out.println();


            res = p.executeQuery();
            //            int count = p.executeUpdate();
            //            System.out.println("count: " + count);

            // Stage 3: Process
            // a) Step through the results
            while (res.next()) {

                // b) Assign result to variables
                int idn = res.getInt("Department_id");
                String name = res.getString(2);
                int loc = res.getInt(3);
                int mgrId = res.getInt("Manager_id");
                System.out.println("ID: " + idn + "\tName: " + name +
                                   "\tLocation id: " + loc + "\tManager ID: " +
                                   mgrId);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            res.close();
            p.close();
            con.close();
        }
    }
}

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import java.sql.*;

public class AppLifeCycleEventDemo implements ServletContextListener {

  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("Initializing Application …");
    // Load the JDBC driver
    try {
      Class.forName("com.mysql.jdbc.Driver ");
    }
    catch (ClassNotFoundException e) {
      System.out.println(e.toString());
    }

    // Get the ServletContext object
    ServletContext servletContext = sce.getServletContext();

    // Set a ServletContext attribute
    //servletContext.setAttribute("dbUrl", "jdbc:mysql://localhost:3306/Products");

	Connection dbcon=DriverManager.getConnection("jdbc:mysql://localhost:3306/Products","root","root");

	servletContext.setAttribute("ConObj", dbcon);
	
    System.out.println("Application initialized");
  }

  public void contextDestroyed(ServletContextEvent cse) {
    System.out.println("Application shut down");
  }
}

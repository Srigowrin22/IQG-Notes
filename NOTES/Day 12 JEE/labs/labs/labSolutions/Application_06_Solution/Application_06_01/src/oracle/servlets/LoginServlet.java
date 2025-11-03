package oracle.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


import javax.annotation.Resource;

import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
  
  
  
  @Resource (name = "jdbc/fodDS") 
        private javax.sql.DataSource myDs;
        private Connection con;
        private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
        private String pass;
        private String name;


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>LoginServlet</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a GET. This is the reply.</p>");
        out.println("</body></html>");
        out.close();
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        name = request.getParameter("user_name");
        pass = request.getParameter("user_password");
        boolean result = verifyPassword(name, pass);
        out.println ("<html>");
        out.println ("<body>");
        if (result == true){
         out.println ("Hello " + name + ": You login module is working great!");
        }
        else{
          out.println ("Invalid user name or password");
        }
          out.println ("</body></html>");
          out.close();

    }

    public void configureConnection() throws SQLException {
       try{
          con = myDs.getConnection();
          }
       catch (Exception e){
          System.out.println("Connection failed: " +e.toString());
          }
     }

    public Connection getConnection() throws SQLException
        {
        configureConnection();
        return con;
        }


    protected boolean verifyPassword(String theuser, String password) {
       String originalPassword=null;

       try {
            con=getConnection();
            Statement stmt= con.createStatement();
            stmt.executeQuery("select password from login where uname='"+theuser+"'");
            ResultSet rs = stmt.getResultSet();
            if(rs.next()) {
                       originalPassword=rs.getString(1);
                   }
              stmt.close();
              if(originalPassword.equals(password)) {
                       return true;
                   } 
               else {
                       return false;
                   }
               }
          catch (Exception e){
                 System.out.println("Exception: verifyPassword="+ e);
                 return false;
             }
      }

}

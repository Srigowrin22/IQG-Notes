package traning.iqgateway;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class DateTimeServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        java.util.Date dateTimeRef = new java.util.Date();
        out.println("<html>");
        out.println("<head><title>DateTimeServlet</title></head>");
        out.println("<body>");
        out.println("<h2>The Current DateTime reference is "+ dateTimeRef +" .</h2>");
        out.println("<p>The servlet has received a GET. This is the reply.</p>");
        out.println("</body></html>");
        out.close();
    }
}

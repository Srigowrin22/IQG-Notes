package counterfilterpro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class DisplayCount extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        
        ServletContext context=getServletContext();
        
        Integer count=(Integer)context.getAttribute("count");
        
        out.println("<html>");
        out.println("<head><title>DisplayCount</title></head>");
        out.println("<body>");
        
        if(count!=null) {
            
            out.println(" The Current count is " +count.intValue());
        }
        else {
            
            out.println(" Count not available ");
        }
        out.println("</body></html>");
        out.close();
    }
}

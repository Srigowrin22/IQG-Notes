package training.iqgateway.servlet;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
 
import training.iqgateway.business.CurrencyConvertor;
 
/**
* Servlet implementation class CurrencyConvertorServlet
*/
public class CurrencyConvertorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrencyConvertorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		// Read the Input Values from the Previous Page
		String givenDollarValue = request.getParameter("dollarInput");
		double dollarValue = Double.parseDouble(givenDollarValue);
		// Get Access to IOC Container 
		ApplicationContext appContext = WebApplicationContextUtils
						.getRequiredWebApplicationContext(getServletContext());
		// Get the Bean 
		CurrencyConvertor ccRef = 
						(CurrencyConvertor)appContext.getBean("ccBean");
		// Invoke the Business Logic
		String result = ccRef.dollarToRS(dollarValue);
		// Formulate the Result
		out.println("<h2> " + result + " </h2>");
		out.println("</html>");
		out.println("</body>");

	}
 
}
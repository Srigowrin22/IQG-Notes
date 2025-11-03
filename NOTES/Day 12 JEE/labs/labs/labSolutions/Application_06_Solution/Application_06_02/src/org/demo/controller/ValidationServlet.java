package org.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.ejb.EJB;

import javax.servlet.*;
import javax.servlet.http.*;

import org.demo.business.CreditCardValidator;

public class ValidationServlet extends HttpServlet {
  
       // add code ....
        @EJB(mappedName = "CreditCardValidatorSessionEJB06_Solution")
        private CreditCardValidator ccEJB;
    
        private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
        private String ccType;
        private int ccNum;


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Servlet</title></head>");
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
        ccType = request.getParameter("card_type");
        ccNum = Integer.parseInt(request.getParameter("card_number"));
        boolean result = ccEJB.validateCreditCard(ccType, ccNum);
        out.println ("<html>");
        out.println ("<body>");
        if (result == true){
         out.println ("Credit Card with the following details is valid <br> Credit Card Type : " + ccType + " <br> Credit Card Number : " + ccNum);
        }
        else{
          out.println ("Invalid Credit Card Type or Credit Card Number");
        }
          out.println ("</body></html>");
          out.close();

    }

}

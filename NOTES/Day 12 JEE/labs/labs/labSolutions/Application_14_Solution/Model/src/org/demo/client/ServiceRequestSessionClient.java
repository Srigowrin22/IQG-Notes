package org.demo.client;

import java.util.Hashtable;
import org.demo.business.ServiceRequestSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.demo.business.ServiceRequestSession;

public class ServiceRequestSessionClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            ServiceRequestSession serviceRequestSession = (ServiceRequestSession)context.lookup("ServiceRequestSessionEJB_Solution#org.demo.business.ServiceRequestSession");
            confirmByEmail(serviceRequestSession);
               System.out.println("Mail Sent....");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void confirmByEmail(ServiceRequestSession message) {

    System.out.println("*** Confirm By Email requested for Service Request Id: \"SKING11296\"");
     try {                  
         String from = "support@srdemo.org";
         String to = "steve.king@srdemo.org";
         String subject = "Notification: OrderID #SKING11296 Created";
         String msgText = "<html>" +
                          "<body>" +
                          "<h2>OrderID #SKING11296 Created</h2>" +
                          "<p>Dear Steve,</p>" +
                          "<p>Thanks for submitting the order. Your ordered has been entered into our system, and will be processed in a couple of days.</p>" +
                          "<p>Yours sincerely,<br>Support</p>" +
                          "</body></html>";
        System.out.println("*** before sendMailMessage ");
        message.sendMailMessage(from, to, subject, msgText);
        }
     catch (Exception e) {
                        System.out.println(e.toString());
                }
    }


    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}

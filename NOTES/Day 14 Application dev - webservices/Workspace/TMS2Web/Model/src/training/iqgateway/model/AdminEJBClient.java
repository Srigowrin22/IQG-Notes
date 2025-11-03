package training.iqgateway.model;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.entities.RoleEO;

public class AdminEJBClient {
    public static void main(String [] args) {
        RoleEO roleEO = new RoleEO(0,"asd");
        
        try {
            final Context context = getInitialContext();
            AdminEJB adminEJB = (AdminEJB)context.lookup("TMSWebApplication-Model-AdminEJB#training.iqgateway.model.AdminEJB");
            adminEJB.addRole(roleEO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://localhost:7101");
        return new InitialContext( env );
    }
}

package training.iqgateway;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CurrencyConvertorClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            CurrencyConvertor currencyConvertor = (CurrencyConvertor)context.lookup("EJBApp-SessionEJB-CurrencyConvertor#training.iqgateway.CurrencyConvertor");
            System.out.println(currencyConvertor.dollarsToRs(1000));
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

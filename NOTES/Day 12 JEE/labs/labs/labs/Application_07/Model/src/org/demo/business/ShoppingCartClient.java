package org.demo.business;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ShoppingCartClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            ShoppingCart shoppingCart = (ShoppingCart)context.lookup("ShoppingCartSessionEJB#org.demo.business.ShoppingCart");
            
            shoppingCart.setUid(10);
            shoppingCart.setCcNum(123456789);
            System.out.println(shoppingCart.generateBill());
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

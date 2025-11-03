package org.demo.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.demo.business.ShoppingCart;

public class ShoppingCartClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            ShoppingCart shoppingCart = (ShoppingCart)context.lookup("ShoppingCartSessionEJB_Solution#org.demo.business.ShoppingCart");

            shoppingCart.setUid(10);
            shoppingCart.setCcNum(123456789);
            System.out.println( shoppingCart.generateBill(  ) );


        } catch (Exception ex) {
            ex.printStackTrace();
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

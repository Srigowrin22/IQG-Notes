package oracle.model;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import oracle.services.SessionEJB13Soln;

public class SessionEJBClient1 {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            SessionEJB13Soln sessionEJB = (SessionEJB13Soln)context.lookup("SessionEJB13Soln_Solution#oracle.services.SessionEJB13Soln");
//            for (Person person : (List<Person>)sessionEJB.queryPersonFindAll()) {
//                System.out.println( "id = " + person.getId() );
//                System.out.println( "firstName = " + person.getFirstName() );
//                System.out.println( "lastName = " + person.getLastName() );
//                System.out.println( "email = " + person.getEmail() );
//                System.out.println( "phoneNumber = " + person.getPhoneNumber() );
//            }
            for (Person person : (List<Person>)sessionEJB.queryPersonFindById( 106 )) {
                System.out.println( "id = " + person.getId() );
                System.out.println( "firstName = " + person.getFirstName() );
                System.out.println( "lastName = " + person.getLastName() );
                System.out.println( "email = " + person.getEmail() );
                System.out.println( "phoneNumber = " + person.getPhoneNumber() );
            }
            for (Person person : (List<Person>)sessionEJB.queryPersonFindByLastName( "A%" )) {
                System.out.println( "id = " + person.getId() );
                System.out.println( "firstName = " + person.getFirstName() );
                System.out.println( "lastName = " + person.getLastName() );
                System.out.println( "email = " + person.getEmail() );
                System.out.println( "phoneNumber = " + person.getPhoneNumber() );
            }
//            for (Product product : (List<Product>)sessionEJB.queryProductFindAll()) {
//                System.out.println( "additionalInfo = " + product.getAdditionalInfo() );
//                System.out.println( "costPrice = " + product.getCostPrice() );
//                System.out.println( "description = " + product.getDescription() );
//                System.out.println( "externalUrl = " + product.getExternalUrl() );
//                System.out.println( "listPrice = " + product.getListPrice() );
//                System.out.println( "minPrice = " + product.getMinPrice() );
//                System.out.println( "productId = " + product.getProductId() );
//                System.out.println( "productName = " + product.getProductName() );
//                System.out.println( "shippingClassCode = " + product.getShippingClassCode() );
//                System.out.println( "supplierId = " + product.getSupplierId() );
//                System.out.println( "warrantyPeriodMonths = " + product.getWarrantyPeriodMonths() );
//                System.out.println( "category = " + product.getCategory() );
//            }
//            for (Category category : (List<Category>)sessionEJB.queryCategoryFindAll()) {
//                System.out.println( "categoryDescription = " + category.getCategoryDescription() );
//                System.out.println( "categoryId = " + category.getCategoryId() );
//                System.out.println( "categoryLockedFlag = " + category.getCategoryLockedFlag() );
//                System.out.println( "categoryName = " + category.getCategoryName() );
//                System.out.println( "productList = " + category.getProductList() );
//                System.out.println( "parent = " + category.getParent() );
//                System.out.println( "children = " + category.getChildren() );
//            }
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

package oracle.model;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import oracle.service.SessionEJB08;

public class SessionEJB08Client {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            SessionEJB08 sessionEJB08 = (SessionEJB08)context.lookup("Application_08-Model-SessionEJB08#oracle.service.SessionEJB08");
            for (Product product : (List<Product>)sessionEJB08.getProductFindAll()) {
                printProduct(product);
            }
            for (Category category : (List<Category>)sessionEJB08.getCategoryFindAll()) {
                printCategory(category);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printProduct(Product product) {
        System.out.println( "additionalInfo = " + product.getAdditionalInfo() );
        System.out.println( "costPrice = " + product.getCostPrice() );
        System.out.println( "description = " + product.getDescription() );
        System.out.println( "externalUrl = " + product.getExternalUrl() );
        System.out.println( "listPrice = " + product.getListPrice() );
        System.out.println( "minPrice = " + product.getMinPrice() );
        System.out.println( "productName = " + product.getProductName() );
        System.out.println( "shippingClassCode = " + product.getShippingClassCode() );
        System.out.println( "supplierId = " + product.getSupplierId() );
        System.out.println( "warrantyPeriodMonths = " + product.getWarrantyPeriodMonths() );
        System.out.println( "category = " + product.getCategory() );
    }

    private static void printCategory(Category category) {
        System.out.println( "categoryDescription = " + category.getCategoryDescription() );
        System.out.println( "categoryId = " + category.getCategoryId() );
        System.out.println( "categoryLockedFlag = " + category.getCategoryLockedFlag() );
        System.out.println( "categoryName = " + category.getCategoryName() );
        System.out.println( "productList = " + category.getProductList() );
        System.out.println( "parent = " + category.getParent() );
        System.out.println( "children = " + category.getChildren() );
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://localhost:7101");
        return new InitialContext( env );
    }
}

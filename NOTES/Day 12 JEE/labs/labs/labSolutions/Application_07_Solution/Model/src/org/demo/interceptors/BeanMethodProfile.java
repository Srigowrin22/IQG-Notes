package org.demo.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


public class BeanMethodProfile {
    public BeanMethodProfile() {
    }
    
    @AroundInvoke
    private Object profile(InvocationContext invCtx) throws Exception {
        
         long time1 = System.nanoTime();
         Object result = invCtx.proceed();
         long time2 = System.nanoTime();
         System.out.println(invCtx.getMethod().getName() + " took: " + 
                                ((time2 - time1)/ 1000.0) + " nano seconds.");
         return result;
     }

    
    
    
    
}

package org.demo.business;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless(name = "CreditCardValidator", mappedName = "CreditCardValidatorSessionEJB07_Solution")
@Remote
@Local
public class CreditCardValidatorBean implements CreditCardValidator,
                                                CreditCardValidatorLocal {
    public CreditCardValidatorBean() {
    }
    
    public String validateCC(int uid, int ccNum){
          if (uid==10 && ccNum==123456789)
                return "Authentic";
          else
                return "Invalid";
    }

    
    
}

package org.demo.business;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless(name = "CreditCardValidator", mappedName = "CreditCardValidatorSessionEJB")
@Remote
@Local
public class CreditCardValidatorBean implements CreditCardValidator,
                                                CreditCardValidatorLocal {
    public CreditCardValidatorBean() {
    }
    
    public boolean validateCreditCard(String type, int number){
        if(type.equalsIgnoreCase("AMEX") && number == 123456789)
            return true;
        else
            return false;
        }
}

package org.demo.business;

import javax.ejb.Stateless;

@Stateless(name = "CreditCardValidator",
           mappedName = "CreditCardValidatorSessionEJB")
public class CreditCardValidatorBean implements CreditCardValidator,
                                                CreditCardValidatorLocal {
    public CreditCardValidatorBean() {
    }

    public String validateCC(int uid, int ccNum) {
        if (uid == 10 && ccNum == 123456789)
            return "Authentic";
        else
            return "Invalid";
    }
}

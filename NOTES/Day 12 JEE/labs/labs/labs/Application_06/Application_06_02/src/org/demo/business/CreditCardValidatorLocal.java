package org.demo.business;

import javax.ejb.Local;

@Local
public interface CreditCardValidatorLocal {
    public boolean validateCreditCard(String type, int number);
}

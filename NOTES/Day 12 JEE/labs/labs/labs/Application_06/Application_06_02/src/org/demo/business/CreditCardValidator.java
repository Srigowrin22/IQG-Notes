package org.demo.business;

import javax.ejb.Remote;

@Remote
public interface CreditCardValidator {
    public boolean validateCreditCard(String type, int number);
}

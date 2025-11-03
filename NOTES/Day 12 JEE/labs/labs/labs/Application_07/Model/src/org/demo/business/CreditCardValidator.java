package org.demo.business;

import javax.ejb.Remote;

@Remote
public interface CreditCardValidator {
    public String validateCC(int uid, int ccNum);
}


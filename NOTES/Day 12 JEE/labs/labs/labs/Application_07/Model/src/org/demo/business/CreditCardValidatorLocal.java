package org.demo.business;

import javax.ejb.Local;

@Local
public interface CreditCardValidatorLocal {
    public String validateCC(int uid, int ccNum);
}

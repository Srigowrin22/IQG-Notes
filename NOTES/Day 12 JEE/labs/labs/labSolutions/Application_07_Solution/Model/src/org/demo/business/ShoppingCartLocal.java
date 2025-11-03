package org.demo.business;

import javax.ejb.Local;

@Local
public interface ShoppingCartLocal {
    
    public void setUid(int uid); 
    public void setCcNum(int ccNum);
    public String generateBill();

    
}

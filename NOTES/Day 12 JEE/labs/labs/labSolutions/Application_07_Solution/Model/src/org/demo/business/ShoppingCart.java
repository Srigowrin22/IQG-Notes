package org.demo.business;

import javax.ejb.Remote;

@Remote
public interface ShoppingCart {
    
    public void setUid(int uid); 
    public void setCcNum(int ccNum);
    public String generateBill();

    
}

package org.demo.business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import javax.interceptor.Interceptors;

import org.demo.interceptors.BeanMethodProfile;

@Stateful(name = "ShoppingCart", mappedName = "ShoppingCartSessionEJB")
@Remote
@Local
@Interceptors(BeanMethodProfile.class)

public class ShoppingCartBean implements ShoppingCart, ShoppingCartLocal {
    
    @EJB private CreditCardValidator ccv;
    
    private int uid;
    private int ccNum;
    
    public ShoppingCartBean() {
    }
    
    public String generateBill(){
        String res = ccv.validateCC(getUid(), getCcNum());
        if(res.equals("Authentic"))
            return "Order Confirmed";
        else
            return "Invalid Transaction";
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setCcNum(int ccNum) {
        this.ccNum = ccNum;
    }

    public int getCcNum() {
        return ccNum;
    }
}

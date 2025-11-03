package oe;

public class Hardware extends Product implements Taxable {
    int warrantyPeriod = 6;
    
    public double getTax(double amount) {
        return amount * TAX_RATE;
    }
    public Hardware(String name,String desc,double price)
		{
		  setName(name);
		  setDescription(desc);
		  setRetailPrice(price);
                }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
}

package oe;

public class Software extends Product {
    String license = "30 day trial";
    
    public Software(String name, String desc, double price)
		{
		  setName(name);
		  setDescription(desc);
		  setRetailPrice(price);
                }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicense() {
        return license;
    }
}

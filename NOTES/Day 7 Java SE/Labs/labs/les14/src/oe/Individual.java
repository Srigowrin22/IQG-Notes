package oe;

public class Individual extends Customer {
    public Individual(String aName, String aAddress, String aPhone,
                 String aLicNumber) {
        super(aName, aAddress, aPhone);
        licNumber = aLicNumber;
    }
    private String licNumber;

    public void setLicNumber(String licNumber) {
        this.licNumber = licNumber;
    }

    public String getLicNumber() {
        return licNumber;
    }
    
    public String toString() {
        return super.toString() + " " + licNumber ; 
        }
}

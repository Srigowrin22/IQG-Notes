package oe;

public class Manual extends Product {
    String publisher = "Oradev Press";
    
    public Manual(String name,String desc,double price)
    {
      setName(name);
      setDescription(desc);
      setRetailPrice(price);
    }


    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }
}

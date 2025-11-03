package training.iqgateway;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String contact;

    //TODO
    public Customer(){ }

    public Customer(int id, String name, String address, String contact) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }
}

package training.iqgateway;

public class CustomerEO {
    
    private Integer id;
    private String name;


    public CustomerEO(){
        super();
    }

    public CustomerEO(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void toString(){
        System.out.println("Customer Details: [ Customer Name: " + this.getName() +
                           " Customer ID: " + this.getId() + " ]");
    }
}

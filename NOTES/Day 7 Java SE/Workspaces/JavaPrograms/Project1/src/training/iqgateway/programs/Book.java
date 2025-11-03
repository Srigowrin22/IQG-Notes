package training.iqgateway.programs;

public class Book {
    private String title;
    private String author;
    private float price;

    public Book() {
        title = "NA";
        author = "NA";
        price = 0.0f;
    }

    public Book(String title, String author) {
        super();
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, float price) {
        super();
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public static void main(String[] args) {
        Book b1 = new Book();
        Book b2 = new Book("Inkheart", "Cornelia Funke");
        Book b3 = new Book("Norwegian Woods", "Murakami", 270.00f);
    }
}

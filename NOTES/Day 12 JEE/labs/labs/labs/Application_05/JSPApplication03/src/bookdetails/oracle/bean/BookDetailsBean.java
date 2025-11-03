package bookdetails.oracle.bean;

public class BookDetailsBean {
    private String title;
    private String author;
    private String description;
    private String isbn;
    
    public BookDetailsBean( String title, String author, String description, String isbn) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
    }

    public String getTitle() {
        return this.title;
    }
    
    public String getAuthor() {
        return this.author;
    }

    public String getDescription() {
        return this.description;
    }
    
    public String getIsbn() {
        return this.isbn;
    }
    
}


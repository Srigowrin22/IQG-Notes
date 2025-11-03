package org.demo.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Books.findAll", query = "select o from Books o"),
   @NamedQuery(name="findBookById", query="select object(o) from Books o where o.bookId = :bookId")
})
public class Books implements Serializable {
    @Id
    @Column(name="BOOK_ID", nullable = false)
    private Long bookId;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private String name;

    public Books() {
    }

    public Books(Long bookId, String genre, String name) {
        this.bookId = bookId;
        this.genre = genre;
        this.name = name;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

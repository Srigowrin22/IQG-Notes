package oracle.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="Person")

@NamedQueries({
    @NamedQuery(name="Person.findAll", query = "select o from Person o"),
    @NamedQuery(name = "Person.findById", query = "select o from Person  o where o.id = :id"),
    @NamedQuery(name = "Person.findByLastName", query = "select o from Person o where o.lastName like :lastName")
})

@Table(name = "PERSONS")

public class Person implements Serializable{
    
    @Id
    @Column(name="PERSON_ID")
    @GeneratedValue(generator = "PER_SEQ", 
                    strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="PER_SEQ",
                           sequenceName = "PERSON_SEQ")
    private Long id;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")    
    private String lastName;
    @Column(name="EMAIL")
    private String email;
    @Column(name="PHONE_NUMBER")
    private String phoneNumber;
    
    
    protected Person() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

package oracle.jsp;

import javax.servlet.http.*;
import java.util.Vector;
import java.util.Enumeration;

public class CartBean {
    Vector vector = new Vector();
    String submit = null;
    String item = null;

    public CartBean() {
    }
    
    private void addItem(String name){
        vector.addElement(name);
    }
    
    private void removeItem(String name){
        vector.removeElement(name);
    }
    
    public String[] getItems(){
        String[] s = new String[vector.size()];
        vector.copyInto(s);
        return s;
    }
    
    public void processRequest(){
        if(submit == null) addItem(item);
        if(submit.equals("Add")) addItem(item);
        else if (submit.equals("Remove")) removeItem(item);
        reset();
    }

    public String showDetails(String name) {
        String response = "";
        if (name.equals("The Time Machine"))
            response = "Author: H. G. Wells </br>" +
                "Country: England </br>" +
                "Language: English </br>" +
                "Genre(s): Science fiction novel/Allegory </br>" +
                "Publisher: William Heinemann </br>" +
                "Publication date: 1895 </br>";

        else if (name.equals("The Count of Monte Cristo"))
            response = "Author: Alexandre Dumas, père </br>" +
                "Country: France </br>" +
                "Language: French </br>" +
                "Genre(s): Historical, Adventure </br>" +
                "Publisher: Chapman and Hall </br>" +
                "Publication date: 1844-1846 </br>";

        else if (name.equals("The Lord of the Rings"))
            response = "Author: J. R. R. Tolkien </br>" +
                "Country: England </br>" +
                "Language: English </br>" +
                "Genre(s): High fantasy, Adventure novel, Heroic romance, Action adventure </br>" +
                "Publisher: Allen & Unwin </br>" +
                "Publication date: 1954 and 1955 </br>";

        else if (name.equals("Pride and Prejudice"))
            response = "Author: Jane Austen </br>" +
                "Country: England </br>" +
                "Language: English </br>" +
                "Genre(s): Romantic comedy, Novel of manners </br>" +
                "Publisher: T. Egerton, Whitehall </br>" +
                "Publication date: 28 January 1813 </br>";

       else if (name.equals("The Adventures of Sherlock Holmes"))
            response = "Author: Arthur Conan Doyle </br>" +
                "Country: England </br>" +
                "Language: English </br>" +
                "Series: Sherlock Holmes </br>" +
                "Genre(s): Detective fiction short stories </br>" +
                "Publisher: George Newnes </br>" +
                "Publication date: 1892 </br>";

        else if (name.equals("Alice's Adventures in Wonderland"))
            response = "Author: Charles \"Lewis Carroll\" Dodgson </br>" +
                "Country: England </br>" +
                "Language: English </br>" +
                "Genre(s): Children's fiction </br>" +
                "Publisher: Macmillan </br>" +
                "Publication date: 1865 </br>";
        
        return response;
    }


    public void setItem(String name) {
        item = name;
    }

    public void setSubmit(String s) {
        submit = s;
    }


    private void reset() {
        submit = null;
        item = null;
    }  
}



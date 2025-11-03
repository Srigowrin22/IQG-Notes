package org.demo.view.backing;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.context.FacesContext;
import org.demo.business.BooksFacadeEJB;
import org.demo.business.BooksFacadeEJBBean;
import org.demo.persistence.Books;
import javax.naming.InitialContext;

//  import javax.transaction.UserTransaction;

public class ListBooks {
    private HtmlForm form1;
    private HtmlInputText bookId;
    private HtmlInputText bookName;
    private HtmlInputTextarea genre;
    private HtmlCommandButton commandButton1;
    private HtmlCommandButton commandButton2;
    private HtmlCommandButton commandButton3;
    private HtmlCommandButton commandButton4;
    private List<Books> books;
    private BooksFacadeEJB BooksFacade;
    private HtmlDataTable dataTable1;
    
//    private UserTransaction userTx;
    
    public ListBooks() {
        
        InitialContext context = null;
        System.out.println("ListBooks()");
        try {
            context = new InitialContext();
            BooksFacade =
            (BooksFacadeEJB)context.lookup("BooksFacadeSessionEJB#org.demo.business.BooksFacadeEJB");
 //          userTx = (UserTransaction)context.lookup("javax.transaction.UserTransaction");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMessage(String text, boolean err) {
        System.out.println("FacesMessage: " + text);
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMsg =
            new FacesMessage((err ? FacesMessage.SEVERITY_ERROR :
                              FacesMessage.SEVERITY_INFO), text, "");
        context.addMessage(null, facesMsg);
    }

    public void setForm1(HtmlForm form1) {
        this.form1 = form1;
    }

    public HtmlForm getForm1() {
        return form1;
    }

    public void setBookId(HtmlInputText bookId) {
        this.bookId = bookId;
    }

    public HtmlInputText getBookId() {
        return bookId;
    }

    public void setBookName(HtmlInputText bookName) {
        this.bookName = bookName;
    }

    public HtmlInputText getBookName() {
        return bookName;
    }

    public void setGenre(HtmlInputTextarea genre) {
        this.genre = genre;
    }

    public HtmlInputTextarea getGenre() {
        return genre;
    }

    public void setCommandButton1(HtmlCommandButton commandButton1) {
        this.commandButton1 = commandButton1;
    }

    public HtmlCommandButton getCommandButton1() {
        return commandButton1;
    }

    public void setCommandButton2(HtmlCommandButton commandButton2) {
        this.commandButton2 = commandButton2;
    }

    public HtmlCommandButton getCommandButton2() {
        return commandButton2;
    }

    public void setCommandButton3(HtmlCommandButton commandButton3) {
        this.commandButton3 = commandButton3;
    }

    public HtmlCommandButton getCommandButton3() {
        return commandButton3;
    }


    public void setCommandButton4(HtmlCommandButton commandButton4) {
        this.commandButton4 = commandButton4;
    }

    public HtmlCommandButton getCommandButton4() {
        return commandButton4;
    }


    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public List<Books> getBooks() {
        try {
            books = BooksFacade.queryBooksFindAll();
        } catch (Exception e) {
            addMessage(e.getMessage(), true);
        }
        return books;
    }

    public void setDataTable1(HtmlDataTable dataTable1) {
        this.dataTable1 = dataTable1;
    }

    public HtmlDataTable getDataTable1() {
        return dataTable1;
    }
    

    public String clearFormFields() {
        bookId.setValue(null);
        bookName.setValue(null);
        genre.setValue(null);
        return "success";
    }


    public String insertBook() {
      /*   try{
               userTx.begin();  
               }
           catch(Exception e)
               {
                addMessage(e.getMessage(), true);
               }      */
        Books prod = new Books();
        Long bkId = Long.parseLong(bookId.getValue().toString());
        prod.setBookId(bkId);
        prod.setName(bookName.getValue().toString());
        prod.setGenre(genre.getValue().toString());
        
        List<Books> book = null;
        book = BooksFacade.findBookById(bkId);
    /*    if (book.size() == 1){
                try {
                    userTx.rollback();
                    addMessage("Book with ID " + prod.getBookId() + " already exist", false);
                    }
                catch (Exception e){
                        addMessage(e.getMessage(), true);
                    }
             }
        else{  */
                try{
                        System.out.println ("In insertBook() method.....");
                        BooksFacade.persistEntity(prod);
                        bookId.setValue(prod.getBookId());
              //        userTx.commit();
                        addMessage("Book " + prod.getBookId() + " inserted", false);
                    } catch (Exception e) {
                        addMessage(e.getMessage(), true);
                    }
      //   }
        
        return "success";
    }

    public String updateBook() {
        List<Books> prods = null;
        Long bookId = (Long)this.bookId.getValue();
        System.out.println("updateBook() bookId: " + bookId);

        try {
      //    userTx.begin();
            prods = BooksFacade.findBookById(bookId);
            if (prods.size() == 1) {
                Books prod = prods.get(0);
                prod.setName((String)bookName.getValue());
                prod.setGenre((String)genre.getValue());
                BooksFacade.mergeEntity(prod);
                addMessage("Book " + prod.getBookId() + " updated", false);
      //        userTx.commit();
            } else {
                addMessage("Book " + bookId + " not Found", false);
      //        userTx.rollback();
            }
        } catch (Exception e) {
            addMessage(e.getMessage(), true);
        }
        return "success";
    }

    public String deleteBook() {
        List<Books> prods = null;
        Long bookId = (Long)this.bookId.getValue();
        System.out.println("deleteBook() bookId: " + bookId);

        try {
         //   userTx.begin();
            prods = BooksFacade.findBookById(bookId);
            if (prods.size() == 1) {
                Books prod = prods.get(0);
                BooksFacade.removeBooks(prod);
                addMessage("Book " + prod.getBookId() + " Deleted", false);
          //    userTx.commit();
              
            } else {
                addMessage("Book " + bookId + " not found", false);
          //    userTx.rollback();
            }
        } catch (Exception e) {
            addMessage(e.getMessage(), true);
        }
        return "success";
    }

    
}

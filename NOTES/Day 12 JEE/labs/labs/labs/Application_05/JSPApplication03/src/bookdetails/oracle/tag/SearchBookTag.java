package bookdetails.oracle.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import java.util.HashMap;

import bookdetails.oracle.bean.BookDetailsBean;

/**
 * SimpleTag handler that pretends to search for a book, and stores
 * the result in a scoped variable.
 */
public class SearchBookTag extends SimpleTagSupport {
    private String var;

    private static final String BOOK_TITLE =
        "Harry Potter and the Philosopher's Stone";
    private static final String BOOK_AUTHOR = " J. K. Rowling";
    private static final String BOOK_DESCRIPTION =
        "Harry Potter and the Philosopher's Stone is the first novel in the Harry Potter series written by J. K. Rowling and featuring Harry Potter, a young wizard";
    private static final String BOOK_ISBN = "0019013552";


    public void setVar(String var) {
        this.var = var;
    }

    public void doTag() throws JspException {
        BookDetailsBean book =
            new BookDetailsBean(BOOK_TITLE, BOOK_AUTHOR, BOOK_DESCRIPTION,
                                BOOK_ISBN);
        getJspContext().setAttribute(this.var, book);
    }
}


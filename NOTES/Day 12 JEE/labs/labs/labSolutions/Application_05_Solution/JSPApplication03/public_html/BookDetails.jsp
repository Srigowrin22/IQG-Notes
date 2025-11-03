<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>

<%@ taglib prefix="ns" uri="/WEB-INF/MyTagLibrary.tld"%>

<html>
    <head>
        <title>Book Details</title>
    </head>
    <body style="font-family:'Comic Sans MS';">
        <h1 align="center">My JSP Book Details Application</h1>
         
        <hr></hr>
         
        <p>The application shows the usage of Custom Tag and the Expression Language.</p>
         <ul>
            <li>
                A 
                <em>&lt;ns:searchBook&gt;</em>
                 tag is invoked to populate the JSP page context with book
                details from the &quot;BookBean&quot; Java bean</li>
            <li>The books fields are all printed in capital letters.</li>
            <li>The details are printed in reverse.</li>
        </ul>
         
        <br></br>  
        <b><u>Result:</u></b>
            
        <br></br>
        
         <ns:searchBook var="book"/>

         
        <table border="1">
            <thead>
            <tr>
                <td>
                    <b>Field</b>
                </td>
                <td>
                    <b>Description</b>
                </td>
                <td>
                    <b>Capitalized</b>
                </td>
                <td>
                    <b>Reversed</b>
                </td>
                </tr>
            </thead>
            <tr>
                <td width="91">Title</td>
                <td width="182">
                    ${book.title}  
                </td>
                <td width="248">
                   ${ns:caps(book.title)}
                </td>
                <td width="271">
      		 ${ns:reverse(book.title)}
                </td>
            </tr>
            <tr>
                <td width="91">Author</td>
                <td width="182">
                    ${book.author}
                </td>
                <td width="248">
                    ${ns:caps(book.author)}
                </td>
                  <td width="271">
                    ${ns:reverse(book.author)}
                </td>
            </tr>
            <tr>
                <td width="91">Description</td>
                <td width="182">
                    ${book.description}
                </td>
                <td width="248">
                    ${ns:caps(book.description)}
                </td>
                  <td width="271">
                    ${ns:reverse(book.description)}
                </td>
            </tr>
            <tr>
                <td width="91">ISBN</td>
                <td width="182">
                    ${book.isbn}
                </td>
                <td width="248">
                    ${ns:caps(book.isbn)}
                </td>
                  <td width="271">
                    ${ns:reverse(book.isbn)}
                </td>
            </tr>
        </table>
         
    </body>
</html>
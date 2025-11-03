<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view>
  <html>
    <head>
      <meta http-equiv="Content-Type"
            content="text/html; charset=windows-1252"/>
      <title>ListBooks</title>
    </head>
    <body>
      <h:form binding="#{backing_ListBooks.form1}" id="form1">
      <h2>Add Book&nbsp;&nbsp;&nbsp;</h2>
        <p>
          <h:messages errorClass="Error" infoClass="Info" layout="table"/>
        </p>
        <table cellspacing="3" cellpadding="2" border="1">
          <tr>
            <td>
              <strong>Book ID</strong>
            </td>
            <td>
              <h:inputText binding="#{backing_ListBooks.bookId}" id="bookId" size="10"
                           converter="javax.faces.Long"/>
            </td>
          </tr>
          <tr>
            <td>
              <strong>Book Name</strong>
            </td>
            <td>
              <h:inputText binding="#{backing_ListBooks.bookName}" id="bookName"/>
            </td>
          </tr>
          <tr>
            <td>
              <strong>Genre</strong>
            </td>
            <td>
              <h:inputTextarea binding="#{backing_ListBooks.genre}" id="genre"
                               cols="30" rows="2"/>
            </td>
          </tr>
        </table>
        <p>
          <h:commandButton value="Insert" binding="#{backing_ListBooks.commandButton1}"
                           id="commandButton1"
                           action="#{backing_ListBooks.insertBook}"/>
           
          <h:commandButton value="Update" binding="#{backing_ListBooks.commandButton2}"
                           id="commandButton2"
                           action="#{backing_ListBooks.updateBook}"/>
           
          <h:commandButton value="Delete" binding="#{backing_ListBooks.commandButton3}"
                           id="commandButton3"
                           action="#{backing_ListBooks.deleteBook}"/>
           
          <h:commandButton value="Refresh" binding="#{backing_ListBooks.commandButton4}"
                           id="commandButton4"
                           action="#{backing_ListBooks.clearFormFields}"/>
           
          
        </p>
        <h2>Book List</h2>
        <p>
          <h:dataTable value="#{backing_ListBooks.books}" var="books"
                       binding="#{backing_ListBooks.dataTable1}" id="dataTable1"
                       border="1" cellpadding="2" cellspacing="3">
            <!--oracle-jdev-comment:Faces.RI.DT.Class.Key:java.util.List<org.srdemo.persistence.Products>-->
            <h:column>
              <f:facet name="header">
                <h:outputText value="Book  ID"/>
              </f:facet>
              <h:outputText value="#{books.bookId}"/>
            </h:column>
            <h:column>
              <f:facet name="header">
                <h:outputText value="Name"/>
              </f:facet>
              <h:outputText value="#{books.name}"/>
            </h:column>
            <h:column>
              <f:facet name="header">
                <h:outputText value="Genre"/>
              </f:facet>
              <h:outputText value="#{books.genre}"/>
            </h:column>
          </h:dataTable>
        </p>
      </h:form>
    </body>
  </html>
</f:view>
<%-- oracle-jdev-comment:auto-binding-backing-bean-name:backing_ListBooks--%>
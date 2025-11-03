package org.demo.business;

import java.util.List;

import javax.ejb.Remote;

import org.demo.persistence.Books;

@Remote
public interface BooksFacadeEJB {
    Object mergeEntity(Object entity);

    Object persistEntity(Object entity);

    List<Books> queryBooksFindAll();

    List<Books> queryBooksFindAllByRange(int firstResult, int maxResults);

    List<Books> findBookById(Object bookId);

    List<Books> findBookByIdByRange(Object bookId, int firstResult,
                                    int maxResults);

    void removeBooks(Books books);
    
}

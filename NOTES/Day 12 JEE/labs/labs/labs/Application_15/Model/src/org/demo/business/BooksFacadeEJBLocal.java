package org.demo.business;

import java.util.List;

import javax.ejb.Local;

import org.demo.persistence.Books;

@Local
public interface BooksFacadeEJBLocal {
    Object mergeEntity(Object entity);

    Object persistEntity(Object entity);

    List<Books> queryBooksFindAll();

    List<Books> queryBooksFindAllByRange(int firstResult, int maxResults);

    List<Books> findBookById(Object bookId);

    List<Books> findBookByIdByRange(Object bookId, int firstResult,
                                    int maxResults);

    void removeBooks(Books books);
    
}

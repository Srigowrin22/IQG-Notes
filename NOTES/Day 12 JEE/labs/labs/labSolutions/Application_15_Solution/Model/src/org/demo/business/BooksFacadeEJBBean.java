package org.demo.business;

import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.demo.persistence.Books;

 import javax.ejb.TransactionAttribute;
 import javax.ejb.TransactionAttributeType;
 import javax.ejb.TransactionManagement;
 import javax.ejb.TransactionManagementType;

@Stateful(name = "BooksFacadeEJB", mappedName = "BooksFacadeSessionEJB_Solution")
   @TransactionManagement(TransactionManagementType.CONTAINER)
// @TransactionAttribute(TransactionAttributeType.REQUIRED)
   @TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Remote
@Local
public class BooksFacadeEJBBean implements BooksFacadeEJB,
                                           BooksFacadeEJBLocal {
    @PersistenceContext(unitName="Model")
    private EntityManager em;

    public BooksFacadeEJBBean() {
       
    }

    public Object mergeEntity(Object entity) {
        return em.merge(entity);
    }
    
    public Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    /** <code>select o from Books o</code> */
    public List<Books> queryBooksFindAll() {
        return em.createNamedQuery("Books.findAll").getResultList();
    }

    /** <code>select o from Books o</code> */
    public List<Books> queryBooksFindAllByRange(int firstResult,
                                                int maxResults) {
        Query query = em.createNamedQuery("Books.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select object(o) from Books o where o.bookId = :bookId</code> */
    public List<Books> findBookById(Object bookId) {
        return em.createNamedQuery("findBookById").setParameter("bookId", bookId).getResultList();
    }

    /** <code>select object(o) from Books o where o.bookId = :bookId</code> */
    public List<Books> findBookByIdByRange(Object bookId, int firstResult,
                                           int maxResults) {
        Query query =
            em.createNamedQuery("findBookById").setParameter("bookId", bookId);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public void removeBooks(Books books) {
        books = em.find(Books.class, books.getBookId());
        em.remove(books);
       
    }
    
}

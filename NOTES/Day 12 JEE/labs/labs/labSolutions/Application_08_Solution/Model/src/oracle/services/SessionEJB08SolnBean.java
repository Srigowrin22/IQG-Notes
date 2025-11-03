package oracle.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import oracle.model.Category;
import oracle.model.Person;
import oracle.model.Product;

@Stateless(name = "SessionEJB08", mappedName = "SessionEJB08_Solution")
@Remote
@Local
public class SessionEJB08SolnBean implements SessionEJB08Soln, SessionEJB08SolnLocal {
    @PersistenceContext(unitName="FOD")
    private EntityManager em;

    public SessionEJB08SolnBean() {
    }

    public Object mergeEntity(Object entity) {
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    /** <code>select o from Product o</code> */
    public List<Product> queryProductFindAll() {
        return em.createNamedQuery("Product.findAll").getResultList();
    }

    public void removeProduct(Product product) {
        product = em.find(Product.class, product.getProductId());
        em.remove(product);
    }

    /** <code>select o from Category o</code> */
    public List<Category> queryCategoryFindAll() {
        return em.createNamedQuery("Category.findAll").getResultList();
    }

    public void removeCategory(Category category) {
        category = em.find(Category.class, category.getCategoryId());
        em.remove(category);
    }

    /** <code>select o from Person o</code> */
    public List<Person> queryPersonFindAll() {
        return em.createNamedQuery("Person.findAll").getResultList();
    }
}

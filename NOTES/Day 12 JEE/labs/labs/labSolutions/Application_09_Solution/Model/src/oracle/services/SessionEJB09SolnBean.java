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

@Stateless(name = "SessionEJB09", mappedName = "SessionEJB09_Solution")
@Remote
@Local
public class SessionEJB09SolnBean implements SessionEJB09Soln, SessionEJB09SolnLocal {
    @PersistenceContext(unitName="FOD")
    private EntityManager em;

    public SessionEJB09SolnBean() {
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

    /** <code>select o from Person  o where o.id = :id</code> */
    public List<Person> queryPersonFindById(Object id) {
        return em.createNamedQuery("Person.findById").setParameter("id", id).getResultList();
    }

    /** <code>select o from Person  o where o.lastName = :lastName</code> */
    public List<Person> queryPersonFindByLastName(Object lastName) {
        return em.createNamedQuery("Person.findByLastName").setParameter("lastName", lastName).getResultList();
    }

    /** <code>select o from Product o where o.id = :id</code> */
    public List<Product> queryProductFindById(Object id) {
        return em.createNamedQuery("Product.findById").setParameter("id", id).getResultList();
    }

    /** <code>select o from Product o where o.name like :name</code> */
    public List<Product> queryProductFindByName(Object name) {
        return em.createNamedQuery("Product.findByName").setParameter("name", name).getResultList();
    }

    /** <code>select o from Category o where o.parent is null</code> */
    public List<Category> queryCategoryFindRoot() {
        return em.createNamedQuery("Category.findRoot").getResultList();
    }

    /** <code>select o from Category o where o.parent is not null</code> */
    public List<Category> queryCategoryFindLeaf() {
        return em.createNamedQuery("Category.findLeaf").getResultList();
    }
}

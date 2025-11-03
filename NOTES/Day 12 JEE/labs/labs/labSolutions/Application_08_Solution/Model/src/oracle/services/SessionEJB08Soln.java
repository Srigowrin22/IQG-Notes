package oracle.services;

import java.util.List;

import javax.ejb.Remote;

import oracle.model.Category;
import oracle.model.Person;
import oracle.model.Product;

@Remote
public interface SessionEJB08Soln {
    Object mergeEntity(Object entity);

    Object persistEntity(Object entity);

    List<Product> queryProductFindAll();

    void removeProduct(Product product);

    List<Category> queryCategoryFindAll();

    void removeCategory(Category category);

    List<Person> queryPersonFindAll();
}

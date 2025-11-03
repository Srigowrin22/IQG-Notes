package oracle.services;

import java.util.List;

import javax.ejb.Local;

import oracle.model.Category;
import oracle.model.Person;
import oracle.model.Product;

@Local
public interface SessionEJB09Local {
    Object mergeEntity(Object entity);

    Object persistEntity(Object entity);

    List<Product> queryProductFindAll();

    void removeProduct(Product product);

    List<Category> queryCategoryFindAll();

    void removeCategory(Category category);

    List<Person> queryPersonFindAll();
}

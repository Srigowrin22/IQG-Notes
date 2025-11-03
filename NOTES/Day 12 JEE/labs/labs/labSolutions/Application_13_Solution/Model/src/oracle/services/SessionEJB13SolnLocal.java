package oracle.services;

import java.util.List;

import javax.ejb.Local;

import oracle.model.Category;
import oracle.model.Person;
import oracle.model.Product;

@Local
public interface SessionEJB13SolnLocal {
    Object mergeEntity(Object entity);

    Object persistEntity(Object entity);

    List<Product> queryProductFindAll();

    void removeProduct(Product product);

    List<Category> queryCategoryFindAll();

    void removeCategory(Category category);

    List<Person> queryPersonFindAll();

    List<Person> queryPersonFindById(Object id);

    List<Person> queryPersonFindByLastName(Object lastName);

    List<Product> queryProductFindById(Object id);

    List<Product> queryProductFindByName(Object name);

    List<Category> queryCategoryFindRoot();

    List<Category> queryCategoryFindLeaf();
}

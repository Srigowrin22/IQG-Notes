package oracle.service;

import java.util.List;

import javax.ejb.Remote;

import oracle.model.Category;
import oracle.model.Product;

@Remote
public interface SessionEJB08 {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Product persistProduct(Product product);

    Product mergeProduct(Product product);

    List<Product> getProductFindAll();

    Category persistCategory(Category category);

    Category mergeCategory(Category category);

    void removeCategory(Category category);

    List<Category> getCategoryFindAll();
}

package oracle.model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "Category.findAll", query = "select o from Category o")
})
@Table(name = "CATEGORIES")
public class Category implements Serializable {
    @Column(name="CATEGORY_DESCRIPTION", length = 1000)
    private String categoryDescription;
    @Id
    @Column(name="CATEGORY_ID", nullable = false)
    private Long categoryId;
    @Column(name="CATEGORY_LOCKED_FLAG", nullable = false, length = 1)
    private String categoryLockedFlag;
    @Column(name="CATEGORY_NAME", nullable = false, length = 50)
    private String categoryName;
    @OneToMany(mappedBy = "category")
    private List<Product> productList;
    @ManyToOne
    @JoinColumn(name = "PARENT_CATEGORY_ID")
    private Category parent;
    @OneToMany(mappedBy = "parent")
    private List<Category> children;

    protected Category() {
    }

    public Category(String categoryDescription, Long categoryId,
                    String categoryLockedFlag, String categoryName,
                    Category category) {
        this.categoryDescription = categoryDescription;
        this.categoryId = categoryId;
        this.categoryLockedFlag = categoryLockedFlag;
        this.categoryName = categoryName;
        this.parent = category;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryLockedFlag() {
        return categoryLockedFlag;
    }

    public void setCategoryLockedFlag(String categoryLockedFlag) {
        this.categoryLockedFlag = categoryLockedFlag;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Product addProduct(Product product) {
        getProductList().add(product);
        product.setCategory(this);
        return product;
    }

    public Product removeProduct(Product product) {
        getProductList().remove(product);
        product.setCategory(null);
        return product;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category category) {
        this.parent = category;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> categoryList) {
        this.children = categoryList;
    }

    public Category addCategory(Category category) {
        getChildren().add(category);
        category.setParent(this);
        return category;
    }

    public Category removeCategory(Category category) {
        getChildren().remove(category);
        category.setParent(null);
        return category;
    }
}

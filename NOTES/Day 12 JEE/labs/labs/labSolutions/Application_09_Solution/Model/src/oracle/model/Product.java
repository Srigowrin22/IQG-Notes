package oracle.model;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "Product.findAll", query = "select o from Product o"),
  @NamedQuery(name = "Product.findById",  query="select o from Product o where o.productId = :id"),
  @NamedQuery(name = "Product.findByName", query = "select o from Product o where o.productName like :name")
})
@Table(name = "PRODUCTS")
public class Product implements Serializable {
    @Column(name="ADDITIONAL_INFO")
    private String additionalInfo;
    @Column(name="COST_PRICE")
    private Double costPrice;
    @Column(nullable = false)
    private String description;
    @Column(name="EXTERNAL_URL")
    private String externalUrl;
    @Column(name="LIST_PRICE", nullable = false)
    private Double listPrice;
    @Column(name="MIN_PRICE", nullable = false)
    private Double minPrice;
    
    @Id
    @Column(name="PRODUCT_ID", nullable = false)
    @GeneratedValue(generator = "PROD_SEQ", 
                    strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="PROD_SEQ",
                       sequenceName = "PRODUCT_SEQ")    
    private Long productId;
    
    @Column(name="PRODUCT_NAME", nullable = false)
    private String productName;
    @Column(name="SHIPPING_CLASS_CODE", nullable = false)
    private String shippingClassCode;
    @Column(name="SUPPLIER_ID", nullable = false)
    private Long supplierId;
    @Column(name="WARRANTY_PERIOD_MONTHS")
    private Long warrantyPeriodMonths;
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    protected Product() {
    }

    public Product(String additionalInfo, 
                   Category category, Double costPrice,
                   String description,
                   String externalUrl, 
                   Double listPrice, Double minPrice,
                   String productName,
                   String shippingClassCode, Long supplierId,
                   Long warrantyPeriodMonths) {
        this.additionalInfo = additionalInfo;
        this.category = category;
        this.costPrice = costPrice;
        this.description = description;
        this.externalUrl = externalUrl;
        this.listPrice = listPrice;
        this.minPrice = minPrice;
        this.productName = productName;
        this.shippingClassCode = shippingClassCode;
        this.supplierId = supplierId;
        this.warrantyPeriodMonths = warrantyPeriodMonths;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }


    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }


    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShippingClassCode() {
        return shippingClassCode;
    }

    public void setShippingClassCode(String shippingClassCode) {
        this.shippingClassCode = shippingClassCode;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getWarrantyPeriodMonths() {
        return warrantyPeriodMonths;
    }

    public void setWarrantyPeriodMonths(Long warrantyPeriodMonths) {
        this.warrantyPeriodMonths = warrantyPeriodMonths;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

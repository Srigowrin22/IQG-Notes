package oracle.ui.backing;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;

import oracle.adf.view.rich.component.rich.data.RichTree;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.model.Category;
import oracle.model.Product;

import oracle.services.SessionEJB11Local;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.TreeModel;

public class ProductBrowsingBean {
    private TreeModel treeModel;
    private RichForm f1;
    private RichDocument d1;
    private RichTree categoryTree;
    private RichOutputText ot1;

    public ProductBrowsingBean() {
        super();
    }
    
    public SessionEJB11Local getSessionBean() throws NamingException{
        InitialContext ic = new InitialContext();
        Object lookupObject = ic.lookup("java:comp/env/local/SessionEJB11");
        return (SessionEJB11Local) lookupObject;
    }

    public TreeModel getTreeModel() throws NamingException {
        if (treeModel == null){
            List<Category> categories = getSessionBean().queryCategoryFindRoot();
            this.treeModel = new ChildPropertyTreeModel(categories, "children");        
        }
        return treeModel;
    }

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public void setCategoryTree(RichTree categoryTree) {
        this.categoryTree = categoryTree;
    }

    public RichTree getCategoryTree() {
        return categoryTree;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }
    
    public List<Product> getAllProducts() throws NamingException{
        List<Product> products = this.getSessionBean().queryProductFindAll();
        return products;
    }
}

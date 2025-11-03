package oracle.ui.backing;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTree;

import oracle.adf.view.rich.component.rich.layout.RichPanelAccordion;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.model.Category;

import oracle.model.Product;

import oracle.services.SessionEJB11SolnLocal;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.TreeModel;

public class ProductBrowsingBean {
    private TreeModel treeModel;
    private RichForm form1;
    private RichDocument document1;
    private RichTree categoryTree;
    private RichOutputText outputText1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelAccordion panelAccordion1;
    private RichShowDetailItem showDetailItem1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelHeader panelHeader1;
    private RichTable productsTable;


    public SessionEJB11SolnLocal getSessionBean() 
                                throws NamingException {
        InitialContext ic = new InitialContext();
        Object lookupObject =
               ic.lookup("java:comp/env/ejb/local/SessionEJB11Soln");
        return (SessionEJB11SolnLocal)lookupObject;
    }

    public ProductBrowsingBean() {
    }

    public TreeModel getTreeModel() throws NamingException {
        
        if (treeModel == null)  {
            List<Category> categories = getSessionBean().queryCategoryFindRoot();    
            this.treeModel = new ChildPropertyTreeModel(categories, "children");
        }
        
        return treeModel;
    }

    public List<Product> getAllProducts() throws NamingException {
        List<Product> products = getSessionBean().queryProductFindAll();
        return products;
    }


    public void setForm1(RichForm form1) {
        this.form1 = form1;
    }

    public RichForm getForm1() {
        return form1;
    }

    public void setDocument1(RichDocument document1) {
        this.document1 = document1;
    }

    public RichDocument getDocument1() {
        return document1;
    }

    public void setCategoryTree(RichTree categoryTree) {
        this.categoryTree = categoryTree;
    }

    public RichTree getCategoryTree() {
        return categoryTree;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }

    public void setPanelAccordion1(RichPanelAccordion panelAccordion1) {
        this.panelAccordion1 = panelAccordion1;
    }

    public RichPanelAccordion getPanelAccordion1() {
        return panelAccordion1;
    }

    public void setShowDetailItem1(RichShowDetailItem showDetailItem1) {
        this.showDetailItem1 = showDetailItem1;
    }

    public RichShowDetailItem getShowDetailItem1() {
        return showDetailItem1;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelHeader1(RichPanelHeader panelHeader1) {
        this.panelHeader1 = panelHeader1;
    }

    public RichPanelHeader getPanelHeader1() {
        return panelHeader1;
    }

    public void setProductsTable(RichTable table1) {
        this.productsTable = table1;
    }

    public RichTable getProductsTable() {
        return productsTable;
    }
}

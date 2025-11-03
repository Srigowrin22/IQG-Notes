package oracle.ui.backing;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTree;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelAccordion;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.layout.RichToolbar;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.model.Category;

import oracle.model.Product;

import oracle.services.SessionEJBLocal;

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
    private RichCommandButton addProduct;
    private RichPanelHeader ph1;
    private RichPanelFormLayout pfl1;
    private RichInputText npName;
    private RichInputText npDescription;
    private RichInputText npAdditionalInfo;
    private RichInputText npListPrice;
    private RichInputText npMinPrice;
    private RichInputText npCostPrice;
    private RichToolbar t1;
    private RichCommandToolbarButton ctb1;


    public SessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        Object lookupObject = ic.lookup("java:comp/env/ejb/local/SessionEJB");
        return (SessionEJBLocal)lookupObject;
    }

    public ProductBrowsingBean() {
    }

    public TreeModel getTreeModel() throws NamingException {

        if (treeModel == null) {
            List<Category> categories =
                getSessionBean().queryCategoryFindRoot();
            this.treeModel =
                    new ChildPropertyTreeModel(categories, "children");
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

    public void setAddProduct(RichCommandButton cb1) {
        this.addProduct = cb1;
    }

    public RichCommandButton getAddProduct() {
        return addProduct;
    }

    public String addProduct() {
        String url = "images/17.jpg";
        String shippingClassCode = "CLASS1";
        Long supplierId = 123L;
        Long warrantyMonths = 12L;
        String status = null;
        Category category = null;

        Product newProduct =
            new Product((String)getNpAdditionalInfo().getValue(), category,
                        Double.parseDouble(getNpCostPrice().getValue().toString()),
                        getNpDescription().getValue().toString(), url,
                        Double.parseDouble(getNpListPrice().getValue().toString()),
                        Double.parseDouble(getNpMinPrice().getValue().toString()),
                        getNpName().getValue().toString(), shippingClassCode,
                        supplierId, warrantyMonths);

        try {
            getSessionBean().persistEntity(newProduct);
            status = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            status = "fail";
        } finally {
        }
        return status;
    }

    public void setPh1(RichPanelHeader ph1) {
        this.ph1 = ph1;
    }

    public RichPanelHeader getPh1() {
        return ph1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setNpName(RichInputText it1) {
        this.npName = it1;
    }

    public RichInputText getNpName() {
        return npName;
    }

    public void setNpDescription(RichInputText it1) {
        this.npDescription = it1;
    }

    public RichInputText getNpDescription() {
        return npDescription;
    }

    public void setNpAdditionalInfo(RichInputText it1) {
        this.npAdditionalInfo = it1;
    }

    public RichInputText getNpAdditionalInfo() {
        return npAdditionalInfo;
    }

    public void setNpListPrice(RichInputText it1) {
        this.npListPrice = it1;
    }

    public RichInputText getNpListPrice() {
        return npListPrice;
    }

    public void setNpMinPrice(RichInputText it1) {
        this.npMinPrice = it1;
    }

    public RichInputText getNpMinPrice() {
        return npMinPrice;
    }

    public void setNpCostPrice(RichInputText it1) {
        this.npCostPrice = it1;
    }

    public RichInputText getNpCostPrice() {
        return npCostPrice;
    }

    public void setT1(RichToolbar t1) {
        this.t1 = t1;
    }

    public RichToolbar getT1() {
        return t1;
    }

    public void setCtb1(RichCommandToolbarButton ctb1) {
        this.ctb1 = ctb1;
    }

    public RichCommandToolbarButton getCtb1() {
        return ctb1;
    }
}

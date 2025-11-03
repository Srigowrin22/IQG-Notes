package oracle.ui.backing;

import java.util.Collections;
import java.util.Iterator;
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

import oracle.services.SessionEJB13SolnLocal;

import org.apache.myfaces.trinidad.component.UIXGroup;
import org.apache.myfaces.trinidad.component.UIXTree;
import org.apache.myfaces.trinidad.event.SelectionEvent;
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
    private RichPanelHeader panelHeader2;
    private RichPanelFormLayout panelFormLayout1;
    private RichInputText npName;
    private RichInputText npDescription;
    private RichInputText npAdditionalInfo;
    private RichInputText npListPrice;
    private RichInputText npMinPrice;
    private RichInputText npCostPrice;
    private RichCommandButton addProductButton;
    private UIXGroup group1;
    private RichToolbar toolbar1;
    private RichCommandToolbarButton addProduct;
    private RichCommandToolbarButton cancelButton;
    
    private Category selectedCategory;


    public SessionEJB13SolnLocal getSessionBean() 
                                throws NamingException {
        InitialContext ic = new InitialContext();
        Object lookupObject =
               ic.lookup("java:comp/env/ejb/local/SessionEJB13Soln");
        return (SessionEJB13SolnLocal)lookupObject;
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

    public void setPanelHeader2(RichPanelHeader panelHeader2) {
        this.panelHeader2 = panelHeader2;
    }

    public RichPanelHeader getPanelHeader2() {
        return panelHeader2;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setNpName(RichInputText inputText1) {
        this.npName = inputText1;
    }

    public RichInputText getNpName() {
        return npName;
    }

    public void setNpDescription(RichInputText inputText1) {
        this.npDescription = inputText1;
    }

    public RichInputText getNpDescription() {
        return npDescription;
    }

    public void setNpAdditionalInfo(RichInputText inputText1) {
        this.npAdditionalInfo = inputText1;
    }

    public RichInputText getNpAdditionalInfo() {
        return npAdditionalInfo;
    }

    public void setNpListPrice(RichInputText inputText1) {
        this.npListPrice = inputText1;
    }

    public RichInputText getNpListPrice() {
        return npListPrice;
    }

    public void setNpMinPrice(RichInputText inputText1) {
        this.npMinPrice = inputText1;
    }

    public RichInputText getNpMinPrice() {
        return npMinPrice;
    }

    public void setNpCostPrice(RichInputText inputText1) {
        this.npCostPrice = inputText1;
    }

    public RichInputText getNpCostPrice() {
        return npCostPrice;
    }


    public boolean isAddProductButtonDisabled () {
        return (selectedCategory == null || !selectedCategory.getChildren().isEmpty());
    }

    public String addProduct() {
        String url = "images/17.jpg";
        String shippingClassCode = "CLASS1";
        Long supplierId = 123L;
        Long warrantyMonths = 12L;
        String status = null;
        Category category = selectedCategory;
        
        Product newProduct = new Product(
                                 (String)getNpAdditionalInfo().getValue()
                                 , category
                                 , Double.parseDouble(getNpCostPrice().getValue().toString())
                                 , getNpDescription().getValue().toString()
                                 , url
                                 , Double.parseDouble(getNpListPrice().getValue().toString())
                                 , Double.parseDouble(getNpMinPrice().getValue().toString())
                                 , getNpName().getValue().toString()
                                 , shippingClassCode
                                 , supplierId
                                 , warrantyMonths                            
                                 );
        
        try {
            selectedCategory.addProduct(newProduct);
            getSessionBean().persistEntity(newProduct);
            resetAddProductFields();
            status = "success";
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
            status = "fail";
        } finally {
        }
        return status;
    }
    
    private void resetAddProductFields() { 
          getNpName().resetValue();
          getNpDescription().resetValue();
          getNpAdditionalInfo().resetValue();
          getNpListPrice().resetValue();
          getNpMinPrice().resetValue();
          getNpCostPrice().resetValue();
    }
    
    public Product getSelectedProduct () {
        return (Product)getProductsTable().getSelectedRowData();

    }    

    public String updateProduct() {
       String status = null;
       try {
     this.getSessionBean().mergeEntity(this.getSelectedProduct());
          status = "success";
       } catch (NamingException e) {
                 e.printStackTrace();
                 status = "success";
         }
       return status;
    }



    public void setAddProductButton(RichCommandButton commandButton1) {
        this.addProductButton = commandButton1;
    }

    public RichCommandButton getAddProductButton() {
        return addProductButton;
    }

    public void setGroup1(UIXGroup group1) {
        this.group1 = group1;
    }

    public UIXGroup getGroup1() {
        return group1;
    }

    public void setToolbar1(RichToolbar toolbar1) {
        this.toolbar1 = toolbar1;
    }

    public RichToolbar getToolbar1() {
        return toolbar1;
    }


    public void setAddProduct(RichCommandToolbarButton addProduct) {
        this.addProduct = addProduct;
    }

    public RichCommandToolbarButton getAddProduct() {
        return addProduct;
    }

    public void setCancelButton(RichCommandToolbarButton commandToolbarButton1) {
        this.cancelButton = commandToolbarButton1;
    }

    public RichCommandToolbarButton getCancelButton() {
        return cancelButton;
    }

    public String cancelButton_action() {
        resetAddProductFields();
        return "success";
    }

    public void categoryTreeSelectionListener(SelectionEvent selectionEvent) {
        // Add event code here...
        UIXTree treeTable = getCategoryTree();
        Iterator selection = treeTable.getSelectedRowKeys().iterator();
        for (;selection.hasNext();) {
            Object rowKey = selection.next();
            treeTable.setRowKey(rowKey);
            this.selectedCategory = (Category)treeTable.getRowData();   
        }
//        if (selectedCategory != null && selectedCategory.getChildren().size()  > 0) {
//            System.out.println("size of children " + selectedCategory.getChildren().isEmpty() ());
//        }
    }
    
    public List<Product> getProductsForSelectedCategory() throws NamingException {
        if (this.selectedCategory != null) {
            return this.selectedCategory.getProductList();
        } else {
            return Collections.emptyList();
        }
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }
}

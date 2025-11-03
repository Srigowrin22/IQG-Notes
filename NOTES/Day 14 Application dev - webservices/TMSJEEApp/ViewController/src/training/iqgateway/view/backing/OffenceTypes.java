package training.iqgateway.view.backing;

import java.util.List;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlForm;

import javax.faces.component.html.HtmlOutputText;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.model.OffenceTypesEO;
import training.iqgateway.services.ClerkSessionEJBLocal;

public class OffenceTypes {
    private HtmlForm ownerDetailsForm;
    private HtmlDataTable dataTable1;
    private HtmlOutputText outputText1;
    private HtmlOutputText outputText2;
    private HtmlOutputText outputText3;
    private HtmlOutputText outputText4;

    public void setOwnerDetailsForm(HtmlForm ownerDetailsForm) {
        this.ownerDetailsForm = ownerDetailsForm;
    }

    public HtmlForm getOwnerDetailsForm() {
        return ownerDetailsForm;
    }

    public ClerkSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        Object lookupObject =
            ic.lookup("java:comp/env/ejb/local/ClerkSessionEJB");
        return (ClerkSessionEJBLocal)lookupObject;
    }

    public List<OffenceTypesEO> getAllOffenceTypesEO() {
        try {
            List<OffenceTypesEO> offenceTypesEO = getSessionBean().getOffenceTypesEOFindAll();
            return offenceTypesEO;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setDataTable1(HtmlDataTable dataTable1) {
        this.dataTable1 = dataTable1;
    }

    public HtmlDataTable getDataTable1() {
        return dataTable1;
    }

    public void setOutputText1(HtmlOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public HtmlOutputText getOutputText1() {
        return outputText1;
    }

    public void setOutputText2(HtmlOutputText outputText2) {
        this.outputText2 = outputText2;
    }

    public HtmlOutputText getOutputText2() {
        return outputText2;
    }

    public void setOutputText3(HtmlOutputText outputText3) {
        this.outputText3 = outputText3;
    }

    public HtmlOutputText getOutputText3() {
        return outputText3;
    }

    public void setOutputText4(HtmlOutputText outputText4) {
        this.outputText4 = outputText4;
    }

    public HtmlOutputText getOutputText4() {
        return outputText4;
    }
}

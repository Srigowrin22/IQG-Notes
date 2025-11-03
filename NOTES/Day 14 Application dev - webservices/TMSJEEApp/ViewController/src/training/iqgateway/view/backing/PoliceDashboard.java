package training.iqgateway.view.backing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlForm;

import javax.faces.component.html.HtmlOutputText;

import javax.faces.context.FacesContext;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.model.AdminEO;
import training.iqgateway.services.ClerkSessionEJBLocal;

public class PoliceDashboard {
    private HtmlForm form1;

    private String designationId;
    private String name;

    private String outsourceQuote;

    private HtmlDataTable dataTable1;
    private HtmlOutputText outputText1;
    private HtmlOutputText outputText2;
    private HtmlOutputText outputText3;
    private HtmlOutputText outputText4;
    private HtmlOutputText outputText5;
    private HtmlOutputText outputText6;
    private HtmlOutputText outputText7;

    public ClerkSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        Object lookupObject =
            ic.lookup("java:comp/env/ejb/local/ClerkSessionEJB");
        return (ClerkSessionEJBLocal)lookupObject;
    }

    private AdminEO adminEO =
        (AdminEO)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInAdmin");

    public AdminEO getLoggedInAdmin() {
        return adminEO;
    }

    public void setForm1(HtmlForm form1) {
        this.form1 = form1;
    }

    public HtmlForm getForm1() {
        return form1;
    }

    public void setDesignationId(String designationId) {
        this.designationId = designationId;
    }

    public String getDesignationId() {
        this.designationId = adminEO.getDesignationId();
        return designationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        this.name = adminEO.getName();
        return name;
    }

    public String getOutsourceQuote() {
        if (outsourceQuote == null) {
            fetchQuoteFromAPI();
        }
        return outsourceQuote;
    }

    private void fetchQuoteFromAPI() {
        try {
            URL url = new URL("https://coda.io/@mark-davis/random-quote");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);

            BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream(),
                                                         "UTF-8"));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Very basic JSON parsing (not robust, but works for this simple structure)
            String json = response.toString();
            String quote = "";
            String author = "";

            int qIndex = json.indexOf("\"q\":\"");
            if (qIndex != -1) {
                int qStart = qIndex + 5;
                int qEnd = json.indexOf("\"", qStart);
                quote = json.substring(qStart, qEnd);
            }

            int aIndex = json.indexOf("\"a\":\"");
            if (aIndex != -1) {
                int aStart = aIndex + 5;
                int aEnd = json.indexOf("\"", aStart);
                author = json.substring(aStart, aEnd);
            }

            if (!quote.isEmpty() && !author.isEmpty()) {
                outsourceQuote = quote + " — " + author;
            } else {
                outsourceQuote =
                        "Unable to fetch quote at the moment!!!!!!!!!!!.";
            }
        } catch (Exception e) {
            outsourceQuote = "Unable to fetch quote at the moment.";
        }
    }

    public String logout_action() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            javax.servlet.http.HttpSession session =
                (javax.servlet.http.HttpSession) facesContext.getExternalContext().getSession(false);
            if (session != null) {
                session.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "exit";
    }

    public String home_action() {
        return "clerk";
    }

    public String raiseOffence_action() {
        return "raise";
    }

    public String viewOwner_action() {
        return "owner";
    }

    public String pendingOffences_action() {
        return "pending";
    }

    public String offenceTypes_action() {
        return "offence";
    }

    public String generateReports_action() {
        return "report";
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

    public void setOutputText5(HtmlOutputText outputText5) {
        this.outputText5 = outputText5;
    }

    public HtmlOutputText getOutputText5() {
        return outputText5;
    }

    public void setOutputText6(HtmlOutputText outputText6) {
        this.outputText6 = outputText6;
    }

    public HtmlOutputText getOutputText6() {
        return outputText6;
    }

    public void setOutputText7(HtmlOutputText outputText7) {
        this.outputText7 = outputText7;
    }

    public HtmlOutputText getOutputText7() {
        return outputText7;
    }
}

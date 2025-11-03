package training.iqgateway.view.backing;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;

import javax.faces.context.FacesContext;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.model.AdminEO;
import training.iqgateway.services.AdminSessionEJBLocal;

public class AdminDashboard {
    private HtmlForm form1;
    private HtmlOutputLabel dashboard;
    private HtmlInputText desigID;
    private HtmlCommandButton home;
    private HtmlCommandButton admin;
    private HtmlCommandButton role;
    private HtmlCommandButton logout;

    private String designationId;
    private String name;
    private HtmlForm form2;


    public AdminSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        Object lookupObject =
            ic.lookup("java:comp/env/ejb/local/AdminSessionEJB");
        return (AdminSessionEJBLocal)lookupObject;
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

    public void setDashboard(HtmlOutputLabel outputLabel1) {
        this.dashboard = outputLabel1;
    }

    public HtmlOutputLabel getDashboard() {
        return dashboard;
    }

    public void setDesigID(HtmlInputText inputText1) {
        this.desigID = inputText1;
    }

    public HtmlInputText getDesigID() {
        return desigID;
    }

    public void setHome(HtmlCommandButton commandButton1) {
        this.home = commandButton1;
    }

    public HtmlCommandButton getHome() {
        return home;
    }

    public void setAdmin(HtmlCommandButton commandButton1) {
        this.admin = commandButton1;
    }

    public HtmlCommandButton getAdmin() {
        return admin;
    }

    public void setRole(HtmlCommandButton commandButton1) {
        this.role = commandButton1;
    }

    public HtmlCommandButton getRole() {
        return role;
    }


    public void setLogout(HtmlCommandButton commandButton1) {
        this.logout = commandButton1;
    }

    public HtmlCommandButton getLogout() {
        return logout;
    }

    public String getDesignationId() throws NamingException {
        this.designationId = adminEO.getDesignationId();
        return designationId;
    }

    public String getName() throws NamingException {
        this.name = adminEO.getName();
        return name;
    }

    public String logout_action() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            javax.servlet.http.HttpSession session =
                (javax.servlet.http.HttpSession)facesContext.getExternalContext().getSession(false);
            if (session != null) {
                session.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "exit";
    }

    public String home_action() {
        return "home";
    }

    public String role_action() {
        return "role";
    }

    public String admin_action() {
        return "admin";
    }

    private String outsourceQuote;

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

    public void setForm2(HtmlForm form2) {
        this.form2 = form2;
    }

    public HtmlForm getForm2() {
        return form2;
    }
}



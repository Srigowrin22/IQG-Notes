package training.iqgateway.view.backing;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;

import javax.faces.context.FacesContext;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.model.AdminEO;
import training.iqgateway.model.RegistrationEO;
import training.iqgateway.services.RTOSessionEJBLocal;

public class RTODashboard {
    private HtmlForm form1;
    private HtmlOutputLabel dashboard;
    private HtmlInputText officeID;
    private HtmlCommandButton home;
    private HtmlCommandButton vehicle;
    private HtmlCommandButton offenceTypes;
    private HtmlCommandButton clearOffence;
    private HtmlCommandButton registration;
    private HtmlCommandButton transferVehicle;
    private HtmlCommandButton logout;

    private String designationId;
    private String name;
    private HtmlForm form2;

    public RTOSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        Object lookupObject =
            ic.lookup("java:comp/env/ejb/local/RTOSessionEJB");
        return (RTOSessionEJBLocal)lookupObject;
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

    public void setOfficeID(HtmlInputText inputText1) {
        this.officeID = inputText1;
    }

    public HtmlInputText getOfficeID() {
        return officeID;
    }

    public void setHome(HtmlCommandButton commandButton1) {
        this.home = commandButton1;
    }

    public HtmlCommandButton getHome() {
        return home;
    }

    public void setVehicle(HtmlCommandButton commandButton1) {
        this.vehicle = commandButton1;
    }

    public HtmlCommandButton getVehicle() {
        return vehicle;
    }

    public void setOffenceTypes(HtmlCommandButton commandButton1) {
        this.offenceTypes = commandButton1;
    }

    public HtmlCommandButton getOffenceTypes() {
        return offenceTypes;
    }

    public void setClearOffence(HtmlCommandButton commandButton1) {
        this.clearOffence = commandButton1;
    }

    public HtmlCommandButton getClearOffence() {
        return clearOffence;
    }

    public void setRegistration(HtmlCommandButton commandButton1) {
        this.registration = commandButton1;
    }

    public HtmlCommandButton getRegistration() {
        return registration;
    }

    public void setTransferVehicle(HtmlCommandButton commandButton1) {
        this.transferVehicle = commandButton1;
    }

    public HtmlCommandButton getTransferVehicle() {
        return transferVehicle;
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
        return "RTO";
    }

    public String vehicle_action() {
        return "vehicle";
    }

    public String offenceTypes_action() {
        return "offence";
    }

    public String registration_action() {
        return "regis";
    }

    public String transferVehicle_action() {
        return "transfer";
    }

    private String quoteOfTheDay;

    public String getQuoteOfTheDay() {
        if (quoteOfTheDay == null) {
            fetchQuoteFromAPI();
        }
        return quoteOfTheDay;
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
                quoteOfTheDay = quote + " — " + author;
            } else {
                quoteOfTheDay = "Unable to fetch quote at the moment.";
            }
        } catch (Exception e) {
            quoteOfTheDay = "Unable to fetch quote at the moment.";
        }
    }

    public void setForm2(HtmlForm form2) {
        this.form2 = form2;
    }

    public HtmlForm getForm2() {
        return form2;
    }

}

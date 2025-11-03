package training.iqgateway.view.backing;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.faces.component.html.HtmlForm;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;

import javax.faces.context.FacesContext;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.model.OwnerEO;
import training.iqgateway.services.OwnerSessionEJBLocal;


public class OwnerDashboard {

    private HtmlForm form1;
    private HtmlOutputLabel dashboard;
    private HtmlInputText name; // UI component reference (optional)
    private HtmlCommandButton home;
    private HtmlCommandButton viewVehicle;
    private HtmlCommandButton payOffence;
    private HtmlCommandButton profile;
    private HtmlCommandButton logout;

    private OwnerEO ownerEO =
        (OwnerEO)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInOwner");


    private String ownerName;
    private HtmlForm form2;

    public OwnerDashboard() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInOwner") == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("OwnerLogin.jspx");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // EJB lookup for OwnerSessionEJBLocal

    public OwnerSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        Object lookupObject =
            ic.lookup("java:comp/env/ejb/local/OwnerSessionEJB");
        return (OwnerSessionEJBLocal)lookupObject;
    }

//     Getter to retrieve the whole OwnerEO object from session

    public OwnerEO getLoggedInOwner() {
        return (OwnerEO)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInOwner");
    }

    // You can add convenience getters for individual attributes if you want

    public String getOwnerName() {
        return ownerEO.getOwnerName();
    }

    // Getters and setters for JSF components and properties

    public void setForm1(HtmlForm form1) {
        this.form1 = form1;
    }

    public HtmlForm getForm1() {
        return form1;
    }

    public void setDashboard(HtmlOutputLabel dashboard) {
        this.dashboard = dashboard;
    }

    public HtmlOutputLabel getDashboard() {
        return dashboard;
    }

    public void setName(HtmlInputText name) {
        this.name = name;
    }

    public HtmlInputText getName() {
        return name;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


    public void setHome(HtmlCommandButton home) {
        this.home = home;
    }

    public HtmlCommandButton getHome() {
        return home;
    }

    public void setViewVehicle(HtmlCommandButton viewVehicle) {
        this.viewVehicle = viewVehicle;
    }

    public HtmlCommandButton getViewVehicle() {
        return viewVehicle;
    }

    public void setPayOffence(HtmlCommandButton payOffence) {
        this.payOffence = payOffence;
    }

    public HtmlCommandButton getPayOffence() {
        return payOffence;
    }

    public void setProfile(HtmlCommandButton profile) {
        this.profile = profile;
    }

    public HtmlCommandButton getProfile() {
        return profile;
    }

    public void setLogout(HtmlCommandButton logout) {
        this.logout = logout;
    }

    public HtmlCommandButton getLogout() {
        return logout;
    }

    // Navigation actions

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
        return "owner";
    }

    public String viewVehicle_action() {
        return "vehicle";
    }

    public String payOffence_action() {
        return "clear";
    }

    public String profile_action() {
        return "profile";
    }

    // Quote of the Day feature
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

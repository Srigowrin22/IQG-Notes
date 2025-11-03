package training.iqgateway.view.backing;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;

import javax.faces.context.FacesContext;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.http.HttpSession;

import training.iqgateway.model.OwnerEO;
import training.iqgateway.services.OwnerSessionEJBLocal;
import training.iqgateway.utils.Format;

public class OwnerLogin {

    // --- JSF Component Bindings for Signup ---
       private HtmlForm signupForm;
       private HtmlInputText ownerNameFLD;
       private HtmlInputText ownerAadharFLD;
       private HtmlInputText phoneFLD;
       private HtmlInputText addressFLD;
       private HtmlSelectOneMenu genderFLD;
       private HtmlInputText panCardFLD;
       private HtmlInputSecret passwordFLD;
       private HtmlInputSecret confirmPasswordFLD;

       // --- JSF Component Bindings for Login ---
       private HtmlForm loginForm;
       private HtmlInputText loginAadharFLD;
       private HtmlInputText loginNameFLD;
       private HtmlInputSecret loginPasswordFLD;

       // Toggle between login and signup
       private boolean loginMode = false; // false = signup, true = login

       // Format utility for validation (assumed available)
       private Format formatUtil = new Format();

       // --- Getters and Setters for all components ---

       public HtmlForm getSignupForm() { return signupForm; }
       public void setSignupForm(HtmlForm signupForm) { this.signupForm = signupForm; }

       public HtmlInputText getOwnerNameFLD() { return ownerNameFLD; }
       public void setOwnerNameFLD(HtmlInputText ownerNameFLD) { this.ownerNameFLD = ownerNameFLD; }

       public HtmlInputText getOwnerAadharFLD() { return ownerAadharFLD; }
       public void setOwnerAadharFLD(HtmlInputText ownerAadharFLD) { this.ownerAadharFLD = ownerAadharFLD; }

       public HtmlInputText getPhoneFLD() { return phoneFLD; }
       public void setPhoneFLD(HtmlInputText phoneFLD) { this.phoneFLD = phoneFLD; }

       public HtmlInputText getAddressFLD() { return addressFLD; }
       public void setAddressFLD(HtmlInputText addressFLD) { this.addressFLD = addressFLD; }

       public HtmlSelectOneMenu getGenderFLD() { return genderFLD; }
       public void setGenderFLD(HtmlSelectOneMenu genderFLD) { this.genderFLD = genderFLD; }

       public HtmlInputText getPanCardFLD() { return panCardFLD; }
       public void setPanCardFLD(HtmlInputText panCardFLD) { this.panCardFLD = panCardFLD; }

       public HtmlInputSecret getPasswordFLD() { return passwordFLD; }
       public void setPasswordFLD(HtmlInputSecret passwordFLD) { this.passwordFLD = passwordFLD; }

       public HtmlInputSecret getConfirmPasswordFLD() { return confirmPasswordFLD; }
       public void setConfirmPasswordFLD(HtmlInputSecret confirmPasswordFLD) { this.confirmPasswordFLD = confirmPasswordFLD; }

       public HtmlForm getLoginForm() { return loginForm; }
       public void setLoginForm(HtmlForm loginForm) { this.loginForm = loginForm; }

       public HtmlInputText getLoginAadharFLD() { return loginAadharFLD; }
       public void setLoginAadharFLD(HtmlInputText loginAadharFLD) { this.loginAadharFLD = loginAadharFLD; }

       public HtmlInputText getLoginNameFLD() { return loginNameFLD; }
       public void setLoginNameFLD(HtmlInputText loginNameFLD) { this.loginNameFLD = loginNameFLD; }

       public HtmlInputSecret getLoginPasswordFLD() { return loginPasswordFLD; }
       public void setLoginPasswordFLD(HtmlInputSecret loginPasswordFLD) { this.loginPasswordFLD = loginPasswordFLD; }

       public boolean isLoginMode() { return loginMode; }
       public void setLoginMode(boolean loginMode) { this.loginMode = loginMode; }

       // --- EJB Lookup ---
       public OwnerSessionEJBLocal getSessionBean() throws NamingException {
           InitialContext ic = new InitialContext();
           Object lookup = ic.lookup("java:comp/env/ejb/local/OwnerSessionEJB");
           return (OwnerSessionEJBLocal) lookup;
       }

    public void switchToLogin() {
        loginMode = true;  // Show login panel
        clearFields();
    }

    public void switchToSignup() {
        loginMode = false; // Show signup panel
        clearFields();
    }
//    private boolean loginMode = false;
//
//    public boolean isLoginMode() {
//        return loginMode;
//    }
//
//    public void setLoginMode(boolean loginMode) {
//        this.loginMode = loginMode;
//    }
    private void clearFields() {
        // Clear all form fields to avoid stale data
        // For example:
        ownerNameFLD = null;
        ownerAadharFLD = null;
        phoneFLD = null;
        addressFLD = null;
        genderFLD = null;
        panCardFLD = null;
        passwordFLD = null;
        confirmPasswordFLD = null;
        loginAadharFLD = null;
        loginNameFLD = null;
        loginPasswordFLD = null;
    }


       // --- Signup action ---
       public String signupAction() {
           try {
               String ownerName = (String) ownerNameFLD.getValue();
               String ownerAadhar = (String) ownerAadharFLD.getValue();
               String phoneStr = (String) phoneFLD.getValue();
               String address = (String) addressFLD.getValue();
               String gender = (String) (genderFLD.getValue() != null ? genderFLD.getValue() : "");
               String panCard = (String) panCardFLD.getValue();
               String password = (String) passwordFLD.getValue();
               String confirmPassword = (String) confirmPasswordFLD.getValue();

               // Validate inputs
               if (ownerName == null || !formatUtil.isOnlyLetters(ownerName)) {
                   addErrorMessage("Name must contain at least 3 letters and only alphabets.");
                   return null;
               }

               String aadharValidationMsg = formatUtil.validateAadhar(ownerAadhar);
               if (aadharValidationMsg != null) {
                   addErrorMessage(aadharValidationMsg);
                   return null;
               }

               long phoneLong;
               try {
                   phoneLong = Long.parseLong(phoneStr);
               } catch (NumberFormatException e) {
                   addErrorMessage("Phone must be numeric.");
                   return null;
               }

               String phoneValidationMsg = formatUtil.validatePhone(phoneLong);
               if (phoneValidationMsg != null) {
                   addErrorMessage(phoneValidationMsg);
                   return null;
               }

               if (password == null || !password.matches("^[a-zA-Z0-9]{6,}$")) {
                   addErrorMessage("Password must be at least 6 alphanumeric characters.");
                   return null;
               }

               if (confirmPassword == null || !password.equals(confirmPassword)) {
                   addErrorMessage("Password and Confirm Password do not match.");
                   return null;
               }

               // Create entity and persist
               OwnerEO newOwner = new OwnerEO();
               newOwner.setOwnerName(ownerName);
               newOwner.setOwnerAadhar(ownerAadhar);
               newOwner.setPhone(phoneLong);
               newOwner.setAddress(address);
               newOwner.setGender(gender);
               newOwner.setPanCard(panCard);
               newOwner.setPassword(password);

               getSessionBean().persistOwnerEO(newOwner);

               addInfoMessage("Owner registered successfully! Please login.");

               // Switch to login form
               loginMode = true;

               // Clear signup form fields
               clearSignupFields();

               return null;

           } catch (NamingException e) {
               addErrorMessage("System error: Unable to access database.");
               e.printStackTrace();
               return null;
           } catch (Exception e) {
               addErrorMessage("Error during registration: " + e.getMessage());
               e.printStackTrace();
               return null;
           }
       }

       // --- Login action ---
       public String loginAction() {
           try {
               String loginAadhar = (String) loginAadharFLD.getValue();
               String loginName = (String) loginNameFLD.getValue();
               String loginPassword = (String) loginPasswordFLD.getValue();

               if (loginAadhar == null || loginName == null || loginPassword == null ||
                   loginAadhar.trim().isEmpty() || loginName.trim().isEmpty() || loginPassword.trim().isEmpty()) {
                   addErrorMessage("All fields are required.");
                   return null;
               }

               OwnerSessionEJBLocal session = getSessionBean();

               boolean valid = session.login(loginAadhar.trim(), loginName.trim(), loginPassword.trim());

               if (!valid) {
                   addErrorMessage("Invalid credentials.");
                   clearLoginFields();
                   return null;
               }

               OwnerEO loggedInOwner = session.findOwnerByAadhar(loginAadhar.trim());

               if (loggedInOwner == null) {
                   addErrorMessage("Owner not found.");
                   clearLoginFields();
                   return null;
               }

               // Store in session
               HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                       .getExternalContext().getSession(true);
               httpSession.setAttribute("loggedInOwner", loggedInOwner);

              return "success";  // Redirect done

           } catch (NamingException e) {
               addErrorMessage("System error: " + e.getMessage());
               e.printStackTrace();
               return null;
           }
       }

       // --- Helper methods ---

       private void addErrorMessage(String msg) {
           FacesContext.getCurrentInstance().addMessage(null,
               new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
       }

       private void addInfoMessage(String msg) {
           FacesContext.getCurrentInstance().addMessage(null,
               new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
       }

       private void clearSignupFields() {
           ownerNameFLD.setValue(null);
           ownerAadharFLD.setValue(null);
           phoneFLD.setValue(null);
           addressFLD.setValue(null);
           genderFLD.setValue(null);
           panCardFLD.setValue(null);
           passwordFLD.setValue(null);
           confirmPasswordFLD.setValue(null);
       }

       private void clearLoginFields() {
           loginAadharFLD.setValue(null);
           loginNameFLD.setValue(null);
           loginPasswordFLD.setValue(null);
       }
}

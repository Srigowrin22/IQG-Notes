package training.iqgateway.view.backing;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.model.AdminEO;
import training.iqgateway.services.AdminSessionEJBLocal;
import training.iqgateway.utils.Format;

public class AdminLogin {

    private Format format = new Format();

    // --- Form fields ---
    private String loginDesignationId;
    private String loginPassword;

    private String signupOldPassword;
    private String signupDesignationId;
    private String signupPassword;
    private String signupConfirmPassword;

    private boolean loginMode = true; // true = login card, false = signup card

    private String message; // For user feedback

    // --- EJB Lookup ---

    private AdminSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        return (AdminSessionEJBLocal)ic.lookup("java:comp/env/ejb/local/AdminSessionEJB");
    }

    // --- Login Action ---

    public String loginAction() {
        message = null;
        try {
            if (loginDesignationId == null ||
                loginDesignationId.trim().isEmpty() || loginPassword == null ||
                loginPassword.trim().isEmpty()) {
                message = "Please enter Designation ID and Password.";
                return null;
            }
            if (!format.isValidPassword(loginPassword)) {
                message =
                        "Password must be at least 6 characters, with both letters and digits.";
                return null;
            }
            AdminEO admin =
                getSessionBean().findAdminByDesigID(loginDesignationId.trim());
            if (admin == null) {
                message = "Admin not found. Please signup first.";
                return null;
            }
            if (admin.getSignup() == null || admin.getSignup() != 1L) {
                message =
                        "Admin not signed up. Please signup and set your password.";
                return null;
            }
            if (!loginPassword.equals(admin.getPassword())) {
                message = "Invalid password.";
                return null;
            }

            // Store admin in session for dashboard use
            javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInAdmin",
                                                                                                           admin);

            // Redirect based on DesignationID prefix
            String prefix = loginDesignationId.trim().toUpperCase();
            if (prefix.startsWith("RTO")) {
                return "RTOlogin";
            } else if (prefix.startsWith("CLK")) {
                return "CLKlogin";
            } else if (prefix.startsWith("PLC")) {
                return "PLClogin";
            } else if (prefix.startsWith("ADM")) {
                return "ADMlogin";
            } else {
                message = "Unknown Designation ID prefix.";
                return null;
            }
        } catch (Exception e) {
            message = "Login error: " + e.getMessage();
            return null;
        }
    }

    // --- Signup Action ---

    public String signupAction() {
        message = null;
        try {
            if (signupDesignationId == null ||
                signupDesignationId.trim().isEmpty() ||
                signupOldPassword == null ||
                signupOldPassword.trim().isEmpty() || signupPassword == null ||
                signupPassword.trim().isEmpty() ||
                signupConfirmPassword == null ||
                signupConfirmPassword.trim().isEmpty()) {
                message = "All fields are required.";
                return null;
            }
            if (!signupPassword.equals(signupConfirmPassword)) {
                message = "Passwords do not match.";
                return null;
            }
            if (!format.isValidPassword(signupPassword)) {
                message =
                        "Password must be at least 6 characters, with both letters and digits.";
                return null;
            }
            AdminEO admin =
                getSessionBean().findAdminByDesigID(signupDesignationId.trim());
            if (admin == null) {
                message =
                        "Admin not found. Contact super admin to create account.";
                return null;
            }
            // Check old password matches
            if (!signupOldPassword.equals(admin.getPassword())) {
                message = "Old password is incorrect.";
                return null;
            }
            // Only allow signup if not already signed up
            if (admin.getSignup() != null && admin.getSignup() == 1L) {
                message = "Admin already signed up. Please login.";
                loginMode = true;
                return null;
            }
            // Update password and set signup=1
            int updated =
                getSessionBean().authorizeAdmin(signupDesignationId.trim(),
                                                admin.getPassword(),
                                                signupPassword);
            if (updated > 0) {
                message = "Signup successful! Please login.";
                loginMode = true;
                signupDesignationId = null;
                signupOldPassword = null;
                signupPassword = null;
                signupConfirmPassword = null;
            } else {
                message = "Signup failed. Please check your credentials.";
            }
        } catch (Exception e) {
            message = "Signup error: " + e.getMessage();
        }
        return null;
    }


    // --- Switch between Login and Signup ---

    public String switchToSignup() {
        loginMode = false;
        loginDesignationId = null;
        loginPassword = null;
        message = null;
        return null;
    }

    public String switchToLogin() {
        loginMode = true;
        signupDesignationId = null;
        signupPassword = null;
        signupConfirmPassword = null;
        message = null;
        return null;
    }

    // --- Getters and Setters ---

    public String getSignupOldPassword() {
        return signupOldPassword;
    }

    public void setSignupOldPassword(String signupOldPassword) {
        this.signupOldPassword = signupOldPassword;
    }

    public String getLoginDesignationId() {
        return loginDesignationId;
    }

    public void setLoginDesignationId(String loginDesignationId) {
        this.loginDesignationId = loginDesignationId;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getSignupDesignationId() {
        return signupDesignationId;
    }

    public void setSignupDesignationId(String signupDesignationId) {
        this.signupDesignationId = signupDesignationId;
    }

    public String getSignupPassword() {
        return signupPassword;
    }

    public void setSignupPassword(String signupPassword) {
        this.signupPassword = signupPassword;
    }

    public String getSignupConfirmPassword() {
        return signupConfirmPassword;
    }

    public void setSignupConfirmPassword(String signupConfirmPassword) {
        this.signupConfirmPassword = signupConfirmPassword;
    }

    public boolean isLoginMode() {
        return loginMode;
    }

    public void setLoginMode(boolean loginMode) {
        this.loginMode = loginMode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

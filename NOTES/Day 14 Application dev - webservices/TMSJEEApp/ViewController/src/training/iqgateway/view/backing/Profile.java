package training.iqgateway.view.backing;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.model.OwnerEO;
import training.iqgateway.services.OwnerSessionEJBLocal;

public class Profile {

    private boolean editMode = false;

    // Always fetch from session to avoid issues with request scope
    public OwnerEO getLoggedInOwner() {
        Object ownerObj = FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("loggedInOwner");
        if (ownerObj instanceof OwnerEO) {
            return (OwnerEO) ownerObj;
        }
        return null; // or throw, or redirect to login page
    }

    public void setLoggedInOwner(OwnerEO owner) {
        // This setter is not used by JSF unless you explicitly bind to it for forms
        // You can leave it empty or assign to session if you want
    }

    public boolean isEditMode() { return editMode; }
    public void setEditMode(boolean editMode) { this.editMode = editMode; }

    // --- EJB Lookup ---
    private OwnerSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        return (OwnerSessionEJBLocal) ic.lookup("java:comp/env/ejb/local/OwnerSessionEJB");
    }

    // --- Action Methods ---

    public String enableEditProfile() {
        this.editMode = true;
        return null;
    }

    public String cancelEditProfile() {
        this.editMode = false;
        // Reload from DB/session to discard changes
        try {
            OwnerEO owner = getLoggedInOwner();
            if (owner != null) {
                OwnerEO fresh = getSessionBean().findOwnerByAadhar(owner.getOwnerAadhar());
                if (fresh != null) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                            .put("loggedInOwner", fresh);
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Could not reload profile.", null));
        }
        return null;
    }

    public String saveProfile() {
        try {
            OwnerEO owner = getLoggedInOwner();
            if (owner != null) {
                getSessionBean().mergeOwnerEO(owner);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile updated successfully.", null));
                // Update session
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .put("loggedInOwner", owner);
            }
            this.editMode = false;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error saving profile.", null));
        }
        return null;
    }

    public String hideProfileCard() {
        // For a separate page, navigate away
        return "owner"; // or your dashboard/home page outcome
    }
}

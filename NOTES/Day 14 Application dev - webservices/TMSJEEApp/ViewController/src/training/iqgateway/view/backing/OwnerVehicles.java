package training.iqgateway.view.backing;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlForm;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.model.OwnerEO;
import training.iqgateway.model.RegistrationEO;
import training.iqgateway.model.VehicleEO;
import training.iqgateway.services.OwnerSessionEJBLocal;


public class OwnerVehicles {

    private HtmlForm form1;

    private OwnerSessionEJBLocal sessionBean;

    private OwnerEO owner =
        (OwnerEO)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInOwner");

    private List<RegistrationEO> registrationList;

    private List<SelectItem> registrationSelectItems;

    private String selectedRegistrationId;

    private RegistrationEO selectedRegistration;

    private VehicleEO selectedVehicle;

    private String message; // Message to show on UI

    // Lazy initialization of session bean

    private OwnerSessionEJBLocal getSession() throws NamingException {
        if (sessionBean == null) {
            InitialContext ic = new InitialContext();
            sessionBean =
                    (OwnerSessionEJBLocal)ic.lookup("java:comp/env/ejb/local/OwnerSessionEJB");
        }
        return sessionBean;
    }


    public OwnerEO getLoggedInOwner() {
        return (OwnerEO)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInOwner");
    }

    // Lazy load owner and registrations when first accessed

    public List<RegistrationEO> getRegistrationList() {
        if (registrationList == null) {
            try {
                OwnerSessionEJBLocal session = getSession();

                // dummy owner aadhar
                if (owner != null) {
                    registrationList =
                            session.findRegistrationByAadhar(owner.getOwnerAadhar());
                    System.out.println(owner.getOwnerAadhar());
                    if (registrationList.isEmpty()) {
                        message = "No vehicles found for this owner.";
                    } else {
                        message = null;
                    }
                } else {
                    registrationList = new ArrayList<RegistrationEO>();
                    message = "Owner not found.";
                }
            } catch (Exception e) {
                registrationList = new ArrayList<RegistrationEO>();
                message = "Error loading registrations: " + e.getMessage();
                e.printStackTrace();
            }
        }
        return registrationList;
    }

    // Prepare SelectItem list for JSF dropdown

    public List<SelectItem> getRegistrationSelectItems() {
        if (registrationSelectItems == null) {
            registrationSelectItems = new ArrayList<SelectItem>();
            // Ensure registrationList is loaded
            if (registrationList == null) {
                getRegistrationList();
            }
            if (registrationList != null) {
                for (RegistrationEO reg : registrationList) {
                    String label =
                        reg.getVehicleEO().getVehicleBrand() + " - " +
                        reg.getVehicleEO().getVehicleModel();
                    registrationSelectItems.add(new SelectItem(reg.getRegistrationId(),
                                                               label));
                }
            }
        }
        return registrationSelectItems;
    }

    // Action method to load selected vehicle and registration details

    public String loadVehicleDetails() {
        if (selectedRegistrationId == null ||
            selectedRegistrationId.isEmpty()) {
            message = "Please select a vehicle registration.";
            selectedRegistration = null;
            selectedVehicle = null;
            return null; // stay on same page
        }

        try {
            selectedRegistration =
                    getSession().findRegistrationByID(selectedRegistrationId);
            if (selectedRegistration != null) {
                selectedVehicle = selectedRegistration.getVehicleEO();
                message = "Vehicle details loaded successfully.";
            } else {
                selectedVehicle = null;
                message = "Selected registration not found.";
            }
        } catch (Exception e) {
            selectedRegistration = null;
            selectedVehicle = null;
            message = "Error loading vehicle details: " + e.getMessage();
            e.printStackTrace();
        }
        return null; // stay on same page
    }

    // Getters and setters

    public HtmlForm getForm1() {
        return form1;
    }

    public void setForm1(HtmlForm form1) {
        this.form1 = form1;
    }

    public OwnerEO getOwner() {
        return owner;
    }

    public void setOwner(OwnerEO owner) {
        this.owner = owner;
    }

    public List<RegistrationEO> getRegistrationListField() {
        return registrationList;
    }

    public void setRegistrationList(List<RegistrationEO> registrationList) {
        this.registrationList = registrationList;
        this.registrationSelectItems =
                null; // reset select items if list changes
    }

    public String getSelectedRegistrationId() {
        return selectedRegistrationId;
    }

    public void setSelectedRegistrationId(String selectedRegistrationId) {
        this.selectedRegistrationId = selectedRegistrationId;
    }

    public RegistrationEO getSelectedRegistration() {
        return selectedRegistration;
    }

    public void setSelectedRegistration(RegistrationEO selectedRegistration) {
        this.selectedRegistration = selectedRegistration;
    }

    public VehicleEO getSelectedVehicle() {
        return selectedVehicle;
    }

    public void setSelectedVehicle(VehicleEO selectedVehicle) {
        this.selectedVehicle = selectedVehicle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

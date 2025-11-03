package training.iqgateway.view.backing;

import javax.faces.component.html.HtmlForm;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.iqgateway.model.OwnerEO;
import training.iqgateway.model.RegistrationEO;
import training.iqgateway.model.VehicleEO;
import training.iqgateway.services.ClerkSessionEJBLocal;

public class OwnerDetails {
    private HtmlForm form1;
    private HtmlForm form2;
    private String searchRegistrationID;
    private String ownerName;
    private String aadhar;
    private String phone;
    private String address;
    private String gender;
    private String pancard;
    private String regisID;
    private String location;
    private String regisDate;
    private String registrar;
    private String vehID;
    private String brand;
    private String model;
    private String vehType;
    private String fuel;
    private String exhaust;
    private String color;
    private String manufDate;

    private String message;
    private static boolean showCard = false;

    OwnerEO owner = new OwnerEO();
    VehicleEO vehicle = new VehicleEO();
    RegistrationEO regis = new RegistrationEO();

    public ClerkSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        Object lookupObject =
            ic.lookup("java:comp/env/ejb/local/ClerkSessionEJB");
        return (ClerkSessionEJBLocal)lookupObject;
    }

    public String search_action() {
        try {
            // Fetch registration by ID
            System.out.println("Search clicked");
            System.out.println(searchRegistrationID);
            regis =
                    getSessionBean().findRegistrationByID(searchRegistrationID);
            if (regis == null || searchRegistrationID == null) {
                message = "Registration not found";
                showCard = false;
                return message;
            }
            showCard = true;
            // Get owner Aadhar and vehicle ID from registration
            String ownAdr = regis.getOwnerEO().getOwnerAadhar();
            Long vehId = regis.getVehicleEO().getVehicleId();

            // Fetch owner and vehicle objects
            owner = getSessionBean().findOwnerByAadhar(ownAdr);
            vehicle = getSessionBean().findVehicleByID(vehId);

            // Set Owner Details fields
            this.ownerName = owner.getOwnerName();
            this.aadhar = owner.getOwnerAadhar();
            this.phone = owner.getPhone() + "";
            this.address = owner.getAddress();
            this.pancard = owner.getPanCard();
            this.gender = owner.getGender();

            // Set Registration Details fields
            this.regisID = regis.getRegistrationId();
            this.location = regis.getLocation();
            this.regisDate =
                    regis.getRegistrationDate() != null ? regis.getRegistrationDate().toString() :
                    null;
            this.registrar = regis.getRegistrar();

            // Set Vehicle Details fields
            this.vehID =
                    vehicle.getVehicleId() != null ? vehicle.getVehicleId().toString() :
                    null;
            this.brand = vehicle.getVehicleBrand();
            this.model = vehicle.getVehicleModel();
            this.vehType = vehicle.getVehicleType();
            this.fuel = vehicle.getFuelType();
            this.exhaust = vehicle.getNoOfExhaust() + "";
            this.color = vehicle.getColor();
            this.manufDate =
                    vehicle.getManufactureDate() != null ? vehicle.getManufactureDate().toString() :
                    null;

            System.out.println(regis);
            System.out.println(vehicle);
            System.out.println(owner);

            // Print all fields to console
            System.out.println("Owner Details:");
            System.out.println("Owner Name: " + ownerName);
            System.out.println("Aadhar: " + aadhar);
            System.out.println("Phone: " + phone);
            System.out.println("Address: " + address);
            System.out.println("Pancard: " + pancard);

            System.out.println("Registration Details:");
            System.out.println("Registration ID: " + regisID);
            System.out.println("Location: " + location);
            System.out.println("Registration Date: " + regisDate);
            System.out.println("Registrar: " + registrar);

            System.out.println("Vehicle Details:");
            System.out.println("Vehicle ID: " + vehID);
            System.out.println("Brand: " + brand);
            System.out.println("Model: " + model);
            System.out.println("Vehicle Type: " + vehType);
            System.out.println("Fuel: " + fuel);
            System.out.println("Exhaust: " + exhaust);
            System.out.println("Color: " + color);
            System.out.println("Manufacture Date: " + manufDate);


        } catch (Exception e) {
            message = "Error during search";
            e.printStackTrace();
        }
        return message;
    }


    public void setForm1(HtmlForm form1) {
        this.form1 = form1;
    }

    public HtmlForm getForm1() {
        return form1;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setForm2(HtmlForm form2) {
        this.form2 = form2;
    }

    public HtmlForm getForm2() {
        return form2;
    }

    public void setSearchRegistrationID(String searchRegistrationID) {
        this.searchRegistrationID = searchRegistrationID;
    }

    public String getSearchRegistrationID() {
        return searchRegistrationID;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPancard(String pancard) {
        this.pancard = pancard;
    }

    public String getPancard() {
        return pancard;
    }

    public void setRegisID(String regisID) {
        this.regisID = regisID;
    }

    public String getRegisID() {
        return regisID;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setRegisDate(String regisDate) {
        this.regisDate = regisDate;
    }

    public String getRegisDate() {
        return regisDate;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    public String getRegistrar() {
        return registrar;
    }

    public void setVehID(String vehID) {
        this.vehID = vehID;
    }

    public String getVehID() {
        return vehID;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setVehType(String vehType) {
        this.vehType = vehType;
    }

    public String getVehType() {
        return vehType;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getFuel() {
        return fuel;
    }

    public void setExhaust(String exhaust) {
        this.exhaust = exhaust;
    }

    public String getExhaust() {
        return exhaust;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setManufDate(String manufDate) {
        this.manufDate = manufDate;
    }

    public String getManufDate() {
        return manufDate;
    }

    public void setShowCard(boolean showCard) {
        this.showCard = showCard;
    }

    public boolean isShowCard() {
        return showCard;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}

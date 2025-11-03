package training.iqgateway.view.backing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;

import javax.faces.model.SelectItem;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import training.iqgateway.model.AdminEO;
import training.iqgateway.model.OffenceTypesEO;
import training.iqgateway.model.RegistrationEO;
import training.iqgateway.model.VehicleOffenceEO;
import training.iqgateway.services.ClerkSessionEJBLocal;
import training.iqgateway.utils.Format;

public class RaiseOffence {
    private HtmlForm form1;
    private HtmlForm raiseOffence;
    private boolean showCard = true;

    private String registrationID;
    private String location;
    private String reporter;
    private String offenceDate;
    private String time;
    private HtmlCommandButton submit;
    private HtmlCommandButton cancel;
    private List<SelectItem> offenceItems;
    private String selectedOffenceType;
    private String message;

    private byte[] imageFileBytes;
    private byte[] videoFileBytes;
    private String imageFilePath; // path to uploaded image on server
    private String videoFilePath; // path to uploaded video on server (optional)

    OffenceTypesEO offTypeEO = new OffenceTypesEO();
    AdminEO adminEO = new AdminEO();
    Format format = new Format();


    public ClerkSessionEJBLocal getSessionBean() throws NamingException {
        InitialContext ic = new InitialContext();
        Object lookupObject =
            ic.lookup("java:comp/env/ejb/local/ClerkSessionEJB");
        return (ClerkSessionEJBLocal)lookupObject;
    }

    public List<SelectItem> getoffenceItems() throws NamingException {
        if (offenceItems == null) {
            offenceItems = new ArrayList<SelectItem>();
            for (OffenceTypesEO offType :
                 getSessionBean().getOffenceTypesEOFindAll()) {
                offenceItems.add(new SelectItem(offType.getOffenceId(),
                                                offType.getOffenceType()));
            }
        }
        return offenceItems;
    }

    public boolean validateInput() {
        boolean valid = true;
        StringBuilder msgBuilder = new StringBuilder();

        try{
            if (registrationID == null || registrationID.trim().isEmpty()) {
                msgBuilder.append("Registration ID is required.\n");
                valid = false;
            }
            String validationMessage =
                format.vadilateRegistrationID(registrationID);
            if (validationMessage != null) {
                msgBuilder.append("Registration ID should be of the format KA01AB1234.\n");
                valid = false;
            }
            RegistrationEO regisEO =
                getSessionBean().findRegistrationByID(registrationID);
            if (regisEO == null) {
                message = "Registration ID not found!\n";
                valid = false;
            }
            if (location == null || location.trim().isEmpty()) {
                msgBuilder.append("Location field is required.\n");
                valid = false;
            }
            if (selectedOffenceType == null ||
                selectedOffenceType.trim().isEmpty()) {
                msgBuilder.append("Please select an offence type.\n");
                valid = false;
            }
            //        if (imageFileBytes == null || imageFileBytes.length == 0) {
            //            msgBuilder.append("Image upload is required.<br/>");
            //            valid = false;
            //        }

            message = msgBuilder.toString();
            return valid;
        }catch(Exception e){
            e.printStackTrace();
        }
        return valid;
    }

    public String submit_action() {
        System.out.println("Submit clicked");

        System.out.println("registrationID = " + registrationID);
        System.out.println("place = " + location);
        System.out.println("reporter = " + reporter);
        System.out.println("imageFilePath = " + imageFilePath);
        System.out.println("date = " + offenceDate);
        System.out.println("time = " + time);
        System.out.println("selectedOffenceType = " + selectedOffenceType);
        System.out.println("offenceItems = " +
                           (offenceItems != null ? offenceItems.size() :
                            "null"));


        // Step 1: Load files from disk into byte arrays
        //        if (!loadFiles()) {
        //            // loadFiles sets message on error
        //            return null;
        //        }

        if (!validateInput()) {
            return null;
        }

        try {
            VehicleOffenceEO vehOffence = new VehicleOffenceEO();
            RegistrationEO regisEO = getSessionBean().findRegistrationByID(registrationID);
            OffenceTypesEO offenceEO = new OffenceTypesEO();
            
            vehOffence.setRegistrationEO(regisEO);
            vehOffence.setLocation(location);
            vehOffence.setReporter(reporter);
            vehOffence.setOffenceDate(format.convertStringToTimestamp(offenceDate));
            vehOffence.setTime(format.formatTimeFormat(time));
            vehOffence.setStatus(0l);
            
            offenceEO = getSessionBean().findOffenceTypeByID(Long.parseLong(selectedOffenceType));
            vehOffence.setOffenceTypesEO(offenceEO);

            // Set proof files as byte arrays
            vehOffence.setProof1(imageFileBytes);
            vehOffence.setProof2(videoFileBytes); // may be null

            // Persist offence entity
            getSessionBean().persistVehicleOffenceEO(vehOffence);
            clearFields();
            message = "Offence reported successfully!";
        } catch (Exception e) {
            message = "Error: ";
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public void cancel_action() {
        clearFields();
    }

    public void clearFields() {
        this.registrationID = null;
        this.location = null;
        this.reporter = null;
        this.imageFilePath = null;
        this.offenceDate = null;
        this.time = null;
        this.selectedOffenceType = null;
        this.offenceItems = null;
    }

    public boolean loadFiles() {
        try {
            if (imageFilePath == null || imageFilePath.trim().isEmpty()) {
                message = "Image file path is missing!";
                return false;
            }
            imageFileBytes = readFileToByteArray(imageFilePath);

            if (videoFilePath != null && !videoFilePath.trim().isEmpty()) {
                videoFileBytes = readFileToByteArray(videoFilePath);
            } else {
                videoFileBytes = null; // no video uploaded
            }
            return true;
        } catch (IOException e) {
            message = "Error reading uploaded files: " + e.getMessage();
            e.printStackTrace();
            return false;
        }
    }


    public byte[] readFileToByteArray(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] fileData = new byte[(int)file.length()];
        try {
            FileInputStream fis = new FileInputStream(file);
            int readBytes = fis.read(fileData);
            if (readBytes != fileData.length) {
                throw new IOException("Could not read entire file: " +
                                      filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileData;
    }

    public void setForm1(HtmlForm form1) {
        this.form1 = form1;
    }

    public HtmlForm getForm1() {
        return form1;
    }

    public void setRaiseOffence(HtmlForm form2) {
        this.raiseOffence = form2;
    }

    public HtmlForm getRaiseOffence() {
        return raiseOffence;
    }

    public void setRegistrationID(String inputText1) {
        this.registrationID = inputText1;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setSubmit(HtmlCommandButton commandButton1) {
        this.submit = commandButton1;
    }

    public HtmlCommandButton getSubmit() {
        return submit;
    }

    public void setCancel(HtmlCommandButton commandButton1) {
        this.cancel = commandButton1;
    }

    public HtmlCommandButton getCancel() {
        return cancel;
    }

    public void setOffenceItems(List<SelectItem> offenceItems) {
        this.offenceItems = offenceItems;
    }

    public void setSelectedOffenceType(String selectedOffenceType) {
        this.selectedOffenceType = selectedOffenceType;
    }

    public String getSelectedOffenceType() {
        return selectedOffenceType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setReporter(String reporter) {
        this.reporter = adminEO.getDesignationId();
    }

    public String getReporter() {
        return reporter;
    }

    public void setOffenceDate(String offenceDate) {
        this.offenceDate = offenceDate;
    }

    public String getOffenceDate() {
        return offenceDate;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setOffTypeEO(OffenceTypesEO offTypeEO) {
        this.offTypeEO = offTypeEO;
    }

    public OffenceTypesEO getOffTypeEO() {
        return offTypeEO;
    }

    public void setShowCard(boolean showCard) {
        this.showCard = showCard;
    }

    public boolean isShowCard() {
        return showCard;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setImageFileBytes(byte[] imageFileBytes) {
        this.imageFileBytes = imageFileBytes;
    }

    public byte[] getImageFileBytes() {
        return imageFileBytes;
    }

    public void setVideoFileBytes(byte[] videoFileBytes) {
        this.videoFileBytes = videoFileBytes;
    }

    public byte[] getVideoFileBytes() {
        return videoFileBytes;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setVideoFilePath(String videoFilePath) {
        this.videoFilePath = videoFilePath;
    }

    public String getVideoFilePath() {
        return videoFilePath;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Format getFormat() {
        return format;
    }
}

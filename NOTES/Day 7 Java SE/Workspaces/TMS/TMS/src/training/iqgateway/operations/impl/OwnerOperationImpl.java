package training.iqgateway.operations.impl;

import java.security.MessageDigest;

import java.util.Collections;
import java.util.List;

import training.iqgateway.dao.OffenceTypesDAO;
import training.iqgateway.dao.OwnerDAO;
import training.iqgateway.dao.RegistrationDAO;
import training.iqgateway.dao.VehicleDAO;
import training.iqgateway.dao.VehicleOffenceDAO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.entities.VehicleOffenceEO;
import training.iqgateway.factory.GenericDAOFactory;
import training.iqgateway.operations.OwnerOperation;

public class OwnerOperationImpl implements OwnerOperation {

    OwnerDAO ownerOpRef = GenericDAOFactory.createOwnerDAO();
    OffenceTypesDAO OffTypesOpRef = GenericDAOFactory.createOffenceTypesDAO();
    RegistrationDAO regisOpRef = GenericDAOFactory.createRegistrationDAO();
    VehicleDAO vehOpRef = GenericDAOFactory.createVehicleDAO();
    VehicleOffenceDAO vehOffOpRef = GenericDAOFactory.createVehicleOffenceDAO();


    public String addOwner(OwnerEO ownerEO) {
        if (ownerEO.getOwnerName() == null ||
            ownerEO.getOwnerAadhar() == null ||
            ownerEO.getPassword() == null || 
            ownerEO.getPhone() == null ||
            ownerEO.getAddress() == null) {
            return "Error : Incomplete fields";
        }
        if (ownerOpRef.findOwnerByAadhar(ownerEO.getOwnerAadhar()) != null) {
            return "Owner already exists";
        }
        Integer check = ownerOpRef.insertOwner(ownerEO);
        return check == 1 ? "Signup Successful" : "Failed to signup";
    }

    public String modifyOwner(OwnerEO ownerEO) {
        if (ownerEO.getOwnerID() == null || 
            ownerEO.getOwnerName() == null ||
            ownerEO.getOwnerAadhar() == null ||
            ownerEO.getPassword() == null || 
            ownerEO.getPhone() == null ||
            ownerEO.getAddress() == null) {
            return "Error : Incomplete fields";
        }
        Integer check = ownerOpRef.updateOwner(ownerEO);
        return check == 1 ? "Successfully Updated" : "Failed to update";
    }

    public String removeOwner(String ownerAadhar, String password) {
        OwnerEO ownerEO = ownerOpRef.findOwnerByAadhar(ownerAadhar);
        if (ownerEO.getPassword().equals(password)) {
            Integer check = ownerOpRef.deleteOwner(ownerAadhar, password);
            if (check == 1)
                return "Account deleted";
        }
        return "Credential Mismatch";
    }
    
    public OffenceTypesEO findOffenceTypeByID (Integer offenceID){
        OffenceTypesEO offenceEO = OffTypesOpRef.findOffenceTypeByID(offenceID);
        return offenceEO;
    }
    
    public OwnerEO findOwnerByAadhar(String aadhar) {
        OwnerEO ownerEO = ownerOpRef.findOwnerByAadhar(aadhar);
        return ownerEO;
    }

    public List<RegistrationEO> findRegistrationByAadhar(String aadhar) {
        List<RegistrationEO> regisEOList = regisOpRef.findRegistrationByAadhar(aadhar);
        return regisEOList;
    }

    public RegistrationEO findRegistrationByID(String regisID) {
        RegistrationEO regisEO = regisOpRef.findRegistrationByID(regisID);
        return regisEO;
    }
    
    public VehicleEO findVehicleByID (Integer vehicleID){
        VehicleEO vehicleEO = vehOpRef.findVehicleByID(vehicleID);
        return vehicleEO;
    }
    
    public String clearVehicleOffence (VehicleOffenceEO vehOffEO){
        if(vehOffEO.getVehicleOffenceID() == null){
               return "Error: Incomplete fields";
        }
        Integer check = vehOffOpRef.clearVehicleOffence(vehOffEO);
        return check == 1 ? "Succesfully cleared Offence" : "Failed to clear offence"; 
    }

    public VehicleOffenceEO findVehicleOffenceByID(Integer vehicleOffenceID){
        VehicleOffenceEO vehOffEO = vehOffOpRef.findVehicleOffenceByID(vehicleOffenceID);
        return vehOffEO;
    }
    
    public List<VehicleOffenceEO> findVehicleOffenceByRegisID(String regisID) {
        List<VehicleOffenceEO> vehOffEOList =
            vehOffOpRef.findVehicleOffenceByRegisID(regisID);
        return vehOffEOList;
    }

    public boolean login(Object ownEO) {
        if (!(ownEO instanceof OwnerEO)) {
            System.out.println("Invalid login object");
            return false;
        }
        OwnerEO ownerEO = (OwnerEO)ownEO;
        try {
            OwnerEO user = ownerOpRef.findOwnerByAadhar(ownerEO.getOwnerAadhar());
            if (user == null) {
                System.out.println("User not found");
                return false;
            }
            if (user.getOwnerName().equals(ownerEO.getOwnerName()) &&
                user.getOwnerAadhar().equals(ownerEO.getOwnerAadhar()) &&
                user.getPassword().equals(ownerEO.getPassword())) {
                System.out.println("Logged In successfully");
                return true;
            } else {
                System.out.println("Credentials mismatch");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Exception during login: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean logout(Object ownEO) {
        return false;
    }
}

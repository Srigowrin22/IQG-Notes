package training.iqgateway.operations.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import training.iqgateway.dao.AdminDAO;
import training.iqgateway.dao.OffenceTypesDAO;
import training.iqgateway.dao.OwnerDAO;
import training.iqgateway.dao.RegistrationDAO;
import training.iqgateway.dao.RoleDAO;
import training.iqgateway.dao.VehicleDAO;
import training.iqgateway.dao.VehicleOffenceDAO;
import training.iqgateway.entities.AdminEO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.entities.VehicleOffenceEO;
import training.iqgateway.factory.GenericDAOFactory;
import training.iqgateway.operations.PoliceOperation;

public class PoliceOperationImpl implements PoliceOperation{
    
    AdminDAO plcOpRef = GenericDAOFactory.createAdminDAO();
    OffenceTypesDAO OffTypesOpRef = GenericDAOFactory.createOffenceTypesDAO();
    OwnerDAO ownerOpRef = GenericDAOFactory.createOwnerDAO();
    RoleDAO roleOpRef = GenericDAOFactory.createRoleDAO();
    RegistrationDAO regisOpRef = GenericDAOFactory.createRegistrationDAO();
    VehicleDAO vehOpRef = GenericDAOFactory.createVehicleDAO();
    VehicleOffenceDAO vehOffOpRef = GenericDAOFactory.createVehicleOffenceDAO();
    
    public AdminEO findAdminByDesigID(String designationID){
        AdminEO adminEO = plcOpRef.findAdminByDesigID(designationID);
        return adminEO;
    }
    
    public List<AdminEO> findAdminByName (String name){
        List<AdminEO> adminEOList = plcOpRef.findAdminByName(name);
        return adminEOList;
    }
    
    public OffenceTypesEO findOffenceTypeByID (Integer offenceID){
        OffenceTypesEO offenceEO = OffTypesOpRef.findOffenceTypeByID(offenceID);
        return offenceEO;
    }
    
    public List<OffenceTypesEO> findAllOffenceType (){
        List<OffenceTypesEO> offenceEOList = OffTypesOpRef.findAllOffenceType();
        return offenceEOList;
    }
    
    public List<OffenceTypesEO> findOffenceByVehicleType (String vehicleType){
        List<OffenceTypesEO> offenceEOList = OffTypesOpRef.findOffenceByVehicleType(vehicleType);
        return offenceEOList;
    }
    
    public List<OffenceTypesEO> findOffenceByOffenceType (String offenceType){
        List<OffenceTypesEO> offenceEOList = OffTypesOpRef.findOffenceByOffenceType(offenceType);
        return offenceEOList;
    }
    
    public OwnerEO findOwnerByAadhar(String ownerAadhar){
        OwnerEO ownerEO = ownerOpRef.findOwnerByAadhar(ownerAadhar);
        return ownerEO;
    }
    
    public List<OwnerEO> findAllOwners (){
        List<OwnerEO> ownerEOList = ownerOpRef.findAllOwners();
        return ownerEOList;   
    }
    
    public List<OwnerEO> findOwnerByName (String ownerName){
        List<OwnerEO> ownerEOList = ownerOpRef.findOwnerByName(ownerName);
        return ownerEOList;  
    }
    
    public RegistrationEO findRegistrationByID(String regisID){
        RegistrationEO regisEO = regisOpRef.findRegistrationByID(regisID);
        return regisEO;
    }
    
    public List<RegistrationEO> findAllRegistrations (){
        List<RegistrationEO> regisEOList = regisOpRef.findAllRegistrations();
        return regisEOList;
    }
    
    public VehicleEO findVehicleByID(Integer vehicleID){
        VehicleEO vehEO = vehOpRef.findVehicleByID(vehicleID);
        return vehEO;
    }
    
    public String addVehicleOffence (VehicleOffenceEO vehOffEO){
        if(vehOffEO.getVehicleOffenceID() == null || vehOffEO.getOffenceID() == null ||
            vehOffEO.getLocation() == null || vehOffEO.getRegistrationID() == null ||
           vehOffEO.getProof2() == null){
               return "Error: Incomplete fields";
        }
        List<VehicleOffenceEO> vehOffEOList = vehOffOpRef.findVehicleOffenceByRegisID(vehOffEO.getRegistrationID().getRegistrationID());
        for(VehicleOffenceEO vehOffence : vehOffEOList){
            Integer offenceID = vehOffence.getOffenceID().getOffenceID();
            Date date = vehOffence.getDate();
            String location = vehOffence.getLocation();
            if(vehOffEO.getOffenceID().getOffenceID().equals(offenceID) &&
               vehOffEO.getDate().equals(date) && 
               vehOffEO.getLocation().equals(location)){
                return "Offence already exists";
            }
            
        }       
        Integer check = vehOffOpRef.insertVehicleOffence(vehOffEO);
        return check == 1 ? "Succesfully updated" : "Failed to update"; 
    }
    
    public String modifyVehicleOffence (VehicleOffenceEO vehOffEO){
        if(vehOffEO.getVehicleOffenceID() == null || vehOffEO.getOffenceID() == null ||
            vehOffEO.getLocation() == null || vehOffEO.getRegistrationID() == null ||
           vehOffEO.getProof2() == null){
               return "Error: Incomplete fields";
        }
        Integer check = vehOffOpRef.updateVehicleOffence(vehOffEO);
        return check == 1 ? "Succesfully updated" : "Failed to update"; 
    }
    
    public String clearVehicleOffence (VehicleOffenceEO vehOffEO){
        if(vehOffEO.getVehicleOffenceID() == null || vehOffEO.getRegistrationID() == null){
               return "Error: Incomplete fields";
        }
        Integer check = vehOffOpRef.clearVehicleOffence(vehOffEO);
        return check == 1 ? "Succesfully cleared Offence" : "Failed to clear offence"; 
    }
    
    public VehicleOffenceEO findVehicleOffenceByID(Integer vehicleOffenceID){
        VehicleOffenceEO vehOffEO = vehOffOpRef.findVehicleOffenceByID(vehicleOffenceID);
        return vehOffEO;
    }
    
    public List<VehicleOffenceEO> findAllVehicleOffence(){
        List<VehicleOffenceEO> vehOffEOList = vehOffOpRef.findAllVehicleOffence();
        return vehOffEOList;
    }
    
    public List<VehicleOffenceEO> findVehicleOffenceByRegisID (String regisID){
        List<VehicleOffenceEO> vehOffEOList = vehOffOpRef.findVehicleOffenceByRegisID(regisID);
        return vehOffEOList;
    }
    
    public List<VehicleOffenceEO> findVehicleOffenceByStatus (String registrationID, Integer status){
        List<VehicleOffenceEO> vehOffEOList = vehOffOpRef.findVehicleOffenceByStatus(registrationID, status);
        return vehOffEOList;
    }
    
    public boolean login(Object admEO) {
        
        AdminEO adminEO = null;
        if(admEO instanceof AdminEO) {
            adminEO = (AdminEO)admEO; 
        }
        if(adminEO.getName().equals("") 
           || adminEO.getDesignationID().equals("")
           || adminEO.getPassword().equals("")){
            System.out.println("Error: Incomplete Fields");
            return false;
        }
        AdminEO user = plcOpRef.findAdminByDesigID(adminEO.getDesignationID());
        if(user!= null){
            if (user.getName().equals(adminEO.getName()) &&
                user.getPassword().equals(adminEO.getPassword()) &&
                user.getSignup()==1){
                return true;
            }
        }
        return false;
        
//        AdminEO adminEO = null;
//        if(admEO instanceof AdminEO) {
//            adminEO = (AdminEO)admEO; 
//        }
//        AdminEO user = PLCOpRef.findAdminByDesigID(adminEO.getDesignationID());
//        if (user.getName().equals(adminEO.getName()) &&
//            user.getPassword().equals(adminEO.getPassword())) {
//            int authorize = PLCOpRef.authorizeAdmin(adminEO.getDesignationID(), adminEO.getPassword());
//            System.out.println(authorize > 0 ? "RTO Admin Authorized": "Failed to authorize");
//            return true;
//        }
//        return false;
    }

    public boolean logout(Object admEO) {
        return false;
    }
}

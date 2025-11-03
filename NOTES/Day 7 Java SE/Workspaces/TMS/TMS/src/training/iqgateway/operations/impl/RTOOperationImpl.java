package training.iqgateway.operations.impl;

import java.util.List;

import javax.swing.JOptionPane;

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
import training.iqgateway.entities.RoleEO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.entities.VehicleOffenceEO;
import training.iqgateway.factory.GenericDAOFactory;
import training.iqgateway.operations.RTOOperation;

public class RTOOperationImpl implements RTOOperation{
    
    AdminDAO RTOOpRef = GenericDAOFactory.createAdminDAO();
    OffenceTypesDAO OffTypesOpRef = GenericDAOFactory.createOffenceTypesDAO();
    OwnerDAO ownerOpRef = GenericDAOFactory.createOwnerDAO();
    RoleDAO roleOpRef = GenericDAOFactory.createRoleDAO();
    RegistrationDAO regisOpRef = GenericDAOFactory.createRegistrationDAO();
    VehicleDAO vehOpRef = GenericDAOFactory.createVehicleDAO();
    VehicleOffenceDAO vehOffOpRef = GenericDAOFactory.createVehicleOffenceDAO();
    
    public AdminEO findAdminByDesigID(String designationID){
        AdminEO adminEO = RTOOpRef.findAdminByDesigID(designationID);
        return adminEO;
    }
    
    public List<AdminEO> findAdminByName (String name){
        List<AdminEO> adminEOList = RTOOpRef.findAdminByName(name);
        return adminEOList;
    }
    
    public String addOffenceType (OffenceTypesEO offenceEO){
        if (offenceEO.getOffenceType() == null 
            || offenceEO.getPenaltyAmt() == null
            || offenceEO.getVehicleType() == null) {
            return "Error : Incomplete fields";
        }
       
        List<OffenceTypesEO> offenceEOList =  OffTypesOpRef.findOffenceByOffenceType(offenceEO.getOffenceType());
        for(OffenceTypesEO offence : offenceEOList){
            String offenceType = offence.getOffenceType();
            String vehicleType = offence.getVehicleType();
            if(offenceEO.getOffenceType().equals(offenceType) && offenceEO.getVehicleType().equals(vehicleType)){
                return "OffenceType already exists";
            }
        }
        Integer check = OffTypesOpRef.insertOffenceType(offenceEO);
        return check == 1 ? "Succesfully added" : "Failed to add";
    }
    
    public String modifyOffenceType (OffenceTypesEO offenceEO){
        if (offenceEO.getOffenceType() == null 
            || offenceEO.getPenaltyAmt() == null
            || offenceEO.getVehicleType() == null) {
            return "Error : Incomplete fields";
        }
        Integer check = OffTypesOpRef.updateOffenceType(offenceEO);
        return check != 0 ? "Succesfully updated" : "Failed to update";
    }
    
    public String removeOffenceType (Integer offenceID){
        if(offenceID <= 0) return "Error: Invalid OffenceID";
        Integer check = OffTypesOpRef.deleteOffenceType(offenceID);
        return check != 0 ? "Succesfully deleted" : "Failed to delete";
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
    
    public int addOwner(OwnerEO ownerEO) {
        if (ownerEO.getOwnerName() == null ||
            ownerEO.getOwnerAadhar() == null ||
            ownerEO.getPassword() == null || 
            ownerEO.getPhone() == null ||
            ownerEO.getAddress() == null) {
            return 0;
        }
        Integer check = ownerOpRef.insertOwner(ownerEO);
        return check;
    }

    public int modifyOwner(OwnerEO ownerEO) {
        if (ownerEO.getOwnerName() == null ||
            ownerEO.getOwnerAadhar() == null ||
            ownerEO.getPhone() == null ||
            ownerEO.getAddress() == null) {
            return 0;
        }
        Integer check = ownerOpRef.updateOwner(ownerEO);
        return check;
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
    
    public List<RoleEO> findAllRoles(){
        List<RoleEO> roleEOList = roleOpRef.findAllRoles();
        return roleEOList;
    }
    
    public String addRegistration (RegistrationEO regisEO){
        if(regisEO.getRegistrationID() == null || regisEO.getLocation() == null 
           || regisEO.getVehicleID().getVehicleID() == null || regisEO.getOwnerAadhar().getOwnerAadhar() == null ){
               return "Error : Incomplete fields";
           }
        VehicleEO vehicleEO = vehOpRef.findVehicleByID(regisEO.getVehicleID().getVehicleID());
        if (vehicleEO == null) {
            JOptionPane.showMessageDialog(null, "Vehicle Not Present!", "Error", JOptionPane.ERROR_MESSAGE);
            return "Error" + " Vehicle Not Present!";
        }
        
        RegistrationEO regis = regisOpRef.findRegistrationByVehID(regisEO.getVehicleID().getVehicleID());
        if (regis != null) {
            return "Error" + " Vehicle ID already registered!";
        }
        
        regis = regisOpRef.findRegistrationByID(regisEO.getRegistrationID());
        if (regisEO != null) {
            return "Error " + "Registration ID already exists!";
        }
        
        Integer check = regisOpRef.insertRegistration(regisEO);
        return check == 1 ? "Succesfully added" : "Failed to add";        
    }
    
    public String modifyRegistration (RegistrationEO regisEO){
        if(regisEO.getRegistrationID() == null){
            return "Error : Incomplete fields";
        }
        RegistrationEO regis = regisOpRef.findRegistrationByID(regisEO.getRegistrationID());
        if(regis == null){
            return "Registration ID doest not exist";
        }
        String regisID = regis.getRegistrationID();
        String aadhar = regis.getOwnerAadhar().getOwnerAadhar();
        
        List<VehicleOffenceEO> vehOffEO = vehOffOpRef.findVehicleOffenceByStatus(regisID, 0);
            
        if( regisID.equals(regisEO.getRegistrationID())){
            if(!aadhar.equals(regisEO.getOwnerAadhar().getOwnerAadhar())){
                return "Registration ID belongs to some other";
            }else if(aadhar.equals(regisEO.getOwnerAadhar().getOwnerAadhar())){
                if(!vehOffEO.isEmpty()){
                    return "Clear the Vehicle Offence Before Transfer/Update";
                }           
            }
        }     
        Integer check = regisOpRef.updateRegistration(regisEO);
        return check == 1 ? "Succesfully updated" : "Failed to update";      
    }
    
    public String transferRegistration (RegistrationEO regisEO){
        if(regisEO.getRegistrationID() == null){
            return "Error : Incomplete fields";
        }
        RegistrationEO regis = regisOpRef.findRegistrationByID(regisEO.getRegistrationID());
        if(regis == null){
            return "Registration ID doest not exist";
        }
        String regisID = regis.getRegistrationID();        
        List<VehicleOffenceEO> vehOffEO = vehOffOpRef.findVehicleOffenceByStatus(regisID, 0);
                      
        if(!vehOffEO.isEmpty()){
            return "Clear the Vehicle Offence Before Transfer";            
        }     
        Integer check = regisOpRef.updateRegistration(regisEO);
        return check == 1 ? "Succesfully updated" : "Failed to update";      
    }
    
    public String removeRegistration (String regisID){
        Integer check = regisOpRef.deleteRegistration(regisID);
        return check == 1 ? "Succesfully deleted" : "Failed to delete";   
    }

    public RegistrationEO findRegistrationByID(String regisID){
        RegistrationEO regisEO = regisOpRef.findRegistrationByID(regisID);
        return regisEO;
    }
    
    public RegistrationEO findRegistrationByVehID(Integer vehID){
        RegistrationEO regisEO = regisOpRef.findRegistrationByVehID(vehID);
        return regisEO;
    }
    
    public List<RegistrationEO> findAllRegistrations (){
        List<RegistrationEO> regisEOList = regisOpRef.findAllRegistrations();
        return regisEOList;
    }
    
    public String addVehicle (VehicleEO vehicleEO){
        if(vehicleEO.getVehicleID()== null || vehicleEO.getVehicleBrand() == null 
           || vehicleEO.getVehicleType() == null || vehicleEO.getManufactureDate() == null){
               return "Error: Incomplete fields";
        }
        if(vehOpRef.findVehicleByID(vehicleEO.getVehicleID()) != null){
            return "Vehicle ID already exists";
        }
        Integer check = vehOpRef.insertVehicle(vehicleEO);
        return check == 1 ? "Succesfully inserted" : "Failed to insert";               
    }
    
    public String modifyVehicle (VehicleEO vehicleEO){
        if(vehicleEO.getVehicleID()== null){
               return "Error: Incomplete fields";
        }
        Integer check = vehOpRef.updateVehicle(vehicleEO);
        return check == 1 ? "Succesfully updated" : "Failed to update";   
    }
    
    public String removeVehicle (Integer vehicleID){
        Integer check = vehOpRef.deleteVehicle(vehicleID);
        return check == 1 ? "Succesfully deleted" : "Failed to delete";  
    }

    public VehicleEO findVehicleByID(Integer vehicleID){
        VehicleEO vehEO = vehOpRef.findVehicleByID(vehicleID);
        return vehEO;
    }
    
    public List<VehicleEO> findAllVehicles(){
        List<VehicleEO> vehEOList = vehOpRef.findAllVehicles();
        return vehEOList;
    }
    
    public List<VehicleEO> findVehicleByType (String vehicleType){
        List<VehicleEO> vehEOList = vehOpRef.findVehicleByType(vehicleType);
        return vehEOList;
    }
       
    public String modifyVehicleOffence (VehicleOffenceEO vehOffEO){
        if(vehOffEO.getVehicleOffenceID() == null || vehOffEO.getOffenceID().getOffenceID() == null ||
            vehOffEO.getLocation() == null || vehOffEO.getRegistrationID().getRegistrationID() == null ||
           vehOffEO.getProof2() == null){
               return "Error: Incomplete fields";
        }
        Integer check = vehOffOpRef.updateVehicleOffence(vehOffEO);
        return check == 1 ? "Succesfully updated" : "Failed to update"; 
    }
    
    public String clearVehicleOffence (VehicleOffenceEO vehOffEO){
        if(vehOffEO.getVehicleOffenceID() == null || vehOffEO.getRegistrationID().getRegistrationID() == null){
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
        AdminEO user = RTOOpRef.findAdminByDesigID(adminEO.getDesignationID());
        if(user!= null){
            if (user.getName().equals(adminEO.getName()) &&
                user.getPassword().equals(adminEO.getPassword()) &&
                user.getSignup()==1){
                return true;
            }
        }
        return false;
    }

    public boolean logout(Object admEO) {
        return false;
    }
}

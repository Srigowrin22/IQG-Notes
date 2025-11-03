package training.iqgateway.model;

import java.sql.Date;

import java.util.List;

import javax.ejb.Stateless;

import training.iqgateway.dao.AdminDAO;
import training.iqgateway.dao.OffenceTypesDAO;
import training.iqgateway.dao.OwnerDAO;
import training.iqgateway.dao.RegistrationDAO;
import training.iqgateway.dao.RoleDAO;
import training.iqgateway.dao.VehicleDAO;
import training.iqgateway.dao.VehicleOffenceDAO;
import training.iqgateway.dao.impl.AdminDAOImpl;
import training.iqgateway.dao.impl.OffenceTypesDAOImpl;
import training.iqgateway.dao.impl.OwnerDAOImpl;
import training.iqgateway.dao.impl.RegistrationDAOImpl;
import training.iqgateway.dao.impl.RoleDAOImpl;
import training.iqgateway.dao.impl.VehicleDAOImpl;
import training.iqgateway.dao.impl.VehicleOffenceDAOImpl;
import training.iqgateway.entities.AdminEO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.entities.VehicleOffenceEO;

@Stateless(name = "ClerkEJB", mappedName = "TMSWebApplication-Model-ClerkEJB")
public class ClerkEJBBean implements ClerkEJB, ClerkEJBLocal {
    public ClerkEJBBean() {
    }

    AdminDAO clkOpRef = new AdminDAOImpl();
    OffenceTypesDAO OffTypesOpRef = new OffenceTypesDAOImpl();
    OwnerDAO ownerOpRef = new OwnerDAOImpl();
    RoleDAO roleOpRef = new RoleDAOImpl();
    RegistrationDAO regisOpRef = new RegistrationDAOImpl();
    VehicleDAO vehOpRef = new VehicleDAOImpl();
    VehicleOffenceDAO vehOffOpRef = new VehicleOffenceDAOImpl();
    
    public AdminEO findAdminByDesigID(String designationID) {
        AdminEO adminEO = clkOpRef.findAdminByDesigID(designationID);
        return adminEO;
    }

    public List<AdminEO> findAdminByName(String name) {
        List<AdminEO> adminEOList = clkOpRef.findAdminByName(name);
        return adminEOList;
    }

    public OffenceTypesEO findOffenceTypeByID(Integer offenceID) {
        OffenceTypesEO offenceEO =
            OffTypesOpRef.findByOffenceID(offenceID);
        return offenceEO;
    }

    public List<OffenceTypesEO> findAllOffenceType() {
        List<OffenceTypesEO> offenceEOList =
            OffTypesOpRef.findAllOffenceTypes();
        return offenceEOList;
    }

    public List<OffenceTypesEO> findOffenceByVehicleType(String vehicleType) {
        List<OffenceTypesEO> offenceEOList =
            OffTypesOpRef.findOffenceTypesByVehicleType(vehicleType);
        return offenceEOList;
    }

    public List<OffenceTypesEO> findOffenceByOffenceType(String offenceType) {
        List<OffenceTypesEO> offenceEOList =
            OffTypesOpRef.findOffenceTypesByOffenceType(offenceType);
        return offenceEOList;
    }

    public OwnerEO findOwnerByAadhar(String ownerAadhar) {
        OwnerEO ownerEO = ownerOpRef.findOwnerByAadhar(ownerAadhar);
        return ownerEO;
    }

    public List<OwnerEO> findAllOwners() {
        List<OwnerEO> ownerEOList = ownerOpRef.findAllOwners();
        return ownerEOList;
    }

    public List<OwnerEO> findOwnerByName(String ownerName) {
        List<OwnerEO> ownerEOList = ownerOpRef.findOwnerByName(ownerName);
        return ownerEOList;
    }

    public RegistrationEO findRegistrationByID(String regisID) {
        RegistrationEO regisEO = regisOpRef.findRegistrationByID(regisID);
        return regisEO;
    }

    public List<RegistrationEO> findAllRegistrations() {
        List<RegistrationEO> regisEOList = regisOpRef.findAllRegistrations();
        return regisEOList;
    }

    public VehicleEO findVehicleByID(Integer vehicleID) {
        VehicleEO vehEO = vehOpRef.findVehicleByID(vehicleID);
        return vehEO;
    }

    public String addVehicleOffence(VehicleOffenceEO vehOffEO) {
        if (vehOffEO.getOffence_id() == null ||
            vehOffEO.getLocation() == null ||
            vehOffEO.getRegistration_id() == null) {
            return "Error: Incomplete fields";
        }
        List<VehicleOffenceEO> vehOffEOList =
            vehOffOpRef.findVehicleOffenceByRegisID(vehOffEO.getRegistration_id().getRegistration_id());
        for (VehicleOffenceEO vehOffence : vehOffEOList) {
            Integer offenceID = vehOffence.getOffence_id().getOffence_id();
            Date date = vehOffence.getOffence_date();
            String location = vehOffence.getLocation();
            if (vehOffEO.getOffence_id().getOffence_id().equals(offenceID) &&
                vehOffEO.getOffence_date().equals(date) &&
                vehOffEO.getLocation().equals(location)) {
                return "Offence already exists";
            }

        }
        Integer check = vehOffOpRef.insertVehicleOffence(vehOffEO);
        return check == 1 ? "Succesfully updated" : "Failed to update";
    }

    public String modifyVehicleOffence(VehicleOffenceEO vehOffEO) {
        if (vehOffEO.getVehicle_offence_id() == null ||
            vehOffEO.getOffence_id() == null ||
            vehOffEO.getLocation() == null ||
            vehOffEO.getRegistration_id() == null ||
            vehOffEO.getProof2() == null) {
            return "Error: Incomplete fields";
        }
        Integer check = vehOffOpRef.updateVehicleOffence(vehOffEO);
        return check == 1 ? "Succesfully updated" : "Failed to update";
    }

    public String clearVehicleOffence(Integer vehOffID) {
        if (vehOffID == null){
            return "Error: Incomplete fields";
        }
        Integer check = vehOffOpRef.clearVehicleOffence(vehOffID);
        return check == 1 ? "Succesfully cleared Offence" :
               "Failed to clear offence";
    }

    public VehicleOffenceEO findVehicleOffenceByID(Integer vehicleOffenceID) {
        VehicleOffenceEO vehOffEO =
            vehOffOpRef.findVehicleOffenceByID(vehicleOffenceID);
        return vehOffEO;
    }

    public List<VehicleOffenceEO> findAllVehicleOffence() {
        List<VehicleOffenceEO> vehOffEOList =
            vehOffOpRef.findAllVehicleOffence();
        return vehOffEOList;
    }

    public List<VehicleOffenceEO> findVehicleOffenceByRegisID(String regisID) {
        List<VehicleOffenceEO> vehOffEOList =
            vehOffOpRef.findVehicleOffenceByRegisID(regisID);
        return vehOffEOList;
    }

    public List<VehicleOffenceEO> findVehicleOffenceByStatus(String registrationID,
                                                             Integer status) {
        List<VehicleOffenceEO> vehOffEOList =
            vehOffOpRef.findVehicleOffenceByStatus(registrationID, status);
        return vehOffEOList;
    }

    public boolean login(Object admEO) {

        AdminEO adminEO = null;
        if (admEO instanceof AdminEO) {
            adminEO = (AdminEO)admEO;
        }
        if (adminEO.getName().equals("") ||
            adminEO.getDesignationID().equals("") ||
            adminEO.getPassword().equals("")) {
            System.out.println("Error: Incomplete Fields");
            return false;
        }
        AdminEO user = clkOpRef.findAdminByDesigID(adminEO.getDesignationID());
        if (user != null) {
            if (user.getName().equals(adminEO.getName()) &&
                user.getPassword().equals(adminEO.getPassword()) &&
                user.getSignup() == 1) {
                return true;
            }
        }
        return false;
    }

//    public boolean logout(Object adminEO) {
//        return false;
//    }
}

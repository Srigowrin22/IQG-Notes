package training.iqgateway.model;

import java.util.List;

import javax.ejb.Stateless;

import javax.swing.JOptionPane;

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
import training.iqgateway.entities.RoleEO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.entities.VehicleOffenceEO;

@Stateless(name = "RTOEJB", mappedName = "TMSWebApplication-Model-RTOEJB")
public class RTOEJBBean implements RTOEJB, RTOEJBLocal {
    
    public RTOEJBBean() {
    }

    AdminDAO rtoOpRef = new AdminDAOImpl();
    OffenceTypesDAO OffTypesOpRef = new OffenceTypesDAOImpl();
    OwnerDAO ownerOpRef = new OwnerDAOImpl();
    RoleDAO roleOpRef = new RoleDAOImpl();
    RegistrationDAO regisOpRef = new RegistrationDAOImpl();
    VehicleDAO vehOpRef = new VehicleDAOImpl();
    VehicleOffenceDAO vehOffOpRef = new VehicleOffenceDAOImpl();

    public AdminEO findAdminByDesigID(String designationID) {
        AdminEO adminEO = rtoOpRef.findAdminByDesigID(designationID);
        return adminEO;
    }

    public List<AdminEO> findAdminByName(String name) {
        List<AdminEO> adminEOList = rtoOpRef.findAdminByName(name);
        return adminEOList;
    }

    public String addOffenceType(OffenceTypesEO offenceEO) {
        if (offenceEO.getOffence_type() == null ||
            offenceEO.getPenalty_amt() == null ||
            offenceEO.getVehicle_type() == null) {
            return "Error : Incomplete fields";
        }

        List<OffenceTypesEO> offenceEOList =
            OffTypesOpRef.findOffenceTypesByOffenceType(offenceEO.getOffence_type());
        for (OffenceTypesEO offence : offenceEOList) {
            String offenceType = offence.getOffence_type();
            String vehicleType = offence.getVehicle_type();
            if (offenceEO.getOffence_type().equals(offenceType) &&
                offenceEO.getVehicle_type().equals(vehicleType)) {
                return "OffenceType already exists";
            }
        }
        Integer check = OffTypesOpRef.insertOffenceTypes(offenceEO);
        return check == 1 ? "Succesfully added" : "Failed to add";
    }

    public String modifyOffenceType(OffenceTypesEO offenceEO) {
        if (offenceEO.getOffence_type() == null ||
            offenceEO.getPenalty_amt() == null ||
            offenceEO.getVehicle_type() == null) {
            return "Error : Incomplete fields";
        }
        Integer check = OffTypesOpRef.updateOffenceTypes(offenceEO);
        return check != 0 ? "Succesfully updated" : "Failed to update";
    }

    public String removeOffenceType(Integer offenceID) {
        if (offenceID <= 0)
            return "Error: Invalid OffenceID";
        Integer check = OffTypesOpRef.deleteOffenceTypes(offenceID);
        return check != 0 ? "Succesfully deleted" : "Failed to delete";
    }

    public OffenceTypesEO findOffenceTypeByID(Integer offenceID) {
        OffenceTypesEO offenceEO = OffTypesOpRef.findByOffenceID(offenceID);
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

    public int addOwner(OwnerEO ownerEO) {
        if (ownerEO.getOwner_name() == null ||
            ownerEO.getOwner_aadhar() == null ||
            ownerEO.getPassword() == null || ownerEO.getPhone() == null ||
            ownerEO.getAddress() == null) {
            return 0;
        }
        Integer check = ownerOpRef.insertOwner(ownerEO);
        return check;
    }

    public int modifyOwner(OwnerEO ownerEO) {
        if (ownerEO.getOwner_name() == null ||
            ownerEO.getOwner_aadhar() == null || ownerEO.getPhone() == null ||
            ownerEO.getAddress() == null) {
            return 0;
        }
        Integer check = ownerOpRef.updateOwner(ownerEO);
        return check;
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

    public List<RoleEO> findAllRoles() {
        List<RoleEO> roleEOList = roleOpRef.findAllRoles();
        return roleEOList;
    }

    public String addRegistration(RegistrationEO regisEO) {
        if (regisEO.getRegistration_id() == null ||
            regisEO.getLocation() == null ||
            regisEO.getVehicle_id().getVehicle_id() == null ||
            regisEO.getOwner_aadhar().getOwner_aadhar() == null) {
            return "Error : Incomplete fields";
        }
        VehicleEO vehicleEO =
            vehOpRef.findVehicleByID(regisEO.getVehicle_id().getVehicle_id());
        if (vehicleEO == null) {
            JOptionPane.showMessageDialog(null, "Vehicle Not Present!",
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return "Error" + " Vehicle Not Present!";
        }

        RegistrationEO regis =
            regisOpRef.findRegistrationByVehID(regisEO.getVehicle_id().getVehicle_id());
        if (regis != null) {
            return "Error" + " Vehicle ID already registered!";
        }

        regis = regisOpRef.findRegistrationByID(regisEO.getRegistration_id());
        if (regisEO != null) {
            return "Error " + "Registration ID already exists!";
        }

        Integer check = regisOpRef.insertRegistration(regisEO);
        return check == 1 ? "Succesfully added" : "Failed to add";
    }

    public String modifyRegistration(RegistrationEO regisEO) {
        if (regisEO.getRegistration_id() == null) {
            return "Error : Incomplete fields";
        }
        RegistrationEO regis =
            regisOpRef.findRegistrationByID(regisEO.getRegistration_id());
        if (regis == null) {
            return "Registration ID doest not exist";
        }
        String regisID = regis.getRegistration_id();
        String aadhar = regis.getOwner_aadhar().getOwner_aadhar();

        List<VehicleOffenceEO> vehOffEO =
            vehOffOpRef.findVehicleOffenceByStatus(regisID, 0);

        if (regisID.equals(regisEO.getRegistration_id())) {
            if (!aadhar.equals(regisEO.getOwner_aadhar().getOwner_aadhar())) {
                return "Registration ID belongs to some other";
            } else if (aadhar.equals(regisEO.getOwner_aadhar().getOwner_aadhar())) {
                if (!vehOffEO.isEmpty()) {
                    return "Clear the Vehicle Offence Before Transfer/Update";
                }
            }
        }
        Integer check = regisOpRef.updateRegistration(regisEO);
        return check == 1 ? "Succesfully updated" : "Failed to update";
    }

    public String transferRegistration(RegistrationEO regisEO) {
        if (regisEO.getRegistration_id() == null) {
            return "Error : Incomplete fields";
        }
        RegistrationEO regis =
            regisOpRef.findRegistrationByID(regisEO.getRegistration_id());
        if (regis == null) {
            return "Registration ID doest not exist";
        }
        String regisID = regis.getRegistration_id();
        List<VehicleOffenceEO> vehOffEO =
            vehOffOpRef.findVehicleOffenceByStatus(regisID, 0);

        if (!vehOffEO.isEmpty()) {
            return "Clear the Vehicle Offence Before Transfer";
        }
        Integer check = regisOpRef.updateRegistration(regisEO);
        return check == 1 ? "Succesfully updated" : "Failed to update";
    }

    public String removeRegistration(String regisID) {
        Integer check = regisOpRef.deleteRegistration(regisID);
        return check == 1 ? "Succesfully deleted" : "Failed to delete";
    }

    public RegistrationEO findRegistrationByID(String regisID) {
        RegistrationEO regisEO = regisOpRef.findRegistrationByID(regisID);
        return regisEO;
    }

    public RegistrationEO findRegistrationByVehID(Integer vehID) {
        RegistrationEO regisEO = regisOpRef.findRegistrationByVehID(vehID);
        return regisEO;
    }

    public List<RegistrationEO> findAllRegistrations() {
        List<RegistrationEO> regisEOList = regisOpRef.findAllRegistrations();
        return regisEOList;
    }

    public String addVehicle(VehicleEO vehicleEO) {
        if (vehicleEO.getVehicle_id() == null ||
            vehicleEO.getVehicle_brand() == null ||
            vehicleEO.getVehicle_type() == null ||
            vehicleEO.getManufacture_date() == null) {
            return "Error: Incomplete fields";
        }
        if (vehOpRef.findVehicleByID(vehicleEO.getVehicle_id()) != null) {
            return "Vehicle ID already exists";
        }
        Integer check = vehOpRef.insertVehicle(vehicleEO);
        return check == 1 ? "Succesfully inserted" : "Failed to insert";
    }

    public String modifyVehicle(VehicleEO vehicleEO) {
        if (vehicleEO.getVehicle_id() == null) {
            return "Error: Incomplete fields";
        }
        Integer check = vehOpRef.updateVehicle(vehicleEO);
        return check == 1 ? "Succesfully updated" : "Failed to update";
    }

    public String removeVehicle(Integer vehicleID) {
        Integer check = vehOpRef.deleteVehicle(vehicleID);
        return check == 1 ? "Succesfully deleted" : "Failed to delete";
    }

    public VehicleEO findVehicleByID(Integer vehicleID) {
        VehicleEO vehEO = vehOpRef.findVehicleByID(vehicleID);
        return vehEO;
    }

    public List<VehicleEO> findAllVehicles() {
        List<VehicleEO> vehEOList = vehOpRef.findAllVehicles();
        return vehEOList;
    }

    public List<VehicleEO> findVehicleByType(String vehicleType) {
        List<VehicleEO> vehEOList = vehOpRef.findVehicleByType(vehicleType);
        return vehEOList;
    }

    public String modifyVehicleOffence(VehicleOffenceEO vehOffEO) {
        if (vehOffEO.getVehicle_offence_id() == null ||
            vehOffEO.getOffence_id().getOffence_id() == null ||
            vehOffEO.getLocation() == null ||
            vehOffEO.getRegistration_id().getRegistration_id() == null ||
            vehOffEO.getProof2() == null) {
            return "Error: Incomplete fields";
        }
        Integer check = vehOffOpRef.updateVehicleOffence(vehOffEO);
        return check == 1 ? "Succesfully updated" : "Failed to update";
    }

    public String clearVehicleOffence(Integer vehOffID) {
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
        AdminEO user = rtoOpRef.findAdminByDesigID(adminEO.getDesignationID());
        if (user != null) {
            if (user.getName().equals(adminEO.getName()) &&
                user.getPassword().equals(adminEO.getPassword()) &&
                user.getSignup() == 1) {
                return true;
            }
        }
        return false;
    }

//    public boolean logout(Object admEO) {
//        return false;
//    }
}

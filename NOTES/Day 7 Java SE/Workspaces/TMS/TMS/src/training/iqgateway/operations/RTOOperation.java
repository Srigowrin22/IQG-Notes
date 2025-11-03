package training.iqgateway.operations;

import java.util.List;

import training.iqgateway.entities.AdminEO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.entities.RoleEO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.entities.VehicleOffenceEO;

public interface RTOOperation extends Operations{
    
    public AdminEO findAdminByDesigID(String designationID);
    
    public List<AdminEO> findAdminByName (String name);
    
    public String addOffenceType (OffenceTypesEO offenceEO);
    
    public String modifyOffenceType (OffenceTypesEO offenceEO);
    
    public String removeOffenceType (Integer offenceID);

    public OffenceTypesEO findOffenceTypeByID (Integer offenceID);
    
    public List<OffenceTypesEO> findAllOffenceType ();
    
    public List<OffenceTypesEO> findOffenceByVehicleType (String vehicleType);
    
    public List<OffenceTypesEO> findOffenceByOffenceType (String offenceType);
    
    public int addOwner(OwnerEO ownerEO);
    
    public int modifyOwner(OwnerEO ownerEO);
    
    public OwnerEO findOwnerByAadhar(String ownerAadhar);
    
    public List<OwnerEO> findAllOwners ();
    
    public List<OwnerEO> findOwnerByName (String ownerName);
    
    public List<RoleEO> findAllRoles();
    
    public String addRegistration (RegistrationEO regisEO);
    
    public String modifyRegistration (RegistrationEO regisEO);
    
    public String transferRegistration (RegistrationEO regisEO);

    public String removeRegistration (String regisID);

    public RegistrationEO findRegistrationByID(String regisID);
    
    public RegistrationEO findRegistrationByVehID(Integer vehID);

    public List<RegistrationEO> findAllRegistrations ();
        
    public String addVehicle (VehicleEO vehicleEO);
    
    public String modifyVehicle (VehicleEO vehicleEO);
    
    public String removeVehicle (Integer vehicleID);

    public VehicleEO findVehicleByID(Integer vehicleID);
    
    public List<VehicleEO> findAllVehicles();
    
    public List<VehicleEO> findVehicleByType (String vehicleType);
           
    public String modifyVehicleOffence (VehicleOffenceEO vehOffEO);
    
    public String clearVehicleOffence (VehicleOffenceEO vehOffEO);
    
    public VehicleOffenceEO findVehicleOffenceByID(Integer vehicleOffenceID);
    
    public List<VehicleOffenceEO> findAllVehicleOffence();
    
    public List<VehicleOffenceEO> findVehicleOffenceByRegisID (String regisID);
    
    public List<VehicleOffenceEO> findVehicleOffenceByStatus (String registrationID, Integer status);
    
}

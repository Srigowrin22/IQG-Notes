package training.iqgateway.model;

import java.util.List;

import javax.ejb.Local;

import training.iqgateway.entities.AdminEO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.entities.VehicleOffenceEO;

@Local
public interface PoliceEJBLocal {
    
    public AdminEO findAdminByDesigID(String designationID);
    
    public List<AdminEO> findAdminByName (String name);
    
    public OffenceTypesEO findOffenceTypeByID (Integer offenceID);
    
    public List<OffenceTypesEO> findAllOffenceType ();
    
    public List<OffenceTypesEO> findOffenceByVehicleType (String vehicleType);
    
    public List<OffenceTypesEO> findOffenceByOffenceType (String offenceType);
    
    public OwnerEO findOwnerByAadhar(String ownerAadhar);
    
    public List<OwnerEO> findAllOwners ();
    
    public List<OwnerEO> findOwnerByName (String ownerName);
    
    public RegistrationEO findRegistrationByID(String regisID);
    
    public List<RegistrationEO> findAllRegistrations ();
    
    public String addVehicleOffence (VehicleOffenceEO vehOffEO);
    
    public String modifyVehicleOffence (VehicleOffenceEO vehOffEO);
    
    public String clearVehicleOffence (Integer vehOffID);
    
    public VehicleOffenceEO findVehicleOffenceByID(Integer vehicleOffenceID);
    
    public List<VehicleOffenceEO> findAllVehicleOffence();
    
    public List<VehicleOffenceEO> findVehicleOffenceByRegisID (String regisID);
    
    public List<VehicleOffenceEO> findVehicleOffenceByStatus (String registrationID, Integer status);
    
}

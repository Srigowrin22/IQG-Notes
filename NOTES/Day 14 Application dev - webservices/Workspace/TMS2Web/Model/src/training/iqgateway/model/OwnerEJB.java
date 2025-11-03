package training.iqgateway.model;

import java.util.List;

import javax.ejb.Remote;

import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.entities.VehicleOffenceEO;

@Remote
public interface OwnerEJB {
    
    public String addOwner (OwnerEO ownerEO);
    
    public String modifyOwner (OwnerEO ownerEO);
    
    public String removeOwner (String ownerAadhar, String password);
    
    public OffenceTypesEO findOffenceTypeByID (Integer offenceID);
    
    public OwnerEO findOwnerByAadhar (String aadhar);
    
    public List<RegistrationEO> findRegistrationByAadhar (String aadhar);
    
    public RegistrationEO findRegistrationByID (String regisID);
    
    public VehicleEO findVehicleByID (Integer vehicleID);
    
    public String clearVehicleOffence (Integer vehOffID);
    
    public VehicleOffenceEO findVehicleOffenceByID(Integer vehicleOffenceID);

    public List<VehicleOffenceEO> findVehicleOffenceByRegisID (String regisID);

}

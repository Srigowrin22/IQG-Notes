package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.VehicleOffenceEO;

public interface VehicleOffenceDAO {
    
    int insertVehicleOffence (VehicleOffenceEO vehOffEO);
    
    int updateVehicleOffence (VehicleOffenceEO vehOffEO);
    
    int clearVehicleOffence (VehicleOffenceEO vehOffEO);
    
    int deleteVehicleOffence (Integer vehicleOffenceID);

    VehicleOffenceEO findVehicleOffenceByID(Integer vehicleOffenceID);
    
    List<VehicleOffenceEO> findAllVehicleOffence();
    
    List<VehicleOffenceEO> findVehicleOffenceByRegisID (String regisID);
    
    List<VehicleOffenceEO> findVehicleOffenceByStatus (String registrationID, Integer status);
    
}

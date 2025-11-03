package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.VehicleEO;

public interface VehicleDAO {
       
    int insertVehicle (VehicleEO vehicleEO);
    
    int updateVehicle (VehicleEO vehicleEO);
    
    int deleteVehicle (Integer vehicleID);

    VehicleEO findVehicleByID(Integer vehicleID);
    
    List<VehicleEO> findAllVehicles();
    
    List<VehicleEO> findVehicleByType (String vehicleType);
        
}

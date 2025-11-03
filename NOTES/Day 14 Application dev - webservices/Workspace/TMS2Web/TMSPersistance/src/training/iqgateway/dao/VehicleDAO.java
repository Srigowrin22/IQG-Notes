package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.VehicleEO;

public interface VehicleDAO {
       
	Integer insertVehicle (VehicleEO vehicleEO);
    
	Integer updateVehicle (VehicleEO vehicleEO);
    
	Integer deleteVehicle (Integer vehicleID);

    VehicleEO findVehicleByID(Integer vehicleID);
    
    List<VehicleEO> findAllVehicles();
    
    List<VehicleEO> findVehicleByType (String vehicleType);
        
}

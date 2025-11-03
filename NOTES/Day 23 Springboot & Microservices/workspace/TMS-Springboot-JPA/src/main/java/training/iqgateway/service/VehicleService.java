package training.iqgateway.service;

import java.util.List;
import training.iqgateway.dto.VehicleDTO;
import training.iqgateway.entities.VehicleEO;

public interface VehicleService {

	String persistVehicleEO(VehicleEO vehicleEO);

	String mergeVehicleEO(VehicleEO vehicleEO);

	Boolean removeVehicleEO(Integer vehicleID);

	VehicleDTO findVehicleByID(Integer vehicleID);

	List<VehicleDTO> findAllVehicles();

	List<VehicleDTO> findVehicleByType(String vehicleType);
}

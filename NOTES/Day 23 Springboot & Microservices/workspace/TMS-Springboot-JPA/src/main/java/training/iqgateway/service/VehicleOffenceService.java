package training.iqgateway.service;

import java.util.List;

import training.iqgateway.dto.VehicleOffenceDTO;
import training.iqgateway.entities.VehicleOffenceEO;

public interface VehicleOffenceService {

	String persistVehicleOffenceEO(VehicleOffenceEO vehicleOffenceEO);

	String mergeVehicleOffenceEO(VehicleOffenceEO vehicleOffenceEO);

	String clearVehicleOffenceEO(VehicleOffenceEO vehicleOffenceEO);

	Boolean removeVehicleOffenceEO(Integer vehicleOffenceID);

	VehicleOffenceDTO findVehicleOffenceByID(Integer vehicleOffenceID);

	List<VehicleOffenceDTO> findAllVehicleOffences();

	List<VehicleOffenceDTO> findVehicleOffenceByRegisID(String registrationID);

	List<VehicleOffenceDTO> findVehicleOffenceByStatus(String registrationID, Integer status);
	
}
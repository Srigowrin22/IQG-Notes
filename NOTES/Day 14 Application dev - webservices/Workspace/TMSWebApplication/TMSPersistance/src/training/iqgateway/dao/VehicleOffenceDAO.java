package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.VehicleOffenceEO;

public interface VehicleOffenceDAO {

	Integer insertVehicleOffence(VehicleOffenceEO vehOffEO);

	Integer updateVehicleOffence(VehicleOffenceEO vehOffEO);

	Integer deleteVehicleOffence(Integer vehicleOffenceID);

	Integer clearVehicleOffence(Integer vehicleOffenceID);

	VehicleOffenceEO findVehicleOffenceByID(Integer vehicleOffenceID);

	List<VehicleOffenceEO> findAllVehicleOffence();

	List<VehicleOffenceEO> findVehicleOffenceByRegisID(String regisID);

	List<VehicleOffenceEO> findVehicleOffenceByStatus(String registrationID, Integer status);

}

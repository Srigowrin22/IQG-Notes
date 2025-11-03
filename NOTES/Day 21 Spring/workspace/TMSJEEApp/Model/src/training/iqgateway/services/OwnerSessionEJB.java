package training.iqgateway.services;

import java.util.List;

import javax.ejb.Remote;

import training.iqgateway.model.OffenceTypesEO;
import training.iqgateway.model.OwnerEO;
import training.iqgateway.model.RegistrationEO;
import training.iqgateway.model.VehicleEO;
import training.iqgateway.model.VehicleOffenceEO;

@Remote
public interface OwnerSessionEJB {

    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    // Owner CRUD

    OwnerEO persistOwnerEO(OwnerEO ownerEO);

    OwnerEO mergeOwnerEO(OwnerEO ownerEO);

    Boolean removeOwnerEO(OwnerEO ownerEO);

    // Find Owner by Aadhar

    OwnerEO findOwnerByAadhar(String aadhar);

    // Find Registration by Owner Aadhar

    List<RegistrationEO> findRegistrationByAadhar(String aadhar);

    // Find Registration by ID

    RegistrationEO findRegistrationByID(String regisID);

    // Find Vehicle by ID

    VehicleEO findVehicleByID(Long vehicleID);

    // Find VehicleOffence by RegisID

    List<VehicleOffenceEO> findVehicleOffenceByRegisID(String regisID);

    // Find VehicleOffence by ID

    VehicleOffenceEO findVehicleOffenceByID(Long vehicleOffenceID);

    // Clear Vehicle Offence

    Boolean removeVehicleOffenceEO(Long vehicleOffenceID);

    // Find OffenceType by ID

    OffenceTypesEO findOffenceTypeByID(Long offenceID);

    boolean login(String ownerAadhar, String ownerName, String password);

}

package training.iqgateway.services;

import java.util.List;

import javax.ejb.Local;

import training.iqgateway.model.AdminEO;
import training.iqgateway.model.OffenceTypesEO;
import training.iqgateway.model.OwnerEO;
import training.iqgateway.model.RegistrationEO;
import training.iqgateway.model.RoleEO;
import training.iqgateway.model.VehicleEO;
import training.iqgateway.model.VehicleOffenceEO;

@Local
public interface ClerkSessionEJBLocal {

    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    // Admin methods

    AdminEO findAdminByDesigID(String designationID);

    List<AdminEO> findAdminByName(String name);

    List<AdminEO> getAdminEOFindAll();

    // OffenceTypes methods

    OffenceTypesEO findOffenceTypeByID(Long offenceID);

    List<OffenceTypesEO> getOffenceTypesEOFindAll();

    List<OffenceTypesEO> findOffenceByVehicleType(String vehicleType);

    List<OffenceTypesEO> findOffenceByOffenceType(String offenceType);

    // Owner methods

    OwnerEO findOwnerByAadhar(String ownerAadhar);

    List<OwnerEO> getOwnerEOFindAll();

    List<OwnerEO> findOwnerByName(String ownerName);

    // Role methods

    List<RoleEO> getRoleEOFindAll();

    // Registration methods

    RegistrationEO findRegistrationByID(String regisID);

    List<RegistrationEO> getRegistrationEOFindAll();

    // Vehicle methods

    VehicleEO findVehicleByID(Long vehicleID);

    List<VehicleEO> getVehicleEOFindAll();

    // VehicleOffence methods

    VehicleOffenceEO persistVehicleOffenceEO(VehicleOffenceEO vehOffEO);

    VehicleOffenceEO mergeVehicleOffenceEO(VehicleOffenceEO vehOffEO);

    Boolean removeVehicleOffenceEO(VehicleOffenceEO vehOffEO);

    VehicleOffenceEO findVehicleOffenceByID(Long vehicleOffenceID);

    List<VehicleOffenceEO> getVehicleOffenceEOFindAll();

    List<VehicleOffenceEO> findVehicleOffenceByRegisID(String regisID);

    List<VehicleOffenceEO> findVehicleOffenceByStatus(String registrationID,
                                                      Long status);
}

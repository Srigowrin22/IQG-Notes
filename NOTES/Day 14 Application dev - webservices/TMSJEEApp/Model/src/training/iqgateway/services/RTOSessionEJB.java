package training.iqgateway.services;

import java.util.List;

import javax.ejb.Remote;

import training.iqgateway.model.AdminEO;
import training.iqgateway.model.OffenceTypesEO;
import training.iqgateway.model.OwnerEO;
import training.iqgateway.model.RegistrationEO;
import training.iqgateway.model.RoleEO;
import training.iqgateway.model.VehicleEO;
import training.iqgateway.model.VehicleOffenceEO;

@Remote
public interface RTOSessionEJB {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);


    // Admin Methods

    AdminEO findAdminByDesigID(String designationID);

    List<AdminEO> findAdminByName(String name);

    List<AdminEO> getAdminEOFindAll();

    // OffenceTypes Methods

    String persistOffenceTypesEO(OffenceTypesEO offenceEO);

    String mergeOffenceTypeEO(OffenceTypesEO offenceEO);

    Boolean removeOffenceTypeEO(Integer offenceID);

    OffenceTypesEO findOffenceTypeByID(Integer offenceID);

    List<OffenceTypesEO> getOffenceTypesEOFindAll();

    List<OffenceTypesEO> findOffenceByVehicleType(String vehicleType);

    List<OffenceTypesEO> findOffenceByOffenceType(String offenceType);
    
    OffenceTypesEO findOffenceByTypeAndVehicle(String offenceType, String vehicleType);


    // Owner Methods

    int persistOwnerEO(OwnerEO ownerEO);

    int mergeOwnerEO(OwnerEO ownerEO);

    OwnerEO findOwnerByAadhar(String ownerAadhar);

    List<OwnerEO> getOwnerEOFindAll();

    List<OwnerEO> findOwnerByName(String ownerName);

    // Role Methods

    List<RoleEO> getRoleEOFindAll();

    // Registration Methods

    String persistRegistrationEO(RegistrationEO regisEO);

    String mergeRegistrationEO(RegistrationEO regisEO);

    String transferRegistrationEO(RegistrationEO regisEO);

    Boolean removeRegistrationEO(String regisID);

    RegistrationEO findRegistrationByID(String regisID);

    RegistrationEO findRegistrationByVehID(Long vehID);

    List<RegistrationEO> getRegistrationEOFindAll();

    // Vehicle Methods

    String persistVehicleEO(VehicleEO vehicleEO);

    String mergeVehicleEO(VehicleEO vehicleEO);

    Boolean removeVehicleEO(Long vehicleID);

    VehicleEO findVehicleByID(Long vehicleID);

    List<VehicleEO> getVehicleEOFindAll();

    List<VehicleEO> findVehicleByType(String vehicleType);

    // VehicleOffence Methods

    String mergeVehicleOffenceEO(VehicleOffenceEO vehOffEO);

    Boolean removeVehicleOffenceEO(VehicleOffenceEO vehOffEO);

    VehicleOffenceEO findVehicleOffenceByID(Long vehicleOffenceID);

    List<VehicleOffenceEO> getVehicleOffenceEOFindAll();

    List<VehicleOffenceEO> findVehicleOffenceByRegisID(String regisID);

    List<VehicleOffenceEO> findVehicleOffenceByStatus(String registrationID,
                                                      Long status);

    Boolean login(AdminEO adminEO);

}

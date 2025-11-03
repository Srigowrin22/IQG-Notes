package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.OwnerEO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.entities.VehicleEO;

public interface RegistrationDAO {

    Integer insertRegistration(RegistrationEO regisEO);

    Integer updateRegistration(RegistrationEO regisEO);

    Integer deleteRegistration(String regisID);

    RegistrationEO findRegistrationByID(String regisID);
    
    RegistrationEO findRegistrationByVehID(Integer vehID);

    List<RegistrationEO> findRegistrationByAadhar(String aadhar);

    List<RegistrationEO> findAllRegistrations();

}

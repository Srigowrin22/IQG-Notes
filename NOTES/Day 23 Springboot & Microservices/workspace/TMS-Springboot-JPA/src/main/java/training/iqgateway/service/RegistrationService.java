package training.iqgateway.service;

import java.util.List;

import training.iqgateway.dto.RegistrationDTO;
import training.iqgateway.entities.RegistrationEO;

public interface RegistrationService {

    String persistRegistrationEO(RegistrationEO regisEO);

    String mergeRegistrationEO(RegistrationEO regisEO);

    Boolean removeRegistrationEO(String regisID);

    RegistrationDTO findRegistrationByID(String regisID);

    RegistrationDTO findRegistrationByVehID(Integer vehID);

    List<RegistrationDTO> findRegistrationByAadhar(String aadhar);

    List<RegistrationDTO> findAllRegistrations();
}

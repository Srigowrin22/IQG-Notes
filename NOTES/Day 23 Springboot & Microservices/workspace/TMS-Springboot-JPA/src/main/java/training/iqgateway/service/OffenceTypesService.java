package training.iqgateway.service;

import java.util.List;

import training.iqgateway.dto.OffenceTypesDTO;
import training.iqgateway.entities.OffenceTypesEO;

public interface OffenceTypesService {
	
    String persistOffenceTypeEO(OffenceTypesEO offenceEO);

    String mergeOffenceTypeEO(OffenceTypesEO offenceEO);

    Boolean removeOffenceTypeEO(OffenceTypesEO offenceEO);

    List<OffenceTypesDTO> getOffenceTypeEOFindAll();

    OffenceTypesEO findOffenceTypeByID(Integer offenceID);

    List<OffenceTypesDTO> findOffenceByVehicleType(String vehicleType);

    List<OffenceTypesDTO> findOffenceByOffenceType(String offenceType);
}

package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.OffenceTypesEO;


public interface OffenceTypesDAO {
    
    int insertOffenceType (OffenceTypesEO offenceEO);
    
    int updateOffenceType (OffenceTypesEO offenceEO);
    
    int deleteOffenceType (Integer offenceID);

    OffenceTypesEO findOffenceTypeByID (Integer offenceID);
    
    List<OffenceTypesEO> findAllOffenceType ();
    
    List<OffenceTypesEO> findOffenceByVehicleType (String vehicleType);
    
    List<OffenceTypesEO> findOffenceByOffenceType (String offenceType);
    
}

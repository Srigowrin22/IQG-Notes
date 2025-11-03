package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.OffenceTypesEO;

public interface OffenceTypesDAO {
	
	Integer insertOffenceTypes(OffenceTypesEO offTypesEO);
	
	Integer updateOffenceTypes (OffenceTypesEO offTypesEO);
	
	Integer deleteOffenceTypes (Integer offenceID);
	
	OffenceTypesEO findByOffenceID (Integer offenceID);
	
	List<OffenceTypesEO> findAllOffenceTypes();
        
        List<OffenceTypesEO> findOffenceTypesByOffenceType(String offenceType);
	
	List<OffenceTypesEO> findOffenceTypesByVehicleType(String vehicleType);
	
	List<OffenceTypesEO> findOffenceTypesByPenalty(Integer penaltyAmt);

}

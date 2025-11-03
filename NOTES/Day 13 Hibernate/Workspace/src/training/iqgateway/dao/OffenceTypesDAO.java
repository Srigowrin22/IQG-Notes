package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.OffenceTypesEO;

public interface OffenceTypesDAO {
	
	public Integer insertOffenceTypes(OffenceTypesEO offTypesEO);
	
	public void updateOffenceTypes (OffenceTypesEO offTypesEO);
	
	public void deleteOffenceTypes (Integer offenceID);
	
	public OffenceTypesEO findByOffenceID (Integer offenceID);
	
	public List<OffenceTypesEO> findAllOffenceTypes();
	
	public List<OffenceTypesEO> findOffenceTypesByVehicleType(String vehicleType);
	
	public List<OffenceTypesEO> findOffenceTypesByPenalty(Integer penaltyAmt);

}

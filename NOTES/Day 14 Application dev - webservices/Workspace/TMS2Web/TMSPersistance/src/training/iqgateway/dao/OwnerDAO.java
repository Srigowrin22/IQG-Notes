package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.OwnerEO;

public interface OwnerDAO {
	
	Integer insertOwner (OwnerEO ownerEO);
    
	Integer updateOwner (OwnerEO ownerEO);
    
	Integer deleteOwner (String ownerAadhar, String password);
    
    OwnerEO findOwnerByAadhar(String ownerAadhar);
    
    List<OwnerEO> findAllOwners ();
    
    List<OwnerEO> findOwnerByName (String ownerName);
    
}

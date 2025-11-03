package training.iqgateway.dao;

import java.util.List;

import training.iqgateway.entities.OwnerEO;

public interface OwnerDAO {
        
    int insertOwner (OwnerEO ownerEO);
    
    int updateOwner (OwnerEO ownerEO);
    
    int deleteOwner (String ownerAadhar, String password);
    
    OwnerEO findOwnerByAadhar(String ownerAadhar);
    
    List<OwnerEO> findAllOwners ();
    
    List<OwnerEO> findOwnerByName (String ownerName);
    
}

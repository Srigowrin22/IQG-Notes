package training.iqgateway.service;

import java.util.List;

import training.iqgateway.dto.OwnerDTO;
import training.iqgateway.entities.OwnerEO;

public interface OwnerService {

    String persistOwnerEO(OwnerEO ownerEO);

    String mergeOwnerEO(OwnerEO ownerEO);

    Boolean removeOwnerEO(String ownerAadhar, String password);

    OwnerEO findOwnerByAadhar(String ownerAadhar);

    List<OwnerDTO> findAllOwners();

    List<OwnerDTO> findOwnerByName(String ownerName);
}


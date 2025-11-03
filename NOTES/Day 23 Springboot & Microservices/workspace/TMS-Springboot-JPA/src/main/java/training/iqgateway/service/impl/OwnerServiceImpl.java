package training.iqgateway.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import training.iqgateway.dto.OwnerDTO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.repository.OwnerRepository;
import training.iqgateway.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    private OwnerDTO toDTO(OwnerEO eo) {
        if (eo == null) return null;
        OwnerDTO dto = new OwnerDTO();
        dto.setOwnerId(eo.getOwnerId());
        dto.setOwnerName(eo.getOwnerName());
        dto.setGender(eo.getGender());
        dto.setOwnerAadhar(eo.getOwnerAadhar());
        dto.setPanCard(eo.getPanCard());
        dto.setPhone(eo.getPhone());
        dto.setAddress(eo.getAddress());
        dto.setPassword(eo.getPassword());
        return dto;
    }

    @Override
    public String persistOwnerEO(OwnerEO ownerEO) {
        if (ownerEO == null) throw new IllegalArgumentException("Owner cannot be null");

        if (ownerRepository.findById(ownerEO.getOwnerAadhar()).isPresent()) {
            throw new RuntimeException("Owner with aadhar already exists: " + ownerEO.getOwnerAadhar());
        }

        Long maxId = ownerRepository.findMaxOwnerId().orElse(0L);
        ownerEO.setOwnerId(maxId + 1);

        ownerRepository.save(ownerEO);
        return "Owner created successfully with ID " + ownerEO.getOwnerId();
    }

    @Override
    public String mergeOwnerEO(OwnerEO ownerEO) {
        if (ownerEO == null || ownerEO.getOwnerAadhar() == null)
            throw new IllegalArgumentException("Owner and aadhar cannot be null");

        OwnerEO existing = ownerRepository.findById(ownerEO.getOwnerAadhar())
                                .orElseThrow(() -> new RuntimeException("Owner not found: " + ownerEO.getOwnerAadhar()));

        // Update fields (example, you can customize)
        existing.setOwnerName(ownerEO.getOwnerName());
        existing.setGender(ownerEO.getGender());
        existing.setPanCard(ownerEO.getPanCard());
        existing.setPhone(ownerEO.getPhone());
        existing.setAddress(ownerEO.getAddress());
        existing.setPassword(ownerEO.getPassword());

        ownerRepository.save(existing);
        return "Owner updated successfully";
    }

    @Override
    public Boolean removeOwnerEO(String ownerAadhar, String password) {
        Optional<OwnerEO> existing = ownerRepository.findById(ownerAadhar);
        if (!existing.isPresent()) return false;

        // Simplistic password check example; you can hash/encrypt
        if (!existing.get().getPassword().equals(password)) {
            throw new RuntimeException("Password mismatch");
        }

        ownerRepository.deleteById(ownerAadhar);
        return true;
    }

    @Override
    public OwnerEO findOwnerByAadhar(String ownerAadhar) {
        return ownerRepository.findById(ownerAadhar).orElse(null);
    }

    @Override
    public List<OwnerDTO> findAllOwners() {
        List<OwnerEO> owners = ownerRepository.findAll();
        List<OwnerDTO> dtos = new ArrayList<>();
        for (OwnerEO eo : owners) {
            dtos.add(toDTO(eo));
        }
        return dtos;
    }

    @Override
    public List<OwnerDTO> findOwnerByName(String ownerName) {
        List<OwnerEO> owners = ownerRepository.findByOwnerName(ownerName);
        List<OwnerDTO> dtos = new ArrayList<>();
        for (OwnerEO eo : owners) {
            dtos.add(toDTO(eo));
        }
        return dtos;
    }
}
package training.iqgateway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import training.iqgateway.dto.VehicleDTO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.repository.VehicleRepository;
import training.iqgateway.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    private VehicleDTO toDTO(VehicleEO eo) {
        if (eo == null) return null;
        VehicleDTO dto = new VehicleDTO();
        dto.setVehicleId(eo.getVehicleId());
        dto.setVehicleBrand(eo.getVehicleBrand());
        dto.setVehicleModel(eo.getVehicleModel());
        dto.setVehicleType(eo.getVehicleType());
        dto.setFuelType(eo.getFuelType());
        dto.setNoOfExhaust(eo.getNoOfExhaust());
        dto.setColor(eo.getColor());
        dto.setManufactureDate(eo.getManufactureDate());
        // NOTE: You might want to convert registrations to a suitable DTO or ID list if needed
        return dto;
    }

    @Override
    public String persistVehicleEO(VehicleEO vehicleEO) {
        if (vehicleEO == null) throw new IllegalArgumentException("Vehicle cannot be null!");

        // check if vehicleId manually present (optional)
        if (vehicleEO.getVehicleId() != null && vehicleRepository.existsById(vehicleEO.getVehicleId())) {
            throw new RuntimeException("Vehicle already exists with ID: " + vehicleEO.getVehicleId());
        }

        Long maxId = vehicleRepository.findMaxVehicleId().orElse(0L);
        vehicleEO.setVehicleId(maxId + 1);

        vehicleRepository.save(vehicleEO);
        return "Vehicle created successfully with ID " + vehicleEO.getVehicleId();
    }

    @Override
    public String mergeVehicleEO(VehicleEO vehicleEO) {
        if (vehicleEO == null || vehicleEO.getVehicleId() == null)
            throw new IllegalArgumentException("Vehicle and Vehicle ID cannot be null.");

        VehicleEO existing = vehicleRepository.findById(vehicleEO.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id " + vehicleEO.getVehicleId()));

        // Update only allowed/needed fields
        existing.setVehicleBrand(vehicleEO.getVehicleBrand());
        existing.setVehicleModel(vehicleEO.getVehicleModel());
        existing.setVehicleType(vehicleEO.getVehicleType());
        existing.setFuelType(vehicleEO.getFuelType());
        existing.setNoOfExhaust(vehicleEO.getNoOfExhaust());
        existing.setColor(vehicleEO.getColor());
        existing.setManufactureDate(vehicleEO.getManufactureDate());

        vehicleRepository.save(existing);
        return "Vehicle updated successfully";
    }

    @Override
    public Boolean removeVehicleEO(Integer vehicleID) {
        if (vehicleID == null) return false;

        if (!vehicleRepository.existsById(vehicleID.longValue())) return false;

        vehicleRepository.deleteById(vehicleID.longValue());
        return true;
    }

    @Override
    public VehicleDTO findVehicleByID(Integer vehicleID) {
        if (vehicleID == null) return null;

        VehicleEO vehicleEO = vehicleRepository.findById(vehicleID.longValue()).orElse(null);
        if (vehicleEO == null) {
            return null;
        }

        return toDTO(vehicleEO); 
    }

    @Override
    public List<VehicleDTO> findAllVehicles() {
        List<VehicleEO> list = vehicleRepository.findAll();
        List<VehicleDTO> dtos = new ArrayList<>();
        for (VehicleEO eo : list) {
            dtos.add(toDTO(eo));
        }
        return dtos;
    }

    @Override
    public List<VehicleDTO> findVehicleByType(String vehicleType) {
        List<VehicleEO> list = vehicleRepository.findByVehicleType(vehicleType);
        List<VehicleDTO> dtos = new ArrayList<>();
        for (VehicleEO eo : list) {
            dtos.add(toDTO(eo));
        }
        return dtos;
    }
}
package training.iqgateway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import training.iqgateway.entities.RegistrationEO;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEO, String> {

    // Find registration by vehicle ID (assumes vehicleId is Long or Integer)
    RegistrationEO findByVehicleVehicleId(Integer vehicleId);

    // Find all registrations for a given owner's aadhar (string)
    List<RegistrationEO> findByOwnerOwnerAadhar(String ownerAadhar);

}
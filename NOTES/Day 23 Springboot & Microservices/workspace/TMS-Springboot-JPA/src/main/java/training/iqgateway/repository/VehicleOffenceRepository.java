package training.iqgateway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import training.iqgateway.entities.VehicleOffenceEO;

@Repository
public interface VehicleOffenceRepository extends JpaRepository<VehicleOffenceEO, Long> {

    @Query("SELECT MAX(v.vehicleOffenceId) FROM VehicleOffenceEO v")
    Optional<Long> findMaxVehicleOffenceId();

    List<VehicleOffenceEO> findByRegistrationRegistrationId(String registrationId);

    List<VehicleOffenceEO> findByRegistrationRegistrationIdAndStatus(String registrationId, Integer status);

}
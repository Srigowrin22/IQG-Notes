package training.iqgateway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import training.iqgateway.entities.VehicleEO;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEO, Long>{

    @Query("SELECT MAX(v.vehicleId) FROM VehicleEO v")
    Optional<Long> findMaxVehicleId();

    List<VehicleEO> findByVehicleType(String vehicleType);
}

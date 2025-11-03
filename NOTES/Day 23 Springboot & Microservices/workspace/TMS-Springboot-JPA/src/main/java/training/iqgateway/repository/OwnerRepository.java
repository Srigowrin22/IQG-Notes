package training.iqgateway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import training.iqgateway.entities.OwnerEO;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEO, String> {

    @Query("SELECT MAX(o.ownerId) FROM OwnerEO o")
    Optional<Long> findMaxOwnerId();

    List<OwnerEO> findByOwnerName(String ownerName);
}

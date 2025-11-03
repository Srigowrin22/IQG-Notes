package training.iqgateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import training.iqgateway.entities.RoleEO;

public interface RoleEORepository extends JpaRepository<RoleEO, Long> {

	RoleEO findByroleName(String roleName);

}

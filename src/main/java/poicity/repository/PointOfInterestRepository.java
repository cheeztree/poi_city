package poicity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poicity.entity.PointOfInterest;

@Repository
public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {

	
	
}

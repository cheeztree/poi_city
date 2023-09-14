package poicity.service;

import java.util.List;

import poicity.dto.PointOfInterestDTO;

public interface PointOfInterestService {
	
	List<PointOfInterestDTO> findByCityId(long city_id);
	
}

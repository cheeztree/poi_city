package poicity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poicity.dto.PointOfInterestDTO;
import poicity.entity.PointOfInterest;
import poicity.entity.PointOfInterestImage;
import poicity.repository.PointOfInterestRepository;
import poicity.service.PointOfInterestService;

@Service
public class PointOfInterestServiceImpl implements PointOfInterestService{

	@Autowired
	PointOfInterestRepository poiRepo;
	
	@Override
	public List<PointOfInterestDTO> findByCityId(long city_id) {
		List<PointOfInterest> listPoi = poiRepo.findAllByCityId(city_id);
		List<PointOfInterestDTO> listPoiDTO = new ArrayList<PointOfInterestDTO>();
		
		for(PointOfInterest poi : listPoi) {
			PointOfInterestDTO poiDTO = new PointOfInterestDTO();
			poiDTO.setId(poi.getId());
			poiDTO.setName(poi.getName());
			poiDTO.setDescription(poi.getDescription());
			poiDTO.setId_city(poi.getCity().getId());
			
			List<Long> id_img = new ArrayList<>();
			for(PointOfInterestImage poiImg : poi.getPoi()) {
				id_img.add(poiImg.getId());
			}
			poiDTO.setId_img(id_img);
			
			listPoiDTO.add(poiDTO);
		}
		
		return listPoiDTO;
	}

}

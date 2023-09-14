package poicity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poicity.dto.ErrorDTO;
import poicity.dto.PointOfInterestDTO;
import poicity.entity.PointOfInterest;
import poicity.repository.PointOfInterestRepository;
import poicity.service.PointOfInterestService;

@RestController
@RequestMapping("/poi")
public class PointOfInterestController {

	@Autowired
	private PointOfInterestRepository poiRepo;
	@Autowired
	PointOfInterestService poiService;
	
	@GetMapping("/getByCityId/{idCity}")
	public ResponseEntity<?> getByCityId(@PathVariable("idCity") long idCity) {
		List<PointOfInterestDTO> listPoi = poiService.findByCityId(idCity);
		
		if(listPoi.size() != 0) {
			return new ResponseEntity<>(listPoi, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorDTO("No PointOfInterest for id '" + idCity + "'"), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		List<PointOfInterest> poi = poiRepo.findAll();
		
		return new ResponseEntity<>(poi, HttpStatus.OK);
	}
	
	@GetMapping("/img")
	public ResponseEntity<?> img() {
		List<PointOfInterest> poi = poiRepo.findAll();
		
		return new ResponseEntity<>(poi, HttpStatus.OK);
	}
	
}

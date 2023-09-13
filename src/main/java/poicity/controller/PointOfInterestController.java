package poicity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poicity.entity.PointOfInterest;
import poicity.repository.PointOfInterestRepository;

@RestController
@RequestMapping("/poi")
public class PointOfInterestController {

	@Autowired
	private PointOfInterestRepository poiRepo;
	
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

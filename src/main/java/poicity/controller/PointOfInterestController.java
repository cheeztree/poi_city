package poicity.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poicity.dto.ErrorDTO;
import poicity.dto.PointOfInterestDTO;
import poicity.dto.UsersPoisChoicesDTO;
import poicity.entity.PointOfInterest;
import poicity.entity.User;
import poicity.entity.UsersPoisChoices;
import poicity.repository.PoiChoicesRepository;
import poicity.repository.PointOfInterestRepository;
import poicity.repository.UsersPoisChoicesRepository;
import poicity.service.PointOfInterestService;
import poicity.service.UserService;

@RestController
@RequestMapping("/poi")
public class PointOfInterestController {

	@Autowired
	PointOfInterestRepository poiRepo;
	@Autowired
	PointOfInterestService poiService;
	@Autowired
	UserService userService;
	@Autowired
	UsersPoisChoicesRepository upcRepo;
	@Autowired
	PoiChoicesRepository poiChoicesRepo;
	
	@PostMapping("/save")
	public ResponseEntity<?> save(Authentication authentication, @RequestBody UsersPoisChoicesDTO dto) {
		if(authentication == null) {
			return new ResponseEntity<>(new ErrorDTO("Token not valid."), HttpStatus.NOT_FOUND);
		}
		
		User user = userService.findByEmail(authentication.getName());
		if(user == null) {
			return new ResponseEntity<>(new ErrorDTO("Email not valid."), HttpStatus.NOT_FOUND);
		}
		
		UsersPoisChoices upc = new UsersPoisChoices();
		try {
			upc.setUser(user);
			upc.setPoi(poiRepo.findById(dto.getId_poi()).get());
			upc.setPoiChoices(poiChoicesRepo.findById(dto.getId_poi_choices()).get());
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorDTO("Some id is not valid."), HttpStatus.NOT_FOUND);
		}
		
		upcRepo.save(upc);
		
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

	
	@GetMapping("/getByCityId/{idCity}")
	public ResponseEntity<?> getByCityId(@PathVariable("idCity") long idCity) {
		List<PointOfInterestDTO> listPoi = poiService.findByCityId(idCity);
		
		if(listPoi.size() != 0) {
			return new ResponseEntity<>(listPoi, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorDTO("No PointOfInterest for city id '" + idCity + "'"), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		List<PointOfInterest> poi = poiRepo.findAll();
		
		return new ResponseEntity<>(poi, HttpStatus.OK);
	}
	
	@GetMapping("/prova")
	public String prova() {
		return "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Document</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <div><a href=\"https://www.google.it/\" target=\"_blank\" rel=\"noopener\">LOREM IPSUM</a></div>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		
	}
	
}

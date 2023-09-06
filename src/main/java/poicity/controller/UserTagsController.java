package poicity.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import poicity.dto.ErrorDTO;
import poicity.dto.UserTagsDTO;
import poicity.service.UserTagsService;

@RestController
@RequestMapping("usertags")
@AllArgsConstructor
public class UserTagsController {

	private UserTagsService service;

	@CrossOrigin
	@GetMapping("/getAll")
	public ResponseEntity<List<UserTagsDTO>> getAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

//	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/saveTags"
//			, produces="application/json"
			)
	
	public ResponseEntity<Object> saveTagsForUser(@RequestBody List<UserTagsDTO> userTags, Authentication authentication) {

		service.saveListUserTagsForUser(userTags, authentication.getName());
		
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/get")
	public ResponseEntity<Object> getByUser(Authentication authentication) {
		if (authentication != null) {
			return new ResponseEntity<>(service.findByEmail(authentication.getName()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorDTO("Authentication not valid"), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/getByLang")
	public ResponseEntity<Object> getByLang(Authentication authentication) {
		if (authentication != null) {
			return new ResponseEntity<>(service.findByEmail(authentication.getName()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorDTO("Authentication not valid"), HttpStatus.UNAUTHORIZED);
		}
	}

}
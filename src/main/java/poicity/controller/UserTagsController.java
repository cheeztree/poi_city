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
	
	public ResponseEntity<Object> saveTagsForUser(@RequestBody List<UserTagsDTO> userTags,
			Authentication authentication) {

		System.out.println(userTags);
		service.saveListUserTagsForUser(userTags, authentication.getName());
//		service.saveListUserTagsForUser(userTags, "christian.llover1a@tel1sone.i1t");
		return new ResponseEntity<>(HttpStatus.OK);

//		try {
//			service.saveListUserTagsForUser(userTags, authentication.getName());
//			return new ResponseEntity<>(null, HttpStatus.OK);
//		} catch(Exception e) {
//			System.out.println("asdasdasdasd");
//			e.printStackTrace();
//			return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.OK);
//		}

	}

	@GetMapping("/get")
	public ResponseEntity<Object> getByUser(Authentication authentication) {
		if (authentication != null) {
			return new ResponseEntity<>(service.findByEmail(authentication.getName()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorDTO("Authentication not valid"), HttpStatus.UNAUTHORIZED);
		}
	}

}
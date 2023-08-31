package poicity.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import poicity.dto.UserDTO;
import poicity.entity.User;
import poicity.repository.UserRepository;
import poicity.service.UserService;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

	private ModelMapper mapper;

	private UserRepository userRepo;
	private UserService userService;

	@PostMapping("create")
	public ResponseEntity<UserDTO> add(@RequestBody UserDTO userDTO) {
		userRepo.save(mapper.map(userDTO, User.class));
		
		return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
	}

	@GetMapping("getById/{id}")
	public ResponseEntity<UserDTO> get(@PathVariable("id") Long id) {
		boolean esiste = userRepo.existsById(id);
		
		if(esiste) {
			User user = userRepo.getReferenceById(id);
			UserDTO userDTO = mapper.map(user, UserDTO.class);
		
			return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
		} else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("getAll")
	public ResponseEntity<List<User>> getAll() {
		List<User> listaUser = userRepo.findAll();
		
		return new ResponseEntity<>(listaUser, HttpStatus.OK);
	}
	
	@PutMapping("update")
	public ResponseEntity<User> update(@RequestBody User user) {
		
		boolean esiste = userRepo.existsById(user.getId());
		
		if(esiste) {		
			userRepo.save(user);
			
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<User> delete(@RequestBody User user) {
		System.out.println(user);
		boolean esiste = userRepo.existsById(user.getId());
		
		if(esiste) {
			userRepo.deleteById(user.getId());
			
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
 
 
//    @GetMapping("affila")
//    public String affila() {
//    	return "AFFILA";
//    }
	
}

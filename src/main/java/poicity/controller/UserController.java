package poicity.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
		userRepo.save(mapper.map(userDTO, User.class));
		return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
	}

//    @GetMapping("affila")
//    public String affila() {
//    	return "AFFILA";
//    }
}

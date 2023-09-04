package poicity.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import poicity.dto.AuthResponse;
import poicity.dto.ErrorDTO;
import poicity.dto.LoginDTO;
import poicity.dto.UserDTO;
import poicity.repository.UserRepository;
import poicity.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private UserRepository userRepo;
	private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
	
	
	@PostMapping("login")
	public ResponseEntity<Object> login(@RequestBody LoginDTO request) {
		System.out.println(request);
		if(userRepo.existsByEmail(request.getEmail())){			
	        String encodedPassword = request.getPassword();
	        if(passwordEncoder.matches(String.valueOf(request.getPassword()), encodedPassword)) {
				System.out.println("2");

				return ResponseEntity.ok(authService.login(request));
	        } else {
				System.out.println("3");
				
				return new ResponseEntity<Object>(new ErrorDTO(new Date(), "Invalid password."), HttpStatus.NOT_FOUND);
			}
		} else {
			System.out.println("1");
			return new ResponseEntity<Object>(new ErrorDTO(new Date(), request.getEmail() +  "' doesn''t exists."), HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping("register")
	public ResponseEntity<Object> register(@RequestBody UserDTO request) {
		System.out.println(request);
		if(userRepo.existsByEmail(request.getEmail())){
			return new ResponseEntity<Object>(new ErrorDTO(new Date(), "User with '" + request.getEmail() +  "' already exists."), HttpStatus.CONFLICT);
		} else {
			System.out.println("ASDASDASDASDASD");
			return ResponseEntity.ok(authService.register(request));
		}
	}
	
	@GetMapping("google")
	public ResponseEntity<String> hello(){
		return new ResponseEntity<>("ASDASDASDASDASDASD", HttpStatus.CREATED);
	}
	
}

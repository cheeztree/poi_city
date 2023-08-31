package poicity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import poicity.dto.AuthResponse;
import poicity.dto.LoginDTO;
import poicity.dto.RegisterDTO;
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
	
	@PostMapping("login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginDTO request) {
		return ResponseEntity.ok(authService.login(request));
	}
	
	@PostMapping("register")
	public ResponseEntity<AuthResponse> register(@RequestBody UserDTO request) {
		if(userRepo.existsByEmail(request.getEmail())){
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		} else {
			return ResponseEntity.ok(authService.register(request));
		}
	}
	
}

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
import poicity.dto.ResetPasswordDTO;
import poicity.dto.UserDTO;
import poicity.entity.User;
import poicity.repository.UserRepository;
import poicity.service.AuthService;
import poicity.utils.JavaMail;
import poicity.utils.PasswordGenerator;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private UserRepository userRepo;
	private final AuthService authService;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginDTO request) {
		if (userRepo.existsByEmail(request.getEmail())) {
			User user = userRepo.findByEmail(request.getEmail());
			if (passwordEncoder.matches(String.valueOf(request.getPassword()), user.getPassword())) {
				return ResponseEntity.ok(authService.login(request));
			} else {
				return new ResponseEntity<Object>(new ErrorDTO(new Date(), "Invalid password."), HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Object>(new ErrorDTO(new Date(), request.getEmail() + "' doesn''t exists."),
					HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody UserDTO request) {
		System.out.println(request);
		if (userRepo.existsByEmail(request.getEmail())) {
			return new ResponseEntity<Object>(
					new ErrorDTO(new Date(), "User with email '" + request.getEmail() + "' already exists."),
					HttpStatus.CONFLICT);
		} else {
			return ResponseEntity.ok(authService.register(request));
		}
	}

	@PostMapping("/resetPassword")
	public ResponseEntity<Object> register(@RequestBody ResetPasswordDTO resetPassDTO) {
		System.out.println(resetPassDTO.getEmail());
		String email = resetPassDTO.getEmail();
		if (userRepo.existsByEmail(email)) {
			
			User user = userRepo.findByEmail(email);
			String newPassword = PasswordGenerator.generate();
			user.setPassword(passwordEncoder.encode(newPassword));

			userRepo.save(user);
			
			JavaMail.mandaEmailXresetPass(user.getEmail(), newPassword, user.getName());
			
		} else {
			return new ResponseEntity<Object>(new ErrorDTO("User with this email doesn't exists."), HttpStatus.NOT_FOUND);
		}

		return null;
	}

	// @GetMapping("google")
	// public ResponseEntity<String> hello(){
	// return new ResponseEntity<>("ASDASDASDASDASDASD", HttpStatus.CREATED);
	// }

}

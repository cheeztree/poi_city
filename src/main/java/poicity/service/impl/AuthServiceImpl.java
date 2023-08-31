package poicity.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import poicity.dto.AuthResponse;
import poicity.dto.LoginDTO;
import poicity.dto.RegisterDTO;
import poicity.dto.UserDTO;

//import org.springframework.security.core.userdetails.User;
import poicity.entity.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import poicity.repository.UserRepository;
import poicity.service.AuthService;
import poicity.service.JwtService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	
	private final ModelMapper mapper;
	private final UserRepository userRepo;
	private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

	@Override
	public AuthResponse login(LoginDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepo.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
	}

	@Override
	public AuthResponse register(UserDTO request) {
		User user = mapper.map(request, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepo.save(user);
		
		return AuthResponse.builder().token(jwtService.getToken(user)).build();
	}

}

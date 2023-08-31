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
import poicity.service.CustomUserDetailsService;
import poicity.service.JwtService;
import poicity.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	
	private final ModelMapper mapper;
	private final UserService userService;
	private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;


	@Override
	public AuthResponse login(LoginDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user = customUserDetailsService.loadUserByEmail(request.getEmail());
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
	}

	@Override
	public AuthResponse register(UserDTO request) {
		User user = mapper.map(request, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
//		userRepo.save(user);
		userService.saveUser(user);

		return AuthResponse.builder().token(jwtService.getToken(user)).build();
	}

}

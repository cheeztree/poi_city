package poicity.service;

import poicity.dto.AuthResponse;
import poicity.dto.LoginDTO;
import poicity.dto.UserDTO;


public interface AuthService {

	AuthResponse login(LoginDTO request);
	
	AuthResponse register(UserDTO request);

	
}

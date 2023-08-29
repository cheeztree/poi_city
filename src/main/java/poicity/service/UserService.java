package poicity.service;

import org.springframework.stereotype.Service;

import poicity.dto.UserDTO;
import poicity.entity.User;

public interface UserService {
	User updateUser(UserDTO userDTO);
}

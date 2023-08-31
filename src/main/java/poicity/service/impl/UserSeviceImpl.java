package poicity.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import poicity.dto.UserDTO;

import poicity.entity.User;
//import org.springframework.security.core.userdetails.User;


import poicity.repository.UserRepository;
import poicity.service.UserService;

@Service
public class UserSeviceImpl implements UserService{

	private ModelMapper mapper;
	private UserRepository userRepo;
	
	@Override
	public User updateUser(UserDTO userDTO) {
		User user = mapper.map(userDTO, User.class);
		userRepo.save(user);
		
		return user;
	}


}

package poicity.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poicity.dto.UserDTO;
import poicity.entity.Role;
import poicity.entity.User;
//import org.springframework.security.core.userdetails.User;
import poicity.repository.RoleRepository;
import poicity.repository.UserRepository;
import poicity.service.UserService;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserSeviceImpl implements UserService {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User updateUser(UserDTO userDTO) {
		User user = mapper.map(userDTO, User.class);
		userRepo.save(user);

		return user;
	}

	@Override
	public void saveUser(User user) {
	
		//ROLE
		Role role = roleRepository.findByName("USER");
		if (role == null) {
			role = checkRoleExist();
		}
		
        Set<Role> hash_Set = new HashSet<Role>();
        hash_Set.add(role);
		user.setRoles(hash_Set);
		
		//LANG
		
		
		userRepo.save(user);
	}
	
    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("USER");
        return roleRepository.save(role);
    }

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

}

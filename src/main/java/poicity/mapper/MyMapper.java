package poicity.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import poicity.dto.UserDTO;
import poicity.entity.User;

//@Service
public class MyMapper extends ModelMapper{
    
//	@Bean
//    public ModelMapper mapper() {
//        return new ModelMapper();
//    }
	
	
	public UserDTO userToUserDTO(User user) {
		UserDTO userDTO = map(user, UserDTO.class);
		userDTO.setLang_id(user.getLang().getId());
		
		return userDTO;
	}
}

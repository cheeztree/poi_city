package poicity.mapper;

import org.modelmapper.ModelMapper;
import poicity.dto.LanguageTextDTO;
import poicity.dto.UserDTO;
import poicity.entity.LanguageText;
import poicity.entity.User;

public class MyMapper extends ModelMapper{
    
	public UserDTO userToUserDTO(User user) {
		UserDTO userDTO = map(user, UserDTO.class);
		userDTO.setLang_id(user.getLang().getId());
		
		return userDTO;
	}
	
	public LanguageTextDTO langTextToLangTextDTO(LanguageText langText) {
		LanguageTextDTO langTextDTO = map(langText, LanguageTextDTO.class);
		return langTextDTO;
	}
}

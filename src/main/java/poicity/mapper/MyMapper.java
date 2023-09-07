package poicity.mapper;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import poicity.dto.LanguageTextDTO;
import poicity.dto.UserDTO;
import poicity.dto.UserTagsDTO;
import poicity.entity.LanguageText;
import poicity.entity.User;
import poicity.entity.UserTags;

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
	
	public HashMap<Long, UserTags> listUserTagsDTOToUserTags(List<UserTagsDTO> listDTO){
		HashMap<Long, UserTags> map = new HashMap<>();
		
		for(UserTagsDTO u : listDTO) {
			map.put(u.getId(), map(u, UserTags.class));
		}
		
		return map;
	}
	
	public Set<UserTags> listUserTagsDTOToUserTags2(List<UserTagsDTO> listDTO){
		Set<UserTags> map = new LinkedHashSet<>();
		
		for(UserTagsDTO u : listDTO) {
			map.add(map(u, UserTags.class));
		}

		return map;
	}
}

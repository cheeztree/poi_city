package poicity.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poicity.dto.UserTagsDTO;
import poicity.entity.User;
import poicity.entity.UserTags;
import poicity.mapper.MyMapper;
import poicity.repository.UserRepository;
import poicity.repository.UserTagsRepository;
import poicity.service.UserTagsService;

@Service
public class UserTagsServiceImpl implements UserTagsService{

	@Autowired
	MyMapper mapper;
	@Autowired
	UserTagsRepository repo;
	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<UserTagsDTO> findAll() {
		List<UserTags> list = repo.findAll();
		List<UserTagsDTO> listDTO = new ArrayList<>();
		
		for(UserTags u : list) {
			listDTO.add(mapper.map(u, UserTagsDTO.class));
		}
		
		return listDTO;
	}

	@Override
	public void saveListUserTagsForUser(List<UserTagsDTO> listDTO, String email) {
		
		User user = userRepo.findByEmail(email);
		Set<UserTags> map = new LinkedHashSet<>();

		for(UserTagsDTO u : listDTO) {
			if(repo.existsById(u.getId())) {
//				System.out.println("esiste");
				map.add(mapper.map(u, UserTags.class));
			} else {
//				System.out.println("NON esiste");

			}
		}
		
//		System.out.println(map);

//		user.setTags(mapper.listUserTagsDTOToUserTags(listDTO));
//		user.setTags(mapper.listUserTagsDTOToUserTags2(listDTO));
		
		user.setTags(map);
		userRepo.save(user);
		
	}

	@Override
	public Set<UserTagsDTO> findByEmail(String email) {
		User user = userRepo.findByEmail(email);
		
		Set<UserTags> map = user.getTags();
		Set<UserTagsDTO> mapDTO = new LinkedHashSet<>();
		
		for(UserTags u : map){
			mapDTO.add(mapper.map(u, UserTagsDTO.class));
		}
		
		return mapDTO;
	}
	
	
	
}

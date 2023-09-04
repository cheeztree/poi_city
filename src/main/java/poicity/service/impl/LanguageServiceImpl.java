package poicity.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poicity.dto.LanguageDTO;
import poicity.dto.LanguageTextDTO;
import poicity.entity.Language;
import poicity.entity.LanguageText;
import poicity.repository.LanguageRepository;
import poicity.repository.LanguageTextRepository;
import poicity.service.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	LanguageRepository langRepo;
	@Autowired
	LanguageTextRepository langTextRepo;

	@Override
	public List<Language> findAll() {
		return langRepo.findAll();
	}

	@Override
	public List<LanguageDTO> getOnlyActive() {
//		List<LanguageDTO> listLangActive = Arrays.asList(mapper.map(langRepo.findByAttivoTrue(), LanguageDTO.class));
//		System.out.println(langRepo.findByAttivoTrue());
//		List<String> list = Collections.singletonList( "data" );
//		System.out.println(listLangActive);

		List<Language> listActives = langRepo.findByAttivoTrue();

		List<LanguageDTO> listDTOs = new ArrayList<>();
		for (Language lang : listActives) {
			listDTOs.add(new LanguageDTO(lang.getId(), lang.getLanguage()));
		}

		return listDTOs;
	}


}

package poicity.service;

import java.util.List;

import poicity.dto.LanguageTextDTO;

public interface LanguageTextService {
	List<LanguageTextDTO> findByLangId(Long lang_id);
}

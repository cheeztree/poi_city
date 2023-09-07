package poicity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poicity.entity.LanguageText;

@Repository
public interface LanguageTextRepository extends JpaRepository<LanguageText, Long>{
	
	List<LanguageText> findByLangId(long id);
	List<LanguageText> findAllByLangIdAndUserTagsIdIn(long id_lang, List<Long> id_userTags);
	List<LanguageText> findAllByUserTagsId(long id);
	
}

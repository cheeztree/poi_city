package poicity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import poicity.dto.LanguageDTO;
import poicity.entity.Language;
import poicity.repository.LanguageRepository;
import poicity.service.LanguageService;

@RestController
@RequestMapping("/lang")
@RequiredArgsConstructor
public class LanguageController {

//	@Autowired
//	LanguageRepository langRepo; 

	@Autowired
	private LanguageService langService;

//    @CrossOrigin
	@GetMapping("getAll")
	public ResponseEntity<List<Language>> getAll() {
		List<Language> listaLangs = langService.findAll();

		return new ResponseEntity<>(listaLangs, HttpStatus.OK);
	}

//	@CrossOrigin
	@GetMapping("getOnlyActive")
	public ResponseEntity<List<LanguageDTO>> getAll2() {
		return new ResponseEntity<>(langService.getOnlyActive(), HttpStatus.OK);
	}

}

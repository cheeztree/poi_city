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
import poicity.entity.Language;
import poicity.repository.LanguageRepository;

@RestController
@RequestMapping("/lang")
@RequiredArgsConstructor
public class LanguageController {

	@Autowired
	LanguageRepository langRepo; 
	
    @CrossOrigin
	@GetMapping("getAll")
	public ResponseEntity<List<Language>> getAll() {
		List<Language> listaLangs = langRepo.findAll();
		
		return new ResponseEntity<>(listaLangs, HttpStatus.OK);
	}
	
	@GetMapping("getAll2")
	public ResponseEntity<List<Language>> getAll2() {
		List<Language> listaLangs = langRepo.findAll();
		
		return new ResponseEntity<>(listaLangs, HttpStatus.OK);
	}
		
}

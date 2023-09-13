package poicity.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poicity.dto.ErrorDTO;
import poicity.utils.FilesUtils;

@RestController
@RequestMapping("img")
public class ImagesController {

	@GetMapping(value = "/logo", produces = MediaType.IMAGE_PNG_VALUE)
	@Async
	public ResponseEntity<?> getLogo() {
		
		String pathLogo = FilesUtils.verificaEcreaPathXlogo();
		
		try {
	        InputStream is = Files.newInputStream(Paths.get(pathLogo));

			byte[] bytes = StreamUtils.copyToByteArray(is);
			is.close();

			return new ResponseEntity<>(bytes, HttpStatus.OK);
		} catch (Exception e) {
//    		e.printStackTrace();

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(new MediaType("application", "json"));

			ResponseEntity<?> res = new ResponseEntity<>(new ErrorDTO("Image not found."), responseHeaders, HttpStatus.NOT_FOUND);
			return res;
		}
	}
}

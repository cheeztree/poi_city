package poicity.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import poicity.dto.ErrorDTO;
import poicity.dto.UserDTO;
import poicity.entity.User;
import poicity.mapper.MyMapper;
import poicity.repository.RoleRepository;
import poicity.repository.UserRepository;
import poicity.utils.FilesUtils;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

	private MyMapper mapper;
	private UserRepository userRepo;
	private RoleRepository roleRepository;

	@PostMapping("create")
	public ResponseEntity<UserDTO> add(@RequestBody UserDTO userDTO) {
		if (userDTO.getAvatar().equals("") || userDTO.getAvatar() == null || userDTO.getAvatar().equals("string")) {
			userDTO.setAvatar(FilesUtils.immagazzinaAvatarDefault2());
		}

		userRepo.save(mapper.map(userDTO, User.class));

		return new ResponseEntity<>(userDTO, HttpStatus.CREATED);

	}

	@GetMapping("getById/{id}")
	public ResponseEntity<UserDTO> get(@PathVariable("id") Long id) {
		boolean esiste = userRepo.existsById(id);

		if (esiste) {
			User user = userRepo.getReferenceById(id);
			UserDTO userDTO = mapper.map(user, UserDTO.class);

			return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("get")
	public ResponseEntity<UserDTO> get(Authentication authentication) {
		UserDTO userDTO = mapper.userToUserDTO(userRepo.findByEmail(authentication.getName()));

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@GetMapping("getAll")
	public ResponseEntity<List<User>> getAll() {
		List<User> listaUser = userRepo.findAll();
		System.out.println(roleRepository.findAll());

		return new ResponseEntity<>(listaUser, HttpStatus.OK);
	}

	@PutMapping("update")
	public ResponseEntity<User> update(@RequestBody User user) {

		boolean esiste = userRepo.existsById(user.getId());

		if (esiste) {
			userRepo.save(user);

			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("delete")
	public ResponseEntity<User> delete(@RequestBody User user) {
		System.out.println(user);
		boolean esiste = userRepo.existsById(user.getId());

		if (esiste) {
			userRepo.deleteById(user.getId());

			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/uploadImgUser")
	public ResponseEntity<?> uploadImgUser(@RequestParam("image") MultipartFile file,
			@RequestParam(value = "email") String email) {
		if (!userRepo.existsByEmail(email)) {
			return new ResponseEntity<>(new ErrorDTO("User with email '" + email + "' already exists."),
					HttpStatus.UNAUTHORIZED);
		}

		if (byteToMB(file.getSize()) > 5) {
			return new ResponseEntity<>(new ErrorDTO("Files sizes limit is 5MB"), HttpStatus.UNAUTHORIZED);
		}

		String pathImg = FilesUtils.immagazzinaImg(file);

		User user = userRepo.findByEmail(email);
		user.setAvatar(pathImg);

		userRepo.save(user);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	private double byteToMB(long bytes) {
		double MB = (double) bytes / 1024 / 1024;
		return MB;
	}

	@GetMapping(value = "/avatarByPath/{path}", produces = "image/*")
	public ResponseEntity<byte[]> getImage(@PathVariable("path") String path) throws IOException {

		InputStream is = new FileInputStream(path);
		byte[] bytes = StreamUtils.copyToByteArray(is);

		return new ResponseEntity<>(bytes, HttpStatus.OK);

	}

	@GetMapping(value = "/avatar/{UserId}", produces = "image/*")
	public ResponseEntity<?> getImageByUserId(@PathVariable("UserId") Long UserId) throws IOException {

		User user;

		try {
			user = userRepo.findById(UserId).get();
		} catch (Exception e) {
//    		e.printStackTrace();
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(new MediaType("application", "json"));

			ResponseEntity<?> res = new ResponseEntity<>(new ErrorDTO("User not found."), responseHeaders, HttpStatus.NOT_FOUND);
			return res;
			

		}

		try {
			InputStream is = new FileInputStream(user.getAvatar());
			byte[] bytes = StreamUtils.copyToByteArray(is);
			is.close();

//            HttpHeaders responseHeaders = new HttpHeaders();
//            responseHeaders.setContentType(new MediaType("image", "*"));

			return new ResponseEntity<>(bytes
//    				, responseHeaders
					, HttpStatus.OK);
		} catch (Exception e) {
//    		e.printStackTrace();
//			return new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(new MediaType("application", "json"));

			ResponseEntity<?> res = new ResponseEntity<>(new ErrorDTO("Image not found."), responseHeaders, HttpStatus.NOT_FOUND);
			return res;
		}

	}

//	@GetMapping("/downloadImgUser")
//	public ResponseEntity<Resource> downloadImgUser(
////			Authentication authentication
//			) throws IOException {
////		User user = userRepo.findByEmail(authentication.getName());
//		User user = userRepo.findByEmail("christian.llovera@telsone.it");
//		
//	    Path path = Paths.get(user.getAvatar());
//	    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//
////	    return ResponseEntity.ok()
////	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
////	            .body(resource);
//	    
//		return new ResponseEntity<>(resource, HttpStatus.OK);
//
//
//		
//	}

//	@GetMapping("/download/{fileImg}")
//	public ResponseEntity<Resource> downloadImgUser(@PathVariable("fileImg") String fileImg) throws IOException{
//		User user = userRepo.findByEmail("christian.llovera@telsone.it");
//		
//	    Path path = Paths.get(user.getAvatar());
//	    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get("C:\\Users\\Telsone\\eclipse-workspace\\poi_city_be\\img\\"+fileImg)));
//	    
//		return new ResponseEntity<>(resource, HttpStatus.OK);
//
//	}

	// @GetMapping("affila")
	// public String affila() {
	// return "AFFILA";
	// }

}

package poicity.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import poicity.dto.ErrorDTO;
import poicity.dto.LoginDTO;
import poicity.entity.User;
import poicity.repository.UserRepository;
import poicity.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

    @GetMapping
    public String index(Model model) {
    	model.addAttribute("chiave", "valore");
        return "admin_login";
    }
    
    @PostMapping
    public String login(@ModelAttribute(name = "loginForm") LoginDTO loginDTO, Model model) {
    	System.out.println(loginDTO);

		if (userService.existsByEmail(loginDTO.getEmail())) {
			User user = userService.findByEmail(loginDTO.getEmail());
			if (passwordEncoder.matches(String.valueOf(loginDTO.getPassword()), user.getPassword())) {
				return "admin_panel";
			} else {
				System.out.println("NON MATCHA");
	            model.addAttribute("logError","logError");
	            model.addAttribute("param","param");
	            
//	            return "/admin?loginError=true";
			}
		} else {
			System.out.println("NON esiste mail");
		}
	
        return "admin_login";
    }
}

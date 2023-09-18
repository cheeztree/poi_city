package poicity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import poicity.dto.AuthResponse;
import poicity.dto.ErrorDTO;
import poicity.dto.LoginDTO;
import poicity.entity.Role;
import poicity.entity.User;
import poicity.repository.RoleRepository;
import poicity.repository.UserRepository;
import poicity.service.AuthService;
import poicity.service.CustomUserDetailsService;
import poicity.service.JwtService;
import poicity.service.UserService;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private final AuthService authService;
	private final AuthenticationManager authenticationManager;
	private final CustomUserDetailsService customUserDetailsService;
	private final JwtService jwtService;
	private final RoleRepository roleRepo;
    @GetMapping
    public String index(Model model) {
    	model.addAttribute("chiave", "valoreeeeeeeee");
        return "admin_login";
    }
    
    @PostMapping
    public String login(@ModelAttribute(name = "loginForm") LoginDTO loginDTO, Model model, HttpServletResponse res) {
//    	System.out.println(loginDTO);
    	
		if (userService.existsByEmail(loginDTO.getEmail())) {
			User user = userService.findByEmail(loginDTO.getEmail());
			Role roleAdmin = roleRepo.findByName("ADMIN");
//			System.out.println(user);
//			System.out.println(user.getRoles().contains(role));

			if(!user.getRoles().contains(roleAdmin)){
				return "redirect:admin?notAdmin";
			}
			
			if (passwordEncoder.matches(String.valueOf(loginDTO.getPassword()), user.getPassword())) {

				String token = authService.login(loginDTO).getToken();
				
				Cookie cookie = new Cookie("token", token);
				cookie.setMaxAge(Integer.MAX_VALUE);
				res.addCookie(cookie);
				
//				return "admin_panel";
			} else {
				System.out.println("PASSWORD NON MATCHA");
	            
				return "redirect:admin?wrongPassword";
			}
		} else {
			System.out.println("NON esiste mail");
            
			return "redirect:admin?wrongMail";

		}
	
        return "admin_panel";
    }
}

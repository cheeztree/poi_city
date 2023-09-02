package poicity.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//import org.springframework.security.core.userdetails.User;
//import poicity.entity.User;

import lombok.RequiredArgsConstructor;
import poicity.mapper.MyMapper;
import poicity.repository.UserRepository;
import poicity.service.CustomUserDetailsService;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

	@Autowired
	UserRepository userRepo;
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public MyMapper model() {
		return new MyMapper();
	}
	
	@Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
	
	@Bean
	public AuthenticationProvider authenticationProvicer() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEndecoder());
		return authenticationProvider;
	}

    public static String bCrypt(String data) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        return passwordEncoder.encode(data);
    }
	
	@Bean
	public PasswordEncoder passwordEndecoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> customUserDetailsService.loadUserByEmail(email);
//        return username -> customUserDetailsService.loadUserByUsername(username);
    }

}

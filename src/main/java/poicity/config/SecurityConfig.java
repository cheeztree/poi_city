package poicity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAthenticationFilter;
	private final AuthenticationProvider authProvider;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http
                .csrf().disable()
				.authorizeHttpRequests (
						authRequest -> authRequest
				.requestMatchers("/swagger-ui/**").permitAll() // http://localhost:8080/swagger-ui/index.html
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/lang/getOnlyActive").permitAll()
//				.requestMatchers("/users/**").permitAll()
//                .requestMatchers("/users").hasRole("USER")
//				.anyRequest().permitAll()
				.anyRequest().authenticated()
				)
//				.formLogin(withDefaults()) //CHIEDE IL LOGIN AD OGNI CAMBIO PAGINA O REFRESH
				.sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authProvider)
				.addFilterBefore(jwtAthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}
		
//		return http
//				.csrf(
//					csrf ->	csrf
//				.disable())
//				.authorizeHttpRequests (authRequest ->
//				authRequest
//				.requestMatchers("/swagger-ui/**").permitAll() // http://localhost:8080/swagger-ui/index.html/
//				.requestMatchers("/auth/**").permitAll()
////				.requestMatchers("/users/**").permitAll()
////                .requestMatchers("/users").hasRole("USER")
//				.anyRequest().permitAll()
////				.anyRequest().authenticated()
//				)
////				.formLogin(withDefaults()) //CHIEDE IL LOGIN AD OGNI CAMBIO PAGINA O REFRESH
//				.sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.authenticationProvider(authProvider)
//				.addFilterBefore(jwtAthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//				.build();
//		
//	}
	
}

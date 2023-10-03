package poicity;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Main extends SpringBootServletInitializer  {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(SpringCpanelApplication.class);
//	}
	
	public static void main(String[] args) {
		Properties props = getInit();
//		new SpringApplicationBuilder(Main.class).properties(props).run(args);

//		Properties props = new Properties();
//		props.put("server.port", "8081");
//		props.put("spring.datasourc.url", "jdbc:mysql://185.81.4.53:3306/baritst_poicity");
//		props.put("spring.datasource.username", "baritst_root");
//		props.put("spring.datasource.password", "G0K5#+5Irp.9");
//		props.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//		props.put("logo", "https://i.ibb.co/c6GGm20/logo.png");
//		props.put("defaultAvatar", "https://i.ibb.co/xGZtcXS/dafault-avatar.png");
		
		new SpringApplicationBuilder(Main.class).properties(props).run(args);
				
		
		System.out.println(" /**********************************************\\");
		System.out.println("| ----------------SERVER AVVIATO---------------- |");
		System.out.println(" \\**********************************************/");
	}

	private static Properties getInit() {
		Properties props = new Properties();
		HashMap<String, String> mapConfig = ConfigIni.loadConfigIni("applicationProperties");

		for (String chiave : mapConfig.keySet()) {
			props.put(chiave, mapConfig.get(chiave));
		}

		return props;
	}

//	public static void main(String[] args) {
//		SpringApplication.run(Main.class, args);
//		
//		System.out.println(" /**********************************************\\");
//		System.out.println("| ----------------SERVER AVVIATO---------------- |");
//		System.out.println(" \\**********************************************/");
//	}

//	@Bean
//	public ModelMapper modelMapper(){
//		return new ModelMapper();
//	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/greeting-javaconfig").allowedOrigins();
//			}
//		};
//	}

//	public Docket apis() {
//		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("poicity")).build();
//	}

}

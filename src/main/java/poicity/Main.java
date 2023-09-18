package poicity;

import java.util.HashMap;
import java.util.Properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
public class Main {

	
	
	public static void main(String[] args) {
        Properties props = getInit();

        new SpringApplicationBuilder(Main.class)
            .properties(props).run(args);
		
		System.out.println(" /**********************************************\\");
		System.out.println("| ----------------SERVER AVVIATO---------------- |");
		System.out.println(" \\**********************************************/");
	}
	
	private static Properties getInit() {
        Properties props = new Properties();
		HashMap<String, String> mapConfig = ConfigIni.loadConfigIni();
		
        for(String chiave : mapConfig.keySet()) {
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

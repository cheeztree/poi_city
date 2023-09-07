package poicity.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FilesUtils {

	public static String immagazzinaImg(MultipartFile file) {
		String newPath = "";
		
		Path pathXimg = Paths.get(new File("").getAbsolutePath()+"\\img");
		
        try {
			Files.createDirectories(pathXimg);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		String nuovoNome = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSS"));
        File fileTo = new File(pathXimg.toString() + "\\" + nuovoNome + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
        
        try {
			file.transferTo(fileTo);
			newPath = fileTo.getAbsolutePath();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
        
		return newPath;
	}
	
}

package poicity.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import poicity.ConfigIni;

public class FilesUtils {

	public static String immagazzinaImg(MultipartFile file, Long idUser) {
		String newPath = "";

		Path pathXimg = Paths.get(new File("").getAbsolutePath() + "\\img");

		try {
			Files.createDirectories(pathXimg);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		String nuovoNome = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSS"));
		String nuovoNome = String.valueOf(idUser);
		File fileTo = new File(
				pathXimg.toString() + "\\" + nuovoNome + "." + FilenameUtils.getExtension(file.getOriginalFilename()));

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

//	public static String immagazzinaAvatarDefault(Long idUser) {
//		String newPath = "";
//		File defaultAvatar = new File("src\\main\\java\\poicity\\utils\\img\\dafault_avatar.png");
//
//		Path pathXimg = Paths.get(new File("").getAbsolutePath() + "\\img");
//
//		try {
//			Files.createDirectories(pathXimg);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
////		String nuovoNome = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSS"));
//		String nuovoNome = String.valueOf(idUser);
//		File fileTo = new File(pathXimg.toString() + "\\" + nuovoNome + "." + FilenameUtils.getExtension(defaultAvatar.getName()));
//
//		try {
//			Files.copy(Paths.get(defaultAvatar.getAbsolutePath()), Paths.get(fileTo.getAbsolutePath()),
//					StandardCopyOption.REPLACE_EXISTING);
//			newPath = fileTo.getAbsolutePath();
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return newPath;
//	}

	public static String immagazzinaAvatarDefault2(Long idUser) {
		String newPath = "";
//		File defaultAvatar = new File("src\\main\\java\\poicity\\utils\\img\\dafault_avatar.png");
		BufferedInputStream in = null;
		try {
//			in = new BufferedInputStream(new URL("https://i.ibb.co/xGZtcXS/dafault-avatar.png").openStream());
			in = new BufferedInputStream(new URL(ConfigIni.OnlineLinkDefaultAvatar()).openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		Path pathXimg = Paths.get(new File("").getAbsolutePath() + "\\img");

		try {
			Files.createDirectories(pathXimg);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		String nuovoNome = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSS"));
		String nuovoNome = String.valueOf(idUser);
		File fileTo = new File(pathXimg.toString() + "\\" + nuovoNome + ".png");

//        System.out.println(fileTo.getAbsolutePath());
		try {
			Files.copy(in, Paths.get(fileTo.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
			newPath = fileTo.getAbsolutePath();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return newPath;
	}

	public static String verificaEcreaPathXlogo() {
		
		Path pathXimg = Paths.get(new File("").getAbsolutePath() + "\\img");
		
		try {
			Files.createDirectories(pathXimg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File fileTo = new File(pathXimg.toString() + "\\" + "logo.png");
		String newPath = fileTo.getAbsolutePath();

		if(!fileTo.exists()) {
//			String linkLogo = "https://i.ibb.co/c6GGm20/logo.png";
			String linkLogo = ConfigIni.OnlineLinkLogo();
			BufferedInputStream in = null;
			try {
				in = new BufferedInputStream(new URL(linkLogo).openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				Files.copy(in, Paths.get(fileTo.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

//		verificaSeLogoCambiato();
		
		return newPath;

	}

	private static void verificaSeLogoCambiato() {

		BufferedImage img1 = null;
		BufferedImage img2 = null;
		try {
			img1 = ImageIO.read(new File("https://i.ibb.co/c6GGm20/logo.png"));
			img2 = ImageIO.read(new File("src/main/java/utils/img/logo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String linkLogo = "https://i.ibb.co/c6GGm20/logo.png";
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new URL(linkLogo).openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
			for (int x = 0; x < img1.getWidth(); x++) {
				for (int y = 0; y < img1.getHeight(); y++) {
					if (img1.getRGB(x, y) != img2.getRGB(x, y))
						System.out.println("NO");

					// return false;
				}
			}
		} else {
			System.out.println("NO");

//	        return false;
		}
		System.out.println("SI");
//	     return true;

	}

}

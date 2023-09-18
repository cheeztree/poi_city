package poicity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;

public class ConfigIni {

	public static HashMap<String, String> loadConfigIni() {

		Path path = Paths.get(new File("").getAbsolutePath() + "/config.ini");

		String mexErrore = "Il file 'config.ini' non è stato trovato o non è formattato correttamente.\n"
				+ "Per favore, verifica la directory '" + path + "'\n\n"
				+ "File di esempio:\n"
				+ "https://politecnicobari-my.sharepoint.com/:u:/g/personal/c_llovera_studenti_poliba_it/EW5RQ7dUGopIpLSlnOTcTnEB3jRwFGlpLne96OwG4U3nug?e=B0mu3F";

		Ini ini = null;
		try {
			ini = new Ini(path.toFile());
		} catch (IOException e) {
			System.err.println(mexErrore);
			System.exit(1);
		}

		if (ini == null) {
			System.err.println(mexErrore);
			System.exit(1);
		}

		HashMap<String, String> map = new HashMap<>();

		for (String sectionName : ini.keySet()) {
			Section section = ini.get(sectionName);
			if (sectionName.equals("applicationProperties")) {
				for (String optionKey : section.keySet()) {
					map.put(optionKey, section.get(optionKey));
				}
			}
		}
//		System.out.println(map);
		return map;
	}
}

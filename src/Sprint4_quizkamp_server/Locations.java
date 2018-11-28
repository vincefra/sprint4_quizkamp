package Sprint4_quizkamp_server;

import java.io.File;

public class Locations {

    public static String questionsPath() {
        return new File("").getAbsolutePath() + "\\sprint4_quizkamp\\src\\Sprint4_quizkamp_server\\Server\\Frågor";
        //return "C:\\Users\\Asd\\Dropbox\\Nackademin\\Kurs Objektorienterad programmering och Java\\Quizkampen\\sprint4_quizkamp\\src\\Sprint4_quizkamp_server\\Server\\Frågor";
    }
    
    public static String settingsPath() {
        return new File("").getAbsolutePath() + "\\sprint4_quizkamp\\src\\Sprint4_quizkamp_server\\Server\\GameProperties.properties";
        //return "C:\\Users\\Asd\\Dropbox\\Nackademin\\Kurs Objektorienterad programmering och Java\\Quizkampen\\sprint4_quizkamp\\src\\Sprint4_quizkamp_server\\Server\\GameProperties.properties";
    }
    
    public static String bgPath() {
        return new File("").getAbsolutePath() + "\\sprint4_quizkamp\\src\\background.jpg";
    }
}

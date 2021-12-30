package Helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppSettings {

    public static String nodesFilePath;
    public static String usersFilePath;
    public static boolean isLive;

    public static void init() throws IOException {

        Properties prop = new Properties();
        String fileName = "src/main/resources/app.config";
        FileInputStream fis = new FileInputStream(fileName);
        prop.load(fis);

        nodesFilePath = prop.getProperty("filesystem.path.nodes");
        usersFilePath = prop.getProperty("filesystem.path.users");
        isLive = false;
    }
}



package Helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppSettings {

    public static String MongoDBUsername;
    public static String MongoDBPassword;

    public static void Init() throws IOException {

        Properties prop = new Properties();
        String fileName = "src/main/resources/app.config";
        FileInputStream fis = new FileInputStream(fileName);
        prop.load(fis);

        MongoDBUsername = prop.getProperty("mongodb.username");
        MongoDBPassword = prop.getProperty("mongodb.password");
    }
}



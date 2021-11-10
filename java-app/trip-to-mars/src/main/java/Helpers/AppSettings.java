package Helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppSettings {

    public static String mongoDBUsername;
    public static String mongoDBPassword;
    public static String mongoDBDatabase;
    public static String mongoDBCollectionNodes;
    public static String mongoDBCollectionUsers;

    public static void init() throws IOException {

        Properties prop = new Properties();
        String fileName = "src/main/resources/app.config";
        FileInputStream fis = new FileInputStream(fileName);
        prop.load(fis);

        mongoDBUsername = prop.getProperty("mongodb.username");
        mongoDBPassword = prop.getProperty("mongodb.password");
        mongoDBDatabase = prop.getProperty("mongodb.database");
        mongoDBCollectionNodes = prop.getProperty("mongodb.collection.nodes");
        mongoDBCollectionUsers = prop.getProperty("mongodb.collection.users");
    }
}



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AppSettings {

    public static String MongoDBUsername;
    public static String MongoDBPassword;

    public static void Init() {
        System.out.println(System.getProperty("user.dir"));
        Properties prop = new Properties();
        String fileName = "src/main/resources/app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            System.out.println("Not found");
            // FileNotFoundException catch is optional and can be collapsed
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(prop.getProperty("app.name"));
        System.out.println(prop.getProperty("app.version"));
    }
}



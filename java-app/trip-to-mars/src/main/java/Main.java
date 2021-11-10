import Helpers.AppSettings;
import Helpers.MapHelper;
import Helpers.MongoDBHelper;
import Models.UserSettings;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Started");

        AppSettings.Init();
        MongoDBHelper.Init();

        // TODO: Get saved settings
        UserSettings settings = new UserSettings();
        settings.SavedItems = List.of("PEN");



        MapHelper.BuildMap();
        MapHelper.ValidateMap(settings);

// SHOW NODES
//        ArrayList<Node> testElements = MongoDBHelper.GetAll();
//        testElements.forEach(System.out::println);

    }
}

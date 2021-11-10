import Helpers.*;
import Models.UserSettings;

import java.util.List;

import static Helpers.IOUtilities.*;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Started");


        AppSettings.init();
        MongoDBHelper.init();

        // TODO: Get saved settings
        UserSettings settings = new UserSettings();
        settings.setSavedItems(List.of("PEN"));


        MapHelper.buildMap();
        MapHelper.MapData mapData = MapHelper.validateMap(settings);

        getStringFromConsole("Awaiting input ...");

        printLine("Nodes: " + mapData.MapValidationData.ExploredNodes.size() + " / " + mapData.MapNodes.size());
        printLine("Total endings: " + mapData.MapValidationData.Endings.size());

// SHOW NODES
//        ArrayList<Node> testElements = MongoDBHelper.GetAll();
//        testElements.forEach(System.out::println);

    }
}

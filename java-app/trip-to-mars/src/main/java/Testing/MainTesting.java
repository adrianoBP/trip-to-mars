package Testing;

import Helpers.AppSettings;
import Helpers.MapNav;
import Models.NodeData.Node;
import Models.NodeData.Option;
import Models.Utils.NodeCollection;

import java.util.ArrayList;
import java.util.List;

import static Helpers.Standard.ConsoleHelper.printLine;

public class MainTesting {

    public static void main(String[] args) throws Exception {

        try {
            Node mainNode = new Node("Malfunction", "According to the systems, there is a malfunction with the spacecraft ventilation", true);
            Node option1 = new Node("Turn back", "Sample description 1");
            Node option2 = new Node("Try and fix", "Sample description 2");

            // Add all the nodes to the collection
            NodeCollection collection = new NodeCollection(true);
            collection.insertNode(mainNode);
            collection.insertNode(option1);
            collection.insertNode(option2);

            // Crete the options
            List<Option> options = new ArrayList<>();
            options.add(new Option(option1.getId(), 0));

            // Link the options to the node
            collection.addNodeOptions(mainNode.getId(), options);

            // Create new MapNavigation object to validate the map
            MapNav mapNavigation = new MapNav(null, collection);

            printLine(collection.toString());

        } catch (Exception ex) {
            printLine("[ERROR] " + ex.getMessage());
        }
    }
}

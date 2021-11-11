import Helpers.*;
import Models.Node;
import Models.UserSettings;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static Helpers.IOUtilities.*;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Started");

        AppSettings.init();
        FSHelper fsHelper = new FSHelper();

        // TODO: Get saved settings
        UserSettings userSettings = new UserSettings();

        MapHelper.buildMap();
        MapHelper.MapData mapData = MapHelper.validateMap(userSettings, fsHelper);

        getStringFromConsole("Awaiting input ...");

        printLine("Nodes: " + mapData.getMapValidationData().getExploredNodes().size() + " / " + mapData.getMapNodes().size());
        printLine("Total endings: " + mapData.getMapValidationData().getEndings().size());

        printLine();

        printLine("-- GAME START --");
        printLine();

        boolean isUserChoice = false;
        Node currentNode = mapData.getStartingNode();
        while (currentNode.getOptions().size() > 0) {

            List<Node> availableNodes = LogicHelper.getNextNodes(mapData.getMapNodes(), currentNode, userSettings);

            if (!StringUtils.isBlank(currentNode.getItemToSave()))
                LogicHelper.SaveResult(currentNode.getItemToSave(), userSettings); // TODO

            // We don't want to print the selected choice again
            if (isUserChoice && availableNodes.size() == 1) {
                currentNode = mapData.getMapNodes().get(availableNodes.get(0).getId());
                isUserChoice = false;
                continue;
            }

            printLine();
            printLine(currentNode.getTitle());

            isUserChoice = false;

            if (availableNodes.size() == 0) {  // End reached
                currentNode = new Node();
            } else if (availableNodes.size() == 1) {  // System or chance based decision
                currentNode = mapData.getMapNodes().get(availableNodes.get(0).getId());
            } else {  // User based decision

                for (int i = 0; i < availableNodes.size(); i++) {
                    printLine("[" + (i + 1) + "] " + availableNodes.get(i).getTitle());
                }

                int selectedOption = getIntFromConsole() - 1;
                // TODO: Handle selected < 0 or > size

                currentNode = mapData.getMapNodes().get(availableNodes.get(selectedOption).getId());

                isUserChoice = true;
            }

            if (!isUserChoice)
                getStringFromConsole("Press ENTER to continue  ...");
        }

        printLine();
        printLine(currentNode.getTitle());

        printLine("-- GAME END --");
    }
}

import Helpers.*;
import Models.NodeData.Node;
import Models.Utils.NodeCollection;
import Models.Utils.Step;
import Models.UserSettings;

import static Helpers.Standard.ConsoleHelper.*;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Started");

        AppSettings.init();

        UserSettings userSettings = new UserSettings();

        MapHelper.buildMap();

        newGame(userSettings);

        // TODO: Testing - First part of map contains all cases
        // TODO(Investigation): do we need to save to file?

        printLine("-- GAME END --");
    }

    private static void newGame(UserSettings userSettings) throws Exception {

        NodeCollection nodeCollection = MapHelper.buildMap();
        MapNav mapNavigation = new MapNav(userSettings, nodeCollection);
        Step currentStep = mapNavigation.getStartingStep();

        do {
            printLine();
            printLine(currentStep.getNode().getTitle());

            // By default, assign the first option to be the selected one,
            // we should only have one or more options as it is the main condition in the loop
            Node selectedOption = currentStep.getUserOptions().get(0);

            String userInput = "";

            if (currentStep.getUserOptions().size() == 1) {
                userInput = getStringFromConsole("Press ENTER to continue  ...");
                if (userInput.equalsIgnoreCase("e")) {break;}
            } else {
                for (int i = 0; i < currentStep.getUserOptions().size(); i++) {
                    printLine("[" + (i + 1) + "] " + currentStep.getUserOptions().get(i).getTitle());
                }

                userInput = getStringFromConsole();
                if (userInput.equalsIgnoreCase("e")) {break;}

                selectedOption = currentStep.getUserOptions().get(Integer.parseInt(userInput));
            }

            currentStep = mapNavigation.selectNextStep(selectedOption, currentStep.isUserChoice());

        } while (currentStep.getUserOptions().size() > 0);

        // Show last step
        printLine();
        printLine(currentStep.getNode().getTitle());
    }
}

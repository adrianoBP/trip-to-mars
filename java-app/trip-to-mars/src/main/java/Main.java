import Helpers.*;
import Models.Node;
import Models.Step;
import Models.UserSettings;

import static Helpers.IOUtilities.*;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Started");

        AppSettings.init();

        UserSettings userSettings = new UserSettings();

        MapHelper.buildMap();

        newGame(userSettings);

        // TODO: Map validation
        // TODO: Add map build
        // TODO: Remove FSHelper
        // TODO: Testing - First part of map contains all cases
        // TODO(Investigation): do we need to save to file?

        printLine("-- GAME END --");
    }

    private static void newGame(UserSettings userSettings) throws Exception {

        MapNav mapNavigation = new MapNav(userSettings);
        Step currentStep = mapNavigation.getStartingStep();

        do {

            printLine();
            printLine(currentStep.node.getTitle());

            // By default, assign the first option to be the selected one,
            // we should only have one or more options as it is the main condition in the loop
            Node selectedOption = currentStep.options.get(0);

            if (currentStep.options.size() == 1) {
                getStringFromConsole("Press ENTER to continue  ...");
            } else {
                for (int i = 0; i < currentStep.options.size(); i++) {
                    printLine("[" + (i + 1) + "] " + currentStep.options.get(i).getTitle());
                }
                selectedOption = currentStep.options.get(getIntFromConsole() - 1);
            }

            mapNavigation.saveOptionInput(selectedOption);

            currentStep = mapNavigation.selectNextStep(currentStep.options.size() > 1);

        } while (currentStep.options.size() > 0);

        // Show last step
        printLine();
        printLine(currentStep.node.getTitle());
    }
}

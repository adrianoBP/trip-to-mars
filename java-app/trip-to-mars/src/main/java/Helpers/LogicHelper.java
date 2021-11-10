package Helpers;

import Models.Node;
import Models.Option;
import Models.Requirement;
import Models.UserSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LogicHelper {

    public static void SaveResult() {

        // TODO: If the node contains save-result == true, save the option in user settings

        // TODO: If the option is already saved, remove it
    }

    public static List<Node> GetNextNodes(Map<String, Node> availableNodes, Node currentNode, UserSettings userSettings)
            throws Exception {

        if (currentNode.Options.size() == 0)  // Ending reached
            return new ArrayList<>();
        else if (currentNode.Options.size() == 1)  // Story line
            return List.of(availableNodes.get(currentNode.Options.get(0).NodeId));
        else {

            List<Node> optionNodes = new ArrayList<>();

            boolean isChanceChoice = true;
            for (Option option : currentNode.Options) {


                if (option.Chance == 0) {  // To be a chance choice, all options must have a chance
                    isChanceChoice = false;

                    // Chance options cannot contain requirements (YET)
                    if (RequirementsAreMet(option.Requirements, userSettings))  // Check if requirements are met
                        optionNodes.add(availableNodes.get(option.NodeId));
                }
            }

            // Chance based decision
            if (isChanceChoice) {
                Option selectedOption = PickOptionByProbability(currentNode.Options);
                return List.of(availableNodes.get(selectedOption.NodeId));
            }

            // Either there's only one option (system based) or multiple options (user based)
            return optionNodes;
        }


        // TO-CHECK: If all the nodes contain % != 0, randomly pick a node from the available nodes according to the % (CHANCE MADE DECISION)

        // TO-CHECK: If ALL the nodes % is 0, then show all the nodes (USER MADE DECISION)

        // TO-CHECK: If the node requirements are not met OR only one node, then the node is not available (SYSTEM MADE DECISION)

        // TO-CHECK: If there are no nodes, the end has been reached
    }

    private static Option PickOptionByProbability(List<Option> options) throws Exception {

        int random = (int) (Math.random() * 100);

        for (Option option : options) {

            if (random < option.Chance)
                return option;
            random -= option.Chance;
        }

        throw new Exception("PickOptionByProbability: Unable to pick an option");
    }

    private static boolean RequirementsAreMet(List<Requirement> requirements, UserSettings userSettings) {

        for (Requirement requirement : requirements) {
            if (userSettings.SavedItems.contains(requirement.Name)) {
                if (!requirement.MustExist)
                    return false;  // User has the item, but the requirement is to NOT have the item
            } else {
                if (requirement.MustExist)
                    return false;  // User does not have the item, but the requirement is to have the item
            }
        }
        return true;
    }
}

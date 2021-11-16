package Helpers;

import Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapNav {

    private final NodeCollection nodeCollection;
    private final UserSettings userSettings;
    private Node currentNode;

    public MapNav(UserSettings userSettings) throws Exception {
        nodeCollection = new NodeCollection();
        this.userSettings = userSettings;
    }

    public Step getStartingStep() {
        currentNode = nodeCollection.getStartingNode();
        return new Step(currentNode, Objects.requireNonNull(currentNode).getOptions().get(0).getNodeId());
    }

    public Step selectNextStep(boolean isUserOption) throws Exception {

        // If is a user option, we want to skip to the next node as we don't want to show the option itself
        if (isUserOption)
            this.currentNode = nodeCollection.getNodeById(currentNode.getOptions().get(0).getNodeId());

        List<Node> availableNodes = getAvailableNodeOptions();
        userSettings.addSavedItem(currentNode.getItemToSave()); // Save node items

        if (availableNodes.size() == 0) {  // End reached
            return new Step(currentNode);
        } else if (availableNodes.size() == 1) {  // System or chance based decision
            return new Step(currentNode, availableNodes.get(0).getId());
        } else {  // User based decision

            return new Step(currentNode, availableNodes);
        }
    }

    public void saveOptionInput(Node selectionOption) {

        // Check if the node is a 'next' node - If so, we want to use the 'next' node option directly
        if (selectionOption.getId() == null) {
            this.currentNode = nodeCollection.getNodeById(selectionOption.getOptions().get(0).getNodeId());
        } else {
            this.currentNode = selectionOption;
        }
        userSettings.addSavedItem(currentNode.getItemToSave()); // Save node items
    }



    public List<Node> getAvailableNodeOptions() throws Exception {

        if (currentNode.getOptions().size() == 0)  // Ending reached
            return new ArrayList<>();
        else if (currentNode.getOptions().size() == 1) {  // Story line
            return List.of(nodeCollection.getNodeById(currentNode.getOptions().get(0).getNodeId()));
        } else {  // User or chance decision

            List<Node> availableNodes = new ArrayList<>();

            // If is a chance decision, pick a node randomly (according to chance)
            if (currentNode.getOptions().stream().anyMatch(Option::isChanceOption)) {
                Option selectedOption = pickOptionByProbability(currentNode);
                return List.of(nodeCollection.getNodeById(selectedOption.getNodeId()));
            }

            // Otherwise, return the options that the user can make
            for (Option option : currentNode.getOptions()) {
                if (option.requirementsAreMet(userSettings))
                    availableNodes.add(nodeCollection.getNodeById(option.getNodeId()));
            }
            return availableNodes;
        }
    }

    private Option pickOptionByProbability(Node node) throws Exception {

        int random = (int) (Math.random() * 100);

        for (Option option : node.getOptions()) {

            if (random < option.getChance())
                return option;
            random -= option.getChance();
        }

        throw new Exception("PickOptionByProbability: Unable to pick an option");
    }
}

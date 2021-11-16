package Helpers;

import Models.Node;
import Models.NodeCollection;
import Models.Option;
import Models.UserSettings;

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
        currentNode = getStartingNode();
        return new Step(currentNode, Objects.requireNonNull(currentNode).getOptions().get(0).getNodeId());
    }

    public Step selectNextStep(boolean isUserOption) throws Exception {

        // If is a user option, we want to skip to the next node as we don't want to show the option itself
        if(isUserOption)
            this.currentNode = nodeCollection.getNodeById(currentNode.getOptions().get(0).getNodeId());

        List<Node> availableNodes = getAvailableNodeOptions();
        userSettings.addSavedItem(currentNode.getItemToSave()); // Save node items

        if (availableNodes.size() == 0) {  // End reached
            return new Step();
        } else if (availableNodes.size() == 1) {  // System or chance based decision
            return new Step(currentNode, availableNodes.get(0).getId());
        } else {  // User based decision

            return new Step(currentNode, availableNodes);
        }
    }

    public void saveOptionInput(Node selectionOption) {

        if (selectionOption.getId() == null) {  // This means that the node is a 'Next' node
            this.currentNode = nodeCollection.getNodeById(selectionOption.getOptions().get(0).getNodeId());
        } else {
            this.currentNode = selectionOption;
        }
        userSettings.addSavedItem(currentNode.getItemToSave()); // Save node items
    }

    public static class Step {
        public Node node;
        public List<Node> options = new ArrayList<>();

        public Step() {}

        public Step(Node node, String nextNodeId) {
            this.node = node;
            // If there are no options, then create a dummy 'next' option for the user to navigate
            this.options = List.of(new Node("Next", new Option(nextNodeId)));
        }

        public Step(Node node, List<Node> options) {
            this.node = node;
            this.options = options;
        }
    }

    private Node getStartingNode() {

        for (Node node : nodeCollection.toList()) {
            if (node.isBeginning())
                return node;
        }
        return null;
    }

    public List<Node> getAvailableNodeOptions() throws Exception {

        if (currentNode.getOptions().size() == 0)  // Ending reached
            return new ArrayList<>();
        else if (currentNode.getOptions().size() == 1) {  // Story line
            return List.of(nodeCollection.getNodeById(currentNode.getOptions().get(0).getNodeId()));
        } else {

            List<Node> availableNodes = new ArrayList<>();
            if (currentNode.getOptions().stream().anyMatch(Option::isChanceOption)) {
                Option selectedOption = pickOptionByProbability(currentNode);
                return List.of(nodeCollection.getNodeById(selectedOption.getNodeId()));
            }
            for (Option option : currentNode.getOptions()) {
                if (option.requirementsAreMet(userSettings))  // Check if requirements are met
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

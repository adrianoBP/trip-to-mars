package Helpers;

import Models.*;
import Models.NodeData.Node;
import Models.NodeData.Option;
import Models.Utils.MapValidationData;
import Models.Utils.NodeCollection;
import Models.Utils.Step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MapNav {

    private final NodeCollection nodeCollection;
    private final UserSettings userSettings;


    public MapNav(UserSettings userSettings, NodeCollection nodeCollection) throws Exception {

        this.nodeCollection = nodeCollection;
        this.userSettings = userSettings;

        if (!AppSettings.isLive)
            processValidationResult(MapHelper.validateMapPath(nodeCollection));
    }

    private void processValidationResult(MapValidationData mapValidationResult) throws Exception {

        // Make sure that there are endings
        if (mapValidationResult.getEndings().size() == 0)
            throw new Exception("No endings found");

        // Verify that all the endings can be reached
        List<String> endingNodes = this.nodeCollection.toList()
                .stream()
                .filter(node -> node.getOptions().size() == 0)
                .map(Node::getId)
                .collect(Collectors.toList());
        endingNodes.removeAll(mapValidationResult.getEndings());
        if (endingNodes.size() != 0)
            throw new Exception("One or more endings cannot be reached");

        // Make sure that all the nodes are used
        if (mapValidationResult.getExploredNodes().size() < this.nodeCollection.toList().size()) {

            List<String> notExploredNodes = new ArrayList<>();
            for (Node node : this.nodeCollection.toList()) {
                if (!mapValidationResult.getExploredNodes().contains(node.getId()))
                    notExploredNodes.add(node.getTitle());
            }

            throw new Exception("One or more nodes are not used: "
                    + String.join(", ", notExploredNodes));
        }
    }

    public Step getStartingStep() {
        Node startingNode = this.nodeCollection.getStartingNode();
        return new Step(
                startingNode,
                Objects.requireNonNull(startingNode).getOptions().get(0).getNodeId());
    }

    public Step selectNextStep(Node selectedOption, boolean isUserOption) throws Exception {

        // Save node items
        userSettings.tryAddItem(selectedOption.getItemToSave());

        // When user clicks on 'next', we want to skip that and go directly to the content
        if (selectedOption.getTitle().equalsIgnoreCase("next"))
            selectedOption = getNodeById(selectedOption.getOptions().get(0).getNodeId());

        // We need to skip the selected option as it is not a story node
        if (isUserOption) {
            if (selectedOption.isChanceChoice())
                // If the current option is a chance node, we need to pick a new node
                selectedOption = getNodeById(pickOptionByProbability(selectedOption).getNodeId());
            else
                selectedOption = getNodeById(selectedOption.getOptions().get(0).getNodeId());
        }

        List<Node> newAvailableOptions = getAvailableNodeOptions(selectedOption);

        if (newAvailableOptions.size() == 0) {
            // End reached
            return new Step(selectedOption);
        } else if (newAvailableOptions.size() == 1) {
            // System or chance based decision
            return new Step(selectedOption, newAvailableOptions.get(0).getId());
        } else {
            // User based decision
            return new Step(selectedOption, newAvailableOptions);
        }
    }

    public List<Node> getAvailableNodeOptions(Node selectedNode) throws Exception {

        if (selectedNode.getOptions().size() == 0)              // Ending reached
            return new ArrayList<>();
        else if (selectedNode.getOptions().size() == 1) {       // Story line
            return getNodeAsList(selectedNode.getOptions().get(0).getNodeId());
        } else {                                                // User or chance decision
            // If is a chance decision, randomly pick a node
            if (selectedNode.isChanceChoice())
                return getNodeAsList(pickOptionByProbability(selectedNode).getNodeId());

            List<Node> availableNodes = new ArrayList<>();
            // Otherwise, return the options that the user can select
            for (Option option : selectedNode.getOptions()) {
                if (option.requirementsAreMet(this.userSettings))
                    availableNodes.add(this.nodeCollection.getNodeById(option.getNodeId()));
            }
            return availableNodes;
        }
    }

    private Option pickOptionByProbability(Node node) {

        int random = (int) (Math.random() * 100);

        for (Option option : node.getOptions()) {

            if (random < option.getChance())
                return option;
            random -= option.getChance();
        }

        // If not found, return an option with an empty id
        return new Option("");
    }

    private List<Node> getNodeAsList(String nodeId) throws Exception {
        return Collections.singletonList(getNodeById(nodeId));
    }

    private Node getNodeById(String nodeId) throws Exception {
        if (nodeId.isBlank())
            throw new Exception("Provided node ID is null or empty!");

        return this.nodeCollection.getNodeById(nodeId);
    }
}

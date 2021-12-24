package com.up2037954.triptomars.Helpers;

import android.content.Context;
import android.text.TextUtils;

import com.up2037954.triptomars.Models.NodeData.*;
import com.up2037954.triptomars.Models.Utils.*;
import com.up2037954.triptomars.Models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MapNav {

    private final NodeCollection nodeCollection;
    private final UserSettings userSettings;


    public MapNav(UserSettings userSettings, Context context) throws Exception {

        nodeCollection = new NodeCollection(context);
        this.userSettings = userSettings;

        processValidationResult(MapHelper.validateMap(nodeCollection));
    }

    private void processValidationResult(MapValidationData mapValidationResult) throws Exception {

        // Make sure that there are endings
        if (mapValidationResult.getEndings().size() == 0)
            throw new Exception("No endings found");

        // Make sure that all the nodes are used
        if (mapValidationResult.getExploredNodes().size() < nodeCollection.toList().size()) {

            List<String> collectionNodeIds = nodeCollection.toList()
                    .stream()
                    .map(Node::getId)
                    .collect(Collectors.toList());

            List<String> notExploredNodes = collectionNodeIds.stream()
                    .filter(aObject -> !mapValidationResult.getExploredNodes().contains(aObject))
                    .collect(Collectors.toList());

            throw new Exception("One or more nodes are not used: "
                    + TextUtils.join(", ", notExploredNodes));
        }
    }

    public Step getStartingStep() {
        Node startingNode = nodeCollection.getStartingNode();
        return new Step(
                startingNode,
                Objects.requireNonNull(startingNode).getOptions().get(0).getNodeId());
    }

    public Step selectNextStep(Node selectedOption, boolean isUserOption) throws Exception {

        // When user clicks on 'next', we want to skip that that and go directly to the content
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

        // Save node items
        userSettings.tryAddItem(selectedOption.getItemToSave());

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
                if (option.requirementsAreMet(userSettings))
                    availableNodes.add(nodeCollection.getNodeById(option.getNodeId()));
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
        if (TextUtils.isEmpty(nodeId))
            throw new Exception("Provided node ID is null or empty!");

        return nodeCollection.getNodeById(nodeId);
    }
}

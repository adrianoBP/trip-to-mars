package com.up2037954.triptomars.Helpers;

import android.content.Context;
import android.text.TextUtils;

import com.up2037954.triptomars.Models.*;
import com.up2037954.triptomars.Models.NodeData.Node;
import com.up2037954.triptomars.Models.NodeData.Option;
import com.up2037954.triptomars.Models.Utils.MapValidationData;
import com.up2037954.triptomars.Models.Utils.NodeCollection;
import com.up2037954.triptomars.Models.Utils.Step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MapNav {

    private final NodeCollection nodeCollection;
    private final UserSettings userSettings;
    private Node currentNode;


    public MapNav(UserSettings userSettings, Context context) throws Exception {

        nodeCollection = new NodeCollection(context);
        this.userSettings = userSettings;

        // If there are any errors, through them, but keep the result in case we want to display it
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

            List<String> noteExploredNodes = collectionNodeIds.stream()
                    .filter(aObject -> !mapValidationResult.getExploredNodes().contains(aObject))
                    .collect(Collectors.toList());

            throw new Exception("One or more nodes are not used: " + TextUtils.join(", ", noteExploredNodes));
        }
    }

    public Step getStartingStep() {
        currentNode = nodeCollection.getStartingNode();
        return new Step(currentNode, Objects.requireNonNull(currentNode).getOptions().get(0).getNodeId());
    }

    public Step selectNextStep(boolean isUserOption) {

        // If is a user option, we want to skip to the next node as we don't want to show the option itself
        if (isUserOption)
            this.currentNode = nodeCollection.getNodeById(currentNode.getOptions().get(0).getNodeId());

        List<Node> availableNodes = getAvailableNodeOptions();
        userSettings.tryAddItem(currentNode.getItemToSave()); // Save node items

        if (availableNodes.size() == 0) {  // End reached
            return new Step(currentNode);
        } else if (availableNodes.size() == 1) {  // System or chance based decision
            return new Step(currentNode, availableNodes.get(0).getId());
        } else {  // User based decision

            return new Step(currentNode, availableNodes);
        }
    }

    public void saveUserOption(Node selectionOption) {

        // Check if the node is a 'next' node - If so, we want to use its user option directly
        if (selectionOption.getTitle().equalsIgnoreCase("next")) {
            this.currentNode = nodeCollection.getNodeById(selectionOption.getOptions().get(0).getNodeId());
        } else {
            this.currentNode = selectionOption;
        }
        userSettings.tryAddItem(currentNode.getItemToSave()); // Save node items
    }


    public List<Node> getAvailableNodeOptions() {

        if (currentNode.getOptions().size() == 0)  // Ending reached
            return new ArrayList<>();
        else if (currentNode.getOptions().size() == 1) {  // Story line
            return Collections.singletonList(nodeCollection.getNodeById(currentNode.getOptions().get(0).getNodeId()));
        } else {  // User or chance decision

            List<Node> availableNodes = new ArrayList<>();

            // If is a chance decision, pick a node randomly (according to chance)
            if (currentNode.getOptions().stream().anyMatch(Option::isChanceOption)) {
                Option selectedOption = pickOptionByProbability(currentNode);
                return Collections.singletonList(nodeCollection.getNodeById(selectedOption.getNodeId()));
            }

            // Otherwise, return the options that the user can make
            for (Option option : currentNode.getOptions()) {
                if (option.requirementsAreMet(userSettings))
                    availableNodes.add(nodeCollection.getNodeById(option.getNodeId()));
            }
            return availableNodes;
        }
    }

    private Option pickOptionByProbability(Node node)  {

        int random = (int) (Math.random() * 100);

        for (Option option : node.getOptions()) {

            if (random < option.getChance())
                return option;
            random -= option.getChance();
        }

        return null;
    }
}

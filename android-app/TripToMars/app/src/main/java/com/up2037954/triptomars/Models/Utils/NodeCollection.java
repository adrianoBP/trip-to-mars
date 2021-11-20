package com.up2037954.triptomars.Models.Utils;

import android.content.Context;

import com.up2037954.triptomars.Helpers.AppSettings;
import com.up2037954.triptomars.Helpers.Standard.FileHelper;
import com.up2037954.triptomars.Models.NodeData.Node;
import com.up2037954.triptomars.Models.NodeData.Option;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NodeCollection {

    private final Context context;

    // TODO: Justify (optimised searching)
    private final Map<String, Node> nodeIdToNode;


    public Node getNodeById(String nodeId) {
        if (nodeIdToNode.containsKey(nodeId))
            return nodeIdToNode.get(nodeId);
        return null;
    }

    public String insertNode(Node node) {
        if (!nodeIdToNode.containsKey(node.getId()))
            nodeIdToNode.put(node.getId(), node);
        return node.getId();
    }

    public void addNodeOptions(String nodeId, ArrayList<Option> options) {

        if (!nodeIdToNode.containsKey(nodeId))
            return;

        for (Option option : options) {
            nodeIdToNode.get(nodeId).addOption(option);
        }
    }

    public NodeCollection(Context context) throws Exception {

        this.context = context;

        String nodesFileContent = FileHelper.getOrCreate(AppSettings.nodesFilePath, "[]", this.context);

        // We need to define the type first due to Gson not recognising List<>, and we are storing a list of objects
        Type listType = new TypeToken<ArrayList<Node>>() {}.getType();
        List<Node> availableNodes = new Gson().fromJson(nodesFileContent, listType);

        // Convert node list to hashmap for optimised searching
        nodeIdToNode = availableNodes.stream().collect(Collectors.toMap(Node::getId, node -> node));
    }

    public Node getStartingNode() {

        for (Node node : toList()) {
            if (node.isBeginning())
                return node;
        }
        return null;
    }

    public void save() throws IOException {
        FileHelper.save(AppSettings.nodesFilePath, new Gson().toJson(nodeIdToNode.values()), context);
    }

    public List<Node> toList() {return new ArrayList<>(nodeIdToNode.values());}
}

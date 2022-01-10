package Models.Utils;

import Helpers.AppSettings;
import Helpers.Standard.FileHelper;
import Models.NodeData.Node;
import Models.NodeData.Option;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NodeCollection {

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

    public void addNodeOptions(String nodeId, List<Option> options) {

        if (!nodeIdToNode.containsKey(nodeId))
            return;

        for (Option option : options) {
            nodeIdToNode.get(nodeId).addOption(option);
        }
    }



    public NodeCollection(boolean ignoreSavedData) throws Exception {

        String nodesFileContent = ignoreSavedData ? "[]" : FileHelper.getOrCreate(AppSettings.nodesFilePath, "[]");

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
        FileHelper.save(AppSettings.nodesFilePath, new Gson().toJson(nodeIdToNode.values()));
    }

    public List<Node> toList() {return new ArrayList<>(nodeIdToNode.values());}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node node : this.nodeIdToNode.values()) {
            builder.append(node.toString() + "\n");
        }
        return builder.toString();
    }
}

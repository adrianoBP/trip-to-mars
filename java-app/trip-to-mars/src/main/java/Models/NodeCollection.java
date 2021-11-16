package Models;

import Helpers.AppSettings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NodeCollection {

    // TODO: Justify (optimised searching)
    private final Map<String, Node> nodes;

    public Node getNodeById(String nodeId) {
        if (nodes.containsKey(nodeId))
            return nodes.get(nodeId);
        return null;
    }

    public NodeCollection() throws Exception {

        Path nodesDataPath = Paths.get(AppSettings.nodesFilePath);

        // Make sure nodes file exist
        if (!Files.exists(nodesDataPath))
            if (nodesDataPath.toFile().createNewFile()) // If it doesn't exist, create an empty array
                Files.write(nodesDataPath, "[]".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

        // We need to define the type first due to Gson not recognising List<>, and we are storing a list of objects
        Type listType = new TypeToken<ArrayList<Node>>() {}.getType();
        List<Node> availableNodes = new Gson().fromJson(Files.readString(nodesDataPath), listType);

        // Convert node list to hashmap for optimised searching
        nodes = availableNodes.stream().collect(Collectors.toMap(Node::getId, node -> node));
    }

    public List<Node> toList() {
        return new ArrayList<>(nodes.values());
    }

    public Node getStartingNode() {

        for (Node node : toList()) {
            if (node.isBeginning())
                return node;
        }
        return null;
    }
}

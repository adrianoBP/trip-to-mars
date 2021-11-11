package Helpers;

import Models.Node;
import Models.Option;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class FSHelper {

    private Map<String, Node> nodes;

    private static Path nodesDataPath;
    private static Path usersDataPath;

    private static final Gson gson = new Gson();

    public FSHelper() throws IOException {
        nodes = new HashMap<>();

        nodesDataPath = Paths.get(AppSettings.nodesFilePath);
        usersDataPath = Paths.get(AppSettings.usersFilePath);

        if (!Files.exists(nodesDataPath))
            if (nodesDataPath.toFile().createNewFile())
                Files.write(nodesDataPath, "[]".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        if (!Files.exists(usersDataPath))
            if (usersDataPath.toFile().createNewFile())
                Files.write(usersDataPath, "[]".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

        Type listType = new TypeToken<ArrayList<Node>>() {}.getType();
        List<Node> availableNodes = gson.fromJson(Files.readString(nodesDataPath), listType);
        nodes = availableNodes.stream().collect(Collectors.toMap(Node::getId, node -> node));
    }


    public List<Node> getNodes() {return new ArrayList<>(this.nodes.values());}

    public String insertNode(Node node) {

        // TODO: Validate node does not exist
        String nodeId = UUID.randomUUID().toString();
        node.setId(nodeId);
        this.nodes.put(node.getId(), node);
        return nodeId;
    }

    public void addNodeOptions(String id, ArrayList<Option> options) {

        // TODO: Validate node does exist
        Node nodeToUpdate = this.nodes.get(id);
        for (Option option : options) {
            nodeToUpdate.addOption(option);
        }
    }


    public void save() throws IOException {
        saveFile(new Gson().toJson(this.nodes.values()), nodesDataPath);
    }

    private void saveFile(String content, Path filePath) throws IOException {
        // TODO: ENCODING

        Files.writeString(filePath, content, StandardCharsets.UTF_8, StandardOpenOption.WRITE);

        System.out.println(content);
    }
}

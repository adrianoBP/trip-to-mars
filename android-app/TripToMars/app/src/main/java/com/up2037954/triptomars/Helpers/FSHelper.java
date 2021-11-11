package com.up2037954.triptomars.Helpers;

import android.content.Context;

import com.up2037954.triptomars.Models.Node;
import com.up2037954.triptomars.Models.Option;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class FSHelper {

    private Map<String, Node> nodes;

    private final File nodesFile;
    private final File userFile;

    private static final Gson gson = new Gson();

    public FSHelper(Context context) throws IOException {
        nodes = new HashMap<>();

        File path = context.getFilesDir();
        nodesFile = new File(path, AppSettings.nodesFilePath);
        userFile = new File(path, AppSettings.usersFilePath);

        if (!nodesFile.exists())
            if (nodesFile.createNewFile())
                saveFile("[]", nodesFile);
        if (!userFile.exists())
            if (userFile.createNewFile())
                saveFile("{}", userFile);

        Type listType = new TypeToken<ArrayList<Node>>() {}.getType();
        List<Node> availableNodes = gson.fromJson(readFile(nodesFile), listType);
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
        saveFile(new Gson().toJson(this.nodes.values()), nodesFile);
        saveFile(new Gson().toJson(this.nodes.values()), userFile);
    }

    private void saveFile(String content, File file) throws IOException {

        try (FileOutputStream stream = new FileOutputStream(file)) {
            stream.write(content.getBytes());
        }
        System.out.println(content);
    }

    private String readFile(File file) throws IOException {
        int length = (int) file.length();

        byte[] bytes = new byte[length];

        try (FileInputStream in = new FileInputStream(file)) {
            in.read(bytes);
        }
        return new String(bytes);
    }
}

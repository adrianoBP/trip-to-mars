package com.up2037954.triptomars.Helpers;

import android.content.Context;

import com.up2037954.triptomars.Models.Node;
import com.up2037954.triptomars.Models.Option;
import com.up2037954.triptomars.Models.Requirement;
import com.up2037954.triptomars.Models.UserSettings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapHelper {

    public static FSHelper buildMap(Context context) throws IOException {

        FSHelper fsHelper = new FSHelper(context);

        List<Node> startingNodes = fsHelper.getNodes().stream().filter(Node::isBeginning).collect(Collectors.toList());
        if (startingNodes.size() > 0)
            return fsHelper;

        // s0 -> start
        // sX -> system choice
        // cX -> chance choice
        // uX -> user choice
        // eX -> Ending

        String s0 = fsHelper.insertNode(new Node("Start", "", "", true));
        String s1 = fsHelper.insertNode(new Node("Select an item", ""));
        String u1 = fsHelper.insertNode(new Node("Kid's toy", "", "KID_TOY"));
        String u2 = fsHelper.insertNode(new Node("Pen", "", "PEN"));
        String u3 = fsHelper.insertNode(new Node("Nothing", ""));
        String s2 = fsHelper.insertNode(new Node("Take off", ""));
        String s3 = fsHelper.insertNode(new Node("Incident in orbit", ""));
        String c1 = fsHelper.insertNode(new Node("Non critical", ""));
        String c2 = fsHelper.insertNode(new Node("Critical", ""));
        String u4 = fsHelper.insertNode(new Node("Investigate and fix", ""));
        String u5 = fsHelper.insertNode(new Node("Ignore the issue", ""));
        String c3 = fsHelper.insertNode(new Node("All good", ""));
        String s4 = fsHelper.insertNode(new Node("Ventilation system issue", "According to the system, you should be able to change route and turn back"));
        String u6 = fsHelper.insertNode(new Node("Turn back", ""));
        String u7 = fsHelper.insertNode(new Node("Try to fix the issue", ""));
        String s5 = fsHelper.insertNode(new Node("Is it enough?", "According to your team's calculations, there isn't enough oxygen for the trip back"));
        String u8 = fsHelper.insertNode(new Node("Trust the system", ""));
        String u9 = fsHelper.insertNode(new Node("Sacrifice yourself", ""));
        String e1 = fsHelper.insertNode(new Node("You live", "You and your team get home safely"));
        String e2 = fsHelper.insertNode(new Node("You die", "You decide to sacrifice yourself and take a pill which kills you. You will never know if your team makes it home"));
        String s6 = fsHelper.insertNode(new Node("A hole!", "You discover a hole in a critical section of the spaceship"));
        String s7 = fsHelper.insertNode(new Node("The pen", "The pen from your boss is the exact size of the hole and could be a temporary fix"));
        String s8 = fsHelper.insertNode(new Node("Use the pen", "Stick the pen in the hole until a proper fix is found"));
        String s9 = fsHelper.insertNode(new Node("Wait for the fix", "You need to wait for your team to come with a proper solution"));
        String e3 = fsHelper.insertNode(new Node("You die", "It took too long and the hole became too big to be fixed"));
        String s10 = fsHelper.insertNode(new Node("Hole patched", "The hole is now patched, but unfortunately the pen is no longer on your possession as during the fixes, it got sucked out of the ship to the vacuum of space", "PEN"));


        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option(s1, 0));
        fsHelper.addNodeOptions(s0, options);

        options = new ArrayList<>();
        options.add(new Option(u1, 0));
        options.add(new Option(u2, 0));
        options.add(new Option(u3, 0));
        fsHelper.addNodeOptions(s1, options);

        options = new ArrayList<>();
        options.add(new Option(s2, 0));
        fsHelper.addNodeOptions(u1, options);

        options = new ArrayList<>();
        options.add(new Option(s2, 0));
        fsHelper.addNodeOptions(u2, options);

        options = new ArrayList<>();
        options.add(new Option(s2, 0));
        fsHelper.addNodeOptions(u3, options);

        options = new ArrayList<>();
        options.add(new Option(s3, 0));
        fsHelper.addNodeOptions(s2, options);

        options = new ArrayList<>();
        options.add(new Option(c1, 50));
        options.add(new Option(c2, 50));
        fsHelper.addNodeOptions(s3, options);

        options = new ArrayList<>();
        options.add(new Option(u4, 0));
        options.add(new Option(u5, 0));
        fsHelper.addNodeOptions(c1, options);

        options = new ArrayList<>();
        options.add(new Option(c2, 1));
        options.add(new Option(c3, 99));
        fsHelper.addNodeOptions(u4, options);

        options = new ArrayList<>();
        options.add(new Option(c2, 90));
        options.add(new Option(c3, 10));
        fsHelper.addNodeOptions(u5, options);

        options = new ArrayList<>();
        options.add(new Option(s4, 0));
        fsHelper.addNodeOptions(c2, options);

        options = new ArrayList<>();
        options.add(new Option(u6, 0));
        options.add(new Option(u7, 0));
        fsHelper.addNodeOptions(s4, options);

        options = new ArrayList<>();
        options.add(new Option(s5, 0));
        fsHelper.addNodeOptions(u6, options);

        options = new ArrayList<>();
        options.add(new Option(u8, 0));
        options.add(new Option(u9, 0));
        fsHelper.addNodeOptions(s5, options);

        options = new ArrayList<>();
        options.add(new Option(e1, 0));
        fsHelper.addNodeOptions(u8, options);

        options = new ArrayList<>();
        options.add(new Option(e2, 0));
        fsHelper.addNodeOptions(u9, options);

        options = new ArrayList<>();
        options.add(new Option(s6, 0));
        fsHelper.addNodeOptions(u7, options);

        options = new ArrayList<>();
        options.add(new Option(s7, 0, Collections.singletonList(new Requirement("PEN", true))));
        options.add(new Option(s9, 0, Collections.singletonList(new Requirement("PEN", false))));
        fsHelper.addNodeOptions(s6, options);

        options = new ArrayList<>();
        options.add(new Option(s8, 0));
        options.add(new Option(s9, 0));
        fsHelper.addNodeOptions(s7, options);

        options = new ArrayList<>();
        options.add(new Option(e3, 0));
        fsHelper.addNodeOptions(s9, options);

        options = new ArrayList<>();
        options.add(new Option(s10, 0));
        fsHelper.addNodeOptions(s8, options);

        options = new ArrayList<>();
        options.add(new Option(c3, 0));
        fsHelper.addNodeOptions(s10, options);

        fsHelper.save();
        return fsHelper;
    }


    public static MapData validateMap(UserSettings userSettings, FSHelper fsHelper) throws Exception {

        MapData mapData = new MapData();

        mapData.setMapNodes(fsHelper.getNodes()
                .stream()
                .collect(Collectors.toMap(
                        Node::getId,
                        node -> node
                )));

        List<Node> startingNodes = mapData.getMapNodes().values()
                .stream()
                .filter(Node::isBeginning)
                .collect(Collectors.toList());

        if (startingNodes.size() == 0)
            throw new Exception("No starting nodes found");
        else if (startingNodes.size() > 1)
            throw new Exception("Multiple starting nodes found");


        mapData.setStartingNode(startingNodes.get(0));
        mapData.setMapValidationData(validatePath(mapData.getMapNodes(), mapData.getStartingNode(), userSettings));

        return mapData;
    }

    private static MapValidationData validatePath(Map<String, Node> allNodes, Node currentNode, UserSettings userSettings) {

        List<Node> nodeOptions = currentNode.getOptions()
                .stream()
                .map(option -> allNodes.get(option.getNodeId()))
                .collect(Collectors.toList());

        MapValidationData data = new MapValidationData(currentNode.getId());

        if (nodeOptions.size() == 0) {
            data.addEnding(currentNode.getId());
            return data;
        }

        for (Node node : nodeOptions) {

            MapValidationData pathData = validatePath(allNodes, node, userSettings);

            data.getExploredNodes().addAll(pathData.getExploredNodes());
            data.getEndings().addAll(pathData.getEndings());
        }

        data.setExploredNodes(data.getExploredNodes().stream().distinct().collect(Collectors.toList()));
        data.setEndings(data.getEndings().stream().distinct().collect(Collectors.toList()));
        return data;
    }

    public static class MapValidationData {

        private List<String> exploredNodes = new ArrayList<>();

        public List<String> getExploredNodes() {return exploredNodes;}

        public void setExploredNodes(List<String> exploredNodes) {this.exploredNodes = exploredNodes;}

        public void addExploredNode(String exploredNode) {exploredNodes.add(exploredNode);}


        private ArrayList<String> endings = new ArrayList<>();

        public ArrayList<String> getEndings() {return endings;}

        public void setEndings(List<String> endings) {this.endings = new ArrayList<>(endings);}

        public void addEnding(String ending) {this.endings.add(ending);}


        private MapValidationData() {}

        private MapValidationData(String nodeId) {
            addExploredNode(nodeId);
        }
    }

    public static class MapData {

        private Node startingNode = new Node();

        public Node getStartingNode() {return startingNode;}

        public void setStartingNode(Node startingNode) {this.startingNode = startingNode;}


        private Map<String, Node> mapNodes = new HashMap<>();

        public Map<String, Node> getMapNodes() {return mapNodes;}

        public void setMapNodes(Map<String, Node> mapNodes) {this.mapNodes = mapNodes;}


        private MapValidationData mapValidationData = new MapValidationData();

        public MapValidationData getMapValidationData() {return mapValidationData;}

        public void setMapValidationData(MapValidationData mapValidationData) {this.mapValidationData = mapValidationData;}

    }
}

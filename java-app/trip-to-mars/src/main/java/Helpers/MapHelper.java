package Helpers;

import Models.NodeData.Node;
import Models.NodeData.Option;
import Models.NodeData.Requirement;
import Models.Utils.MapValidationData;
import Models.Utils.NodeCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapHelper {

    public static void buildMap() throws Exception {

        NodeCollection nodeCollection = new NodeCollection();

        if (nodeCollection.getStartingNode() != null)
            return;

        // s0 -> start
        // sX -> system choice
        // cX -> chance choice
        // uX -> user choice
        // eX -> Ending
        // tX -> Testing

        String s0 = nodeCollection.insertNode(new Node("Start", "", "", true));
        String s1 = nodeCollection.insertNode(new Node("Select an item", ""));
        String u1 = nodeCollection.insertNode(new Node("Kid's toy", "", "KID_TOY"));
        String u2 = nodeCollection.insertNode(new Node("Pen", "", "PEN"));
        String u3 = nodeCollection.insertNode(new Node("Nothing", ""));
        String s2 = nodeCollection.insertNode(new Node("Take off", ""));
        String s3 = nodeCollection.insertNode(new Node("Incident in orbit", ""));
        String c1 = nodeCollection.insertNode(new Node("Non critical", ""));
        String c2 = nodeCollection.insertNode(new Node("Critical", ""));
        String u4 = nodeCollection.insertNode(new Node("Investigate and fix", ""));
        String u5 = nodeCollection.insertNode(new Node("Ignore the issue", ""));
        String c3 = nodeCollection.insertNode(new Node("All good", ""));
        String s4 = nodeCollection.insertNode(new Node("Ventilation system issue", "According to the system, you should be able to change route and turn back"));
        String u6 = nodeCollection.insertNode(new Node("Turn back", ""));
        String u7 = nodeCollection.insertNode(new Node("Try to fix the issue", ""));
        String s5 = nodeCollection.insertNode(new Node("Is it enough?", "According to your team's calculations, there isn't enough oxygen for the trip back"));
        String u8 = nodeCollection.insertNode(new Node("Trust the system", ""));
        String u9 = nodeCollection.insertNode(new Node("Sacrifice yourself", ""));
        String e1 = nodeCollection.insertNode(new Node("You live", "You and your team get home safely"));
        String e2 = nodeCollection.insertNode(new Node("You die", "You decide to sacrifice yourself and take a pill which kills you. You will never know if your team makes it home"));
        String s6 = nodeCollection.insertNode(new Node("A hole!", "You discover a hole in a critical section of the spaceship"));
        String s7 = nodeCollection.insertNode(new Node("The pen", "The pen from your boss is the exact size of the hole and could be a temporary fix"));
        String s8 = nodeCollection.insertNode(new Node("Use the pen", "Stick the pen in the hole until a proper fix is found"));
        String s9 = nodeCollection.insertNode(new Node("Wait for the fix", "You need to wait for your team to come with a proper solution"));
        String e3 = nodeCollection.insertNode(new Node("You die", "It took too long and the hole became too big to be fixed"));
        String s10 = nodeCollection.insertNode(new Node("Hole patched", "The hole is now patched, but unfortunately the pen is no longer on your possession as during the fixes, it got sucked out of the ship to the vacuum of space", "PEN"));

//        String t0 = nodeCollection.insertNode(new Node("TEST", "TEST", "", true));

        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option(s1, 0));
        nodeCollection.addNodeOptions(s0, options);

        options = new ArrayList<>();
        options.add(new Option(u1, 0));
        options.add(new Option(u2, 0));
        options.add(new Option(u3, 0));
        nodeCollection.addNodeOptions(s1, options);

        options = new ArrayList<>();
        options.add(new Option(s2, 0));
        nodeCollection.addNodeOptions(u1, options);

        options = new ArrayList<>();
        options.add(new Option(s2, 0));
        nodeCollection.addNodeOptions(u2, options);

        options = new ArrayList<>();
        options.add(new Option(s2, 0));
        nodeCollection.addNodeOptions(u3, options);

        options = new ArrayList<>();
        options.add(new Option(s3, 0));
        nodeCollection.addNodeOptions(s2, options);

        options = new ArrayList<>();
        options.add(new Option(c1, 50));
        options.add(new Option(c2, 50));
        nodeCollection.addNodeOptions(s3, options);

        options = new ArrayList<>();
        options.add(new Option(u4, 0));
        options.add(new Option(u5, 0));
        nodeCollection.addNodeOptions(c1, options);

        options = new ArrayList<>();
        options.add(new Option(c2, 1));
        options.add(new Option(c3, 99));
        nodeCollection.addNodeOptions(u4, options);

        options = new ArrayList<>();
        options.add(new Option(c2, 90));
        options.add(new Option(c3, 10));
        nodeCollection.addNodeOptions(u5, options);

        options = new ArrayList<>();
        options.add(new Option(s4, 0));
        nodeCollection.addNodeOptions(c2, options);

        options = new ArrayList<>();
        options.add(new Option(u6, 0));
        options.add(new Option(u7, 0));
        nodeCollection.addNodeOptions(s4, options);

        options = new ArrayList<>();
        options.add(new Option(s5, 0));
        nodeCollection.addNodeOptions(u6, options);

        options = new ArrayList<>();
        options.add(new Option(u8, 0));
        options.add(new Option(u9, 0));
        nodeCollection.addNodeOptions(s5, options);

        options = new ArrayList<>();
        options.add(new Option(e1, 0));
        nodeCollection.addNodeOptions(u8, options);

        options = new ArrayList<>();
        options.add(new Option(e2, 0));
        nodeCollection.addNodeOptions(u9, options);

        options = new ArrayList<>();
        options.add(new Option(s6, 0));
        nodeCollection.addNodeOptions(u7, options);

        options = new ArrayList<>();
        options.add(new Option(s7, 0, List.of(new Requirement("PEN", true))));
        options.add(new Option(s9, 0, List.of(new Requirement("PEN", false))));
        nodeCollection.addNodeOptions(s6, options);

        options = new ArrayList<>();
        options.add(new Option(s8, 0));
        options.add(new Option(s9, 0));
        nodeCollection.addNodeOptions(s7, options);

        options = new ArrayList<>();
        options.add(new Option(e3, 0));
        nodeCollection.addNodeOptions(s9, options);

        options = new ArrayList<>();
        options.add(new Option(s10, 0));
        nodeCollection.addNodeOptions(s8, options);

        options = new ArrayList<>();
        options.add(new Option(c3, 0));
        nodeCollection.addNodeOptions(s10, options);

        nodeCollection.save();
    }

    public static MapValidationData validateMap(NodeCollection nodeCollection) throws Exception {

        Map<String, Node> nodeIdToNode = new HashMap<>();
        String startingNodeId = "";

        for (Node node : nodeCollection.toList()) {
            if (nodeIdToNode.containsKey(node.getId()))
                continue;
            nodeIdToNode.put(node.getId(), node);

            if (node.isBeginning()) {
                if (!startingNodeId.isBlank())
                    throw new Exception("Multiple starting nodes found");
                startingNodeId = node.getId();
            }
        }

        if (startingNodeId.isBlank())
            throw new Exception("No starting nodes found");

        return validatePath(nodeIdToNode, startingNodeId);
    }

    private static MapValidationData validatePath(Map<String, Node> nodeIdToNode, String currentNodeId) {

        List<Option> nodeOptions = nodeIdToNode.get(currentNodeId).getOptions();

        MapValidationData data = new MapValidationData(currentNodeId);

        if (nodeOptions.size() == 0) {
            data.addEnding(currentNodeId);
            return data;
        }

        for (Option option : nodeOptions) {

            MapValidationData pathData = validatePath(nodeIdToNode, option.getNodeId());

            data.getExploredNodes().addAll(pathData.getExploredNodes());
            data.getEndings().addAll(pathData.getEndings());
        }

        data.setExploredNodes(data.getExploredNodes().stream().distinct().collect(Collectors.toList()));
        data.setEndings(data.getEndings().stream().distinct().collect(Collectors.toList()));
        return data;
    }
}

package Helpers;

import Models.Node;
import Models.Option;
import Models.Requirement;
import Models.UserSettings;
import com.mongodb.client.model.Filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapHelper {

    public static void buildMap() {

        List<Node> startingNodes = MongoDBHelper.getNodes(Filters.eq("is-beginning", true));
        if (startingNodes.size() > 0)
            return;

        // s0 -> start
        // sX -> system choice
        // cX -> chance choice
        // uX -> user choice
        // eX -> Ending

        String s0 = MongoDBHelper.insertNode(new Node("Start", "", "", true));
        String s1 = MongoDBHelper.insertNode(new Node("Select an item", ""));
        String u1 = MongoDBHelper.insertNode(new Node("Kid's toy", "", "KID_TOY"));
        String u2 = MongoDBHelper.insertNode(new Node("Pen", "", "PEN"));
        String u3 = MongoDBHelper.insertNode(new Node("Nothing", ""));
        String s2 = MongoDBHelper.insertNode(new Node("Take off", ""));
        String s3 = MongoDBHelper.insertNode(new Node("Incident in orbit", ""));
        String c1 = MongoDBHelper.insertNode(new Node("Non critical", ""));
        String c2 = MongoDBHelper.insertNode(new Node("Critical", ""));
        String u4 = MongoDBHelper.insertNode(new Node("Investigate and fix", ""));
        String u5 = MongoDBHelper.insertNode(new Node("Fix the issue", ""));
        String c3 = MongoDBHelper.insertNode(new Node("All good", ""));
        String s4 = MongoDBHelper.insertNode(new Node("Ventilation system issue", "According to the system, you should be able to change route and turn back"));
        String u6 = MongoDBHelper.insertNode(new Node("Turn back", ""));
        String u7 = MongoDBHelper.insertNode(new Node("Ignore", ""));
        String s5 = MongoDBHelper.insertNode(new Node("Is it enough?", "According to your team's calculations, there isn't enough oxygen for the trip back"));
        String u8 = MongoDBHelper.insertNode(new Node("Trust the system", ""));
        String u9 = MongoDBHelper.insertNode(new Node("Sacrifice yourself", ""));
        String e1 = MongoDBHelper.insertNode(new Node("You live", "You and your team get home safely"));
        String e2 = MongoDBHelper.insertNode(new Node("You die", "You decide to sacrifice yourself and take a pill which kills you. You will never know if your team makes it home"));
        String s6 = MongoDBHelper.insertNode(new Node("A hole!", "You discover a hole in a critical section of the spaceship"));
        String s7 = MongoDBHelper.insertNode(new Node("The pen", "The pen from your boss is the exact size of the hole and could be a temporary fix"));
        String s8 = MongoDBHelper.insertNode(new Node("Use the pen", "Stick the pen in the hole until a proper fix is found"));
        String s9 = MongoDBHelper.insertNode(new Node("Wait for the fix", "You need to wait for your team to come with a proper solution"));
        String e3 = MongoDBHelper.insertNode(new Node("You die", "It took too long and the hole became too big to be fixed"));
        String s10 = MongoDBHelper.insertNode(new Node("Hole patched", "The hole is now patched, but unfortunately the pen is no longer on your possession as during the fixes, it got sucked out of the ship to the vacuum of space", "PEN"));


        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option(s1, 0));
        MongoDBHelper.addNodeOptions(s0, options);

        options = new ArrayList<>();
        options.add(new Option(u1, 0));
        options.add(new Option(u2, 0));
        options.add(new Option(u3, 0));
        MongoDBHelper.addNodeOptions(s1, options);

        options = new ArrayList<>();
        options.add(new Option(s2, 0));
        MongoDBHelper.addNodeOptions(u1, options);

        options = new ArrayList<>();
        options.add(new Option(s2, 0));
        MongoDBHelper.addNodeOptions(u2, options);

        options = new ArrayList<>();
        options.add(new Option(s2, 0));
        MongoDBHelper.addNodeOptions(u3, options);

        options = new ArrayList<>();
        options.add(new Option(s3, 0));
        MongoDBHelper.addNodeOptions(s2, options);

        options = new ArrayList<>();
        options.add(new Option(c1, 50));
        options.add(new Option(c2, 50));
        MongoDBHelper.addNodeOptions(s3, options);

        options = new ArrayList<>();
        options.add(new Option(u4, 0));
        options.add(new Option(u5, 0));
        MongoDBHelper.addNodeOptions(c1, options);

        options = new ArrayList<>();
        options.add(new Option(c2, 1));
        options.add(new Option(c3, 99));
        MongoDBHelper.addNodeOptions(u4, options);

        options = new ArrayList<>();
        options.add(new Option(c2, 90));
        options.add(new Option(c3, 10));
        MongoDBHelper.addNodeOptions(u5, options);

        options = new ArrayList<>();
        options.add(new Option(s4, 0));
        MongoDBHelper.addNodeOptions(c2, options);

        options = new ArrayList<>();
        options.add(new Option(u6, 0));
        options.add(new Option(u7, 0));
        MongoDBHelper.addNodeOptions(s4, options);

        options = new ArrayList<>();
        options.add(new Option(s5, 0));
        MongoDBHelper.addNodeOptions(u6, options);

        options = new ArrayList<>();
        options.add(new Option(u8, 0));
        options.add(new Option(u9, 0));
        MongoDBHelper.addNodeOptions(s5, options);

        options = new ArrayList<>();
        options.add(new Option(e1, 0));
        MongoDBHelper.addNodeOptions(u8, options);

        options = new ArrayList<>();
        options.add(new Option(e2, 0));
        MongoDBHelper.addNodeOptions(u9, options);

        options = new ArrayList<>();
        options.add(new Option(s6, 0));
        MongoDBHelper.addNodeOptions(u7, options);

        options = new ArrayList<>();
        options.add(new Option(s7, 0, List.of(new Requirement("PEN", true))));
        options.add(new Option(s9, 0, List.of(new Requirement("PEN", false))));
        MongoDBHelper.addNodeOptions(s6, options);

        options = new ArrayList<>();
        options.add(new Option(s8, 0));
        options.add(new Option(s9, 0));
        MongoDBHelper.addNodeOptions(s7, options);

        options = new ArrayList<>();
        options.add(new Option(e3, 0));
        MongoDBHelper.addNodeOptions(s9, options);

        options = new ArrayList<>();
        options.add(new Option(s10, 0));
        MongoDBHelper.addNodeOptions(s8, options);

        options = new ArrayList<>();
        options.add(new Option(c3, 0));
        MongoDBHelper.addNodeOptions(s10, options);

    }


    public static MapData validateMap(UserSettings userSettings) throws Exception {

        MapData mapData = new MapData();

        mapData.MapNodes = MongoDBHelper.getNodes()
                .stream()
                .collect(Collectors.toMap(
                        node -> node.getId(),
                        node -> node
                ));

        List<Node> startingNodes = mapData.MapNodes.values()
                .stream()
                .filter(Node::isBeginning)
                .collect(Collectors.toList());

        if (startingNodes.size() == 0)
            throw new Exception("No starting nodes found");
        else if (startingNodes.size() > 1)
            throw new Exception("Multiple starting nodes found");


        mapData.StartingNode = startingNodes.get(0);
        mapData.MapValidationData = validatePath(mapData.MapNodes, mapData.StartingNode, userSettings);

        return mapData;
    }

    private static MapValidationData validatePath(Map<String, Node> allNodes, Node currentNode, UserSettings userSettings) {

        List<Node> nodeOptions = currentNode.getOptions()
                .stream()
                .map(option -> allNodes.get(option.getNodeId()))
                .collect(Collectors.toList());

        MapValidationData data = new MapValidationData(currentNode.getId());

        if (nodeOptions.size() == 0) {
            data.Endings.add(currentNode.getId());
            return data;
        }

        for (Node node : nodeOptions) {

            MapValidationData pathData = validatePath(allNodes, node, userSettings);

            data.ExploredNodes.addAll(pathData.ExploredNodes);
            data.Endings.addAll(pathData.Endings);
        }

        data.ExploredNodes = data.ExploredNodes.stream().distinct().collect(Collectors.toList());
        data.Endings = data.Endings.stream().distinct().collect(Collectors.toList());
        return data;
    }

    public static class MapValidationData {

        public List<String> ExploredNodes = new ArrayList<>();
        public List<String> Endings = new ArrayList<>();

        public MapValidationData() {}

        public MapValidationData(String nodeId) {
            ExploredNodes.add(nodeId);
        }
    }

    public static class MapData {

        public Node StartingNode = new Node();
        public Map<String, Node> MapNodes = new HashMap<>();

        public MapValidationData MapValidationData = new MapValidationData();

    }
}

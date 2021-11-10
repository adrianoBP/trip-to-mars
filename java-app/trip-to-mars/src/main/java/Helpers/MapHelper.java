package Helpers;

import Models.Node;
import Models.Option;
import Models.Requirement;
import Models.UserSettings;
import com.mongodb.client.model.Filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapHelper {

    public static void BuildMap() {

        List<Node> startingNodes = MongoDBHelper.GetNodes(Filters.eq("is-beginning", true));
        if (startingNodes.size() > 0)
            return;

        // s0 -> start
        // sX -> system choice
        // cX -> chance choice
        // uX -> user choice
        // eX -> Ending

        String s0 = MongoDBHelper.InsertNode(new Node("Start", "", "", true));
        String s1 = MongoDBHelper.InsertNode(new Node("Select an item", ""));
        String u1 = MongoDBHelper.InsertNode(new Node("Kid's toy", "", "KID_TOY"));
        String u2 = MongoDBHelper.InsertNode(new Node("Pen", "", "PEN"));
        String u3 = MongoDBHelper.InsertNode(new Node("Nothing", ""));
        String s2 = MongoDBHelper.InsertNode(new Node("Take off", ""));
        String s3 = MongoDBHelper.InsertNode(new Node("Incident in orbit", ""));
        String c1 = MongoDBHelper.InsertNode(new Node("Non critical", ""));
        String c2 = MongoDBHelper.InsertNode(new Node("Critical", ""));
        String u4 = MongoDBHelper.InsertNode(new Node("Investigate and fix", ""));
        String u5 = MongoDBHelper.InsertNode(new Node("Fix the issue", ""));
        String c3 = MongoDBHelper.InsertNode(new Node("All good", ""));
        String s4 = MongoDBHelper.InsertNode(new Node("Ventilation system issue", "According to the system, you should be able to change route and turn back"));
        String u6 = MongoDBHelper.InsertNode(new Node("Turn back", ""));
        String u7 = MongoDBHelper.InsertNode(new Node("Ignore", ""));
        String s5 = MongoDBHelper.InsertNode(new Node("Is it enough?", "According to your team's calculations, there isn't enough oxygen for the trip back"));
        String u8 = MongoDBHelper.InsertNode(new Node("Trust the system", ""));
        String u9 = MongoDBHelper.InsertNode(new Node("Sacrifice yourself", ""));
        String e1 = MongoDBHelper.InsertNode(new Node("You live", "You and your team get home safely"));
        String e2 = MongoDBHelper.InsertNode(new Node("You die", "You decide to sacrifice yourself and take a pill which kills you. You will never know if your team makes it home"));
        String s6 = MongoDBHelper.InsertNode(new Node("A hole!", "You discover a hole in a critical section of the spaceship"));
        String s7 = MongoDBHelper.InsertNode(new Node("The pen", "The pen from your boss is the exact size of the hole and could be a temporary fix"));
        String s8 = MongoDBHelper.InsertNode(new Node("Use the pen", "Stick the pen in the hole until a proper fix is found"));
        String s9 = MongoDBHelper.InsertNode(new Node("Wait for the fix", "You need to wait for your team to come with a proper solution"));
        String e3 = MongoDBHelper.InsertNode(new Node("You die", "It took too long and the hole became too big to be fixed"));
        String s10 = MongoDBHelper.InsertNode(new Node("Hole patched", "The hole is now patched, but unfortunately the pen is no longer on your possession as during the fixes, it got sucked out of the ship to the vacuum of space", "PEN"));


        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option(s1, 0));
        MongoDBHelper.AddNodeOptions(s0, options);

        options = new ArrayList<>();
        options.add(new Option(u1, 0));
        options.add(new Option(u2, 0));
        options.add(new Option(u3, 0));
        MongoDBHelper.AddNodeOptions(s1, options);

        options = new ArrayList<>();
        options.add(new Option(s2, 0));
        MongoDBHelper.AddNodeOptions(u1, options);

        options = new ArrayList<>();
        options.add(new Option(s2, 0));
        MongoDBHelper.AddNodeOptions(u2, options);

        options = new ArrayList<>();
        options.add(new Option(s2, 0));
        MongoDBHelper.AddNodeOptions(u3, options);

        options = new ArrayList<>();
        options.add(new Option(s3, 0));
        MongoDBHelper.AddNodeOptions(s2, options);

        options = new ArrayList<>();
        options.add(new Option(c1, 50));
        options.add(new Option(c2, 50));
        MongoDBHelper.AddNodeOptions(s3, options);

        options = new ArrayList<>();
        options.add(new Option(u4, 0));
        options.add(new Option(u5, 0));
        MongoDBHelper.AddNodeOptions(c1, options);

        options = new ArrayList<>();
        options.add(new Option(c2, 1));
        options.add(new Option(c3, 99));
        MongoDBHelper.AddNodeOptions(u4, options);

        options = new ArrayList<>();
        options.add(new Option(c2, 90));
        options.add(new Option(c3, 10));
        MongoDBHelper.AddNodeOptions(u5, options);

        options = new ArrayList<>();
        options.add(new Option(s4, 0));
        MongoDBHelper.AddNodeOptions(c2, options);

        options = new ArrayList<>();
        options.add(new Option(u6, 0));
        options.add(new Option(u7, 0));
        MongoDBHelper.AddNodeOptions(s4, options);

        options = new ArrayList<>();
        options.add(new Option(s5, 0));
        MongoDBHelper.AddNodeOptions(u6, options);

        options = new ArrayList<>();
        options.add(new Option(u8, 0));
        options.add(new Option(u9, 0));
        MongoDBHelper.AddNodeOptions(s5, options);

        options = new ArrayList<>();
        options.add(new Option(e1, 0));
        MongoDBHelper.AddNodeOptions(u8, options);

        options = new ArrayList<>();
        options.add(new Option(e2, 0));
        MongoDBHelper.AddNodeOptions(u9, options);

        options = new ArrayList<>();
        options.add(new Option(s6, 0));
        MongoDBHelper.AddNodeOptions(u7, options);

        options = new ArrayList<>();
        options.add(new Option(s7, 0, List.of(new Requirement("PEN", true))));
        options.add(new Option(s9, 0, List.of(new Requirement("PEN", false))));
        MongoDBHelper.AddNodeOptions(s6, options);

        options = new ArrayList<>();
        options.add(new Option(s8, 0));
        options.add(new Option(s9, 0));
        MongoDBHelper.AddNodeOptions(s7, options);

        options = new ArrayList<>();
        options.add(new Option(e3, 0));
        MongoDBHelper.AddNodeOptions(s9, options);

        options = new ArrayList<>();
        options.add(new Option(s10, 0));
        MongoDBHelper.AddNodeOptions(s8, options);

        options = new ArrayList<>();
        options.add(new Option(c3, 0));
        MongoDBHelper.AddNodeOptions(s10, options);

    }


    public static void ValidateMap(UserSettings userSettings) throws Exception {

        List<Node> allNodes = MongoDBHelper.GetNodes();
        Map<String, Node> nodeIdToNode = allNodes.stream().collect(Collectors.toMap(
                node -> node.Id,
                node -> node
        ));

        List<Node> startingNodes = allNodes
                .stream()
                .filter(node -> node.IsBeginning)
                .collect(Collectors.toList());

        if (startingNodes.size() == 0)
            throw new Exception("No starting nodes found");
        else if (startingNodes.size() > 1)
            throw new Exception("Multiple starting nodes found");

        MapValidationData resultData = ValidatePath(nodeIdToNode, startingNodes.get(0), userSettings);

        System.out.println("Nodes: " + resultData.ExploredNodes.size() + " / " + allNodes.size());
        System.out.println("Total endings: " + resultData.Endings.size());

    }

    private static MapValidationData ValidatePath(Map<String, Node> allNodes, Node currentNode, UserSettings userSettings) {

        List<Node> nodeOptions = currentNode.Options
                .stream()
                .map(option -> allNodes.get(option.NodeId))
                .collect(Collectors.toList());

        MapValidationData data = new MapValidationData(currentNode.Id);

        if (nodeOptions.size() == 0) {
            data.Endings.add(currentNode.Id);
            return data;
        }

        for (Node node : nodeOptions) {

            MapValidationData pathData = ValidatePath(allNodes, node, userSettings);

            data.ExploredNodes.addAll(pathData.ExploredNodes);
            data.Endings.addAll(pathData.Endings);
        }

        data.ExploredNodes = data.ExploredNodes.stream().distinct().collect(Collectors.toList());
        data.Endings = data.Endings.stream().distinct().collect(Collectors.toList());
        return data;
    }

    private static class MapValidationData {

        public List<String> ExploredNodes = new ArrayList<>();
        public List<String> Endings = new ArrayList<>();

        public MapValidationData(String nodeId) {
            ExploredNodes.add(nodeId);
        }
    }
}

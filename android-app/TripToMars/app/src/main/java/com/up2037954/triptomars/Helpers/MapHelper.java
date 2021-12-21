package com.up2037954.triptomars.Helpers;

import android.content.Context;
import android.text.TextUtils;

import com.up2037954.triptomars.Models.NodeData.Node;
import com.up2037954.triptomars.Models.NodeData.Option;
import com.up2037954.triptomars.Models.NodeData.Requirement;
import com.up2037954.triptomars.Models.Utils.MapValidationData;
import com.up2037954.triptomars.Models.Utils.NodeCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapHelper {

    public static void buildMap(Context context) throws Exception {

        NodeCollection nodeCollection = new NodeCollection(context);

        if (nodeCollection.getStartingNode() != null)
            return;

        // s0 -> start
        // sX -> system choice
        // cX -> chance choice
        // uX -> user choice
        // eX -> Ending
        // tX -> Testing

        String s0 = nodeCollection.insertNode(new Node("Start", "", "", true));
        String s1 = nodeCollection.insertNode(new Node("Select an item", "As you embark for a new adventure, you wonder if you should bring with you an item. What should you bring?"));
        String u1 = nodeCollection.insertNode(new Node("Kid's toy", "", "KID_TOY"));
        String u2 = nodeCollection.insertNode(new Node("Pen", "", "PEN"));
        String u3 = nodeCollection.insertNode(new Node("Nothing", ""));
        String s2 = nodeCollection.insertNode(new Node("Take off", "And it's take off! You just started your new adventure", "", "idle_astronaut_1"));
        String s3 = nodeCollection.insertNode(new Node("Incident in orbit", "Something is not quite right - The ship trembles a bit more than expected", "", "warning"));
        String c1 = nodeCollection.insertNode(new Node("Non critical", "Seems like the issue is not critical to the life of the ship - But you are still a bit worried: these are complex systems and you should be extra safe"));
        String c2 = nodeCollection.insertNode(new Node("Critical", "There's a critical issue somewhere on the ship! Red lights and loud sounds are all over the ship!", ""));
        String u4 = nodeCollection.insertNode(new Node("Investigate and fix", ""));
        String u5 = nodeCollection.insertNode(new Node("Ignore the issue", ""));
        String c3 = nodeCollection.insertNode(new Node("All good!", "The ship is back on its trajectory and you start seeing the starts moving by from your window", ""));
        String s4 = nodeCollection.insertNode(new Node("Ventilation system issue", "The systems indicate that there's an issue with the ventilation system, however, you may have enough oxygen to just turn back"));
        String u6 = nodeCollection.insertNode(new Node("Turn back", ""));
        String u7 = nodeCollection.insertNode(new Node("Try to fix the issue", ""));
        String s5 = nodeCollection.insertNode(new Node("Is it enough?", "The system sensors are sure are positive that you will make it home safely, however, according to your team's calculations, the oxygen won't be enough for all of you..."));
        String u8 = nodeCollection.insertNode(new Node("Trust the system", ""));
        String u9 = nodeCollection.insertNode(new Node("Sacrifice yourself", ""));
        String e1 = nodeCollection.insertNode(new Node("You live", "The systems were right! You get back safely into the Earths atmosphere and splash into the ocean! You are home."));
        String e2 = nodeCollection.insertNode(new Node("You die", "You sacrificed yourself for the team. You will never know if your team makes it home or if they were wrong, but you die peacefully."));
        String s6 = nodeCollection.insertNode(new Node("A hole!", "A small meteor perforated the structure of the ship and there's an oxygen leak!"));
        String s7 = nodeCollection.insertNode(new Node("The pen", "The pen from your boss is the exact size of the hole and could be a temporary fix"));
        String s8 = nodeCollection.insertNode(new Node("Use the pen", "Stick the pen in the hole until a proper fix is found"));
        String s9 = nodeCollection.insertNode(new Node("Wait for the fix", "You need to wait for your team to come with a proper solution"));
        String e3 = nodeCollection.insertNode(new Node("You die", "It took too long and the hole became too big to be fixed"));
        String s10 = nodeCollection.insertNode(new Node("Hole patched", "The hole is now patched, but the pen got sucked out of the ship to the vacuum of space", "PEN"));
        String s11 = nodeCollection.insertNode(new Node("Land on Mars", "You made it! You finally landed on the red planet.. but now there's a lot of pressure! Who will be the first to put foot on the new planet? Will you push to be the first or will you follow the procedures?"));
        String u10 = nodeCollection.insertNode(new Node("Push", ""));
        String u11 = nodeCollection.insertNode(new Node("Follow procedures", ""));
        String c4 = nodeCollection.insertNode(new Node("You tripped", "In the rush to get out you trip over a step!"));
        String e4 = nodeCollection.insertNode(new Node("You die", "The pen you had perforated the suit resulting in a catastrophic failure!"));
        String e5 = nodeCollection.insertNode(new Node("You die", "Your helmet cracks and yuo die from asphyxia!"));
        String c5 = nodeCollection.insertNode(new Node("You get hurt", "The suit is still all in one place, but you are sore and not really sure why - Should you tell anyone?"));
        String u12 = nodeCollection.insertNode(new Node("Don't tell anyone", ""));
        String u13 = nodeCollection.insertNode(new Node("Ask for help", ""));
        String c6 = nodeCollection.insertNode(new Node("Just a scratch", "You get back on your feet with no issues! That could have ended very bad!"));
        String c7 = nodeCollection.insertNode(new Node("You walk!", "You got down the ladder and you are now walking on the red planet!"));



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
        options.add(new Option(c1, 99));    // TODO: Change to 50
        options.add(new Option(c2, 1));    // TODO: Change to 50
        nodeCollection.addNodeOptions(s3, options);

        options = new ArrayList<>();
        options.add(new Option(u4, 0));
        options.add(new Option(u5, 0));
        nodeCollection.addNodeOptions(c1, options);

        options = new ArrayList<>();
        options.add(new Option(c2, 1));     // TODO: Check random
        options.add(new Option(c3, 99));    // TODO: Check random
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
        options.add(new Option(s7, 0, Collections.singletonList(new Requirement("PEN", true))));
        options.add(new Option(s9, 0, Collections.singletonList(new Requirement("PEN", false))));
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

        options = new ArrayList<>();
        options.add(new Option(s11, 0));
        nodeCollection.addNodeOptions(c3, options);

        options = new ArrayList<>();
        options.add(new Option(u10, 0));
        options.add(new Option(u11, 0));
        nodeCollection.addNodeOptions(s11, options);

        options = new ArrayList<>();
        options.add(new Option(c4, 70));
        options.add(new Option(c7, 30));
        nodeCollection.addNodeOptions(u10, options);

        options = new ArrayList<>();
        options.add(new Option(e4, 20, Collections.singletonList(new Requirement("PEN", true))));
        options.add(new Option(e5, 20, Collections.singletonList(new Requirement("PEN", true))));
        options.add(new Option(c5, 30, Collections.singletonList(new Requirement("PEN", true))));
        options.add(new Option(c6, 30, Collections.singletonList(new Requirement("PEN", true))));
        options.add(new Option(e5, 20, Collections.singletonList(new Requirement("PEN", false))));
        options.add(new Option(c5, 40, Collections.singletonList(new Requirement("PEN", false))));
        options.add(new Option(c6, 40, Collections.singletonList(new Requirement("PEN", false))));
        nodeCollection.addNodeOptions(c4, options);

        options = new ArrayList<>();
        options.add(new Option(u12, 0));
        options.add(new Option(u13, 0));
        nodeCollection.addNodeOptions(c5, options);

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
                if (!TextUtils.isEmpty(startingNodeId))
                    throw new Exception("Multiple starting nodes found");
                startingNodeId = node.getId();
            }
        }

        if (TextUtils.isEmpty(startingNodeId))
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

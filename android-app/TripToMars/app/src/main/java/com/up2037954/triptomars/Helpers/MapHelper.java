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

        String s0 = nodeCollection.insertNode(new Node("A new beginning", "Here you are! Your team is already waiting for you in the spaceship and they are all ready to go!", true));
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
        String s12 = nodeCollection.insertNode(new Node("Exploration", "There you go! You set foot on a new planet and you are now ready to explore it! Now that you are ready, will you be brave enough to go alone or will you take your team with you?"));
        String s13 = nodeCollection.insertNode(new Node("Hidden pain", "You know you are hurt but you also don't want to miss out on this new big adventure!"));
        String u14 = nodeCollection.insertNode(new Node("Explore the planet", ""));
        String u15 = nodeCollection.insertNode(new Node("Stay near the ship", "", "SHIP"));
        String s14 = nodeCollection.insertNode(new Node("Dizziness", "As you feared, you start feeling dizzy and your vision starts to reduce!"));
        String e6 = nodeCollection.insertNode(new Node("You die", "Seems like the fall caused you an internal bleeding that caused your death!"));
        String s15 = nodeCollection.insertNode(new Node("Medic!", "Thankfully, the medic is nearby and can take care of you"));
        String u16 = nodeCollection.insertNode(new Node("Bring the team", ""));
        String u17 = nodeCollection.insertNode(new Node("Go alone", ""));
        String s16 = nodeCollection.insertNode(new Node("A new sign", "After walking for many many horus, you notice that on the horizon there is what can only be described as a civilization! But is it? Or is or is it just your mind playing tricks?"));
        String u18 = nodeCollection.insertNode(new Node("Go check", ""));
        String u19 = nodeCollection.insertNode(new Node("Report to HQ", ""));
        String s19 = nodeCollection.insertNode(new Node("Aliens?!", "You are still far away, but they start looking like aliens!"));
        String u20 = nodeCollection.insertNode(new Node("Hide and watch", ""));
        String u21 = nodeCollection.insertNode(new Node("Present yourself", ""));
        String s20 = nodeCollection.insertNode(new Node("No movement", "It has been almost 4 hours and you still can't identify the subjects.. maye it is time to go back?"));
        String u22 = nodeCollection.insertNode(new Node("Wait longer", ""));
        String s21 = nodeCollection.insertNode(new Node("Something's on!", "It has been 2 more hours but something is starting to move - Looks quite dangerous though!"));
        String u23 = nodeCollection.insertNode(new Node("Try to go back", ""));
        String e7 = nodeCollection.insertNode(new Node("You die", "It is too late now and you don't have enough resources to get back! You died from thirst"));
        String s22 = nodeCollection.insertNode(new Node("Humans?", "You approach them but they seem to be humans! Only issue is that you don't recognize the symbols on their space suit - Should you assert dominance and be their new leader from a far world or should you just try to communicate?"));
        String u24 = nodeCollection.insertNode(new Node("Be peaceful", ""));
        String u25 = nodeCollection.insertNode(new Node("Assert dominance", ""));
        String s23 = nodeCollection.insertNode(new Node("Earth people!", "They are from Earth! What a great news. After some more talking, they explain to you that they were in a secret mission but got lost after a meteor storm killed their navigator and now they are just trying to survive - Your presence will be extremely helpful to them"));
        String e8 = nodeCollection.insertNode(new Node("You live", "You and your team decide to join the secret mission - Is this already a new beginning?"));
        String s24 = nodeCollection.insertNode(new Node("Weapons!", "That probably wasn't the best move! You now clearly see that they have weapons and they are pointing at you!"));
        String u26 = nodeCollection.insertNode(new Node("Surrender", ""));
        String u27 = nodeCollection.insertNode(new Node("Fight", ""));
        String s25 = nodeCollection.insertNode(new Node("Half down", "You tried to fight back, but after just a couple of minutes, half of your team is dead! Should you keep fighting?"));
        String u28 = nodeCollection.insertNode(new Node("Keep fighting", ""));
        String e9 = nodeCollection.insertNode(new Node("You die", "That wasn't the best move.. All your team, including you, die on a pointless fight"));
        String s26 = nodeCollection.insertNode(new Node("Skepticism", "They spare you, however, they are very very skeptical of you.. "));
        String s27 = nodeCollection.insertNode(new Node("The toy", "The Toy! You have your Kid's toy that you could offer as a gift! Should you give it to them?"));
        String u29 = nodeCollection.insertNode(new Node("Gift the toy", ""));
        String u30 = nodeCollection.insertNode(new Node("Hide it", ""));
        String s28 = nodeCollection.insertNode(new Node("Accepted", "They accepted the gift! They seem to be a bit more easy going"));
        String c8 = nodeCollection.insertNode(new Node("It's gone", "They are happy now, but they think that they should keep the gift.. Not much you can do, is it?", "KID_TOY"));
        String c9 = nodeCollection.insertNode(new Node("Come back", "That was a great test from their end, and you passed it! They also gave the toy back to you!"));
        String s29 = nodeCollection.insertNode(new Node("Wise team", "The team is speaking up - They quietly suggest you to just turn back and forget everything... Maybe your team member are wiser than you?"));
        String u31 = nodeCollection.insertNode(new Node("Ignore the team", ""));
        String c10 = nodeCollection.insertNode(new Node("True followers", "They understand you are the leader and they stick with you!"));
        String c11 = nodeCollection.insertNode(new Node("Rebellion", "A true leader should listen to the team! They rebel against you!"));
        String e10 = nodeCollection.insertNode(new Node("You die", "You find yourself alone, with nothing to do or eat"));
        String s30 = nodeCollection.insertNode(new Node("Explanation", "You explain to them that you were just scared.. They seem to ne understanding and they accept your apology"));


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

        options = new ArrayList<>();
        options.add(new Option(s13, 0));
        nodeCollection.addNodeOptions(u12, options);

        options = new ArrayList<>();
        options.add(new Option(u14, 0));
        options.add(new Option(u15, 0));
        nodeCollection.addNodeOptions(s13, options);

        options = new ArrayList<>();
        options.add(new Option(s14, 0));
        nodeCollection.addNodeOptions(u14, options);

        options = new ArrayList<>();
        options.add(new Option(s14, 0));
        nodeCollection.addNodeOptions(u15, options);

        options = new ArrayList<>();
        options.add(new Option(e6, 0, Collections.singletonList(new Requirement("SHIP", false))));
        options.add(new Option(s15, 0, Collections.singletonList(new Requirement("SHIP", true))));
        nodeCollection.addNodeOptions(s14, options);

        options = new ArrayList<>();
        options.add(new Option(s15, 0));
        nodeCollection.addNodeOptions(u13, options);

        options = new ArrayList<>();
        options.add(new Option(s12, 0));
        nodeCollection.addNodeOptions(c7, options);

        options = new ArrayList<>();
        options.add(new Option(s12, 0));
        nodeCollection.addNodeOptions(c6, options);

        options = new ArrayList<>();
        options.add(new Option(s12, 0));
        nodeCollection.addNodeOptions(s15, options);

        options = new ArrayList<>();
        options.add(new Option(u16, 0));
        options.add(new Option(u17, 0));
        nodeCollection.addNodeOptions(s12, options);

        options = new ArrayList<>();
        options.add(new Option(s16, 0));
        nodeCollection.addNodeOptions(u16, options);

        options = new ArrayList<>();
        options.add(new Option(u18, 0));
        options.add(new Option(u19, 0));
        nodeCollection.addNodeOptions(s16, options);

        options = new ArrayList<>();
        options.add(new Option(s19, 0));
        nodeCollection.addNodeOptions(u18, options);

        options = new ArrayList<>();
        options.add(new Option(u19, 0));
        options.add(new Option(u20, 0));
        options.add(new Option(u21, 0));
        nodeCollection.addNodeOptions(s19, options);

        options = new ArrayList<>();
        options.add(new Option(s20, 0));
        nodeCollection.addNodeOptions(u20, options);

        options = new ArrayList<>();
        options.add(new Option(u19, 0));
        options.add(new Option(u21, 0));
        options.add(new Option(u22, 0));
        nodeCollection.addNodeOptions(s20, options);

        options = new ArrayList<>();
        options.add(new Option(s21, 0));
        nodeCollection.addNodeOptions(u22, options);

        options = new ArrayList<>();
        options.add(new Option(u21, 0));
        options.add(new Option(u23, 0));
        nodeCollection.addNodeOptions(s21, options);

        options = new ArrayList<>();
        options.add(new Option(e7, 0));
        nodeCollection.addNodeOptions(u23, options);

        options = new ArrayList<>();
        options.add(new Option(s22, 0));
        nodeCollection.addNodeOptions(u21, options);

        options = new ArrayList<>();
        options.add(new Option(u24, 0));
        options.add(new Option(u25, 0));
        nodeCollection.addNodeOptions(s22, options);

        options = new ArrayList<>();
        options.add(new Option(s23, 0));
        nodeCollection.addNodeOptions(u24, options);

        options = new ArrayList<>();
        options.add(new Option(e8, 0));
        nodeCollection.addNodeOptions(s23, options);

        options = new ArrayList<>();
        options.add(new Option(s24, 0));
        nodeCollection.addNodeOptions(u25, options);

        options = new ArrayList<>();
        options.add(new Option(u26, 0));
        options.add(new Option(u27, 0));
        nodeCollection.addNodeOptions(s24, options);

        options = new ArrayList<>();
        options.add(new Option(s25, 0));
        nodeCollection.addNodeOptions(u27, options);

        options = new ArrayList<>();
        options.add(new Option(u26, 0));
        options.add(new Option(u28, 0));
        nodeCollection.addNodeOptions(s25, options);

        options = new ArrayList<>();
        options.add(new Option(e9, 0));
        nodeCollection.addNodeOptions(u28, options);

        options = new ArrayList<>();
        options.add(new Option(s26, 0));
        nodeCollection.addNodeOptions(u26, options);

        options = new ArrayList<>();
        options.add(new Option(s27, 0, Collections.singletonList(new Requirement("KID_TOY", true))));
        options.add(new Option(s29, 0, Collections.singletonList(new Requirement("KID_TOY", false))));
        nodeCollection.addNodeOptions(s26, options);

        options = new ArrayList<>();
        options.add(new Option(u29, 0));
        options.add(new Option(u30, 0));
        nodeCollection.addNodeOptions(s27, options);

        options = new ArrayList<>();
        options.add(new Option(s28, 0));
        nodeCollection.addNodeOptions(u29, options);

        options = new ArrayList<>();
        options.add(new Option(c8, 20));
        options.add(new Option(c9, 80));
        nodeCollection.addNodeOptions(s28, options);

        options = new ArrayList<>();
        options.add(new Option(s23, 0));
        nodeCollection.addNodeOptions(c8, options);

        options = new ArrayList<>();
        options.add(new Option(s23, 0));
        nodeCollection.addNodeOptions(c9, options);

        options = new ArrayList<>();
        options.add(new Option(s29, 0));
        nodeCollection.addNodeOptions(u30, options);

        options = new ArrayList<>();
        options.add(new Option(u23, 0));
        options.add(new Option(u31, 0));
        nodeCollection.addNodeOptions(s29, options);

        options = new ArrayList<>();
        options.add(new Option(c10, 50));
        options.add(new Option(c11, 50));
        nodeCollection.addNodeOptions(u31, options);

        options = new ArrayList<>();
        options.add(new Option(e10, 0));
        nodeCollection.addNodeOptions(c11, options);

        options = new ArrayList<>();
        options.add(new Option(s30, 0));
        nodeCollection.addNodeOptions(c10, options);

        options = new ArrayList<>();
        options.add(new Option(s23, 0));
        nodeCollection.addNodeOptions(s30, options);


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

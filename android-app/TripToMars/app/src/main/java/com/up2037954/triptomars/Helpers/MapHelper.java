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

    public static NodeCollection buildMap(Context context) throws Exception {

        NodeCollection nodeCollection = new NodeCollection(context);

        if (nodeCollection.getStartingNode() != null)
            return nodeCollection;

        // s0 -> start
        // sX -> system choice
        // cX -> chance choice
        // uX -> user choice
        // eX -> Ending
        // tX -> Testing

        String s0 = nodeCollection.insertNode(new Node("A new beginning", "Here you are! Your team is already waiting for you in the spaceship and they are all ready to go!", true));
        String s1 = nodeCollection.insertNode(new Node("Select an item", "As you embark on a new adventure, you wonder if you should bring with you an item. What should you bring?"));
        String u1 = nodeCollection.insertNode(new Node("Kid's toy", "", "KID_TOY"));
        String u2 = nodeCollection.insertNode(new Node("Pen", "", "PEN"));
        String u3 = nodeCollection.insertNode(new Node("Nothing", ""));
        String s2 = nodeCollection.insertNode(new Node("Take off", "And it's take off! You just started your new adventure", "", "take_off"));
        String s3 = nodeCollection.insertNode(new Node("Incident in orbit", "Something is not quite right - The ship trembles a bit more than expected", "", "warning"));
        String c1 = nodeCollection.insertNode(new Node("Non-critical", "Seems like the issue is not critical to the life of the ship - But you are still a bit worried: these are complex systems and you should be extra safe"));
        String c2 = nodeCollection.insertNode(new Node("Critical", "There's a critical issue somewhere on the ship! Red lights and loud sounds are all over the ship!", ""));
        String u4 = nodeCollection.insertNode(new Node("Investigate and fix", ""));
        String u5 = nodeCollection.insertNode(new Node("Ignore the issue", ""));
        String c3 = nodeCollection.insertNode(new Node("All good!", "The ship is back on its trajectory and you start seeing the starts moving by from your window", "", "green_check", 1));
        String s4 = nodeCollection.insertNode(new Node("Ventilation system issue", "The systems indicate that there's an issue with the ventilation system, however, you may have enough oxygen to just turn back"));
        String u6 = nodeCollection.insertNode(new Node("Turn back", ""));
        String u7 = nodeCollection.insertNode(new Node("Try to fix the issue", ""));
        String s5 = nodeCollection.insertNode(new Node("Is it enough?", "The system sensors are sure are positive that you will make it home safely, however, according to your team's calculations, the oxygen won't be enough for all of you..."));
        String u8 = nodeCollection.insertNode(new Node("Trust the system", ""));
        String u9 = nodeCollection.insertNode(new Node("Sacrifice yourself", ""));
        String e1 = nodeCollection.insertNode(new Node("You live", "The systems were right! You get back safely into the Earth's atmosphere and splash into the ocean! You are home."));
        String e2 = nodeCollection.insertNode(new Node("You die", "You sacrificed yourself for the team. You will never know if your team makes it home or if they were wrong, but you die peacefully.", "", "falling_astronaut"));
        String s6 = nodeCollection.insertNode(new Node("A hole!", "A small meteor perforated the structure of the ship and there's an oxygen leak!"));
        String s7 = nodeCollection.insertNode(new Node("The pen", "The pen from your boss is the exact size of the hole and could be a temporary fix"));
        String s8 = nodeCollection.insertNode(new Node("Use the pen", "Stick the pen in the hole until a proper fix is found"));
        String s9 = nodeCollection.insertNode(new Node("Wait for the fix", "You need to wait for your team to come with a proper solution"));
        String e3 = nodeCollection.insertNode(new Node("You die", "It took too long and the hole became too big to be fixed", "", "falling_astronaut"));
        String s10 = nodeCollection.insertNode(new Node("Hole patched", "The hole is now patched, but the pen got sucked out of the ship to the vacuum of space", "PEN"));
        String s11 = nodeCollection.insertNode(new Node("Land on Mars", "You made it! You finally landed on the red planet.. but now there's a lot of pressure! Who will be the first to put foot on the new planet? Will you push to be the first or will you follow the procedures?"));
        String u10 = nodeCollection.insertNode(new Node("Push", ""));
        String u11 = nodeCollection.insertNode(new Node("Follow procedures", ""));
        String c4 = nodeCollection.insertNode(new Node("You tripped", "In the rush to get out you trip over a step!"));
        String e4 = nodeCollection.insertNode(new Node("You die", "The pen you had perforated the suit resulting in a catastrophic failure!", "", "falling_astronaut"));
        String e5 = nodeCollection.insertNode(new Node("You die", "Your helmet cracks and yuo die from asphyxia!", "", "falling_astronaut"));
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
        String e6 = nodeCollection.insertNode(new Node("You die", "Seems like the fall caused you an internal bleeding that caused your death!", "", "falling_astronaut"));
        String s15 = nodeCollection.insertNode(new Node("Medic!", "Thankfully, the medic is nearby and can take care of you"));
        String u16 = nodeCollection.insertNode(new Node("Bring the team", ""));
        String u17 = nodeCollection.insertNode(new Node("Go alone", ""));
        String s16 = nodeCollection.insertNode(new Node("A new sign", "After walking for many many hours, you notice that on the horizon there is what can only be described as a civilization! But is it? Or is or is it just your mind playing tricks?"));
        String u18 = nodeCollection.insertNode(new Node("Go check", ""));
        String u19 = nodeCollection.insertNode(new Node("Report to HQ", ""));
        String s19 = nodeCollection.insertNode(new Node("Aliens?!", "You are still far away, but they start looking like aliens!"));
        String u20 = nodeCollection.insertNode(new Node("Hide and watch", ""));
        String u21 = nodeCollection.insertNode(new Node("Present yourself", ""));
        String s20 = nodeCollection.insertNode(new Node("No movement", "It has been almost 4 hours and you still can't identify the subjects.. maybe it is time to go back?"));
        String u22 = nodeCollection.insertNode(new Node("Wait longer", ""));
        String s21 = nodeCollection.insertNode(new Node("Something's on!", "It has been 2 more hours but something is starting to move - Looks quite dangerous though!"));
        String u23 = nodeCollection.insertNode(new Node("Try to go back", ""));
        String e7 = nodeCollection.insertNode(new Node("You die", "It is too late now and you don't have enough resources to get back! You died from thirst", "", "falling_astronaut"));
        String s22 = nodeCollection.insertNode(new Node("Humans?", "You approach them but they seem to be humans! The only issue is that you don't recognize the symbols on their spacesuit - Should you assert dominance and be their new leader from a far world or should you just try to communicate?"));
        String u24 = nodeCollection.insertNode(new Node("Be peaceful", ""));
        String u25 = nodeCollection.insertNode(new Node("Assert dominance", ""));
        String s23 = nodeCollection.insertNode(new Node("Earth people!", "They are from Earth! What great news. After some more talking, they explain to you that they were on a secret mission but got lost after a meteor storm killed their navigator and now they are just trying to survive - Your presence will be extremely helpful to them"));
        String e8 = nodeCollection.insertNode(new Node("You live", "You and your team decide to join the secret mission - Is this already a new beginning?"));
        String s24 = nodeCollection.insertNode(new Node("Weapons!", "That probably wasn't the best move! You now clearly see that they have weapons and they are pointing at you!"));
        String u26 = nodeCollection.insertNode(new Node("Surrender", ""));
        String u27 = nodeCollection.insertNode(new Node("Fight", ""));
        String s25 = nodeCollection.insertNode(new Node("Half down", "You tried to fight back, but after just a couple of minutes, half of your team is dead! Should you keep fighting?"));
        String u28 = nodeCollection.insertNode(new Node("Keep fighting", ""));
        String e9 = nodeCollection.insertNode(new Node("You die", "That wasn't the best move.. All your team, including you, die on a pointless fight", "", "falling_astronaut"));
        String s26 = nodeCollection.insertNode(new Node("Skepticism", "They spare you, however, they are very very skeptical of you.. "));
        String s27 = nodeCollection.insertNode(new Node("The toy", "The Toy! You have your Kid's toy that you could offer as a gift! Should you give it to them?"));
        String u29 = nodeCollection.insertNode(new Node("Gift the toy", ""));
        String u30 = nodeCollection.insertNode(new Node("Hide it", ""));
        String s28 = nodeCollection.insertNode(new Node("Accepted", "They accepted the gift! They seem to be a bit easier going"));
        String c8 = nodeCollection.insertNode(new Node("It's gone", "They are happy now, but they think that they should keep the gift.. Not much you can do, is it?", "KID_TOY"));
        String c9 = nodeCollection.insertNode(new Node("Come back", "That was a great test from their end, and you passed it! They also gave the toy back to you!"));
        String s29 = nodeCollection.insertNode(new Node("Wise team", "The team is speaking up - They quietly suggest you to just turn back and forget everything... Maybe your team member is wiser than you?"));
        String u31 = nodeCollection.insertNode(new Node("Ignore the team", ""));
        String c10 = nodeCollection.insertNode(new Node("True followers", "They understand you are the leader and they stick with you!"));
        String c11 = nodeCollection.insertNode(new Node("Rebellion", "A true leader should listen to the team! They rebel against you!"));
        String e10 = nodeCollection.insertNode(new Node("You die", "You find yourself alone, with nothing to do or eat", "", "falling_astronaut"));
        String s30 = nodeCollection.insertNode(new Node("Explanation", "You explain to them that you were just scared.. They seem to be understanding and they accept your apology"));
        String s31 = nodeCollection.insertNode(new Node("The toy", "In between procedures, you got some spare time and you pick up the toy when you notice that it has a message in it!"));
        String u32 = nodeCollection.insertNode(new Node("Leave it", ""));
        String u33 = nodeCollection.insertNode(new Node("Read it", ""));
        String s32 = nodeCollection.insertNode(new Node("Hidden message", "It is a message from your kid! You kid misses you... and a joyful tear travels its way on your cheek. You start wondering if you should stay on the ship instead of going out.. after all, it is the safest option, isn't it"));
        String u34 = nodeCollection.insertNode(new Node("Stay in the ship", ""));
        String u35 = nodeCollection.insertNode(new Node("Get ready to go", ""));
        String c12 = nodeCollection.insertNode(new Node("Call home", "You convince your Earth supervisor to let you call your kid home - Strangely, they let you! But of course, it will take quite some time to connect"));
        String c13 = nodeCollection.insertNode(new Node("Fellow spaceman", "One of your team members sees you there, by yourself, and approaches you - You definitely would prefer to stay alone and don't talk to anyone, but is that this situation? Or maybe you should get ready to go?"));
        String u36 = nodeCollection.insertNode(new Node("Talk about the kid", ""));
        String s33 = nodeCollection.insertNode(new Node("Nice bloke", "After quite some time, your team member explains that you are there to make your kid proud and convinces you to get out and explore - This is how you get trust from your team!", "TEAM_TRUST"));
        String s34 = nodeCollection.insertNode(new Node("Suit up", "It's time to go! You suit up and get ready to exit the spaceship - Your food is about to touch the Red Planet!"));
        String s35 = nodeCollection.insertNode(new Node("Sandstorm", "As soon as you step outside, you notice a very strong sandstorm making its way towards you - Maybe you should reunite with the team?", "", "wind"));
        String u37 = nodeCollection.insertNode(new Node("Reunite", ""));
        String u38 = nodeCollection.insertNode(new Node("Don't call the team", ""));
        String s36 = nodeCollection.insertNode(new Node("Missing person", "You reunite with the team, but you notice that one of the team members is missing"));
        String u39 = nodeCollection.insertNode(new Node("Don't search", ""));
        String u40 = nodeCollection.insertNode(new Node("Go search", ""));
        String c14 = nodeCollection.insertNode(new Node("Lost", "You and your team get lost in the sandstorm searching for the team member.. Maybe it wasn't the wisest choice"));
        String c15 = nodeCollection.insertNode(new Node("Found", "You find the missing astronaut! You quickly run back to the ship trying to escape from the sandstorm!"));
        String e11 = nodeCollection.insertNode(new Node("You die", "You weren't able to escape the powerful sandstorm and killed you are your team - You killed everyone while trying to find one", "", "falling_astronaut"));
        String c16 = nodeCollection.insertNode(new Node("Came back", "The astronaut was able to find the way back! That was a very lucky escape!"));
        String c17 = nodeCollection.insertNode(new Node("Gone missing", "Unfortunately, the team member never came back.. This means that from now on, your team will miss one team member - Will it be enough to complete the mission?", "MISSING_PERSON"));
        String c18 = nodeCollection.insertNode(new Node("Team rules", "Your team doesn't think that was the smartest choice and they go - They all go to search the missing person leaving you alone!"));
        String s37 = nodeCollection.insertNode(new Node("Meteors!", "While waiting for the call to connect, you notice from one of the windows that a few meteors are starting to fall down but they are still quite far - Is it safe for your team?", "", "falling_star"));
        String u41 = nodeCollection.insertNode(new Node("Go out to check", "", ""));
        String u42 = nodeCollection.insertNode(new Node("Call the team in", "", ""));
        String s38 = nodeCollection.insertNode(new Node("Team's gone", "All your team members get killed by meteors"));
        String e12 = nodeCollection.insertNode(new Node("You die", "You can't survive alone - Cold, hunger, and loneliness kill you", "", "falling_astronaut"));
        String s39 = nodeCollection.insertNode(new Node("Team's gone", "All your team members get killed by the sandstorm"));
        String c19 = nodeCollection.insertNode(new Node("Meteor hit", "You got hit by a meteor!", "", "idle_astronaut_2"));
        String e13 = nodeCollection.insertNode(new Node("You die", "The hit is too severe and you can't survive any longer", "", "falling_astronaut"));
        String c20 = nodeCollection.insertNode(new Node("Team reached", "You were able to reach your team! But is it safe to stay close to the meteor shower?", "", "idle_astronaut_2"));
        String u43 = nodeCollection.insertNode(new Node("Stay out", ""));
        String s40 = nodeCollection.insertNode(new Node("Space gift", "While out, a small meteor falls near you. It has a very bright red color and looks piping hot"));
        String u44 = nodeCollection.insertNode(new Node("Leave it", ""));
        String u45 = nodeCollection.insertNode(new Node("Pick it up", ""));
        String s41 = nodeCollection.insertNode(new Node("New item", "It does look super hot, but to the touch it actually very cold! It surely must have some useful properties!", "METEOR"));
        String s42 = nodeCollection.insertNode(new Node("Closing by", "The meteor shower starts closing by - You wisely decide to return to the ship"));
        String s43 = nodeCollection.insertNode(new Node("Back to the ship", "You are now back in the ship with your team and wait for the storm to finish and move away.. It is still a nice and cozy place after all!", "", "idle_astronaut_2"));
        String s44 = nodeCollection.insertNode(new Node("Safe outside", "The dangerous storm seems to have passed by - You can safely get out and start exploring the planet.. or should you start searching for resources to go back to home?"));
        String u46 = nodeCollection.insertNode(new Node("Explore", ""));
        String u47 = nodeCollection.insertNode(new Node("Get for resources", ""));
        String s45 = nodeCollection.insertNode(new Node("HQ is back", "After a long silence from headquarters due to the storm, they finally reached you and mentioned that there's a Rover not far from your position and maybe a nice sight - Should you search for the Rover? Or maybe you shouldn't risk compromising its mission?"));
        String u48 = nodeCollection.insertNode(new Node("Don't compromise", ""));
        String u49 = nodeCollection.insertNode(new Node("Search for it", ""));
        String s46 = nodeCollection.insertNode(new Node("Nothing here", "You walked for hours now, but still no sight of the Rover.. Maybe it got lost or is under a dune of sand?"));
        String u50 = nodeCollection.insertNode(new Node("Go back", ""));
        String u51 = nodeCollection.insertNode(new Node("Keep searching", ""));
        String s47 = nodeCollection.insertNode(new Node("Meteor fuel!", "You got back to the ship but you don't have anything to fuel it with.. Unless.. the meteor! Maybe you can try to use it as fuel? Surely it can't be that bad!"));
        String c21 = nodeCollection.insertNode(new Node("Clogged pipes", "You all are ready to go - Fire up the engines and it is lift-off! But hold on, something is not quite right! The meteor clogged the pipes!"));
        String c22 = nodeCollection.insertNode(new Node("New fuel", "It works! What a great advance in science! You can finally go back to Earth, but you will definitely come back for further research", "METEOR"));
        String e14 = nodeCollection.insertNode(new Node("You die", "The spaceship pipes erupted and caused the vehicle to explode", "", "falling_astronaut"));
        String e15 = nodeCollection.insertNode(new Node("You live", "After many days in orbit, you get back to Earth safely, ready to tell many, many stories"));
        String s48 = nodeCollection.insertNode(new Node("Too far", "Oh no! You went too far from the ship and you can't get any signals from it"));
        String s49 = nodeCollection.insertNode(new Node("There it is", "After a couple of hours, you find the Rover! It is a marvelous piece of engineering, but seems like it is still moving?"));
        String s50 = nodeCollection.insertNode(new Node("Wait and see", "You decide to look at it from a distance and think of a plan, maybe if you hit it with something it will stop? Or will the solar panels stop working at night?"));
        String u52 = nodeCollection.insertNode(new Node("Wait until dark", ""));
        String u53 = nodeCollection.insertNode(new Node("Throw sand", ""));
        String s51 = nodeCollection.insertNode(new Node("Freezing cold", "You didn't consider that you are on a way colder planet! It is way too cold to be outside at night!"));
        String e16 = nodeCollection.insertNode(new Node("You die", "You die from hypothermia! Staying outside at night on a different planet was not the smartest option", "", "falling_astronaut"));
        String s52 = nodeCollection.insertNode(new Node("Powerful sand", "It worked! Seems like the rover is no longer moving - You probably hit one of its sensors!"));
        String s53 = nodeCollection.insertNode(new Node("Approach", "As the rover is not moving, you approach it and start picking up the most important pieces - These will be helpful to build some structures"));
        String e17 = nodeCollection.insertNode(new Node("You live", "You now have all the components to start building the most critical components for a lab and a very small camp! Here you will start a new whole story!"));
        String s54 = nodeCollection.insertNode(new Node("The rock!", "You have a meteor that you could use to hit the Rover with! Should you throw it to the Rover?"));
        String u54 = nodeCollection.insertNode(new Node("Don't throw", ""));
        String u55 = nodeCollection.insertNode(new Node("Throw it", ""));
        String c23 = nodeCollection.insertNode(new Node("Miss", "You missed! You should definitely work on your through skills because you just lost the meteor!", "METEOR"));
        String c24 = nodeCollection.insertNode(new Node("Nice hit!", "You hit the Rover right on one of its sensors! It is definitely not going to move now! And you make sure to pick up the rock"));
        String s55 = nodeCollection.insertNode(new Node("Resources", "You start searching for resources, however, it is taking quite some time and nothing useful is being found... maybe you should split into smaller groups to cover more ground?"));
        String u56 = nodeCollection.insertNode(new Node("Split", ""));
        String u57 = nodeCollection.insertNode(new Node("Stay together", ""));
        String s56 = nodeCollection.insertNode(new Node("Not enough", "Due to the missing team member, your team has an odd number of people - You can try to go by yourself or join another team"));
        String u58 = nodeCollection.insertNode(new Node("Go alone", ""));
        String u59 = nodeCollection.insertNode(new Node("Join a team", ""));
        String s57 = nodeCollection.insertNode(new Node("Cave", "You walked many hours by yourself and you are very tired when.. Oh no! You felt into a cave! You are way too tired now to try to climb back"));
        String e18 = nodeCollection.insertNode(new Node("You die", "You can't climb up - You die alone into the cave. You should always stay with someone!", "", "falling_astronaut"));
        String s58 = nodeCollection.insertNode(new Node("Oddly quiet", "You started walking with your team, but they seem oddly quiet. You are aware that not all your decisions may have been the best ones..."));
        String s59 = nodeCollection.insertNode(new Node("Rebellion", "You try to make a start at a conversation, but as soon as you open the mouth, they attack you! Your team is rebelling against you! You try to fight your way back but they knock you down"));
        String e19 = nodeCollection.insertNode(new Node("You die", "The team took all your stuff and ran away - Maybe some of the decisions you took were not the best?", "", "falling_astronaut"));
        String s60 = nodeCollection.insertNode(new Node("A kid story", "To break the ice, you tell a story about your kid - It is a wonderful story and they also open up!"));
        String s61 = nodeCollection.insertNode(new Node("The piece", "After walking for quite some distance, you find part of the Rover - Does this mean that the Rover got destroyed? It still may contain helpful resources, but you need to open it up first"));
        String s62 = nodeCollection.insertNode(new Node("The pen!", "You start looking at it but you have no clue how to open it.. Until one of your members mentions the pen - Of course! The pen doubles as a screwdriver! You can finally take it apart"));
        String s63 = nodeCollection.insertNode(new Node("Rock hard", "You don't have any tools to open it, but maybe throwing a rock will open it? You will just need to be very, very careful to not break any useful components"));
        String u60 = nodeCollection.insertNode(new Node("Smash it", ""));
        String u61 = nodeCollection.insertNode(new Node("Leave it", ""));
        String c25 = nodeCollection.insertNode(new Node("Nice hit!", "It worked! You smashed one of the critical joints and it opened like an egg - You can finally get all the components"));
        String c26 = nodeCollection.insertNode(new Node("Junk metal", "Oh no! You hit one of the critical components! It is now literally a piece of junk metal.. The only left thing to do is to go back"));
        String e20 = nodeCollection.insertNode(new Node("You live", "You now have all the components and you had back to the ship - You can finally fix the broken parts to head home!"));
        String s64 = nodeCollection.insertNode(new Node("HQ rules", "Headquarters don't consider it safe - This means that you have to go back to Earth!"));


//        String t0 = nodeCollection.insertNode(new Node("TEST", "TEST"));

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

        options = new ArrayList<>();
        options.add(new Option(s31, 0, Collections.singletonList(new Requirement("KID_TOY", true))));
        options.add(new Option(s34, 0, Collections.singletonList(new Requirement("KID_TOY", false))));
        nodeCollection.addNodeOptions(u11, options);

        options = new ArrayList<>();
        options.add(new Option(u32, 0));
        options.add(new Option(u33, 0));
        nodeCollection.addNodeOptions(s31, options);

        options = new ArrayList<>();
        options.add(new Option(s12, 0));
        nodeCollection.addNodeOptions(u32, options);

        options = new ArrayList<>();
        options.add(new Option(s32, 0));
        nodeCollection.addNodeOptions(u33, options);

        options = new ArrayList<>();
        options.add(new Option(u34, 0));
        options.add(new Option(u35, 0));
        nodeCollection.addNodeOptions(s32, options);

        options = new ArrayList<>();
        options.add(new Option(c12, 50));
        options.add(new Option(c13, 50));
        nodeCollection.addNodeOptions(u34, options);

        options = new ArrayList<>();
        options.add(new Option(u35, 0));
        options.add(new Option(u36, 0));
        nodeCollection.addNodeOptions(c13, options);

        options = new ArrayList<>();
        options.add(new Option(s33, 0));
        nodeCollection.addNodeOptions(u36, options);

        options = new ArrayList<>();
        options.add(new Option(s34, 0));
        nodeCollection.addNodeOptions(s33, options);

        options = new ArrayList<>();
        options.add(new Option(s34, 0));
        nodeCollection.addNodeOptions(u35, options);

        options = new ArrayList<>();
        options.add(new Option(s35, 0));
        nodeCollection.addNodeOptions(s34, options);

        options = new ArrayList<>();
        options.add(new Option(u37, 0));
        options.add(new Option(u38, 0));
        nodeCollection.addNodeOptions(s35, options);

        options = new ArrayList<>();
        options.add(new Option(s36, 0));
        nodeCollection.addNodeOptions(u37, options);

        options = new ArrayList<>();
        options.add(new Option(u39, 0));
        options.add(new Option(u40, 0));
        nodeCollection.addNodeOptions(s36, options);

        options = new ArrayList<>();
        options.add(new Option(c16, 50, Collections.singletonList(new Requirement("TEAM_TRUST", true))));
        options.add(new Option(c17, 50, Collections.singletonList(new Requirement("TEAM_TRUST", true))));
        options.add(new Option(c17, 50, Collections.singletonList(new Requirement("TEAM_TRUST", false))));
        options.add(new Option(c18, 50, Collections.singletonList(new Requirement("TEAM_TRUST", false))));
        nodeCollection.addNodeOptions(u39, options);

        options = new ArrayList<>();
        options.add(new Option(c14, 60));
        options.add(new Option(c15, 40));
        nodeCollection.addNodeOptions(s36, options);

        options = new ArrayList<>();
        options.add(new Option(e11, 0));
        nodeCollection.addNodeOptions(c14, options);

        options = new ArrayList<>();
        options.add(new Option(s43, 0));
        nodeCollection.addNodeOptions(c15, options);

        options = new ArrayList<>();
        options.add(new Option(s43, 0));
        nodeCollection.addNodeOptions(c16, options);

        options = new ArrayList<>();
        options.add(new Option(s43, 0));
        nodeCollection.addNodeOptions(c17, options);

        options = new ArrayList<>();
        options.add(new Option(s39, 0));
        nodeCollection.addNodeOptions(c18, options);

        options = new ArrayList<>();
        options.add(new Option(e12, 0));
        nodeCollection.addNodeOptions(s39, options);

        options = new ArrayList<>();
        options.add(new Option(s37, 0));
        nodeCollection.addNodeOptions(c12, options);

        options = new ArrayList<>();
        options.add(new Option(u38, 0));
        options.add(new Option(u41, 0));
        options.add(new Option(u42, 0));
        nodeCollection.addNodeOptions(s37, options);

        options = new ArrayList<>();
        options.add(new Option(s38, 0));
        nodeCollection.addNodeOptions(u38, options);

        options = new ArrayList<>();
        options.add(new Option(e12, 0));
        nodeCollection.addNodeOptions(s38, options);

        options = new ArrayList<>();
        options.add(new Option(c19, 40));
        options.add(new Option(c20, 60));
        nodeCollection.addNodeOptions(u41, options);

        options = new ArrayList<>();
        options.add(new Option(e13, 0));
        nodeCollection.addNodeOptions(c19, options);

        options = new ArrayList<>();
        options.add(new Option(u42, 0));
        options.add(new Option(u43, 0));
        nodeCollection.addNodeOptions(c20, options);

        options = new ArrayList<>();
        options.add(new Option(s43, 0));
        nodeCollection.addNodeOptions(u42, options);

        options = new ArrayList<>();
        options.add(new Option(s40, 0));
        nodeCollection.addNodeOptions(u43, options);

        options = new ArrayList<>();
        options.add(new Option(u44, 0));
        options.add(new Option(u45, 0));
        nodeCollection.addNodeOptions(s40, options);

        options = new ArrayList<>();
        options.add(new Option(s41, 0));
        nodeCollection.addNodeOptions(u45, options);

        options = new ArrayList<>();
        options.add(new Option(s42, 0));
        nodeCollection.addNodeOptions(u44, options);

        options = new ArrayList<>();
        options.add(new Option(s42, 0));
        nodeCollection.addNodeOptions(s41, options);

        options = new ArrayList<>();
        options.add(new Option(s43, 0));
        nodeCollection.addNodeOptions(s42, options);

        options = new ArrayList<>();
        options.add(new Option(s40, 0));
        nodeCollection.addNodeOptions(u17, options);

        options = new ArrayList<>();
        options.add(new Option(s44, 0));
        nodeCollection.addNodeOptions(s43, options);

        options = new ArrayList<>();
        options.add(new Option(u46, 0));
        options.add(new Option(u47, 0));
        nodeCollection.addNodeOptions(s44, options);

        options = new ArrayList<>();
        options.add(new Option(s45, 0));
        nodeCollection.addNodeOptions(u46, options);

        options = new ArrayList<>();
        options.add(new Option(u48, 0));
        options.add(new Option(u49, 0));
        nodeCollection.addNodeOptions(s45, options);

        options = new ArrayList<>();
        options.add(new Option(s46, 0));
        nodeCollection.addNodeOptions(u48, options);

        options = new ArrayList<>();
        options.add(new Option(u50, 0));
        options.add(new Option(u51, 0));
        nodeCollection.addNodeOptions(s46, options);

        options = new ArrayList<>();
        options.add(new Option(s47, 0, Collections.singletonList(new Requirement("METEOR", true))));
        options.add(new Option(e7, 0, Collections.singletonList(new Requirement("METEOR", false))));
        nodeCollection.addNodeOptions(u39, options);

        options = new ArrayList<>();
        options.add(new Option(c21, 10));
        options.add(new Option(c22, 90));
        nodeCollection.addNodeOptions(s47, options);

        options = new ArrayList<>();
        options.add(new Option(e14, 0));
        nodeCollection.addNodeOptions(c21, options);

        options = new ArrayList<>();
        options.add(new Option(e15, 0));
        nodeCollection.addNodeOptions(c22, options);

        options = new ArrayList<>();
        options.add(new Option(s48, 70));
        options.add(new Option(s49, 30));
        nodeCollection.addNodeOptions(s47, options);

        options = new ArrayList<>();
        options.add(new Option(e7, 0));
        nodeCollection.addNodeOptions(s48, options);

        options = new ArrayList<>();
        options.add(new Option(s49, 0));
        nodeCollection.addNodeOptions(u49, options);

        options = new ArrayList<>();
        options.add(new Option(s54, 0, Collections.singletonList(new Requirement("METEOR", true))));
        options.add(new Option(s50, 0, Collections.singletonList(new Requirement("METEOR", false))));
        nodeCollection.addNodeOptions(s49, options);

        options = new ArrayList<>();
        options.add(new Option(u52, 0));
        options.add(new Option(u53, 0));
        nodeCollection.addNodeOptions(s50, options);

        options = new ArrayList<>();
        options.add(new Option(s51, 0));
        nodeCollection.addNodeOptions(u52, options);

        options = new ArrayList<>();
        options.add(new Option(e16, 0));
        nodeCollection.addNodeOptions(s51, options);

        options = new ArrayList<>();
        options.add(new Option(s52, 0));
        nodeCollection.addNodeOptions(u53, options);

        options = new ArrayList<>();
        options.add(new Option(u54, 0));
        options.add(new Option(u55, 0));
        nodeCollection.addNodeOptions(s54, options);

        options = new ArrayList<>();
        options.add(new Option(c23, 20));
        options.add(new Option(c24, 80));
        nodeCollection.addNodeOptions(u55, options);

        options = new ArrayList<>();
        options.add(new Option(s50, 0));
        nodeCollection.addNodeOptions(c23, options);

        options = new ArrayList<>();
        options.add(new Option(s53, 0));
        nodeCollection.addNodeOptions(c24, options);

        options = new ArrayList<>();
        options.add(new Option(s52, 0));
        nodeCollection.addNodeOptions(u53, options);

        options = new ArrayList<>();
        options.add(new Option(e17, 0));
        nodeCollection.addNodeOptions(s53, options);

        options = new ArrayList<>();
        options.add(new Option(s53, 0));
        nodeCollection.addNodeOptions(s52, options);

        options = new ArrayList<>();
        options.add(new Option(s64, 0));
        nodeCollection.addNodeOptions(u19, options);

        options = new ArrayList<>();
        options.add(new Option(s55, 0));
        nodeCollection.addNodeOptions(s64, options);

        options = new ArrayList<>();
        options.add(new Option(s55, 0));
        nodeCollection.addNodeOptions(u47, options);

        options = new ArrayList<>();
        options.add(new Option(u56, 0));
        options.add(new Option(u57, 0));
        nodeCollection.addNodeOptions(s55, options);

        options = new ArrayList<>();
        options.add(new Option(s56, 0, Collections.singletonList(new Requirement("MISSING_PERSON", true))));
        options.add(new Option(s58, 0, Collections.singletonList(new Requirement("MISSING_PERSON", false))));
        nodeCollection.addNodeOptions(u56, options);

        options = new ArrayList<>();
        options.add(new Option(u58, 0));
        options.add(new Option(u59, 0));
        nodeCollection.addNodeOptions(s56, options);

        options = new ArrayList<>();
        options.add(new Option(s57, 0));
        nodeCollection.addNodeOptions(u58, options);

        options = new ArrayList<>();
        options.add(new Option(e18, 0));
        nodeCollection.addNodeOptions(s57, options);

        options = new ArrayList<>();
        options.add(new Option(s58, 0));
        nodeCollection.addNodeOptions(u59, options);

        options = new ArrayList<>();
        options.add(new Option(s59, 0, Collections.singletonList(new Requirement("TEAM_TRUST", true))));
        options.add(new Option(s60, 0, Collections.singletonList(new Requirement("TEAM_TRUST", false))));
        nodeCollection.addNodeOptions(s58, options);

        options = new ArrayList<>();
        options.add(new Option(e19, 0));
        nodeCollection.addNodeOptions(s59, options);

        options = new ArrayList<>();
        options.add(new Option(s61, 0));
        nodeCollection.addNodeOptions(u57, options);

        options = new ArrayList<>();
        options.add(new Option(s61, 0));
        nodeCollection.addNodeOptions(s60, options);

        options = new ArrayList<>();
        options.add(new Option(s62, 0, Collections.singletonList(new Requirement("PEN", true))));
        options.add(new Option(s63, 0, Collections.singletonList(new Requirement("PEN", false))));
        nodeCollection.addNodeOptions(s61, options);

        options = new ArrayList<>();
        options.add(new Option(e20, 0));
        nodeCollection.addNodeOptions(s62, options);

        options = new ArrayList<>();
        options.add(new Option(u60, 0));
        options.add(new Option(u61, 0));
        nodeCollection.addNodeOptions(s63, options);

        options = new ArrayList<>();
        options.add(new Option(c25, 60));
        options.add(new Option(c26, 40));
        nodeCollection.addNodeOptions(u60, options);

        options = new ArrayList<>();
        options.add(new Option(e20, 0));
        nodeCollection.addNodeOptions(c25, options);

        options = new ArrayList<>();
        options.add(new Option(e7, 0));
        nodeCollection.addNodeOptions(c26, options);

        options = new ArrayList<>();
        options.add(new Option(e7, 0));
        nodeCollection.addNodeOptions(u61, options);

        nodeCollection.save();

        return nodeCollection;
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

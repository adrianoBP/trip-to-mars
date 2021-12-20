package Testing;

import Models.NodeData.Node;
import Models.NodeData.Option;

import java.util.ArrayList;

import static Helpers.Standard.ConsoleHelper.printLine;

public class MainTesting {

    public static void main(String[] args) {

        Node mainNode = new Node("Malfunction", "According to the systems, there is a malfunction with the spacecraft ventilation");
        Node option1 = new Node("Turn back");
        Node option2 = new Node("Try and fix");

        mainNode.addOption(new Option(option1.getId(), 0));
        mainNode.addOption(new Option(option2.getId(), 0));

        printLine(mainNode.toString());
        printLine(option1.toString());
        printLine(option2.toString());
    }
}

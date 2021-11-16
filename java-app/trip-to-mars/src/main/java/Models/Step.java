package Models;

import java.util.ArrayList;
import java.util.List;

public class Step {
    public Node node;
    public List<Node> options = new ArrayList<>();

    public Step(Node node) {this.node = node;}

    public Step(Node node, String nextNodeId) {
        this.node = node;
        // If there's only one option. create a dummy 'next' option for the user to navigate
        this.options = List.of(new Node("Next", new Option(nextNodeId)));
    }

    public Step(Node node, List<Node> options) {
        this.node = node;
        this.options = options;
    }
}
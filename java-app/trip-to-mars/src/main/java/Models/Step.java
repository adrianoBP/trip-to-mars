package Models;

import java.util.List;

public class Step {

    private final Node node;
    private List<Node> options;

    public Node getNode() {return node;}

    public List<Node> getOptions() {return options;}


    public Step(Node node) {this.node = node;}

    public Step(Node node, List<Node> options) {
        this.node = node;
        this.options = options;
    }

    public Step(Node node, String nextNodeId) {
        this.node = node;
        // If there's only one option, create a dummy 'next' option for the user to navigate
        this.options = List.of(new Node("Next", new Option(nextNodeId)));
    }


}
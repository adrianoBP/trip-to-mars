package com.up2037954.triptomars.Models.Utils;

import com.up2037954.triptomars.Models.NodeData.Node;
import com.up2037954.triptomars.Models.NodeData.Option;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Step {

    private final Node node;
    private List<Node> userOptions = new ArrayList<>();

    public Node getNode() {return node;}

    public List<Node> getUserOptions() {return userOptions;}


    public Step(Node node) {this.node = node;}

    public Step(Node node, List<Node> options) {
        this.node = node;
        this.userOptions = options;
    }

    public Step(Node node, String nextNodeId) {
        this.node = node;
        // If there's only one option, create a dummy 'next' option for the user to navigate
        this.userOptions = Collections.singletonList(new Node("Next", new Option(nextNodeId)));
    }
}
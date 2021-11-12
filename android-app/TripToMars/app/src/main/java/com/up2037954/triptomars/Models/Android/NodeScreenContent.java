package com.up2037954.triptomars.Models.Android;

import com.google.android.material.button.MaterialButton;
import com.up2037954.triptomars.Models.Node;

import java.util.ArrayList;
import java.util.List;

public class NodeScreenContent {


    private final Node node;

    public Node getNode() {return node;}


    private final List<MaterialButton> buttons = new ArrayList<>();

    public List<MaterialButton> getButtons() {return buttons;}

    public void addButton(MaterialButton button) {this.buttons.add(button);}


    public NodeScreenContent(Node currentNode) {
        this.node = currentNode;
    }
}

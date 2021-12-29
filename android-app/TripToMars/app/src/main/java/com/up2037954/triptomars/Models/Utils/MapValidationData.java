package com.up2037954.triptomars.Models.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapValidationData {

    private List<String> exploredNodes = new ArrayList<>();

    public List<String> getExploredNodes() {return exploredNodes;}

    public void setExploredNodes(List<String> exploredNodes) {

        exploredNodes = exploredNodes.stream().distinct().collect(Collectors.toList());
        this.exploredNodes = exploredNodes;
    }

    public void addExploredNode(String exploredNode) {exploredNodes.add(exploredNode);}

    public void addExploredNodes(List<String> exploredNodes) {this.exploredNodes.addAll(exploredNodes);}


    private ArrayList<String> endings = new ArrayList<>();

    public ArrayList<String> getEndings() {return endings;}

    public void setEndings(List<String> endings) {

        endings = endings.stream().distinct().collect(Collectors.toList());
        this.endings = new ArrayList<>(endings);
    }

    public void addEnding(String ending) {this.endings.add(ending);}

    public void addEndings(List<String> endings) {this.endings.addAll(endings);}


    public MapValidationData(String nodeId) {
        addExploredNode(nodeId);
    }
}

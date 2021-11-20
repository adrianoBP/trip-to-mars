package Models.Utils;

import java.util.ArrayList;
import java.util.List;

public class MapValidationData {

    private List<String> exploredNodes = new ArrayList<>();

    public List<String> getExploredNodes() {return exploredNodes;}

    public void setExploredNodes(List<String> exploredNodes) {this.exploredNodes = exploredNodes;}

    public void addExploredNode(String exploredNode) {exploredNodes.add(exploredNode);}


    private ArrayList<String> endings = new ArrayList<>();

    public ArrayList<String> getEndings() {return endings;}

    public void setEndings(List<String> endings) {this.endings = new ArrayList<>(endings);}

    public void addEnding(String ending) {this.endings.add(ending);}


    public MapValidationData(String nodeId) {
        addExploredNode(nodeId);
    }
}

package Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Option {

    @SerializedName(value = "node-id")
    private String nodeId;

    public String getNodeId() {return nodeId;}

    public void setNodeId(String nodeId) {this.nodeId = nodeId;}


    @SerializedName(value = "chance")
    private int chance;

    public int getChance() {return chance;}

    public void setChance(int chance) {this.chance = chance;}


    @SerializedName(value = "requirements")
    private List<Requirement> requirements = new ArrayList<>();

    public List<Requirement> getRequirements() {return requirements;}

    public void setRequirements(List<Requirement> requirements) {this.requirements = requirements;}

    public void addRequirement(Requirement requirement) {this.requirements.add(requirement);}


    public Option() {}

    public Option(String nodeId, int chance) {
        this.setNodeId(nodeId);
        this.setChance(chance);
    }

    public Option(String nodeId, int chance, List<Requirement> requirements) {
        this.setNodeId(nodeId);
        this.setChance(chance);
        this.setRequirements(requirements);
    }
}

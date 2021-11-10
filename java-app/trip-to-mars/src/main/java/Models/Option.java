package Models;

import com.google.gson.annotations.SerializedName;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Option {

    @SerializedName(value = "node-id")
    public String NodeId;

    @SerializedName(value = "chance")
    public double Chance;

    @SerializedName(value = "requirements")
    public List<Requirement> Requirements = new ArrayList<>();

    public Option() {}

    public Option(String nodeId, double chance) {
        this.NodeId = nodeId;
        this.Chance = chance;
    }

    public Option(String nodeId, double chance, List<Requirement> requirements) {
        this.NodeId = nodeId;
        this.Chance = chance;
        this.Requirements = requirements;
    }

    public Document toBson() {

        Document bsonDocument = new Document();
        bsonDocument.put("node-id", NodeId);
        bsonDocument.put("chance", Chance);

        List<Document> requirements = Requirements.stream().map(Requirement::toBson).collect(Collectors.toList());
        bsonDocument.put("requirements", requirements);

        return bsonDocument;
    }
}

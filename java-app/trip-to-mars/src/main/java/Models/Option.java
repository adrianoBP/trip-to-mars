package Models;

import com.google.gson.annotations.SerializedName;
import org.bson.Document;

import java.util.ArrayList;

public class Option {

    @SerializedName(value = "node-id")
    public String NodeId;

    @SerializedName(value = "chance")
    public double Chance;

    @SerializedName(value = "requirements")
    public ArrayList<String> Requirements = new ArrayList<>();

    public Option() {}

    public Option(double chance, ArrayList<String> requirements) {
        this.Chance = chance;
        this.Requirements = requirements;
    }

    public Document toBson() {

        Document bsonDocument = new Document();
        bsonDocument.put("node-id", NodeId);
        bsonDocument.put("chance", Chance);
        bsonDocument.put("requirements", Requirements);

        return bsonDocument;
    }
}

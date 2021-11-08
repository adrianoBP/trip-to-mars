package Models;

import com.google.gson.annotations.SerializedName;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node {

    @SerializedName(value = "_id")
    public String Id;

    @SerializedName(value = "title")
    public String Title = "";

    @SerializedName(value = "description")
    public String Description = "";

    @SerializedName(value = "options")
    public ArrayList<Option> Options = new ArrayList<>();

    @SerializedName(value = "save-result")
    public boolean SaveResult = false;

    @SerializedName(value = "is-beginning")
    public boolean IsBeginning = false;

    public Node() {}

    public Node(String title, String description) {
        this.Title = title;
        this.Description = description;
    }
    public Node(String title, String description, boolean saveResult) {
        this.Title = title;
        this.Description = description;
        this.SaveResult = saveResult;
    }
    public Node(String title, String description, boolean saveResult, boolean isBeginning) {
        this.Title = title;
        this.Description = description;
        this.SaveResult = saveResult;
        this.IsBeginning = isBeginning;
    }

    public Document toBson() {

        Document bsonDocument = new Document();
        bsonDocument.put("title", Title);
        bsonDocument.put("description", Description);
        bsonDocument.put("save-result", SaveResult);

        List<Document> options = Options.stream().map(Option::toBson).collect(Collectors.toList());
        bsonDocument.put("options", options);

        return bsonDocument;
    }
}

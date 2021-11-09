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
    public List<Option> Options = new ArrayList<>();

    @SerializedName(value = "item-to-save")
    public String ItemToSave = "";

    @SerializedName(value = "is-beginning")
    public boolean IsBeginning = false;

    public Node() {}

    public Node(String title, String description) {
        this.Title = title;
        this.Description = description;
    }
    public Node(String title, String description, String itemToSave) {
        this.Title = title;
        this.Description = description;
        this.ItemToSave = itemToSave;
    }
    public Node(String title, String description, String itemToSave, boolean isBeginning) {
        this.Title = title;
        this.Description = description;
        this.ItemToSave = itemToSave;
        this.IsBeginning = isBeginning;
    }

    public Document toBson() {

        Document bsonDocument = new Document();
        bsonDocument.put("title", Title);
        bsonDocument.put("description", Description);
        bsonDocument.put("item-to-save", ItemToSave);
        bsonDocument.put("is-beginning", IsBeginning);

        List<Document> options = Options.stream().map(Option::toBson).collect(Collectors.toList());
        bsonDocument.put("options", options);

        return bsonDocument;
    }
}

package Models;

import com.google.gson.annotations.SerializedName;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node {

    @SerializedName(value = "_id")
    private String id;

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public void setId(ObjectId id) {this.id = id.toString();}


    @SerializedName(value = "title")
    private String title = "";

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}


    @SerializedName(value = "description")
    private String description = "";

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}


    @SerializedName(value = "options")
    private List<Option> options = new ArrayList<>();

    public List<Option> getOptions() {return options;}

    public void setOptions(List<Option> options) {this.options = options;}


    @SerializedName(value = "item-to-save")
    private String itemToSave = "";

    public String getItemToSave() {return itemToSave;}

    public void setItemToSave(String itemToSave) {this.itemToSave = itemToSave;}


    @SerializedName(value = "is-beginning")
    private boolean isBeginning = false;

    public boolean isBeginning() {return isBeginning;}

    public void setBeginning(boolean beginning) {isBeginning = beginning;}


    public Node() {}

    public Node(String title, String description) {
        this.setTitle(title);
        this.setDescription(description);
    }

    public Node(String title, String description, String itemToSave) {
        this.setTitle(title);
        this.setDescription(description);
        this.setItemToSave(itemToSave);
    }

    public Node(String title, String description, String itemToSave, boolean isBeginning) {
        this.setTitle(title);
        this.setDescription(description);
        this.setItemToSave(itemToSave);
        this.setBeginning(isBeginning);
    }

    public Document toBson() {

        Document bsonDocument = new Document();
        bsonDocument.put("title", this.getTitle());
        bsonDocument.put("description", this.getDescription());
        bsonDocument.put("item-to-save", this.getItemToSave());
        bsonDocument.put("is-beginning", this.isBeginning);

        List<Document> options = this.getOptions().stream().map(Option::toBson).collect(Collectors.toList());
        bsonDocument.put("options", options);
        return bsonDocument;
    }
}

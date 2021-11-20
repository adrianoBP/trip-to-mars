package Models.NodeData;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Node {

    @SerializedName(value = "_id")
    private String id;

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}


    @SerializedName(value = "title")
    private String title = "";

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}


    @SerializedName(value = "description")
    private String description = "";

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}


    @SerializedName(value = "item-to-save")
    private String itemToSave = "";

    public String getItemToSave() {return itemToSave;}

    public void setItemToSave(String itemToSave) {this.itemToSave = itemToSave;}


    @SerializedName(value = "is-beginning")
    private boolean isBeginning = false;

    public boolean isBeginning() {return isBeginning;}

    public void setBeginning(boolean beginning) {isBeginning = beginning;}


    @SerializedName(value = "options")
    private List<Option> options = new ArrayList<>();

    public List<Option> getOptions() {return options;}

    public void setOptions(List<Option> options) {this.options = options;}

    public void addOption(Option option) {this.options.add(option);}


    public Node() {}

    public Node(String title, String description) {
        this.setId(UUID.randomUUID().toString());
        this.setTitle(title);
        this.setDescription(description);
    }

    public Node(String title, String description, String itemToSave) {
        this.setId(UUID.randomUUID().toString());
        this.setTitle(title);
        this.setDescription(description);
        this.setItemToSave(itemToSave);
    }

    public Node(String title, String description, String itemToSave, boolean isBeginning) {
        this.setId(UUID.randomUUID().toString());
        this.setTitle(title);
        this.setDescription(description);
        this.setItemToSave(itemToSave);
        this.setBeginning(isBeginning);
    }

    public Node(String title, Option option) {
        this.setId(UUID.randomUUID().toString());
        this.setTitle(title);
        this.options = List.of(option);
    }
}

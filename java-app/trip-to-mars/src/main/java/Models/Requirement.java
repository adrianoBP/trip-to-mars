package Models;

import com.google.gson.annotations.SerializedName;
import org.bson.Document;

public class Requirement {

    @SerializedName(value = "name")
    private String name = "";

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}


    @SerializedName(value = "must-exist")
    private boolean mustExist = false;

    public boolean mustExist() {return mustExist;}

    public void setMustExist(boolean mustExist) {this.mustExist = mustExist;}


    public Requirement() {}

    public Requirement(String name, boolean mustExist) {
        this.setName(name);
        this.setMustExist(mustExist);
    }

    public Document toBson() {

        Document bsonDocument = new Document();
        bsonDocument.put("name", this.getName());
        bsonDocument.put("must-exist", this.mustExist);

        return bsonDocument;
    }
}

package Models;

import com.google.gson.annotations.SerializedName;
import org.bson.Document;

public class Requirement {

    @SerializedName(value = "name")
    public String Name = "";

    @SerializedName(value = "must-exist")
    public boolean MustExist = false;

    public Requirement() {}

    public Requirement(String name, boolean mustExist) {
        this.Name = name;
        this.MustExist = mustExist;
    }

    public Document toBson() {

        Document bsonDocument = new Document();
        bsonDocument.put("name", Name);
        bsonDocument.put("must-exist", MustExist);

        return bsonDocument;
    }
}

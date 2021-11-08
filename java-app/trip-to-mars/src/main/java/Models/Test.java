package Models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    @SerializedName(value = "_id")
    public String id;

    @SerializedName(value = "name")
    public String Name;

    @SerializedName(value = "age")
    public int Age;

    @SerializedName(value = "createdAt")
    public Date CreatedAt = new Date();

    @SerializedName(value = "ttt")
    public Date Ttt = new Date();

    public Test() {}

    public Test(String name, int age) {
        this.Name = name;
        this.Age = age;
    }

    @Override
    public String toString() {
        return "[" + id + "] - " + Name + ", " + Age + ", " + CreatedAt.toString();
    }

//    public Document toBsonDocument() {
//        DateFormat parser = new SimpleDateFormat("YYYY-mm-ddTHH:MM:ssZ");
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(Date.class,
//
//
//                        (JsonSerializer<Date>) (json, typeOfT, context) -> parser.format(json)))
//                .create();
//
//        Gson gson = new Gson();
//        String json = gson.toJson(this);
//        return  Document.parse(json);
//    }
}

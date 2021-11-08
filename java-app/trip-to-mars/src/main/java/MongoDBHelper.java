import Models.Node;
import Models.Option;
import Models.Test;
import com.google.gson.*;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MongoDBHelper {

    private static final String nodesCollectionName = "node-data";
    private static final String usersCollectionName = "user-data";

    private static MongoDatabase mongoDB = null;
    private static final JsonWriterSettings settings = JsonWriterSettings.builder()
            .objectIdConverter((value, writer) -> writer.writeString(value.toString()))
            .dateTimeConverter((value, writer) -> writer.writeString(value.toString()))
            .build();

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class,
                    (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()))
            .create();

    public static void Init() {

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://" + AppSettings.MongoDBUsername + ":" + AppSettings.MongoDBPassword + "@adriano-bp.lu0vf.mongodb.net"));
        mongoDB = mongoClient.getDatabase("PAPL-trip-to-mars");
    }

    public static void InsertNode(Node node) {

        MongoCollection<Document> collection = mongoDB.getCollection(nodesCollectionName);
        collection.insertOne(node.toBson());
    }

    public static void AddNodeOptions(String id, ArrayList<Option> options) {

        List<Document> optionsDocuments = options.stream().map(Option::toBson).collect(Collectors.toList());

        // TODO: Update document with above options
    }

    public static void UpdateNode(String id, Updates updates) {

        MongoCollection<Document> collection = mongoDB.getCollection(nodesCollectionName);
        collection.updateOne(
                Filters.eq("_id", new ObjectId(id)),
                Updates.combine(
                        Updates.set("name", "NAME UPDATED AGAIN"),
                        Updates.set("age", 4)
                ));
    }


    public static ArrayList<Test> GetAll() {

        MongoCollection<Document> collection = mongoDB.getCollection(nodesCollectionName);
        MongoCursor<Document> cursor = collection.find().cursor();
        return CursorToElements(cursor);
    }

    public static void AddTest() {

        MongoCollection<Document> collection = mongoDB.getCollection(nodesCollectionName);
        Test insertTest = new Test("T", 11);
//
//        collection.insertOne(insertTest.toBsonDocument());
    }

    public static void UpdateTest(String id) {

        MongoCollection<Document> collection = mongoDB.getCollection(nodesCollectionName);
        collection.updateOne(
                Filters.eq("_id", new ObjectId(id)),
                Updates.combine(
                        Updates.set("name", "NAME UPDATED AGAIN"),
                        Updates.set("age", 4)
                ));
    }

    private static ArrayList<Test> CursorToElements(MongoCursor<Document> cursor) {
        ArrayList<Test> documents = new ArrayList<>();

        while (cursor.hasNext()) {

            String jsonContent = cursor.next().toJson(settings);
            Test t = gson.fromJson(jsonContent, Test.class);
            documents.add(t);
        }
        return documents;
    }
}

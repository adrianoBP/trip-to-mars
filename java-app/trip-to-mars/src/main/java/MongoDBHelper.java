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

        MongoCollection<Document> collection = mongoDB.getCollection(nodesCollectionName);
        collection.updateOne(
                Filters.eq("_id", new ObjectId(id)),
                Updates.combine(
                        Updates.set("options", optionsDocuments)
                ));
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

    public static void AddNewNodeProperty(String name, Object value) {

        ArrayList<Node> availableNodes = GetAll();
        MongoCollection<Document> collection = mongoDB.getCollection(nodesCollectionName);

        for (Node n : availableNodes) {
            collection.updateOne(
                    Filters.eq("_id", new ObjectId(n.Id)),
                    Updates.combine(
                            Updates.set(name, value)
                    ));
        }
    }


    public static ArrayList<Node> GetAll() {

        MongoCollection<Document> collection = mongoDB.getCollection(nodesCollectionName);
        MongoCursor<Document> cursor = collection.find().cursor();
        return CursorToNodes(cursor);
    }


    private static ArrayList<Node> CursorToNodes(MongoCursor<Document> cursor) {
        ArrayList<Node> documents = new ArrayList<>();

        while (cursor.hasNext()) {

            String jsonContent = cursor.next().toJson(settings);
            Node node = gson.fromJson(jsonContent, Node.class);
            documents.add(node);
        }
        return documents;
    }
}

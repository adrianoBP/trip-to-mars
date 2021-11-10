package Helpers;

import Models.Node;
import Models.Option;
import com.google.gson.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MongoDBHelper {

    private static MongoCollection<Document> nodesCollection;
    private static MongoCollection<Document> userDataCollection;

    private static final JsonWriterSettings settings = JsonWriterSettings.builder()
            .objectIdConverter((value, writer) -> writer.writeString(value.toString()))
            .dateTimeConverter((value, writer) -> writer.writeString(value.toString()))
            .build();

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class,
                    (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()))
            .create();

    public static void init() {

        MongoClient mongoClient = MongoClients.create("mongodb+srv://" +
                AppSettings.mongoDBUsername + ":" + AppSettings.mongoDBPassword + "@adriano-bp.lu0vf.mongodb.net");

        MongoDatabase mongoDB = mongoClient.getDatabase(AppSettings.mongoDBDatabase);
        nodesCollection = mongoDB.getCollection(AppSettings.mongoDBCollectionNodes);
        userDataCollection = mongoDB.getCollection(AppSettings.mongoDBCollectionUsers);
    }


    public static List<Node> getNodes() {

        MongoCursor<Document> cursor = nodesCollection.find().cursor();
        return cursorToNodes(cursor);
    }

    public static List<Node> getNodes(Bson filter) {
        MongoCursor<Document> cursor = nodesCollection.find(filter).cursor();
        return cursorToNodes(cursor);
    }


    public static String insertNode(Node node) {

        Document docToInsert = node.toBson();
        nodesCollection.insertOne(docToInsert);

        return docToInsert.getObjectId("_id").toString();
    }

    public static void addNodeOptions(String id, ArrayList<Option> options) {

        List<Document> optionsDocuments = options.stream().map(Option::toBson).collect(Collectors.toList());

        nodesCollection.updateOne(
                Filters.eq("_id", new ObjectId(id)),
                Updates.combine(
                        Updates.set("options", optionsDocuments)
                ));
    }

//    public static void UpdateNode(String id, Updates updates) {
//
//        MongoCollection<Document> collection = mongoDB.getCollection(nodesCollectionName);
//        collection.updateOne(
//                Filters.eq("_id", new ObjectId(id)),
//                Updates.combine(
//                        Updates.set("name", "NAME UPDATED AGAIN"),
//                        Updates.set("age", 4)
//                ));
//    }

//    public static void AddNewNodeProperty(String name, Object value) {
//
//        List<Node> availableNodes = GetAll();
//        MongoCollection<Document> collection = mongoDB.getCollection(nodesCollectionName);
//
//        for (Node n : availableNodes) {
//            collection.updateOne(
//                    Filters.eq("_id", new ObjectId(n.Id)),
//                    Updates.combine(
//                            Updates.set(name, value)
//                    ));
//        }
//    }


    private static List<Node> cursorToNodes(MongoCursor<Document> cursor) {
        List<Node> documents = new ArrayList<>();

        while (cursor.hasNext()) {

            String jsonContent = cursor.next().toJson(settings);
            Node node = gson.fromJson(jsonContent, Node.class);
            documents.add(node);
        }
        return documents;
    }
}

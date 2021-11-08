import Models.Node;
import Models.Option;
import Models.Test;
import com.mongodb.Mongo;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Started");

        AppSettings.Init();
        MongoDBHelper.Init();

// ADD NODE
//        MongoDBHelper.InsertNode(new Node("Take off", "", false));

        MongoDBHelper.AddNewNodeProperty("is-beginning", false);
// UPDATE NODE
//        ArrayList<Option> options = new ArrayList<>();
//        options.add(new Option("6189240d9432b754a264f2d0", 0, new ArrayList<>()));
//        MongoDBHelper.AddNodeOptions("618922b7bbcfd5422b8e3af1", options);
//        MongoDBHelper.AddNodeOptions("618922b8bbcfd5422b8e3af2", options);
//        MongoDBHelper.AddNodeOptions("618922b8bbcfd5422b8e3af3", options);


// SHOW NODES
//        ArrayList<Node> testElements = MongoDBHelper.GetAll();
//        testElements.forEach(System.out::println);

    }
}

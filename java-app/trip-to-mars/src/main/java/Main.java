import Models.Node;
import Models.Test;
import com.mongodb.Mongo;
import org.bson.Document;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Started");

        AppSettings.Init();

        MongoDBHelper.Init();

//        Node node1 = new Node("Select an item", "", true);
//        MongoDBHelper.InsertNode(node1);



//        Node node2 = new Node("Start", "");
//        MongoDBHelper.InsertNode(node2);
//
//        Node node3 = new Node("Start", "");
//        MongoDBHelper.InsertNode(node3);


//        ArrayList<Test> testElements = MongoDBHelper.GetAll();
//        testElements.forEach(System.out::println);

//        MongoDBHelper.UpdateTest(testElements.get(1).id);
//
//        MongoDBHelper.AddTest();
//
//        testElements = MongoDBHelper.GetAll();
//        testElements.forEach((x) -> System.out.println(x.toString() + "\n"));
    }
}

import Helpers.AppSettings;
import Helpers.MapHelper;
import Helpers.MongoDBHelper;
import Models.Option;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Started");

        AppSettings.Init();
        MongoDBHelper.Init();

        MapHelper.BuildMap();

// ADD NODE




// UPDATE NODE
//        options.add(new Option(b, 50, new ArrayList<>()));
//        options.add(new Option(c, 50, new ArrayList<>()));
//        MongoDBHelper.AddNodeOptions(a, options);
//
//        options = new ArrayList<>();
//        options.add(new Option(d, 0, new ArrayList<>()));
//        options.add(new Option(e, 0, new ArrayList<>()));
//        MongoDBHelper.AddNodeOptions(b, options);
//
//        options = new ArrayList<>();
//        options.add(new Option(f, 0, new ArrayList<>()));
//        MongoDBHelper.AddNodeOptions(c, options);
//
//        options = new ArrayList<>();
//        options.add(new Option(g, 0, new ArrayList<>()));
//        options.add(new Option(h, 0, new ArrayList<>()));
//        MongoDBHelper.AddNodeOptions(f, options);
//
//        options = new ArrayList<>();
//        options.add(new Option(i, 0, new ArrayList<>()));
//        MongoDBHelper.AddNodeOptions(g, options);
//
//        options = new ArrayList<>();
//        options.add(new Option(j, 0, new ArrayList<>()));
//        options.add(new Option(k, 0, new ArrayList<>()));
//        MongoDBHelper.AddNodeOptions(i, options);
//
//        options = new ArrayList<>();
//        options.add(new Option(b, 0, new ArrayList<>()));
//        MongoDBHelper.AddNodeOptions(a, options);


//        MongoDBHelper.AddNodeOptions("618922b8bbcfd5422b8e3af2", options);
//        MongoDBHelper.AddNodeOptions("618922b8bbcfd5422b8e3af3", options);

// CREATE NEW PROPERTY
//        MongoDBHelper.AddNewNodeProperty("is-beginning", false);


// SHOW NODES
//        ArrayList<Node> testElements = MongoDBHelper.GetAll();
//        testElements.forEach(System.out::println);

    }
}

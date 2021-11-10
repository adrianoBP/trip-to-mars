package Models;

import java.util.ArrayList;
import java.util.List;

public class UserSettings {

    private String name;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}


    private List<String> savedItems = new ArrayList<>();

    public List<String> getSavedItems() {return savedItems;}

    public void setSavedItems(List<String> savedItems) {this.savedItems = savedItems;}
}

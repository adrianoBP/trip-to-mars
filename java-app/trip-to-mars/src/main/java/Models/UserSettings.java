package Models;

import java.util.ArrayList;
import java.util.List;

public class UserSettings {

    private String name;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}


    private List<String> savedItems = new ArrayList<>();

    public List<String> getSavedItems() {return savedItems;}

    public boolean hasSavedItem(String item) {return savedItems.contains(item);}

    public void setSavedItems(List<String> savedItems) {this.savedItems = savedItems;}

    public void addSavedItem(String item) {this.savedItems.add(item);}

    public void removeSavedItem(String item) {this.savedItems.remove(item);}
}

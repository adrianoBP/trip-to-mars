package com.up2037954.triptomars.Models;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class UserSettings {

    private String name;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}


    private final List<String> savedItems = new ArrayList<>();

    public List<String> getSavedItems() {return savedItems;}

    /**
     * Adds an item to the user profile - If it is already exist, it will remove it instead.
     */
    public void tryAddItem(String item) {

        if (TextUtils.isEmpty(item))
            return;

        if (this.savedItems.contains(item))
            this.savedItems.remove(item);
        else
            this.savedItems.add(item);

        // TODO: Save to file
    }


    public UserSettings() {
        // TODO: Get saved settings from filesystem
    }
}

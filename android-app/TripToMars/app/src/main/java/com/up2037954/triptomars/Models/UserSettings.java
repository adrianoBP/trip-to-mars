package com.up2037954.triptomars.Models;

import android.content.Context;
import android.text.TextUtils;

import androidx.core.graphics.drawable.IconCompat;

import com.google.gson.Gson;
import com.up2037954.triptomars.Helpers.AppSettings;
import com.up2037954.triptomars.Helpers.Standard.FileHelper;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserSettings implements Serializable {


    // App related settings

    public boolean allowImageAnimations = true;
    public boolean allowTextAnimations = true;

    // Game related settings

    private List<String> savedItems = new ArrayList<>();

    /**
     * Adds an item to the user profile - If it is already exist, it will remove it instead.
     */
    public void tryAddItem(String item, Context context) throws IOException {

        if (TextUtils.isEmpty(item))
            return;

        if (this.savedItems.contains(item))
            this.savedItems.remove(item);
        else
            this.savedItems.add(item);

        save(context);
    }

    public List<String> getSavedItems() {return savedItems;}


    private String lastVisitedNode = null;

    public String getLastVisitedNode() { return this.lastVisitedNode; }

    public void setLastVisitedNode(String nodeId) { this.lastVisitedNode = nodeId; }


    private String lastAnimationId = null;

    public String getLastAnimationId() { return this.lastAnimationId;}

    public void setLastAnimationId(String lastAnimationId, Context context) throws IOException {
        if (lastAnimationId != null) {
            this.lastAnimationId = lastAnimationId;
            save(context);
        }
    }


    private List<String> exploredNodes = new ArrayList<>();

    public void addExploredNode(String nodeId, Context context) throws IOException {

        if (!exploredNodes.contains(nodeId))
            exploredNodes.add(nodeId);

        lastVisitedNode = nodeId;

        save(context);
    }

    public List<String> getExploredNodes() { return this.exploredNodes; }


    public UserSettings(Context context) throws IOException {

        String userSettingsFileContent = FileHelper.getOrCreate(AppSettings.usersFilePath, "{}", context);

        UserSettings settings = new Gson().fromJson(userSettingsFileContent, UserSettings.class);

        if (settings.lastVisitedNode != null) {
            this.allowImageAnimations = settings.allowImageAnimations;
            this.allowTextAnimations = settings.allowTextAnimations;

            this.savedItems = settings.getSavedItems();
            this.lastVisitedNode = settings.getLastVisitedNode();
            this.exploredNodes = settings.getExploredNodes();
        }
    }

    public void save(Context context) throws IOException {
        FileHelper.save(AppSettings.usersFilePath, new Gson().toJson(this), context);
    }
}

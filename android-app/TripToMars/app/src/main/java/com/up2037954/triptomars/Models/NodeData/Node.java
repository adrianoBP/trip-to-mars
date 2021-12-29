package com.up2037954.triptomars.Models.NodeData;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Node {

    @SerializedName(value = "_id")
    private String id;

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}


    @SerializedName(value = "title")
    private String title = "";

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}


    @SerializedName(value = "description")
    private String description = "";

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}


    @SerializedName(value = "item-to-save")
    private String itemToSave = "";

    public String getItemToSave() {return itemToSave;}

    public void setItemToSave(String itemToSave) {this.itemToSave = itemToSave;}


    @SerializedName(value = "is-beginning")
    private boolean isBeginning = false;

    public boolean isBeginning() {return isBeginning;}

    public void setBeginning(boolean beginning) {isBeginning = beginning;}


    @SerializedName(value = "options")
    private List<Option> options = new ArrayList<>();

    public List<Option> getOptions() {return options;}

    public void setOptions(List<Option> options) {this.options = options;}

    public void addOption(Option option) {this.options.add(option);}

    @SerializedName(value = "animation")
    private String animation = "";

    public String getAnimation() {return animation;}

    public void setAnimation(String animation) {this.animation = animation;}

    public boolean hasAnimation() {return !TextUtils.isEmpty(getAnimation());}

    @SerializedName(value = "animationDuration")
    private int animationLoops = 0;

    public int getAnimationLoops() { return animationLoops;}

    private void setAnimationLoops(int animationLoops) {
        this.animationLoops = animationLoops;
    }


    public Node() {}

    public Node(String title, String description) {
        this.setId(UUID.randomUUID().toString());
        this.setTitle(title);
        this.setDescription(description);
    }

    public Node(String title, String description, String itemToSave) {
        this.setId(UUID.randomUUID().toString());
        this.setTitle(title);
        this.setDescription(description);
        this.setItemToSave(itemToSave);
    }

    public Node(String title, String description, String itemToSave, String animation) {
        this.setId(UUID.randomUUID().toString());
        this.setTitle(title);
        this.setDescription(description);
        this.setItemToSave(itemToSave);
        this.setAnimation(animation);
    }

    public Node(String title, String description, String itemToSave, String animation, int animationLoops) {
        this.setId(UUID.randomUUID().toString());
        this.setTitle(title);
        this.setDescription(description);
        this.setItemToSave(itemToSave);
        this.setAnimation(animation);
        this.setAnimationLoops(animationLoops);
    }

    public Node(String title, String description, boolean isBeginning) {
        this.setId(UUID.randomUUID().toString());
        this.setTitle(title);
        this.setDescription(description);
        this.setBeginning(isBeginning);
    }

    public Node(String title, Option option) {
        this.setId(UUID.randomUUID().toString());
        this.setTitle(title);
        this.setOptions(Collections.singletonList(option));
    }

    public boolean isChanceChoice() {
        return this.getOptions().stream().allMatch(Option::isChanceOption);
    }
}

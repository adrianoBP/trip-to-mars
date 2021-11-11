package com.up2037954.triptomars.Models;

import com.google.gson.annotations.SerializedName;

public class Requirement {

    @SerializedName(value = "name")
    private String name = "";

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}


    @SerializedName(value = "must-exist")
    private boolean mustExist = false;

    public boolean mustExist() {return mustExist;}

    public void setMustExist(boolean mustExist) {this.mustExist = mustExist;}


    public Requirement() {}

    public Requirement(String name, boolean mustExist) {
        this.setName(name);
        this.setMustExist(mustExist);
    }
}

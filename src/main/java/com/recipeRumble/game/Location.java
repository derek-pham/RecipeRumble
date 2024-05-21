package com.recipeRumble.game;

public class Location {
    String name;
    String description;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }
}

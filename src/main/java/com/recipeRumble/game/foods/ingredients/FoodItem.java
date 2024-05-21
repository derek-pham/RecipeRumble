package com.recipeRumble.game.foods.ingredients;

public class FoodItem {
    private String name;
    private String description;

    public FoodItem(String name, String description, int searchScoreReq) {
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

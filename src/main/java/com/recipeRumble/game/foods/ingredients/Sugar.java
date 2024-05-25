package com.recipeRumble.game.foods.ingredients;

public class Sugar extends FoodItem{
    private final String adjective = "Sweet";

    public Sugar() {
        super("Sugar", "Derived from the Saccharum Crystalis plant");
    }

    public String getAdjective() {
        return adjective;
    }
}

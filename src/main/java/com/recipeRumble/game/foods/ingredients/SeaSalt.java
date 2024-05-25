package com.recipeRumble.game.foods.ingredients;

public class SeaSalt extends FoodItem{
    private final String adjective = "Salty";

    public SeaSalt() {
        super("Sea Salt", "Harvested from the pristine waters of the Nidaria ocean.");
    }

    @Override
    public String getAdjective() {
        return adjective;
    }
}

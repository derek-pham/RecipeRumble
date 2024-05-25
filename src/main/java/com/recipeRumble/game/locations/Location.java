package com.recipeRumble.game.locations;

import com.recipeRumble.game.Inventory;
import com.recipeRumble.game.foods.ingredients.FoodItem;
import com.recipeRumble.game.utils.PrintUtils;

public abstract class Location {
    private String name;
    private String description;

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

    public abstract void search();

    public abstract FoodItem takeItem(String foodName);

    public void cook(Inventory inventory) {
        PrintUtils.printWithDelay("You can't cook here. You need to be in the kitchen to do that.");
    };

    public int serve(Inventory inventory) {
        PrintUtils.printWithDelay("You can only serve in the dining hall.");
        return 0;
    };

    public void resetHasSearched() {

    }
}

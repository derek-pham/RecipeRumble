package com.recipeRumble.game.foods.ingredients;

import java.util.HashMap;
import java.util.Map;

public class FoodItemDatabase {
    private static final Map<String, FoodItem> foodItemDB = new HashMap<>();

    static {
        foodItemDB.put("purple maitake", new PurpleMaitake());
        foodItemDB.put("green anise", new GreenAnise());
        foodItemDB.put("cucu berry", new CucuBerry());
        foodItemDB.put("pyrus bulb", new PyrusBulb());
        foodItemDB.put("cherry beet", new CherryBeet());
        foodItemDB.put("wild carota", new WildCarota());
        foodItemDB.put("sea salt", new SeaSalt());
        foodItemDB.put("sugar", new Sugar());
    }


    public static FoodItem convertStringtoFoodItem(String foodItemName) {
        return foodItemDB.get(foodItemName.toLowerCase());
    }
}

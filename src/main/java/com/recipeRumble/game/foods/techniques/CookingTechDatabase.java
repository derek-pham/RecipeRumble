package com.recipeRumble.game.foods.techniques;

import java.util.HashMap;
import java.util.Map;

public class CookingTechDatabase {
    private static final Map<String, CookingTechnique> foodTechDB = new HashMap<>();

    static {
        foodTechDB.put("grilling", new Grilling());
        foodTechDB.put("baking", new Baking());
    }

    public static CookingTechnique convertStringtoCookingTech(String techName) {
        return foodTechDB.get(techName.toLowerCase());
    }
}

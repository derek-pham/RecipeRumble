package com.recipeRumble.game.locations;

import com.recipeRumble.game.foods.ingredients.CucuBerry;
import com.recipeRumble.game.foods.ingredients.FoodItem;
import com.recipeRumble.game.foods.ingredients.GreenAnise;
import com.recipeRumble.game.foods.ingredients.PurpleMaitake;
import com.recipeRumble.game.utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Forest extends Location {
    private Map<String, FoodItem> foodItemMap = new HashMap<>();
    private Random random = new Random();
    private int searchScore = 0;
    private boolean hasSearched = false;

    public Forest() {
        super("Forest", "A dense forest with vibrant hues and fragrant scents mingling in the air.");
    }

    @Override
    public void search() {
        if (!hasSearched) {
            PrintUtils.printWithDelay("You look across the forest floor..");
            this.searchScore = random.nextInt(101); // 0 - 100 dice roll
            //Issue: if you've found purple maitake then the rest is guaranteed
            checkAndAddFoodItem(new PurpleMaitake(), PurpleMaitake.searchScoreReq);
            checkAndAddFoodItem(new GreenAnise(), GreenAnise.searchScoreReq);
            checkAndAddFoodItem(new CucuBerry(), CucuBerry.searchScoreReq);

            if (foodItemMap.isEmpty()) {
                PrintUtils.printWithDelay("You didn't find any ingredients.");
            }
            this.hasSearched = true;
        } else {
            PrintUtils.printWithDelay("You've already searched the forest.");
            if (foodItemMap.isEmpty()) {
                PrintUtils.printWithDelay("There is nothing left here.");
            } else {
                PrintUtils.printWithDelay("You've yet to take the following:\n");

                String[] foodItemNames = {"purple maitake", "green anise", "cucu berry"};

                for (String foodItemName : foodItemNames) {
                    if (foodItemMap.containsKey(foodItemName)) {
                        PrintUtils.printWithDelay(foodItemMap.get(foodItemName).getName());
                    }
                }
                System.out.println();
            }
        }
    }

    private void checkAndAddFoodItem(FoodItem foodItem, int scoreReq) {
        if (searchScore >= scoreReq) {
            foodItemMap.put(foodItem.getName().toLowerCase(), foodItem);
            PrintUtils.printWithDelay("You found a " + foodItem.getName() + "!");
        }
    }

    @Override
    public void resetHasSearched() {
        this.hasSearched = false;
        foodItemMap.clear();
    }

    @Override
    public FoodItem takeItem(String foodName) {
        // Get the item from the map
        FoodItem foodItem = foodItemMap.get(foodName.toLowerCase());
        if (foodItem != null) {
            // Remove the item from the map
            foodItemMap.remove(foodName.toLowerCase());
            PrintUtils.printWithDelay("You have taken the "+foodItem.getName()+".");
        }
        return foodItem;
    }
}
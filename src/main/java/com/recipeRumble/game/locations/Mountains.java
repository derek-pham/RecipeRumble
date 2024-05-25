package com.recipeRumble.game.locations;

import com.recipeRumble.game.foods.ingredients.*;
import com.recipeRumble.game.utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Mountains extends Location{
    private Map<String, FoodItem> foodItemMap = new HashMap<>();
    private Random random = new Random();
    private int searchScore = 0;
    private boolean hasSearched = false;

    public Mountains() {
        super("Mountain", "A tall mountain with a breathtaking view, featuring lush fields and diverse flora at its base.");
    }

    @Override
    public void search() {
        if (!hasSearched) {
            PrintUtils.printWithDelay("You look across the mountain fields..");
            this.searchScore = random.nextInt(101); // 0 - 100 dice roll
            checkAndAddFoodItem(new PyrusBulb(), PyrusBulb.searchScoreReq);
            checkAndAddFoodItem(new CherryBeet(), CherryBeet.searchScoreReq);
            checkAndAddFoodItem(new WildCarota(), WildCarota.searchScoreReq);

            if (foodItemMap.isEmpty()) {
                PrintUtils.printWithDelay("You didn't find any ingredients.");
            }
            this.hasSearched = true;
        } else {
            PrintUtils.printWithDelay("You've already searched the mountains.");
            if (foodItemMap.isEmpty()) {
                PrintUtils.printWithDelay("There is nothing left here.");
            } else {
                PrintUtils.printWithDelay("You've yet to take the following:\n");

                String[] foodItemNames = {"pyrus bulb", "cherry beet", "wild carota"};

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

    public void resetHasSearched() {
        this.hasSearched = false;
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

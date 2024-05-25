package com.recipeRumble.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.recipeRumble.game.foods.dishes.FoodDish;
import com.recipeRumble.game.foods.dishes.Recipe;
import com.recipeRumble.game.foods.ingredients.*;
import com.recipeRumble.game.foods.techniques.Baking;
import com.recipeRumble.game.foods.techniques.Grilling;
import com.recipeRumble.game.utils.PrintUtils;

public class Inventory {
    Map<Object, Integer> inventory;

    public Inventory() {
        this.inventory = new HashMap<>();
//        FOR TESTING PURPOSES:
        // SUCCESS
//        Recipe testDish = new Recipe();
//        testDish.setMainIngredient(new CucuBerry());
//        testDish.setMainSeasoning(new Sugar());
//        testDish.setCookingTechnique(new Baking());
//        inventory.put(new FoodDish(testDish),1);
//
//        // FAIL ON CKTECH 2ND CHECK
//        Recipe testDish2 = new Recipe();
//        testDish2.setMainIngredient(new CherryBeet());
//        testDish2.setMainSeasoning(new SeaSalt());
//        testDish2.setCookingTechnique(new Grilling());
//        inventory.put(new FoodDish(testDish2),1);

        // FAIL ON MAIN INGREDIENT 1ST CHECK
//        Recipe testDish3 = new Recipe();
//        testDish3.setMainIngredient(new PyrusBulb());
//        testDish3.setMainSeasoning(new SeaSalt());
//        testDish3.setCookingTechnique(new Grilling());
//        inventory.put(new FoodDish(testDish3),1);
//
//        // FAIL ON MAIN seasoning 3RD CHECK
//        Recipe testDish4 = new Recipe();
//        testDish4.setMainIngredient(new PurpleMaitake());
//        testDish4.setMainSeasoning(new Sugar());
//        testDish4.setCookingTechnique(new Grilling());
//        inventory.put(new FoodDish(testDish4),1);
//        //
    }

    public void addItem(Object item, int quantity) {
        inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
    }

    public int getQuantity(Object item) {
        return inventory.getOrDefault(item, 0);
    }

    public FoodItem removeItemFromString(String itemName) {
        FoodItem item = FoodItemDatabase.convertStringtoFoodItem(itemName);
        if (item != null && inventory.containsKey(item)) {
            int currentQuantity = inventory.get(item);
            if (currentQuantity <= 1) {
                inventory.remove(item);
            } else {
                inventory.put(item, currentQuantity - 1);
            }
            PrintUtils.printWithDelay("You have taken 1 " + itemName + " out of your inventory.");
            return item;
        } else {
            PrintUtils.printWithDelay(itemName + " is not in your inventory.");
            return null;
        }
    }

    public void removeFoodDish(FoodDish foodDish) {
        if (foodDish != null && inventory.containsKey(foodDish)) {
            int currentQuantity = inventory.get(foodDish);
            if (currentQuantity <= 1) {
                inventory.remove(foodDish);
            } else {
                inventory.put(foodDish, currentQuantity - 1);
            }
            PrintUtils.printWithDelay("You have taken 1 " + foodDish + " out of your inventory.");
        } else {
            PrintUtils.printWithDelay(foodDish + " is not in your inventory.");
        }
    }

    public void printInventory() {
        for (Map.Entry<Object, Integer> entry : inventory.entrySet()) {
            if (entry.getKey() instanceof FoodItem) {
                PrintUtils.printWithDelay(((FoodItem) entry.getKey()).getName() + ": " + entry.getValue());
            } else {
                PrintUtils.printWithDelay(entry.getKey().toString() + ": " + entry.getValue());
            }
        }
    }

    // New method to check if an item exists by name
    public boolean itemExists(String itemName) {
        for (Object item : inventory.keySet()) {
            if (item instanceof FoodItem && ((FoodItem) item).getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Inventory inventory1 = new Inventory();
        inventory1.addItem(new CucuBerry(), 1);
        inventory1.printInventory();
        inventory1.removeItemFromString("cucu berry");
        Recipe testDish4 = new Recipe();
        testDish4.setMainIngredient(new PurpleMaitake());
        testDish4.setMainSeasoning(new Sugar());
        testDish4.setCookingTechnique(new Grilling());
        FoodDish testfoodDish4 = new FoodDish(testDish4);
        inventory1.removeFoodDish(testfoodDish4);
        inventory1.printInventory();
    }

    public Set getFoodDishes() {
        return inventory.keySet();
    }


}

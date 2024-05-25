package com.recipeRumble.game.locations;

import com.recipeRumble.game.Inventory;
import com.recipeRumble.game.foods.dishes.FoodDish;
import com.recipeRumble.game.foods.dishes.Recipe;
import com.recipeRumble.game.foods.ingredients.FoodItem;
import com.recipeRumble.game.foods.ingredients.FoodItemDatabase;
import com.recipeRumble.game.foods.techniques.CookingTechDatabase;
import com.recipeRumble.game.foods.techniques.CookingTechnique;
import com.recipeRumble.game.utils.PrintUtils;

import java.util.Scanner;

public class Kitchen extends Location {
    Recipe recipe = new Recipe();

    public Kitchen() {
        super("Kitchen", "A place where delicious meals are made.");
    }

    @Override
    public void search() {
        PrintUtils.printWithDelay("The kitchen looks clean.");
    }

    @Override
    public FoodItem takeItem(String foodName) {
        return null;
    }

    @Override
    public void cook(Inventory inventory) {
        Scanner scanner = new Scanner(System.in);
        PrintUtils.printWithDelay("Alright, let's cook something delicious!");

        if (!handleMainIngredient(scanner, inventory)) {
            PrintUtils.printWithDelay("Cooking process cancelled.");
            return;
        }

        if (!handleMainSeasoning(scanner, inventory)) {
            PrintUtils.printWithDelay("Cooking process cancelled.");
            return;
        }

        if (!handleCookingTechnique(scanner, inventory)) {
            PrintUtils.printWithDelay("Cooking process cancelled.");
            return;
        }

        PrintUtils.printWithDelay("All set! Let's get cooking!");
        PrintUtils.delay(500);
        PrintUtils.printWithDelay("SiZzLe*CHop%CrAcKle#Spl@sh!WhIsk4bUzz$CRunch&HiSs^swiSh*PoP");
        PrintUtils.delay(1000);

        FoodDish cookedDish = new FoodDish(this.recipe);
        inventory.addItem(cookedDish, 1);
        PrintUtils.printWithDelay("You cooked up a "+cookedDish.toString()+"!");
        resetRecipe();
    }

    public void resetRecipe() {
        this.recipe = new Recipe();
    }

    public boolean handleMainIngredient(Scanner scanner, Inventory inventory) {
        boolean hasMainIngredientAvailable = false;
        for (Object item: inventory.getFoodDishes()) {
            if (item instanceof FoodItem) {
                hasMainIngredientAvailable = true;
            }
        }
        if (!hasMainIngredientAvailable) {
            PrintUtils.printWithDelay("You do not have any ingredients to cook with right now.");
            return false;
        }


        int i = 0;
        while (true) {
            PrintUtils.printWithDelay("What would you like your main ingredient to be?");
            if (i < 1) {
                PrintUtils.printWithDelay("Your inventory currently has:");
                inventory.printInventory();
                i++;
            }
            String mainIngredientChoice = scanner.nextLine();
            if (mainIngredientChoice.equalsIgnoreCase("back")) {
                return false;
            }
            if (inventory.itemExists(mainIngredientChoice)) {
                FoodItem itemTaken = inventory.removeItemFromString(mainIngredientChoice);
                recipe.setMainIngredient(itemTaken);
                PrintUtils.printWithDelay("You have chosen the " + itemTaken.getName());
                return true;
            } else {
                PrintUtils.printWithDelay("Invalid choice. You can only pick from the ingredients you found while searching.");
            }
        }
    }

    private boolean handleMainSeasoning(Scanner scanner, Inventory inventory) {
        while (true) {
            PrintUtils.printWithDelay("What would you like your main seasoning to be? You can only find 'sea salt' or 'sugar' in the kitchen.");
            String mainSeasoningChoice = scanner.nextLine();
            if (mainSeasoningChoice.equalsIgnoreCase("back")) {
                return false;
            }
            FoodItem mainSObj = FoodItemDatabase.convertStringtoFoodItem(mainSeasoningChoice);
            if (mainSObj != null) {
                recipe.setMainSeasoning(mainSObj);
                PrintUtils.printWithDelay("You have chosen " + mainSObj.getName() + " as the main seasoning.");
                return true;
            } else {
                PrintUtils.printWithDelay("The only seasonings you have available are sea salt and sugar.");
            }
        }
    }

    private boolean handleCookingTechnique(Scanner scanner, Inventory inventory) {
        while (true) {
            PrintUtils.printWithDelay("What cooking technique would you like to use? You only know how to do 'grilling' or 'baking'.");
            String cookingTechniqueChoice = scanner.nextLine();
            if (cookingTechniqueChoice.equalsIgnoreCase("back")) {
                return false;
            }
            CookingTechnique mainCkTech = CookingTechDatabase.convertStringtoCookingTech(cookingTechniqueChoice);
            if (mainCkTech != null) {
                recipe.setCookingTechnique(mainCkTech);
                PrintUtils.printWithDelay("You have chosen " + mainCkTech.getName() + " as the cooking technique.");
                return true;
            } else {
                PrintUtils.printWithDelay("The only techniques you know how to do are grilling and baking.");
            }
        }
    }
}

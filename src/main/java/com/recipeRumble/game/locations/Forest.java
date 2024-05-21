package com.recipeRumble.game.locations;

import com.recipeRumble.game.foods.ingredients.CucuBerry;
import com.recipeRumble.game.foods.ingredients.FoodItem;
import com.recipeRumble.game.foods.ingredients.GreenAnise;
import com.recipeRumble.game.foods.ingredients.PurpleMaitake;

import java.util.Random;

public class Forest extends Location {
    FoodItem purpleMaitake = null;
    FoodItem greenAnise = null;
    FoodItem cucuBerry = null;
    Random random = new Random();
    int searchScore = 0;
    boolean hasSearched = false;

    public Forest() {
        super("Forest", "A dense forest with vibrant hues and fragrant scents mingling in the air.");
    }

    public void searchForest() {
        if (!hasSearched) {
            this.searchScore = random.nextInt(101); // 0 - 100 dice roll
            if (searchScore >= PurpleMaitake.searchScoreReq) {
                this.purpleMaitake = new PurpleMaitake();
                System.out.println("You found a Purple Maitake!");
            }
            if (searchScore >= GreenAnise.searchScoreReq) {
                this.greenAnise = new GreenAnise();
                System.out.println("You found a Green Anise!");

            }
            if (searchScore >= CucuBerry.searchScoreReq) {
                this.cucuBerry = new CucuBerry();
                System.out.println("You found a Cucu Berry!");
            } else {
                System.out.println("You didn't find any ingredients.");
            }
            this.hasSearched = true;
        } else {
            System.out.println("You've already searched the forest.");
        }

    }

    public void resetHasSearched() {
        this.hasSearched = false;
    }
}
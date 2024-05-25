package com.recipeRumble.game;

import com.recipeRumble.game.foods.ingredients.CucuBerry;
import com.recipeRumble.game.foods.ingredients.FoodItem;

public class Player {
    String name;
    Inventory inventory;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
        // ADDING INVENTORY ITEMS FOR TESTING
        this.inventory.addItem(new CucuBerry(),1);
    }   
}
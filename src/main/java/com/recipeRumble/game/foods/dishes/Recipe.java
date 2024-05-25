package com.recipeRumble.game.foods.dishes;

import com.recipeRumble.game.foods.ingredients.FoodItem;
import com.recipeRumble.game.foods.techniques.CookingTechnique;

public class Recipe {
    private FoodItem mainIngredient;
    private FoodItem secondaryIngredient;
    private FoodItem baseIngredient;
    private FoodItem mainSeasoning;
    private CookingTechnique cookingTechnique;

    public Recipe() {

    }

    // GETTERS AND SETTERS
    // GET/SET MAIN
    public FoodItem getMainIngredient() {
        return this.mainIngredient;
    }

    public void setMainIngredient(FoodItem mainIngredient) {
        this.mainIngredient = mainIngredient;
    }
    // GET/SET SECONDARY
    public FoodItem getSecondaryIngredient() {
        return this.secondaryIngredient;
    }

    public void getSecondaryIngredient(FoodItem secondaryIngredient) {
        this.secondaryIngredient = secondaryIngredient;
    }
    // GET/SET BASE
    public FoodItem getBaseIngredient() {
        return this.baseIngredient;
    }

    public void setBaseIngredient(FoodItem baseIngredient) {
        this.baseIngredient = baseIngredient;
    }
    // GET/SET SEASONING
    public FoodItem getMainSeasoning() {
        return this.mainSeasoning;
    }

    public void setMainSeasoning(FoodItem mainSeasoning) {
        this.mainSeasoning = mainSeasoning;
    }
    // GET/SET TECHNIQUE
    public CookingTechnique getCookingTechnique() {
        return cookingTechnique;
    }

    public void setCookingTechnique(CookingTechnique cookingTechnique) {
        this.cookingTechnique = cookingTechnique;
    }
}

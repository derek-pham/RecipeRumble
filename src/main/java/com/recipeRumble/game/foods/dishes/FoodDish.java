package com.recipeRumble.game.foods.dishes;

import com.recipeRumble.game.foods.ingredients.FoodItem;
import com.recipeRumble.game.foods.techniques.CookingTechnique;

import java.util.Objects;

public class FoodDish {
    private FoodItem mainIngredient;
    private FoodItem secondaryIngredient;
    private FoodItem baseIngredient;
    private FoodItem mainSeasoning;
    private CookingTechnique cookingTechnique;
    private String name;

    public FoodDish(Recipe recipe) {
        this.mainIngredient = recipe.getMainIngredient();
        this.secondaryIngredient = recipe.getSecondaryIngredient();
        this.baseIngredient = recipe.getBaseIngredient();
        this.mainSeasoning = recipe.getMainSeasoning();
        this.cookingTechnique = recipe.getCookingTechnique();
        this.name = buildDishName(recipe);
    }

    public String buildDishName(Recipe recipe) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(recipe.getCookingTechnique().getAdjective());
        stringBuilder.append(" ");
        stringBuilder.append(recipe.getMainSeasoning().getAdjective());
        stringBuilder.append(" ");
        stringBuilder.append(recipe.getMainIngredient().getName());

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FoodDish foodDish = (FoodDish) obj;
        return Objects.equals(name, foodDish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainIngredient, secondaryIngredient, baseIngredient, mainSeasoning, cookingTechnique, name);
    }

    public FoodItem getMainIngredient() {
        return mainIngredient;
    }

    public CookingTechnique getCookingTechnique() {
        return cookingTechnique;
    }

    public FoodItem getMainSeasoning() {
        return mainSeasoning;
    }
}

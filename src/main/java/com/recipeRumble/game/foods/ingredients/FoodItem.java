package com.recipeRumble.game.foods.ingredients;

public class FoodItem {
    private String name;
    private String description;

    public FoodItem(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    public String getAdjective() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodItem foodItem = (FoodItem) o;
        return name.equalsIgnoreCase(foodItem.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}

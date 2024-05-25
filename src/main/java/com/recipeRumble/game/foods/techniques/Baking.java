package com.recipeRumble.game.foods.techniques;

public class Baking extends CookingTechnique{

    public Baking() {
        super("Baking", "Baked");
    }

    public static void main(String[] args) {
        Baking baking = new Baking();
        System.out.println(baking.getName().toLowerCase());
    }
}

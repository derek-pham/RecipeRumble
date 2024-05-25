package com.recipeRumble.game;

import com.recipeRumble.game.foods.dishes.FoodDish;
import com.recipeRumble.game.foods.dishes.Recipe;
import com.recipeRumble.game.foods.ingredients.*;
import com.recipeRumble.game.foods.techniques.Baking;
import com.recipeRumble.game.foods.techniques.Grilling;
import com.recipeRumble.game.utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LordFairfax {
    public FoodDish[] lordsfavorites = new FoodDish[4];
    public Map<Object, String> critiques = new HashMap<>();

    public LordFairfax() {
        // BAKED SWEET CUCU BERRY
        Recipe faveDish1 = new Recipe();
        faveDish1.setMainIngredient(new CucuBerry());
        faveDish1.setMainSeasoning(new Sugar());
        faveDish1.setCookingTechnique(new Baking());
        lordsfavorites[0] = new FoodDish(faveDish1);

        // GRILLED SALTY PURPLE MAITAKE
        Recipe faveDish2 = new Recipe();
        faveDish2.setMainIngredient(new PurpleMaitake());
        faveDish2.setMainSeasoning(new SeaSalt());
        faveDish2.setCookingTechnique(new Grilling());
        lordsfavorites[1] = new FoodDish(faveDish2);

        // GRILLED SWEET WILD CAROTA
        Recipe faveDish3 = new Recipe();
        faveDish3.setMainIngredient(new WildCarota());
        faveDish3.setMainSeasoning(new Sugar());
        faveDish3.setCookingTechnique(new Grilling());
        lordsfavorites[2] = new FoodDish(faveDish3);

        // BAKED SALTY CHERRY BEET
        Recipe faveDish4 = new Recipe();
        faveDish4.setMainIngredient(new CherryBeet());
        faveDish4.setMainSeasoning(new SeaSalt());
        faveDish4.setCookingTechnique(new Baking());
        lordsfavorites[3] = new FoodDish(faveDish4);

    }

    public boolean tasteTest(FoodDish chosenDish) {
        critiques.clear(); // Clear previous critiques
        for (FoodDish faveDish : lordsfavorites) {
            if (faveDish != null && faveDish.equals(chosenDish)) {
                return true;  // Exit the loop early if a match is found
            }
        }
        makeCritiques(chosenDish);
        return false;
    }

    public void makeCritiques(FoodDish chosenDish) {
        for (FoodDish faveDish : lordsfavorites) {
            if (!faveDish.getMainIngredient().equals(chosenDish.getMainIngredient())) {
                if (Objects.equals(critiques.get(chosenDish.getMainIngredient()), "liked the ")) return;
                critiques.put(chosenDish.getMainIngredient(),"is not a fan of ");
//                return;
            } else if (faveDish.getMainIngredient().equals(chosenDish.getMainIngredient()) &&
            !faveDish.getCookingTechnique().equals(chosenDish.getCookingTechnique())){
                if (Objects.equals(critiques.get(chosenDish.getCookingTechnique()), "enjoyed it being ")) return;
                critiques.put(chosenDish.getMainIngredient(),"liked the ");
                critiques.put(chosenDish.getCookingTechnique(),"did not like that ");
//                return;
            } else if (faveDish.getMainIngredient().equals(chosenDish.getMainIngredient()) && faveDish.getCookingTechnique().equals(chosenDish.getCookingTechnique()) && !faveDish.getMainSeasoning().equals(chosenDish.getMainSeasoning())
            ) {
                critiques.put(chosenDish.getMainIngredient(),"liked the ");
                critiques.put(chosenDish.getCookingTechnique(),"enjoyed it being ");
                critiques.put(chosenDish.getMainSeasoning(),"was not keen on it being ");
//                return;
            }
        }
    }

    public static void main(String[] args) {
        LordFairfax lordFairfax = new LordFairfax();

        // FAIL ON MAIN INGREDIENT 1ST CHECK
        Recipe testDish3 = new Recipe();
        testDish3.setMainIngredient(new PyrusBulb());
        testDish3.setMainSeasoning(new SeaSalt());
        testDish3.setCookingTechnique(new Grilling());
        FoodDish foodDish3 = new FoodDish(testDish3);

        // FAIL ON CKTECH 2ND CHECK
        Recipe testDish2 = new Recipe();
        testDish2.setMainIngredient(new CherryBeet());
        testDish2.setMainSeasoning(new SeaSalt());
        testDish2.setCookingTechnique(new Grilling());
        FoodDish foodDish2 = new FoodDish(testDish2);

        // FAIL ON CKTECH 3RD CHECK
        Recipe testDish4 = new Recipe();
        testDish4.setMainIngredient(new PurpleMaitake());
        testDish4.setMainSeasoning(new Sugar());
        testDish4.setCookingTechnique(new Grilling());
        FoodDish foodDish4 = new FoodDish(testDish4);

        lordFairfax.tasteTest(foodDish3);
//        System.out.println(lordFairfax.critiques);
        System.out.println("Turns out that Fairfax "+lordFairfax.critiques.get(testDish3.getMainIngredient()) +testDish3.getMainIngredient().getName().toLowerCase()+".");
        System.out.println();

        lordFairfax.tasteTest(foodDish2);
//        System.out.println(lordFairfax.critiques);
        System.out.println("Fairfax seemed to have "+lordFairfax.critiques.get(foodDish2.getMainIngredient()) +foodDish2.getMainIngredient().getName().toLowerCase()+" but "+lordFairfax.critiques.get(foodDish2.getCookingTechnique())+"it was "+foodDish2.getCookingTechnique().getAdjective().toLowerCase()+".");
        System.out.println();

        lordFairfax.tasteTest(foodDish4);
//        System.out.println(lordFairfax.critiques);
        System.out.println("Fairfax seemed to have "+lordFairfax.critiques.get(foodDish4.getMainIngredient()) +foodDish4.getMainIngredient().getName().toLowerCase()+" and "+lordFairfax.critiques.get(foodDish4.getCookingTechnique())+foodDish4.getCookingTechnique().getAdjective().toLowerCase()+". However Fairfax "+lordFairfax.critiques.get(foodDish4.getMainSeasoning())+foodDish4.getMainSeasoning().getAdjective().toLowerCase()+".");
    }
}

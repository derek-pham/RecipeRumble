package com.recipeRumble.game.locations;

import com.recipeRumble.game.Inventory;
import com.recipeRumble.game.LordFairfax;
import com.recipeRumble.game.foods.dishes.FoodDish;
import com.recipeRumble.game.foods.ingredients.FoodItem;
import com.recipeRumble.game.utils.PrintUtils;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class DiningHall extends Location{
    public static int currentStage = 0;
    public String[] stages = {"breakfast", "lunch", "dinner"};
    Map<Integer, FoodDish> menu;
    Scanner scanner = new Scanner(System.in);
    LordFairfax lordFairfax = new LordFairfax();

    public DiningHall() {
        super("Dining Hall", "A grand dining hall with high vaulted ceilings, ornate chandeliers, and long tables.");
        this.menu = new HashMap<>();
    }

    @Override
    public void search() {

    }

    @Override
    public FoodItem takeItem(String foodName) {
        return null;
    }

    @Override
    public int serve(Inventory inventory) {
        PrintUtils.printWithDelay("Alright, it's currently time to serve "+stages[currentStage]+".");
        if (currentStage==0) {
            PrintUtils.printWithDelay("You may only serve one dish.");
        } else if (currentStage==1) {
            PrintUtils.printWithDelay("You may serve up to two dishes.");
        } else if (currentStage==2) {
            PrintUtils.printWithDelay("You may now serve up to three dishes here.");
        }

        FoodDish[] chosenDishes = chooseDishes(currentStage, inventory);
        if (chosenDishes.length ==6) {
            PrintUtils.printWithDelay("You need at least one dish to serve. You can make some in the kitchen.");
            return 0;
        }
        if (areAllValuesNull(chosenDishes)) {
            PrintUtils.printWithDelay("You decided to reconsider before serving.");
            return 0;
        }
        boolean isLordsFave;

        for (FoodDish chosenDish : chosenDishes) {
            if (chosenDish != null) {
                dialoguePreEval(chosenDish);
                isLordsFave = lordFairfax.tasteTest(chosenDish);
                if (isLordsFave) {
                    return dialoguePostEval(isLordsFave, chosenDish);
                } else {
                    dialoguePostEval(isLordsFave, chosenDish);
                }
            }
        }
        if (currentStage==0) {
            PrintUtils.printWithDelay("I will see you soon again for lunch. Now go.");
            PrintUtils.delay(500);
            PrintUtils.printWithDelay("*You make your way back to the kitchen.");
            PrintUtils.delay(500);
            PrintUtils.printWithDelay("*For lunch you can serve two dishes so think carefully.");
        } else if (currentStage==1) {
            PrintUtils.printWithDelay("*Unimpressed, Fairfax gestures you to leave in wait of one last attempt at dinner.");
            PrintUtils.delay(500);
            PrintUtils.printWithDelay("*You make your way back to the kitchen.");
            PrintUtils.delay(500);
            PrintUtils.printWithDelay("*For dinner you can serve three final dishes. Better make it count.");
        }
        currentStage ++;
        return 1;
    }

    private FoodDish[] chooseDishes(int currentStage, Inventory inventory) {
        resetMenu(inventory);
        if (menu.isEmpty()) {
            return new FoodDish[6]; // Code '6' to start
        }
        PrintUtils.printWithDelay("Here are the dishes you can serve. Please choose by inputting their number or input 99 to end early.");
        for (Integer menuNum : menu.keySet()) {
            PrintUtils.printWithDelay("[" + menuNum + "] " + menu.get(menuNum));
        }
        FoodDish chosenDish1;
        FoodDish chosenDish2 = null;
        FoodDish chosenDish3 = null;

        chosenDish1 = handleMenuInput(inventory);
        // End early if player decides chosenDish1 to be null
        if (chosenDish1 == null) {
            return new FoodDish[]{null};
        }

        if (currentStage >= 1) {
            resetMenu(inventory);
            if (menu.isEmpty()) {
                boolean wantsToContinue = handleNoMoreDishes();
                if (wantsToContinue) {
                    return new FoodDish[]{chosenDish1}; // Return only chosenDish1
                } else {
                    inventory.addItem(chosenDish1,1);
                    return new FoodDish[]{null};
                }
            }
            PrintUtils.printWithDelay("Now choose a second dish to serve. Please choose by inputting their number or input 99 to end early..");
            for (Integer menuNum : menu.keySet()) {
                PrintUtils.printWithDelay("[" + menuNum + "] " + menu.get(menuNum));
            }
            chosenDish2 = handleMenuInput(inventory);
            // End early if player decides chosenDish2 to be null
            if (chosenDish2 == null) {
                return new FoodDish[]{chosenDish1}; // Return only chosenDish1
            }
        }

        if (currentStage >= 2) {
            resetMenu(inventory);
            if (menu.isEmpty()) {
                boolean wantsToContinue = handleNoMoreDishes();
                if (wantsToContinue) {
                    return new FoodDish[]{chosenDish1, chosenDish2}; // Return only chosenDish1 and chosenDish2
                } else {
                    inventory.addItem(chosenDish1,1);
                    inventory.addItem(chosenDish2,1);
                    return new FoodDish[]{null};
                }
            }
            PrintUtils.printWithDelay("Now choose the third final dish to serve. Please choose by inputting their number or input 99 to end early..");
            for (Integer menuNum : menu.keySet()) {
                PrintUtils.printWithDelay("[" + menuNum + "] " + menu.get(menuNum));
            }
            chosenDish3 = handleMenuInput(inventory);
            // End early if player decides chosenDish3 to be null
            if (chosenDish3 == null) {
                return new FoodDish[]{chosenDish1,chosenDish2}; // Return only chosenDish1 and chosenDish2
            }
        }

        PrintUtils.printWithDelay("Now let's see what Lord Fairfax has to say..");
        PrintUtils.delay(1000);
        return new FoodDish[]{chosenDish1, chosenDish2, chosenDish3};
    }

    private void resetMenu(Inventory inventory) {
        Integer menuNumber = 0;
        menu.clear();
        for (Object foodItem: inventory.getFoodDishes()) {
            if (foodItem instanceof FoodDish) {
                menu.put(menuNumber, (FoodDish) foodItem);
                menuNumber ++;
            }
        }
    }

    private FoodDish handleMenuInput(Inventory inventory) {
        while (true) {
            try {
                Integer input = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (input == 99) {
                    return null;
                }
                if (!menu.containsKey(input)) {
                    throw new InputMismatchException();
                }
                inventory.removeFoodDish(menu.get(input));
                return menu.get(input);

            } catch (InputMismatchException e) {
                scanner.nextLine();
                PrintUtils.printWithDelay("Invalid input. Please make sure that you've put a number for the listed dishes.");
            }
        }
    }

    private void dialoguePreEval(FoodDish chosenDish) {
        PrintUtils.printWithDelay("Fairfax takes a bite of the "+chosenDish.toString()+" and ...");
        PrintUtils.delay(1000);
    }

    private int dialoguePostEval(boolean result, FoodDish chosenDish) {
        if (result) {
            dialogueWin(chosenDish);
            return 2;
        } else {
            dialogueFail(chosenDish);
            return 1;
        }
    }

    private void dialogueWin(FoodDish chosenDish) {
        PrintUtils.printWithDelay("Ah...  ");
        PrintUtils.delay(2000);
        PrintUtils.printWithDelay("An adequate result from a surprisingly competent young cook. Many areas to refine but I see potential here...");
        PrintUtils.delay(2000);
        PrintUtils.printWithDelay("Very well. You have passed my examination and now may leave back to your residence. I look forward to seeing your culinary endeavours develop moving forward..");
        PrintUtils.delay(2000);
        PrintUtils.printWithDelay("*Congratulations! You have passed the test and impressed Lord Reginald Fairfax with your '"+chosenDish.toString()+"'.");
        PrintUtils.delay(2000);
        PrintUtils.printWithDelay("*You make your way back to your home. After a long restless day fulfilled by a successful impression, you head to bed and slowly drift asleep.");
        PrintUtils.delay(5000);
        PrintUtils.printWithDelay("Thank you so much for playing! yaddyadydayday");
    }

    private void dialogueFail(FoodDish chosenDish) {
        if (lordFairfax.critiques.keySet().size() == 1) {
            PrintUtils.printWithDelay("Turns out that Fairfax " +
                    lordFairfax.critiques.get(chosenDish.getMainIngredient()) +
                    chosenDish.getMainIngredient().getName().toLowerCase() +
                    ".");
        } else if (lordFairfax.critiques.keySet().size() == 2) {
            PrintUtils.printWithDelay("Fairfax seemed to have " +
                    lordFairfax.critiques.get(chosenDish.getMainIngredient()) +
                    chosenDish.getMainIngredient().getName().toLowerCase() +
                    " but " +
                    lordFairfax.critiques.get(chosenDish.getCookingTechnique()) +
                    "it was " +
                    chosenDish.getCookingTechnique().getAdjective().toLowerCase() +
                    ".");
        } else if (lordFairfax.critiques.keySet().size() == 3) {
            PrintUtils.printWithDelay("Fairfax seemed to have " +
                    lordFairfax.critiques.get(chosenDish.getMainIngredient()) +
                    chosenDish.getMainIngredient().getName().toLowerCase() +
                    " and " +
                    lordFairfax.critiques.get(chosenDish.getCookingTechnique()) +
                    chosenDish.getCookingTechnique().getAdjective().toLowerCase() +
                    ". However Fairfax " +
                    lordFairfax.critiques.get(chosenDish.getMainSeasoning()) +
                    chosenDish.getMainSeasoning().getAdjective().toLowerCase() +
                    ".");
        }
    }

    public static int getCurrentStage() {
        return currentStage;
    }

    public boolean areAllValuesNull(FoodDish[] array) {
        for (FoodDish item : array) {
            if (item != null) {
                return false; // Found a non-null element
            }
        }
        return true; // All elements are null
    }

    public boolean handleNoMoreDishes() {
        PrintUtils.printWithDelay("You've run out of dishes to serve. Would you still like to proceed with what you have? Please answer 'yes' or 'no'.");
        while (true) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("yes")) {
                    return true;
                } else if (input.equals("no")) {
                    return false;
                } else {
                    PrintUtils.printWithDelay("Invalid answer. Please answer 'yes' or 'no'.");
                }
            }
        }
    }

}
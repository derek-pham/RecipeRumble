package com.recipeRumble.game;

import java.util.Scanner;

import com.recipeRumble.game.foods.ingredients.FoodItem;
import com.recipeRumble.game.locations.DiningHall;
import com.recipeRumble.game.locations.Forest;
import com.recipeRumble.game.locations.Location;
import com.recipeRumble.game.locations.LocationMap;
import com.recipeRumble.game.utils.PrintUtils;

public class Game {
    private boolean isRunning;
    private Player player;
    private Location currentLocation;
    private LocationMap locationMap;

    public Game() {
        this.player = new Player("Player1");
        this.locationMap = new LocationMap();
        this.currentLocation = locationMap.getLocation("kitchen");
    }

    public void start() {
        isRunning = true;
        Scanner scanner = new Scanner(System.in); // Create a Scanner object to read user input
//        introDialogue();
        PrintUtils.printWithDelay("What will you do now?");
        PrintUtils.printWithDelay("Commands: -look -move to [forest, mountain, dining hall, kitchen] -search -take xxx -check inventory -cook -serve -quit");
        // Main game loop
        while (this.isRunning) {
            System.out.print("> "); // Prompt for user input
            String input = scanner.nextLine(); // Read user input
            handleCommand(input); // Process the user input
        }
        scanner.close();
    }

    private void handleCommand(String command) {
        if (command.equals("quit")) {
            this.quit();
        } else if (command.equals("look")) {
            this.look();
        } else if (command.startsWith("move to ")) {
            this.moveTo(command.substring(8));
        } else if (command.equals("search")) {
            this.currentLocation.search();
        } else if (command.startsWith("take ")) {
            FoodItem ingredient = this.currentLocation.takeItem(command.substring(5));
            if (ingredient != null) {
                player.inventory.addItem(ingredient, 1);
            } else {
                System.out.println("There is nothing to take here.");
            }
        } else if (command.equals("cook")) {
            currentLocation.cook(player.inventory);
        } else if (command.equals("serve")) {
            int gameResult = currentLocation.serve(player.inventory);
            handleGameResult(gameResult);
            if (DiningHall.getCurrentStage()>=3) {
                endGameBadEnding();
            }
        } else if (command.equals("check inventory")) {
            player.inventory.printInventory();
        } else {
            System.out.println("Unknown command.");
        }
    }

    // Method to look around the current location
    private void look() {
        PrintUtils.printWithDelay(
                "You are at the " + currentLocation.getName() + ". " + currentLocation.getDescription());
    }

    // Method to quit the game
    private void quit() {
        this.isRunning = false; // Set the game to not running state
        PrintUtils.printWithDelay("Thank you for playing Recipe Rumble!");
        PrintUtils.delay(1000);
        PrintUtils.printWithDelay("We hope to see you again some time!");

    }

    private void moveTo(String destination) {
        if (this.locationMap.containsLocation(destination)) {
            this.currentLocation = locationMap.getLocation(destination);
            PrintUtils.printWithDelay("You are now at the " + currentLocation.getName());
        } else {
            PrintUtils.printWithDelay("That location doesn't exist. The locations currently accessible are:\n"
                    + locationMap.listLocations());
        }
    }

    private void introDialogue() {
        PrintUtils.printWithDelay("....");
        PrintUtils.delay(1000);
        PrintUtils.printWithDelay("....");
        PrintUtils.delay(1000);
        PrintUtils.printWithDelay("Ah, so you are the one tasked with preparing my meals today.");
        PrintUtils.delay(800);
        PrintUtils.printWithDelay("I must say, your appearance does not inspire confidence.");
        PrintUtils.delay(800);
        PrintUtils.printWithDelay("Are you certain you're up to this challenge?");
        PrintUtils.delay(800);
        PrintUtils.printWithDelay("I must say, your appearance does not inspire confidence.");
        PrintUtils.delay(800);
        PrintUtils.printWithDelay("You have three opportunities to prove your worth—breakfast, lunch, and dinner. Each meal must be impeccable.");
        PrintUtils.delay(800);
        PrintUtils.printWithDelay("Fail, and you will not only lose this opportunity but any future prospects in this field.");
        PrintUtils.delay(1000);
        PrintUtils.printWithDelay("Let us begin with breakfast. I expect nothing short of excellence. Do not disappoint me.");
        PrintUtils.delay(1000);
        PrintUtils.printWithDelay("Now, off you go, shoo. Get to work.");
        PrintUtils.delay(3000);
        PrintUtils.printWithDelay("You are a hired chef for Lord Reginald Fairfax and you only have three chances to make a dish that he likes and so you make your way to the kitchen to decide what to do next.");
        PrintUtils.delay(1000);
    }

    public void handleGameResult(int gameResult) {
        if (gameResult == 2) {
            quit();
        } else if (gameResult == 1){
            locationMap.getLocation("forest").resetHasSearched();
            locationMap.getLocation("mountain").resetHasSearched();
            PrintUtils.printWithDelay("*You can search the forest and mountain again.");
            this.moveTo("kitchen");
        }
    }

    public void endGameBadEnding() {
        PrintUtils.printWithDelay("Hm....");
        PrintUtils.delay(1000);
        PrintUtils.printWithDelay("I think I have seen enough.");
        PrintUtils.delay(2000);
        PrintUtils.printWithDelay("I have given you three chances... and unfortunately for you none of the dishes you have presented are near adequate enough to meet my standards.");
        PrintUtils.delay(2000);
        PrintUtils.printWithDelay("You may leave now.");
        PrintUtils.delay(2000);
        PrintUtils.printWithDelay("*Despite your best efforts you couldn't figure what Fairfax liked. You make your way back home and collect yourself...");
        PrintUtils.delay(2000);
        PrintUtils.printWithDelay("*You remember seeing a painting of a Purple Maitake in the dining hall... or was it a Cherry Beet?..");
        PrintUtils.delay(1000);
        PrintUtils.printWithDelay("The end..");
        PrintUtils.delay(4000);
        quit();
    }
}
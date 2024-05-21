package com.recipeRumble.game;

import java.util.Scanner;

import com.recipeRumble.game.locations.Forest;
import com.recipeRumble.game.locations.Location;
import com.recipeRumble.game.locations.LocationMap;

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
        printWithDelay("Welcome to Recipe Rumble: A Text Adventure!");
        printWithDelay("What would you like to do?");
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
        } else if (command.equals("search") && this.currentLocation == locationMap.getLocation("forest")) {
            if (this.currentLocation instanceof Forest) {
                Forest forest = (Forest) this.currentLocation;
                forest.searchForest();
            } else {
                System.out.println("You can't search here.");
            }
        } else {
            System.out.println("Unknown command.");
        }
    }

    // Method to look around the current location
    private void look() {
        printWithDelay("You are at the " + currentLocation.getName() + ". " + currentLocation.getDescription());
    }

    // Method to quit the game
    private void quit() {
        this.isRunning = false; // Set the game to not running state
        printWithDelay("Thank you for playing Recipe Rumble!");
    }

    private void moveTo(String destination) {
        if (this.locationMap.containsLocation(destination)) {
            this.currentLocation = locationMap.getLocation(destination);
            printWithDelay("You are now at the " + currentLocation.getName());
            delay(200);
            printWithDelay(currentLocation.getDescription());
        } else {
            printWithDelay("That location doesn't exist. The locations currently accessible are:\n"
                    + locationMap.listLocations());
        }
    }

    private void printWithDelay(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c); // Print each character
            delay(10);
        }
        delay(400);
        System.out.println(); // Move to the next line after the text is printed
    }

    private void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
    }
}
package com.recipeRumble.game;

import java.util.Scanner;

public class Game {
    private boolean isRunning;
    private Player player;
    private Location currentLocation;

    public Game() {
        this.player = new Player("Player1");
        this.currentLocation = new Location("Kitchen", "A place where delicious meals are made.");
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
    }

    private void handleCommand(String command) {
        if (command.equals("quit")) {
            this.quit();
        } else if (command.equals("look")) {
            this.look();
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

    private void printWithDelay(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c); // Print each character
            try {
                Thread.sleep(10); // Delay for 50 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle the interruption
            }
        }
        System.out.println(); // Move to the next line after the text is printed
    }

}
package com.recipeRumble.game.utils;

public class PrintUtils {

    public static void printWithDelay(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c); // Print each character
            delay(10);
        }
        delay(400);
        System.out.println(); // Move to the next line after the text is printed
    }

    public static void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
    }
}

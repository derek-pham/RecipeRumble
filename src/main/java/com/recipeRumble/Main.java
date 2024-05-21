package com.recipeRumble;

import com.recipeRumble.game.Game;

/**
 * Main
 */
public class Main {
    Game game;

    public Main() {
        this.game = new Game();
    }

    public static void main(String[] args) {
        Game mainGame = new Game();
        mainGame.start();
    }
}
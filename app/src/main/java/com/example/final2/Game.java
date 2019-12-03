package com.example.final2;

// Every game is a Game Object that has several operations.
public class Game {
    private int gameIndex;
    private String hint;
    public Game(int index, String setHint) {
        gameIndex = index;
        hint = setHint;
    }
    public Game() { }
    public String getHint() {
        return hint;
    }
    public void runGame() {
        return;
    }
    public int getGameIndex() {
        return gameIndex;
    }
}

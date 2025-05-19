package com.example.task3;

public class Game {
    private int playerTurn;
    private Bed bed;

    public Game() {
        playerTurn = 1;
        bed = new Bed(6, 6);
    }

    public Bed getBed() {return bed;}

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void endTurn()
    {
        if(playerTurn == 2)
            playerTurn = 1;
        else
            playerTurn = 2;
    }
}

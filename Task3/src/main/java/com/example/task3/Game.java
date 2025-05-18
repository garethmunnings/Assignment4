package com.example.task3;

public class Game {
    private String playerTurn;
    private Bed bed;
    public Game() {
        playerTurn = "player 1";
        bed = new Bed(6, 6);
    }

    public Bed getBed() {return bed;}

    public String getPlayerTurn() {
        return playerTurn;
    }

    public void endTurn()
    {
        if(playerTurn.equals("player 2"))
            playerTurn = "player 1";
        else
            playerTurn = "player 2";
    }
}

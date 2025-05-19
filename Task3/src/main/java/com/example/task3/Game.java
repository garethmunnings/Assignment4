package com.example.task3;

public class Game {
    private int playerTurn;
    private Bed bed;
    private Player player1;
    private Player player2;

    public Game() {
        playerTurn = 1;
        bed = new Bed(6, 6);
        player1 = new Player(1);
        player2 = new Player(2);
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

    public Player getPlayer1(){return player1;}
    public Player getPlayer2(){return player2;}

    public Player getCurrentPlayer(){
        if(playerTurn == 1) return player1;
        else return player2;
    }
}

package com.example.task3;

public class Player {
    private Pool pool;
    private int playerNum;

    public Player(int playerNum) {

        this.playerNum = playerNum;
        pool = new Pool(playerNum);
    }

    public int getNum(){return playerNum;}
    public Pool getPool(){return pool;}
}

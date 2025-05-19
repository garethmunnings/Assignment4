package com.example.task3;

public class Tile {
    private Feline feline;
    private int row;
    private int col;

    public Feline getFeline() { return feline; }

    public void setFeline(Feline feline) { this.feline = feline; }

    public boolean isEmpty() { return feline == null; }

    public void setEmpty(){
        feline = null;
    }

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
}

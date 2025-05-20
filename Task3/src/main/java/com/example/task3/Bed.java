package com.example.task3;

import java.util.ArrayList;

public class Bed {
    private final int rows;
    private final int cols;
    private final Tile[][] grid;

    public Bed(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Tile[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                grid[i][j] = new Tile(i,j);
    }

    public Tile getTile(int row, int col) {
        return grid[row][col];
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public Tile[][] getGrid() { return grid; }

    public ArrayList<Feline> updateTile(int row, int col, Tile tile, boolean chainReaction) {
        grid[row][col] = tile;
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(!chainReaction)
            felinesBoopedOffBed = boop(row,col);
        return felinesBoopedOffBed;
    }

    public ArrayList<Feline> boop(int row, int col) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        felinesBoopedOffBed.addAll(boopDown(row,col));
        felinesBoopedOffBed.addAll(boopUp(row,col));
        felinesBoopedOffBed.addAll(boopLeft(row,col));
        felinesBoopedOffBed.addAll(boopRight(row,col));

        felinesBoopedOffBed.addAll(boopUpLeft(row,col));
        felinesBoopedOffBed.addAll(boopUpRight(row,col));
        felinesBoopedOffBed.addAll(boopDownLeft(row,col));
        felinesBoopedOffBed.addAll(boopDownRight(row,col));

        return felinesBoopedOffBed;
    }

    private ArrayList<Feline> boopDown(int row, int col) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(row + 1 < rows) { //is one space ahead on the board

            if (grid[row + 1][col].getFeline() != null) { //is there a feline one space ahead

                Feline feline = grid[row + 1][col].getFeline();
                if (row + 2 < rows) { //is two spaces ahead on the baord

                    if (grid[row + 2][col].getFeline() != null) { //is there a feline two spaces ahead
                        //do nothing
                    } else {
                        Tile t = new Tile(row + 2, col);
                        t.setFeline(feline);
                        updateTile(row + 2, col, t, true);
                        grid[row + 1][col].setFeline(null);
                    }
                } else {
                    //send cat back to pool
                    felinesBoopedOffBed.add(grid[row + 1][col].getFeline());
                    grid[row + 1][col].setFeline(null);
                }
            }
        }
        return felinesBoopedOffBed;
    }
    private ArrayList<Feline> boopUp(int row, int col) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(row - 1 >= 0) { //is one space behind on the board

            if (grid[row - 1][col].getFeline() != null) { //is there a feline one space behind

                Feline feline = grid[row - 1][col].getFeline();
                if (row - 2 >= 0) { //is two spaces ahead on the board

                    if (grid[row - 2][col].getFeline() != null) { //is there a feline two spaces beind
                        //do nothing
                    } else {
                        Tile t = new Tile(row - 2, col);
                        t.setFeline(feline);
                        updateTile(row - 2, col, t, true);
                        grid[row - 1][col].setFeline(null);
                    }
                } else {
                    //just set the space behind to null (kick the cat off the board)
                    felinesBoopedOffBed.add(grid[row-1][col].getFeline());
                    grid[row - 1][col].setFeline(null);
                }

            }
        }
        return felinesBoopedOffBed;
    }
    private ArrayList<Feline> boopRight(int row, int col) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(col + 1 < cols) { //is one space ahead on the board

            if (grid[row][col + 1].getFeline() != null) { //is there a feline one space ahead

                Feline feline = grid[row][col + 1].getFeline();
                if (col + 2 < cols) { //is two spaces ahead on the baord

                    if (grid[row][col + 2].getFeline() != null) { //is there a feline two spaces ahead
                        //do nothing
                    } else {
                        Tile t = new Tile(row, col + 2);
                        t.setFeline(feline);
                        updateTile(row, col + 2, t, true);
                        grid[row][col + 1].setFeline(null);
                    }
                } else {
                    //just set the space ahead to null (kick the cat off the board)
                    felinesBoopedOffBed.add(grid[row][col + 1].getFeline());
                    grid[row][col + 1].setFeline(null);
                }
            }
        }
        return felinesBoopedOffBed;
    }
    private ArrayList<Feline> boopLeft(int row, int col) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(col - 1 >= 0) { //is one space behind on the board

            if (grid[row][col - 1].getFeline() != null) { //is there a feline one space behind

                Feline feline = grid[row][col - 1].getFeline();
                if (col - 2 >= 0) { //is two spaces ahead on the board

                    if(grid[row][col - 2].getFeline() != null) { //is there a feline two spaces beind
                        //do nothing
                    }
                    else{
                        Tile t = new Tile(row, col -2);
                        t.setFeline(feline);
                        updateTile(row, col - 2, t, true);
                        grid[row][col - 1].setFeline(null);
                    }
                }
                else{
                    //just set the space behind to null (kick the cat off the board)
                    felinesBoopedOffBed.add(grid[row][col - 1].getFeline());
                    grid[row][col - 1].setFeline(null);
                }

            }
        }
        return felinesBoopedOffBed;
    }

    private ArrayList<Feline> boopUpLeft(int row, int col) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(row - 1 >= 0 && col - 1 >= 0) { //is one space behind on the board

            if (grid[row - 1][col - 1].getFeline() != null) { //is there a feline one space behind

                Feline feline = grid[row - 1][col - 1].getFeline();
                if (row - 2 >= 0 && col - 2 >= 0) { //is two spaces ahead on the board

                    if (grid[row - 2][col - 2].getFeline() != null) { //is there a feline two spaces beind
                        //do nothing
                    } else {
                        Tile t = new Tile(row-2, col -2);
                        t.setFeline(feline);
                        updateTile(row - 2, col - 2, t, true);
                        grid[row - 1][col - 1].setFeline(null);
                    }
                } else {
                    //just set the space behind to null (kick the cat off the board)
                    felinesBoopedOffBed.add(grid[row - 1][col - 1].getFeline());
                    grid[row - 1][col - 1].setFeline(null);
                }

            }
        }
        return felinesBoopedOffBed;
    }
    private ArrayList<Feline> boopUpRight(int row, int col) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(row - 1 >= 0 && col + 1 < cols) { //is one space behind on the board

            if (grid[row - 1][col + 1].getFeline() != null) { //is there a feline one space behind

                Feline feline = grid[row - 1][col + 1].getFeline();
                if (row - 2 >= 0 && col + 2 < cols) { //is two spaces ahead on the board

                    if (grid[row - 2][col + 2].getFeline() != null) { //is there a feline two spaces beind
                        //do nothing
                    } else {
                        Tile t = new Tile(row - 2, col + 2);
                        t.setFeline(feline);
                        updateTile(row - 2, col + 2, t, true);
                        grid[row - 1][col + 1].setFeline(null);
                    }
                } else {
                    //just set the space behind to null (kick the cat off the board)
                    felinesBoopedOffBed.add(grid[row - 1][col + 1].getFeline());
                    grid[row - 1][col + 1].setFeline(null);
                }

            }
        }
        return felinesBoopedOffBed;
    }
    private ArrayList<Feline> boopDownLeft(int row, int col) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(row + 1 < rows && col - 1 >= 0) { //is one space behind on the board

            if (grid[row + 1][col - 1].getFeline() != null) { //is there a feline one space behind

                Feline feline = grid[row + 1][col - 1].getFeline();
                if (row + 2 < rows && col - 2 >= 0) { //is two spaces ahead on the board

                    if (grid[row + 2][col - 2].getFeline() != null) { //is there a feline two spaces beind
                        //do nothing
                    } else {
                        Tile t = new Tile(row + 2, col - 2);
                        t.setFeline(feline);
                        updateTile(row + 2, col - 2, t, true);
                        grid[row + 1][col - 1].setFeline(null);
                    }
                } else {
                    //just set the space behind to null (kick the cat off the board)
                    felinesBoopedOffBed.add(grid[row + 1][col - 1].getFeline());
                    grid[row + 1][col - 1].setFeline(null);
                }

            }
        }
        return felinesBoopedOffBed;
    }
    private ArrayList<Feline> boopDownRight(int row, int col) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(row + 1 < rows && col + 1 < cols) { //is one space behind on the board

            if (grid[row + 1][col + 1].getFeline() != null) { //is there a feline one space behind

                Feline feline = grid[row + 1][col + 1].getFeline();
                if (row + 2 < rows && col + 2 < cols) { //is two spaces ahead on the board

                    if (grid[row + 2][col + 2].getFeline() != null) { //is there a feline two spaces beind
                        //do nothing
                    } else {
                        Tile t = new Tile(row + 2, col + 2);
                        t.setFeline(feline);
                        updateTile(row + 2, col + 2, t, true);
                        grid[row + 1][col + 1].setFeline(null);
                    }
                } else {
                    //just set the space behind to null (kick the cat off the board)
                    felinesBoopedOffBed.add(grid[row + 1][col + 1].getFeline());
                    grid[row + 1][col + 1].setFeline(null);
                }

            }
        }
        return felinesBoopedOffBed;
    }

//    public void display() {
//        for (int i = 0; i < rows; i++){
//            for (int j = 0; j < cols; j++){
//                if(grid[i][j].getFeline() instanceof Feline)
//                    System.out.print("c ");
//                else
//                    System.out.print("x ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
}

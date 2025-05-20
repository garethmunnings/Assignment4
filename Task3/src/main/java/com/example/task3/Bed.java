package com.example.task3;

import java.util.ArrayList;
import java.util.Arrays;

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
        boolean isKitten = tile.getFeline() instanceof Kitten;

        if(!chainReaction)
            felinesBoopedOffBed = boop(row,col, isKitten);
        return felinesBoopedOffBed;
    }

    public ArrayList<Feline> boop(int row, int col, boolean isKitten) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();


        felinesBoopedOffBed.addAll(boopDown(row,col, isKitten));
        felinesBoopedOffBed.addAll(boopUp(row,col, isKitten));
        felinesBoopedOffBed.addAll(boopLeft(row,col, isKitten));
        felinesBoopedOffBed.addAll(boopRight(row,col, isKitten));

        felinesBoopedOffBed.addAll(boopUpLeft(row,col, isKitten));
        felinesBoopedOffBed.addAll(boopUpRight(row,col, isKitten));
        felinesBoopedOffBed.addAll(boopDownLeft(row,col, isKitten));
        felinesBoopedOffBed.addAll(boopDownRight(row,col, isKitten));

        return felinesBoopedOffBed;
    }

    private ArrayList<Feline> boopDown(int row, int col, boolean isKitten) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();

        if(isKitten) //only boop kittens
            {
                if (row + 1 < rows) { //is one space ahead on the board

                    if (grid[row + 1][col].getFeline() instanceof Kitten) { //is there a feline one space ahead

                        Feline feline = grid[row + 1][col].getFeline();
                        if (row + 2 < rows) { //is two spaces ahead on the board

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
            }
        else { //boop everyone
            if (row + 1 < rows) { //is one space ahead on the board

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
        }
        return felinesBoopedOffBed;
    }
    private ArrayList<Feline> boopUp(int row, int col, boolean isKitten) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(isKitten)
        {
            if(row - 1 >= 0) { //is one space behind on the board

                if (grid[row - 1][col].getFeline() instanceof Kitten) { //is there a feline one space behind

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
        }
        else {
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
        }

        return felinesBoopedOffBed;
    }
    private ArrayList<Feline> boopRight(int row, int col, boolean isKitten) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(isKitten)
        {
            if(col + 1 < cols) { //is one space ahead on the board

                if (grid[row][col + 1].getFeline() instanceof Kitten) { //is there a feline one space ahead

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
        }
        else{
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
        }

        return felinesBoopedOffBed;
    }
    private ArrayList<Feline> boopLeft(int row, int col, boolean isKitten) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(isKitten)
        {
            if(col - 1 >= 0) { //is one space behind on the board

                if (grid[row][col - 1].getFeline() instanceof Kitten) { //is there a feline one space behind

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
        }
        else
        {
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
        }

        return felinesBoopedOffBed;
    }

    private ArrayList<Feline> boopUpLeft(int row, int col, boolean isKitten) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(isKitten)
        {
            if(row - 1 >= 0 && col - 1 >= 0) { //is one space behind on the board

                if (grid[row - 1][col - 1].getFeline() instanceof Kitten) { //is there a feline one space behind

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
        }
        else{
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
        }

        return felinesBoopedOffBed;
    }
    private ArrayList<Feline> boopUpRight(int row, int col, boolean isKitten) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(isKitten)
        {
            if(row - 1 >= 0 && col + 1 < cols) { //is one space behind on the board

                if (grid[row - 1][col + 1].getFeline() instanceof Kitten) { //is there a feline one space behind

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
        }
        else{
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
        }
        return felinesBoopedOffBed;
    }
    private ArrayList<Feline> boopDownLeft(int row, int col, boolean isKitten) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(isKitten)
        {
            if(row + 1 < rows && col - 1 >= 0) { //is one space behind on the board

                if (grid[row + 1][col - 1].getFeline() instanceof Kitten) { //is there a feline one space behind

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
        }
        else{
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
        }

        return felinesBoopedOffBed;
    }
    private ArrayList<Feline> boopDownRight(int row, int col, boolean isKitten) {
        ArrayList<Feline> felinesBoopedOffBed = new ArrayList<>();
        if(isKitten)
        {
            if(row + 1 < rows && col + 1 < cols) { //is one space behind on the board

                if (grid[row + 1][col + 1].getFeline() instanceof Kitten) { //is there a feline one space behind

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
        }
        else{
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
        }

        return felinesBoopedOffBed;
    }

    public ArrayList<Feline> threeKittensInARow()
    {
        for(int i = 0 ; i < grid.length ; i++){
            for(int j = 0 ; j < grid.length ; j++){
                int player = 0;
                Feline current = grid[i][j].getFeline();
                if(current instanceof Kitten) {
                    player = current.getPlayer();
                }


                //horizontal
                if (j + 2 < cols &&
                        grid[i][j + 1].getFeline() instanceof Kitten &&
                        grid[i][j + 2].getFeline() instanceof Kitten)
                {
                    if(grid[i][j + 1].getFeline().getPlayer() == player &&
                            grid[i][j + 2].getFeline().getPlayer() == player)
                        return new ArrayList<>(Arrays.asList(current, grid[i][j + 1].getFeline(), grid[i][j + 2].getFeline()));
                }

                //vertical
                if (i + 2 < rows &&
                        grid[i + 1][j].getFeline() instanceof Kitten &&
                        grid[i + 2][j].getFeline() instanceof Kitten) {
                    if(grid[i + 1][j].getFeline().getPlayer() == player && grid[i + 2][j].getFeline().getPlayer() == player)
                        return new ArrayList<>(Arrays.asList(current, grid[i + 1][j].getFeline(), grid[i + 2][j].getFeline()));
                }

                //down right
                if (i + 2 < rows && j + 2 < cols &&
                        grid[i + 1][j + 1].getFeline() instanceof Kitten &&
                        grid[i + 2][j + 2].getFeline() instanceof Kitten) {
                    if(grid[i + 1][j + 1].getFeline().getPlayer() == player && grid[i + 2][j + 2].getFeline().getPlayer() == player)
                        return new ArrayList<>(Arrays.asList(current, grid[i + 1][j + 1].getFeline(), grid[i + 2][j + 2].getFeline()));
                }

                //down left
                if (i + 2 < rows && j - 2 >= 0 &&
                        grid[i + 1][j - 1].getFeline() instanceof Kitten &&
                        grid[i + 2][j - 2].getFeline() instanceof Kitten) {
                    if(grid[i + 1][j - 1].getFeline().getPlayer() == player && grid[i + 2][j - 2].getFeline().getPlayer() == player)
                        return new ArrayList<>(Arrays.asList(current, grid[i + 1][j - 1].getFeline(), grid[i + 2][j - 2].getFeline()));
                }
            }
        }
        return null;
    }

    public boolean threeCatsInARow()
    {
        for(int i = 0 ; i < grid.length ; i++){
            for(int j = 0 ; j < grid.length ; j++){
                int player = 0;
                Feline current = grid[i][j].getFeline();
                if(current instanceof Cat) {
                    player = current.getPlayer();
                }


                //horizontal
                if (j + 2 < cols &&
                        grid[i][j + 1].getFeline() instanceof Cat &&
                        grid[i][j + 2].getFeline() instanceof Cat)
                {
                    if(grid[i][j + 1].getFeline().getPlayer() == player &&
                            grid[i][j + 2].getFeline().getPlayer() == player)
                        return true;
                }

                //vertical
                if (i + 2 < rows &&
                        grid[i + 1][j].getFeline() instanceof Cat &&
                        grid[i + 2][j].getFeline() instanceof Cat) {
                    if(grid[i + 1][j].getFeline().getPlayer() == player && grid[i + 2][j].getFeline().getPlayer() == player)
                        return true;
                }

                //down right
                if (i + 2 < rows && j + 2 < cols &&
                        grid[i + 1][j + 1].getFeline() instanceof Cat &&
                        grid[i + 2][j + 2].getFeline() instanceof Cat) {
                    if(grid[i + 1][j + 1].getFeline().getPlayer() == player && grid[i + 2][j + 2].getFeline().getPlayer() == player)
                        return true;
                }

                //down left
                if (i + 2 < rows && j - 2 >= 0 &&
                        grid[i + 1][j - 1].getFeline() instanceof Cat &&
                        grid[i + 2][j - 2].getFeline() instanceof Cat) {
                    if(grid[i + 1][j - 1].getFeline().getPlayer() == player && grid[i + 2][j - 2].getFeline().getPlayer() == player)
                        return true;
                }
            }
        }
        return false;
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

    public Tile getTileWithFeline(Feline feline) {
        for(int row = 0; row < rows ; row++) {
            for(int col = 0; col < cols ; col++) {
                if(grid[row][col].getFeline() == feline)
                    return grid[row][col];
            }
        }
        return null;
    }
}

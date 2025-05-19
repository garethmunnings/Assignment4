package com.example.task3;

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

    public void updateTile(int row, int col, Tile tile, boolean chainReaction) {
        grid[row][col] = tile;
        if(!chainReaction)
            boop(row,col);
    }

    public void boop(int row, int col) {
        boopDown(row,col);
        boopUp(row,col);
        boopLeft(row,col);
        boopRight(row,col);

        boopUpLeft(row,col);
        boopUpRight(row,col);
        boopDownLeft(row,col);
        boopDownRight(row,col);
    }

    private void boopDown(int row, int col) {
        if(row + 1 < rows) { //is one space ahead on the board

        if (grid[row + 1][col].getFeline() != null) { //is there a feline one space ahead

            Feline feline = grid[row + 1][col].getFeline();
            if (row + 2 < rows) { //is two spaces ahead on the baord

                if(grid[row + 2][col].getFeline() != null) { //is there a feline two spaces ahead
                    //do nothing
                }
                else{
                    Tile t = new Tile(row + 2, col);
                    t.setFeline(feline);
                    updateTile(row + 2, col, t, true);
                    grid[row + 1][col].setFeline(null);
                }
            }
            else{
                //just set the space ahead to null (kick the cat off the board)
                grid[row + 1][col].setFeline(null);
            }

        }
    }}
    private void boopUp(int row, int col) {
        if(row - 1 >= 0) { //is one space behind on the board

        if (grid[row - 1][col].getFeline() != null) { //is there a feline one space behind

            Feline feline = grid[row - 1][col].getFeline();
            if (row - 2 >= 0) { //is two spaces ahead on the board

                if(grid[row - 2][col].getFeline() != null) { //is there a feline two spaces beind
                    //do nothing
                }
                else{
                    Tile t = new Tile(row - 2, col);
                    t.setFeline(feline);
                    updateTile(row - 2, col, t, true);
                    grid[row - 1][col].setFeline(null);
                }
            }
            else{
                //just set the space behind to null (kick the cat off the board)
                grid[row - 1][col].setFeline(null);
            }

        }
    }}
    private void boopRight(int row, int col) {
        if(col + 1 < cols) { //is one space ahead on the board

        if (grid[row][col + 1].getFeline() != null) { //is there a feline one space ahead

            Feline feline = grid[row][col + 1].getFeline();
            if (col + 2 < cols) { //is two spaces ahead on the baord

                if(grid[row][col + 2].getFeline() != null) { //is there a feline two spaces ahead
                    //do nothing
                }
                else{
                    Tile t = new Tile(row,col + 2);
                    t.setFeline(feline);
                    updateTile(row, col + 2, t, true);
                    grid[row][col + 1].setFeline(null);
                }
            }
            else{
                //just set the space ahead to null (kick the cat off the board)
                grid[row][col + 1].setFeline(null);
            }

        }
    }}
    private void boopLeft(int row, int col) {
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
                    grid[row][col - 1].setFeline(null);
                }

            }
        }
    }

    private void boopUpLeft(int row, int col) {
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
                    grid[row - 1][col - 1].setFeline(null);
                }

            }
        }
    }
    private void boopUpRight(int row, int col) {
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
                    grid[row - 1][col + 1].setFeline(null);
                }

            }
        }
    }
    private void boopDownLeft(int row, int col) {
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
                    grid[row + 1][col - 1].setFeline(null);
                }

            }
        }
    }
    private void boopDownRight(int row, int col) {
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
                    grid[row + 1][col + 1].setFeline(null);
                }

            }
        }
    }

    public void display() {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if(grid[i][j].getFeline() instanceof Feline)
                    System.out.print("c ");
                else
                    System.out.print("x ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

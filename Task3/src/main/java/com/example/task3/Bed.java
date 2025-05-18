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
                grid[i][j] = new Tile();
    }

    public Tile getTile(int row, int col) {
        return grid[row][col];
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public Tile[][] getGrid() { return grid; }
}

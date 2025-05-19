package com.example.task3;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainScreenController {


    @FXML
    private GridPane gridPane;
    Game game;

    @FXML
    private void initialize() {
        game = new Game();
        drawGrid();
    }

    private void drawGrid() {
        gridPane.getChildren().clear();

        for (int row = 0; row < game.getBed().getRows(); row++) {
            for (int col = 0; col < game.getBed().getCols(); col++) {
                Tile tile = game.getBed().getTile(row, col);

                Pane pane = new Pane();
                pane.setPrefSize(100, 100);
                pane.getStyleClass().add("tile");

                int r = row, c = col;
                pane.setOnMouseClicked(e -> handleTileClick(r, c));

                if (!tile.isEmpty()) {
                    Feline feline = tile.getFeline();

                    ImageView iv = feline.getIV();
                    pane.getChildren().add(iv);
                }
                gridPane.add(pane, col, row);
            }
        }
    }


    private void handleTileClick(int r, int c) {
        Tile tile = game.getBed().getTile(r, c);
        if (tile.isEmpty()) {
            tile.setFeline(new Kitten(game.getPlayerTurn()));
        }

        game.endTurn();
        drawGrid();
    }

}
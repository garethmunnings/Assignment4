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

                Pane tilePane = new Pane();
                tilePane.setPrefSize(100, 100);
                tilePane.getStyleClass().add("tile");

                int r = row, c = col;
                tilePane.setOnMouseClicked(e -> handleTileClick(r, c));

                if (!tile.isEmpty()) {
                    if(game.getPlayerTurn() == 1) {

                        //tilePane.getChildren().add();
                    }
                    else{
                        ImageView imageView = new ImageView();
                        tilePane.getChildren().add(imageView);
                    }
                }
                gridPane.add(tilePane, col, row);
            }
        }
    }


    private void handleTileClick(int r, int c) {
        Tile tile = game.getBed().getTile(r, c);
        if (tile.isEmpty()) {
            tile.setFeline(new Kitten(1));
        }
        else {
            tile.setFeline(null);
        }
        game.endTurn();
        drawGrid();
    }

}
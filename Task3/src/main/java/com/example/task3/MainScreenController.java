package com.example.task3;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainScreenController {
    private final Image orangeCat = new Image(getClass().getResource("/com/example/task3/images/OrangeCat.png").toString());
    private final Image orangeKitten = new Image(getClass().getResource("/com/example/task3/images/OrangeKitten.png").toString());
    private final Image purpleCat = new Image(getClass().getResource("/com/example/task3/images/PurpleCat.png").toString());
    private final Image purpleKitten = new Image(getClass().getResource("/com/example/task3/images/PurpleKitten.png").toString());

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
                    if(game.getPlayerTurn().equals("player 1")) {
                        ImageView imageView = new ImageView(orangeKitten);
                        initializeIV(imageView);
                        tilePane.getChildren().add(imageView);
                    }
                    else{
                        ImageView imageView = new ImageView(purpleKitten);
                        initializeIV(imageView);
                        tilePane.getChildren().add(imageView);
                    }
                }
                gridPane.add(tilePane, col, row);
            }
        }
    }
    private void initializeIV(ImageView imageView) {
        imageView.setFitWidth(80);
        imageView.setPreserveRatio(true);

    }

    private void handleTileClick(int r, int c) {
        Tile tile = game.getBed().getTile(r, c);
        if (tile.isEmpty()) {
            tile.setFeline(new Kitten("Player1"));
        }
        else {
            tile.setFeline(null);
        }
        game.endTurn();
        drawGrid();
    }

}
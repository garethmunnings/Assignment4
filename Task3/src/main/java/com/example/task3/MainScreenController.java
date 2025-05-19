package com.example.task3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class MainScreenController {


    @FXML
    private GridPane gridPane;
    @FXML Label playerTurnLabel;
    @FXML private GridPane player1GridPane;
    @FXML private GridPane player2GridPane;
    Game game;

    @FXML
    private void initialize() {
        game = new Game();
        drawGrid();
        drawPlayerPools();
    }

    private void drawPlayerPools(){
        player1GridPane.getChildren().clear();
        for (int j = 0; j < 8; j++)
        {
            //Pane pane = new Pane();
            //pane.setPrefSize(100, 100);
            //pane.getStyleClass().add("pool-tile");

            //System.out.println(game.getPlayer1().getPool().getNextKitten().getIV() + " " + j);
            //pane.getChildren().add(game.getPlayer1().getPool().getNextKitten().getIV());
            player1GridPane.add(game.getPlayer1().getPool().getNextKitten().getIV(),0,j);
            player2GridPane.add(game.getPlayer2().getPool().getNextKitten().getIV(),0,j);
        }
        for (int j = 0; j < 8; j++)
        {
            //Pane pane = new Pane();
            //pane.setPrefSize(100, 100);
            //pane.getStyleClass().add("pool-tile");

            //System.out.println(game.getPlayer1().getPool().getNextCat().getIV() + " " + j);
            //pane.getChildren().add(game.getPlayer1().getPool().getNextCat().getIV());
            player1GridPane.add(game.getPlayer1().getPool().getNextCat().getIV(), 1,j);
            player2GridPane.add(game.getPlayer2().getPool().getNextCat().getIV(),1,j);
        }

    }

    private void changePlayerTurnLabel() {
        playerTurnLabel.setText("Player " + game.getPlayerTurn() + "'s turn");
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
            if(game.getCurrentPlayer().getPool().hasKittenAvailable()) {
                tile.setFeline(game.getCurrentPlayer().getPool().getNextKitten());
            }
        }
        game.endTurn();
        changePlayerTurnLabel();
        drawGrid();
    }

}
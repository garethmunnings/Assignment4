package com.example.task3;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
        for (int j = 0; j < game.getPlayer1().getPool().getNumberOfKittensAvailable(); j++)
        {
            Pane pane1 = new Pane();
            pane1.setPrefSize(100, 100);
            pane1.getStyleClass().add("pool-tile");

            ImageView iv = new Kitten(1).getIV();
            pane1.getChildren().add(iv);
            player1GridPane.add(pane1,1,j);

            setUpDragStartEvent(pane1, 1, j);

            Pane pane2 = new Pane();
            pane2.setPrefSize(100, 100);
            pane2.getStyleClass().add("pool-tile");


            pane2.getChildren().add(game.getPlayer2().getPool().getNextKitten().getIV());
            player2GridPane.add(pane2,0,j);

            setUpDragStartEvent(pane2, 0, j);
        }
        for (int j = 0; j < 8; j++)
        {
            Pane pane1 = new Pane();
            pane1.setPrefSize(100, 100);
            pane1.getStyleClass().add("pool-tile");

            setUpDragStartEvent(pane1, 0,j);

            Pane pane2 = new Pane();
            pane2.setPrefSize(100, 100);
            pane2.getStyleClass().add("pool-tile");

            setUpDragStartEvent(pane2, 1,j);
            pane1.getChildren().add(game.getPlayer1().getPool().getNextCat().getIV());
            player1GridPane.add(pane1,0,j);

            pane2.getChildren().add(game.getPlayer2().getPool().getNextCat().getIV());
            player2GridPane.add(pane2,1,j);
        }

    }

    private void setUpDragStartEvent(Pane pane, int col, int row){
        pane.setOnDragDetected(event -> {
            Dragboard db = pane.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString("kitten");
            db.setContent(content);

            game.getCurrentPlayer().getPool();

            event.consume();
        });
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


                pane.setOnDragOver(event -> {
                    if (event.getGestureSource() != pane && event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                });

                pane.setOnDragDropped(event -> {
                    Dragboard db = event.getDragboard();
                    boolean success = false;

                    if (db.hasString()) {
                        Pane sourcePane = (Pane) event.getGestureSource();
                        Node kitten = sourcePane.getChildren().get(0);

                        sourcePane.getChildren().clear();
                        pane.getChildren().add(kitten);

                        success = true;
                    }

                    Tile t = new Tile();
                    t.setFeline(game.getPlayer1().getPool().getNextKitten());
                    game.getBed().updateTile(0,0, t);
                    game.getBed().display();
                    event.setDropCompleted(success);
                    event.consume();
                });

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
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
        drawPool(1);
        drawPool(2);

    }

    private void drawPool(int playernum){
        Player player;
        if(playernum == 1)
            player = game.getPlayer1();
        else if(playernum == 2)
            player = game.getPlayer2();
        else{
            System.out.println("Invalid playernum");
            return;
        }
    int i = 0;
        for (Kitten kitten : player.getPool().getKittenPool()) {
            Pane pane = new Pane();
            pane.setPrefSize(100, 100);
            pane.getStyleClass().add("pool-tile");

            ImageView iv = kitten.getIV();
            pane.getChildren().add(iv);
            if(playernum == 1)
                player1GridPane.add(pane, 0, i);
            else
                player2GridPane.add(pane, 0, i);

            Tile t = new Tile(-1,-1);
            t.setFeline(kitten);
            setUpDragStartEvent(pane, t);
            i++;
        }
        for (Cat cat : player.getPool().getCatPool()) {
            Pane pane = new Pane();
            pane.setPrefSize(100, 100);
            pane.getStyleClass().add("pool-tile");

            ImageView iv = new Cat(playernum).getIV();
            pane.getChildren().add(iv);
            if(playernum == 1)
                player1GridPane.add(pane, 0, i + player.getPool().getNumberOfKittensAvailable());
            else
                player2GridPane.add(pane, 0, i + player.getPool().getNumberOfKittensAvailable());

            Tile t = new Tile(-1,-1);
            t.setFeline(cat);
            setUpDragStartEvent(pane, t);
            i++;
        }
    }

    private void setUpDragStartEvent(Pane pane, Tile tile){
        pane.setOnDragDetected(event -> {
            DragContext.draggedObject = tile;
            Dragboard db = pane.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString("feline");
            db.setContent(content);

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
                int r = row; int c = col;
                pane.setOnDragDropped(event -> {
                    Dragboard db = event.getDragboard();
                    boolean success = false;

                    if (db.hasString()) {
                        Tile t = (Tile)DragContext.draggedObject;
                        tile.setFeline(t.getFeline());
                        game.getBed().updateTile(r,c, tile, false);
                        pane.getChildren().add(tile.getFeline().getIV());

                        game.getBed().getTile(t.getRow(), t.getCol()).setFeline(null);
                        success = true;
                    }

                    game.getBed().display();
                    drawGrid();

                    event.setDropCompleted(success);
                    event.consume();
                });

//              pane.setOnMouseClicked(e -> handleTileClick(r, c));

                if (!tile.isEmpty()) {
                    Feline feline = tile.getFeline();

                    ImageView iv = feline.getIV();
                    pane.getChildren().add(iv);
                    setUpDragStartEvent(pane, tile);
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
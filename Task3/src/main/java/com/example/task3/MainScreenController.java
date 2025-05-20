package com.example.task3;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MainScreenController {


    @FXML
    private GridPane gridPane;
    @FXML Label playerTurnLabel;
    @FXML private GridPane player1GridPane;
    @FXML private GridPane player2GridPane;
    @FXML private Label winLabel;
    Game game;

    @FXML
    private void initialize() {
        game = new Game();
        drawGrid();
        drawPlayerPools();
    }

    private void drawPlayerPools(){
        player1GridPane.getChildren().clear();
        player2GridPane.getChildren().clear();
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
            setUpDragStartEvent(pane, t, true);
            i++;
        }
        for (Cat cat : player.getPool().getCatPool()) {
            Pane pane = new Pane();
            pane.setPrefSize(100, 100);
            pane.getStyleClass().add("pool-tile");

            ImageView iv = cat.getIV();
            pane.getChildren().add(iv);
            if(playernum == 1)
                player1GridPane.add(pane, 0, i + player.getPool().getNumberOfKittensAvailable());
            else
                player2GridPane.add(pane, 0, i + player.getPool().getNumberOfKittensAvailable());

            Tile t = new Tile(-1,-1);
            t.setFeline(cat);
            setUpDragStartEvent(pane, t, true);
            i++;
        }
    }

    private void setUpDragStartEvent(Pane pane, Tile tile, boolean fromPool){
        if(game.getCurrentPlayer().getNum() == tile.getFeline().getPlayer()){

            pane.setOnMouseEntered(event -> pane.setCursor(Cursor.MOVE));
            pane.setOnMouseExited(event -> pane.setCursor(Cursor.DEFAULT));

            pane.setOnDragDetected(event -> {
                DragContext.draggedObject = tile;
                DragContext.fromPool = fromPool;
                Dragboard db = pane.startDragAndDrop(TransferMode.MOVE);

                //remove feline from pool
                if(fromPool) {
                    int p = tile.getFeline().getPlayer();
                    if (p == 1) {
                        if (tile.getFeline() instanceof Kitten)
                            game.getPlayer1().getPool().removeKitten((Kitten) tile.getFeline());
                        else if(tile.getFeline() instanceof Cat)
                            game.getPlayer1().getPool().removeCat((Cat) tile.getFeline());
                    }
                    if (p == 2) {
                        if (tile.getFeline() instanceof Kitten)
                            game.getPlayer2().getPool().removeKitten((Kitten) tile.getFeline());
                        else if(tile.getFeline() instanceof Cat)
                            game.getPlayer2().getPool().removeCat((Cat) tile.getFeline());
                    }
                }

                ClipboardContent content = new ClipboardContent();
                content.putString("feline");
                db.setContent(content);

                event.consume();
            });
        }
        else {
            pane.setOnDragDetected(null); // disable dragging
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
                        if(DragContext.fromPool){//remove feline from pool

                            if(t.getFeline() instanceof Cat){
                                if(t.getFeline().player == 1)
                                    game.getPlayer1().getPool().removeCat((Cat)t.getFeline());
                                else
                                    game.getPlayer2().getPool().removeCat((Cat)t.getFeline());
                            }
                            else {
                                if (t.getFeline().player == 1)
                                    game.getPlayer1().getPool().removeKitten((Kitten) t.getFeline()); //not finding kitten
                                else if (t.getFeline().player == 2)
                                    game.getPlayer2().getPool().removeKitten((Kitten) t.getFeline()); //not finding kitten
                                }
                        }
                        t.getFeline().setInPlay();
                        tile.setFeline(t.getFeline());
                        if(!DragContext.fromPool) //set the tile on the beds feline to null
                            game.getBed().getTile(t.getRow(), t.getCol()).setFeline(null);

                        ArrayList<Feline> felinesBoopedOffBed;
                        felinesBoopedOffBed = game.getBed().updateTile(r,c, tile, false);

                        for(Feline f : felinesBoopedOffBed){
                            f.setOutOfPlay();

                            if(f.getPlayer() == 1) {
                                game.getPlayer1().getPool().addFeline(f);
                            }
                            else if (f.getPlayer() == 2) {
                                game.getPlayer2().getPool().addFeline(f);
                            }

                        }

                        drawPlayerPools();
                        pane.getChildren().add(tile.getFeline().getIV());


                        success = true;
                        if(game.getBed().threeCatsInARow())
                        {
                            winLabel.setText("Player " + game.getPlayerTurn() + "wins");
                            winLabel.setVisible(true);
                        }
                        game.endTurn();

                        if(game.getBed().threeKittensInARow() != null){
                            upgradeKittensToCats(game.getBed().threeKittensInARow());
                        }

                        drawPlayerPools();
                        changePlayerTurnLabel();
                    }
                    drawGrid();

                    event.setDropCompleted(success);
                    event.consume();
                });

//              pane.setOnMouseClicked(e -> handleTileClick(r, c));

                if (!tile.isEmpty()) {
                    Feline feline = tile.getFeline();

                    ImageView iv = feline.getIV();
                    pane.getChildren().add(iv);
                    setUpDragStartEvent(pane, tile, false);
                }
                gridPane.add(pane, col, row);

            }
        }
    }

    private void upgradeKittensToCats(ArrayList<Feline> kittensInARow)
    {;
        int player = kittensInARow.getFirst().getPlayer();
        if(player == 1)
            for(Feline f : kittensInARow){
                Kitten k = (Kitten)f;
                game.getBed().getTileWithFeline(k).setEmpty();

                game.getPlayer1().getPool().addCat(new Cat(player));

            }
        else if(player == 2)
        {
            for(Feline f : kittensInARow) {
                Kitten k = (Kitten)f;
                game.getBed().getTileWithFeline(k).setEmpty();
                game.getPlayer2().getPool().addCat(new Cat(player));
            }
        }
        drawPlayerPools();
    }


    private void handleTileClick(int r, int c) {
        Tile tile = game.getBed().getTile(r, c);
        if (tile.isEmpty()) {

        }
    }

}
package com.example.task3;

import javafx.scene.image.ImageView;

public abstract class Feline {
    protected int player;
    protected ImageView image;
    protected boolean inPlay;

    public int getPlayer() {return player;}
    public boolean getInPlay() {return inPlay;}

    public void setInPlay() {this.inPlay = true;}
    public void setOutOfPlay() {this.inPlay = false;}

    protected void initializeIV(ImageView imageView) {
        imageView.setFitWidth(80);
        imageView.setPreserveRatio(true);

    }

    public ImageView getIV() {return image;}
}

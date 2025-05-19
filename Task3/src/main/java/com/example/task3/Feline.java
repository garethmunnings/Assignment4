package com.example.task3;

import javafx.scene.image.ImageView;

public abstract class Feline {
    String type;
    int player;
    ImageView image;

    public int getPlayer() {return player;}
    public String getType() {return type;}

    protected void initializeIV(ImageView imageView) {
        imageView.setFitWidth(80);
        imageView.setPreserveRatio(true);

    }

    public ImageView getIV() {return image;}
}

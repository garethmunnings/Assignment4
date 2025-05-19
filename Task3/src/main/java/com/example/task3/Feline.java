package com.example.task3;

import javafx.scene.image.ImageView;

public abstract class Feline {
    String type;
    String player;

    public String getPlayer() {return player;}
    public String getType() {return type;}

    protected void initializeIV(ImageView imageView) {
        imageView.setFitWidth(80);
        imageView.setPreserveRatio(true);

    }
}

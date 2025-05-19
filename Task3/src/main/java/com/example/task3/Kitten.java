package com.example.task3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Kitten extends Feline {
    private String type;
    private int player;
    private final Image purpleKitten = new Image(getClass().getResource("/com/example/task3/images/PurpleKitten.png").toString());
    private final Image orangeKitten = new Image(getClass().getResource("/com/example/task3/images/OrangeKitten.png").toString());
    private ImageView kittenIV;

    public Kitten(int player) {
        type = "kitten";
        this.player = player;

        if(player == 1)
            kittenIV = new ImageView(orangeKitten);
        else
            kittenIV = new ImageView(purpleKitten);
        initializeIV(kittenIV);
    }

    @Override
    public ImageView getIV() {
        return kittenIV;
    }
    @Override
    public String getType() {
        return type;
    }
}

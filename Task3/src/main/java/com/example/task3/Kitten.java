package com.example.task3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Kitten extends Feline {
    private String type;
    private int player;
    private final Image purpleKitten = new Image(getClass().getResource("/com/example/task3/images/PurpleKittenLower.png").toString());
    private final Image orangeKitten = new Image(getClass().getResource("/com/example/task3/images/OrangeKittenLower.png").toString());
    private ImageView kittenIV;

    public Kitten(int player) {
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

}

package com.example.task3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cat extends Feline {
    private String type;
    private int player;
    private final Image orangeCat = new Image(getClass().getResource("/com/example/task3/images/OrangeCat.png").toString());
    private final Image purpleCat = new Image(getClass().getResource("/com/example/task3/images/PurpleCat.png").toString());
    private ImageView catIV;

    public Cat(int player) {
        type = "cat";
        this.player = player;
        if(player == 1)
            catIV = new ImageView(orangeCat);
        else
            catIV = new ImageView(purpleCat);

        initializeIV(catIV);

    }
}

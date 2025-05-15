package com.example.task2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MusicApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        HBox usernameBox = new HBox(10,new Label("Username"), new TextField());
        usernameBox.setAlignment(Pos.CENTER);

        HBox passwordBox = new HBox(10, new Label("Password"), new TextField());
        passwordBox.setAlignment(Pos.CENTER);

        Button login = new Button("Login");

        VBox vbox = new VBox(10, usernameBox, passwordBox, login);
        vbox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);

        login.setOnAction(event -> {

        });
        Scene scene = new Scene(borderPane, 800, 600);

        stage.setScene(scene);
        stage.setTitle("Music Application");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

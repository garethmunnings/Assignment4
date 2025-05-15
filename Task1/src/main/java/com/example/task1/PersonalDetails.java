package com.example.task1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Label;

public class PersonalDetails extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Personal Details");

        Label nameLabel = new Label("Name: ");
        TextField nameField = new TextField();

        Label surnameLabel = new Label("Surname: ");
        TextField surnameField = new TextField();

        Label studentNumberLabel = new Label("Student Number: ");
        TextField studentNumberField = new TextField();

        Label courseLabel = new Label("Course: ");
        TextField courseField = new TextField();

        Label yearLabel = new Label("Year: ");
        TextField yearField = new TextField();

        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setStyle("-fx-padding: 10;");

        pane.add(nameLabel, 0, 0);
        pane.add(nameField, 1, 0);
        pane.add(surnameLabel, 0, 1);
        pane.add(surnameField, 1, 1);
        pane.add(studentNumberLabel, 0, 2);
        pane.add(studentNumberField, 1, 2);
        pane.add(courseLabel, 0, 3);
        pane.add(courseField, 1, 3);
        pane.add(yearLabel, 0, 4);
        pane.add(yearField, 1, 4);

        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        HBox buttonBox = new HBox(10, okButton, cancelButton);
        buttonBox.setStyle("-fx-padding: 10 0 0 110;");

        VBox mainLayout = new VBox(pane, buttonBox);

        // Set Scene
        Scene scene = new Scene(mainLayout, 300, 250);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
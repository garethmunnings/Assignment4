package com.example.task1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrinterDialog extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Print Dialog");

        Label printerLabel = new Label("Printer: Epson Epl-700");
        printerLabel.setLayoutX(10);
        printerLabel.setLayoutY(10);

        RadioButton imageButton = new RadioButton("Image");
        imageButton.setLayoutX(100);
        imageButton.setLayoutY(50);

        RadioButton textButton = new RadioButton("Text");
        textButton.setLayoutX(100);
        textButton.setLayoutY(70);

        RadioButton codeButton = new RadioButton("Code");
        codeButton.setLayoutX(100);
        codeButton.setLayoutY(90);

        Label qualityLabel = new Label("Print Quality:");
        qualityLabel.setLayoutX(10);
        qualityLabel.setLayoutY(140);


        ChoiceBox<String> qualityChoiceBox = new ChoiceBox<>();
        ObservableList<String> options = FXCollections.observableArrayList("Low", "Medium", "High");
        qualityChoiceBox.setItems(options);
        qualityChoiceBox.setValue("Low");
        qualityChoiceBox.setLayoutX(85);
        qualityChoiceBox.setLayoutY(135);

        RadioButton printToFileButton = new RadioButton("Print To File");
        printToFileButton.setLayoutX(200);
        printToFileButton.setLayoutY(140);

        Button btn1 = new Button("Ok");
        Button btn2 = new Button("Cancel");
        Button btn3 = new Button("Setup...");
        Button btn4 = new Button("Help");

        double h = 40;
        double w = 60;
        btn1.setPrefSize(w, h);
        btn2.setPrefSize(w, h);
        btn3.setPrefSize(w, h);
        btn4.setPrefSize(w, h);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(btn1, btn2, btn3, btn4);
        vbox.setLayoutX(300);
        vbox.setLayoutY(1);


        //BorderPane borderPane = new BorderPane();
        //borderPane.setRight(vbox);


        Pane root = new Pane(printerLabel, imageButton, textButton, codeButton, qualityLabel, qualityChoiceBox, printToFileButton, vbox);
        //root.getChildren().add(borderPane);
        Scene scene = new Scene(root, 360, 170);

        stage.setTitle("Print Dialog");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
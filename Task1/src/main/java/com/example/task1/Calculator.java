package com.example.task1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calculator extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Calculator");

        GridPane buttons = new GridPane();
        buttons.setHgap(7);
        buttons.setVgap(7);

        double buttonWidth = 50;
        double buttonHeight = 40;

        Button mc = new Button("MC");
        Button mr = new Button("MR");
        Button ms = new Button("MS");
        Button mPlus = new Button("M+");
        Button mMinus = new Button("M-");

        Button back = new Button("<-");
        Button ce = new Button("CE");
        Button c = new Button("C");
        Button plusMinus = new Button("+-");
        Button sqrt = new Button("/");

        Button seven = new Button("7");
        Button eight = new Button("8");
        Button nine = new Button("9");
        Button slash = new Button("/");
        Button percentage = new Button("%");

        Button four = new Button("4");
        Button five = new Button("5");
        Button six = new Button("6");
        Button star = new Button("*");
        Button over = new Button("1/x");

        Button one = new Button("1");
        Button two = new Button("2");
        Button three = new Button("3");
        Button minus = new Button("-");
        Button equal = new Button("=");

        Button zero = new Button("0");
        Button point = new Button(".");
        Button plus = new Button("+");

        Button[] buttonsArr = {
                mc, mr, ms, mPlus, mMinus,
                back, ce, c, plusMinus, sqrt,
                seven, eight, nine, slash, percentage,
                four, five, six, star, over,
                one, two, three, minus, equal,
                zero, point, plus
        };

        Button[] numbers = {
                zero, one, two, three, four, five, six, seven, eight, nine, point
        };

        Button[] gradientButtons = {back, ce, c, plusMinus, sqrt, slash, percentage, star, over, minus, equal, plus};

        Button[] otherButtons = {mc, mr, ms, mPlus, mMinus};

        for(Button button: buttonsArr)
        {
            button.setPrefSize(buttonWidth, buttonHeight);
        }

        for(Button button: numbers)
        {
            button.setStyle("-fx-background-color: #ebf2f8; " +
                    "-fx-border-color: #a3b1c2; " +
                    "-fx-border-width: 1px; " +
                    "-fx-border-radius: 2; " +
                    "-fx-background-radius: 2;" +
                    "-fx-font-size: 16px;");
        }

        for(Button button: gradientButtons)
        {
            button.setStyle(
                    "-fx-background-color: linear-gradient(to bottom, #ebf2f8 40%, #d4e0ed 60%);" +
                            "-fx-border-color: #a3b1c2; " +
                            "-fx-border-width: 1px; " +         // Border thickness
                            "-fx-border-radius: 2; " +          // Rounded corners
                            "-fx-background-radius: 2;" +
                            "-fx-font-size: 16px;");
        }
        for(Button button: otherButtons)
        {
            button.setStyle(
                    "-fx-background-color: #d4e0ed;" +
                            "-fx-border-color: #a3b1c2; " +
                            "-fx-border-width: 1px; " +         // Border thickness
                            "-fx-border-radius: 2; " +          // Rounded corners
                            "-fx-background-radius: 2;" +
                            "-fx-font-size: 16px;");
        }

        zero.setPrefSize(buttonWidth * 2 + 7, buttonHeight);
        equal.setPrefSize(buttonWidth, buttonHeight * 2 + 7);

        buttons.add(mc, 0, 0);
        buttons.add(mr, 1, 0);
        buttons.add(ms, 2, 0);
        buttons.add(mPlus, 3, 0);
        buttons.add(mMinus, 4, 0);

        buttons.add(back, 0, 1);
        buttons.add(ce, 1, 1);
        buttons.add(c, 2, 1);
        buttons.add(plusMinus, 3, 1);
        buttons.add(sqrt, 4, 1);

        buttons.add(seven, 0, 2);
        buttons.add(eight, 1, 2);
        buttons.add(nine, 2, 2);
        buttons.add(slash, 3, 2);
        buttons.add(percentage, 4, 2);

        buttons.add(four, 0, 3);
        buttons.add(five, 1, 3);
        buttons.add(six, 2, 3);
        buttons.add(star, 3, 3);
        buttons.add(over, 4, 3);

        buttons.add(one, 0, 4);
        buttons.add(two, 1, 4);
        buttons.add(three, 2, 4);
        buttons.add(minus, 3, 4);
        buttons.add(equal, 4, 4, 1, 2);

        buttons.add(zero, 0, 5, 2, 1);
        buttons.add(point, 2, 5);
        buttons.add(plus, 3, 5);

        TextField display = new TextField("0");
        display.setFont(Font.font( 30));
        display.setPrefSize(100, 100);
        display.setEditable(false);
        display.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #e5eefb, #ffffff);" +
                        "-fx-border-color: #c2ccd8; " +
                        "-fx-border-width: 2px; " +         // Border thickness
                        "-fx-border-radius: 5; " +          // Rounded corners
                        "-fx-background-radius: 5; " +
                        "-fx-alignment: BOTTOM_RIGHT;"
        );


        Label view = new Label("View");
        Label edit = new Label("Edit");
        Label help = new Label("Help");

        Label[] labels = new Label[] {view, edit, help};

        for(Label label: labels)
        {
            label.setStyle("-fx-font-size: 16px;");
        }

        HBox options = new HBox(10, view, edit, help);
        options.setPrefSize(10, 10);
        options.setStyle("-fx-background-color: white;");

        VBox root = new VBox(10, display, buttons);
        root.setStyle("-fx-background-color: #d9e4f1");
        root.setPadding(new Insets(10));

        VBox main = new VBox(options, root);
        Platform.runLater(options::requestFocus);
        Scene scene = new Scene(main,300, 430);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

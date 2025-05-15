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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MusicApplication extends Application {
    private Connection con = null;
    private Statement stmt = null;

    @Override
    public void start(Stage stage) throws Exception {


        stage.setScene(createLoginPage(stage));

        stage.setTitle("Music Application");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public Scene createLoginPage(Stage stage)
    {
        TextField usernameTextField = new TextField();
        HBox usernameBox = new HBox(10,new Label("Username"), usernameTextField);
        usernameBox.setAlignment(Pos.CENTER);

        TextField passwordTextField = new TextField();
        HBox passwordBox = new HBox(10, new Label("Password"), passwordTextField);
        passwordBox.setAlignment(Pos.CENTER);

        Button login = new Button("Login");

        VBox vbox = new VBox(10, usernameBox, passwordBox, login);
        vbox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);

        login.setOnAction(event -> {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();


            if(!connectToDB(username, password))
            {
                usernameTextField.setText("");
                passwordTextField.setText("");
            }
            else{
                stage.setScene(createMainPage());
            };

        });
        Scene loginPage = new Scene(borderPane, 800, 600);
        return loginPage;
    }

    public Scene createMainPage()
    {
        Pane pane = new Pane();
        Scene scene = new Scene(pane,800,600);
        return scene;
    }

    public boolean connectToDB(String username, String password) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String connectionString = "jdbc:sqlserver://postsql.mandela.ac.za\\WRR;database=WRAP301Music;encrypt=true;trustServerCertificate=true";

        try {
            con = DriverManager.getConnection(connectionString, username, password);
            System.out.println("Connection successful.");
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}

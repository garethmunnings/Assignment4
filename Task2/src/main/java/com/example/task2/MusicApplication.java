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

import java.sql.*;

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
        usernameTextField.setText("WRAP301User");
        HBox usernameBox = new HBox(10,new Label("Username"), usernameTextField);
        usernameBox.setAlignment(Pos.CENTER);

        TextField passwordTextField = new TextField();
        passwordTextField.setText("WRAP301");
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
        //browse albums
        Button browseAlbum = new Button("Browse albums");

        //browse songs
        Button browseSong = new Button("Browse songs");

        HBox buttonsBox = new HBox(10, browseAlbum, browseSong);
        buttonsBox.setAlignment(Pos.CENTER);

        browseAlbum.setOnAction(event -> {

        });

        getMetaData("Album");
        Scene scene = new Scene(buttonsBox,800,600);
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
    /**
     * All done. Be polite and close the connection with the database.
     */
    public void disconnectDB() {
        System.out.println("Disconnecting from database...");

        try {
            //Important to close connection (same as with files)
            con.close();
        } catch (Exception ex) {
            System.out.println("   Unable to disconnect from database");
        }
    }

    public void getMetaData(String tableName) {
        System.out.println("Examining Meta Data...");

        try {
            // perform query on database and retrieve results
            String sql = "SELECT * FROM " + tableName;
            System.out.println("   Performing query, sql = " + sql);
            ResultSet result = stmt.executeQuery(sql);

            // get meta data of result set
            ResultSetMetaData meta = result.getMetaData();

            int columns = meta.getColumnCount();
            System.out.println("\tColumns = " + columns);
            for (int i = 1; i <= columns; i++) {
                String colName = meta.getColumnLabel(i);
                String colType = meta.getColumnTypeName(i);
                System.out.println("\tcol[" + i + "]: name = " + colName + ", type = " + colType);
            }

            System.out.println();
            System.out.println("\tDisplay by index");
            // while there are tuples in the result set, display them... using indices
            int row = 0;
            while (result.next()) {
                // get values from current tuple
                row++;
                String line = "\tRow[" + row + "]=";
                for (int i = 1; i <= columns; i++) {
                    line = line.concat(result.getString(i) + " ");
                }

                // use info
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Could not query database... " + e.getMessage());
        }

        System.out.println();
    }
}

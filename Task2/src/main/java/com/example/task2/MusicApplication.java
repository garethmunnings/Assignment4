package com.example.task2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
//TODO Browse songs / add songs to albums

//TODO search for albums

//TODO Album Added conformation

//TODO delete song from album
public class MusicApplication extends Application {
    private Connection con = null;
    private Statement stmt = null;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(createLoginScene(stage));

        stage.setTitle("Music Application");
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public Scene createLoginScene(Stage stage)
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
        login.setId("loginButton");

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
                stage.setScene(createMainScene(stage));
            };

        });
        Scene loginPage = new Scene(borderPane, 800, 600);
        return loginPage;
    }

    protected Scene createMainScene(Stage stage)
    {
        //browse albums
        Button browseAlbum = new Button("Browse albums");

        //browse songs
        Button browseSong = new Button("Browse songs");

        Button exit = new Button("Exit");
        HBox buttonsBox = new HBox(10, browseAlbum, browseSong, exit);
        buttonsBox.setAlignment(Pos.CENTER);

        browseAlbum.setOnAction(event -> {
            stage.setScene(createBrowseAlbums(stage));
        });

        browseSong.setOnAction(event -> {
            stage.setScene(createBrowseSongs(stage));
        });

        exit.setOnAction(event -> {
            disconnectDB();
            stage.close();
        });

        VBox vbox = new VBox(10,new Label("Main Page"), buttonsBox);
        Scene scene = new Scene(vbox,800,600);
        return scene;
    }

    public Scene createBrowseAlbums(Stage stage)
    {
        Button addAlbum = new Button("+");

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search");
        Button search = new Button("Search");
        HBox searchBox = new HBox(10,searchBar, search, addAlbum);



        ObservableList<String> albums = getMetaData("Album");
        ListView<String> listView = new ListView<String>(albums);

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                stage.setScene(createViewAlbum(stage, newVal));
            }
        });

        search.setOnAction(event -> {
            String title = searchBar.getText();
            String sql = "SELECT * FROM Album WHERE title LIKE '%" + title + "%'";
            ResultSet rs = null;
            ObservableList<String> albumsThatMatch = FXCollections.observableArrayList();
            try {
                rs = stmt.executeQuery(sql);

                ResultSetMetaData rsmd = rs.getMetaData();
                int columns = rsmd.getColumnCount();
                while(rs.next())
                {
                    String line = "";
                    for (int i = 1; i <= columns; i++) {
                        line = line.concat(rs.getString(i) + " ");
                    }
                    albumsThatMatch.add(line);
                    System.out.println(line);
                }
                //TODO update the list
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            listView.setItems(albumsThatMatch);
        });

        addAlbum.setOnAction(event -> {
            stage.setScene(createAddAlbum(stage));
        });

        Button home = new Button("Home");
        home.setOnAction(event -> {
            stage.setScene(createMainScene(stage));
        });

        HBox optionsBox = new HBox(10, home);

        VBox root = new VBox(10,new Label("Browse Albums"),searchBox, listView, optionsBox);
        Scene scene = new Scene(root,800,600);

        return scene;
    }

    public Scene createBrowseSongs(Stage stage)
    {
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search");
        Button search = new Button("Search");
        HBox searchBox = new HBox(10,searchBar, search);



        ObservableList<String> songs = getMetaData("Song");
        ListView<String> listView = new ListView<String>(songs);

        Button delete = new Button("Delete");
        delete.setOnAction(event -> {
            String selectedSong = listView.getSelectionModel().getSelectedItem();
            String SID = selectedSong.split(" ")[0];
            if(showConfirmation("Delete song", "Are you sure you want to delete this song?")){
                String sql = "DELETE FROM Song WHERE SID = '" + SID + "'";
                try {
                    stmt.executeUpdate(sql);
                    songs.remove(selectedSong);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Song deleted");

            }
            else {
                System.out.println("Song not deleted");
            }
        });

        Button edit = new Button("Edit");
        edit.setOnAction(event -> {
            String selectedSong = listView.getSelectionModel().getSelectedItem();
            stage.setScene(createEditSong(stage, selectedSong.split(" ")[0]));
        });
        HBox optionsBox = new HBox(10, delete, edit);

        search.setOnAction(event -> {
            String title = searchBar.getText();
            String sql = "SELECT * FROM Song WHERE title LIKE '%" + title + "%'";
            ResultSet rs = null;
            ObservableList<String> songsThatMatch = FXCollections.observableArrayList();
            try {
                rs = stmt.executeQuery(sql);

                ResultSetMetaData rsmd = rs.getMetaData();
                int columns = rsmd.getColumnCount();
                while(rs.next())
                {
                    String line = "";
                    for (int i = 1; i <= columns; i++) {
                        line = line.concat(rs.getString(i) + " ");
                    }
                    songsThatMatch.add(line);
                    System.out.println(line);
                }
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            listView.setItems(songsThatMatch);
        });

        Button home = new Button("Home");
        home.setOnAction(event -> {
            stage.setScene(createMainScene(stage));
        });

        VBox root = new VBox(10,new Label("Browse Songs"),searchBox, listView, optionsBox, home);
        Scene scene = new Scene(root,800,600);

        return scene;
    }

    public Scene createViewAlbum(Stage stage, String album){
        //TODO edit songs in albums
        VBox root = new VBox();

        Label label = new Label("View Album");
        root.getChildren().add(label);

        String[] parts = album.split(" ");
        String AID = parts[0];
        //String sql = "SELECT * FROM album WHERE AID = '" + AID + "'";
        String sql = "SELECT * FROM song INNER JOIN album ON song.AID = album.AID WHERE song.AID = '" + AID + "'";

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            ObservableList<String> songs = FXCollections.observableArrayList();
            while (rs.next()) {
                songs.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
            }

            ListView<String> ls = new ListView<String>(songs);
            root.getChildren().add(ls);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        Button delete = new Button("Delete");
        delete.setOnAction(event -> {
            if(deleteAlbum(AID))
            {
                System.out.println("Deleted Album");
            }
        });


        Button editAlbum = new Button("Edit");
        editAlbum.setOnAction(event -> {
            stage.setScene(createEditAlbum(stage, AID));
        });

        HBox editDelete = new HBox(10,editAlbum,delete);

        Button home = new Button("Home");
        home.setOnAction(event -> {
            stage.setScene(createMainScene(stage));
        });

        Button back = new Button("Back");
        back.setOnAction(event -> {
            stage.setScene(createBrowseAlbums(stage));
        });

        HBox optionsBox = new HBox(10, home, back);
        root.getChildren().add(editDelete);
        root.getChildren().add(optionsBox);

        Scene scene = new Scene(root,800,600);
        return scene;
    }

    public Scene createAddAlbum(Stage stage){
        VBox fields = new VBox();

        Label albumNameLabel = new Label("Album Name");
        TextField albumNameTF = new TextField();
        HBox albumNameHBox = new HBox(10, albumNameLabel, albumNameTF);

        Label releaseYearLabel = new Label("Release Year");
        TextField releaseYearTF = new TextField();
        HBox releaseYearHBox = new HBox(10, releaseYearLabel, releaseYearTF);

        Label albumArtistLabel = new Label("Artist");
        TextField artistNameTF = new TextField();
        HBox albumArtistHBox = new HBox(10, albumArtistLabel, artistNameTF);



        List<HBox> songs = new ArrayList<>();
        Button addSong = new Button("Add Song");

        addSong.setOnAction(e -> {
            TextField name = new TextField();
            name.setPromptText("Song name");

            TextField length = new TextField();
            length.setPromptText("Length (min:sec)");

            HBox addSongHBox = new HBox(10, name, length);
            songs.add(addSongHBox);
            fields.getChildren().add(fields.getChildren().size() - 2, addSongHBox);
        });

        Button save = new Button("Save");
        AtomicInteger albumId = new AtomicInteger();
        save.setOnAction(event -> {
            String a = "INSERT INTO Album VALUES ('"+albumNameTF.getText()+"', '" + releaseYearTF.getText() + "');";
            try {
                stmt.execute(a);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            String b = "SELECT * FROM Album WHERE Title = '"+albumNameTF.getText()+"';";

            try {
                ResultSet result = stmt.executeQuery(b);
                if (result.next()) {
                    albumId.set(result.getInt("AID"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            //add songs from list
            int i = 1;

            for(HBox song : songs){
                TextField songName = (TextField)song.getChildren().getFirst();
                TextField songLength = (TextField)song.getChildren().getLast();
                String s = "INSERT INTO Song VALUES ("+ albumId +","+i+",'"+songName.getText()+"', '" + artistNameTF.getText() + "', '"+songLength.getText()+"');";
                try{
                    stmt.executeUpdate(s);
                }
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                i++;
            }
        });

        Button home = new Button("Home");
        home.setOnAction(event -> {
            stage.setScene(createMainScene(stage));
        });

        Button back = new Button("Back");
        back.setOnAction(event -> {
            stage.setScene(createBrowseAlbums(stage));
        });


        HBox optionsBox = new HBox(10, home, back);
        fields.getChildren().addAll(albumArtistHBox, albumNameHBox, releaseYearHBox, addSong, save, optionsBox);
        Scene scene = new Scene(fields,800,600);
        return scene;
    }

    public Scene createEditAlbum(Stage stage, String AID)
    {
        String sqlAlbum = "SELECT * FROM Album WHERE AID = '"+AID+"';";
        String sqlSong = "SELECT * FROM Song WHERE AID = '"+AID+"';";

        String title = "";
        String releaseYear = "";
        String artistName = "";

        try {
            ResultSet rs = stmt.executeQuery(sqlAlbum);
            if (rs.next()) {
                title = rs.getString("Title");
                releaseYear = rs.getString("Year");
            }

            ResultSet songs = stmt.executeQuery(sqlSong);
            if(songs.next())
                artistName = songs.getString("Artist");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        VBox fields = new VBox();

        Label albumNameLabel = new Label("Album Name");
        TextField albumNameTF = new TextField();
        albumNameTF.setText(title);
        HBox albumNameHBox = new HBox(10, albumNameLabel, albumNameTF);

        Label releaseYearLabel = new Label("Release Year");
        TextField releaseYearTF = new TextField();
        releaseYearTF.setText(releaseYear);
        HBox releaseYearHBox = new HBox(10, releaseYearLabel, releaseYearTF);

        Label albumArtistLabel = new Label("Artist");
        TextField artistNameTF = new TextField();
        artistNameTF.setText(artistName);
        HBox albumArtistHBox = new HBox(10, albumArtistLabel, artistNameTF);



        List<HBox> songs = new ArrayList<>();
        Button addSong = new Button("Add Song");

        addSong.setOnAction(e -> {
            TextField name = new TextField();
            name.setPromptText("Song name");

            TextField length = new TextField();
            length.setPromptText("Length (min:sec)");

            HBox addSongHBox = new HBox(10, name, length);
            songs.add(addSongHBox);
            fields.getChildren().add(fields.getChildren().size() - 2, addSongHBox);
        });

        Button save = new Button("Save");
        AtomicInteger albumId = new AtomicInteger();
        save.setOnAction(event -> {
            String a = "INSERT INTO Album VALUES ('"+albumNameTF.getText()+"', '" + releaseYearTF.getText() + "');";
            try {
                stmt.execute(a);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            String b = "SELECT * FROM Album WHERE Title = '"+albumNameTF.getText()+"';";

            try {
                ResultSet result = stmt.executeQuery(b);
                if (result.next()) {
                    albumId.set(result.getInt("AID"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            //add songs from list
            int i = 1;

            for(HBox song : songs){
                TextField songName = (TextField)song.getChildren().getFirst();
                TextField songLength = (TextField)song.getChildren().getLast();
                String s = "INSERT INTO Song VALUES ("+ albumId +","+i+",'"+songName.getText()+"', '" + artistNameTF.getText() + "', '"+songLength.getText()+"');";
                try{
                    stmt.executeUpdate(s);
                }
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                i++;
            }
        });

        Button home = new Button("Home");
        home.setOnAction(event -> {
            stage.setScene(createMainScene(stage));
        });

        Button back = new Button("Back");
        back.setOnAction(event -> {
            stage.setScene(createBrowseAlbums(stage));
        });


        HBox optionsBox = new HBox(10, home, back);
        fields.getChildren().addAll(albumArtistHBox, albumNameHBox, releaseYearHBox, addSong, save, optionsBox);
        Scene scene = new Scene(fields,800,600);
        return scene;
    }

    public Scene createEditSong(Stage stage, String SID) {
        String sql = "SELECT * FROM Song WHERE SID = '"+SID+"';";
        ResultSet rs = null;
        String title = "";
        String length = "";
        String artistName = "";

        try{
            rs = stmt.executeQuery(sql);
            title = rs.getString("Title");
            length = rs.getString("Length");
            artistName = rs.getString("Artist");

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());


        }

        Label nameLabel = new Label("Song Name:");
        TextField name = new TextField();
        name.setText(title);

        Label lengthLabel = new Label("Length (min:sec): ");
        TextField lengthTF = new TextField();
        lengthTF.setText(length);

        Label artistLabel = new Label("Artist: ");
        TextField artistNameTF = new TextField();
        artistNameTF.setText(artistName);

        HBox nameHBox = new HBox(10, nameLabel, name);
        HBox lengthHBox = new HBox(10, lengthLabel, lengthTF);
        HBox artistHBox = new HBox(10, artistLabel, artistNameTF);

        VBox fields = new VBox(nameHBox, lengthHBox, artistHBox);
        Scene scene = new Scene(fields,800,600);
        return null;
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
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public void disconnectDB() {
        System.out.println("Disconnecting from database...");

        try {
            //Important to close connection (same as with files)
            con.close();
        } catch (Exception ex) {
            System.out.println("   Unable to disconnect from database");
        }
    }

    public ObservableList<String> getMetaData(String tableName) {
        ObservableList<String> items = FXCollections.observableArrayList();
        try {
            // perform query on database and retrieve results
            String sql = "SELECT * FROM " + tableName;
            ResultSet result = stmt.executeQuery(sql);

            // get meta data of result set
            ResultSetMetaData meta = result.getMetaData();

            int columns = meta.getColumnCount();

            while (result.next()) {
                // get values from current tuple
                String line = "";
                for (int i = 1; i <= columns; i++) {
                    line = line.concat(result.getString(i) + " ");

                }
                items.add(line);
            }
        } catch (Exception e) {
            System.out.println("Could not query database... " + e.getMessage());
        }
        return items;
    }

    public boolean deleteAlbum(String AID)
    {
        try {
            String sqlSong = "DELETE FROM Song WHERE AID = " + AID;
            String sqlAlbum = "DELETE FROM Album WHERE AID = " + AID;
            stmt.executeUpdate(sqlSong);
            stmt.executeUpdate(sqlAlbum);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null); // Optional: no header
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}

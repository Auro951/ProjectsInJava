package sample;

import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Menu;
//import javafx.scene.control.MenuBar;
//import javafx.scene.control.MenuItem;
//import javafx.scene.image.Image;
import javafx.scene.paint.Color;
//import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    VideoPlayer videoPlayer;


    @Override
    public void start(Stage primaryStage) throws Exception
    {

        videoPlayer = new VideoPlayer();
        Scene scene = new Scene(videoPlayer, 1280, 760, Color.BLACK);
        primaryStage.setTitle("VideoPlayer");
//        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}

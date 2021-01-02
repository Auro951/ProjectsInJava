package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
//import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class VideoPlayer extends BorderPane {

    Media media;
    MediaPlayer mediaPlayer;
    MediaView mediaView;
    Pane pane;
    FileChooser fileChooser;

    //VideoBar

    VideoBar bar;


    public VideoPlayer() {
        MenuItem open = new MenuItem("Open");
        Menu file = new Menu("File");
        MenuBar menu = new MenuBar();
        pane = new Pane();
        file.getItems().add(open);
        menu.getMenus().add(file);
        setTop(menu);
        fileChooser = new FileChooser();
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                File file = fileChooser.showOpenDialog(null);

                if(file != null){
                    try{
                        media = new Media(file.toURI().toURL().toExternalForm());
                        mediaPlayer = new MediaPlayer(media);
                        mediaView = new MediaView(mediaPlayer);
                        pane.getChildren().add(mediaView);
                        //one more Line
                        setCenter(pane);
                        bar = new VideoBar(mediaPlayer);
                        setBottom(bar);


                        mediaPlayer.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });




        //add Video Bar Here




    }


}

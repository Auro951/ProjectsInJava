package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sample3 extends BorderPane {

    @FXML
    private MediaView View;
    MediaPlayer player;
    Button b1 = new Button();
    Slider timeSlider = new Slider();
    Slider volSlider = new Slider();
    HBox hb = new HBox();
    Label volume = new Label("Volume");
    StackPane stackPane = new StackPane();

    public Sample3() {

    }

    public Sample3(Duration d, MediaPlayer m1, Stage s, Slider si) throws FileNotFoundException {
        prefWidth(1366);
        prefHeight(768);
        opacityProperty().setValue(1);
        player = m1;
        View = new MediaView(player);
        View.setFitWidth(1366.0);
        View.setFitHeight(768.0);
        View.opacityProperty().setValue(1);
        setCenter(stackPane);
        stackPane.getChildren().add(View);stackPane.setStyle("-fx-background-color:black;-fx-fore-color:black;");
        player.play();
        b1.setGraphic(new ImageView(new Image(new FileInputStream("C:/icons/exitfsc.png"))));
        stackPane.getChildren().add(hb);
        hb.setAlignment(Pos.BOTTOM_CENTER);
//        hb.setMaxHeight(25.0);
//        hb.setPrefHeight(25.0);
        volSlider.setPrefWidth(140.0);
        volSlider.setMinWidth(39.0);
        volSlider.prefHeight(30);
        volSlider.setValue(player.getVolume()*100);
        timeSlider.prefHeight(30);
        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        hb.getChildren().add(b1);
        hb.getChildren().add(timeSlider);
        hb.getChildren().add(volume);
        hb.getChildren().add(volSlider);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.pause();
                Stage stage = (Stage)b1.getScene().getWindow();
                stage.close();
                player.play();
                s.show();si.setValue(player.getVolume()*100);
            }
        });

        player.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                moveSlider();

            }
        });
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {

                if(timeSlider.isPressed()) {
//                    timeSlider.setValue(timeSlider.getValue()/100);
//                    player.setStartTime(player.getMedia().getDuration());
                    player.seek(new Duration(timeSlider.getValue()));

                }
            }});

        volSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(volSlider.isPressed()){
                    player.setVolume(volSlider.getValue()/100);
                }
            }
        });
    }
    private void moveSlider(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                timeSlider.setMin(0.0);
                timeSlider.setMax(player.getMedia().getDuration().toMillis());

                timeSlider.setValue(player.getCurrentTime().toMillis());


            }

        });


    }



}

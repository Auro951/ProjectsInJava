package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class VideoBar extends HBox {

    Slider timeSlider = new Slider();
    Slider volSlider = new Slider();

    Button button = new Button("||");
    Label volume = new Label("Volume");
    Button button1 = new Button("FullScr");

    MediaPlayer player;

    public VideoBar(MediaPlayer play) throws IOException {
        player = play;

        //css Styling Thing
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5,10,5,10));


        volSlider.setPrefWidth(70.0);
        volSlider.setMinWidth(30.0);
        volSlider.setValue(100);
        HBox.setHgrow(timeSlider, Priority.ALWAYS);

        button.prefWidth(40.0);

        getChildren().add(button);
        getChildren().add(timeSlider);
        getChildren().add(button1);
        getChildren().add(volume);
        getChildren().add(volSlider);

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    button1();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MediaPlayer.Status status = player.getStatus();

                if (status == MediaPlayer.Status.PLAYING){
                    if(player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())){
                        player.seek(player.getStartTime());
                        player.play();

                    }
                    else{
                        player.pause();
                        button.setText(">");

                    }
                }
                if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.STOPPED){
                player.play();
                button.setText("||");
            }
        }} );

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
                    player.seek(player.getMedia().getDuration().multiply(timeSlider.getValue()/100));
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


        public void button1() throws IOException{

        }

        private void moveSlider(){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    timeSlider.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis()*100);


                }

            });
        }
}









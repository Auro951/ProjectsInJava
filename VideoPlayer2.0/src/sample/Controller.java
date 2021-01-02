package sample;


import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    MediaPlayer player;



    @FXML
    private Label lbl1;


    @FXML
    public MediaView mediaView;

    @FXML
    public Button playBtn;

    @FXML
    private Button preBtn;

    @FXML
    private Button nxtBtn;

    @FXML
    private Slider timeSlider;

    @FXML
    public Slider volSlider;

    @FXML
    public Button fullSbtn;

    String v;
    Duration d;


    @FXML
    void openSongMenu(ActionEvent event) throws FileNotFoundException {
        try {
            System.out.println("OpenSongClicked");

            FileChooser choose = new FileChooser();

            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Mp4 File Please","*.mp4");

            choose.getExtensionFilters().add(filter);

            File file = choose.showOpenDialog(null);

            v = file.toURI().toURL().toString();
            Media m = new Media(file.toURI().toURL().toString());


            if(player != null){
                player.dispose();
            }



            player = new MediaPlayer(m);



            mediaView.setMediaPlayer(player);

            player.setOnReady(()->{
                // when PLayer Is Ready
                volSlider.setValue(100);
                timeSlider.setMin(0);
                double d1 = player.getMedia().getDuration().toMinutes();
                if (d1 >= 60) {

                    timeSlider.setMax(player.getMedia().getDuration().toHours());

                    timeSlider.setValue(0.0);
                }
                else if( d1 < 60){
                    timeSlider.setMax(player.getMedia().getDuration().toMinutes());

                    timeSlider.setValue(0.0);
                }
                try {
                    playBtn.setGraphic(new ImageView(new Image(new FileInputStream("C:/icons/play.png"))));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }});


            player.currentTimeProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
//                    moveSlider();

                    double d1 = player.getMedia().getDuration().toMinutes();
                    d = player.getCurrentTime();
                    if (d1 >= 60)
                        timeSlider.setValue(d.toHours());
                    else if( d1 < 60)
                        timeSlider.setValue(d.toMinutes());
                }
            });



            timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

//                player.pause();
//            playBtn.setText("Play");
                    if(timeSlider.isPressed()) {
                        double d1 = player.getMedia().getDuration().toMinutes();
//                    timeSlider.setValue(timeSlider.getValue()/100);
                        if (d1 >= 60)
                            player.seek(new Duration(timeSlider.getValue()*1000*60*60));
                        else if( d1 < 60)
                            player.seek(new Duration(timeSlider.getValue()*1000*60));
                    }
                }
            });

            volSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    if(volSlider.isPressed()){
                        player.setVolume(volSlider.getValue()/100);
                    }
                }
            });


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                try {
//                    fullscreen();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//           }
//       });


    }

    @FXML
    void fulls(ActionEvent event) throws IOException {
        player.pause();
        Stage stage = (Stage)fullSbtn.getScene().getWindow();
        stage.hide();
        Stage Fulls = new Stage();
        Sample3 samplex = new Sample3(d, player, stage, volSlider);
        Fulls.getIcons().add(new Image("file:///C:/icons/VideoPlayer.png"));
        Fulls.setTitle("VideoPlayer");
        Scene scene = new Scene(samplex, 1366, 768, Color.BLACK);
        Fulls.setScene(scene);
        Fulls.setFullScreen(true);
        Fulls.setFullScreenExitHint("Press The Button on the Bottom-Left corner To Exit Full Screen");

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                Fulls.hide();
                stage.show();
            }
            else if(e.getCode() == KeyCode.A){
                try {
                    MediaPlayer.Status status = player.getStatus();


                    if (status == MediaPlayer.Status.PLAYING) {

                        if(player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())){
                            player.seek(player.getStartTime());
                            player.play();

                        }
                        else{
                            player.pause();
                            playBtn.setGraphic(new ImageView(new Image(new FileInputStream("C:/icons/play.png"))));

                        }


                    } else {
                        player.play();

                        playBtn.setGraphic(new ImageView(new Image(new FileInputStream("C:/icons/pause.png"))));
                    }

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    Fulls.hide();
                    stage.show();
                }
                else if(event.getButton() == MouseButton.SECONDARY){

                        System.exit(0);
                }
            }
        });

        Fulls.show();
    }


    @FXML
    void about(ActionEvent event) throws IOException {
        Stage primarystage1;
        primarystage1 = new Stage();
        Parent root1 = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        primarystage1.getIcons().add(new Image("file:///C:/icons/VideoPlayer.png"));
        primarystage1.setTitle("About This Player");
        primarystage1.setScene(new Scene(root1,880,328));
        primarystage1.initStyle(StageStyle.UTILITY);
        primarystage1.show();

    }


    @FXML
    void Exit(ActionEvent event) {

        System.exit(0);

    }


    @FXML
    void nextBtnClick(ActionEvent event) {
        double cd = player.getCurrentTime().toSeconds();
        cd = cd+10;
        player.seek(new Duration(cd *1000));

    }

    @FXML
    void prevBtnClick(ActionEvent event) {
        double cd = player.getCurrentTime().toSeconds();
        cd = cd-10;
        player.seek(new Duration(cd *1000));
    }

    @FXML
    void Play(ActionEvent event) throws FileNotFoundException {
        try {
            MediaPlayer.Status status = player.getStatus();


            if (status == MediaPlayer.Status.PLAYING) {

                if(player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())){
                    player.seek(player.getStartTime());
                    player.play();

                }
                else{
                    player.pause();
                    playBtn.setGraphic(new ImageView(new Image(new FileInputStream("C:/icons/play.png"))));

                }


            } else {
                player.play();

                playBtn.setGraphic(new ImageView(new Image(new FileInputStream("C:/icons/pause.png"))));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            playBtn.setGraphic(new ImageView(new Image(new FileInputStream("C:/icons/play.png"))));
            preBtn.setGraphic(new ImageView(new Image(new FileInputStream("C:/icons/previous.png"))));
            nxtBtn.setGraphic(new ImageView(new Image(new FileInputStream("C:/icons/next.png"))));
            lbl1.setGraphic(new ImageView(new Image(new FileInputStream("C:/icons/speaker.png"))));
            fullSbtn.setGraphic(new ImageView(new Image(new FileInputStream("C:/icons/fullscreen.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

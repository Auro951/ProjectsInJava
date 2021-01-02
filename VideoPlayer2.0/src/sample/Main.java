package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        // load fxml file
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.getIcons().add(new Image("file:///C:/icons/VideoPlayer.png"));
        primaryStage.setTitle("VideoPlayer");
        Scene scene;
        scene = new Scene(root, 1366, 668, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setFullScreenExitHint("Press FullScreen Button ToGo True Full Screen Or Double Click Again ToGoBack TO DefaultView");
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    if(primaryStage.isFullScreen())
                        primaryStage.setFullScreen(false);
                    else
                        primaryStage.setFullScreen(true);
                }
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

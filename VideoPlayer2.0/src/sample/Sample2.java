package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Sample2 implements Initializable {
    @FXML
    private Label lbl2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            lbl2.setGraphic(new ImageView(new Image(new FileInputStream("C:/icons/VideoPlayer.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

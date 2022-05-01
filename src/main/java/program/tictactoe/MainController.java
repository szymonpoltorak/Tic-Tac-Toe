package program.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainController {
    @FXML
    private ImageView image00;
    @FXML
    private ImageView image01;
    @FXML
    private ImageView image02;
    @FXML
    private ImageView image10;
    @FXML
    private ImageView image11;
    @FXML
    private ImageView image12;
    @FXML
    private ImageView image20;
    @FXML
    private ImageView image21;
    @FXML
    private ImageView image22;
    @FXML
    private Label myLabel;

    @FXML
    public void userClickAction(MouseEvent event){
        Image circle = new Image(getClass().getResource("img/circle.png").toString());
        image00.setImage(circle);
    }

}
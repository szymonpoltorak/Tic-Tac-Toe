package pl.edu.pw.ee.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MainController {
    @FXML
    public Button resetButton;
    private Image blank;
    private boolean[] used;
    private ImageView[] images;
    private Board board;
    @FXML
    private Label titleLabel;
    @FXML
    private GridPane gameBoard;

    public void initialize(){
        blank = new Image(Objects.requireNonNull(getClass().getResource("img/blank.png")).toString());
        used = new boolean[9];
        board = new Board();
        images = new ImageView[9];

        titleLabel.setText("Tic-Tac-Toe");
        MainController.gridInit(images, gameBoard, blank);

        gameBoard.setOnMouseClicked(event -> {
            var source = (Node) event.getTarget();
            var index = 3 * GridPane.getRowIndex(source) + GridPane.getColumnIndex(source);

            if (used[index] && !Results.ifGameIsOver(board)){
                Alerts.pupWrongPositionAlert();
                return;
            }
            if (used[index] && Results.ifGameIsOver(board)){
                Alerts.popGameOverAlert();
                return;
            }

            Moves.makeUserMove(images, index, board, used);
            Results.checkIfResulted(board, used);

            Moves.makeComputerMove(board, images, used);
            Results.checkIfResulted(board, used);
        });
    }

    public static void gridInit(ImageView @NotNull [] images, GridPane gameBoard, Image blank){
        for (int i = 0; i < images.length; i++){
            images[i] = new ImageView();
            images[i].setImage(blank);
            gameBoard.add(images[i], i % 3,i / 3);
        }
        gameBoard.setGridLinesVisible(true);
    }

    public void resetBoard(ActionEvent actionEvent) {
        for (int i = 0; i < used.length; i++){
            used[i] = false;
            images[i].setImage(blank);
            board.resetPosition(i);
        }
    }
}
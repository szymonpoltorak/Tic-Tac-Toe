package program.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Objects;


public class MainController {
    @FXML
    public Label resultLabel;
    @FXML
    public Button resetButton;
    private Image circle; //TODO przerzucic do innej klasy
    private Image cross; //TODO przerzucic do innej klasy
    private Image blank;
    private boolean[] used; //to zostało zmienione z int[]
    private ImageView[] images;
    private Board board;
    @FXML
    private Label titleLabel;
    @FXML
    private GridPane gameBoard;

    public void initialize(){
        circle = new Image(Objects.requireNonNull(getClass().getResource("img/circle.png")).toString());
        cross = new Image(Objects.requireNonNull(getClass().getResource("img/cross.png")).toString());
        blank = new Image(Objects.requireNonNull(getClass().getResource("img/blank.png")).toString());
        used = new boolean[9];
        board = new Board();
        images = new ImageView[9];

        MainController.gridInit(images, gameBoard, blank); //zabrano stad petle

        gameBoard.setOnMouseClicked(event -> {
            Node source = (Node) event.getTarget();
            int index = 3 * GridPane.getRowIndex(source) + GridPane.getColumnIndex(source);

            if (used[index]){
                resultLabel.setText("THIS PLACE WAS ALREADY TAKEN !!!");
                return;
            }
/*
            images[index].setImage(circle);
            board.setPosition(index, CIRCLE);
            used[index] = true;
*/
            Moves.makeUserMove(circle, images, index, board, used);

            Results.checkIfResulted(board, used);

            Moves.makeComputerMove(board, images, cross, used);
/*
            int bestIndex = Board.getBestMove(board, CROSS);
            images[bestIndex].setImage(cross);
            board.setPosition(bestIndex, CROSS);
            used[bestIndex] = true;
 */
            Results.checkIfResulted(board, used);
        });
    }

    public static void gridInit(ImageView[] images, GridPane gameBoard, Image blank){
        for (int i = 0; i < images.length; i++){
            images[i] = new ImageView();
            images[i].setImage(blank);
            gameBoard.add(images[i], i % 3,i / 3);
        }
        gameBoard.setGridLinesVisible(true);
    }

    public void resetBoard(ActionEvent actionEvent) { //TODO resetowanie planszy

    }
}
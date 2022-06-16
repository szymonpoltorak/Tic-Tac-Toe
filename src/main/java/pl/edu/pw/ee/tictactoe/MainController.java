package pl.edu.pw.ee.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

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
    @FXML
    private MenuBar menuBar;

    public void initialize(){
        var size = 9;
        var rowLength = (int) Math.sqrt(size);
        blank = new Image(Objects.requireNonNull(getClass().getResource("img/blank.png")).toString());
        used = new boolean[size];
        board = new Board(size);
        images = new ImageView[size];

        titleLabel.setText("Tic-Tac-Toe");
        gridInit(board.getPositions().length);

        gameBoard.setOnMouseClicked(event -> {
            var source = (Node) event.getTarget();
            var index = rowLength * GridPane.getRowIndex(source) + GridPane.getColumnIndex(source);

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

    public void gridInit(int length){
        var rowLength = (int) Math.sqrt(length);
        gameBoard.getRowConstraints().clear();
        gameBoard.getColumnConstraints().clear();

        for (int i = 0; i < rowLength; i++){
            var col = new ColumnConstraints();
            col.setHgrow(Priority.ALWAYS);
            gameBoard.getColumnConstraints().add(col);
        }

        for (int i = 0; i < rowLength; i++){
            var row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            gameBoard.getRowConstraints().add(row);
        }

        for (int i = 0; i < images.length; i++){
            images[i] = new ImageView();
            images[i].setFitHeight(gameBoard.getPrefHeight() / rowLength);
            images[i].setFitWidth(gameBoard.getPrefWidth() / rowLength);
            images[i].setImage(blank);
            gameBoard.add(images[i], i % rowLength,i / rowLength);
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
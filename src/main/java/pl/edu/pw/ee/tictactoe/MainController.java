package pl.edu.pw.ee.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.tictactoe.Constants.AUTOMATE_MODE;
import static pl.edu.pw.ee.tictactoe.Constants.MANUAL_MODE;
import static pl.edu.pw.ee.tictactoe.Images.BLANK_IMAGE;

public class MainController implements Initializable {
    @FXML
    public Button resetButton;
    private boolean[] used;
    private ImageView[] images;
    private Board board;
    @FXML
    private Label titleLabel;
    @FXML
    private GridPane gameBoard;
    @FXML
    private MenuBar menuBar;
    private Movement move;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        var size = 9;

        titleLabel.setText("Tic-Tac-Toe");
        initMenuBar();
        gridInit(size);
        move = new Movement();

        gameBoard.setOnMouseClicked(event -> {
            var source = (Node) event.getTarget();
            if (!(source instanceof ImageView)){
                return;
            }
            var index = board.getSideLength() * GridPane.getRowIndex(source) + GridPane.getColumnIndex(source);

            if (used[index] && !Results.ifGameIsOver(board)){
                Alerts.popWrongPositionAlert();
                return;
            }
            if (used[index] && Results.ifGameIsOver(board)){
                Alerts.popGameOverAlert();
                return;
            }

            move.makeUserMove(images, index, board, used);
            Results.checkIfResulted(board, used);

            if (!move.getMode() && !Results.ifGameIsOver(board)) {
                move.makeComputerMove(board, images, used);
                Results.checkIfResulted(board, used);
            }
        });
    }

    public void gridInit(int length){
        used = new boolean[length];
        board = new Board(length);
        var sideLength = board.getSideLength();
        images = new ImageView[length];

        gameBoard.getChildren().clear();
        gameBoard.getRowConstraints().clear();
        gameBoard.getColumnConstraints().clear();
        gameBoard.setGridLinesVisible(false);

        for (int i = 0; i < sideLength; i++){
            var col = new ColumnConstraints();
            col.setHgrow(Priority.ALWAYS);
            gameBoard.getColumnConstraints().add(i, col);

            var row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            gameBoard.getRowConstraints().add(i, row);
        }

        for (int i = 0; i < images.length; i++){
            images[i] = new ImageView();
            images[i].setFitHeight(gameBoard.getPrefHeight() / sideLength);
            images[i].setFitWidth(gameBoard.getPrefWidth() / sideLength);
            images[i].setImage(BLANK_IMAGE);
            gameBoard.add(images[i], i % sideLength,i / sideLength);
        }
        gameBoard.setGridLinesVisible(true);
    }

    public void initMenuBar(){
        var bigMenu = new Menu();
        bigMenu.setText("GridSize");

        for (int i = 0; i < 8; i++){
            var menu = new MenuItem();
            final var index = i + 3;

            menu.setText(index + "x" + index);
            menu.setOnAction(event -> {
                gridInit((index) * (index));
            });
            bigMenu.getItems().add(menu);
        }

        var mode = new Menu("Mode");
        var manualMode = new MenuItem("Manual");
        manualMode.setOnAction(event -> {
            move.setMode(MANUAL_MODE);
            gridInit(board.getPositions().length);
        });

        var automateMode = new MenuItem("Automate");
        automateMode.setOnAction(event -> {
            move.setMode(AUTOMATE_MODE);
            gridInit(board.getPositions().length);
        });
        mode.getItems().addAll(manualMode, automateMode);

        menuBar.getMenus().get(1).getItems().get(0).setOnAction(event -> {
            Alerts.popAboutWindow();
        });
        menuBar.getMenus().get(0).getItems().clear();
        menuBar.getMenus().get(0).getItems().addAll(bigMenu, mode);
    }

    public void resetBoard(ActionEvent actionEvent) {
        for (int i = 0; i < used.length; i++){
            used[i] = false;
            images[i].setImage(BLANK_IMAGE);
            board.resetPosition(i);
        }
    }
}
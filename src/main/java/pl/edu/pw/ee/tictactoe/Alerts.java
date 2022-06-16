package pl.edu.pw.ee.tictactoe;

import javafx.scene.control.Alert;

public class Alerts {
    private static final String WARNING = "Warning!";

    private Alerts(){}

    public static void pupWrongPositionAlert(){
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(WARNING);
        alert.setHeaderText("Wrong position");
        alert.setContentText("This place was already taken! Choose another one!");
        alert.show();
    }

    public static void popGameOverAlert(){
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(WARNING);
        alert.setHeaderText("The Game is already over.");
        alert.setContentText("Please reset the board to play again!");
        alert.show();
    }
}

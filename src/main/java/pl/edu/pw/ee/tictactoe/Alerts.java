package pl.edu.pw.ee.tictactoe;

import javafx.scene.control.Alert;

public class Alerts {
    private static final String WARNING = "Warning!";

    private Alerts(){}

    public static void popWrongPositionAlert(){
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(WARNING);
        alert.setHeaderText("Wrong position");
        alert.setContentText("This place was already taken! Choose another one!");
        alert.showAndWait();
    }

    public static void popGameOverAlert(){
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(WARNING);
        alert.setHeaderText("The Game is already over.");
        alert.setContentText("Please reset the board to play again!");
        alert.showAndWait();
    }

    public static void popAboutWindow(){
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Szymon Półtorak");
        alert.setContentText("Github: https://github.com/szymonpoltorak");
        alert.showAndWait();
    }
}

module program.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens program.tictactoe to javafx.fxml;
    exports program.tictactoe;
}
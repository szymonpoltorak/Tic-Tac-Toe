module program.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens program.tictactoe to javafx.fxml;
    exports program.tictactoe;
}
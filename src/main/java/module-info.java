module program.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires java.desktop;

    opens pl.edu.pw.ee.tictactoe to javafx.fxml;
    exports pl.edu.pw.ee.tictactoe;
}
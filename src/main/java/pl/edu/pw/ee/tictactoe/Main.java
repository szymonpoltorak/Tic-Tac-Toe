package pl.edu.pw.ee.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        int width = 800;
        int height = 700;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("img/icon.png")).toString()));
        stage.setTitle("Tic-Tac-Toe");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
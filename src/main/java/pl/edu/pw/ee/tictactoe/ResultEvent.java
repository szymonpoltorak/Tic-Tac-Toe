package pl.edu.pw.ee.tictactoe;

import javafx.scene.control.Alert;
import org.jetbrains.annotations.NotNull;

public class ResultEvent {
    private static final String GAME_RESULT = "Game Result";

    private ResultEvent(){}

    public static void drawResult(@NotNull Alert info){
        info.setTitle(GAME_RESULT);
        info.setHeaderText("DRAW!");
        info.setContentText("Your game has ended with draw. Try to win next time!");
        info.showAndWait();
    }

    public static void winResult(@NotNull Alert info){
        info.setTitle(GAME_RESULT);
        info.setHeaderText("YOU WON!");
        info.setContentText("Your game has ended with draw. Congratulations! Another game ?");
        info.showAndWait();
    }

    public static void loseResult(@NotNull Alert info){
        info.setTitle(GAME_RESULT);
        info.setHeaderText("YOU LOST");
        info.setContentText("Your game has ended with loss. Try to win next time!");
        info.showAndWait();
    }
}

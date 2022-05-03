package program.tictactoe;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

public class ResultEvent {
    public static void drawResult(@NotNull Alert info, @NotNull Label resultLabel){
        info.setTitle("Game Result");
        info.setHeaderText("DRAW!");
        info.setContentText("Your game has ended with draw. Try to win next time!");
        info.show();
        resultLabel.setFont(new Font(40));
        resultLabel.setText("DRAW!");
        resultLabel.setTextFill(Color.DARKGRAY);
    }

    public static void winResult(@NotNull Alert info, @NotNull Label resultLabel){
        info.setTitle("Game Result");
        info.setHeaderText("YOU WON!");
        info.setContentText("Your game has ended with draw. Congratulations! Another game ?");
        info.show();
        resultLabel.setFont(new Font(40));
        resultLabel.setText("YOU WON!");
        resultLabel.setTextFill(Color.GREEN);
    }

    public static void loseResult(@NotNull Alert info, @NotNull Label resultLabel){
        info.setTitle("Game Result");
        info.setHeaderText("YOU LOST");
        info.setContentText("Your game has ended with loss. Try to win next time!");
        info.show();
        resultLabel.setFont(new Font(40));
        resultLabel.setText("YOU LOST");
        resultLabel.setTextFill(Color.RED);
    }
}

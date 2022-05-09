package pl.edu.pw.ee.tictactoe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Moves {
    public static void makeUserMove(ImageView @NotNull [] images, int index, @NotNull Board board, boolean @NotNull [] used){
        Image circle = new Image(Objects.requireNonNull(MainController.class.getResource("img/circle.png")).toString());
        images[index].setImage(circle);
        board.setPosition(index, Constants.CIRCLE);
        used[index] = true;
    }

    public static void makeComputerMove(Board board, ImageView[] images, boolean[] used){
        Image cross = new Image(Objects.requireNonNull(MainController.class.getResource("img/cross.png")).toString());
        int bestIndex = Minimax.getBestMove(board, Constants.CROSS);

        if (bestIndex == -1 || used[bestIndex]){
            return;
        }

        images[bestIndex].setImage(cross);
        board.setPosition(bestIndex, Constants.CROSS);
        used[bestIndex] = true;
    }
}

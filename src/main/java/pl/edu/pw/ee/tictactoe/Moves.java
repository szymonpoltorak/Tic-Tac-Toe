package pl.edu.pw.ee.tictactoe;

import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import static pl.edu.pw.ee.tictactoe.Images.CIRCLE_IMAGE;
import static pl.edu.pw.ee.tictactoe.Images.CROSS_IMAGE;

public class Moves {
    private Moves(){}

    public static void makeUserMove(ImageView @NotNull [] images, int index, @NotNull Board board, boolean @NotNull [] used){
        images[index].setImage(CIRCLE_IMAGE);
        board.setPosition(index, Constants.CIRCLE);
        used[index] = true;
    }

    public static void makeComputerMove(Board board, ImageView[] images, boolean[] used){
        var bestIndex = Minimax.getBestMove(board, Constants.CROSS);

        if (bestIndex == -1 || used[bestIndex]){
            return;
        }

        images[bestIndex].setImage(CROSS_IMAGE);
        board.setPosition(bestIndex, Constants.CROSS);
        used[bestIndex] = true;
    }
}

package program.tictactoe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

import static program.tictactoe.Constants.*;

public class Moves {
    public static void makeUserMove(ImageView[] images, int index, Board board, boolean[] used){
        Image circle = new Image(Objects.requireNonNull(MainController.class.getResource("img/circle.png")).toString());
        images[index].setImage(circle);
        board.setPosition(index, CIRCLE);
        used[index] = true;
    }

    public static void makeComputerMove(Board board, ImageView[] images, boolean[] used){
        Image cross = new Image(Objects.requireNonNull(MainController.class.getResource("img/cross.png")).toString());
        int bestIndex = Board.getBestMove(board, CROSS);

        if (bestIndex == -1 || used[bestIndex]){
            return;
        }

        images[bestIndex].setImage(cross);
        board.setPosition(bestIndex, CROSS);
        used[bestIndex] = true;
    }
}

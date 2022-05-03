package program.tictactoe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static program.tictactoe.Constants.*;

public class Moves {
    public static void makeUserMove(Image circle, ImageView[] images, int index, Board board, boolean[] used){
        images[index].setImage(circle);
        board.setPosition(index, CIRCLE);
        used[index] = true;
    }

    public static void makeComputerMove(Board board, ImageView[] images, Image cross, boolean[] used){
        int bestIndex = Board.getBestMove(board, CROSS);

        if (used[bestIndex]){
            return;
        }

        images[bestIndex].setImage(cross);
        board.setPosition(bestIndex, CROSS);
        used[bestIndex] = true;
    }
}

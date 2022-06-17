package pl.edu.pw.ee.tictactoe;

import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import static pl.edu.pw.ee.tictactoe.Constants.*;
import static pl.edu.pw.ee.tictactoe.Images.CIRCLE_IMAGE;
import static pl.edu.pw.ee.tictactoe.Images.CROSS_IMAGE;

public class Movement {
    private boolean mode;
    private boolean currentPlayer;

    public Movement(){
        this.mode = AUTOMATE_MODE;
        this.currentPlayer = CIRCLE_PLAYER;
    }

    public void makeUserMove(ImageView @NotNull [] images, int index, @NotNull Board board, boolean @NotNull [] used){
        if (mode == AUTOMATE_MODE) {
            images[index].setImage(CIRCLE_IMAGE);
            board.setPosition(index, CIRCLE);
            used[index] = true;
        } else {
            images[index].setImage(currentPlayer == CIRCLE_PLAYER ? CIRCLE_IMAGE : CROSS_IMAGE);
            board.setPosition(index, currentPlayer == CIRCLE_PLAYER ? CIRCLE : CROSS);
            used[index] = true;
            currentPlayer = (currentPlayer == CIRCLE_PLAYER ? CROSS_PLAYER : CIRCLE_PLAYER);
        }
    }

    public void setMode(boolean mode){
        this.mode = mode;
    }

    public boolean getMode(){
        return mode;
    }

    public void makeComputerMove(Board board, ImageView[] images, boolean[] used){
        var bestIndex = Minimax.getBestMove(board, CROSS);

        if (bestIndex == -1 || used[bestIndex]){
            return;
        }

        images[bestIndex].setImage(CROSS_IMAGE);
        board.setPosition(bestIndex, CROSS);
        used[bestIndex] = true;
    }
}

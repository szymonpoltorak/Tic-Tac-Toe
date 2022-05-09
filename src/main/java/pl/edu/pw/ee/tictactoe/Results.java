package pl.edu.pw.ee.tictactoe;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

public class Results {
    private Results(){}

    public static void checkIfResulted(Board board, boolean[] used, Label resultLabel){
        int result = Results.isResulted(board);

        if (result > 0){
            for (int i = 0; i < used.length; i++){
                if (!used[i]){
                    used[i] = true;
                }
            }
        }
        Alert info = new Alert(Alert.AlertType.INFORMATION);

        if (result == 0 && ifGameIsOver(board)){
            ResultEvent.drawResult(info, resultLabel);
        }
        if (result == Constants.CROSS && ifGameIsOver(board)){
            ResultEvent.loseResult(info, resultLabel);
        }
        if (result == Constants.CIRCLE && ifGameIsOver(board)){
            ResultEvent.winResult(info, resultLabel);
        }
    }

    public static int isResulted(Board board){
        columns:
        for (int i = 0; i < 3; i++) {
            int columnPlayer = board.getPosition(i);

            if (columnPlayer == 0){
                continue;
            }

            for (int j = i; j < 9; j += 3) {
                if (columnPlayer != board.getPosition(j)){
                    continue columns;
                }
            }
            return columnPlayer;
        }

        rows:
        for (int i = 0; i < 9; i += 3) {
            int rowsPlayer = board.getPosition(i);

            if (rowsPlayer == 0){
                continue;
            }

            for (int j = i; j < i + 3; j++) {
                if (rowsPlayer != board.getPosition(j)){
                    continue rows;
                }
            }
            return rowsPlayer;
        }

        int leftDiagonalPlayer = board.getPosition(0);
        int sameValue = 1;

        for (int d = 4; d < 9 && leftDiagonalPlayer != 0; d += 4){
            if (leftDiagonalPlayer != board.getPosition(d)){
                break;
            }
            sameValue++;
        }

        if (sameValue == 3){
            return leftDiagonalPlayer;
        }

        int rightDiagonalPlayer = board.getPosition(2);
        sameValue = 1;

        for (int d = 4; d < 7 && rightDiagonalPlayer != 0; d += 2){
            if (rightDiagonalPlayer != board.getPosition(d)){
                break;
            }
            sameValue++;
        }

        if (sameValue == 3){
            return rightDiagonalPlayer;
        }

        return 0;
    }

    public static boolean isGameBoardFull(@NotNull Board board){
        for (int position : board.getPositions()){
            if (position == 0){
                return false;
            }
        }
        return true;
    }

    public static boolean ifGameIsOver(Board board){
        return isGameBoardFull(board) || isResulted(board) != 0;
    }
}

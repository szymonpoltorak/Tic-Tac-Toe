package pl.edu.pw.ee.tictactoe;

import javafx.scene.control.Alert;
import org.jetbrains.annotations.NotNull;

public class Results {
    private Results(){}

    public static void checkIfResulted(Board board, boolean[] used){
        var result = Results.isResulted(board);

        if (result > 0){
            for (int i = 0; i < used.length; i++){
                if (!used[i]){
                    used[i] = true;
                }
            }
        }
        var info = new Alert(Alert.AlertType.INFORMATION);

        if (result == 0 && ifGameIsOver(board)){
            ResultEvent.drawResult(info);
        }
        if (result == Constants.CROSS && ifGameIsOver(board)){
            ResultEvent.loseResult(info);
        }
        if (result == Constants.CIRCLE && ifGameIsOver(board)){
            ResultEvent.winResult(info);
        }
    }

    public static int isResulted(Board board){
        var result = 0;

        if ((result = Results.checkColumns(board)) != -1){
            return result;
        }

        if ((result = Results.checkRows(board)) != -1){
            return result;
        }

        if ((result = Results.checkLeftDiagonal(board)) != -1){
            return result;
        }

        if ((result = Results.checkRightDiagonal(board)) != -1){
            return result;
        }

        return 0;
    }

    public static int checkRightDiagonal(@NotNull Board board){
        int rightDiagonalPlayer = board.getPosition(2);
        int sameValue = 1;

        for (int d = 4; d < 7 && rightDiagonalPlayer != 0; d += 2){
            if (rightDiagonalPlayer != board.getPosition(d)){
                break;
            }
            sameValue++;
        }

        if (sameValue == 3){
            return rightDiagonalPlayer;
        }

        return -1;
    }

    public static int checkLeftDiagonal(@NotNull Board board){
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

        return -1;
    }

    public static int checkRows(@NotNull Board board){
        for (int i = 0; i < 9; i += 3) {
            var rowsPlayer = board.getPosition(i);
            var temp = -1;

            for (int j = i; j < i + 3; j++) {
                if (rowsPlayer != board.getPosition(j) || rowsPlayer == 0){
                    temp++;
                    break;
                }
            }
            if (temp == -1) {
                return rowsPlayer;
            }
        }
        return -1;
    }

    public static int checkColumns(@NotNull Board board){
        for (int i = 0; i < 3; i++) {
            var columnPlayer = board.getPosition(i);
            var temp = -1;

            for (int j = i; j < 9; j += 3) {
                if (columnPlayer != board.getPosition(j) || columnPlayer == 0){
                    temp++;
                    break;
                }
            }
            if (temp == -1) {
                return columnPlayer;
            }
        }
        return -1;
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

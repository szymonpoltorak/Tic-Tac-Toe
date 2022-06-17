package pl.edu.pw.ee.tictactoe;

import javafx.scene.control.Alert;
import org.jetbrains.annotations.NotNull;

public class Results {
    private Results(){}

    public static void checkIfResulted(Board board, boolean @NotNull [] used){
        var result = Results.isResulted(board);

        for (int i = 0; i < used.length && result > 0; i++){
            if (!used[i]){
                used[i] = true;
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
        var rowLength = board.getSideLength();
        var step = rowLength - 1;
        var rightDiagonalPlayer = board.getPosition(step);
        var sameValue = 1;
        var iterations = rowLength * (rowLength - 1) + 1;

        for (int d = 2 * step; d < iterations && rightDiagonalPlayer != 0; d += step){
            if (rightDiagonalPlayer != board.getPosition(d)){
                break;
            }
            sameValue++;
        }
        return sameValue == rowLength ? rightDiagonalPlayer : -1;
    }

    public static int checkLeftDiagonal(@NotNull Board board){
        var rowLength = board.getSideLength();
        var step = rowLength + 1;
        var leftDiagonalPlayer = board.getPosition(0);
        var sameValue = 1;

        for (int d = step; d < board.getPositions().length && leftDiagonalPlayer != 0; d += step){
            if (leftDiagonalPlayer != board.getPosition(d)){
                break;
            }
            sameValue++;
        }
        return sameValue == rowLength ? leftDiagonalPlayer : -1;
    }

    public static int checkRows(@NotNull Board board){
        var rowLength = board.getSideLength();

        for (int i = 0; i < board.getPositions().length; i += rowLength) {
            var rowsPlayer = board.getPosition(i);
            var temp = -1;

            for (int j = i; j < i + rowLength; j++) {
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
        var rowLength = board.getSideLength();

        for (int i = 0; i < rowLength; i++) {
            var columnPlayer = board.getPosition(i);
            var temp = -1;

            for (int j = i; j < board.getPositions().length; j += rowLength) {
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

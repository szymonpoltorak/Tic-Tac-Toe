package pl.edu.pw.ee.tictactoe;

import org.jetbrains.annotations.NotNull;

import static pl.edu.pw.ee.tictactoe.Constants.*;

public class Evaluation {
    private static final int BASE_EVAL = 5;

    private Evaluation(){}

    public static float evaluatePosition(Board board){
        return evaluateRows(board) + evaluateColumns(board) + evaluateRightDiagonal(board) + evaluateLeftDiagonal(board);
    }

    public static float evaluateRows(@NotNull Board board){
        var rowEvaluation = 0;
        var rowSize = (int) Math.sqrt(board.getPositions().length);

        for (int i = 0; i < board.getPositions().length; i += rowSize){
            var computer = 0;
            var player = 0;

            for (int j = i; j < i + rowSize; j++) {
                if (board.getPosition(j) == CIRCLE){
                    player++;
                }
                if (board.getPosition(j) == CROSS){
                    computer++;
                }
            }
            rowEvaluation += Evaluation.countEvaluation(player, computer);
        }
        return rowEvaluation;
    }

    public static float evaluateColumns(@NotNull Board board){
        var columnSize = (int) Math.sqrt(board.getPositions().length);
        var columnEvaluation = 0;

        for (int i = 0; i < columnSize; i++){
            var computer = 0;
            var player = 0;

            for (int j = i; j < board.getPositions().length; j += columnSize){
                if (board.getPosition(j) == CIRCLE){
                    player++;
                }
                if (board.getPosition(j) == CROSS){
                    computer++;
                }
            }
            columnEvaluation += Evaluation.countEvaluation(player, computer);
        }
        return columnEvaluation;
    }

    public static float evaluateLeftDiagonal(@NotNull Board board){
        var sideLength = (int) Math.sqrt(board.getPositions().length);
        var step = sideLength + 1;
        var computer = 0;
        var player = 0;

        for (int i = 0; i < board.getPositions().length; i += step){
            if (board.getPosition(i) == CIRCLE){
                player++;
            }
            if (board.getPosition(i) == CROSS){
                computer++;
            }
        }
        return Evaluation.countEvaluation(player, computer);
    }

    public static float evaluateRightDiagonal(@NotNull Board board){
        var sideLength = (int) Math.sqrt(board.getPositions().length);
        var step = sideLength - 1;
        var computer = 0;
        var player = 0;
        var iterations = sideLength * (sideLength - 1) + 1;

        for (int i = step; i < iterations; i += step){
            if (board.getPosition(i) == CIRCLE){
                player++;
            }
            if (board.getPosition(i) == CROSS){
                computer++;
            }
        }
        return Evaluation.countEvaluation(player, computer);
    }

    private static float countEvaluation(int player, int computer){
        return (float) Math.pow(BASE_EVAL, computer) - (float) Math.pow(BASE_EVAL, player);
    }
}

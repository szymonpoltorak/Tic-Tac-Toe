package pl.edu.pw.ee.tictactoe;

import org.jetbrains.annotations.NotNull;

import static pl.edu.pw.ee.tictactoe.Constants.*;

public class Evaluation implements Eval {
    private static final int BASE_EVAL = 5;
    private final int sideLength;

    public Evaluation(int sideLength){
        this.sideLength = sideLength;
    }

    public float evaluatePosition(Board board){
        return evaluateRows(board) + evaluateColumns(board) + evaluateRightDiagonal(board) + evaluateLeftDiagonal(board);
    }

    public float evaluateRows(@NotNull Board board){
        var rowEvaluation = 0;

        for (int i = 0; i < board.getPositions().length; i += sideLength){
            var computer = 0;
            var player = 0;

            for (int j = i; j < i + sideLength; j++) {
                if (board.getPosition(j) == CIRCLE){
                    player++;
                }
                if (board.getPosition(j) == CROSS){
                    computer++;
                }
            }
            rowEvaluation += countEvaluation(player, computer);
        }
        return rowEvaluation;
    }

    public float evaluateColumns(@NotNull Board board){
        var columnEvaluation = 0;

        for (int i = 0; i < sideLength; i++){
            var computer = 0;
            var player = 0;

            for (int j = i; j < board.getPositions().length; j += sideLength){
                if (board.getPosition(j) == CIRCLE){
                    player++;
                }
                if (board.getPosition(j) == CROSS){
                    computer++;
                }
            }
            columnEvaluation += countEvaluation(player, computer);
        }
        return columnEvaluation;
    }

    public float evaluateLeftDiagonal(@NotNull Board board){
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
        return countEvaluation(player, computer);
    }

    public float evaluateRightDiagonal(@NotNull Board board){
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
        return countEvaluation(player, computer);
    }

    private float countEvaluation(int player, int computer){
        return (float) Math.pow(BASE_EVAL, computer) - (float) Math.pow(BASE_EVAL, player);
    }
}

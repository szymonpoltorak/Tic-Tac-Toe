package pl.edu.pw.ee.tictactoe;

import org.jetbrains.annotations.NotNull;

import static pl.edu.pw.ee.tictactoe.Constants.*;

public class Minimax {
    private Minimax(){}

    public static float minimax(Board board, int depth, float alpha, float beta, boolean maximizingPlayer){
        if (depth == 0 || Results.ifGameIsOver(board)){
            return Minimax.evaluatePosition(board, maximizingPlayer);
        }

        if (maximizingPlayer == CIRCLE_PLAYER){
            var maxEval = -Float.MAX_VALUE;

            for (Board child : board.makeChildren(CROSS)){
                var movementEval = minimax(child, depth - 1, alpha, beta, CROSS_PLAYER);
                maxEval = Math.max(maxEval, movementEval);
                alpha = Math.max(alpha, movementEval);

                if (beta <= alpha){
                    break;
                }
            }
            return maxEval;
        }
        else if (maximizingPlayer == CROSS_PLAYER) {
            var minEval = Float.MAX_VALUE;

            for (Board child : board.makeChildren(CIRCLE)){
                var movementEval = minimax(child, depth - 1, alpha, beta, CIRCLE_PLAYER);
                minEval = Math.min(minEval, movementEval);
                beta = Math.min(beta, movementEval);

                if (beta <= alpha){
                    break;
                }
            }
            return minEval;
        }
        throw new IllegalStateException("No idea how i got here");
    }

    public static float evaluatePosition(Board board, boolean maximizingPlayer){
        var winner = Results.isResulted(board);

        if (winner == CROSS){
            return maximizingPlayer == CROSS_PLAYER ? Float.MAX_VALUE : -Float.MAX_VALUE;
        }
        else if (winner == CIRCLE){
            return maximizingPlayer == CIRCLE_PLAYER ? -Float.MAX_VALUE : Float.MAX_VALUE;
        }

        return 0;
    }

    public static int getBestMove(@NotNull Board board, int player) {
        var max = -Float.MAX_VALUE;
        var alpha = -Float.MAX_VALUE;
        var beta = Float.MAX_VALUE;
        var bestPosition = -1;

        for (int i = 0; i < board.getPositions().length; i++) {
            if(board.getPosition(i) != 0) {
                continue;
            }

            var child = new Board(board);
            child.setPosition(i, player);
            var eval = Minimax.minimax(child, 9, alpha, beta, CROSS_PLAYER);

            if (max < eval) {
                max = eval;
                bestPosition = i;
            }
        }
        return bestPosition;
    }
}

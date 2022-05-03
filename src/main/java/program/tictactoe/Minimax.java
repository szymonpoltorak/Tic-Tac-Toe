package program.tictactoe;

import static program.tictactoe.Constants.*;

public class Minimax {
    public static float minimax(Board board, int depth, boolean maximizingPlayer){
        if (depth == 0 || Results.ifGameIsOver(board)){
            return Minimax.evaluatePosition(board, maximizingPlayer);
        }

        if (maximizingPlayer == CIRCLE_PLAYER){
            float maxEval = -Float.MAX_VALUE;

            for (Board child : board.makeChildren(CROSS)){
                float movementEval = minimax(child, depth - 1, CROSS_PLAYER);
                maxEval = Math.max(maxEval, movementEval);
            }
            return maxEval;
        }
        else if (maximizingPlayer == CROSS_PLAYER) {
            float minEval = Float.MAX_VALUE;

            for (Board child : board.makeChildren(CIRCLE)){
                float movementEval = minimax(child, depth - 1, CIRCLE_PLAYER);
                minEval = Math.min(minEval, movementEval);
            }
            return minEval;
        }
        throw new IllegalStateException("No idea how i got here");
    }

    public static float evaluatePosition(Board board, boolean maximizingPlayer){
        int winner = Results.isResulted(board);

        if (winner == CROSS){
            return maximizingPlayer == CROSS_PLAYER ? Float.MAX_VALUE : -Float.MAX_VALUE;
        }
        else if (winner == CIRCLE){
            return maximizingPlayer == CIRCLE_PLAYER ? -Float.MAX_VALUE : Float.MAX_VALUE;
        }

        if (!Results.ifGameIsOver(board)){
            throw new IllegalStateException("Winner: " + winner + " ifGameIsOver: " + Results.ifGameIsOver(board));
        }
        return 0;
    }
}

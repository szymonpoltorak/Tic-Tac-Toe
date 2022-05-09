package pl.edu.pw.ee.tictactoe;

import org.jetbrains.annotations.NotNull;

public class Minimax {
    private Minimax(){}

    public static float minimax(Board board, int depth, float alpha, float beta, boolean maximizingPlayer){
        if (depth == 0 || Results.ifGameIsOver(board)){
            return Minimax.evaluatePosition(board, maximizingPlayer);
        }

        if (maximizingPlayer == Constants.CIRCLE_PLAYER){
            float maxEval = -Float.MAX_VALUE;

            for (Board child : board.makeChildren(Constants.CROSS)){
                float movementEval = minimax(child, depth - 1, alpha, beta, Constants.CROSS_PLAYER);
                maxEval = Math.max(maxEval, movementEval);
                alpha = Math.max(alpha, movementEval);

                if (beta <= alpha){
                    break;
                }
            }
            return maxEval;
        }
        else if (maximizingPlayer == Constants.CROSS_PLAYER) {
            float minEval = Float.MAX_VALUE;

            for (Board child : board.makeChildren(Constants.CIRCLE)){
                float movementEval = minimax(child, depth - 1, alpha, beta, Constants.CIRCLE_PLAYER);
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
        int winner = Results.isResulted(board);

        if (winner == Constants.CROSS){
            return maximizingPlayer == Constants.CROSS_PLAYER ? Float.MAX_VALUE : -Float.MAX_VALUE;
        }
        else if (winner == Constants.CIRCLE){
            return maximizingPlayer == Constants.CIRCLE_PLAYER ? -Float.MAX_VALUE : Float.MAX_VALUE;
        }

        if (!Results.ifGameIsOver(board)){
            throw new IllegalStateException("Winner: " + winner + " ifGameIsOver: " + Results.ifGameIsOver(board));
        }
        return 0;
    }

    public static int getBestMove(@NotNull Board board, int player) {
        float max = -Float.MAX_VALUE;
        float alpha = -Float.MAX_VALUE;
        float beta = Float.MAX_VALUE;
        int bestPosition = -1;

        for (int i = 0; i < board.getPositions().length; i++) {
            if(board.getPosition(i) != 0) {
                continue;
            }

            Board child = new Board(board);
            child.setPosition(i, player);
            float eval = Minimax.minimax(child, 9, alpha, beta, Constants.CROSS_PLAYER);

            if (max < eval) {
                max = eval;
                bestPosition = i;
            }
        }
        return bestPosition;
    }
}

package program.tictactoe;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static program.tictactoe.Constants.*;

public class Board {
    private final int[] positions;

    public Board(){
        this.positions = new int[9];
    }

    public Board(@NotNull Board board){
        this.positions = new int[board.getPositions().length];
        System.arraycopy(board.getPositions(), 0, this.positions, 0, board.getPositions().length);
    }

    public int getPosition(int index) {
        return positions[index];
    }

    public int[] getPositions(){
        return positions;
    }

    public void resetPosition(int index){
        this.positions[index] = 0;
    }

    public void setPosition(int index, int player) {
        if (positions[index] != 0){
            throw new IllegalArgumentException("Position not found!");
        }

        positions[index] = player;
    }

    public static int getBestMove(@NotNull Board board, int player) {
        float max = -Float.MAX_VALUE;
        int bestPosition = -1;

        for (int i = 0; i < board.getPositions().length; i++) {
            if(board.getPosition(i) != 0) {
                continue;
            }

            Board child = new Board(board);
            child.setPosition(i, player);
            float eval = Minimax.minimax(child, 9, CROSS_PLAYER);

            if (max < eval) {
                max = eval;
                bestPosition = i;
            }
        }
        return bestPosition;
    }

    public Board[] makeChildren(int player){
        int blankSquares = 0;

        for (int position : positions) {
            if (position == 0) {
                blankSquares++;
            }
        }

        Board[] children = new Board[blankSquares];
        for (int i = 0, j = 0; i < positions.length; i++){
            if (positions[i] == 0){
                children[j] = new Board(this);
                children[j++].setPosition(i, player);
            }
        }

        return children;
    }

    @Contract(pure = true)
    private @NotNull String prettyPos(int i) {
        return positions[i] == 0 ? " " : (positions[i] == CIRCLE ? "O" : "X");
    }

    @Override
    public String toString() {
        return  "[ "+prettyPos(0)+" | "+prettyPos(1)+" | "+prettyPos(2)+" ]\n" +
                "-------------\n" +
                "[ "+prettyPos(3)+" | "+prettyPos(4)+" | "+prettyPos(5)+" ]\n" +
                "-------------\n" +
                "[ "+prettyPos(6)+" | "+prettyPos(7)+" | "+prettyPos(8)+" ]\n";
    }
}
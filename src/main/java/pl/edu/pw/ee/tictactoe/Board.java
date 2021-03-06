package pl.edu.pw.ee.tictactoe;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Board {
    private final int[] positions;
    private final int sideLength;

    public Board(int size){
        this.positions = new int[size];
        this.sideLength = (int) Math.sqrt(size);
    }

    public Board(@NotNull Board board){
        this.positions = new int[board.getPositions().length];
        this.sideLength = board.getSideLength();
        System.arraycopy(board.getPositions(), 0, this.positions, 0, board.getPositions().length);
    }

    public int getPosition(int index) {
        return positions[index];
    }

    public int getSideLength(){
        return sideLength;
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

    public Board[] makeChildren(int player){
        var blankSquares = 0;

        for (int position : positions) {
            if (position == 0) {
                blankSquares++;
            }
        }

        var children = new Board[blankSquares];
        var j = 0;
        for (int i = 0; i < positions.length; i++){
            if (positions[i] == 0){
                children[j] = new Board(this);
                children[j++].setPosition(i, player);
            }
        }
        return children;
    }

    @Contract(pure = true)
    private @NotNull String prettyPos(int i) {
        if (positions[i] == 0){
            return " ";
        }
        return positions[i] == Constants.CIRCLE ? "O" : "X";
    }

    @Override
    public String toString() {
        return  "[ "+prettyPos(0)+" | "+prettyPos(1)+" | "+prettyPos(2)+" ]\n" +
                "-------------\n" +
                "[ "+prettyPos(3)+" | "+prettyPos(4)+" | "+prettyPos(5)+" ]\n" +
                "-------------\n" +
                "[ "+prettyPos(6)+" | "+prettyPos(7)+" | "+prettyPos(8)+" ]\n";
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(positions);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        var board = (Board) object;

        return Arrays.equals(positions, board.positions);
    }
}
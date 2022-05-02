package program.tictactoe;

public class Board {
    private int[] positions;

    public Board(){
        this.positions = new int[9];

        for (int i = 0; i < 9; i++){
            positions[i] = -1;
        }
    }

    public Board(Board board){
        this.positions = new int[9];
        System.arraycopy(board.getPositions(), 0, this.positions, 0, 9);
    }

    public int getPosition(int index) {
        return positions[index];
    }

    public int[] getPositions(){
        return positions;
    }

    public void setPosition(int index, int player) {
        if (positions[index] != 0){
            throw new IllegalArgumentException("Position not found!");
        }

        positions[index] = player;
    }

    public Board[] makeChildren(int player){ //TODO dodac poprawne tworzenie dzieci
        int blankSquares = 0;

        for (int position : positions) {
            if (position == -1) {
                blankSquares++;
            }
        }

        Board[] children = new Board[blankSquares];
        for (int i = 0, j = 0; i < positions.length; i++){
            if (positions[i] == 0){
                children[++j] = new Board(this);
                children[j].setPosition(i, player);
            }
        }

        return children;
    }
}
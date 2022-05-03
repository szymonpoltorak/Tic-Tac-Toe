package program.tictactoe;

public class Results {
    public static void checkIfResulted(Board board, boolean[] used){
        if (Results.isResulted(board) > 0){ //TODO poprawić tę funkcję aby wyświetlała wynik
            for (int i = 0; i < used.length; i++){
                if (!used[i]){
                    used[i] = true;
                }
            }
        }
    }

    public static int isResulted(Board board){
        columns:
        for (int i = 0; i < 3; i++) {
            int columnPlayer = board.getPosition(i);

            if (columnPlayer == 0){
                continue;
            }

            for (int j = i; j < 9; j += 3) {
                if (columnPlayer != board.getPosition(j)){
                    continue columns;
                }
            }

            return columnPlayer;
        }

        rows:
        for (int i = 0; i < 9; i += 3) {
            int rowsPlayer = board.getPosition(i);

            if (rowsPlayer == 0){
                continue;
            }

            for (int j = i; j < i + 3; j++) {
                if (rowsPlayer != board.getPosition(j)){
                    continue rows;
                }
            }
            return rowsPlayer;
        }

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

        int rightDiagonalPlayer = board.getPosition(2);
        sameValue = 1;

        for (int d = 4; d < 7 && rightDiagonalPlayer != 0; d += 2){
            if (rightDiagonalPlayer != board.getPosition(d)){
                break;
            }
            sameValue++;
        }

        if (sameValue == 3){
            return rightDiagonalPlayer;
        }

        return 0;
    }

    public static boolean ifGameIsOver(Board board){
        if (Results.isResulted(board) == 0){
            return false;
        }

        for (int position : board.getPositions()){
            if (position == 0){
                return false;
            }
        }
        return true;
    }
}

package program.tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static program.tictactoe.Constants.CIRCLE;
import static program.tictactoe.Constants.CROSS;

public class ResultsTest {
    private Board board;

    @BeforeEach
    public void makeBoard(){
        this.board = new Board();
    }

    @Test
    public void isGameBoardFull_return_false(){
        //given
        boolean expected = false;

        //when
        boolean result = Results.isGameBoardFull(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void isGameBoardFull_return_true(){
        //given
        boolean expected = true;

        for (int i = 0; i < board.getPositions().length; i++){
            board.setPosition(i, CIRCLE);
        }

        //when
        boolean result = Results.isGameBoardFull(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void isResulted_row_player_wins(){
        //given
        int expected = CROSS;
        board.setPosition(0, CROSS);
        board.setPosition(1, CROSS);
        board.setPosition(2, CROSS);

        //when
        int result = Results.isResulted(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void isResulted_column_player_wins(){
        //given
        int expected = CIRCLE;
        board.setPosition(0, CIRCLE);
        board.setPosition(3, CIRCLE);
        board.setPosition(6, CIRCLE);

        //when
        int result = Results.isResulted(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void isResulted_left_diagonal_player_wins(){
        //given
        int expected = CROSS;
        board.setPosition(0, CROSS);
        board.setPosition(4, CROSS);
        board.setPosition(8, CROSS);

        //when
        int result = Results.isResulted(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void isResulted_right_diagonal_player_wins(){
        //given
        int expected = CIRCLE;
        board.setPosition(2, CIRCLE);
        board.setPosition(4, CIRCLE);
        board.setPosition(6, CIRCLE);

        //when
        int result = Results.isResulted(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void ifGameIsOver_true_one_player_won(){
        //given
        boolean expected = true;
        board.setPosition(2, CIRCLE);
        board.setPosition(4, CIRCLE);
        board.setPosition(6, CIRCLE);

        //when
        boolean result = Results.ifGameIsOver(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void ifGameIsOver_true_board_is_full(){
        //given
        boolean expected = true;
        for (int i = 0; i < board.getPositions().length; i++){
            board.setPosition(i, CIRCLE);
        }

        //when
        boolean result = Results.ifGameIsOver(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void ifGameIsOver_false(){
        //given
        boolean expected = false;

        //when
        boolean result = Results.ifGameIsOver(board);

        //then
        Assertions.assertEquals(expected, result);
    }
}

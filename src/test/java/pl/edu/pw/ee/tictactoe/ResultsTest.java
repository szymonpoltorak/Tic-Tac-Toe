package pl.edu.pw.ee.tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static pl.edu.pw.ee.tictactoe.Constants.CIRCLE;
import static pl.edu.pw.ee.tictactoe.Constants.CROSS;

class ResultsTest {
    private Board board;

    @BeforeEach
    public void makeBoard(){
        this.board = new Board(9);
    }

    @Test
    void isGameBoardFull_return_false(){
        //given
        var expected = false;

        //when
        boolean result = Results.isGameBoardFull(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void isGameBoardFull_return_true(){
        //given
        var expected = true;

        for (int i = 0; i < board.getPositions().length; i++){
            board.setPosition(i, CIRCLE);
        }

        //when
        boolean result = Results.isGameBoardFull(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void isResulted_row_player_wins(){
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
    void isResulted_column_player_wins(){
        //given
        var expected = CIRCLE;
        board.setPosition(0, CIRCLE);
        board.setPosition(3, CIRCLE);
        board.setPosition(6, CIRCLE);

        //when
        var result = Results.isResulted(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void isResulted_left_diagonal_player_wins(){
        //given
        var expected = CROSS;
        board.setPosition(0, CROSS);
        board.setPosition(4, CROSS);
        board.setPosition(8, CROSS);

        //when
        int result = Results.isResulted(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void isResulted_right_diagonal_player_wins(){
        //given
        var expected = CIRCLE;
        board.setPosition(2, CIRCLE);
        board.setPosition(4, CIRCLE);
        board.setPosition(6, CIRCLE);

        //when
        int result = Results.isResulted(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void ifGameIsOver_true_one_player_won(){
        //given
        var expected = true;
        board.setPosition(2, CIRCLE);
        board.setPosition(4, CIRCLE);
        board.setPosition(6, CIRCLE);

        //when
        var result = Results.ifGameIsOver(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void ifGameIsOver_true_board_is_full(){
        //given
        var expected = true;
        for (int i = 0; i < board.getPositions().length; i++){
            board.setPosition(i, CIRCLE);
        }

        //when
        var result = Results.ifGameIsOver(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void ifGameIsOver_false(){
        //given
        var expected = false;

        //when
        var result = Results.ifGameIsOver(board);

        //then
        Assertions.assertEquals(expected, result);
    }
}

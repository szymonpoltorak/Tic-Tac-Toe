package pl.edu.pw.ee.tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static pl.edu.pw.ee.tictactoe.Constants.CIRCLE;
import static pl.edu.pw.ee.tictactoe.Constants.CROSS;

class BoardTest {
    private Board board;

    @BeforeEach
    public void makeBoard(){
        board = new Board(9);
    }

    @Test
    void setPosition_exception(){
        //given
        int index = 3;

        //when
        board.setPosition(index, CROSS);

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.setPosition(index, CROSS));
    }

    @Test
    void board_constructor_copy_pass(){
        //given

        //when
        Board newBoard = new Board(board);

        //then
        Assertions.assertEquals(board, newBoard);
    }

    @Test
    void board_reset_position(){
        //given
        var expected = 0;
        board.setPosition(0, CIRCLE);

        //when
        board.resetPosition(0);
        var result = board.getPosition(0);

        //then
        Assertions.assertEquals(expected, result);
    }
}

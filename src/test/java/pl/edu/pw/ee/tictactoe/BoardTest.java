package pl.edu.pw.ee.tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}

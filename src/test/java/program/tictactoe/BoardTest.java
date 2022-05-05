package program.tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static program.tictactoe.Constants.CROSS;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void makeBoard(){
        board = new Board();
    }

    @Test
    public void setPosition_exception(){
        //given
        int index = 3;

        //when
        board.setPosition(index, CROSS);

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.setPosition(index, CROSS));
    }

    @Test
    public void board_constructor_copy_pass(){
        //given

        //when
        Board newBoard = new Board(board);

        //then
        Assertions.assertEquals(board, newBoard);
    }
}

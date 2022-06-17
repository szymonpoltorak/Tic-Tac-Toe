package pl.edu.pw.ee.tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static pl.edu.pw.ee.tictactoe.Constants.*;

class MinimaxTest {
    private Board board;

    @BeforeEach
    public void makeBoard(){
        this.board = new Board(9);
    }

    @Test
    void getBestMove_test(){
        //given
        var expected = 6;
        board.setPosition(0, CROSS);
        board.setPosition(4, CROSS);
        board.setPosition(1, CIRCLE);
        board.setPosition(2, CIRCLE);

        //when
        var result = Minimax.getBestMove(board, CROSS);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void count_depth_test(){
        //given
        var expected = Minimax.countDepth(board);

        //when
        var result = 10;

        //then
        Assertions.assertEquals(expected, result);
    }
}

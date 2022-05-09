package pl.edu.pw.ee.tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static pl.edu.pw.ee.tictactoe.Constants.*;

class MinimaxTest {
    private Board board;

    @BeforeEach
    public void makeBoard(){
        this.board = new Board();
    }

    @Test
    void getBestMove_test(){
        //given
        int expected = 3;
        board.setPosition(0, CROSS);
        board.setPosition(4, CROSS);
        board.setPosition(1, CIRCLE);
        board.setPosition(2, CIRCLE);

        //when
        int result = Minimax.getBestMove(board, CROSS);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void evaluatePosition_cross_player_wins(){
        //given
        float expected = Float.MAX_VALUE;
        board.setPosition(2, CROSS);
        board.setPosition(4, CROSS);
        board.setPosition(6, CROSS);

        //when
        float result = Minimax.evaluatePosition(board, CROSS_PLAYER);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void evaluatePosition_circle_player_wins(){
        //given
        float expected = Float.MAX_VALUE;
        board.setPosition(2, CIRCLE);
        board.setPosition(4, CIRCLE);
        board.setPosition(6, CIRCLE);

        //when
        float result = Minimax.evaluatePosition(board, CROSS_PLAYER);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void evaluatePosition_test_exception(){
        //given
        board.setPosition(2, CIRCLE);
        board.setPosition(4, CIRCLE);
        board.setPosition(6, CROSS);

        //when

        //then
        Assertions.assertThrows(IllegalStateException.class, () -> Minimax.evaluatePosition(board, CROSS_PLAYER));
    }
}

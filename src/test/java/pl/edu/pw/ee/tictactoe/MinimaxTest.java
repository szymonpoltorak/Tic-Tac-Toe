package pl.edu.pw.ee.tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static pl.edu.pw.ee.tictactoe.Constants.*;

class MinimaxTest {
    private Board board;
    private Eval eval;

    @BeforeEach
    public void makeBoard(){
        this.board = new Board(9);
        this.eval = new Evaluation(board.getSideLength());
    }

    @Test
    void getBestMove_test(){
        //given
        int expected = 6;
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
        float expected = 152;
        board.setPosition(2, CROSS);
        board.setPosition(4, CROSS);
        board.setPosition(6, CROSS);

        //when
        float result = eval.evaluatePosition(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void evaluatePosition_circle_player_wins(){
        //given
        float expected = -152;
        board.setPosition(2, CIRCLE);
        board.setPosition(4, CIRCLE);
        board.setPosition(6, CIRCLE);

        //when
        float result = eval.evaluatePosition(board);

        //then
        Assertions.assertEquals(expected, result);
    }
}

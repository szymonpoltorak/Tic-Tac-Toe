package pl.edu.pw.ee.tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static pl.edu.pw.ee.tictactoe.Constants.CIRCLE;
import static pl.edu.pw.ee.tictactoe.Constants.CROSS;

class EvaluationTest {
    private Board board;
    private Evaluation eval;

    @BeforeEach
    public void makeBoard(){
        this.board = new Board(9);
        this.eval = new Evaluation(board.getSideLength());
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

    @Test
    void evaluateRows_test(){
        //given
        var expected = -4;
        board.setPosition(3, CIRCLE);
        board.setPosition(4, CROSS);
        board.setPosition(6, CIRCLE);

        //when
        var result = eval.evaluateRows(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void evaluateColumns_test(){
        //given
        var expected = -4;
        board.setPosition(2, CIRCLE);
        board.setPosition(4, CROSS);
        board.setPosition(1, CIRCLE);

        //when
        var result = eval.evaluateColumns(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void evaluateLeftDiagonal_test(){
        //given
        var expected = -4;
        board.setPosition(4, CIRCLE);
        board.setPosition(3, CROSS);
        board.setPosition(2, CIRCLE);

        //when
        var result = eval.evaluateLeftDiagonal(board);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void evaluateRightDiagonal_test(){
        //given
        var expected = 4;
        board.setPosition(8, CIRCLE);
        board.setPosition(4, CROSS);
        board.setPosition(7, CIRCLE);

        //when
        var result = eval.evaluateRightDiagonal(board);

        //then
        Assertions.assertEquals(expected, result);
    }
}

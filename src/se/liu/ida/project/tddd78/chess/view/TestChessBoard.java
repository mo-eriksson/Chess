package se.liu.ida.project.tddd78.chess.view;

import se.liu.ida.project.tddd78.chess.model.Board;

/**
 * This is the mainclass witch runs the game, where the board and the frame is created to the appropiate size.
 */
public final class TestChessBoard {

    /*
    * Standard size of chess board
    */

    private static final int BOARD_HIEGHT = 8;
    private static final int BOARD_WIDTH = 8;

    private TestChessBoard() {}

    public static void main(String[] args) { newGame(); }

        public static void newGame() {

            /**
             * Chess if created as a continer for chessComponent, which is add to the chessFrame.
             */

            Board chessBoard = new Board(BOARD_HIEGHT, BOARD_WIDTH);
            @SuppressWarnings("UnusedAssignment") ChessFrame chessFrame = new ChessFrame("Chess", chessBoard);
        }
    }




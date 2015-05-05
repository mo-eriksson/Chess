package se.liu.ida.project.tddd78.chess.view;

import se.liu.ida.project.tddd78.chess.model.Board;

/**
 * This is the main class witch runs the game, where the board and the frame is created to the appropriate size.
 */
@SuppressWarnings({ "UnusedAssignment", "SuppressionAnnotation" })
public final class TestChessBoard {

    /*
    * Standard size of chess board
    */

    private static final int BOARD_HEIGHT = 8;
    private static final int BOARD_WIDTH = 8;

    private TestChessBoard() {}

    public static void main(String[] args) { newGame(); }

        public static void newGame() {

            /**
             * Chess if created as a container for chessComponent, which is add to the chessFrame.
             */

            Board chessBoard = new Board(BOARD_HEIGHT, BOARD_WIDTH);
            ChessFrame chessFrame = new ChessFrame("Chess", chessBoard);
        }
    }




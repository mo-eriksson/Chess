package se.liu.ida.dinadress.tddd78.chess;

import javax.swing.JFrame;

/**
 * This is the mainclass witch runs the game, where the board and the frame is created to the appropiate size.
 */
public final class TestChessBoard {

    private static final int BOARD_HIEGHT = 8; // Standard size of chess board + one for boarder
    private static final int BOARD_WIDTH = 8;

    private TestChessBoard() {}

    public static void main(String[] args) { newGame(); }

        public static void newGame() {

            Board chessBoard = new Board(BOARD_HIEGHT, BOARD_WIDTH);
            JFrame chessFrame = new ChessFrame("Chess", chessBoard);
        }
    }




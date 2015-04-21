package se.liu.ida.dinadress.tddd78.chess;

import javax.swing.JFrame;

public class TestChessBoard {

    private static final int BOARD_HIEGHT = 8; // Standard size of chess board + one for boarder
    private static final int BOARD_WIDTH = 8;


    public static void main(String[] args) {

	Board chessBoard = new Board(BOARD_HIEGHT, BOARD_WIDTH);
    JFrame chessFrame = new ChessFrame("Chess", chessBoard);
    }
}


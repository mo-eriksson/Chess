package se.liu.ida.dinadress.tddd78.chess;

import javax.swing.JFrame;

public class TestChessBoard {

    private static final int BOARD_HIEGHT = 9; // Standard size of chess board + one for boarder
    private static final int BOARD_WIDTH = 9;


    public static void main(String[] args) {
	Board testBoard = new Board(BOARD_HIEGHT, BOARD_WIDTH);
    JFrame testFrame = new ChessFrame("YOLO", testBoard);
    }
}

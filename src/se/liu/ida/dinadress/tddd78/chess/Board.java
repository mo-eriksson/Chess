package se.liu.ida.dinadress.tddd78.chess;

import java.util.ArrayList;

/**
 * The class that is responsible for keeping check on the board and tell other classes when it is updated
 */

public class Board {
	private static int BOTTOM_BOARDER = 1;
	private static int LEFT_BOARDER = -1;
	private static int BOARD_WIDTH_PLUS_ONE = 9;

    private int boardHeight;
    private int boardWidth;

    private Piece[][] gameField;

    public Board(int boardHeight, int boardWidth) {
	this.boardWidth = boardWidth;
	this.boardHeight = boardHeight;
	this.gameField = new Piece[boardHeight][boardWidth];

    }
    public Piece[][] setStartPosition() {
        ArrayList<Piece> firstRowPos = new ArrayList();
        firstRowPos.add(Piece.ROOK), Piece.KNIGHT, Piece.BISHOP, Piece.QUEEN, Piece.KING, Piece.BISHOP, Piece.KNIGHT, Piece.ROOK)
        Piece[][] startPosition = new Piece[][]{
                {Piece.ROOK, Piece.KNIGHT, Piece.BISHOP, Piece.QUEEN, Piece.KING, Piece.BISHOP, Piece.KNIGHT, Piece.ROOK}};

        return setGameField();

    }



    public Piece getPieceFromCoordinate(int row, int column) {
	Piece thisIsOnCoordinate = gameField[column][row];
	return thisIsOnCoordinate;
    }

    public int getBoardHeight() {
	return boardHeight;
    }

    public int getBoardWidth() {
	return boardWidth;
    }

    public Piece[][] getGameField() {
	return gameField;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public void setGameField(Piece[][] gameField) {
        this.gameField = gameField;
    }
}

package se.liu.ida.dinadress.tddd78.chess;

/**
 * The class that is responsible for keeping check on the board and tell other classes when it is updated
 */

public class Board {

    private int boardHeight;
    private int boardWidth;

    private Piece[][] gameField;

    public Board(final int boardHeight, final int boardWidth) {
	this.boardWidth = boardWidth;
	this.boardHeight = boardHeight;
	this.gameField = new Piece[boardHeight][boardWidth];

	for (int row = 0; row < boardHeight; row++) {
	    for (int column = 0; column < boardWidth; column++) {

		if (row % 2 == 0) {
		    gameField[row][column] = Piece.EMPTY_WHITE;
		    if (column % 2 == 0) {
			gameField[row][column] = Piece.EMPTY_WHITE;
		    } else {
			gameField[row][column] = Piece.EMPTY_BLACK;
		    }
		}

		else {
		    gameField[row][column] = Piece.EMPTY_BLACK;

		    if (column % 2 != 0) {
			gameField[row][column] = Piece.EMPTY_WHITE;
		    } else {
			gameField[row][column] = Piece.EMPTY_BLACK;
		    }
		}
	    }
	}
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

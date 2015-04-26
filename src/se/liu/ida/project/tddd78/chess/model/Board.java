package se.liu.ida.project.tddd78.chess.model;

import java.awt.*;


/**
 * The class that is responsible for keeping check on the board (gamefield), and all the changes of the board will be done
 * here.
 */

public class Board
{

    private int boardHeight;
    private int boardWidth;

    private ChessPiece[][] gameField;

    public Board(int boardHeight, int boardWidth) {
	this.boardWidth = boardWidth;
	this.boardHeight = boardHeight;
	this.gameField = new ChessPiece[boardHeight][boardWidth];

	setStartPosition();
    }

    /**
     * Fills the gameField with the right start poss.
     */

    public void setStartPosition() {
	for (int row = 0; row < boardHeight; row++) {
	    for (int column = 0; column < boardWidth; column++) {

		if (row == 0) {
		    setAdvancetRow(row, column, Color.BLACK);
		} else if (row == 7) {
		    setAdvancetRow(row, column, Color.WHITE);
		} else if (row == 1) {
		    gameField[row][column] = new Pawn(this, Piece.PAWN, Color.BLACK);
		} else if (row == 6) {
		    gameField[row][column] = new Pawn(this, Piece.PAWN, Color.WHITE);
		} else {
		    gameField[row][column] = new Empty(this, Piece.EMPTY, Color.BLUE);
		}

	    }
	}
    }

    /**
     * Start position for the first and last row (0 and 7)
     */
    private void setAdvancetRow(int row, int column, Color color) {
	switch (column) {

	    case 0:
		gameField[row][column] = new Rook(this, Piece.ROOK, color);
		break;
	    case 1:
		gameField[row][column] = new Knight(this, Piece.KNIGHT, color);
		break;
	    case 2:
		gameField[row][column] = new Bishop(this, Piece.BISHOP, color);
		break;
	    case 3:
		gameField[row][column] = new Queen(this, Piece.QUEEN, color);
		break;
	    case 4:
		gameField[row][column] = new King(this, Piece.KING, color);
		break;
	    case 5:
		gameField[row][column] = new Bishop(this, Piece.BISHOP, color);
		break;
	    case 6:
		gameField[row][column] = new Knight(this, Piece.KNIGHT, color);
		break;
	    case 7:
		gameField[row][column] = new Rook(this, Piece.ROOK, color);
		break;
	}
    }

    public void movePieceOnField(ChessPiece chessPiece, int xNew, int yNew) {
	gameField[yNew][xNew] = chessPiece;
    }

    public void removeOldPiece(int selectedOldX, int selectedOldY) {
	gameField[selectedOldY][selectedOldX] = new Empty(this, Piece.EMPTY, Color.BLUE);

    }

    public ChessPiece getPieceOnCoordinate(int row, int column) {
	ChessPiece thisIsOnCoordinate = gameField[row][column];
	return thisIsOnCoordinate;
    }

    /**
     * Tries all possible moves for every piece of the users color(the user thats tries to escape checkmate) on the gamefield
     */
    public boolean isCheckmate(Color selfColor) {
	boolean checkmate = true;

	for (int currentRow = 0; currentRow < boardHeight; currentRow++) {
	    for (int currentColumn = 0; currentColumn < boardWidth; currentColumn++) {
		for (int destinationRow = 0; destinationRow < boardHeight; destinationRow++) {
		    for (int destinationColumn = 0; destinationColumn < boardWidth; destinationColumn++) {
			if (getPieceOnCoordinate(currentRow, currentColumn).getColor().equals(selfColor)) {
			    if (getPieceOnCoordinate(currentRow, currentColumn)
					.validMove(destinationRow,
						   destinationColumn, currentColumn, currentRow) && !isItCheck(selfColor)) {
				checkmate = false;
			    }
			}
		    }
		}
	    }
	}
	return checkmate;
    }

    /**
     * Finds the king in Y (row) of the color on the game field
     * <p>
     * If there is not a king of the color on the field something is not right
     */

    private int findKingX(Color color) {
	int kingXPosition = -1;
	for (int row = 0; row < boardHeight; row++) {
	    for (int column = 0; column < boardWidth; column++) {
		if (getPieceOnCoordinate(row, column).getPiece() == Piece.KING && getPieceOnCoordinate(row, column).getColor().equals(
			color)) {
		    kingXPosition = column;
		}
	    }
	}
	if (kingXPosition == -1) {
	    throw new IllegalArgumentException("Did not found any king on the field");
	}
	return kingXPosition;
    }

    /**
     * Finds the king in X (coloumn) of the color on the game field
     * <p>
     * If there is not a king of the color on the field something is not right
     */

    private int findKingY(Color color) {
	int kingYPosition = -1;
	for (int row = 0; row < boardHeight; row++) {
	    for (int column = 0; column < boardWidth; column++) {
		if (getPieceOnCoordinate(row, column).getPiece() == Piece.KING && getPieceOnCoordinate(row, column).getColor().equals(
			color)) {
		    kingYPosition = row;
		}
	    }
	}
	if (kingYPosition == -1) {
	    throw new IllegalArgumentException("Did not found any king on the field");
	}
	return kingYPosition;
    }

    /**
     * Loopes over the game field and tries all possible move to enemy King, if it's a valid move to enemy king check
     */

    public boolean isItCheck(Color color) {

	Color enemyColor = Color.BLACK;
	if (color.equals(Color.BLACK)) {
	    enemyColor = Color.WHITE;
	}

	boolean check = false;
	for (int row = 0; row < boardHeight; row++) {
	    for (int column = 0; column < boardWidth; column++) {
		if (getPieceOnCoordinate(row, column).getPiece() != Piece.KING) {
		    if (getPieceOnCoordinate(row, column)
			    .validMove(findKingX(enemyColor), findKingY(enemyColor), column, row)) {
			check = true;
		    }
		}
	    }
	}
	return check;
    }

    /**
     * Check if the move that the user is about to make puts him in friendly check, tries to check on the gamfield that has been
     * updated with the move and then updates the gamefield to the pre-move state.
     */
    public boolean isItSelfCheck(Color selfColor, ChessPiece chessPiece, int oldX, int newX, int oldY, int newY) {
	ChessPiece temp = getPieceOnCoordinate(newY, newX);
	movePieceOnField(chessPiece, newX, newY);
	removeOldPiece(oldX, oldY);
	boolean check = false;

	for (int row = 0; row < boardHeight; row++) {
	    for (int column = 0; column < boardWidth; column++) {

		if (getPieceOnCoordinate(row, column)
			.validMove(findKingX(selfColor), findKingY(selfColor), column, row)) {
		    check = true;
		}
	    }
	}
	movePieceOnField(chessPiece, oldX, oldY);
	movePieceOnField(temp, newX, newY);
	return check;
    }

    public int getBoardHeight() {
	return boardHeight;
    }

    public int getBoardWidth() {
	return boardWidth;
    }

}

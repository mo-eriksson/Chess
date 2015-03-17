package se.liu.ida.dinadress.tddd78.chess;

/**
 *
 */
public abstract class AbstractChessPiece implements ChessPiece {
    protected Board board;
    protected Piece piece;

    public AbstractChessPiece(final Board board, final Piece piece) {
        this.board = board;
        this.piece = piece;
    }

    @Override public boolean isMovable() {
	return false;
    }

    @Override public int getXCoordinate() {
	return 0;
    }

    @Override public int getYCoordinate() {
	return 0;
    }

    @Override public void getType() {
    }
}

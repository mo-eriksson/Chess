package se.liu.ida.dinadress.tddd78.chess;

/**
 *
 */
public abstract class AbstractChessPiece implements ChessPiece {
    protected Board board;
    protected Piece piece;

    public AbstractChessPiece(Board board, Piece piece) {
        this.board = board;
        this.piece = piece;
    }

    public boolean validMove(int newXCoordinate, int newYCoordinate, Piece[][] gameField ){

    }

    public void setPosition( int newXCoordinate, int newYCoordinate){
        setPosition(newXCoordinate, newYCoordinate);
    }



    @Override public boolean isMovable() {return false;}

    @Override public int getXCoordinate() {
	return 0;
    }

    @Override public int getYCoordinate() {
	return 0;
    }

    @Override public void getType() {}
}

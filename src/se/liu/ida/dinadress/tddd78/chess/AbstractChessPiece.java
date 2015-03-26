package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

/**
 *
 */
public abstract class AbstractChessPiece implements ChessPiece {
    protected Board board;
    protected Piece piece;
    protected Color color;

    public AbstractChessPiece(Board board, Piece piece, Color color) {
        this.board = board;
        this.piece = piece;
        this.color = color;
    }

    public boolean validMove(int newXCoordinate, int newYCoordinate, Piece[][] gameField ){
        return false;
    }

    //public void setPosition( int newXCoordinate, int newYCoordinate){
      //  setPosition(newXCoordinate, newYCoordinate);
    //}



    @Override public boolean isMovable() {return false;}

    @Override public int getXCoordinate() {
	return 0;
    }

    @Override public int getYCoordinate() {
	return 0;
    }

    @Override public void getType() {}

    @Override public Color getColor() { return color;}
}

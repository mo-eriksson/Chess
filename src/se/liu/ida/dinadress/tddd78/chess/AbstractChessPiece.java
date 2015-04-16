package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

/**
 *
 */
public abstract class AbstractChessPiece implements ChessPiece {
    protected Board board;
    protected Piece piece;
    protected Color color;

    protected AbstractChessPiece(Board board, Piece piece, Color color) {
        this.board = board;
        this.piece = piece;
        this.color = color;
    }

    @Override public int getXCoordinate() {
	return 0;
    }

    @Override public int getYCoordinate() {
	return 0;
    }

    @Override public Piece getPiece() {
        return piece;
    }

    @Override public Color getColor() {
        return color;}

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public boolean validMove(ChessPiece chessPiece, int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate) {
        return false;
    }
}

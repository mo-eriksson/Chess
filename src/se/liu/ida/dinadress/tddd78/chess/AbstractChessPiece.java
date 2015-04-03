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
}

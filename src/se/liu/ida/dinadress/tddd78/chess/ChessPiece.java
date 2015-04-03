package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

public interface ChessPiece {

    public boolean isMovable();

    boolean validMove(int newXCoordinate, int newYCoordinate);

    public int getXCoordinate();

    public int getYCoordinate();

    public Piece getPiece();

    public Color getColor();
}

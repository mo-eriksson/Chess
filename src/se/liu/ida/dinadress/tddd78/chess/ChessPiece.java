package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

public interface ChessPiece {

    public boolean isMovable();

    boolean validMove(ChessPiece chessPiece, int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate);

    public int getXCoordinate();

    public int getYCoordinate();

    public Piece getPiece();

    public Color getColor();

    public boolean isPromoted();
    
    public ChessPiece promotedTo();


}

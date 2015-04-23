package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

/**
 * The interface declaretes all the different methods witch are used by the different abstract chess pieces
 * involved in this chessgame.
 */
public interface ChessPiece {

    boolean validMove(ChessPiece chessPiece, int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate);

    public Piece getPiece();

    public Color getColor();

    public boolean isPromoted();
    
    public ChessPiece promotedTo();


}

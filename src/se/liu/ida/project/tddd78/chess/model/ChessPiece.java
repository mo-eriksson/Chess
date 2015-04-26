package se.liu.ida.project.tddd78.chess.model;

import java.awt.*;

/**
 * The interface declares some of the different methods which are implemented by the different abstract chess pieces
 * and later the specific Piece.
 */
public interface ChessPiece {

    boolean validMove(int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate);

    public Piece getPiece();

    public Color getColor();

    public boolean isPromoted();
    
    public ChessPiece promotedTo();

    public void checkForPromotion(int newYCoordinate, int oldYCoordinate);


}

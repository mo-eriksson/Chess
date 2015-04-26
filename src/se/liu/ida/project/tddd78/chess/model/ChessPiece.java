package se.liu.ida.project.tddd78.chess.model;

import java.awt.*;

/**
 * The interface declaretes all the different methods witch are used by the different abstract chess pieces
 * involved in this chessgame.
 */
public interface ChessPiece {

    boolean validMove(int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate);

    public Piece getPiece();

    public Color getColor();

    public boolean isPromoted();
    
    public ChessPiece promotedTo();

    public void checkForPromotion(int newYCoordinate, int oldYCoordinate);


}

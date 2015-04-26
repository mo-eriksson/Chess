package se.liu.ida.project.tddd78.chess.model;

import java.awt.*;

/**
 * The empty "piece" act like all the other abstract chesspieces but are used to represent empty
 * cordinates on the gamefield.
 */
public class Empty extends AbstractChessPiece
{

    public Empty(Board board, Piece piece, Color color) {
        super(board, piece, color);
    }

    @Override public boolean validMove(final int newXCoordinate, final int newYCoordinate, final int oldXCoordinate, final int oldYCoordinate)
    {
        return false;
    }
}

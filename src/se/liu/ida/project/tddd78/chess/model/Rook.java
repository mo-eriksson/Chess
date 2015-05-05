package se.liu.ida.project.tddd78.chess.model;

import java.awt.*;

/**
 * The Rook class contains the different valid move methods for the Rook and also inherits valid moves from
 * the interface. The Rook can move through out the whole game field  straight forward/backward or to
 * the right or to the left, any number of steps.
 */
public class Rook extends AbstractChessPiece
{

    public Rook(Board board, Piece piece, Color color) {
        super(board, piece, color);

    }

    @Override
    public boolean validMove(int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate) {

    boolean validMove;
    validMove = validMoveForRook(newYCoordinate, oldYCoordinate, newXCoordinate, oldXCoordinate);
    return validMove;
    }
}


package se.liu.ida.project.tddd78.chess.model;

import java.awt.*;

/**
 * This class specifies the valid moves for the Bishop Piece and houses the constructor for the
 * object Bishop. The Bishop can move diagonally through out the whole game field in all directions.
 */
public class Bishop extends AbstractChessPiece {

    public Bishop(Board board, Piece piece, Color color) {
        super(board, piece, color);
    }

    @Override
    public boolean validMove(int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate) {
        boolean isMovable;
        isMovable = validMoveForBishop(newYCoordinate, oldYCoordinate, newXCoordinate, oldXCoordinate);
        return isMovable;

    }
}

package se.liu.ida.project.tddd78.chess.model;

import java.awt.*;

/**
 * The Queen class contains the valid methods of how the Queen can move(Bishop or Rook). It also inherits valid moves from the
 * AbstractChessPiece. And this class inherits both the valid moves for the Rook and Bishop because the
 * Queen can move through out the whole game field like both these pieces.
 */
public class Queen extends AbstractChessPiece
{

    public Queen(Board board, Piece piece, Color color) {
        super(board, piece, color);
    }

    @Override
    public boolean validMove(int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate) {
        boolean isMovable;
        isMovable = validMoveForBishop(newYCoordinate, oldYCoordinate, newXCoordinate, oldXCoordinate) ||
                validMoveForRook(newYCoordinate, oldYCoordinate, newXCoordinate, oldXCoordinate);
        return isMovable;
    }
}

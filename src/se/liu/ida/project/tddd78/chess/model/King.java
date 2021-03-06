package se.liu.ida.project.tddd78.chess.model;

import java.awt.*;

/**
 * The King class contains the different valid moves on the game field. It inherits the
 * valid move method from the interface just as all other pieces.
 * The king can move one step in all the directions around itself.
 */
public class King extends AbstractChessPiece
{

    public King(Board board, Piece piece, Color color) {
        super(board, piece, color);
    }

    @Override
    public boolean validMove(int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate) {
        boolean moveIsValid = false;
        if (Math.abs(newXCoordinate - oldXCoordinate) == 1 && Math.abs(newYCoordinate - oldYCoordinate) == 1) {

            moveIsValid = validMoveForBishop(newYCoordinate, oldYCoordinate, newXCoordinate, oldXCoordinate);
        }
        else if (Math.abs(newXCoordinate - oldXCoordinate) == 1 || Math.abs(newYCoordinate - oldYCoordinate) == 1) {
            moveIsValid = validMoveForRook(newYCoordinate, oldYCoordinate, newXCoordinate, oldXCoordinate);
        }

        return moveIsValid;
    }
}

package se.liu.ida.dinadress.tddd78.chess;
import java.awt.*;

/**
 * The King class contains the different valid moves on the gamefield. It inharitets the
 * valid move method from the AbstractChessPiece class.
 * The king can move one step in all the directions around it self.
 */
public class King extends AbstractChessPiece {

    public King(Board board, Piece piece, Color color) {
        super(board, piece, color);
    }

    @Override
    public boolean validMove(ChessPiece chessPiece, int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate) {
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

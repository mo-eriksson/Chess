package se.liu.ida.dinadress.tddd78.chess;

/**
 * Kings own class were if any special moves or catsting will that place
 */
import java.awt.*;

public class King extends AbstractChessPiece {

    public King(Board board, Piece piece, Color color) {
        super(board, piece, color);
    }

    @Override
    public boolean isMovable() {
        return false;
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

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
    public boolean validMove(ChessPiece chessPiece,int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate) {
        return false;
    }
}

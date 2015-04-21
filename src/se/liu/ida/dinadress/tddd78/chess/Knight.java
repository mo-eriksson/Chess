package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

public class Knight extends AbstractChessPiece {

    public Knight(Board board, Piece piece, Color color) {
        super(board, piece, color);
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public boolean validMove(ChessPiece chessPiece,int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate) {
        boolean moveIsValid = false;
        if ((Math.abs(newXCoordinate - oldXCoordinate) == 2 && Math.abs(newYCoordinate - oldYCoordinate) == 1) ||
                (Math.abs(newYCoordinate - oldYCoordinate) == 2 && Math.abs(newXCoordinate - oldXCoordinate) == 1)) {
            if (! board.getGameField()[newYCoordinate][newXCoordinate].getColor().equals(this.color) ||
                    board.getGameField()[newYCoordinate][newXCoordinate].getPiece() == Piece.EMPTY) {

                moveIsValid = true;
            }
        }
        return moveIsValid;
    }
}

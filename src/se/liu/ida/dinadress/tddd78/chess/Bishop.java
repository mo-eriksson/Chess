package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

public class Bishop extends AbstractChessPiece {

    public Bishop(Board board, Piece piece, Color color) {
        super(board, piece, color);
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public boolean validMove(ChessPiece chessPiece,int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate) {
        boolean isMovable = false;
        isMovable = validMoveForBishop(newYCoordinate, oldYCoordinate, newXCoordinate, oldXCoordinate);
        return isMovable;

    }
}

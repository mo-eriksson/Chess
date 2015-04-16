package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

public class Rook extends AbstractChessPiece {

    public Rook(Board board, Piece piece, Color color) {
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

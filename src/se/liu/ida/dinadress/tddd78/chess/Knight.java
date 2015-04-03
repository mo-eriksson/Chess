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
    public boolean validMove(int newXCoordinate, int newYCoordinate) {
        return false;
    }
}

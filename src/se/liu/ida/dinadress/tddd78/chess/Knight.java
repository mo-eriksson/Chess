package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

/**
 * The Knight class contains the valid moves for the Knight on the gamefield. It also inharitets the valid
 * move method from the AbstractChessPiece class. The knight can move like a L, (+-) 2 step in the x or y direction
 * and (+-) 1 step in direction it didn't take two step in.
 * Can junp over other pieces, this is unique for the Knight.
 */
public class Knight extends AbstractChessPiece {

    public Knight(Board board, Piece piece, Color color) {
        super(board, piece, color);
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

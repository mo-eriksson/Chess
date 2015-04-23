package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

/**
 * The Rook class contains the different valid move methods for the Rook and also inharits valid moves from the
 * AbstractChessPiece class. The Rook can move through out the whole gamefield eather straight forward, to
 * the right or to the left.
 */
public class Rook extends AbstractChessPiece {

    public Rook(Board board, Piece piece, Color color) {
        super(board, piece, color);

    }

    @Override
    public boolean validMove(ChessPiece chessPiece, int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate) {
    boolean validMove;
    validMove = validMoveForRook(newYCoordinate, oldYCoordinate, newXCoordinate, oldXCoordinate);
    return validMove;
    }

    /*public boolean checkIfPathIsClear(int newYCoordinate, int oldYCoordinate, int newXCoordinate, int oldXCoordinate) {
        boolean pathIsClear = true;
        int startCheckAtNext = 1;

        int startX = Math.min(newXCoordinate, oldXCoordinate);
        int startY = Math.min(newYCoordinate, oldYCoordinate);
        int stopX = Math.max(newXCoordinate, oldXCoordinate);
        int stopY = Math.max(newYCoordinate, oldYCoordinate);

        if (startX != stopX) {
            startX++;
        } else {
            startY++;
        }

        while (! (startX == stopX && startY == stopY)) {
            if (board.getGameField()[startY][startX].getPiece() != Piece.EMPTY) {
                pathIsClear = false;
            }

            if (startX != stopX) {
               startX++;
           } else {
               startY++;
           }
         }

        pathIsClear = pathIsClear &&
                (board.getGameField()[newYCoordinate][newXCoordinate].getPiece() == Piece.EMPTY ||
                 ! board.getGameField()[newYCoordinate][newXCoordinate].getColor().equals(this.color));

        return pathIsClear;*/
    }


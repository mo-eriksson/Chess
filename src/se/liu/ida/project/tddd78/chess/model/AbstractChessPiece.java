package se.liu.ida.project.tddd78.chess.model;

import java.awt.*;

/**
 * The abstract class houses the common method for more then one piece object and mostly revoles around the
 * diffrent implention of validMove.
 */
public abstract class AbstractChessPiece implements ChessPiece
{

    protected Board board;
    protected Piece piece;
    protected Color color;

    protected AbstractChessPiece(Board board, Piece piece, Color color) {
        this.board = board;
        this.piece = piece;
        this.color = color;
    }

    @Override
    public Piece getPiece() {
        return piece;
    }

    @Override
    public Color getColor() {
        return color;
    }

    /**
     * This implention of validMove is only used when the user tries to move the object "Empty", which is not ok
     */
    @Override
    public boolean validMove(ChessPiece chessPiece, int newXCoordinate, int newYCoordinate,
                             int oldXCoordinate, int oldYCoordinate) {
        return false;
    }

    /**
     * Check that the move is not to the same place where the piece stod.
     * Loops over the bishops path to new coordinates and check that there is nothing in the way.
     *
     */
    public boolean validMoveForBishop(int newYCoordinate, int oldYCoordinate, int newXCoordinate, int oldXCoordinate) {
        boolean isMovable = false;
        boolean pathIsClear = true;

        if (! (newYCoordinate == oldYCoordinate && newXCoordinate == oldXCoordinate)) {

            if ((oldXCoordinate < newXCoordinate && oldYCoordinate > newYCoordinate) ||
                    (oldXCoordinate > newXCoordinate && oldYCoordinate < newYCoordinate)) {

                boolean pathIsClearCountingDown;
                pathIsClearCountingDown = validMoveForBishopDown(newYCoordinate,
                        oldYCoordinate, newXCoordinate, oldXCoordinate);

                return pathIsClearCountingDown;

            } else {
                int startY = Math.min(newYCoordinate, oldYCoordinate);
                int stopY = Math.max(newYCoordinate, oldYCoordinate);

                int startX = Math.min(newXCoordinate, oldXCoordinate);
                int stopX = Math.max(newXCoordinate, oldXCoordinate);

                if (Math.abs(newYCoordinate - oldYCoordinate) == Math.abs(newXCoordinate - oldXCoordinate)) {
                    startX++;
                    startY++;

                    while (!(startX == stopX && startY == stopY)) {
                        if (board.getPieceOnCoordinate(startY, startX).getPiece() != Piece.EMPTY) {
                            pathIsClear = false;
                        }
                        startX++;
                        startY++;
                    }
                    pathIsClear = pathIsClear &&
                            (board.getPieceOnCoordinate(newYCoordinate, newXCoordinate).getPiece() == Piece.EMPTY ||
                                    !board.getPieceOnCoordinate(newYCoordinate, newXCoordinate).getColor().
                                            equals(this.color));

                    isMovable = pathIsClear;
                }
            }
        }
        return isMovable;
    }

    @Override public void checkForPromotion(int newYCoordinate, int oldYCoordinate) {

    }

    public boolean validMoveForBishopDown(int newYCoordinate, int oldYCoordinate, int newXCoordinate, int oldXCoordinate) {

        boolean isMovable = false;
        boolean pathIsClear = true;

        if (! (newYCoordinate == oldYCoordinate && newXCoordinate == oldXCoordinate)) {

            int startY = Math.max(newYCoordinate, oldYCoordinate);
            int stopY = Math.min(newYCoordinate, oldYCoordinate);

            int startX = Math.min(newXCoordinate, oldXCoordinate);
            int stopX = Math.max(newXCoordinate, oldXCoordinate);

            if (Math.abs(newYCoordinate - oldYCoordinate) == Math.abs(newXCoordinate - oldXCoordinate)) {

                startX++;
                startY--;

                while (!(startX == stopX && startY == stopY)) {
                    if (board.getPieceOnCoordinate(startY, startX).getPiece() != Piece.EMPTY) {
                        pathIsClear = false;
                    }
                    startX++;
                    startY--;
                }
                pathIsClear = pathIsClear &&
                        (board.getPieceOnCoordinate(newYCoordinate, newXCoordinate).getPiece() == Piece.EMPTY ||
                                !board.getPieceOnCoordinate(newYCoordinate, newXCoordinate).getColor().equals(this.color));

                isMovable = pathIsClear;
            }
        }
        return isMovable;
    }

    public boolean checkIfPathIsClearForRook(int newYCoordinate, int oldYCoordinate, int newXCoordinate, int oldXCoordinate) {
        boolean pathIsClear = true;

        int startX = Math.min(newXCoordinate, oldXCoordinate);
        int startY = Math.min(newYCoordinate, oldYCoordinate);
        int stopX = Math.max(newXCoordinate, oldXCoordinate);
        int stopY = Math.max(newYCoordinate, oldYCoordinate);

        if (startX != stopX) {
            startX++;
        } else {
            startY++;
        }

        while (!(startX == stopX && startY == stopY)) {
            if (board.getPieceOnCoordinate(startY, startX).getPiece() != Piece.EMPTY) {
                pathIsClear = false;
            }

            if (startX != stopX) {
                startX++;
            } else {
                startY++;
            }
        }

        pathIsClear = pathIsClear &&
                (board.getPieceOnCoordinate(newYCoordinate, newXCoordinate).getPiece() == Piece.EMPTY ||
                        !board.getPieceOnCoordinate(newYCoordinate, newXCoordinate).getColor().equals(this.color));

        return pathIsClear;
    }

    public boolean validMoveForRook(int newYCoordinate, int oldYCoordinate, int newXCoordinate, int oldXCoordinate) {
        boolean isValidMove = false;

        if (newXCoordinate == oldXCoordinate && newYCoordinate != oldYCoordinate ||
                newYCoordinate == oldYCoordinate && newXCoordinate != oldXCoordinate) {
            if (checkIfPathIsClearForRook(newYCoordinate, oldYCoordinate, newXCoordinate, oldXCoordinate)) {
                isValidMove = true;
            }
        }
        return isValidMove;
    }

    @Override
    public boolean isPromoted() {
        return false;
    }

    @Override
    public ChessPiece promotedTo() {
        return null;
    }
}

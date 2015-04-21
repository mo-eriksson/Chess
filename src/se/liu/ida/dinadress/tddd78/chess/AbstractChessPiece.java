package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

/**
 *
 */
public abstract class AbstractChessPiece implements ChessPiece {
    protected Board board;
    protected Piece piece;
    protected Color color;

    protected AbstractChessPiece(Board board, Piece piece, Color color) {
        this.board = board;
        this.piece = piece;
        this.color = color;
    }

    @Override
    public int getXCoordinate() {
        return 0;
    }

    @Override
    public int getYCoordinate() {
        return 0;
    }

    @Override
    public Piece getPiece() {
        return piece;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public boolean validMove(ChessPiece chessPiece, int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate) {
        return false;
    }

    public boolean validMoveForBishop(int newYCoordinate, int oldYCoordinate, int newXCoordinate, int oldXCoordinate) {
        boolean isMovable = false;
        boolean pathIsClear = true;

        if (!(newYCoordinate == oldYCoordinate && newXCoordinate == oldXCoordinate)) {

            int startX = Math.min(newXCoordinate, oldXCoordinate);
            int startY = Math.min(newYCoordinate, oldYCoordinate);
            int stopX = Math.max(newXCoordinate, oldXCoordinate);
            int stopY = Math.max(newYCoordinate, oldYCoordinate);

            if (Math.abs(newYCoordinate - oldYCoordinate) == Math.abs(newXCoordinate - oldXCoordinate)) {

                startX++;
                startY++;

                while (!(startX == stopX && startY == stopY)) {
                    System.out.println(startX + " X and Y " + startY);
                    if (board.getGameField()[startY][startX].getPiece() != Piece.EMPTY) {
                        pathIsClear = false;
                    }
                    startX++;
                    startY++;
                }
                pathIsClear = pathIsClear &&
                        (board.getGameField()[newYCoordinate][newXCoordinate].getPiece() == Piece.EMPTY ||
                                !board.getGameField()[newYCoordinate][newXCoordinate].getColor().equals(this.color));

                isMovable = pathIsClear;
            }
        }
        return isMovable;
    }

    public boolean checkIfPathIsClearForRook(int newYCoordinate, int oldYCoordinate, int newXCoordinate, int oldXCoordinate) {
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

        while (!(startX == stopX && startY == stopY)) {
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
                        !board.getGameField()[newYCoordinate][newXCoordinate].getColor().equals(this.color));

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

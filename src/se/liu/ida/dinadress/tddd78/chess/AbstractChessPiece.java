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
    public Piece getPiece() {
        return piece;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean validMove(ChessPiece chessPiece, int newXCoordinate, int newYCoordinate,
                             int oldXCoordinate, int oldYCoordinate) {
        return false;
    }

    public boolean validMoveForBishop(int newYCoordinate, int oldYCoordinate, int newXCoordinate, int oldXCoordinate) {
        boolean isMovable = false;
        boolean pathIsClear = true;

        if (!(newYCoordinate == oldYCoordinate && newXCoordinate == oldXCoordinate)) {

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

                        System.out.println(startX + " X start and  start Y " + startY + " är dom tomma " +
                                (board.getGameField()[startY][startX].getPiece() != Piece.EMPTY));

                        if (board.getGameField()[startY][startX].getPiece() != Piece.EMPTY) {
                            System.out.println(startX + " start X och start Y " + startY);
                            pathIsClear = false;
                        }
                        startX++;
                        startY++;
                    }
                    pathIsClear = pathIsClear &&
                            (board.getGameField()[newYCoordinate][newXCoordinate].getPiece() == Piece.EMPTY ||
                                    !board.getGameField()[newYCoordinate][newXCoordinate].getColor().
                                            equals(this.color));

                    isMovable = pathIsClear;
                }
            }
        }
        System.out.println(isMovable);
        return isMovable;
    }

    /**
     * public boolean validMoveForBishopNy(int newYCoordinate, int oldYCoordinate, int newXCoordinate, int oldXCoordinate) {
     * boolean isMovable = false;
     * boolean pathIsClear = true;
     * <p>
     * int yDiff = (newYCoordinate - oldYCoordinate) / (Math.abs(newYCoordinate - oldYCoordinate)+1);
     * int xDiff = (newXCoordinate - oldXCoordinate) / (Math.abs(newXCoordinate - oldXCoordinate)+1);
     * <p>
     * <p>
     * if (xDiff * yDiff == 1 || xDiff * yDiff == -1) {
     * <p>
     * int currentY = oldYCoordinate;
     * int currentX = oldXCoordinate;
     * currentX += xDiff;
     * currentY += yDiff;
     * <p>
     * while (currentX != newXCoordinate) {
     * if (board.getGameField()[currentY][currentX].getPiece() != Piece.EMPTY) {
     * return false;
     * }
     * currentX += xDiff;
     * currentY += yDiff;
     * }
     * return true;
     * <p>
     * //            int stopY = Math.max(newYCoordinate, oldYCoordinate);
     * //
     * //            int startX = Math.min(newXCoordinate, oldXCoordinate);
     * //            int stopX = Math.max(newXCoordinate, oldXCoordinate);
     * //
     * //            if (Math.abs(newYCoordinate - oldYCoordinate) == Math.abs(newXCoordinate - oldXCoordinate)) {
     * //
     * //                if (startX < stopX && startY > stopY) {
     * //                    System.out.println("calll to neew");
     * //                    boolean pathIsClearCountingDown;
     * //                    pathIsClearCountingDown = pathIsClearCountingDown(newYCoordinate, oldYCoordinate, newXCoordinate, oldXCoordinate);
     * //                    System.out.println(pathIsClearCountingDown);
     * //                    return pathIsClearCountingDown;
     * //
     * //                } else {
     * //                    startX++;
     * //                    startY++;
     * //
     * //                    while (!(startX == stopX && startY == stopY)) {
     * //
     * //                        System.out.println(startX + " X start and  start Y " + startY + " är dom tomma " +
     * //                                (board.getGameField()[startY][startX].getPiece() != Piece.EMPTY));
     * //
     * //                        if (board.getGameField()[startY][startX].getPiece() != Piece.EMPTY) {
     * //                            System.out.println(startX + " start X och start Y " + startY);
     * //                            pathIsClear = false;
     * //                        }
     * //                        startX++;
     * //                        startY++;
     * //                    }
     * //                    pathIsClear = pathIsClear &&
     * //                            (board.getGameField()[newYCoordinate][newXCoordinate].getPiece() == Piece.EMPTY ||
     * //                                    !board.getGameField()[newYCoordinate][newXCoordinate].getColor().equals(this.color));
     * //
     * //                    isMovable = pathIsClear;
     * //                }
     * //            }
     * //        }
     * //        System.out.println(isMovable);
     * //        return isMovable;
     * }
     * <p>
     * return false;
     * }*
     */


    public boolean validMoveForBishopDown(int newYCoordinate, int oldYCoordinate, int newXCoordinate, int oldXCoordinate) {

        boolean isMovable = false;
        boolean pathIsClear = true;

        if (!(newYCoordinate == oldYCoordinate && newXCoordinate == oldXCoordinate)) {

            int startY = Math.max(newYCoordinate, oldYCoordinate);
            int stopY = Math.min(newYCoordinate, oldYCoordinate);

            int startX = Math.min(newXCoordinate, oldXCoordinate);
            int stopX = Math.max(newXCoordinate, oldXCoordinate);

            if (Math.abs(newYCoordinate - oldYCoordinate) == Math.abs(newXCoordinate - oldXCoordinate)) {

                startX++;
                startY--;

                while (!(startX == stopX && startY == stopY)) {

                    System.out.println(startX + " X start and  start Y " + startY + " är dom tomma " +
                            (board.getGameField()[startY][startX].getPiece() != Piece.EMPTY));

                    if (board.getGameField()[startY][startX].getPiece() != Piece.EMPTY) {
                        System.out.println(startX + " start X och start Y " + startY);
                        pathIsClear = false;
                    }
                    startX++;
                    startY--;
                }
                pathIsClear = pathIsClear &&
                        (board.getGameField()[newYCoordinate][newXCoordinate].getPiece() == Piece.EMPTY ||
                                !board.getGameField()[newYCoordinate][newXCoordinate].getColor().equals(this.color));

                isMovable = pathIsClear;
            }
        }
        System.out.println(isMovable);
        return isMovable;
    }

    /**
     * private boolean pathIsClearCountingDown(int newYCoordinate, int oldYCoordinate, int newXCoordinate, int oldXCoordinate) {
     * boolean pathIsClear = true;
     * boolean isMovable = false;
     * <p>
     * int startY = Math.max(newYCoordinate, oldYCoordinate);
     * int stopY = Math.min(newYCoordinate, oldYCoordinate);
     * <p>
     * int startX = Math.min(newXCoordinate, oldXCoordinate);
     * int stopX = Math.max(newXCoordinate, oldXCoordinate);
     * <p>
     * startX++;
     * startY--;
     * <p>
     * while (!(startX == stopX && startY == stopY)) {
     * <p>
     * System.out.println(startX + " X start and  start Y " + startY + " är dom tomma " +
     * (board.getGameField()[startY][startX].getPiece() != Piece.EMPTY));
     * <p>
     * if (board.getGameField()[startY][startX].getPiece() != Piece.EMPTY) {
     * System.out.println(startX + " start X och start Y " + startY);
     * pathIsClear = false;
     * }
     * startX++;
     * startY--;
     * }
     * pathIsClear = pathIsClear &&
     * (board.getGameField()[newYCoordinate][newXCoordinate].getPiece() == Piece.EMPTY ||
     * !board.getGameField()[newYCoordinate][newXCoordinate].getColor().equals(this.color));
     * <p>
     * isMovable = pathIsClear;
     * <p>
     * return isMovable;
     * }*
     */

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

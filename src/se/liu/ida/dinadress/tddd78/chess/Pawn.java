package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

public class Pawn extends AbstractChessPiece {
    private final static int ONE_STEP_FORWARD_WHITE = -1;
    private final static int TWO_STEP_FORWARD_WHITE = -2;
    private final static int ONE_STEP_FORWARD_BLACK = 1;
    private final static int TWO_STEP_FORWARD_BLACK= 2;
    private final static int INT_WHITE_COLOR = -1;
    private final static int INT_BLACK_COLOR = -16777216;


    public Pawn(Board board, Piece piece, Color color) {
        super(board, piece, color);

    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public boolean validMove(ChessPiece chessPiece,int newXCoordinate, int newYCoordinate, int oldXCoordinate, int oldYCoordinate) {
        boolean moveIsValid = false;
        int startPositionY;
        int movePawnTwoSteps;
        int movePawnOneStep;
        boolean emptyOneStepForward = false;
        boolean emptyTwoStepForward = false;

        if (chessPiece.getColor().getRGB() == INT_WHITE_COLOR) {
            startPositionY = 6;
            movePawnTwoSteps = TWO_STEP_FORWARD_WHITE;
            movePawnOneStep = ONE_STEP_FORWARD_WHITE;
        }
        else {
            startPositionY = 1;
            movePawnTwoSteps = TWO_STEP_FORWARD_BLACK;
            movePawnOneStep = ONE_STEP_FORWARD_BLACK;
        }

        if (board.getGameField()[oldYCoordinate + movePawnOneStep][oldXCoordinate].getPiece() == Piece.EMPTY) {
            emptyOneStepForward = true;

            if (board.getGameField()[oldYCoordinate + movePawnTwoSteps][oldXCoordinate].getPiece() == Piece.EMPTY) {
                emptyTwoStepForward = true;
            }
        }

            if (startPositionY == oldYCoordinate) {
                if (newXCoordinate == oldXCoordinate && newYCoordinate == oldYCoordinate + movePawnTwoSteps &&
                        emptyTwoStepForward)
                    moveIsValid = true;
                else if (newYCoordinate == (oldYCoordinate + movePawnOneStep) && newXCoordinate == oldXCoordinate &&
                        emptyOneStepForward) {
                    moveIsValid = true;
                }

            } else if (newYCoordinate == (oldYCoordinate + movePawnOneStep) && newXCoordinate == oldXCoordinate &&
                    emptyOneStepForward) {
                moveIsValid = true;
            }

            else if (board.getGameField()[newYCoordinate][newXCoordinate].getPiece() != Piece.EMPTY) {
                System.out.println(moveIsValid + "a");
                if (newYCoordinate == oldYCoordinate + movePawnOneStep && (newXCoordinate == oldXCoordinate + 1 || newXCoordinate == oldXCoordinate - 1)) {
                    System.out.println(moveIsValid);
                    moveIsValid = true;
                }
        }
        return moveIsValid;
    }
}


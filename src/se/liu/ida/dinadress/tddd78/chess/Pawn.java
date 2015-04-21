package se.liu.ida.dinadress.tddd78.chess;

import javax.swing.*;
import java.awt.*;

public class Pawn extends AbstractChessPiece {
    private final static int ONE_STEP_FORWARD_WHITE = -1;
    private final static int TWO_STEP_FORWARD_WHITE = -2;
    private final static int ONE_STEP_FORWARD_BLACK = 1;
    private final static int TWO_STEP_FORWARD_BLACK= 2;
    private final static int INT_WHITE_COLOR = -1;
    private final static int INT_BLACK_COLOR = -16777216;

    private int initialY;

    private boolean promoted = false;
    private ChessPiece promotedTo = null;

    private JFrame framePopUp = null;


    public Pawn(Board board, Piece piece, Color color) {
        super(board, piece, color);
        if (color.getRGB() == INT_BLACK_COLOR ) {
            this.initialY = 1;
        }
        else {
            this.initialY = 6;
        }
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

            if (oldYCoordinate == initialY) {
                if (board.getGameField()[oldYCoordinate + movePawnTwoSteps][oldXCoordinate].getPiece() == Piece.EMPTY) {
                    emptyTwoStepForward = true;
                }

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
            if (newYCoordinate == oldYCoordinate + movePawnOneStep && (newXCoordinate == oldXCoordinate + 1 ||
                    newXCoordinate == oldXCoordinate - 1) &&
                    ! board.getGameField()[newYCoordinate][newXCoordinate].getColor().equals(this.color)) {
                System.out.println(moveIsValid);
                moveIsValid = true;
            }
        }
        if (moveIsValid && ((newYCoordinate == 0 && this.color.getRGB() == INT_WHITE_COLOR) ||
                (newYCoordinate == 7 && this.color.getRGB() == INT_BLACK_COLOR))) {
            promotionOfPawn(newYCoordinate, newXCoordinate, this.color);
            this.promoted = true;
        }
        return moveIsValid;
    }

    private void promotionOfPawn(int newYCoordinate, int newXCoordinate, Color color) {
        String[] options = {
                "Queen",
                "Bishop",
                "Knight",
                "Rook"
        };
        int optionsChosen = JOptionPane.showOptionDialog(
                framePopUp,
                "Select your prefered promotion",
                "Promotion of Pawn",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (optionsChosen == 0) {
            this.promotedTo = new Queen(board, Piece.QUEEN, color);
        }
        if (optionsChosen == 1) {
            this.promotedTo = new Bishop(board, Piece.BISHOP, color);
        }
        if (optionsChosen == 2) {
            this.promotedTo = new Knight(board, Piece.KNIGHT, color);
        }
        if (optionsChosen == 3) {
            promotedTo = new Rook(board, Piece.ROOK, color);

        }
    }

    @Override
    public boolean isPromoted() {

        return this.promoted;
    }

    @Override
    public ChessPiece promotedTo() {
        return this.promotedTo;
    }

}


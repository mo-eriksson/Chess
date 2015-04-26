package se.liu.ida.project.tddd78.chess.model;

import javax.swing.*;
import java.awt.*;

/**
 * The Pawn class contains the different valid moves for the Pawn and also inharites the valid move method from the
 * AbstractChessPiece class. The pawn can move one step forward except for the first time it moves. At this move the player can
 * choose if the Pawn will move one step or two steps forward, the pawn can only take out other pieces in a diagonal direction
 * forward to bouth the left or the right direction. And if the Pawn reaches the other side of the gamefield it will be promoted
 * to the piece of choice. (Queen,Knight,Rook,Bishop)
 */
public class Pawn extends AbstractChessPiece
{

    private final static int INT_WHITE_COLOR = -1;
    private final static int INT_BLACK_COLOR = -16777216;

    private int initialY;

    private boolean promoted = false;
    private ChessPiece promotedTo = null;

    private JFrame framePopUp = null;


    public Pawn(Board board, Piece piece, Color color) {
	super(board, piece, color);
	if (color.equals(Color.BLACK)) {
	    this.initialY = 1;
	} else {
	    this.initialY = 6;
	}
    }

    @Override public boolean validMove(final ChessPiece chessPiece, final int newXCoordinate, final int newYCoordinate,
				       final int oldXCoordinate, final int oldYCoordinate)
    {
	if (color.equals(Color.WHITE)) {
	    return validWhiteMove(chessPiece, newXCoordinate, newYCoordinate, oldXCoordinate, oldYCoordinate);
	} else {
	    return validBlackMove(chessPiece, newXCoordinate, newYCoordinate, oldXCoordinate, oldYCoordinate);
	}
    }

    /**
     * Black starts at 1=y and goes to y=7, if at y = 1 two steps  forward in Y is ok Strikes one forward y and one (+-) x Can
     * only move forward in y if nothing is in the way
     */
    public boolean validBlackMove(ChessPiece chessPiece, int newXCoordinate, int newYCoordinate, int oldXCoordinate,
				  int oldYCoordinate)
    {
	boolean validMove = false;

	if (!(newYCoordinate == oldYCoordinate && newXCoordinate == oldXCoordinate)) {
	    if (newYCoordinate - oldYCoordinate == 2) {
		if (newXCoordinate == oldXCoordinate) {
		    validMove = (oldYCoordinate == initialY) &&
				pathIsClearBlackPawn(newYCoordinate, oldYCoordinate, oldXCoordinate);
		}
	    } else if (newYCoordinate - oldYCoordinate == 1 && newXCoordinate == oldXCoordinate) {
		validMove = pathIsClearBlackPawn(newYCoordinate, oldYCoordinate, oldXCoordinate);
	    } else if (newYCoordinate - oldYCoordinate == 1 && Math.abs(newXCoordinate - oldXCoordinate) == 1) {
		validMove = board.getPieceOnCoordinate(newYCoordinate, newXCoordinate).getColor().equals(Color.WHITE);

	    }
	}
	return validMove;
    }

    /**
     * White starts at 6=y and goes to y=0, if at y = 6 two steps forward in Y is ok. Strikes one forward(-) y and one (+-) x.
     * Can only move forward in y if nothing is in the way.
     */

    public boolean validWhiteMove(ChessPiece chessPiece, int newXCoordinate, int newYCoordinate, int oldXCoordinate,
				  int oldYCoordinate)
    {
	boolean validMove = false;

	if (!(newYCoordinate == oldYCoordinate && newXCoordinate == oldXCoordinate)) {
	    if (newYCoordinate - oldYCoordinate == -2) {
		if (newXCoordinate == oldXCoordinate) {
		    validMove = (oldYCoordinate == initialY) &&
				pathIsClearWhitePawn(newYCoordinate, oldYCoordinate, oldXCoordinate);
		}
	    } else if (newYCoordinate - oldYCoordinate == -1 && newXCoordinate == oldXCoordinate) {
		validMove = pathIsClearWhitePawn(newYCoordinate, oldYCoordinate, oldXCoordinate);
	    } else if (newYCoordinate - oldYCoordinate == -1 && Math.abs(newXCoordinate - oldXCoordinate) == 1) {
		validMove = board.getPieceOnCoordinate(newYCoordinate, newXCoordinate).getColor().equals(Color.BLACK);
	    }
	}
	return validMove;
    }

    private boolean pathIsClearBlackPawn(int newYCoordinate, int oldYCoordinate, int oldXCoordinate) {
	boolean pathIsClear = true;

	int y = oldYCoordinate;

	y++;

	while (y <= newYCoordinate) {
	    if (board.getPieceOnCoordinate(y, oldXCoordinate).getPiece() != Piece.EMPTY) {
		pathIsClear = false;
	    }
	    y++;
	}
	return pathIsClear;
    }

    private boolean pathIsClearWhitePawn(int newYCoordinate, int oldYCoordinate, int oldXCoordinate) {
	boolean pathIsClear = true;

	int y = oldYCoordinate;

	y--;

	while (y >= newYCoordinate) {
	    if (board.getPieceOnCoordinate(y, oldXCoordinate).getPiece() != Piece.EMPTY) {
		pathIsClear = false;
	    }
	    y--;
	}
	return pathIsClear;
    }

    private void promotionOfPawn(Color color) {
	String[] options = { "Queen", "Bishop", "Knight", "Rook" };
	int optionsChosen = JOptionPane.showOptionDialog(framePopUp, "Select your prefered promotion", "Promotion of Pawn",
							 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							 options, options[0]);

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

    @Override public void checkForPromotion(int newYCoordinate, int oldYCoordinate) {
	super.checkForPromotion(newYCoordinate, oldYCoordinate);

	boolean isPromotionWhite = ((newYCoordinate == 0 && this.color.equals(Color.WHITE)) && oldYCoordinate == 1);
	boolean isPromotionBlack = ((newYCoordinate == 7 && this.color.equals(Color.BLACK)) && oldYCoordinate == 6);
	if (isPromotionWhite || isPromotionBlack) {
	    promotionOfPawn(this.color);
	    this.promoted = true;
	}
    }

    @Override public boolean isPromoted() {
	return this.promoted;
    }

    @Override public ChessPiece promotedTo() {
	return this.promotedTo;
    }

}


package se.liu.ida.project.tddd78.chess.view;


import se.liu.ida.project.tddd78.chess.model.Board;
import se.liu.ida.project.tddd78.chess.model.ChessPiece;
import se.liu.ida.project.tddd78.chess.model.King;
import se.liu.ida.project.tddd78.chess.model.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;


/**
 * This makes the board graphic in forms of a background and a different ChessPieces.
 * Also where clicks are registered from the user.
 * All test for general rules is found here.
 */
public class ChessComponent extends JComponent implements Composite {

    private static final int SQUARE_SIDE = 90;

    private Board board;

    private JFrame checkPopUp = null;
    private JFrame checkmatePopUp = null;

    private int selectedOldX;
    private int selectedOldY;

    private int selectedNewX;
    private int selectedNewY;

    private ChessPiece selectedPiece = null;

    private boolean clicked = false;

    private Color nextTurn = Color.WHITE;

    public ChessComponent(Board board) {

	this.board = board;

	setPreferredSize(new Dimension(board.getBoardWidth() * SQUARE_SIDE, board.getBoardHeight() * SQUARE_SIDE));
	addMouseListener(new MouseInputAdapter() {

	    @Override public void mouseClicked(MouseEvent e) {

		int xCoord = e.getX() / SQUARE_SIDE;
		int yCoord = e.getY() / SQUARE_SIDE;
		super.mouseClicked(e);

		if ((xCoord <= 7 && xCoord >= 0) && (yCoord >= 0 && yCoord <= 7)) {

		    if (clicked) {

			if ((!selectedPiece.validMove(xCoord, yCoord, selectedOldX, selectedOldY)) ||
			    board.isItSelfCheck(selectedPiece.getColor(), selectedPiece, selectedOldX, xCoord, selectedOldY,
						yCoord)) {

			    invalidMoveResetAll();
			} else {

			    selectedPiece.checkForPromotion(yCoord, selectedOldY);
			    selectedNewX = xCoord;
			    selectedNewY = yCoord;

			    if (selectedPiece.isPromoted()) {
				selectedPiece = selectedPiece.promotedTo();
			    }

			    board.movePieceOnField(selectedPiece, selectedNewX, selectedNewY);
			    board.removeOldPiece(selectedOldX, selectedOldY);
			    clicked = false;

			    repaint();
			    displayCheck();
			    if (board.isCheckmate(nextTurn)) {
				displayCheckmate();
			    }
			}
		    } else {

			if (board.getPieceOnCoordinate(yCoord, xCoord).getColor().equals(Color.BLACK) &&
			    nextTurn.equals(Color.BLACK)) {
			    nextTurn = Color.WHITE;

			    clicked = true;
			    selectedOldX = xCoord;
			    selectedOldY = yCoord;
			    selectedPiece = board.getPieceOnCoordinate(selectedOldY, selectedOldX);

			    System.out.println(xCoord + " x and y " + yCoord);

			} else if (board.getPieceOnCoordinate(yCoord, xCoord).getColor().equals(Color.WHITE) &&
				   nextTurn.equals(Color.WHITE)) {
			    nextTurn = Color.BLACK;

			    clicked = true;
			    selectedOldX = xCoord;
			    selectedOldY = yCoord;
			    selectedPiece = board.getPieceOnCoordinate(selectedOldY, selectedOldX);

			    System.out.println(xCoord + " x and y " + yCoord);
			}
		    }
		}
	    }
	});
    }

    /**
     * Uses JOptionPane to pop up show when check is flagged
     */

    private void displayCheck() {
	Color playerThatMovedLast = Color.WHITE;
	Color enemyColor = Color.BLACK;
	String enemyKingColor = "Black";

	if (selectedPiece.getColor().equals(Color.BLACK)) {
	    playerThatMovedLast = Color.BLACK;
	    enemyColor = Color.WHITE;
	    enemyKingColor = "White";
	}
	if (board.isItCheck(playerThatMovedLast)) {
	    String[] options = { "OK" };
	    JOptionPane.showOptionDialog(checkPopUp, enemyKingColor + " king is in Check!!!", "Chess Pop Up",
					 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					 iconMapping(new King(board, Piece.KING, enemyColor)), options, options[0]);
	}
    }

    private void displayCheckmate() {
	Color playerThatMovedLast = Color.WHITE;
	Color enemyColor = Color.BLACK;
	String enemyKingColor = "Black";

	if (selectedPiece.getColor().equals(Color.BLACK)) {
	    playerThatMovedLast = Color.BLACK;
	    enemyColor = Color.WHITE;
	    enemyKingColor = "White";
	}
	if (board.isItCheck(playerThatMovedLast)) {
	    String[] options = { "OK" };
	    JOptionPane.showOptionDialog(checkmatePopUp, enemyKingColor + " is in Checkmate  ;) ", "Chess Pop Up",
					 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					 iconMapping(new King(board, Piece.KING, enemyColor)), options, options[0]);
	}
    }

    @Override protected void paintComponent(Graphics g) {

	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	/**
	 *  Goes through all different coordinates in the board and calls the appropriate draw method
	 */
	try {
	    setBackground(g2d);
	} catch (RuntimeException e) {
	    e.printStackTrace();
	}

	for (int row = 0; row < board.getBoardHeight(); row++) {
	    for (int col = 0; col < board.getBoardWidth(); col++) {
		if (board.getPieceOnCoordinate(row, col).getPiece() != Piece.EMPTY) {
		    try {
			drawPieceImage(SQUARE_SIDE * col, SQUARE_SIDE * row, board.getPieceOnCoordinate(row, col), this, g2d);
		    } catch (RuntimeException e) {
			e.printStackTrace();
		    }
		}
	    }
	}
    }

    /**
     * Draws/Creates black background squares
     */
    private void drawBackgroundBlock(int row, int col, Graphics2D g2d) {

	int startY = row * SQUARE_SIDE;
	int startX = col * SQUARE_SIDE;

	g2d.setColor(Color.BLACK);
	g2d.fillRect(startX, startY, SQUARE_SIDE, SQUARE_SIDE);

	g2d.setColor(Color.BLACK);
	g2d.drawRect(startX, startY, SQUARE_SIDE, SQUARE_SIDE);
    }

    private void setBackground(Graphics2D g2d) {

	for (int row = 0; row < board.getBoardHeight(); row++) {
	    for (int column = 0; column < board.getBoardWidth(); column++) {

		if (row % 2 == 0) {

		    if ((column % 2) == 1) {
			drawBackgroundBlock(row, column, g2d);
		    }
		} else {

		    if ((column % 2) != 1) {
			drawBackgroundBlock(row, column, g2d);
		    }
		}
	    }
	}
    }

    /**
     * Gets the correct icon from iconMapping and then draws it, at the correct coordinates
     */
    private void drawPieceImage(int x, int y, ChessPiece chessPiece, JComponent jComponent, Graphics2D g2d) {

	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	iconMapping(chessPiece).paintIcon(jComponent, g2d, x, y);
    }

    /**
     * Maps a ChessPiece to a icon
     */
    private Icon iconMapping(ChessPiece chessPiece) {

	Map<Piece, Icon> iconMapping = new EnumMap<>(Piece.class);

	iconMapping.put(Piece.BISHOP, getPieceImage("bishop", chessPiece.getColor()));
	iconMapping.put(Piece.KING, getPieceImage("king", chessPiece.getColor()));
	iconMapping.put(Piece.PAWN, getPieceImage("pawn", chessPiece.getColor()));
	iconMapping.put(Piece.QUEEN, getPieceImage("queen", chessPiece.getColor()));
	iconMapping.put(Piece.KNIGHT, getPieceImage("hourse", chessPiece.getColor()));
	iconMapping.put(Piece.ROOK, getPieceImage("tower", chessPiece.getColor()));

	return iconMapping.get(chessPiece.getPiece());
    }

    /**
     * Read in the pic and cast them into Icon
     */
    private Icon getPieceImage(String pieceName, Color color) {

	String playerColor = null;



	BufferedImage myPic = null;
	if (color.equals(Color.WHITE)) {
	    playerColor = "white";
	} else if (color.equals(Color.BLACK)) {
	    playerColor = "black";
	}
	try {

	    myPic = ImageIO.read(
		    new File("src" + File.separator + "se" + File.separator + "liu" + File.separator + "ida" + File.separator +
			     "project" + File.separator + "tddd78" + File.separator + "chess" + File.separator +
			     "Chess-Pieces-Images" + File.separator + pieceName + playerColor + ".PNG"));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	assert myPic != null;
	Icon imageIcon = new ImageIcon(myPic);
	return imageIcon;
    }

    @Override public CompositeContext createContext(ColorModel srcColorModel, ColorModel dstColorModel, RenderingHints hints) {
	return null;
    }

    /**
     * If user fails to do a correct move this will reset all and give the user a new try
     */
    private void invalidMoveResetAll() {
	clicked = false;
	if (nextTurn.equals(Color.WHITE)) {
	    nextTurn = Color.BLACK;
	} else {
	    nextTurn = Color.WHITE;
	}
    }
}

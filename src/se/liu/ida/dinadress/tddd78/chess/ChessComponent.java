package se.liu.ida.dinadress.tddd78.chess;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.event.MouseInputAdapter;


/**
 * This makes the board graphic in forms of a backgrund and a diffrent ChessPieces
 */
public class ChessComponent extends JComponent implements Composite {

    private static final int SQUARE_SIDE = 90;

    private Board board;

    private JFrame checkPopUp = null;

    private int selectedOldX;
    private int selectedOldY;

    private int selectedNewX;
    private int selectedNewY;

    private ChessPiece selectedPiece = null;

    private boolean clicked = false;

    private Color nextTurn = Color.WHITE;

    /**
     * When getRGB is used on awt color the int used to represent white and black
     */

    private final static int INT_WHITE_COLOR_VALUE = -1;
    private final static int INT_BLACK_COLOR_VALUE = -16777216;

    public ChessComponent(Board board) {

        this.board = board;

        setPreferredSize(new Dimension(board.getBoardWidth() * SQUARE_SIDE, board.getBoardHeight() * SQUARE_SIDE));
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int xCoord = e.getX() / SQUARE_SIDE;
                int yCoord = e.getY() / SQUARE_SIDE;
                boolean checkOnFriendly = false;

                if (board.isCheckmate(nextTurn)) {
                    System.out.println("GameOver");
                }

                if ((xCoord <= 7 && xCoord >= 0) && (yCoord >= 0 && yCoord <= 7)) {

                    if (isClicked()) {

                        if ((! selectedPiece.validMove(selectedPiece, xCoord, yCoord, selectedOldX, selectedOldY)) ||
                                board.isItSelfCheck(selectedPiece.getColor(), selectedPiece, selectedOldX, xCoord,
                                        selectedOldY, yCoord)) {

                            checkOnFriendly = board.isItSelfCheck(selectedPiece.getColor(), selectedPiece,
                                    selectedOldX, xCoord, selectedOldY, yCoord);

                            invalidMoveResetAll();
                        } else {

                            setSelectedNewX(xCoord);
                            setSelectedNewY(yCoord);

                            if (selectedPiece.isPromoted()) {
                                selectedPiece = selectedPiece.promotedTo();
                            }

                            board.movePieceOnField(selectedPiece, selectedNewX, selectedNewY);
                            board.removeOldPiece(selectedOldX, selectedOldY);
                            setClicked(false);

                            repaint();
                            displayCheck(checkOnFriendly);

                        }
                    } else {

                        if (board.getPieceOnCoordinate(yCoord, xCoord).getColor().equals(Color.BLACK) &&
                                nextTurn.equals(Color.BLACK)) {
                            nextTurn = Color.WHITE;

                            setClicked(true);
                            setSelectedOldX(xCoord);
                            setSelectedOldY(yCoord);
                            setSelectedPiece(board.getPieceOnCoordinate(selectedOldY, selectedOldX));

                            System.out.println(xCoord + " x and y " + yCoord);

                        } else if (board.getPieceOnCoordinate(yCoord, xCoord).getColor().equals(Color.WHITE) &&
                                nextTurn.equals(Color.WHITE)) {
                            nextTurn = Color.BLACK;

                            setClicked(true);
                            setSelectedOldX(xCoord);
                            setSelectedOldY(yCoord);
                            setSelectedPiece(board.getPieceOnCoordinate(selectedOldY, selectedOldX));

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

    private void displayCheck(boolean checkOnFriendly) {
        Color playerThatMovedLast = Color.WHITE;
        Color enemyColor = Color.BLACK;
        String enemyKingColor = "Black";

        if (selectedPiece.getColor().equals(Color.BLACK)) {
            playerThatMovedLast = Color.BLACK;
            enemyColor = Color.WHITE;
            enemyKingColor = "White";
        }
        if (board.isItCheck(playerThatMovedLast) || checkOnFriendly) {
            String[] options = {
                    "OK"
            };
            int optionsChosen = JOptionPane.showOptionDialog(
                    checkPopUp,
                    enemyKingColor + " king is in Check!!! If you don't move your king out of danger you lose",
                    "Chess Pop Up",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    iconMaping(new King(board, Piece.KING, enemyColor)),
                    options,
                    options[0]
            );
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

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


    private void drawBackgrundBlock(int row, int col, Graphics2D g2d) {

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

                    if (column % 2 == 1) {
                        drawBackgrundBlock(row, column, g2d);
                    }
                } else {

                    if (column % 2 != 1) {
                        drawBackgrundBlock(row, column, g2d);

                    }
                }
            }
        }
    }

    /**
     *
     */
    private void drawPieceImage(int x, int y, ChessPiece chessPiece, JComponent jComponent, Graphics2D g2d) {

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        iconMaping(chessPiece).paintIcon(jComponent, g2d, x, y);
    }

    /**
     * Maps a chesspiece to a icon
     */
    private Icon iconMaping(ChessPiece chessPiece) {

        Map<Piece, Icon> iconMaping = new EnumMap<>(Piece.class);

        iconMaping.put(Piece.BISHOP, getPieceImage("bishop", chessPiece.getColor().getRGB()));
        iconMaping.put(Piece.KING, getPieceImage("king", chessPiece.getColor().getRGB()));
        iconMaping.put(Piece.PAWN, getPieceImage("pawn", chessPiece.getColor().getRGB()));
        iconMaping.put(Piece.QUEEN, getPieceImage("queen", chessPiece.getColor().getRGB()));
        iconMaping.put(Piece.KNIGHT, getPieceImage("hourse", chessPiece.getColor().getRGB()));
        iconMaping.put(Piece.ROOK, getPieceImage("tower", chessPiece.getColor().getRGB()));

        return iconMaping.get(chessPiece.getPiece());
    }


    //@Override
    //public Dimension getPreferredSize() {
      //  return super.getPreferredSize();
    //}

    /**
     * Read in the pic and cast them into Icon
     */
    private Icon getPieceImage(String pieceName, int colorIntValue) {
        // player color maight bee null

        String playerColor = null;

        BufferedImage myPic = null;
        if (colorIntValue == INT_WHITE_COLOR_VALUE) {
            playerColor = "white";
        } else if (colorIntValue == INT_BLACK_COLOR_VALUE) {
            playerColor = "black";
        }
        try {
            myPic = ImageIO.read(new File("src/se/liu/ida/dinadress/tddd78/chess/Chess-Pieces-Images/" + pieceName + playerColor + ".PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Icon imageIcon = new ImageIcon(myPic);
        return imageIcon;
    }

    @Override
    public CompositeContext createContext(ColorModel srcColorModel, ColorModel dstColorModel, RenderingHints hints) {
        return null;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isClicked() {
        return clicked;
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

    public void setSelectedNewX(int selectedNewX) {
        this.selectedNewX = selectedNewX;
    }

    public void setSelectedNewY(int selectedNewY) {
        this.selectedNewY = selectedNewY;
    }

    public void setSelectedOldX(int selectedOldX) {
        this.selectedOldX = selectedOldX;
    }

    public void setSelectedOldY(int selectedOldY) {
        this.selectedOldY = selectedOldY;
    }

    public void setSelectedPiece(ChessPiece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }
}

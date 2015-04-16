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
 * Paint class
 */
public class ChessComponent extends JComponent implements Composite {

    private static final int SQUARE_SIDE = 90;
    private Board board;

    private int selectedOldX;
    private int selectedOldY;

    private int selectedNewX;
    private int selectedNewY;

    private ChessPiece selectedPiece = null;

    private boolean clicked = false;

    public ChessComponent(Board board) {

        this.board = board;

        setPreferredSize(new Dimension(board.getBoardWidth() * SQUARE_SIDE, board.getBoardHeight() * SQUARE_SIDE));
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int xCoord = e.getX() / SQUARE_SIDE;
                int yCoord = e.getY() / SQUARE_SIDE;



                //if (isClicked() && selectedOldX == xCoord && selectedOldY == yCoord) {
                  //  setClicked(false);

                //}
                if (isClicked()) {
                    if (!selectedPiece.validMove(selectedPiece, xCoord, yCoord, selectedOldX, selectedOldY)) {
                        invalidMoveResetAll();
                    }
                    else {

                    setSelectedNewX(xCoord);
                    setSelectedNewY(yCoord);


                    //System.out.println(board.getPieceOnCoordinate(selectedOldY, selectedOldX).getColor()+"is clicked");
                    board.movePieceOnField(selectedPiece, selectedNewX, selectedNewY);
                    board.removeOldPiece(selectedOldX, selectedOldY);
                    setClicked(false);

                    repaint();
                    }
                }

                else {
                    setClicked(true);
                    setSelectedOldX(xCoord);
                    setSelectedOldY(yCoord);
                    System.out.println(xCoord + " x and y " + yCoord);
                    setSelectedPiece(board.getPieceOnCoordinate(selectedOldY, selectedOldX));
                }
            }
        });
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
    private void drawBlock(int row, int col, Graphics2D g2d) {
        /**
         * Background
         * Black frame around a square
         * Filled with mapped color
         */
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
                        drawBlock(row, column, g2d);
                    }
                } else {

                    if (column % 2 != 1) {
                        drawBlock(row, column, g2d);

                    }
                }
            }
        }
    }

    private void drawPieceImage(int x, int y, ChessPiece chessPiece , JComponent jComponent , Graphics2D g2d) {

            System.out.println(String.valueOf(chessPiece.getColor().getRGB()));

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        iconMaping(chessPiece).paintIcon(jComponent, g2d, x, y);
    }

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



    @Override public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }

    private Icon getPieceImage(String pieceName, int colorIntValue) {
        // player color maight bee null
        int intWhiteColorValue = -1;
        int intBlackColorValue = -16777216;
        String playerColor = null;

        BufferedImage myPic = null;
        if (colorIntValue == intWhiteColorValue) {
            playerColor = "white";
        }
        else if(colorIntValue == intBlackColorValue){
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

    private ChessPiece lookUpCoordinatesFromMouse(int y, int x) {
        ChessPiece currentPiece = board.getPieceOnCoordinate(y, x);
        System.out.println(currentPiece);
        return currentPiece;
    }

    public void invalidMoveResetAll(){
        setClicked(false);
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

    public ChessPiece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(ChessPiece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }


}

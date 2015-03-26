package se.liu.ida.dinadress.tddd78.chess;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 * Paint class
 */
public class ChessComponent extends JComponent {
    private static final int SQUARE_SIDE = 90;
    private Board board;
    //private final Icon king = null;


    public ChessComponent(Board board) {

        this.board = board;
        //this.king =
        setPreferredSize(new Dimension(board.getBoardWidth() * SQUARE_SIDE, board.getBoardHeight() * SQUARE_SIDE));
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        /**
         *  Goes through all different coordinates in the board and calls the appropriate draw method
         */
        setBackground(g2d);

        //for (int row = 0; row < board.getBoardHeight(); row++) {
        //for (int col = 0; col < board.getBoardWidth(); col++) {
        //drawBlock(row, col, g2d);

        //  }
        //}
        //drawPieceImage(90,90, "kingBlack", this, g2d);
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

    private void drawPieceImage(int x, int y, String pieceName, JComponent jComponent , Graphics2D g2d) {
        getPieceImage(pieceName).paintIcon(jComponent, g2d, x, y);
    }

    /**
     *
     *
     *

     private Icon IconMaping(Piece piece){
     Map<Piece, Icon> iconMaping = new EnumMap<>(Piece.class);
     iconMaping.put(Piece.BISHOP_BLACK, getPieceImage("bishopBlack"));
     iconMaping.put(Piece.BISHOP_WHITE, getPieceImage("bishopWhite"));

     iconMaping.put(Piece.KING_BLACK, getPieceImage("kingBlack"));
     iconMaping.put(Piece.KING_WHITE, getPieceImage("kingWhite"));

     iconMaping.put(Piece.PAWN_BLACK, getPieceImage("pawnBlack"));
     iconMaping.put(Piece.PAWN_WHITE, getPieceImage("pawnWhite"));

     iconMaping.put(Piece.QUEEN_BLACK, getPieceImage("queenBlack"));
     iconMaping.put(Piece.QUEEN_WHITE, getPieceImage("queenWhite"));

     iconMaping.put(Piece.KNIGHT_BLACK, getPieceImage("hourseBlack"));
     iconMaping.put(Piece.KNIGHT_WHITE, getPieceImage("hourseWhite"));

     *iconMaping.put(Piece.ROOK_BLACK, getPieceImage("towerBlack"));
     *iconMaping.put(Piece.ROOK_WHITE, getPieceImage("towerWhite"));

     * return iconMaping.get(piece);
     *}
     */

    private Color colorMapping(Piece piece) {
        /**
         * map each square type to a separate color
         */
        Map<Piece, Color> colorMapping = new EnumMap<>(Piece.class);

        //colorMapping.put(Piece.EMPTY_WHITE, Color.WHITE);
        //colorMapping.put(Piece.EMPTY_BLACK, Color.BLACK);
        colorMapping.put(Piece.BOARDER, Color.LIGHT_GRAY);


        return colorMapping.get(piece);
    }


    @Override public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
    private Icon getPieceImage(String pieceName) {
        BufferedImage myPic = null;
        try {
            myPic = ImageIO.read(new File("src/se/liu/ida/dinadress/tddd78/chess/Chess-Pieces-Images/" + pieceName + ".PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //JLabel picLabel = new JLabel(new ImageIcon(myPic));
        //return picLabel;
        Icon testIcon = new ImageIcon(myPic);
        return testIcon;

    }

}

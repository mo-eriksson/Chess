package se.liu.ida.dinadress.tddd78.chess;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 * Paint class
 */
public class ChessComponent extends JComponent implements Composite {
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


        for (int row = 0; row < board.getBoardHeight(); row++) {
            for (int col = 0; col < board.getBoardWidth(); col++) {
                if (board.getPieceFromCoordinate(row, col) != Piece.EMPTY) {
                    String player = "White";
                    if (col == 0 || col == 1){
                        player = "Black";
                    }
                    drawPieceImage(SQUARE_SIDE * row, SQUARE_SIDE * col, board.getPieceFromCoordinate(row, col), player, this, g2d);
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

    private void drawPieceImage(int x, int y, Piece piece, String player, JComponent jComponent , Graphics2D g2d) {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));
        IconMaping(piece, player).paintIcon(jComponent, g2d, x, y);
    }

    private Icon IconMaping(Piece piece, String player) {
        Map<Piece, Icon> iconMaping = new EnumMap<>(Piece.class);
        iconMaping.put(Piece.BISHOP, getPieceImage("bishop", player));
        //iconMaping.put(Piece.BISHOP_WHITE, getPieceImage("bishopWhite"));

        iconMaping.put(Piece.KING, getPieceImage("king", player));
        //iconMaping.put(Piece.KING_WHITE, getPieceImage("kingWhite"));

        iconMaping.put(Piece.PAWN, getPieceImage("pawn", player));
        //iconMaping.put(Piece.PAWN_WHITE, getPieceImage("pawnWhite"));

        iconMaping.put(Piece.QUEEN, getPieceImage("queen", player));
        //iconMaping.put(Piece.QUEEN_WHITE, getPieceImage("queenWhite"));

        iconMaping.put(Piece.KNIGHT, getPieceImage("hourse", player));
        //iconMaping.put(Piece.KNIGHT_WHITE, getPieceImage("hourseWhite"));

        iconMaping.put(Piece.ROOK, getPieceImage("tower", player));
        //iconMaping.put(Piece.ROOK_WHITE, getPieceImage("towerWhite"));

        return iconMaping.get(piece);
    }



    @Override public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
    private Icon getPieceImage(String pieceName, String player) {
        BufferedImage myPic = null;
        try {
            myPic = ImageIO.read(new File("src/se/liu/ida/dinadress/tddd78/chess/Chess-Pieces-Images/" + pieceName + player + ".PNG"));
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
}

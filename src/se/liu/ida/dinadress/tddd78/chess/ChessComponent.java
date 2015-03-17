package se.liu.ida.dinadress.tddd78.chess;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

/**
 * Paint class
 */
public class ChessComponent extends JComponent {
    private static final int SQUARE_SIDE = 120;
    private Board board;

    public ChessComponent(Board board) {

        this.board = board;
        setPreferredSize(new Dimension(board.getBoardWidth() * SQUARE_SIDE, board.getBoardHeight() * SQUARE_SIDE));
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        /**
         *  Goes through all different coordinates in the board and calls the appropriate draw method
         */

        for (int row = 0; row < board.getBoardHeight(); row++) {
            for (int col = 0; col < board.getBoardWidth(); col++) {
                drawBlock(row, col, g2d, board.getPieceFromCoordinate(col, row));

            }
        }
    }
    private void drawBlock(int row, int col, Graphics2D g2d, Piece piece) {
        /**
         * Responsible for drawing according to their square type
         * Black frame around a square
         * Filled with mapped color
         */
        int startY = row * SQUARE_SIDE;
        int startX = col * SQUARE_SIDE;

        g2d.setColor(colorMapping(piece));
        g2d.fillRect(startX, startY, SQUARE_SIDE, SQUARE_SIDE);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(startX, startY, SQUARE_SIDE, SQUARE_SIDE);
    }
    private Color colorMapping(Piece piece) {
        /**
         * map each square type to a separate color
         */
        Map<Piece, Color> colorMapping = new EnumMap<>(Piece.class);

        colorMapping.put(Piece.EMPTY_WHITE, Color.WHITE);
        colorMapping.put(Piece.EMPTY_BLACK, Color.BLACK);


        return colorMapping.get(piece);
    }


    @Override public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
}

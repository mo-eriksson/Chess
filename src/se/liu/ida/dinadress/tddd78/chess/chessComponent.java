package se.liu.ida.dinadress.tddd78.chess;

import javax.swing.*;
import java.awt.*;

public class chessComponent extends JComponent {
    private static final int SQUARE_WIDTH = 30;
    private Board board;

    public chessComponent(final Board board) {

        this.board = board;
    }

    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.drawRect(100,100,100,100);


    }
    private Graphics2D drawBoard() {
        for (int height = 0; height < board.getBoardHeight(); height++) {
            for (int width = 0; width < board.getBoardWidth(); width++) {
                if (board.getGameField()[height][width] == )

            }

        }

    }


    @Override public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
}

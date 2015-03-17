package se.liu.ida.dinadress.tddd78.chess;

import javax.swing.*;
import java.awt.*;

public class ChessFrame extends JFrame {
    private Board board;



    public ChessFrame(String title, Board board) throws HeadlessException {
      super(title);
      this.board = board;

      //this.setJMenuBar(createMenuBar());

      ChessComponent tetrisComponent = new ChessComponent(board); // add it self to board listener, moved to tetrisComponent
      //board.addBoardListener(tetrisComponent);

      this.setLayout(new BorderLayout());
      this.add(BorderLayout.CENTER, tetrisComponent);
      //this.add(BorderLayout.SOUTH, makePointJTextArea());

      this.pack();
      this.setVisible(true);
  }

    private JTextArea tempFunc() {
	// old, not used anymore
	JTextArea jTextArea = new JTextArea(BoardToTextConverter.convertToText(board));
	return jTextArea;
    }
}

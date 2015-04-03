package se.liu.ida.dinadress.tddd78.chess;

import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.awt.*;

public class ChessFrame extends JFrame{
  private Board board;



  public ChessFrame(String title, Board board) throws HeadlessException {
    super(title);
    this.board = board;

    //this.setJMenuBar(createMenuBar());

    ChessComponent chessComponent = new ChessComponent(board); // add it self to board listener, moved to tetrisComponent
    //board.addBoardListener(tetrisComponent);


    this.setLayout(new BorderLayout());
    this.add(BorderLayout.CENTER, chessComponent);

    //this.add(BorderLayout.SOUTH, makePointJTextArea());

    this.pack();
    this.setVisible(true);
  }

}

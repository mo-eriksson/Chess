package se.liu.ida.dinadress.tddd78.chess;

import javax.swing.*;
import java.awt.*;

public class ChessFrame extends JFrame {

    private Board board;
    private JFrame popUpFrame = null;


    public ChessFrame(String title, Board board) throws HeadlessException {
        super(title);
        this.board = board;

        this.setJMenuBar(createMenuBar());

        ChessComponent chessComponent = new ChessComponent(board); // add it self to board listener, moved to tetrisComponent

        this.setLayout(new BorderLayout());

        this.add(BorderLayout.CENTER, chessComponent);

        this.pack();
        this.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        final JMenu menu = new JMenu("Menu");

        JMenuItem restartButton = new JMenuItem("Restart");
        menu.add(restartButton);

        /**
         * Anonymous ActionListener replaced with lambda
         */
        restartButton.addActionListener(e -> {
            Object[] options = {
                    "YES",
                    "NO"
            };
            int optionsChosen = JOptionPane.showOptionDialog(
                    popUpFrame,
                    "Restart really???",
                    "Restart POPUP",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]
            );
            if (optionsChosen == JOptionPane.YES_OPTION) {
                //dispose();
                // lägg till new game funk...

            }
        });

        menu.addSeparator();

        JMenuItem quitButton = new JMenuItem("Quit");
        menu.add(quitButton);
        /**
         * replaced with lambda
         */
        quitButton.addActionListener(e -> {
            Object[] options = {
                    "YES",
                    "NO"
            };
            int optionsChosen = JOptionPane.showOptionDialog(
                    popUpFrame,
                    "Quit really???",
                    "EXIT POPUP",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]
            );
            if (optionsChosen == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        final JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(menu);

        return jMenuBar;
    }
}

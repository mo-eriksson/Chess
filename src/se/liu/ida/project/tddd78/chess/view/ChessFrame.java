package se.liu.ida.project.tddd78.chess.view;

import se.liu.ida.project.tddd78.chess.model.Board;

import javax.swing.*;
import java.awt.*;

/**
 * This class handles, pop up frames, the menu bar and disposing of old frames
 */

public class ChessFrame extends JFrame {

    private JFrame popUpFrame = null;

    public ChessFrame(String title, Board board) throws HeadlessException {
        super(title);

        this.setJMenuBar(createMenuBar());

        ChessComponent chessComponent = new ChessComponent(board);

        this.setLayout(new BorderLayout());

        this.add(BorderLayout.CENTER, chessComponent);

        this.pack();
        this.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        final JMenu menu = new JMenu("Menu");

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

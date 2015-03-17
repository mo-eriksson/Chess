import javax.swing.*;
import java.awt.*;

public class chessFrame extends JFrame {
    private Board board;



    public chessFrame(final String title, final Board board) throws HeadlessException {
	super(title);
	this.board = board;
	this.setSize(500,500);

	JComponent testJComponent = new chessComponent(board);
	this.setLayout(new BorderLayout());
	this.add(testJComponent, BorderLayout.CENTER);

	this.pack();
	this.setVisible(true);
    }

    private JTextArea tempFunc() {
	// old, not used anymore
	JTextArea jTextArea = new JTextArea(BoardToTextConverter.convertToText(board));
	return jTextArea;
    }
}

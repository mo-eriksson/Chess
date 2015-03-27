package se.liu.ida.dinadress.tddd78.chess;

import javafx.scene.input.MouseEvent;

public class HandleMouseEvent implements MouseListener {

    private double x;
    private double y;
    private boolean clicked;

    public HandleMouseEvent(double x, double y, boolean clicked) {
        this.x = x;
        this.y = y;
        this.clicked = clicked;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();

    }

    public boolean isClicked() {
        return clicked;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
}

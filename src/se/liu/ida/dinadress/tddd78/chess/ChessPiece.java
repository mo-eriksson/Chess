package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

public interface ChessPiece {

    public boolean isMovable();

    public int getXCoordinate();

    public int getYCoordinate();

    public void getType();

    public Color getColor();
}

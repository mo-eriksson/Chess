package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;

/**
 * The empty "piece" act like all the other abstract chesspieces but are used to represent empty
 * cordinates on the gamefield.
 */
public class Empty extends AbstractChessPiece {

    public Empty(Board board, Piece piece, Color color) {
        super(board, piece, color);
    }

}

package se.liu.ida.dinadress.tddd78.chess;

import java.util.ArrayList;

/**
 * The class that is responsible for keeping check on the board and tell other classes when it is updated
 */

public class Board {
    private static int BOTTOM_BOARDER = 1;
    private static int LEFT_BOARDER = -1;
    private static int BOARD_WIDTH_PLUS_ONE = 9;

    private int boardHeight;
    private int boardWidth;

    private Piece[][] gameField;

    public Board(int boardHeight, int boardWidth) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.gameField = new Piece[boardHeight][boardWidth];

        setStartPosition();

    }
    public void setStartPosition() {
        for (int row = 0; row < boardHeight; row++) {
            for (int column = 0; column < boardWidth; column++) {

                if (column == 0 || column == 7) {
                    setThisRow(row, column);
                }
                    else if (column == 1 || column == 6) {
                        gameField[row][column] = Piece.PAWN;
                    }
                    else {
                        gameField[row][column] = Piece.EMPTY;
                    }
                }
            }
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                System.out.println(getPieceFromCoordinate(i,j));
            }
        }
        }

    private void setThisRow(int row, int column) {
            switch (row) {

                case 0:
                    gameField[row][column] = Piece.ROOK;
                    break;
                case 1:
                    gameField[row][column] = Piece.KNIGHT;
                    break;
                case 2:
                    gameField[row][column] = Piece.BISHOP;
                    break;
                case 3:
                    gameField[row][column] = Piece.QUEEN;
                    break;
                case 4:
                    gameField[row][column] = Piece.KING;
                    break;
                case 5:
                    gameField[row][column] = Piece.BISHOP;
                    break;
                case 6:
                    gameField[row][column] = Piece.KNIGHT;
                    break;
                case 7:
                    gameField[row][column] = Piece.ROOK;
                    break;

            }
        }
    private Piece setPiecePos(int col){
        ArrayList<Piece> pieceList = new ArrayList<>();
        pieceList.add(Piece.ROOK);
        pieceList.add(Piece.KNIGHT);
        pieceList.add(Piece.BISHOP);
        pieceList.add(Piece.QUEEN);
        pieceList.add(Piece.KING);
        pieceList.add(Piece.BISHOP);
        pieceList.add(Piece.KNIGHT);
        pieceList.add(Piece.ROOK);
        return pieceList.get(col);
    }



    public Piece getPieceFromCoordinate(int row, int column) {
        Piece thisIsOnCoordinate = gameField[row][column];
        return thisIsOnCoordinate;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public Piece[][] getGameField() {
        return gameField;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public void setGameField(Piece[][] gameField) {
        this.gameField = gameField;
    }
}

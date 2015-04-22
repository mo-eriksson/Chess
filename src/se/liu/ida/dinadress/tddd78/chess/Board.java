package se.liu.ida.dinadress.tddd78.chess;

import java.awt.*;


/**
 * The class that is responsible for keeping check on the board and tell other classes when it is updated
 */

public class Board {


    private int boardHeight;
    private int boardWidth;
    private Color nextTurn;

    private ChessPiece[][] gameField;

    public Board(int boardHeight, int boardWidth) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.gameField = new ChessPiece[boardHeight][boardWidth];

        setStartPosition();

    }

    public void setStartPosition() {
        for (int row = 0; row < boardHeight; row++) {
            for (int column = 0; column < boardWidth; column++) {

                if (row == 0) {
                    setThisRow(row, column, Color.BLACK);
                } else if (row == 7) {
                    setThisRow(row, column, Color.WHITE);
                } else if (row == 1) {
                    gameField[row][column] = new Pawn(this, Piece.PAWN, Color.BLACK);
                } else if (row == 6) {
                    gameField[row][column] = new Pawn(this, Piece.PAWN, Color.WHITE);
                } else {
                    gameField[row][column] = new Empty(this, Piece.EMPTY, Color.BLUE);
                }

            }
        }
    }


    private void setThisRow(int row, int column, Color color) {
        switch (column) {

            case 0:
                gameField[row][column] = new Rook(this, Piece.ROOK, color);
                break;
            case 1:
                gameField[row][column] = new Knight(this, Piece.KNIGHT, color);
                break;
            case 2:
                gameField[row][column] = new Bishop(this, Piece.BISHOP, color);
                break;
            case 3:
                gameField[row][column] = new Queen(this, Piece.QUEEN, color);
                break;
            case 4:
                gameField[row][column] = new King(this, Piece.KING, color);
                break;
            case 5:
                gameField[row][column] = new Bishop(this, Piece.BISHOP, color);
                break;
            case 6:
                gameField[row][column] = new Knight(this, Piece.KNIGHT, color);
                break;
            case 7:
                gameField[row][column] = new Rook(this, Piece.ROOK, color);
                break;

        }
    }

    public void movePieceOnField(ChessPiece chessPiece, int xNew, int yNew) {
        gameField[yNew][xNew] = chessPiece;
    }

    public void removeOldPiece(int selectedOldX, int selectedOldY) {
        gameField[selectedOldY][selectedOldX] = new Empty(this, Piece.EMPTY, Color.BLUE);

    }

    public void captureEmemyPiece(int yNew, int xNew) {
        gameField[yNew][xNew] = new Empty(this, Piece.EMPTY, Color.BLUE);

    }

    public ChessPiece getPieceOnCoordinate(int row, int column) {
        ChessPiece thisIsOnCoordinate = gameField[row][column];
        return thisIsOnCoordinate;
    }

    private boolean isValidMove(ChessPiece chessPiece, int xNew, int yNew) {
        boolean validMove = false;
        return validMove;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public ChessPiece[][] getGameField() {
        return gameField;
    }

    private int findKingX(Color color) {
        int kingXPosition = -1;
        for (int row = 0; row < boardHeight; row++) {
            for (int column = 0; column < boardWidth; column++) {
                if (gameField[row][column].getPiece() == Piece.KING && gameField[row][column].getColor().equals(color)) {
                    kingXPosition = column;
                }
            }
        }
        if (kingXPosition == -1) {
            throw new IllegalArgumentException("Did not found any king on the field");
        }
        return kingXPosition;
    }

    private int findKingY(Color color) {
        int kingYPosition = -1;
        for (int row = 0; row < boardHeight; row++) {
            for (int column = 0; column < boardWidth; column++) {
                if (gameField[row][column].getPiece() == Piece.KING && gameField[row][column].getColor().equals(color)) {
                    kingYPosition = row;
                }
            }
        }
        if (kingYPosition == -1) {
            throw new IllegalArgumentException("Did not found any king on the field");
        }
        return kingYPosition;
    }

    public boolean isItCheck(Color color) {
        Color enemyColor = Color.BLACK;
        if (color.equals(Color.BLACK)) {
            enemyColor = Color.WHITE;
        }

        boolean check = false;
        for (int row = 0; row < boardHeight; row++) {
            for (int column = 0; column < boardWidth; column++) {
                if (gameField[row][column].getPiece() != Piece.KING) {
                    if (gameField[row][column].validMove(gameField[row][column], findKingX(enemyColor), findKingY(enemyColor), column, row)) {
                        check = true;
                        //System.out.println("test");
                    }
                }
            }
        }
        return check;
    }
}

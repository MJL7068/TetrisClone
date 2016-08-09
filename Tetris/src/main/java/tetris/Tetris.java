package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import tetris.pieces.*;
import tetris.userinterface.*;

public class Tetris extends Timer implements ActionListener {

    private int[][] squares;
    private Updateable gameBoard;

    private Piece piece;
    private int pieceNumber;
    private String[] tetrominos;
    private int endingY;
    
    private int nextPiece;
    
    ArrayList<Block> squaresToUpdate;

    public Tetris(int height, int widht) {
        super(500, null);

        this.squares = new int[height][widht];
        this.tetrominos = new String[]{"sblock", "zblock", "tblock", "iblock", "jblock", "lblock", "oblock"};
        this.endingY = -1;
        this.pieceNumber = -1;
        
        this.squaresToUpdate = new ArrayList<>();
        
        this.nextPiece = generateNextPieceNumber();

        this.piece = new Piece();
        setPiece();
        updatePiece();

        addActionListener(this);
    }

    public void setGameBoard(Updateable gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int getSquare(int x, int y) {
        return squares[y][x];
    }

    public int[][] getSquares() {
        return squares;
    }

    public void updatePiece() {
        for (Block block : piece.getPiece()) {
            squares[block.getY()][block.getX()] = pieceNumber;
        }
    }

    public boolean isMoveLegal(Block block, int dx, int dy) {
        int newX = block.getX() + dx;
        int newY = block.getY() + dy;
        if (newX < squares[0].length && newX >= 0 && newY < squares.length && newY >= 0 && (squares[newY][newX] == 0 || piece.getPiece().contains(new Block(newX, newY)))) {
            return true;
        }
        return false;
    }

    public void moveDown() {
        boolean move = true;
        for (Block block : piece.getPiece()) {
            if (!isMoveLegal(block, 0, 1)) {
                move = false;
                continue;
            }
        }

        if (move) {
            for (Block block : piece.getPiece()) {
                squares[block.getY()][block.getX()] = 0;
                block.move(0, 1);
                squares[block.getY()][block.getX()] = pieceNumber;
            }
        }
    }

    public void moveLeft() {
        boolean move = true;
        for (Block block : piece.getPiece()) {
            if (!isMoveLegal(block, -1, 0)) {
                move = false;
            }
        }

        if (move) {
            for (int i = 0; i < piece.getPiece().size(); i++) {
                Block block = piece.getPiece().get(i);
                squares[block.getY()][block.getX()] = 0;
                block.move(-1, 0);
                squares[block.getY()][block.getX()] = pieceNumber;
            }
        }
    }

    public void moveRight() {
        boolean move = true;
        for (Block block : piece.getPiece()) {
            if (!isMoveLegal(block, 1, 0)) {
                move = false;
            }
        }

        if (move) {
            for (int i = piece.getPiece().size() - 1; i >= 0; i--) {
                Block block = piece.getPiece().get(i);
                squares[block.getY()][block.getX()] = 0;
                block.move(1, 0);
                squares[block.getY()][block.getX()] = pieceNumber;
            }
        }        
    }

    public void rotatePiece() {
        ArrayList<Block> newBlocks = piece.getRotatedPiece();
        boolean clear = true;
        for (Block block : newBlocks) {
            if ((squares[block.getY()][block.getX()] != 0 && !piece.getPiece().contains(new Block(block.getX(), block.getY()))) || block.getY() >= squares.length) {
                clear = false;
            }
        }

        if (clear) {
            for (Block block : piece.getPiece()) {
                squares[block.getY()][block.getX()] = 0;
            }            

            piece.setNewBlocks(newBlocks);
            updatePiece();
        } else {
            for (Block block : piece.getPiece()) {
                squares[block.getY()][block.getX()] = pieceNumber;
            }
        }
    }

    public void checkIfEnd() {
        for (Block block : piece.getPiece()) {            
            if (block.getY() == squares.length - 1 || (squares[block.getY() + 1][block.getX()] != 0 && !piece.getPiece().contains(new Block(block.getX(), block.getY() + 1)))) {
                this.endingY = block.getY();
                checkIfGameOver();
                setPiece();                
                break;
            }
        }
    }
    
    public void checkIfGameOver() {
        for (Block block : piece.getPiece()) {
            if (block.getY() == 0) {
                gameOver();
                break;
            }
        }
    }
    
    public void gameOver() {
//        gameBoard.gameOver();
        this.stop();
    }

    public void checkIfFullRow(int row) {
        boolean fullRow = true;
        for (int i = 0; i < squares[row].length; i++) {
            if (squares[row][i] == 0) {
                fullRow = false;
                break;
            }
        }

        if (fullRow) {
            clearRow(row);
            if (row > 0) {
                checkIfFullRow(row - 1);
            }

            for (int i = row - 1; i > 0; i--) {
                for (int j = 0; j < squares[i].length; j++) {
                    if (squares[i][j] != 0 && !piece.getPiece().contains(new Block(j, i))) {
                        squares[i + 1][j] = squares[i][j];
                        squares[i][j] = 0;
                    }
                }
            }

            gameBoard.update();
        }
    }

    public void clearRow(int row) {
        for (int i = 0; i < squares[row].length; i++) {
            squares[row][i] = 0;
        }

    }

    public void setPiece() {
        pieceNumber = new Random().nextInt(7) + 1;
        piece.setTetromino(tetrominos[nextPiece]);
        nextPiece = generateNextPieceNumber();               
    }

    public String getNextPiece() {
        return tetrominos[nextPiece];
    }        
    
    public int generateNextPieceNumber() {
        return new Random().nextInt(7);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (endingY >= 0) {
            checkIfFullRow(endingY);
            endingY = -1;
        }
        
        moveDown();
        
        checkIfEnd();
        gameBoard.update();      
    }
    
    public void update() {
        gameBoard.update();
    }

}

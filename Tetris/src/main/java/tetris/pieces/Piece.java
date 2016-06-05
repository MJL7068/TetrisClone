package tetris.pieces;

import java.util.ArrayList;
import tetris.pieces.tetrominos.*;

public class Piece {
    private ArrayList<Block> oldBlocks;
    private Tetromino tetromino;
    
    public Piece() {
        this.oldBlocks = new ArrayList<>();
    }
    
    public void setTetromino(String piece) {
        switch (piece) {
            case "sblock": 
                this.tetromino = new Sblock();
                break;
            case "zblock":
                this.tetromino = new Zblock();
                break;
            case "tblock": 
                this.tetromino = new Tblock();
                break;
            case "iblock":
                this.tetromino = new Iblock();
                break;
            case "jblock":
                this.tetromino = new Jblock();
                break;
            case "lblock":
                this.tetromino = new Lblock();
                break;
            case "oblock":
                this.tetromino = new Oblock();              
                break;
        }
                
        tetromino.resetCounter();
        tetromino.reset();
    }
    
    public ArrayList<Block> getRotatedPiece() {
        ArrayList<Block> tetr = tetromino.getNewPosition();
        ArrayList<Block> newBlocks = new ArrayList<>();
        ArrayList<Block> oldBlocks = tetromino.getBlocks();
        int lowY = oldBlocks.get(0).getY();
        int x = ((oldBlocks.get(0).getX() + oldBlocks.get(oldBlocks.size() - 1).getX()) / 2);
        for (int i = 0; i < tetr.size(); i++) {
            Block newBlock = new Block((tetr.get(i).getX() + x), (tetr.get(i).getY() + lowY));
            newBlocks.add(newBlock);
        }

        return newBlocks;
    }
    
    public void setNewBlocks(ArrayList<Block> newBlocks) {
        tetromino.clearBlocks();
        tetromino.setBlocks(newBlocks);
    }
    
    public void move(int dx, int dy) {
        for (Block block : tetromino.getBlocks()) {
            block.move(dx, dy);
        }
    }
    
    public ArrayList<Block> getPiece() {
        return tetromino.getBlocks();
    }
}

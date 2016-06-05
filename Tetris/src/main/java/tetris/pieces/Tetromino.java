package tetris.pieces;
import java.util.ArrayList;

public abstract class Tetromino {
    private ArrayList<Block> blocks;
    private ArrayList<ArrayList<Block>> positions;
    
    public Tetromino() {
        this.blocks = new ArrayList<>();
        this.positions = new ArrayList<>();
    }
    
    public void addBlock(Block block) {
        blocks.add(block);
    }
    
    public void addPosition(ArrayList<Block> position) {
        positions.add(position);
    }
    
    public ArrayList<Block> getPosition(int k) {
        return positions.get(k);
    }
    
    public ArrayList<Block> getBlocks() {
        return blocks;
    }
    
    public void clearBlocks() {
        blocks.clear();
    }
    
    public void setBlocks(ArrayList<Block> newBlocks){
        blocks = newBlocks;
    }
    
    public abstract void resetCounter();
    public abstract void reset();
    public abstract ArrayList<Block> getNewPosition();
}

package tetris.pieces.tetrominos;

import java.util.ArrayList;
import tetris.pieces.Block;
import tetris.pieces.Tetromino;

public class Zblock extends Tetromino {
    private int counter;
    private ArrayList<Block> originalPosition;
    
    public Zblock() {
        this.counter = 1;
        this.originalPosition = new ArrayList<>();
        
        Block original1 = new Block(5, 1);
        originalPosition.add(original1);
        Block original2 = new Block(6, 1);
        originalPosition.add(original2);
        Block original3 = new Block(4, 0);
        originalPosition.add(original3);
        Block original4 = new Block(5, 0);
        originalPosition.add(original4);
        
        ArrayList<Block> piece1 = new ArrayList<>();
        Block block2 = new Block(0, 1);
        piece1.add(block2);
        Block block1 = new Block(1, 1);
        piece1.add(block1);
        Block block4 = new Block(-1, 0);
        piece1.add(block4);
        Block block3 = new Block(0, 0);
        piece1.add(block3);
        
        super.addPosition(piece1);

        ArrayList<Block> piece2 = new ArrayList<>();
        Block block22 = new Block(0, 1);
        piece2.add(block22);
        Block block21 = new Block(0, 0);
        piece2.add(block21);
        Block block24 = new Block(1, 0);
        piece2.add(block24);
        Block block23 = new Block(1, -1);
        piece2.add(block23);

        super.addPosition(piece2);
        
//        reset();
    }

    @Override
    public void resetCounter() {
        counter = 1;
    }
    
    @Override
    public void reset() {
        super.setBlocks(originalPosition);
    }

    @Override
    public ArrayList<Block> getNewPosition() {
        ArrayList<Block> r = super.getPosition(counter);
        if (counter == 0) {
            counter = 1;
        } else if (counter == 1) {
            counter = 0;
        }

        return r;
    }
    
}

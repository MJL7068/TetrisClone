package tetris.userinterface;
import java.util.ArrayList;
import tetris.pieces.Block;

public interface Updateable {
    void update();
    void updateSquare(ArrayList<Block> blocks);

    void gameOver();
}

package tetris.userinterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.Tetris;

public class KeyboardListener  implements KeyListener {
    private Tetris tetris;
    
    public KeyboardListener(Tetris tetris) {
        this.tetris = tetris;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            tetris.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tetris.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {            
            tetris.rotatePiece();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            tetris.moveDown();
        }
        
        tetris.update();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}

package tetris.userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import tetris.Tetris;
import tetris.pieces.Block;

public class DrawBoard extends JPanel implements Updateable {

    private Tetris tetris;

    private Color[] colors;

    public DrawBoard(Tetris tetris) {
        this.tetris = tetris;

        this.colors = new Color[]{Color.WHITE, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.gray, Color.CYAN};
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        this.setBackground(Color.white);                

        for (int i = 0; i < tetris.getSquares().length; i++) {
            for (int j = 0; j < tetris.getSquares()[i].length; j++) {
                int number = tetris.getSquare(j, i);

                if (number != 0) {
                    graphics.setColor(colors[number]);
                    graphics.fill3DRect(j * 20, i * 20, 20, 20, true);
                }
            }
        }
        
        graphics.setColor(Color.black);
        graphics.drawString(tetris.getNextPiece(), 10, 10);
    }

    @Override
    public void update() {
        this.repaint();
    }

}

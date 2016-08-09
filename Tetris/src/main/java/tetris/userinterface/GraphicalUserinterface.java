package tetris.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import tetris.Tetris;
import tetris.pieces.Block;

public class GraphicalUserinterface  implements Runnable {

    private JFrame frame;
    private Tetris tetris;
    private JLabel[][] squares;
    private Color[] colors;    

    private int luku;
    
    private DrawBoard dboard;

    public GraphicalUserinterface(Tetris tetris) {
        this.tetris = tetris;
        this.squares = new JLabel[tetris.getSquares().length][tetris.getSquares()[0].length];
        this.colors = new Color[]{Color.WHITE, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.gray, Color.CYAN};

        this.luku = 1;
    }

    @Override
    public void run() {
        this.frame = new JFrame("Tetris");

        frame.setPreferredSize(new Dimension((12 * 20 + 20), (28 * 20)));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        frame.addKeyListener(new KeyboardListener(tetris));

        this.dboard = new DrawBoard(tetris);
        container.add(dboard);
    }
    
//    public void setNextPieceLabel(String nextPiece) {
//        nextPieceLabel.setText(nextPiece);
//    }
//    
//    public JPanel createBoard() {
//        JPanel board = new JPanel(new GridLayout(squares.length, squares[0].length));
//        
//        for (int y = 0; y < squares.length; y++) {
//            for (int x = 0; x < squares[y].length; x++) {
//                JLabel newLabel = new JLabel();
//                int number = tetris.getSquare(x, y);
//                squares[y][x] = newLabel;
//                newLabel.setOpaque(true);
//                setColor(newLabel, number);
//                board.add(newLabel);
//            }
//        }
//        
//        return board;
//    }
//
    public DrawBoard getDrawBoard() {
        return dboard;
    }
//
//    public void setColor(JLabel label, int number) {
//        if (number == 0) {
//            label.setBackground(colors[0]);
//            label.setBorder(null);
//        }  else {
//            label.setBackground(colors[number]);
//            label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
//        }
//    }
//
//    @Override
//    public void update() {
////        for (int i = 0; i < squares.length; i++) {
////            for (int j = 0; j < squares[i].length; j++) {
////                int number = tetris.getSquare(j, i);
////                setColor(squares[i][j], number);
////            }
////        }
//    }
//        
//    public void updateSquare(ArrayList<Block> blocks) {
//        for (Block block : blocks) {
//            int y = block.getY();
//            int x = block.getX();
//            int number = tetris.getSquare(x, y);
//            setColor(squares[y][x], number);
////            squares[y][x].setText("" + number);
//            
//            nextPieceLabel.setText(tetris.getNextPiece());
//        }
//    }
//        
//    public void gameOver() {
//        JOptionPane.showMessageDialog(frame, "GAME OVER!");
//    }
}

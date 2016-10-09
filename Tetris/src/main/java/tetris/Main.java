package tetris;
import javax.swing.SwingUtilities;
import tetris.userinterface.*;

public class Main {

    public static void main(String[] args) {
        Tetris tetris = new Tetris(26, 12);
        GraphicalUserinterface gui = new GraphicalUserinterface(tetris);
        
        SwingUtilities.invokeLater(gui);  
        
//        tetris.setGameBoard(gui);
        
        while (gui.getDrawBoard() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
        
        tetris.setGameBoard(gui.getDrawBoard());
        tetris.start();
    }
    
}

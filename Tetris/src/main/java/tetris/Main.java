package tetris;
import javax.swing.SwingUtilities;
import tetris.userinterface.*;

public class Main {

    public static void main(String[] args) {
        Tetris tetris = new Tetris(26, 12);
        GraphicalUserinterface gui = new GraphicalUserinterface(tetris);
        
        SwingUtilities.invokeLater(gui);  
        
        tetris.setGameBoard(gui);
        tetris.start();
    }
    
}

/* 
 * TCSS 305 Autumn 2014
 * Assignment 6 - Tetris
 */

package view;

/**
 * Tetris Main method that executes the game.
 * 
 * @author Chris Kubec
 * @version Autumn 2014
 */
public final class TetrisMain {

    /**
     * Default private Constructor for Tetris.
     */
    private TetrisMain() {
        // Private main method to prevent instantiation of main runner class.
    }

    /**
     * Create and run Tetris using the Tetris Frame.
     * 
     * @param theArgs String array passed in during the start of Tetris.
     */
    public static void main(final String[] theArgs) {
        final TetrisGUI tetrisFrame = new TetrisGUI();
        tetrisFrame.setVisible(true);
    }
}

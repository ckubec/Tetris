/* 
 * TCSS 305 Autumn 2014
 * Assignment 5 - Power Paint
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * Create the Central Panel in the Tetris Game where the blocks fall onto the screen.
 * 
 * @author Chris Kubec
 * @version Autumn 2014
 */
@SuppressWarnings("serial") //TODO Figure out Implementation for later use.
public class TetrisCenterBoard extends JPanel implements Observer {
    /** Default width of Panel. */
    public static final int WIDTH = 300;
    /** Default Height of Panel. */
    public static final int HEIGHT = 600;
    /** Default Block Width and Height. */
    public static final int BLOCK_WIDTH = 30;
    /** The number of blocks per row counting walls. */
    private static final int BLOCK_PER_ROW = 12;
    /** The starting location of the board in the String. */
    private static final int BOARD_START = 49;
    /** String Representing the Tetris Board. */
    private String myBoard;
    
    /**
     * Create the Tetris Board in the GUI.
     */
    public TetrisCenterBoard() {
        super(true);
        setSize(WIDTH, HEIGHT);
        setPreferredSize(getSize());
        setBackground(Color.DARK_GRAY);
        myBoard = "";
    }
    
    /**
     * Paints the pieces on the JPanel in the Center of the screen.
     * 
     * @param theGraphics Takes a Graphics as a parameter to be used by paintComponent.
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        int columnCount = 0;
        int rowCount = 0;
        
        for (int i = BOARD_START; i < myBoard.length(); i++) {
            final String subline = myBoard.substring(i, i + 1);
            
            if ("*".equals(subline)) {
                g2d.fill3DRect(BLOCK_WIDTH * rowCount, BLOCK_WIDTH * columnCount, 
                             BLOCK_WIDTH, BLOCK_WIDTH, true);
            } else if ("X".equals(subline)) {
                g2d.setColor(Color.BLUE);
                g2d.fill3DRect(BLOCK_WIDTH * rowCount, BLOCK_WIDTH * columnCount, 
                             BLOCK_WIDTH, BLOCK_WIDTH, true);
            }
            rowCount++;

            if (rowCount > BLOCK_PER_ROW) {
                rowCount = 0;
                columnCount++;
            }
        }      
    }

    /**
     * Updates the panel used for Tetris pieces.
     * 
     * @param theObserver Passed from Observable helps define where pieces are.
     * @param theArg null object passed from Observable.
     */
    public void update(final Observable theObserver, final Object theArg) {
        myBoard = theObserver.toString();
        repaint();
    }
}

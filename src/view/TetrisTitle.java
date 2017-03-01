/* 
 * TCSS 305 Autumn 2014
 * Assignment 5 - Power Paint
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Create Tetris Title.
 * 
 * @author Chris Kubec
 * @version Autumn 2014
 */
@SuppressWarnings("serial") //TODO Figure out Implementation for later use.
public class TetrisTitle extends JPanel {
    /** Color used as background of Title. */
    public static final Color DARK_BLUE = new Color(61, 89, 171);
    /** Default Width of Panel. */
    private static final int[] PANEL_WIDTH = {250, 500};
    /** Default Height of Panel. */
    private static final int[] PANEL_HEIGHT = {60, 120};
    /** Width and Height used by Blocks. */
    private static final int[] BLOCK_WIDTH = {10, 20};
    /** Box Locations of Title Letters. */
    private static final int[][][] TITLE = {
        {{0, 0}, {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {2, 0}},
        {{4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {5, 0}, {5, 2}, {5, 4}, {6, 0}, {6, 4}},
        {{8, 0}, {9, 0}, {9, 1}, {9, 2}, {9, 3}, {9, 4}, {10, 0}}, 
        {{12, 0}, {12, 1}, {12, 2}, {12, 3}, {12, 4}, {13, 0}, {13, 2}, {14, 0}, {14, 1}, 
            {14, 3}, {14, 4}},
        {{16, 0}, {16, 4}, {17, 0}, {17, 1}, {17, 2}, {17, 3}, {17, 4}, {18, 0}, {18, 4}},
        {{20, 0}, {20, 1}, {20, 2}, {20, 4}, {21, 0}, {21, 2}, {21, 4}, {22, 0}, {22, 2}, 
            {22, 3}, {22, 4}}};
    /** Title Block Colors. Last color is Purple as Magenta is ugly. */
    private static final Color[] COLORS = {Color.RED, Color.ORANGE, Color.YELLOW,
        Color.GREEN, Color.CYAN, new Color(128, 0, 128)};
    /** Determines default size. */
    private final int mySize;
    
    /**
     * Create the Title JPanel.
     * 
     * @param theSize pass in a 0 or 1 for small or large sized title bar.
     */
    public TetrisTitle(final int theSize) {
        super(true);
        mySize = theSize;
        setSize(PANEL_WIDTH[mySize], PANEL_HEIGHT[mySize]);
        setPreferredSize(getSize());
        setBackground(DARK_BLUE);
    }
    
    /**
     * Draw Title Screen.
     * 
     * @param theGraphics Pass in a Graphic component to draw on Panel with.
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < TITLE.length; i++) {
            g2d.setColor(COLORS[i]);
            for (int j = 0; j < TITLE[i].length; j++) {
                g2d.fill3DRect(TITLE[i][j][0] * BLOCK_WIDTH[mySize] + BLOCK_WIDTH[mySize], 
                               TITLE[i][j][1] * BLOCK_WIDTH[mySize] + BLOCK_WIDTH[mySize] / 2, 
                               BLOCK_WIDTH[mySize], BLOCK_WIDTH[mySize], true);
            }
        }
    }
}

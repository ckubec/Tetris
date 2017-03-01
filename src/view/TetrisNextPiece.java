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

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.AbstractPiece;
import model.Board;
import model.Piece;

/**
 * Create the Panel used to create the next piece.
 * 
 * @author Chris Kubec
 * @version Autumn 2014
 */
@SuppressWarnings("serial") //TODO Figure out Implementation for later use.
public class TetrisNextPiece extends JPanel implements Observer {
    /** The preferred Width and Height of the Panel. */
    private static final int PANEL_WH = 200;
    /** Starting Board for getting the first Next Piece. */
    private Board myTetrisBoard;
    /** Piece used for Next Piece placeholder. */
    private Piece myNewPiece;

    /**
     * Create the Next piece window.
     * 
     * @param theBoard takes starting board
     */
    public TetrisNextPiece(final Board theBoard) {
        super(true);
        myTetrisBoard = theBoard;
        final JLabel scoreLabel = new JLabel("NEXT PIECE");
        add(scoreLabel);
        myNewPiece = (AbstractPiece) myTetrisBoard.getNextPiece();
        setBackground(TetrisTitle.DARK_BLUE);
        setSize(PANEL_WH, PANEL_WH);
        setPreferredSize(getSize());
    }
    
    /**
     * Redraws the next piece in the graphics component.
     * 
     * @param theGraphics Takes a Graphic to draw on the panel.
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        final int[][] newPieceArray = ((AbstractPiece) myNewPiece).getRotation();
        
        for (int i = 0; i < newPieceArray.length; i++) {
            g2d.fill3DRect(TetrisCenterBoard.BLOCK_WIDTH * (newPieceArray[i][1] + 1), 
                         TetrisCenterBoard.BLOCK_WIDTH * (newPieceArray[i][0] + 1), 
                         TetrisCenterBoard.BLOCK_WIDTH, TetrisCenterBoard.BLOCK_WIDTH, true);
        }
    }

    /**
     * Updates the Next Piece window.
     * 
     * @param theObservable 
     * @param theArg 
     */
    public void update(final Observable theObservable, final Object theArg) {
        myTetrisBoard = (Board) theObservable;
        myNewPiece = myTetrisBoard.getNextPiece();
        repaint();    
    }
}

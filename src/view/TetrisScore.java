/* 
 * TCSS 305 Autumn 2014
 * Assignment 5 - Power Paint
 */

package view;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Board;

/**
 * Tetris Score Board used to keep track of players current score, current lines cleared, 
 * and level.
 * 
 * @author Chris Kubec
 * @version Autumn 2014
 */
@SuppressWarnings("serial") //TODO Figure out Implementation for later use.
public class TetrisScore extends JPanel implements Observer {
    /** The preferred Width of the Panel. */
    private static final int PANEL_W = 200;
    /** The preferred Height of the Panel. */
    private static final int PANEL_H = 250;
    /** Width of Scoreboard. */
    private static final int SCORE_W = 2;
    /** Height of Scoreboard. */
    private static final int SCORE_H = 3;
    
    /** Multiplier used for lines increased for each level. */
    private static final int LEVEL_MULTIPLIER = 10;
    /** Points earned per line. */
    private static final int ONE_LINE = 40;    
    /** Points earned per 2 line. */
    private static final int TWO_LINE = 100;    
    /** Points earned per 3 line. */
    private static final int THREE_LINE = 300;    
    /** Points earned per 4 line. */
    private static final int FOUR_LINE = 1000;    
    
    /** Used for when using labels and integer values of scores. */
    private static final String SPACE = " ";
    
    /** Current lines on the screen. */
    private int myCurrentLine;
    /** Label of Lines Cleared. */
    private JLabel myClearedLabel;
    /** Label used to keep score. */
    private JLabel myScoreLabel;
    /** Current Level Label. */
    private JLabel myLevelLabel;
    /** Keep track of the Score. */
    private int myScore;
    /** Keeps track of Level. */
    private int myLevel;
    /** Keeps track of lines cleared. */
    private int myLinesClear;
    /** Main GUI used for changing pace of game. */
    private final TetrisGUI myGUI;

    /**
     * Create the default Tetris Score Panel.
     * 
     * @param theGUI Pass in the GUI to be used for changing scoring.
     */
    public TetrisScore(final TetrisGUI theGUI) {
        super();
        myGUI = theGUI;
        setup();
        drawPaneling();
    }
    
    /**
     * Used for setting up scoreboard.
     */
    private void setup() {
        myCurrentLine = 0;
        myScore = 0;
        myLevel = 1;
        myLinesClear = 0;
        
        setSize(PANEL_W, PANEL_H);
        setPreferredSize(getSize());
        setBackground(TetrisTitle.DARK_BLUE);
        setLayout(new GridLayout(SCORE_H, SCORE_W));
    }
    
    /**
     * Resets the board when a new game is started.
     */
    public void reset() {
        myCurrentLine = 0;
        myScore = 0;
        myLevel = 1;
        myLinesClear = 0;
    }
    
    /**
     * Draw ScorePanel and add first scores.
     */
    private void drawPaneling() {
        final JLabel score = new JLabel("Score:");
        myScoreLabel = new JLabel(SPACE + myScore);
        final JLabel linesClear = new JLabel("Lines Cleared: ");
        myClearedLabel = new JLabel(SPACE + myLinesClear);
        final JLabel level = new JLabel("Level:");
        myLevelLabel = new JLabel(SPACE + myLevel);
        add(score);
        add(myScoreLabel);
        add(linesClear);
        add(myClearedLabel);
        add(level);
        add(myLevelLabel);
    }
    
    /**
     * Gives the user a score.
     * 
     * @return Returns an int value of the score.
     */
    public int getScore() {
        return myScore;
    }

    /**
     * Update the Scoreboard from the Observer.
     * 
     * @param theObservable 
     * @param theArg 
     */
    public void update(final Observable theObservable, final Object theArg) {
        final Board tetrisBoard;
        tetrisBoard = (Board) theObservable;
        final int currentLine = tetrisBoard.getFrozenBlocks().size();
        if (myCurrentLine > currentLine) {
            final int linesCleared = myCurrentLine - currentLine;
            myLinesClear += linesCleared;
            
            if (linesCleared == 1) {
                myScore +=  myLevel * ONE_LINE;  
            } else if (linesCleared == 2) {
                myScore += myLevel * TWO_LINE;
            } else if (linesCleared == SCORE_H) {
                myScore += myLevel * THREE_LINE;
            } else {
                myScore += myLevel * FOUR_LINE;
            }
            
            if (myLinesClear % (myLevel * LEVEL_MULTIPLIER) == 0) {
                myLevel++;
                myGUI.changePacer();
            }
            panelPaint();
        }
        myCurrentLine = currentLine;
        repaint();
    }

    /**
     * Changes the Labels to proper scoring.
     */
    private void panelPaint() {
        myClearedLabel.setText(SPACE + myLinesClear);
        myScoreLabel.setText(SPACE + myScore);
        myLevelLabel.setText(SPACE + myLevel);
    }   
}

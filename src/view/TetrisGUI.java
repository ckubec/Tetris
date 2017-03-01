/* 
 * TCSS 305 Autumn 2014
 * Assignment 6 - Tetris
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import model.Board;

/**
 * Main GUI file that created makes the overall GUI Frame for Tetris.
 * 
 * @author Chris Kubec
 * @version Autumn 2014
 */
@SuppressWarnings("serial") //TODO Figure out Implementation for later use.
public class TetrisGUI extends JFrame implements ActionListener, KeyListener {
    /** New Games Width. */
    private static final int WIDTH_N = 10;
    /** New Games Height. */
    private static final int HEIGHT_N = 20;
    /** Base speed of objects falling. */
    private static final int START_TIMER = 1000;
    /** Speed increase per level. */
    private static final double LEVEL_INCR = 0.66;
    /** Speed of Timer. */
    private double myTimerSpeed; 
    /** Tetris Board used to keep track of pieces. */
    private Board myTetrisBoard;
    /** Timer used to keep track of moves in Tetris. */
    private Timer myBoardTimer;
    /** Is the Game paused. */
    private boolean myPause;
    /** Retain value of user quiting. */
    private boolean myQuit;
    /** Score Board used in GUI. */
    private TetrisScore myScoreBoard;
    

    /**
     * Create the Tetris Frame and add the parts to the Frame.
     */
    public TetrisGUI() {
        super("Tetris");
        settingUp();
        buildTitle();
        buildMenu();
        buildBoard();
        buildScoreBoardPanel();
        openningMessage();
        startStop();
        
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Set up frame Tetris Frames property.
     */
    private void settingUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(this);
        
        myTimerSpeed = START_TIMER;
        myBoardTimer = new Timer((int) myTimerSpeed, this);
        myTetrisBoard = new Board();
        myPause = false;
        myQuit = false;
    }
    
    /**
     * Builds a title screen used at the top of the game.
     */
    private void buildTitle() {
        final TetrisTitle label = new TetrisTitle(1);
        final JPanel title = new JPanel();
        title.add(label);
        title.setBackground(Color.BLACK);
        add(title, BorderLayout.NORTH);
    }
    
    /**
     * Create and add menu to the Frame.
     */
    private void buildMenu() {
        final TetrisMenu menu = new TetrisMenu(this);
        setJMenuBar(menu);
    }
    
    /**
     * Create the Center Panel used to display pieces on board.
     */
    private void buildBoard() {
        final JPanel centerPanel = new JPanel();
        final TetrisCenterBoard centerBoard;
        centerBoard = new TetrisCenterBoard();
        centerPanel.add(centerBoard, BorderLayout.CENTER);
        //centerPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, BORDER));
        add(centerBoard, BorderLayout.WEST);
        myTetrisBoard.addObserver(centerBoard);
    }
    
    /**
     * Create Score Board Panel.
     */
    private void buildScoreBoardPanel() {
        final JPanel scorePanel = new JPanel();
        final TetrisNextPiece nextPiece = new TetrisNextPiece(myTetrisBoard);
        myScoreBoard = new TetrisScore(this);
        scorePanel.setBackground(Color.BLACK);
        scorePanel.setLayout(new BorderLayout());
        scorePanel.add(nextPiece, BorderLayout.NORTH);
        scorePanel.add(myScoreBoard, BorderLayout.SOUTH);
        myTetrisBoard.addObserver(myScoreBoard);
        
        add(scorePanel, BorderLayout.CENTER);
        myTetrisBoard.addObserver(nextPiece);
    }
    
    /**
     * Creates the Opening greeting.
     */
    private void openningMessage() {
        final JPanel openningDialog = new JPanel();
        openningDialog.setLayout(new BorderLayout());
        openningDialog.add(new TetrisTitle(0), BorderLayout.NORTH);
        final JTextArea labeling = new JTextArea("Welcome to Tetris.\n" 
                                        + "The Default Controls Are: \n"
                                        + "Left: A \n" + "Down: S \n" 
                                        + "Right: D \n" + "Rotate: F \n" 
                                        + "Hard Down: SPACE BAR ");
        labeling.setEditable(false);
        
        labeling.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        openningDialog.add(labeling, BorderLayout.SOUTH);
        
        JOptionPane.showMessageDialog(null, openningDialog, "Welcome", 
                                      JOptionPane.DEFAULT_OPTION); 
    }
    
    /**
     * Starts and stops the timer when the game starts and stops.
     */
    private void startStop() {
        if (myPause) {
            myBoardTimer.stop();
        } else {
            myBoardTimer.start();
        }
    }
    
    /**
     * Changes the pause status of the game.
     */
    public void setPause() {
        if (myPause) {
            myPause = false;
            this.addKeyListener(this);
        } else {
            myPause = true;
            this.removeKeyListener(this);
        }
        startStop();
    }
    
    /**
     * Set if the Game is done manually.
     * 
     * @param theQuit Pass a boolean representing if the game is done.
     */
    public void setQuit(final boolean theQuit) {
        myQuit = theQuit;
    }
    
    /**
     * Start a new Game.
     */
    public void newGameStart() {
        myTetrisBoard.newGame(WIDTH_N, HEIGHT_N, null);
        myQuit = false;
        setPause();
        startStop();
        myScoreBoard.reset();
        this.dispose();
        final TetrisGUI newGUI = new TetrisGUI();
        newGUI.setVisible(true);
    }
    
    /**
     * Change the Pace of the Game.
     */
    public void changePacer() {
        myTimerSpeed = myTimerSpeed * LEVEL_INCR;
        myBoardTimer.setDelay((int) myTimerSpeed);
    }

    /**
     * The board steps forward in the game and gives an ending message if the game ends.
     * 
     * @param theEvent takes a ActionEvent corresponding to the Timer.
     */
    public void actionPerformed(final ActionEvent theEvent) {
        if (myTetrisBoard.isGameOver() || myQuit) {
            JOptionPane.showMessageDialog(null, "Your game has ended your Final Score was: " 
                                            + myScoreBoard.getScore(), "GAME OVER",
                                            JOptionPane.PLAIN_MESSAGE);
            setPause();
        } else {
            myTetrisBoard.step();
        }
    }

    /**
     * Key typed method unused in Tetris.
     * 
     * @param theEvent takes a KeyEvent and does not use it.
     */
    public void keyTyped(final KeyEvent theEvent) {
        //Used KeyPressed to track when a user pushes keys.
    }

    /**
     * Used to retrieve input from player moving pieces around.
     * 
     * @param theEvent KeyEvent pushed by user.
     */
    public void keyPressed(final KeyEvent theEvent) {
        if (theEvent.getKeyCode() == KeyEvent.VK_A) {
            myTetrisBoard.moveLeft();
        } else if (theEvent.getKeyCode() == (KeyEvent.VK_D)) {
            myTetrisBoard.moveRight();
        } else if (theEvent.getKeyCode() == (KeyEvent.VK_S)) {
            myTetrisBoard.moveDown();
        } else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
            myTetrisBoard.hardDrop();
        } else if (theEvent.getKeyCode() == KeyEvent.VK_F) {
            myTetrisBoard.rotateCW();
        }
    }

    /**
     * Key released method unused in Tetris.
     * 
     * @param theEvent takes a KeyEvent and does not use it.
     */
    public void keyReleased(final KeyEvent theEvent) {
        //Used KeyPressed to track when a user pushes keys.
    }
    

    /**
     * Gives the value of the game being over or not.
     * @return Boolean vaule representing if the game has been quit.
     */
    public boolean isQuit() {
        return myQuit;
    }
}

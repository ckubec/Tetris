/* 
 * TCSS 305 Autumn 2014
 * Assignment 5 - Power Paint
 */

package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Create the Tetris Main Menu Bar.
 * 
 * @author Chris Kubec
 * @version Autumn 2014
 */
@SuppressWarnings("serial") //TODO Figure out Implementation for later use.
public class TetrisMenu extends JMenuBar {
    /** GUI used for changing button functions. */
    private final TetrisGUI myGUI;

    /**
     * Default constructor for Tetris Menu Bar.
     * 
     * @param theGUI Pass in the GUI for menu functionality.
     */
    public TetrisMenu(final TetrisGUI theGUI) {
        super();
        myGUI = theGUI;
        createFile();
        createOptions();
        createAbout();
    }
    
    /**
     * Create the File Menu.
     */
    private void createFile() {
        final JMenu file = new JMenu("File");
        
        final JMenuItem newGame = new JMenuItem("New Game");
        final JMenuItem endGame = new JMenuItem("End Game");
        
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                if (myGUI.isQuit()) {
                    myGUI.newGameStart();
                }
            }
        });
        
        endGame.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myGUI.setQuit(true);
            }           
        });
        
        file.add(newGame);
        file.add(endGame);
        file.addSeparator();
        
        final JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                System.exit(0);
            }
        });
        
        add(file);
    }
    
    /**
     * Create Options Menu.
     */
    private void createOptions() {
        final JMenu option = new JMenu("Options");
        final JCheckBox pause = new JCheckBox("Pause");
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {     
                myGUI.setPause();
            }    
        });
        option.add(pause);
        add(option);     
    }
    
    /**
     * Create About Menu.
     */
    private void createAbout() {
        final JMenu about = new JMenu("Help");
        
        final JMenuItem info = new JMenuItem("About");
        info.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                final JPanel aboutPanel = new JPanel();
                aboutPanel.setLayout(new BorderLayout());
                aboutPanel.add(new TetrisTitle(0), BorderLayout.NORTH);
                final JTextArea labeling = new JTextArea("This Tetris game was created by: "
                                                + "Chris Kubec \n" 
                                                + "With backend model code "
                                                + "supplied by Alan Fowler.");
                aboutPanel.add(labeling, BorderLayout.SOUTH);
                JOptionPane.showMessageDialog(null, aboutPanel, info.getText(), 
                                              JOptionPane.QUESTION_MESSAGE);  
            }        
        });
        
        final JMenuItem keys = new JMenuItem("Keys");
        keys.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null, "The Controls Are: \n"
                                        + "Left: A \n" + "Down: S \n" 
                                        + "Right: D \n" + "Rotate: F \n" 
                                        + "Hard Down: SPACE BAR ", "Controls", 
                                        JOptionPane.PLAIN_MESSAGE);
            }  
        });
        
        final JMenuItem scoring = new JMenuItem("Score");
        scoring.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null, "Score:\n"
                                                + "The score is classic Tetris rules, "
                                                + "every line cleared earns a base 40 points "
                                                + "multiplied by the current level.\n"
                                                + "Clearing 2 lines will earn 100 points with "
                                                + "the same level multiplier, "
                                                + "3 lines will raise base to 300. "
                                                + "While\nthe Almighty 4 line Tetris clearance"
                                                + " will have a base of 1000.\n\n Speed: \n "
                                                + "Every level the speed will increase by a "
                                                + "third. Each level passed increases "
                                                + "the minimum lines required\n for the "
                                                + "next level by ten level 1 starts at "
                                                + "10 lines required to pass.", 
                                                "Scoring",
                                        JOptionPane.PLAIN_MESSAGE);
            }  
        });
        
        
        about.add(info);
        about.add(keys);
        about.add(scoring);
        add(about);
    }
}

/*
 * Viewer.java     11/15/99
 *
 * Copyright (c) 1998, 1999 Rod Howell, All Rights Reserved.
 *
 */

package edu.ksu.cis.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Panel;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.UIManager;



import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This is the driver class for the Search Tree Viewer.  It now 
 * will only run as an application. It recognizes a single command-line 
 * argument, "mono", which indicates that the program should run in 
 * monochrome mode, displaying red nodes as gray (for AA Trees and 
 * Red-Black Trees). Any other command-line arguments are ignored.
 *
 * The program displays the following GUI:
 * <p>
 * <img alt="The tree viewer main GUI." src="Viewer.gif">
 * <p>
 * On the left are seven choices:
 * <ul>
 * <li> AA Tree
 * <li> AVL Tree
 * <li> Binary Search Tree
 * <li> Red-Black Tree
 * <li> Patricia Trie
 * <li> Splay Tree
 * <li> Trie
 * </ul>
 * When the Start button is pressed, a frame for creating and manipulating an
 * instance of the current choice is displayed.  When this window is closed, 
 * the program terminates.
 *
 * @author Rod Howell
 *         (<a href="mailto:howell@cis.ksu.edu">howell@cis.ksu.edu</a>)
 *
 * @see         BSTFrame
 * @see         AATree
 * @see         AVLTree
 * @see         BinarySearchTree
 * @see         RedBlackTree
 * @see         PatriciaTrie
 * @see         SplayTree
 * @see         Trie
 */
public class Viewer extends Panel {

  /**
   * The choice box for selecting the type of search tree.
   */
  private JComboBox<String> choices 
    = new JComboBox<>(new String[] {"AA Tree", "AVL Tree", "Binary Search Tree",
				  "Red-Black Tree", "Patricia Trie",
				  "Splay Tree", "Trie"});

  /**
   * Indicates whether the program is running in monochrome mode.
   */
  private static boolean monochrome = false;

  /**
   * Constructs an instance of the Viewer GUI with default size.
   */
  public Viewer() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      // This shouldn't happen
      e.printStackTrace();
    }
    choices.setSelectedIndex(0);
    choices.setEditable(false);
    setLayout(new FlowLayout());
    add(choices);
    JButton st = new JButton("Start");
    st.addActionListener(new StartButtonListener(this));
    add(st);
  }

  /**
   * Starts the viewer as an application.  Opens a Frame containing the 
   * Viewer GUI.  If the Frame is closed, the application terminates.
   * @param         args    command-line arguments
   */
  public static void main(String args[]) {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("mono")) {
	monochrome = true;
      }
    }
    Viewer v = new Viewer();
    JFrame fr = new JFrame();
    fr.getContentPane().add(v);
    fr.setTitle("Viewer");
    fr.addWindowListener(new Closer());
    fr.pack();
    fr.setVisible(true);
  }

  /**
   * Event handler for the Start button in the Viewer GUI.  Opens a Frame for
   * creating and manipulating an instance of the current choice.
   */
  public void openWindow() {
    switch (choices.getSelectedIndex()) {
    case 0:
      new BSTFrame(new AATree(), "AA Tree").setVisible(true);
      break;
    case 1:
      new BSTFrame(new AVLTree(), "AVL Tree").setVisible(true);
      break;
    case 2:
      new BSTFrame(new BinarySearchTree(), "Binary Search Tree").setVisible(true);
      break;
    case 3:
      new BSTFrame(new RedBlackTree(),
		   "Red-Black Tree").setVisible(true);
      break;
    case 4:
      new BSTFrame(new PatriciaTrie(), "Patricia Trie").setVisible(true);
      break;
    case 5:
      new BSTFrame(new SplayTree(), "Splay Tree").setVisible(true);
      break;
    case 6:
      new BSTFrame(new Trie(), "Trie").setVisible(true);
      break;
    }
  }

  /**
   * Gets whether the program is running in monochrome mode.
   * @return   whether the program is running in monochrome mode.
   */
  public static boolean isMonochrome()
  {
    return monochrome;
  }
}

/**
 * The event handler for the Start button.
 */
class StartButtonListener implements ActionListener {

  /**
   * The Viewer containing the button.
   */
  private Viewer theViewer;

  /**
   * Constructs a listener for the Start button.
   */
  public StartButtonListener(Viewer v) {
    theViewer = v;
  }

  /**
   * Handles the event.
   * @param e the event
   */
  public void actionPerformed(ActionEvent e) {
    theViewer.openWindow();
  }
}

/**
 * The event handler for the window-closing event.  This event is handled
 * by terminating the program.
 */
class Closer extends WindowAdapter {

  /**
   * Handles the event.
   * @param e the event
   */
  public void windowClosing(WindowEvent e) {
    System.exit(0);
  }
}

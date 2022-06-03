/*
 * BSTInterface.java     11/15/99
 *
 * Copyright (c) 1998, 1999, Rod Howell, All Rights Reserved.
 *
 */

package edu.ksu.cis.viewer;

import java.awt.Font;
import javax.swing.JComponent;




/** 
 * This interface is implemented by each of the data structures whose 
 * functionality is equivalent to a binary search tree.  Functionality 
 * includes the ability to
 * insert and delete String keys, to clone itself, and to draw itself.
 * <p>
 * Note that this interface does not extend {@link java.lang.Cloneable 
 * java.lang.Cloneable}.
 * This is because implementations may not need to use the 
 * <code>clone</code>
 * method of {@link java.lang.Object Object} in order to clone themselves.
 *
 * @author      Rod Howell
 *         (<a href="mailto:howell@cis.ksu.edu">howell@cis.ksu.edu</a>)
 *
 * @see         AVLTree
 * @see         BinarySearchTree
 * @see         SplayTree
 * @see         java.lang.Cloneable
 * @see         java.lang.Object
 */
public interface BSTInterface {

  /** 
   * Returns the result of inserting <code>key</code> into this tree.  If
   * <code>key</code> is already in the tree, a tree having the same contents 
   * is returned.
   * @param             key    the key to insert
   * @return            the resutling tree
   * @exception         NullPointerException
   *                    If <code>key</code> is <code>null</code>
   */
  public BSTInterface put(String key) throws NullPointerException;

  /**
   * Returns the result of removing <code>key</code> from this.  If <code>key</code>
   * is not in the tree, a tree having the same contents is returned.
   * @param             key       the key to remove
   * @return            the resulting tree
   * @exception         NullPointerException
   *                    If key is <code>null</code>
   */
  public BSTInterface remove(String key) throws NullPointerException;

  /**
   * Returns a drawing of the tree.
   * @return            a drawing of the tree
   */
  public JComponent getDrawing();

  /**
   * Returns a drawing of the tree using the given Font.
   * @param             fnt      the font to use
   * @return            a drawing of the tree
   * @throws NullPointerException if <code>fnt</code> is <code>null</code>.
   */
  public JComponent getDrawing(Font fnt);

  /**
   * Returns a clone of this tree.
   * @return             a clone of the this tree
   */
  public Object clone();
}

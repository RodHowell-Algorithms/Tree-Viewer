/*
 * TreeInterface.java    11/15/99
 *
 * Copyright (c) 1999, Rod Howell, All Rights Reserved.
 *
 */

package edu.ksu.cis.viewer;



/**
 * This interface may be implemented by a tree in order to allow a
 * {@link TreeDrawing TreeDrawing} to be constructed from the tree.  
 * Empty trees may be
 * represented either by <code>null</code> references or by objects whose
 * {@link #isEmpty isEmpty} methods return <code>true</code>.  If <code>null</code> 
 * references
 * are used, {@link #isEmpty isEmpty} should always return <code>false</code>.
 *
 * @author Rod Howell
 *         (<a href="mailto:howell@cis.ksu.edu">howell@cis.ksu.edu</a>)
 *
 * @see        TreeDrawing
 */
public interface TreeInterface {

  /**
   * Returns the root of the tree.  The <code>TreeDrawing</code> class
   * converts the returned <code>Object</code> to a <code>String</code>.  In
   * order to control how this conversion is done, its <code>toString</code>
   * method may be overridden.
   * @return the root of this tree
   * @throws EmptyTreeException if the tree is empty.
   * @see java.lang.Object Object.toString()
   */
  public Object getRoot() throws EmptyTreeException;

  /**
   * Returns the children of the tree.  A <code>null</code> returned value
   * is treated as equivalent to an array of length 0.
   * @return an array containing the children of this tree
   * @throws EmptyTreeException if the tree is empty.
   */
  public TreeInterface[] getChildren() throws EmptyTreeException;

  /**
   * Returns <code>true</code> if the tree is empty.
   * @return whether this tree is empty
   */
  public boolean isEmpty();
}

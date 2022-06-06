/*
 * EmptyTreeException.java     11/13/99
 *
 * Copyright (c) 1998, 1999, Rod Howell, All Rights Reserved.
 *
 */

package edu.ksu.cis.viewer;



/**
 * An exception thrown when an attempt is made to access the root or a child of
 * an emtpy BinaryTree.
 *
 * @author Rod Howell
 *         (<a href="mailto:rhowell@ksu.edu">rhowell@ksu.edu</a>)
 *
 * @see BinaryTree
 */
public class EmptyTreeException extends RuntimeException {

  /**
   * Used for consistency in serialization.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a new <code>EmptyTreeException</code>.
   */
  public EmptyTreeException() {
    
  }
}

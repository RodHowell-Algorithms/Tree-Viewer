/*
 * EmptyListException.java     11/13/99
 *
 * Copyright (c) 1998, 1999, Rod Howell, All Rights Reserved.
 *
 */

package edu.ksu.cis.viewer;



/**
 * An exception thrown when an attempt is made to access the head or tail of
 * an emtpy ConsList.
 *
 * @author Rod Howell
 *         (<a href="mailto:rhowell@ksu.edu">rhowell@ksu.edu</a>)
 *
 * @see ConsList
 */
public class EmptyListException extends RuntimeException {

  /**
   * Used for consistency in serialization.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a new <code>EmptyListException</code>.
   */
  public EmptyListException() {
    
  }
}


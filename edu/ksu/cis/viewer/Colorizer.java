/*
 * Colorizer.java     11/13/99
 *
 * Copyright (c) 1998, 1999, Rod Howell, All Rights Reserved.
 *
 */

package edu.ksu.cis.viewer;

import java.awt.Color;

/**
 * An interface to encapsulate a mechanism for associating colors to
 * Objects.
 *
 * @author Rod Howell
 *         (<a href="mailto:rhowell@ksu.edu">rhowell@ksu.edu</a>)
 */
public interface Colorizer {

  /**
   * Returns the Color associated with the given Object.
   * @param          obj     the object whose color is to be retrieved
   * @return         the color of <code>obj</code>
   */
  public Color getColor(Object obj);
}

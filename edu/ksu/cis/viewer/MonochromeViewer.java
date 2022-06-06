/*
 * MonochromeViewer	6/6/2022
 *
 * Copyright (c) 2022, All Rights Reserved
 *
 */

package edu.ksu.cis.viewer;

/**
 * An alternate entry point to the Search Tree Viewer to run the program
 * in monochrome mode. Using this class as the main class is equivalent to
 * using the {@link Viewer} class as the main class with an extra command-line
 * argument of "mono".
 * @see Viewer
 */
public class MonochromeViewer {

  /**
   * Runs the Search Tree Viewer in monochrome mode.
   * @param    args    the command-line arguments
   */
  public static void main(String args[]) {
    String[] a = new String[args.length + 1];
    System.arraycopy(args, 0, a, 0, args.length);
    a[args.length] = "mono";
    Viewer.main(a);
  }

  /**
   * Constructs a MonochromeViewer (unused).
   */
  private MonochromeViewer() {
  }
}

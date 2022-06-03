/*
 * Node.java     11/15/99
 *
 * Copyright (c) 1998, 1999, Rod Howell, All Rights Reserved.
 *
 */
package edu.ksu.cis.viewer;

import java.awt.Color;



/**
 * The objects used as the nodes of trees in the <code>BinaryTree</code> class.  
 * The contents of a Node are represented
 * by a String.  In addition, each Node has an <code>int</code> tag and a color. 
 * A Node is immutable.
 *
 * @author Rod Howell
 *         (<a href="mailto:howell@cis.ksu.edu">howell@cis.ksu.edu</a>)
 *
 * @see BinaryTree
 */
public final class Node implements Cloneable {

  /**
   * The contents of the node.
   */
  private String contents;

  /**
   * The tag.
   */
  private int tag;

  /**
   * The node's color.
   */
  private Color theColor;

  /**
   * Constructs a black Node with the given contents and a tag of 0.
   * @param  s   the contents of the node
   * @throws NullPointerException if <code>s</code> is <code>null</code>
   */
  public Node(String s) throws NullPointerException {
    this(s, 0, Color.black);
  }

  /**
   * Constructs a black Node with the given contents and tag.
   * @param    s        the contents of the Node
   * @param    tag      the tag
   * @throws NullPointerException if <code>s</code> is <code>null</code>
   */
  public Node(String s, int tag) throws NullPointerException {
    this(s, tag, Color.black);
  }

  /**
   * Constructs a Node of the given color with the given contents and a tag 
   * of 0.
   * @param    s        the contents of the Node
   * @param    col      the color of the Node
   * @throws NullPointerException if either <code>s</code> or <code>col</code> is
   *                              <code>null</code>
   */
  public Node(String s, Color col) throws NullPointerException {
    this(s, 0, col);
  }

  /**
   * Constructs a Node of the given color with the given contents and tag.
   * @param    s        the contents of the Node
   * @param    tag      the tag
   * @param    col      the color of the Node
   * @throws NullPointerException if either <code>s</code> or <code>col</code> is
   *                              <code>null</code>
   */
  public Node(String s, int tag, Color col) throws NullPointerException {
    if (s == null || col == null) throw new NullPointerException();
    contents = s;
    theColor = col;
    this.tag = tag;
  }

  /**
   * Returns the contents of the Node.
   * @return   the contents of this node
   */
  public String getContents() {
    return contents;
  }

  /**
   * Returns the tag of the Node.
   * @return   the tag for this node
   */
  public int getTag() {
    return tag;
  }

  /**
   * Returns the color of the Node.
   * @return   the color of this node
   */
  public Color getColor() {
    return theColor;
  }

  /**
   * Because this structure is immutable, this method simply returns the node
   * itself.
   */
  public Object clone() {
    return this;
  }
}

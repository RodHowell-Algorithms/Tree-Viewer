/*
 * RedBlackTree.java     8/15/01
 *
 * Copyright (c) 1998, 1999, 2001, Rod Howell, All Rights Reserved.
 *
 */

package edu.ksu.cis.viewer;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComponent;



/**
 * An immutable red-black tree that can draw itself.  A red-black tree is
 * a binary search tree with the following properties:
 * <ol>
 * <li> Each node is colored either red or black.
 * <li> The root is black.
 * <li> If an internal node is red, its parent is black.
 * <li> For every empty subtree, the number of black proper ancestors is the 
 *      same.
 * </ol>
 *
 * @author Rod Howell
 *         (<a href="mailto:howell@cis.ksu.edu">howell@cis.ksu.edu</a>)
 */
public final class RedBlackTree implements BSTInterface {

  /**
   * The tree.  
   */
  private BinaryTree theTree;

  /**
   * Constructs an empty RedBlackTree.
   */
  public RedBlackTree() {
    theTree = new BinaryTree();
  }

  /**
   * Constructs a RedBlackTree object from the given BinaryTree.  <code>t</code>
   * is assumed to satisfy the properties of a red-black tree.
   */
  private RedBlackTree(BinaryTree t) {
    theTree = t;
  }

  /**
   * Returns the <code>BinaryTree</code> resulting from performing a single
   * rotate left on the binary tree whose root is given by <code>root</code>,
   * whose left-hand child is given by <code>left</code>, and whose right-hand
   * child is given by <code>right</code>.
   */
  private static BinaryTree rotateLeft(Node root, BinaryTree left,
				       BinaryTree right) {
    return new BinaryTree(right.getRoot(),
			  new BinaryTree(root, left, right.getLeftChild()),
			  right.getRightChild());
  }

  /**
   * Returns the <code>BinaryTree</code> resulting from performing a single
   * rotate right on the binary tree whose root is given by <code>root</code>,
   * whose left-hand child is given by <code>left</code>, and whose right-hand
   * child is given by <code>right</code>.
   */
  private static BinaryTree rotateRight(Node root, BinaryTree left,
				        BinaryTree right) {
    return new BinaryTree(left.getRoot(),
			  left.getLeftChild(),
			  new BinaryTree(root, left.getRightChild(), right));
  }

  /**
   * Returns the <code>BinaryTree</code> resulting from coloring the root of
   * the given <code>BinaryTree</code> with the given <code>Color</code>.
   */
  private static BinaryTree colorRoot(BinaryTree t, Color c) {
    if (t.isEmpty()) return t;
    Node root = t.getRoot();
    return new BinaryTree(new Node(root.getContents(), c),
			  t.getLeftChild(),
			  t.getRightChild());
  }

  /**
   * Returns <code>true</code> if <code>t</code> has a red root.
   */
  private static boolean hasRedRoot(BinaryTree t) {
    Color red = Viewer.isMonochrome() ? Color.gray : Color.red;
    if (t.isEmpty()) return false;
    else return t.getRoot().getColor().equals(red);
  }  

  /**
   * Returns the <code>RedBlackTree</code> resulting from the insertion of
   * <code>key</code> into this <code>RedBlackTree</code>.  If <code>key</code> is
   * already in this tree, an identical tree is returned.
   * @exception     NullPointerException
   *                If <code>key</code> is <code>null</code>
   */
  public BSTInterface put(String key) throws NullPointerException {
    if (key == null) {
      throw new NullPointerException();
    }
    return new RedBlackTree(colorRoot(put(key, theTree), Color.black));
  }

  /**
   * Returns the red-black tree that results from the insertion of <code>key</code>
   * into <code>t</code>.
   * @param key   The value to be inserted.
   * @param t     The tree into which <code>key</code> is to be inserted.  This
   *              tree is assumed to be a valid red-black tree, except that
   *              it may have a red root.
   * @return      The result of inserting  <code>key</code> into <code>t</code>.  
   *              This tree is guaranteed to be a valid red-black tree, 
   *              except that it may have a red root, which may in turn have 
   *              one red child if <code>t</code> has a red root.  
   *              Furthermore, the number of black nodes on any path from the 
   *              root to an empty subtree is guaranteed to be the same as for 
   *              <code>t</code>.
   */
  private static BinaryTree put(String key, BinaryTree t) {
    Color red = Viewer.isMonochrome() ? Color.gray : Color.red;
    if (t.isEmpty()) {  // Insert here

      // The node must be red because t contains no black nodes.
      return new BinaryTree(new Node(key, red), null, null);
    }
    else {
      Node root = t.getRoot();
      int result = key.compareTo(root.getContents());
      if (result == 0) {  // key is already in t
	return t;
      }
      else if (result < 0) {  // Insert in left child
	BinaryTree left = put(key, t.getLeftChild());
	BinaryTree right = t.getRightChild();
	if (hasRedRoot(left)) { 

	  // left might have a red child.  If it does, the left child of
	  // t must be red, which implies that root is black.

	  if (hasRedRoot(right)) { 
	    if (hasRedRoot(left.getLeftChild()) || 
	       hasRedRoot(left.getRightChild())) {

	      // Case 1:  Both left and right are red, and left has
	      // a red child.  We change the colors of root, left, and right.
	      // This yields a red-black tree, except that it has a red root.

	      return new BinaryTree(new Node(root.getContents(),
					     red),
				    colorRoot(left, Color.black),
				    colorRoot(right, Color.black));
	    }
	  }
	  else {
	    if (hasRedRoot(left.getRightChild())) {

	      // Case 2: left is red, right is black, and the right child
	      // of left is red.  We do a rotateLeft on left, which gives
	      // us an instance of Case 3.

	      left = rotateLeft(left.getRoot(),
				left.getLeftChild(),
				left.getRightChild());
	    }
	    if (hasRedRoot(left.getLeftChild())) {

	      // Case 3: left is red, right is black, and the left child of
	      // left is red.  We change the colors of root and left, and
	      // do a rotate right.  This yields a red-black tree.

	      return rotateRight(new Node(root.getContents(),
					  red),
				 colorRoot(left, Color.black),
				 right);
	    }
	  }
	}

	// If we reach this point, either left is black or it has no red
	// children.  If root is black, the tree is a red-black tree.  If root
	// is red, it may have one red child (left), but this only occurs if
	// the root of t is red.  Otherwise, the properties of a red-black
	// tree are satisfied.

	return new BinaryTree(root, left, right);
      }
      else {  // Insert in right child
	BinaryTree left = t.getLeftChild();
	BinaryTree right = put(key, t.getRightChild());
	if (hasRedRoot(right)) {

	  // right might have a red child.  If it does, the right child of
	  // t must be red, which implies that root is black.

	  if (hasRedRoot(left)) {
	    if (hasRedRoot(right.getLeftChild()) ||
		hasRedRoot(right.getRightChild())) {

	      // Case 1:  Both left and right are red, and right has
	      // a red child.  We change the colors of root, left, and right.
	      // This yields a red-black tree, except that it has a red root.

	      return new BinaryTree(new Node(root.getContents(),
					     red),
				    colorRoot(left, Color.black),
				    colorRoot(right, Color.black));
	    }
	  }
	  else {
	    if (hasRedRoot(right.getLeftChild())) {

	      // Case 2: left is black, right is red, and the left child
	      // of right is red.  We do a rotateRight on right, which gives
	      // us an instance of Case 3.

	      right = rotateRight(right.getRoot(),
				  right.getLeftChild(),
				  right.getRightChild());
	    }
	    if (hasRedRoot(right.getRightChild())) {

	      // Case 3: left is black, right is red, and the right child of
	      // right is red.  We change the colors of root and right, and
	      // do a rotate left.  This yields a red-black tree.

	      return rotateLeft(new Node(root.getContents(),
					 red),
				left,
				colorRoot(right, Color.black));
	    }
	  }
	}

	// If we reach this point, either right is black or it has no red
	// children.  If root is black, the tree is a red-black tree.  If root
	// is red, it may have one red child (right), but this only occurs if
	// the root of t is red.  Otherwise, the properties of a red-black
	// tree are satisfied.

	return new BinaryTree(root, left, right);
      }
    }
  }

  /**
   * Returns the <code>RedBlackTree</code> resulting from the removal of 
   * <code>key</code> from this <code>RedBlackTree</code>.  If <code>key</code>
   * is not in this tree, an identical tree is returned.
   * @exception         NullPointerException
   *                    If key is <code>null</code>
   */
  public BSTInterface remove(String key) throws NullPointerException {
    if (key == null) {
      throw new NullPointerException();
    }
    return new RedBlackTree(colorRoot(remove(key, theTree).getTree(),
				      Color.black));
  }

  /**
   * Returns a TreeBooleanPair describing the result of removing <code>s</code>
   * from <code>t</code>.
   * returned.
   * @param s   The value to be deleted.
   * @param t   The tree from which <code>s</code> is to be deleted.  This tree
   *            is assumed to be a valid red-black tree, except that it may
   *            have a red root.
   * @return    The <code>BinaryTree</code> in the pair is the result of removing
   *            <code>s</code> from <code>t</code>.  It is guaranteed to be a valid
   *            red-black tree, except that it may have a red root.  The
   *            number of black nodes on any path from the root to an empty
   *            subtree is either the same as or one less than that value for 
   *            <code>t</code>.  In the former case, the boolean in the pair is
   *            <code>true</code>; otherwise, it is <code>false</code>.
   */
  private static TreeBooleanPair remove(String s, BinaryTree t) {
    if (t.isEmpty()) {  // s is not found
      return new TreeBooleanPair(t, true);
    }
    else {
      Node root = t.getRoot();
      BinaryTree left = t.getLeftChild();
      BinaryTree right = t.getRightChild();
      int result = s.compareTo(root.getContents());
      if (result == 0) {  // found it
	if (left.isEmpty()) {
	  if (right.isEmpty()) {
	    if (root.getColor().equals(Color.black)) {

	      // We are deleting a black leaf.  This decreases the number
	      // of black nodes.

	      return new TreeBooleanPair(right, false);
	    }
	    else {

	      // We are deleting a red leaf.  This causes no problems.

	      return new TreeBooleanPair(right, true);
	    }
	  }
	  else {

	    // The node to be deleted has a right child but no left child.
	    // In order to satisfy the properties of a red-black tree, the
	    // right child must be red.  Therefore, we can maintain a
	    // red-black tree by replacing the deleted node with its right
	    // child and coloring it black.

	    return new TreeBooleanPair(colorRoot(right, Color.black), true);
	  }
	}
	else if (right.isEmpty()) {

	  // The node to be deleted has a left child but no right child.
	  // In order to satisfy the properties of a red-black tree, the
	  // left child must be red.  Therefore, we can maintain a
	  // red-black tree by replacing the deleted node with its left
	  // child and coloring it black.

	  return new TreeBooleanPair(colorRoot(left, Color.black), true);
	}
	else {

	  // We are deleting a node with 2 children.  We will replace it with 
	  // the minimum node in its right child, keeping the color of the
	  // deleted node.

	  TreeBooleanPair pair = minToRoot(right);
	  root = new Node(pair.getTree().getRoot().getContents(),
			  root.getColor());
	  right = pair.getTree().getRightChild();

	  // We may need to rebalance.

	  return balanceRight(root, left, right, pair.isBalanced());
	}
      }
      else if (result < 0) {  // Look in the left child
	TreeBooleanPair pair = remove(s, left);
	return balanceLeft(root, pair.getTree(), right, pair.isBalanced());
      }
      else {  // Look in the right child
	TreeBooleanPair pair = remove(s, right);
	return balanceRight(root, left, pair.getTree(), pair.isBalanced());
      }
    }
  }

  /**
   * Returns a <code>TreeBooleanPair</code> describing the result of moving
   * the minimum value in <code>t</code> to the root.
   * @param t   A nonempty tree, which is assumed to be a valid red-black
   *            tree, except that it may have a red root.
   * @return    The <code>BinaryTree</code> in the pair contains the minimum
   *            value of <code>t</code> at the root, and has an empty left
   *            child.  The right child contains the remaining elements of 
   *            <code>t</code>, and is a red-black tree, except that it may have
   *            a red root.  The number of black nodes in any path from the 
   *            root of the right child to an empty subtree is either the same
   *            as or one less than the number of black nodes in any path from
   *            the root of <code>t</code> to an empty subtree.  In the former 
   *            case, the boolean in the pair is <code>true</code>; otherwise, it 
   *            is </code>false</code>.
   */
  private static TreeBooleanPair minToRoot(BinaryTree t) {
    BinaryTree left = t.getLeftChild();
    Color red = Viewer.isMonochrome() ? Color.gray : Color.red;
    if (left.isEmpty()) {  // The root is the minimum
      if (t.getRoot().getColor().equals(red)) {

	// If the root is red, we can delete it with no problem.

	return new TreeBooleanPair(t, true);
      }
      else if (!t.getRightChild().isEmpty()) {

	// Because t has no left child, its nonempty right child must be
	// red.  Because we are deleting a black node and replacing it with
	// its right child, we must color the replacement black.  This
	// maintains the red-black tree.

	return new TreeBooleanPair(new BinaryTree(t.getRoot(), 
						  t.getLeftChild(),
						  colorRoot(t.getRightChild(),
							    Color.black)),
				   true);
      }
      else {

	// t is a black leaf.

	return new TreeBooleanPair(t, false);
      }
    }
    else {  // t has a nonempty left child, which contains the minimum.
      TreeBooleanPair pair = minToRoot(left);
      TreeBooleanPair rpair = balanceLeft(t.getRoot(), 
					  pair.getTree().getRightChild(),
					  t.getRightChild(), 
					  pair.isBalanced());
      return new TreeBooleanPair(new BinaryTree(pair.getTree().getRoot(), 
						pair.getTree().getLeftChild(),
						rpair.getTree()),
				 rpair.isBalanced());
    }
  }

  /**
   * Returns a TreeBooleanPair describing the result of rebalancing
   * the red-black tree described by the parameters.
   * @param root   The root of the tree to be balanced.
   * @param left   The left child of the tree to be balanced.  This is
   *               assumed to be a valid red-black tree, possibly with a
   *               red root if <code>root</code> is black.
   * @param right  The right child of the tree to be balanced.  This is
   *               assumed to be a valid red-black tree, possibly with a
   *               red root if <code>root</code> is black.  The number of black 
   *               nodes on each path is either equal to or one greater than 
   *               the number of black nodes on each path of <code>left</code>.  
   *               In the latter case, <code>left</code> cannot have a red root.
   * @param bal    <code>true</code> iff the number of black nodes in each path
   *               is the same for <code>left</code> and <code>right</code>.
   * @return       A TreeBooleanPair whose tree is a valid red-black tree,
   *               possibly with a red root, containing the nodes given
   *               in the parameters, and whose boolean is <code>true</code> iff
   *               the number of black nodes on each path is the same as
   *               the number of black nodes on each path from <code>root</code>
   *               to any empty subtree in <code>right</code> in the tree 
   *               described by the parameters.
   *               
   */
  private static TreeBooleanPair balanceLeft(Node root, BinaryTree left,
					     BinaryTree right, boolean bal) {
    if (bal) {

      // The tree is a valid red-black tree, perhaps with a red root.

      return new TreeBooleanPair(new BinaryTree(root, left, right), true);
    }
    else {

      // The number of black nodes on a path from root to any empty subtree
      // in left is one less than the number of black nodes on a path from 
      // root to any empty subtree in right.  Furthermore, left does not have 
      // a red root.

      Color red = Viewer.isMonochrome() ? Color.gray : Color.red;
      if (hasRedRoot(right)) {

	// Case 1:  root is black, left is black (or empty), and right is red.
	// Because each path from root to an empty subtree in right must 
	// contain at least two black nodes, right has two black children.  We
	// first change the colors of root and right, then do a rotateLeft.

	root = new Node(root.getContents(), red);
	right = colorRoot(right, Color.black);
	BinaryTree t = rotateLeft(root, left, right);

	// left is now red with two black children, and the number of black
	// nodes on any path from left to an empty subtree in its left child 
	// is one less than the number of black nodes on any path from left to
	// an empty subtree in its right child.  left is therefore and 
	// instance of one of the other 3 cases.

	left = balanceLeft(t.getLeftChild().getRoot(),
			   t.getLeftChild().getLeftChild(),
			   t.getLeftChild().getRightChild(),
			   false).getTree();

	// Because left had a red root, the tree is now a red-black tree.

	return new TreeBooleanPair(new BinaryTree(t.getRoot(),
						  left,
						  t.getRightChild()),
				   true);
      }
      else if (!hasRedRoot(right.getLeftChild()) && 
	       !hasRedRoot(right.getRightChild())) {

	// Case 2: left is black (or empty), right is black, and any children
	// of right are black.  We change the color of right.  This yields
	// a red-black tree, possibly with a red root, but the number of
	// black nodes on each path from root to an empty subtree is the same 
	// as the number of black nodes initially on each path from root to an
	// empty subtree in left.

	right = colorRoot(right, Color.red);
	if (root.getColor().equals(Color.black)) {
	  return new TreeBooleanPair(new BinaryTree(root, left, right), false);
	}
	else {

	  // If root is red, we can change it to black, yielding a red-black
	  // tree.  Furthermore, the number of black nodes on any path from
	  // root to an empty subtree increases by 1 to the proper number.

	  return 
	    new TreeBooleanPair(new BinaryTree(new Node(root.getContents(),
							Color.black),
					       left, right),
				true);
	}
      }
      else {
	if (!hasRedRoot(right.getRightChild())) {

	  // Case 3: left is black (or empty), right is black, the left
	  // child of right is red, and the right child of right is black
	  // (or empty).  We change the colors of right and the left child
	  // of right and do a rotateRight on right.  This yields an
	  // instance of Case 4 in which the number of black nodes on each
	  // path is unchanged.

	  right = rotateRight(new Node(right.getRoot().getContents(),
				       Color.red),
			      colorRoot(right.getLeftChild(), Color.black),
			      right.getRightChild());
	}

	// Case 4: left is black (or empty), right is black, and the right
	// child of right is red.  We change the color of the right child of 
	// right, make right the color of root and make root black, then do a 
	// rotateLeft.  This yields a red-black tree, possibly with a red
	// root.  Furthermore, the number of black nodes on any path from
	// root to an empty subtree is the same as the number initially on any
	// path from root to an empty subtree in right.

	right = new BinaryTree(new Node(right.getRoot().getContents(),
					root.getColor()),
			       right.getLeftChild(),
			       colorRoot(right.getRightChild(), Color.black));
	root = new Node(root.getContents(), Color.black);
	return new TreeBooleanPair(rotateLeft(root, left, right), true);
      }
    }
  }

      
  /**
   * Returns a TreeBooleanPair describing the result of rebalancing
   * the red-black tree described by the parameters.
   * @param root   The root of the tree to be balanced.
   * @param left   The left child of the tree to be balanced.  This is
   *               assumed to be a valid red-black tree, possibly with a
   *               red root if <code>root</code> is black.
   * @param right  The right child of the tree to be balanced.  This is
   *               assumed to be a valid red-black tree, possibly with a
   *               red root if root is <code>black</code>.  The number of black 
   *               nodes on each path is either equal to or one less than 
   *               the number of black nodes on each path of <code>left</code>.
   *               In the latter case, <code>right</code> cannot have a red root.
   * @param bal    <code>true</code> iff the number of black nodes in each path
   *               is the same for <code>left</code> and <code>right</code>.
   * @return       A TreeBooleanPair whose tree is a valid red-black tree,
   *               possibly with a red root, containing the nodes given
   *               in the parameters, and whose boolean is <code>true</code> iff
   *               the number of black nodes on each path is the same as
   *               the number of black nodes on each path from <code>root</code>
   *               to any empty subtree in <code>left</code> in the tree described
   *               by the parameters.
   *               
   */
  private static TreeBooleanPair balanceRight(Node root, BinaryTree left,
					      BinaryTree right, boolean bal) {
    if (bal) {

      // The tree is a valid red-black tree, perhaps with a red root.

      return new TreeBooleanPair(new BinaryTree(root, left, right), true);
    }
    else {

      // The number of black nodes on a path from root to any empty subtree
      // in right is one less than the number of black nodes on a path from
      // root to any empty subtree in left.  Furthermore, right does not have 
      // a red root.

      if (hasRedRoot(left)) {

	// Case 1:  root is black, left is red, and right is black (or empty).
	// Because each path from root to an empty subtree in left must 
	// contain at least two black nodes, left has two black children.  
	// We first change the colors of root and left, then do a rotateRight.

	root = new Node(root.getContents(), Color.red);
	left = colorRoot(left, Color.black);
	BinaryTree t = rotateRight(root, left, right);

	// right is now red with two black children, and the number of black
	// nodes on any path from right to an empty subtree in its right child
	// is one less than the number of black nodes on any path from right 
	// to an empty subtree in its left child.  right is therefore and 
	// insance of one of the other 3 cases.  

	right = balanceRight(t.getRightChild().getRoot(),
			     t.getRightChild().getLeftChild(),
			     t.getRightChild().getRightChild(),
			     false).getTree();

	// Because right had a red root, the tree is now a red-black tree.

	return new TreeBooleanPair(new BinaryTree(t.getRoot(),
						  t.getLeftChild(),
						  right),
				   true);
      }
      else if (!hasRedRoot(left.getRightChild()) && 
	       !hasRedRoot(left.getLeftChild())) {

	// Case 2: left is black, right is black (or empty), and any children
	// of left are black.  We change the color of left.  This yields
	// a red-black tree, possibly with a red root, but the number of
	// black nodes on each path from root to an empty subtree is the same 
	// as the number of black nodes initially on each path from root to an 
	// empty subtree in right.

	left = colorRoot(left, Color.red);
	if (root.getColor().equals(Color.black)) {
	  return new TreeBooleanPair(new BinaryTree(root, left, right), false);
	}
	else {

	  // If root is red, we can change it to black, yielding a red-black
	  // tree.  Furthermore, the number of black nodes on any path from
	  // root to an empty subtree increases by 1 to the proper number.

	  return 
	    new TreeBooleanPair(new BinaryTree(new Node(root.getContents(),
							Color.black),
					       left, right),
				true);
	}
      }
      else {
	if (!hasRedRoot(left.getLeftChild())) {

	  // Case 3: left is black, right is black (or empty), the right
	  // child of left is red, and the left child of left is black
	  // (or empty).  We change the colors of left and the right child
	  // of left and do a rotateLeft on left.  This yields an
	  // instance of Case 4 in which the number of black nodes on each
	  // path is unchanged.

	  left = rotateLeft(new Node(left.getRoot().getContents(),
				     Color.red),
			    left.getLeftChild(),
			    colorRoot(left.getRightChild(), Color.black));
	}

	// Case 4: left is black, right is black (or empty), and the left
	// child of left is red.  We change the color of the left child of 
	// left, make left the color of root and make root black, then do a 
	// rotateRight.  This yields a red-black tree, possibly with a red
	// root.  Furthermore, the number of black nodes on any path from
	// root to an empty subtree is the same as the number initially on 
	// any path from root to an empty subtree in left.

	left = new BinaryTree(new Node(left.getRoot().getContents(),
				       root.getColor()),
			      colorRoot(left.getLeftChild(), Color.black),
			      left.getRightChild());
	root = new Node(root.getContents(), Color.black);
	return new TreeBooleanPair(rotateRight(root, left, right), true);
      }
    }
  }

  /**
   * Returns a drawing of this tree.
   */
  public JComponent getDrawing() {
    return theTree.getDrawing();
  }

  /**
   * Returns a drawing of this tree using the given Font.
   * @throws NullPointerException if <code>fnt</code> is <code>null</code>.
   */
  public JComponent getDrawing(Font fnt) {
    return theTree.getDrawing(fnt);
  }

  /** 
   * Because this structure is immutable, this method simply returns this tree.
   */
  public Object clone() {
    return this;
  }
}

/**
 * A pair consisting of a BinaryTree and a boolean.  The boolean indicates
 * whether a black node has been removed from the tree, thus rendering it
 * out of balance with respect to the tree containing it.
 */
final class TreeBooleanPair {

  /**
   * The tree.
   */
  private BinaryTree theTree;

  /**
   * <code>true</code> iff the number of black nodes on each path is the same
   * as in the tree from which <code>theTree</code> was derived.
   */
  private boolean balanced;

  /**
   * Constructs a TreeBooleanPair from the given <code>BinaryTree</code> and
   * boolean.
   */
  public TreeBooleanPair(BinaryTree t, boolean b) {
    theTree = t;
    balanced = b;
  }

  /**
   * Returns the tree.
   */
  public BinaryTree getTree() {
    return theTree;
  }

  /**
   * Returns <code>true</code> iff the number of black nodes on each path has
   * not changed.
   */
  public boolean isBalanced() {
    return balanced;
  }
}

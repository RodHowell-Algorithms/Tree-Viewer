/*
 * AA Tree	12/03/99
 *
 * Copyright (c) 1999, All Rights Reserved
 *
 */

package edu.ksu.cis.viewer;

import java.awt.*;
import javax.swing.*;



/**
 * An immutable AA tree that can draw itself.  An AA tree is a binary
 * search tree in which each node has a level and a color, either red or 
 * black. The level of any leaf is 1, and the level of any non-leaf is one
 * greater than the level of its left child (if it has one), and either equal
 * to or one greater than the level of its right child (if it has one). A node
 * is considered to be red if it has the same level as its parent; otherwise,
 * it is considered to be black (the root is always black). The parent
 * of a red node must always be black. 
 *
 * @author 	Stella Houston and Rod Howell
 *              (<a href="mailto:rhowell@ksu.edu">rhowell@ksu.edu</a>)
 */
public final class AATree implements BSTInterface {
  
  /** 
   * The tree.  The <code>tag</code> fields of the nodes will be used to
   * store the levels of the subtrees.
   * @see Node
   */
  private BinaryTree theTree;

  /**
   * Used for consistency in serialization.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructs an empty AA Tree.  
   */
  public AATree() {
    theTree = new BinaryTree();}

  /**
   * Constructs an AATree object from the given BinaryTree.  <code>t</code>
   * is assumed to be arranged into a valid AA tree with the level of
   * each subtree stored in the Node at its root.
   */
  private AATree(BinaryTree t) {
    theTree = t;}
 
  /** 
   * Returns the <code>AATree</code> resulting from the insertion
   * <code>key</code> into this <code>AATree</code>.  If <code>key</code>
   * is already in the tree, an identical tree is returned.
   * @exception        NullPointerException
   *		       If <code>key</code> is <code>null</code>
   */
  
  public BSTInterface put(String key) throws NullPointerException {
    if (key == null) {
      throw new NullPointerException();}
    
    return new AATree(put(key, theTree));}

  /**
   * Returns the AA Tree that results from the insertion of <code>s</code>
   * into <code>t</code>, which is assumed to be a valid AA Tree.  If <code>t</code>
   * contains <code>s</code>, an identical tree is returned.
   */
  private static BinaryTree put(String s, BinaryTree t) {
    if (t.isEmpty()) {
      return new BinaryTree(new Node(s,1), null, null);}
     
    else {
      Node root = t.getRoot();
      BinaryTree left = t.getLeftChild();
      BinaryTree right = t.getRightChild();
      int result = s.compareTo(root.getContents());
      if (result == 0) {
        return t;}
      else if (result < 0) {
        left = put(s, t.getLeftChild());}
      else {
        right = put(s, t.getRightChild());}
      t = skew(root,left,right);
      t = split(t.getRoot(),t.getLeftChild(),t.getRightChild());
      return getBinaryTree(t.getRoot(),t.getLeftChild(),t.getRightChild());}}

  /**
   * Returns the <code>BinaryTree</code> resulting from performing a single 
   * rotate left on the binary tree whose root is given by <code>root</code>,
   * whose left-hand child is given by <code>left</code>, and whose right-hand
   * child is given by <code>right</code>.  The <code>tag</code> fields of all
   * nodes are guaranteed to record the level of their subtrees provided
   * this was initially the case in both <code>left</code> and <code>right</code>.
   */
  private static BinaryTree rotateLeft(Node root,BinaryTree left,BinaryTree right) {
    
    BinaryTree newLeft = getBinaryTree(root,left,right.getLeftChild());
    
    return getBinaryTree(right.getRoot(),newLeft,right.getRightChild());}

  /**
   * Returns the <code>BinaryTree</code> resulting from performing a single 
   * rotate right on the binary tree whose root is given by <code>root</code>,
   * whose left-hand child is given by <code>left</code>, and whose right-hand
   * child is given by <code>right</code>.  The <code>tag</code> fields of all
   * nodes are guaranteed to record the level of their subtrees provided
   * this was initially the case in both <code>left</code> and <code>right</code>.
   */
  private static BinaryTree rotateRight(Node root,BinaryTree left,BinaryTree right) {
    
    BinaryTree newRight = getBinaryTree(root,left.getRightChild(),right);
    
    return getBinaryTree(left.getRoot(),left.getLeftChild(),newRight);}

  /**
   * Returns the level <code>t</code>.  <code>t</code> is assumed to have its level
   * recorded in the <code>tag</code> field of its root if it is non-empty.
   */
  private static int level(BinaryTree t) {
    if (t.isEmpty()) {
      return 0;}
    else {
      return (t.getRoot()).getTag();}}

  /**
   * Returns the <code>BinaryTree</code> resulting from the removal of the horizontal
   * left link on the tree whose root is given by <code>root</code>, whose left-hand 
   * child is given by <code>left</code>, and whose right-hand child is given 
   * by <code>right</code>.  The <code>tag</code> fields of all nodes are guaranteed to 
   * record the level of their subtrees provided this was initially the case in 
   * both <code>left</code> and <code>right</code>.
   */
  private static BinaryTree skew(Node root,BinaryTree left,BinaryTree right) {
    if (!left.isEmpty() && level(left) == root.getTag()) {
      BinaryTree t = rotateRight(root,left,right);
      return getBinaryTree(t.getRoot(),t.getLeftChild(),t.getRightChild());}

    else { 
      return getBinaryTree(root,left,right);}}

  /**
   * Returns the <code>BinaryTree</code> resulting from the removal of consecutive horizontal
   * right links on the tree whose root is given by <code>root</code>, whose left-hand child is 
   * given by <code>left</code>, and whose right-hand child is given by <code>right</code>.  The 
   * <code>tag</code> fields of all nodes are guaranteed to record the level of their subtrees 
   * provided this was initially the case in both <code>left</code> and <code>right</code>.
   */  
  private static BinaryTree split(Node root,BinaryTree left,BinaryTree right) {
    if (!right.isEmpty() && level(right.getRightChild()) == root.getTag()) {
      BinaryTree t = rotateLeft(root,left,right);
      return getBinaryTree(new Node(t.getRoot().getContents(), level(t)+1),
                             t.getLeftChild(),t.getRightChild());}
    
    else {
      return getBinaryTree(root,left,right);}}

  /**
   * Returns the <code>AATree</code> resulting from the removal of <code>key</code>
   * from this <code>AATree</code>.  If <code>key</code> is not in the tree, an
   * identical tree is returned.
   * @exception         NullPointerException
   *                    If key is <code>null</code>
   */
  public BSTInterface remove(String key) throws NullPointerException{
    if (key == null) {
      throw new NullPointerException();}
     
    return new AATree(remove(key, theTree));}

  /**
   * Returns the AA Tree resulting from the removal of <code>s</code> from 
   * <code>t</code>, which is assumed to be a valid AA Tree.  If <code>t</code>
   * does not contain <code>s</code>, an identical tree is returned.
   */
  private static BinaryTree remove(String s, BinaryTree t) {
    if (t.isEmpty()) { 
      return t;}
     
    else {
      Node root = t.getRoot();
      BinaryTree left = t.getLeftChild();
      BinaryTree right = t.getRightChild();
      int result = s.compareTo(root.getContents());
      if (result == 0) {
        if (!left.isEmpty()) {
          BinaryTree newTree = minToRoot(right);
	  root = new Node(newTree.getRoot().getContents(),level(t));
          right = newTree.getRightChild();
          return balance(root,left,right);} 

        else{              
	  return right;}}

      else if (result < 0) {
        left = remove(s, left);
        return balance(root,left,right);}
        
      else {
        right = remove(s, right);
        return balance(root,left,right);}}}
        
  /**
   * Returns an AA Tree containing the nodes of all three arguments.
   */
  private static BinaryTree balance(Node root,BinaryTree left,BinaryTree right) {
    
    int leftLevel = level(left);
    int rightLevel = level(right);
    int treeLevel = (root.getTag());

    if (leftLevel < treeLevel-1 || rightLevel < treeLevel-1) {
      root = new Node(root.getContents(),--treeLevel);

      if (rightLevel > treeLevel) {
        right = getBinaryTree(new Node(right.getRoot().getContents(),treeLevel),
                               right.getLeftChild(),right.getRightChild());}
             
      BinaryTree t = skew(root,left,right);
      right = t.getRightChild();
      
      if (!right.isEmpty()) {
        BinaryTree tree = skew(right.getRoot(),right.getLeftChild(),right.getRightChild());
        root = t.getRoot();
        left = t.getLeftChild();
        
        if (!tree.getRightChild().isEmpty()) {
          BinaryTree treeRight = skew(tree.getRightChild().getRoot(),
                                      tree.getRightChild().getLeftChild(),
                                      tree.getRightChild().getRightChild());
          right = getBinaryTree(tree.getRoot(),tree.getLeftChild(),treeRight);}
          
        else {
          right = tree;}}
                     
      t = split(root,left,right);
      root = t.getRoot();
      left = t.getLeftChild();
      right = t.getRightChild();
      
      if (!right.isEmpty()) {
        right = split(right.getRoot(),right.getLeftChild(),right.getRightChild());}
      
      t = getBinaryTree(root,left,right);
      return t;}
        
      else {
        return getBinaryTree(root,left,right);}}
            
  /**
   * Returns a binary search tree containing the same nodes as <code>t</code>, but
   * whose left child is empty and whose right child is an AA tree.  The 
   * <code>tag</code> field of the root of the returned tree is meaningless.  
   * <code>t</code> is assumed to be a valid AA Tree.
   */
  private static BinaryTree minToRoot(BinaryTree t) {
    if (t.getLeftChild().isEmpty()) {
      return t;}

    else {
      BinaryTree temp = minToRoot(t.getLeftChild());
      return new BinaryTree(temp.getRoot(), null,
                         balance(t.getRoot(),temp.getRightChild(),
                                 t.getRightChild()));}}
 
  /**
   * Returns an <code>AA Tree</code> containing the nodes of all three arguments, where
   * the <code>right</code> child will be red if it is at the same level as the root on the
   * binary tree whose root is given by <code>root</code>, whose left-hand child is given 
   * by <code>left</code>, and whose right-hand child is given by <code>right</code>. 
   */
  private static BinaryTree getBinaryTree(Node root,BinaryTree left,   
                                          BinaryTree right) {
    Color red = Viewer.isMonochrome() ? Color.gray : Color.red;
    if ((!left.isEmpty())&&(left != null)) {
      left = new BinaryTree(new Node(left.getRoot().getContents(),left.getRoot().getTag()),
                            left.getLeftChild(),left.getRightChild());}
    
    if ((!right.isEmpty())&&(right != null)) {
      if (level(right) == root.getTag()) {
        right = new BinaryTree(new Node(right.getRoot().getContents(),
                                        right.getRoot().getTag(),red),
                               right.getLeftChild(),right.getRightChild());}
      else {
        right = new BinaryTree(new Node(right.getRoot().getContents(),level(right)),
                               right.getLeftChild(),right.getRightChild());}}
    return new BinaryTree(new Node(root.getContents(),root.getTag()),left,right);}
             
  /**
   * Returns a drawing of the tree.
   */
  public JComponent getDrawing(){
    return theTree.getDrawing();}

  /**
   * Returns a drawing of the tree using the given Font.
   * @throws NullPointerException if <code>fnt</code> is <code>null</code>.
   */
  public JComponent getDrawing(Font fnt){
    return theTree.getDrawing(fnt);}
  
  /**
   * Returns a clone of this tree.
   * @return            a clone of this tree
   */
  public Object clone() {
    return this;}
}
 

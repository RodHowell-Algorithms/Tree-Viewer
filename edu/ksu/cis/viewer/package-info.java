/**
 * A package of tools for viewing and manipulating trees. 
 * There are two main uses for this package:
 *
 * <ol>
 *   <li>
 *      A search tree viewer. This viewer is a program for displaying and 
 *      manipulating search trees. Strings may be inserted or deleted from the 
 *      trees, the user may move forward or backward through the history of 
 *      modifications, and trees with their histories may be cloned and 
 *      manipulated independently. The driver for this program is the 
 *      {@link Viewer} class. It 
 *      requires no arguments or other supporting packages.
 *   </li>
 *   <li>
 *     A set of tree-viewing tools for use within user programs. A developer 
 *     may define tree structure which implements {@link TreeInterface}. If 
 *     colored nodes are desired, a class implementing {@link Colorizer} may 
 *     also be defined. A drawing of the tree can then be created using the 
 *     {@link TreeDrawing}, {@link TreeComponent}, and/or 
 *     {@link TreeFrame} classes.
 *   </li>
 * </ol>
 * @see Viewer
 * @see TreeInterface
 * @see Colorizer
 * @see TreeDrawing
 * @see TreeComponent
 * @see TreeFrame
 */
package edu.ksu.cis.viewer;

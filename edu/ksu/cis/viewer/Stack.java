/*
 * Stack.java   11/15/99
 *
 * Copyright (c) 1998, 1999, Rod Howell, All Rights Reserved.
 *
 */

package edu.ksu.cis.viewer;

import java.util.EmptyStackException;
import java.io.Serializable;


/**
 * A stack built from an immutable {@link ConsList ConsList}.  Using an 
 * immutable structure
 * for the underlying storage allows a shallow clone to be sufficient.
 *
 * @author Rod Howell
 *         (<a href="mailto:rhowell@ksu.edu">rhowell@ksu.edu</a>)
 */
public final class Stack<E extends Serializable> implements Serializable {

  /**
   * The structure used for the stack.
   */
  private ConsList<E> theStack;

  /**
   * Used for consistency in serialization.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructs an empty Stack.
   */
  public Stack() {
    theStack = new ConsList<E>();
  }

  /**
   * Pushes the given Object onto the top of the stack.
   * @param   obj                  the object to push onto the stack
   */
  public void push(E obj) {
    theStack = new ConsList<E>(obj, theStack);
  }

  /**
   * Removes the Object from the top of the stack and returns it.
   * @return  the object at the top of the stack
   * @throws  EmptyStackException  If the stack is empty.
   */
  public E pop() throws EmptyStackException {
    if (theStack.isEmpty()) {
      throw new EmptyStackException();
    }
    E top = theStack.getHead();
    theStack = theStack.getTail();
    return top;
  }

  /**
   * Returns true if the stack is empty.
   * @return   whether this stack is empty
   */
  public boolean empty() {
    return theStack.isEmpty();
  }

  /**
   * Returns a clone of this stack.
   * Because the underlying structure is immutable, a shallow clone is
   * performed.
   */
  public Stack<E> clone() {
    Stack<E> theClone = new Stack<E>();
    theClone.theStack = theStack;
    return theClone;
  }
}

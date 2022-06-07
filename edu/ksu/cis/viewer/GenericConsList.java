/*
 * GenericConsList.java     06/07/2022
 *
 * Copyright (c) 1998, 1999, 2022, Rod Howell, All Rights Reserved.
 *
 */

package edu.ksu.cis.viewer;

import java.io.Serializable;

/**
 * An immutable linear recursive structure with a head and
 * a tail, which is a GenericConsList.
 * @param <E> the type of the elements in the head of each list
 *
 * @author Rod Howell
 *         (<a href="mailto:rhowell@ksu.edu">rhowell@ksu.edu</a>)
 */
public final class GenericConsList<E extends Serializable>
  implements Serializable {

  /**
   * Is <code>true</code> if this <code>ConsList</code> is empty.
   */
  private boolean empty;

  /**
   * The head of this <code>GenericConsList</code>.
   */
  private E head;

  /**
   * The tail of this <code>GenericConsList</code>.
   */
  private GenericConsList<E> tail;

  /**
   * Used for consistency in serialization.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructs an empty GenericConsList.
   */
  public GenericConsList() {
    empty = true;
  }

  /**
   * Constructs a Generic ConsList with the given head and tail.  If the given 
   * tail is
   * <code>null</code>, an empty GenericConsList is used in its place.
   * @param    h        the head of the list
   * @param    lst      the tail of the list
   */
  public GenericConsList(E h, GenericConsList<E> lst) {
    empty = false;
    head = h;
    tail = lst == null ? new GenericConsList<E>() : lst;
  }

  /**
   * Returns <code>true</code> if this list is empty.
   * @return            whether this list is empty
   */
  public boolean isEmpty() {
    return empty;
  }

  /**
   * Returns the head of this list.
   * @return            the head of this list
   * @exception         EmptyListException
   *                    If this list is empty.
   */
  public E getHead() throws EmptyListException {
    if (empty) {
      throw new EmptyListException();
    }
    return head;
  }

  /**
   * Returns the tail of the list.
   * @return            the tail of this list
   * @exception         EmptyListException
   *                    If this list is empty.
   */
  public GenericConsList<E> getTail() throws EmptyListException {
    if (empty) {
      throw new EmptyListException();
    }
    return tail;
  }
}

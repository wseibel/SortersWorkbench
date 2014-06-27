package de.uks.workbench.list;

import de.uks.workbench.interfaces.ISortElement;

/**
 * 
 * A generic list node
 * 
 */
public class ListNode<E extends ISortElement> { 
	public E element;
	public ListNode<E> next;
	
	// Constructors
	public ListNode(E element) {
		this(element, null);
	}

	public ListNode(E element, ListNode<E> next) {
		this.element = element;
		this.next = next;
	}
	
	/**
	 * Returns a the key of the contained element as a string
	 * 
	 * @return the key of the contained element
	 */
	@Override
	public String toString() {
		return "" + element.getKey();
	}
}

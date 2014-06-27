package de.uks.workbench.list;

import de.uks.workbench.interfaces.ISortElement;

/**
 * 
 * A generic implementation of a list with a header and a reference to the last element
 * 
 * @param <E>
 */
public class ElementList<E extends ISortElement> {

	private ListNode<E> header;
	private ListNode<E> last;

	/**
	 * Construct the list
	 */
	public ElementList() {
		header = new ListNode<E>(null);
	}

	/**
	 * Test if the list is logically empty
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return header.next == null;
	}

	/**
	 * Make the list logically empty.
	 */
	public void makeEmpty() {
		header.next = null;
		last = null;
	}

	/**
	 * Set this list as the given list
	 * 
	 * @param list
	 *                the list which this list will point to
	 */
	public void setAs(ElementList<E> list) {
		this.makeEmpty();
		header.next = list.first();
		last = list.last;
	}

	/**
	 * @return the fist element of the list
	 */
	public ListNode<E> first() {
		return header.next;
	}

	/**
	 * @return the last element of the list
	 */
	public ListNode<E> last() {
		return last;
	}

	/**
	 * Appends a node to the end of the list
	 * 
	 * @param node
	 *                the node to append
	 */
	public void appendNode(ListNode<E> node) {
		if (last != null) {
			last.next = node;
			last = last.next;
		} else {
			header.next = node;
			last = node;
			;
		}
	}

	/**
	 * Unlinks the "last" element from its next element, so the list ends with "last"
	 */
	public void unlinkAfterLast() {
		if (last != null) {
			last.next = null;
		}
	}

	/**
	 * Appends a list to the end of this list
	 * 
	 * @param listToAppend
	 *                the list to append
	 */
	public void appendList(ElementList<E> listToAppend) {
		if (listToAppend.first() != null) {
			if (last != null) {
				last.next = listToAppend.first();
				last = listToAppend.last();
			} else {
				header.next = listToAppend.first();
				last = listToAppend.last();
			}
		}
	}

	/**
	 * Shows all the keys of every element in the list
	 * 
	 * @return the keys of the elements in the list
	 */
	@Override
	public String toString() {
		String result = "";
		if (!this.isEmpty()) {
			for (ListNode<E> iter = this.first(); iter != null; iter = iter.next) {
				result += iter + ", ";
			}
			result += "\n";
		}
		return result;
	}
}

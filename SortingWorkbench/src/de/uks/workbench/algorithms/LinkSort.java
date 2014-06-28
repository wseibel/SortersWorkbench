package de.uks.workbench.algorithms;

import de.uks.workbench.interfaces.IListAlgorithm;
import de.uks.workbench.interfaces.ISortElement;
import de.uks.workbench.list.ElementList;
import de.uks.workbench.list.ListNode;

/**
 * 
 * Generic version of the LinkSort. A Quicksort algorithm for linked lists.
 * 
 * @param E
 *                the elements being sorted
 */
public class LinkSort<E extends ISortElement> implements IListAlgorithm<E> {

	private static final boolean IS_STABLE = true;

	/**
	 * The start method for the sorting algorithm
	 * 
	 * @param L
	 *                the list to sort
	 */
	@Override
	public void runSort(ElementList<E> L) {
		// if list size > 1
		if (!L.isEmpty() && L.first().next != null) {
			ElementList<E> L1 = new ElementList<E>(); // L1 contains elements <= pivot
			int H = L.last().element.getKey();
			ListNode<E> iter = L.first();
			// ends with last element if list is unary...
			// ...or with the first element != pivot
			while (iter.element.getKey() == H) {
				L1.appendNode(iter);
				if (iter.next != null && iter.next == L.last()) {
					L1.appendNode(iter.next);
					return;
				}
				iter = iter.next;
			}
			// list is not unary -> another list is needed
			ElementList<E> L2 = new ElementList<E>(); // L2 contains elements > pivot
			// ends before last element (pivot)
			while (iter.next != null) {
				// divide list in two parts
				if (iter.element.getKey() <= H) {
					L1.appendNode(iter);
				} else if (iter.element.getKey() > H) {
					L2.appendNode(iter);
				}
				iter = iter.next;
			}
			L1.unlinkAfterLast();
			L2.unlinkAfterLast();
			// if L1 size > 1: sort L1 recursively
			if (!L1.isEmpty() && L1.first().next != null) {
				runSort(L1);
			}
			// if L2 size > 1: sort L2 recursively
			if (!L2.isEmpty() && L2.first().next != null) {
				runSort(L2);
			}
			// append the last element (pivot) to L1 after sorting
			L1.appendNode(iter);
			// link the lists after sorting
			L1.appendList(L2);
			L.setAs(L1);
		}
	}

	/**
	 * The start method for the sorting algorithm with a build-in counter for key comparisons
	 * 
	 * @param L
	 *                the list to sort
	 * @param keycount
	 *                The initializing value for the key comparison counter
	 * @return The number of key comparisons the algorithm needed
	 */
	public long runSortWithCounter(ElementList<E> L, long keycount) {
		// if list size > 1
		if (!L.isEmpty() && L.first().next != null) {
			ElementList<E> L1 = new ElementList<E>(); // L1 contains elements <= pivot
			int H = L.last().element.getKey();
			ListNode<E> iter = L.first();
			// ends with last element if list is unary...
			// ...or with the first element != pivot
			while (iter.element.getKey() == H) {
				keycount++;
				L1.appendNode(iter);
				if (iter.next != null && iter.next == L.last()) {
					L1.appendNode(iter.next);
					return keycount;
				}
				iter = iter.next;
			}
			keycount++;
			// list is not unary -> another list is needed
			ElementList<E> L2 = new ElementList<E>(); // L2 contains elements > pivot
			// ends before last element (pivot)
			while (iter.next != null) {
				// divide list in two parts
				if (iter.element.getKey() <= H) {
					L1.appendNode(iter);
				} else if (iter.element.getKey() > H) {
					L2.appendNode(iter);
				}
				keycount += 2;
				iter = iter.next;
			}
			L1.unlinkAfterLast();
			L2.unlinkAfterLast();
			// if L1 size > 1: sort L1 recursively
			if (!L1.isEmpty() && L1.first().next != null) {
				keycount = runSortWithCounter(L1, keycount);
			}
			// L2 can not be empty
			keycount = runSortWithCounter(L2, keycount);
			// append the last element (pivot) after sorting
			L1.appendNode(iter);
			L1.appendList(L2);
			L.setAs(L1);
		}
		return keycount;
	}

	/**
	 * @return the boolean which indicates if the algorithm is stable
	 */
	public boolean getIsStable() {
		return IS_STABLE;
	}

}

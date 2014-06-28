package de.uks.workbench.algorithms;

import de.uks.workbench.interfaces.IListAlgorithm;
import de.uks.workbench.interfaces.ISortElement;
import de.uks.workbench.list.ElementList;
import de.uks.workbench.list.ListNode;

/**
 * 
 * Generic version of the TriSort. A Quicksort algorithm for linked lists.
 * 
 * @param E
 *                the elements being sorted
 */
public class TriSort<E extends ISortElement> implements IListAlgorithm<E> {

	private static final boolean IS_STABLE = true;

	/**
	 * The start method for the sorting algorithm
	 * 
	 * @param L
	 *                the list to sort
	 */
	@Override
	public void runSort(ElementList<E> L) {
		ElementList<E> L1 = new ElementList<E>();	// L1 contains elements < pivot
		ElementList<E> L2 = new ElementList<E>();	// L1 contains elements > pivot
		ElementList<E> Lh = new ElementList<E>();	// L1 contains elements == pivot

		if (!L.isEmpty() && L.first().next != null) {
			int H = L.last().element.getKey();
			for (ListNode<E> iter = L.first(); iter != null; iter = iter.next) {
				if (iter.element.getKey() < H) {
					L1.appendNode(iter);
				} else if (iter.element.getKey() > H) {
					L2.appendNode(iter);
				} else {
					Lh.appendNode(iter);
				}
			}
			L1.unlinkAfterLast();
			L2.unlinkAfterLast();
			Lh.unlinkAfterLast();
			// if L1 size > 1: sort L1 recursively
			if (!L1.isEmpty() && L1.first().next != null) {
				runSort(L1);
			}
			// if L2 size > 1: sort L2 recursively
			if (!L2.isEmpty() && L2.first().next != null) {
				runSort(L2);
			}
			// link all three lists after sorting
			L1.appendList(Lh);
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
		ElementList<E> L1 = new ElementList<E>();	// L1 contains elements < pivot
		ElementList<E> L2 = new ElementList<E>();	// L1 contains elements > pivot
		ElementList<E> Lh = new ElementList<E>();	// L1 contains elements == pivot

		if (!L.isEmpty() && L.first().next != null) {
			int H = L.last().element.getKey();
			for (ListNode<E> iter = L.first(); iter != null; iter = iter.next) {
				if (iter.element.getKey() < H) {
					L1.appendNode(iter);
				} else if (iter.element.getKey() > H) {
					L2.appendNode(iter);
				} else {
					Lh.appendNode(iter);
				}
				keycount += 2;
			}
			L1.unlinkAfterLast();
			L2.unlinkAfterLast();
			Lh.unlinkAfterLast();
			// if L1 size > 1: sort L1 recursively
			if (!L1.isEmpty() && L1.first().next != null) {
				keycount = runSortWithCounter(L1, keycount);
			}
			// if L2 size > 1: sort L2 recursively
			if (!L2.isEmpty() && L2.first().next != null) {
				keycount = runSortWithCounter(L2, keycount);
			}
			// link all three lists after sorting
			L1.appendList(Lh);
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

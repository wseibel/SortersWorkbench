package de.uks.workbench.interfaces;

import de.uks.workbench.list.ElementList;

/**
 * 
 * An interface for all sorting algorithms based on lists
 * 
 * @param E
 *                the elements being sorted in the algorithm
 */
public interface IListAlgorithm<E extends ISortElement> {
	/**
	 * The start method for the sorting algorithm
	 * 
	 * @param first
	 *                The first element in the list
	 * @param last
	 *                The last element in the list
	 */
	public void runSort(ElementList<E> L);

	/**
	 * The start method for the sorting algorithm with a build-in counter for key comparisons
	 * 
	 * @param first
	 *                The first element in the list
	 * @param last
	 *                The last element in the list
	 * @param keycount
	 *                The initializing value for the key comparison counter
	 * @return The number of key comparisons the algorithm needed
	 */
	public long runSortWithCounter(ElementList<E> L, long keycount);

	/**
	 * @return the boolean which indicates if the algorithm is stable
	 */
	public boolean getIsStable();
}
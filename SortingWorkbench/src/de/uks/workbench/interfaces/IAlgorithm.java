package de.uks.workbench.interfaces;

/**
 * 
 * An interface for all sorting algorithms based on arrays
 * 
 */
public interface IAlgorithm <T extends ISortElement> {
	/**
	 * The start method for the sorting algorithm
	 * 
	 * @param a
	 *                The array which will be sorted
	 * @param l
	 *                The left index of the sub array which is being sorted
	 * @param r
	 *                The right index of the sub array which is being sorted
	 */
	public void runSort(T[] a, int l, int r);

	/**
	 * The start method for the sorting algorithm with a build-in counter for key comparisons
	 * 
	 * @param a
	 *                The array which will be sorted
	 * @param l
	 *                The left index of the sub array which is being sorted
	 * @param r
	 *                The right index of the sub array which is being sorted
	 * @param keycount
	 *                The initializing value for the key comparison counter
	 * @return The number of key comparisons the algorithm needed
	 */
	public long runSortWithCounter(T[] a, int l, int r, long keycount);

	/**
	 * @return the boolean which indicates if the algorithm is stable
	 */
	public boolean getIsStable();
}

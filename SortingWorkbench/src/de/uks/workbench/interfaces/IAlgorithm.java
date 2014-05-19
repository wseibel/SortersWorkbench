package de.uks.workbench.interfaces;

/**
 * 
 * An interface for all sort algorithms
 * 
 */
public interface IAlgorithm {
	/**
	 * The start method for the sort algorithm
	 * 
	 * @param a
	 *                The array which will be sorted
	 * @param l
	 *                The left index of the sub array which is being sorted
	 * @param r
	 *                The right index of the sub array which is being sorted
	 * @param keycount
	 *                The initializing value for the key comparison counter
	 */
	public <T extends ISortElement> void runSort(T[] a, int l, int r);

	/**
	 * The start method for the sort algorithm with a build-in counter for key comparisons
	 * 
	 * @param a
	 *                The array which will be sorted
	 * @param l
	 *                The left index of the sub array which is being sorted
	 * @param r
	 *                The right index of the sub array which is being sorted
	 * @return The number of key comparisons the algorithm needed
	 */
	public <T extends ISortElement> long runSortWithCounter(T[] a, int l, int r, long keycount);

	/**
	 * @return the boolean which indicates if the algorithm is stable
	 */
	public boolean getIsStable();
}

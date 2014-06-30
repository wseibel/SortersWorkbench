package de.uks.workbench.algorithms;

import de.uks.workbench.interfaces.IAlgorithm;
import de.uks.workbench.interfaces.ISortElement;
import de.uks.workbench.util.Util;

/**
 * 
 * Generic version of a median of 3 quicksort.
 * 
 * @param T
 *                the type of the values being sorted
 */
public class Med3Quicksort<T extends ISortElement> implements IAlgorithm<T> {

	public int c = 20;

	private static final boolean IS_STABLE = false;

	/**
	 * The start method for the median of 3 quicksort
	 * 
	 * @param array
	 *                The array which will be sorted
	 * @param l
	 *                The left index of the (sub) array which is being sorted
	 * @param r
	 *                The right index of the (sub) array which is being sorted
	 */
	public void runSort(T[] array, int l, int r) {
		int j;
		T aux;
		if (r - l >= c) {
			int k = (l + r) / 2;
			Util.switchValues(array, k, l);
			// Pivot as median of 3
			if (array[l + 1].getKey() > array[r].getKey()) {
				Util.switchValues(array, l + 1, r);
			}
			if (array[l].getKey() > array[r].getKey()) {
				Util.switchValues(array, l, r);
			}
			if (array[l + 1].getKey() > array[l].getKey()) {
				Util.switchValues(array, l + 1, l);
			}
			// Median of 3 sits in a[l] and a[l+1] <= a[l]as pivot
			int i = l + 1;
			j = r;
			int v = array[l].getKey();
			while (true) {
				do {
					i++;
				} while (array[i].getKey() < v);
				do {
					j--;
				} while (array[j].getKey() > v);
				if (i > j) {
					break; // i is pivot position
				}
				Util.switchValues(array, j, i);
			}
			Util.switchValues(array, j, l);
			runSort(array, l, j - 1);
			runSort(array, i, r);
		}
		// Insertionsort for a[l..r] with a[r+1] >= a[l]...a[r]
		else {
			for (int i = r - 1; i > l-1; i--) {
				if (array[i].getKey() > array[i + 1].getKey()) {
					aux = array[i];
					j = i + 1;
					do {
						array[j - 1] = array[j];
						j++;
					} while (array[j].getKey() < aux.getKey());
					array[j - 1] = aux;
				}
			}
		}
	}

	/**
	 * The start method for the median of 3 quicksort with a build-in counter for key comparisons
	 * 
	 * @param array
	 *                The array which will be sorted
	 * @param l
	 *                The left index of the (sub) array which is being sorted
	 * @param r
	 *                The right index of the (sub) array which is being sorted
	 * @param keycount
	 *                The initializing value for the key comparison counter
	 * @return The number of key comparisons the algorithm needed
	 */
	public long runSortWithCounter(T[] array, int l, int r, long keycount) {
		if (r - l >= c) {
			int k = (l + r) / 2;
			Util.switchValues(array, k, l);
			// Pivot as median of 3
			if (array[l + 1].getKey() > array[r].getKey()) {
				Util.switchValues(array, l + 1, r);
			}
			if (array[l].getKey() > array[r].getKey()) {
				Util.switchValues(array, l, r);
			}
			if (array[l + 1].getKey() > array[l].getKey()) {
				Util.switchValues(array, l + 1, l);
			}
			keycount += 3;
			// Median of 3 sits in a[l] and a[l+1] <= a[l]as pivot
			int i = l + 1;
			int j = r;
			int v = array[l].getKey();
			while (true) {
				do {
					i++;
					keycount++;
				} while (array[i].getKey() >= v);
				do {
					j--;
					keycount++;
				} while (array[j].getKey() <= v);
				if (i > j) {
					break; // i is pivot position
				}
			}
			Util.switchValues(array, j, l);
			keycount = runSortWithCounter(array, l, j - 1, keycount);
		}
		// Insertionsort for a[l..r] with a[r+1] >= a[l]...a[r]
		else {
			for (int i = r - 1; i > 0; i--) {
				T aux = array[i];
				int j = i++;
				do {
					array[j - 1] = array[j];
					j++;
					keycount++;
				} while (array[j].getKey() >= aux.getKey());
				array[j - 1] = aux;
			}
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

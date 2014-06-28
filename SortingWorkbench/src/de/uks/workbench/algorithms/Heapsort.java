package de.uks.workbench.algorithms;

import de.uks.workbench.interfaces.IAlgorithm;
import de.uks.workbench.interfaces.ISortElement;
import de.uks.workbench.util.Util;

/**
 * 
 * Generic version of the LomutoQuicksort.
 * 
 * @param T
 *                the type of the values being sorted
 * 
 */
public class Heapsort<T extends ISortElement> implements IAlgorithm<T> {

	private static final boolean IS_STABLE = false;

	/**
	 * The start method for the heapsort
	 * 
	 * @param array
	 *                The array which will be sorted
	 * @param s
	 *                The left index of the array which is being sorted
	 * @param r
	 *                The right index of the array which is being sorted
	 */
	public void runSort(T[] array, int s, int r) {
		for (int i = r / 2; i > 0; i--) {
			sift(array, i, r);
		}
		for (int i = r; i > 1; i--) {
			Util.switchValues(array, i, 1);
			sift(array, 1, i - 1);
		}
	}

	private void sift(T[] array, int i, int r) {
		while (2 * i <= r) {
			int j = 2 * i;
			if (j < r) {
				if (array[j].getKey() < array[j + 1].getKey()) {
					j++;
				}
			}
			if (array[i].getKey() < array[j].getKey()) {
				Util.switchValues(array, i, j);
				i = j;
			} else {
				i = r;
			}
		}
	}

	/**
	 * The start method for the heapsort with a build-in counter for key comparisons
	 * 
	 * @param array
	 *                The array which will be sorted
	 * @param s
	 *                The left index of the array which is being sorted
	 * @param r
	 *                The right index of the array which is being sorted
	 */
	public long runSortWithCounter(T[] array, int s, int r, long keycount) {
		for (int i = r / 2; i > 0; i--) {
			keycount = sift(array, i, r, keycount);
		}
		for (int i = r; i > 1; i--) {
			Util.switchValues(array, i, 1);
			keycount = sift(array, 1, i-1, keycount);
		}
		return keycount;
	}

	private long sift(T[] array, int i, int r, long keycount) {
		while (2 * i <= r) {
			int j = 2 * i;
			if (j < r) {
				if (array[j].getKey() < array[j + 1].getKey()) {
					j++;
				}
				keycount++;
			}
			if (array[i].getKey() < array[j].getKey()) {
				Util.switchValues(array, i, j);
				i = j;
			} else {
				i = r;
			}
			keycount++;
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
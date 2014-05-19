package de.uks.workbench.algorithms;

import de.uks.workbench.interfaces.IAlgorithm;
import de.uks.workbench.interfaces.ISortElement;
import de.uks.workbench.util.Util;

/**
 * 
 * Generic version of the TurkuQuicksort.
 * 
 * @param <T>
 *                the type of the values being sorted
 */
public class TurkuQuicksort implements IAlgorithm {

	private static final boolean IS_STABLE = false;

	/**
	 * The start method for the recursive TurkuQuicksort
	 * 
	 * @param a
	 *                The array which will be sorted
	 * @param l
	 *                The left index of the sub array which is being sorted
	 * @param r
	 *                The right index of the sub array which is being sorted
	 * @return The number of key comparisons the algorithm needed
	 */
	public <T extends ISortElement> void runSort(T[] a, int l, int r) {
		int H;
		int rt, lt;

		if (l + 1 < r) {
			lt = l - 1;
			rt = r;
			H = a[r].getKey();

			while (lt <= rt) {
				do {
					lt++;
				} while (a[lt].getKey() < H);
				do {
					rt--;
				} while (a[rt].getKey() > H);
				Util.switchValues(a, lt, rt);
			}
			Util.switchValues(a, lt, rt);
			Util.switchValues(a, lt, r);
			if (l < rt) {
				runSort(a, l, rt);
			}
			if (lt + 1 < r) {
				runSort(a, lt + 1, r);
			}
		} else {
			if (a[l].getKey() > a[r].getKey()) {
				Util.switchValues(a, l, r);
			}
		}
	}

	/**
	 * The start method for the recursive TurkuQuicksort with a build-in counter for key comparisons
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
	public <T extends ISortElement> long runSortWithCounter(T[] a, int l, int r, long keycount) {
		int H;
		int rt, lt;

		if (l + 1 < r) {
			lt = l - 1;
			rt = r;
			H = a[r].getKey();

			while (lt <= rt) {
				do {
					lt++;
					keycount++;
				} while (a[lt].getKey() < H);

				do {
					rt--;
					keycount++;
				} while (a[rt].getKey() > H);

				Util.switchValues(a, lt, rt);
			}
			Util.switchValues(a, lt, rt);
			Util.switchValues(a, lt, r);
			if (l < rt) {
				keycount = runSortWithCounter(a, l, rt, keycount);
			}
			if (lt + 1 < r) {
				keycount = runSortWithCounter(a, lt + 1, r, keycount);
			}
		} else {
			if (a[l].getKey() > a[r].getKey()) {
				Util.switchValues(a, l, r);
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

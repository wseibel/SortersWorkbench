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
 */
public class LomutoQuicksort implements IAlgorithm {

	private static final boolean IS_STABLE = false;

	/**
	 * The start method for the recursive LomutoQuicksort
	 * 
	 * @param X
	 *                The array which will be sorted
	 * @param L
	 *                The left index of the sub array which is being sorted
	 * @param U
	 *                The right index of the sub array which is being sorted
	 */
	public <T extends ISortElement> void runSort(T[] X, int L, int U) {
		int T;
		int l, LastLow;

		if (L < U) {
			Util.switchValues(X, L, Util.algorithmRandomGen().nextInt((U + 1) - L) + L);
			T = X[L].getKey();
			LastLow = L;

			for (l = L + 1; l <= U; l++) {
				if (X[l].getKey() < T) {
					LastLow++;
					Util.switchValues(X, LastLow, l);
				}
			}
			Util.switchValues(X, L, LastLow);
			runSort(X, L, LastLow - 1);
			runSort(X, LastLow + 1, U);
		}
	}

	/**
	 * The start method for the recursive LomutoQuicksort with a build-in counter for key comparisons
	 * 
	 * @param X
	 *                The array which will be sorted
	 * @param L
	 *                The left index of the sub array which is being sorted
	 * @param U
	 *                The right index of the sub array which is being sorted
	 * @param keycount
	 *                The initializing value for the key comparison counter
	 * @return The number of key comparisons the algorithm needed
	 */
	public <T extends ISortElement> long runSortWithCounter(T[] X, int L, int U, long keycount) {
		int T;
		int l, LastLow;

		if (L < U) {
			Util.switchValues(X, L, Util.algorithmRandomGen().nextInt((U + 1) - L) + L);
			T = X[L].getKey();
			LastLow = L;

			for (l = L + 1; l <= U; l++) {
				if (X[l].getKey() < T) {
					LastLow++;
					Util.switchValues(X, LastLow, l);
				}
				keycount++;
			}
			Util.switchValues(X, L, LastLow);
			keycount = runSortWithCounter(X, L, LastLow - 1, keycount);
			keycount = runSortWithCounter(X, LastLow + 1, U, keycount);
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

package de.uks.workbench.algorithms;

import de.uks.workbench.interfaces.ISortValue;
import de.uks.workbench.util.Util;

/**
 * Generic version of the TurkuQuicksort.
 * 
 * @param <T>
 *                the type of the values being sorted
 */
public class TurkuQuicksort {

	public static <T extends ISortValue> void quicksort(T[] a, int l, int r) {
		int H;
		int rt, lt;

		if (l + 1 < r) {
			lt = l - 1;
			rt = r;
			H = a[r].getKey();

			while (lt <= rt) {
				while (lt<r && a[lt].getKey() < H) {
					lt++;
				}
				while (rt >l && a[rt].getKey() > H) {
					rt--;
				}
				Util.switchValues(a, lt, rt);
			}
			Util.switchValues(a, lt, rt);
			Util.switchValues(a, lt, r);
			if (l < rt) {
				quicksort(a, l, rt);
			}
			if (lt + 1 < r) {
				quicksort(a, lt + 1, r);
			}
		} else {
			if (a[l].getKey() > a[r].getKey()) {
				Util.switchValues(a, l, r);
			}
		}
	}
}

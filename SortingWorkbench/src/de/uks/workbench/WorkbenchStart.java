package de.uks.workbench;

import de.uks.workbench.algorithms.TurkuQuicksort;
import de.uks.workbench.elements.DefaultElement;
import de.uks.workbench.interfaces.PermutationType;
import de.uks.workbench.interfaces.Result;

/**
 * This class includes the starting point for the benchmark suite for sort algorithms and provides all settings
 * 
 */

public class WorkbenchStart {
	// Settings
	private static final int N = 111;
	private static final int M = N / 7;
	private static final int V = 75;
	private static final boolean CHECK_STABLE = false;
	private static final boolean SHOW_VALUES = false;

	private static final String TAG = PermutationType.NINETY_TEN_SEQUENCE.toString();

	/**
	 * The starting point for the workbench
	 * 
	 * @param <args>
	 *                Program arguments
	 */
	public static void main(String[] args) {
		// Create the inital array
		DefaultElement[] A = new DefaultElement[N + 1];
		for(int i = 0; i<N+1; i++){
			A[i] = new DefaultElement(-1, -1);
		}
		// Generate keys and info
		Workbench<DefaultElement> wb = new Workbench<DefaultElement>();
		wb.DataGen(A, N, M);
		System.out.println("Data generation: " + (A != null ? "done" : "failed"));
		// Permute the keys
		wb.DataPerm(A, V, TAG);
		System.out.println("Data permutation (" + TAG + "): " + (A != null ? "done" : "failed"));
		// Sort Data
		int left = 1, right = N;
		TurkuQuicksort.quicksort(A, left, right);
		// Show sort result
		Result result = wb.checkSort(A, CHECK_STABLE);
		System.out.println("\nSort result: " + result.toString() + "\n");
		if (SHOW_VALUES) {
			wb.showValues(A);
		}
	}

}

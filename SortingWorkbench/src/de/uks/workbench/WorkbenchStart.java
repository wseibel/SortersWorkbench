package de.uks.workbench;

import de.uks.workbench.algorithms.TurkuQuicksort;
import de.uks.workbench.interfaces.PermutationType;
import de.uks.workbench.interfaces.Result;
import de.uks.workbench.values.DefaultValue;

public class WorkbenchStart {
	private static final int N = 111;
	private static final int M = N / 7;
	private static final int V = 75;
	private static final boolean CHECK_STABLE = false;
	private static final boolean SHOW_VALUES = false;

	private static final String TAG = PermutationType.NINETY_TEN_SEQUENCE.toString();

	public static void main(String[] args) {
		// Generate Data
		Workbench wb = new Workbench();
		for (int i = 1; i < N; i++) {
			DefaultValue[] A = wb.DataGen(i, M);
			System.out.println("Data generation: " + (A != null ? "done." : "failed"));
			A = wb.DataPerm(A, V, TAG);
			System.out.println("Data permutation (" + TAG +"): " + (A != null ? "done" : "failed"));
			// Sort Data
			int left = 1, right = i;
			TurkuQuicksort.quicksort(A, left, right);
			// Show sort result
			Result result = wb.checkSort(A, CHECK_STABLE);
			System.out.println("\nSort result: " + result.toString() + "\n");
			if(SHOW_VALUES){
				wb.showValues(A);
			}
		}
	}

}

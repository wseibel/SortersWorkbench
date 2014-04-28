package de.uks.workbench;

import de.uks.workbench.algorithms.TurkuQuicksort;
import de.uks.workbench.values.DefaultValue;

public class WorkbenchStart {
	private static final int N = 100000;
	private static final int M = N / 5;
	private static final int V = 75;
	
	private static final String TAG = "default";

	public static void main(String[] args) {
		// Generate Data
		Workbench wb = new Workbench();
		DefaultValue[] A = wb.DataGen(N, M);
		A = wb.DataPerm(A, V, TAG);
		// Sort Data
		int left = 1, right = N;
		TurkuQuicksort.quicksort(A, left, right);
		// Show sort result
		System.out.println("The sort was executed " + (wb.checkSort(A) ? "successfully." : "unsuccessfully."));
	}

}

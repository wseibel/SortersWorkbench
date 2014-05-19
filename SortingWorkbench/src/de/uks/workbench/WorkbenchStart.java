package de.uks.workbench;

import de.uks.workbench.interfaces.AlgoType;
import de.uks.workbench.interfaces.MeasurementMethod;
import de.uks.workbench.interfaces.PermutationType;

/**
 * 
 * This class includes the starting point for the benchmark suite for sort algorithms and provides all settings
 * 
 */

public class WorkbenchStart {
	// Settings
	private static final AlgoType ALGO_TYPE = AlgoType.LOMUTO_QUICKSORT;
	private static final MeasurementMethod MEASUREMENT_METHOD = MeasurementMethod.TIME;
	private static final int ITERATIONS = 5;
	private static final int N = 100;
	private static final int M = 1;
	private static final int V = 0;
	private static final String TAG = PermutationType.DEFAULT.toString();
	
	private static final String COMMENT = "";
	private static final String FILE_NAME = "results" + ".csv";

	/**
	 * The starting point for the workbench
	 * 
	 * @param args
	 *                Program arguments
	 */
	public static void main(String[] args) {
		// Start the benchmark
		Workbench wb = new Workbench();
		wb.runSort(ALGO_TYPE, MEASUREMENT_METHOD, N, M, V, TAG, ITERATIONS, COMMENT, FILE_NAME);
	}

}

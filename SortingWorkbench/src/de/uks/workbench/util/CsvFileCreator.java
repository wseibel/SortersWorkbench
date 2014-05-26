package de.uks.workbench.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import de.uks.workbench.interfaces.AlgoType;
import de.uks.workbench.interfaces.MeasurementMethod;

/**
 * 
 * A class for writing results in to a csv file
 * 
 */
public class CsvFileCreator {

	private static final String SEPARATOR = ";";
	private static final String SINGLE_RESULT = "E";
	private static final String AVERAGE_FIGURE = "D";
	private static final String FILE_HEADER = "AlgoType;N;M;V;PermutationType;W;Comment;ED;Time;KeyComparisons";

	/**
	 * A static method for writing results into a csv file. The file will not be overwritten if it already exists but the information
	 * will be appended to the end of the file
	 * 
	 * @param type
	 *                Determines the algorithm which is used for sorting
	 * @param measurement
	 *                Determines the measurement method (time or key comparisons)
	 * @param N
	 *                Number of key values
	 * @param M
	 *                A factor for the number of repetitions for each key (n=N/M: n determines how many different keys will be created)
	 * @param V
	 *                Degree of pre-sorting (0 <= V <= 100). A sample of (100 - V)/100 * N keys will be permuted (is only used with
	 *                DEFAULT tag)
	 * @param tag
	 *                Describes the permutation pattern
	 * @param W
	 *                Number iterations the benchmark is executed
	 * @param comment
	 *                A freely usable text which can be used for benchmark identifying
	 * @param fileName
	 *                The name for the file in which the result will be written
	 * @return A boolean which shows whether the write operation was successfully (true = success)
	 */
	public static boolean saveToCsvFile(long[] results, AlgoType type, MeasurementMethod measurement, int N, int M, int V, String tag,
			int W, String comment, String fileName) {
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			// Write the header
			writer.println(FILE_HEADER);
			String values = type.toString() + SEPARATOR + N + SEPARATOR + M + SEPARATOR + V + SEPARATOR + tag + SEPARATOR + W
					+ SEPARATOR + comment;
			long sumResult = 0;
			// Write a new line for every result
			for (int i = 0; i < W; i++) {
				writer.println(values + SEPARATOR + SINGLE_RESULT + resultToString(results[i], measurement));
				sumResult += results[i];
			}
			// Write the average result value into file
			writer.println(values + SEPARATOR + AVERAGE_FIGURE + resultToString(sumResult / W, measurement));
			writer.println();

			// Close file
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Converts the numerical result into a string
	 * 
	 * @param result
	 *                The benchmark result to convert
	 * @param measurement
	 *                The measurement method (time / key comparisons)
	 * @return The result as a String
	 */
	private static String resultToString(long result, MeasurementMethod measurement) {
		String ret = "";
		if (measurement == MeasurementMethod.TIME) {
			double resultInSeconds = (double) result / 1000;
			ret = SEPARATOR + resultInSeconds + "sec" + SEPARATOR + " " + SEPARATOR;
		}
		if (measurement == MeasurementMethod.KEY_COMPARISONS) {
			ret = SEPARATOR + " " + SEPARATOR + result;
		}
		return ret;
	}
}

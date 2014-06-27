package de.uks.workbench;

import java.util.Arrays;
import java.util.HashMap;

import de.uks.workbench.algorithms.LinkSort;
import de.uks.workbench.algorithms.LomutoQuicksort;
import de.uks.workbench.algorithms.TriSort;
import de.uks.workbench.algorithms.TurkuQuicksort;
import de.uks.workbench.elements.DefaultElement;
import de.uks.workbench.handlers.DefaultTagHandler;
import de.uks.workbench.handlers.NinetyTenTagHandler;
import de.uks.workbench.handlers.RoofTagHandler;
import de.uks.workbench.handlers.SawtoothTagHandler;
import de.uks.workbench.handlers.TagHandler;
import de.uks.workbench.interfaces.AlgoType;
import de.uks.workbench.interfaces.IAlgorithm;
import de.uks.workbench.interfaces.IListAlgorithm;
import de.uks.workbench.interfaces.ISortElement;
import de.uks.workbench.interfaces.MeasurementMethod;
import de.uks.workbench.interfaces.Result;
import de.uks.workbench.interfaces.PermutationType;
import de.uks.workbench.list.ListNode;
import de.uks.workbench.list.ElementList;
import de.uks.workbench.util.CsvFileCreator;
import de.uks.workbench.util.Util;

/**
 * 
 * This class is supposed to prepare an array for benchmarking different sorting algorithms. It also checks the result after the sort
 * 
 */
public class Workbench {
	// HashMap for different handlers which handle the different permutation options
	HashMap<String, TagHandler> tagHandlers = new HashMap<String, TagHandler>();
	// HashMap for the different algorithms
	HashMap<AlgoType, IAlgorithm<ISortElement>> algorithms = new HashMap<AlgoType, IAlgorithm<ISortElement>>();
	// HashMap for the different list based algorithms
	HashMap<AlgoType, IListAlgorithm<DefaultElement>> listAlgorithms = new HashMap<AlgoType, IListAlgorithm<DefaultElement>>();

	public Workbench() {
		// Initialize the handler HashMap
		tagHandlers.put(PermutationType.DEFAULT.toString(), new DefaultTagHandler());
		tagHandlers.put(PermutationType.ROOF.toString(), new RoofTagHandler());
		tagHandlers.put(PermutationType.SAWTOOTH.toString(), new SawtoothTagHandler());
		tagHandlers.put(PermutationType.NINETY_TEN_SEQUENCE.toString(), new NinetyTenTagHandler());
		// Initialize the algorithm HashMap
		algorithms.put(AlgoType.LOMUTO_QUICKSORT, new LomutoQuicksort<ISortElement>());
		algorithms.put(AlgoType.TURKU_QUICKSORT, new TurkuQuicksort<ISortElement>());
		// Initialize the list based algorithm HashMap
		listAlgorithms.put(AlgoType.TRISORT, new TriSort<DefaultElement>());
		listAlgorithms.put(AlgoType.LINKSORT, new LinkSort<DefaultElement>());
	}

	/**
	 * Creates an array of values with keys according to the N and M parameter
	 * 
	 * @param N
	 *                The number of values to create. The array will have the size of N+1 because the first element is reserved for a
	 *                stopper value
	 * @param M
	 *                Determines the repetitions of a certain key
	 * @return The accordingly to N and M parameters generated Array
	 */
	public DefaultElement[] DataGen(int N, int M) {
		int n = 0;
		if (M <= N) {
			n = N / M;
		} else {
			n = N;
		}
		// Create an array with n different numbers and a stopper element at index 0
		DefaultElement[] array = new DefaultElement[N + 1];
		array[0] = new DefaultElement();
		for (int i = 1; i < N + 1; i++) {
			int key = ((i - 1) % n) + 1;
			array[i] = new DefaultElement(key);
		}
		Arrays.sort(array);
		// Set the position in the info variable
		for (int i = 1; i < N + 1; i++) {
			array[i].setInfo(i);
		}
		return array;
	}

	/**
	 * Permutes the elements in the given array in a certain pattern depending on the parameters
	 * 
	 * @param A
	 *                The array which supposed to be permuted
	 * @param V
	 *                Degree of pre-sorting (0 <= V <= 100). A sample of (100 - V)/100 * N keys will be permuted (is only used with
	 *                DEFAULT tag)
	 * @param Tag
	 *                Describes the pattern of permutation
	 * @return The accordingly to the tag parameter permuted array
	 */
	public DefaultElement[] DataPerm(DefaultElement A[], int V, String Tag) {
		return tagHandlers.get(Tag).permData(A, V);
	}

	/**
	 * The start method for the benchmark
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
	 */
	public void runSort(AlgoType type, MeasurementMethod measurement, int N, int M, int V, String tag, int W, String comment,
			String fileName) {
		// Reset the seed for the random generators
		Util.resetRandomGen();
		Util.resetAlgorithmRandomGen();
		long[] results = new long[W];
		// Repeat the benchmark W times with different values (since seed is not reseted)
		for (int i = 0; i < W; i++) {
			System.out.println("Iteration: " + (i + 1));
			// Generate keys and info
			DefaultElement[] array = DataGen(N, M);
			System.out.println("Data generation: " + (array != null ? "done" : "failed"));
			// Permute the keys
			DataPerm(array, V, tag);
			System.out.println("Data permutation (" + tag + "): " + (array != null ? "done" : "failed"));
			// Sort Data
			if (type == AlgoType.TRISORT || type == AlgoType.LINKSORT) {
				sortList(type, measurement, results, i, array);
			} else {
				sortArray(type, measurement, N, results, i, array);
			}
		}
		// Write the results to file
		System.out.print("Writing result to file: ");
		System.out.println(CsvFileCreator.saveToCsvFile(results, type, measurement, N, M, V, tag, W, comment, fileName) ? "done"
				: "failed");
	}

	private void sortArray(AlgoType type, MeasurementMethod measurement, int N, long[] results, int i, DefaultElement[] array) {
		int left = 1, right = N;
		if (measurement == MeasurementMethod.TIME) {
			IAlgorithm<ISortElement> algorithm = algorithms.get(type);
			long startTime = System.currentTimeMillis();
			algorithm.runSort(array, left, right);
			results[i] = System.currentTimeMillis() - startTime;
		}
		if (measurement == MeasurementMethod.KEY_COMPARISONS) {
			results[i] = algorithms.get(type).runSortWithCounter(array, left, right, 0);
		}
		// Check if the elements are in the correct order
		Result checkResult = checkSort(array, algorithms.get(type).getIsStable());
		System.out.println("Sort result: " + checkResult.toString() + "\n");
	}

	private void sortList(AlgoType type, MeasurementMethod measurement, long[] results, int i, DefaultElement[] array) {
		ElementList<DefaultElement> list = new ElementList<DefaultElement>();
		copyArray2List(array, list);
		if (measurement == MeasurementMethod.TIME) {
			IListAlgorithm<DefaultElement> algorithm = listAlgorithms.get(type);
			long startTime = System.currentTimeMillis();
			algorithm.runSort(list);
			results[i] = System.currentTimeMillis() - startTime;
		}
		if (measurement == MeasurementMethod.KEY_COMPARISONS) {
			results[i] = listAlgorithms.get(type).runSortWithCounter(list, 0);
		}
		copyList2Array(list, array);
		// Check if the elements are in the correct order
		Result checkResult = checkSort(array, listAlgorithms.get(type).getIsStable());
		System.out.println("Sort result: " + checkResult.toString() + "\n");
	}

	private void copyList2Array(ElementList<DefaultElement> list, DefaultElement[] array) {
		int i = 1;
		for (ListNode<DefaultElement> iter = list.first(); i < array.length && iter != null; i++, iter = iter.next) {
			array[i] = iter.element;
		}
	}

	private void copyArray2List(DefaultElement[] array, ElementList<DefaultElement> list) {
		for (int i = 1; i < array.length; i++) {
			ListNode<DefaultElement> newNode = new ListNode<DefaultElement>(array[i]);
			list.appendNode(newNode);
		}
	}

	/**
	 * Checks if the elements in the given array are in the right order (ascending). Therefore the key values are compared. The
	 * stability can be checked, also.
	 * 
	 * @param a
	 *                The array which should be checked
	 * @param checkStable
	 *                Determines if the stability should be checked, also
	 * @return A result which shows if the order is correct. If the checkStable parameter is true, the array is in the right order, and
	 *         the array is not stable, the result shows that
	 */
	public Result checkSort(DefaultElement[] a, boolean checkStable) {
		for (int i = 1; i < a.length - 1; i++) {
			if (a[i].getKey() > a[i + 1].getKey()) {
				return Result.WRONG_ORDER;
			}
			if (checkStable && a[i].getKey() == a[i + 1].getKey()) {
				if (a[i].getInfo() >= a[i + 1].getInfo()) {
					return Result.NOT_STABLE;
				}
			}
		}
		return Result.OK;
	}

	/**
	 * Shows all elements in the given array with their info and key value
	 * 
	 * @param A
	 *                The array to show
	 */
	public void showValues(DefaultElement[] A) {
		System.out.println("Values after sort: ");
		for (DefaultElement value : A) {
			System.out.println("key: " + value.getKey() + ", info: " + value.getInfo());
		}
	}
}

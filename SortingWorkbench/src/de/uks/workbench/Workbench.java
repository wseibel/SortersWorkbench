package de.uks.workbench;

import java.util.Arrays;
import java.util.HashMap;

import de.uks.workbench.elements.DefaultElement;
import de.uks.workbench.handlers.DefaultTagHandler;
import de.uks.workbench.handlers.NinetyTenTagHandler;
import de.uks.workbench.handlers.RoofTagHandler;
import de.uks.workbench.handlers.SawtoothTagHandler;
import de.uks.workbench.handlers.TagHandler;
import de.uks.workbench.interfaces.Result;
import de.uks.workbench.interfaces.PermutationType;

/**
 * 
 * This class is supposed to prepare an array for benchmarking different sort algorithms. It also checks the result after the sort
 * 
 */
public class Workbench {

	// HashMap for different handlers which handle the different permutation options
	HashMap<String, TagHandler<DefaultElement>> tagHandlers = new HashMap<String, TagHandler<DefaultElement>>();

	public Workbench() {
		// Initialize the handler HashMap
		tagHandlers.put(PermutationType.DEFAULT.toString(), new DefaultTagHandler<DefaultElement>());
		tagHandlers.put(PermutationType.ROOF.toString(), new RoofTagHandler<DefaultElement>());
		tagHandlers.put(PermutationType.SAWTOOTH.toString(), new SawtoothTagHandler<DefaultElement>());
		tagHandlers.put(PermutationType.NINETY_TEN_SEQUENCE.toString(), new NinetyTenTagHandler<DefaultElement>());
	}

	/**
	 * Creates an array of values with keys according to the N and M parameter
	 * 
	 * @param <N>
	 *                The number of values to create. The array will have the size of N+1 because the first element is reserved for a
	 *                stopper value
	 * @param <M>
	 *                Determines the repetitions of a certain key
	 * @return The accordingly to N and M parameters generated Array
	 */
	public DefaultElement[] DataGen(int N, int M) {
		DefaultElement[] a = new DefaultElement[N + 1];
		int n = 0;
		if (M <= N) {
			n = N / M;
		} else {
			n = N;
		}
		// Create a stopper at index 0
		a[0] = new DefaultElement(-1, 0);
		// Create an array with n different numbers
		for (int i = 1; i < N + 1; i++) {
			int key = ((i - 1) % n) + 1;
			a[i] = new DefaultElement(key);
		}
		Arrays.sort(a);
		// Set the position in the info variable
		for (int i = 1; i < N + 1; i++) {
			a[i].setInfo(i);
		}
		return a;
	}

	/**
	 * Permutes the elements in the given array in a certain pattern depending on the parameters
	 * 
	 * @param <A>The array which supposed to be permuted
	 * @param <V>
	 *                Degree of pre-sorting (0 <= V <= 100). A sample of (100 - V)/100 * N keys will be permuted (is only used with
	 *                DEFAULT tag)
	 * @param <Tag>
	 *                Describes the pattern of permutation
	 * @return The accordingly to the tag parameter permuted array
	 */
	public DefaultElement[] DataPerm(DefaultElement A[], int V, String Tag) {
		return tagHandlers.get(Tag).permData(A, V);
	}

	/**
	 * Checks if the elements in the given array are in the right order (ascending). Therefore the key values are compared. The
	 * stability can be checked, also.
	 * 
	 * @param <a>
	 *                The array which should be checked
	 * @param <checkStable>
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
	 * @param <A>
	 *                The array to show
	 */
	public void showValues(DefaultElement[] A) {
		System.out.println("\nValues after sort: ");
		for (DefaultElement value : A) {
			System.out.println("key: " + value.getKey() + ", info: " + value.getInfo());
		}
	}
}

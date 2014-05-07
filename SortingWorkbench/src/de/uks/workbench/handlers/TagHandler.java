package de.uks.workbench.handlers;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

import de.uks.workbench.interfaces.ISortElement;
import de.uks.workbench.util.Util;

/**
 * The abstract handler for the permutation options
 * 
 * @param <T>
 *                The type of elements which supposed to be permuted
 */
public abstract class TagHandler<T extends ISortElement> {

	public abstract T[] permData(T A[], int V);

	protected void permuteSample(T[] sample) {
		for (int i = 0; i < sample.length; i++) {
			int rand = Util.randomGen().nextInt(sample.length);
			Util.switchValueKeys(sample[i], sample[rand]);
		}
	}

	/**
	 * Provides random picked sample of elements from the given array.
	 * 
	 * @param <array>
	 *                The array the sample should get taken from
	 * @param <sampleSize>
	 *                The size of the sample
	 * @return An array which holds the random picked sample of elements
	 */
	@SuppressWarnings("unchecked")
	protected T[] getRandomSample(T[] array, int sampleSize) {
		T[] shuffledArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length - 1);
		T[] sample = (T[]) Array.newInstance(array.getClass().getComponentType(), sampleSize);
		for (int i = 0; i < array.length - 1; i++) {
			shuffledArray[i] = array[i + 1];
		}
		shuffleValues(shuffledArray);
		for (int i = 0; i < sampleSize; i++) {
			sample[i] = shuffledArray[i];
		}
		return sample;
	}

	private void shuffleValues(T[] array) {
		// Shuffle array
		for (int i = array.length; i > 1; i--) {
			Util.switchValues(array, i - 1, Util.randomGen().nextInt(i));
		}
	}

	/**
	 * Sorts the given (part) array descending according to the key values of the elements
	 * 
	 * @param <array>
	 *                The array in which the elements supposed to be sorted descending
	 * @param <fromIndex>
	 *                The start index (inclusively)
	 * @param <toIndex>
	 *                The end index (exclusively)
	 */
	protected void sortArrayDescending(T[] array, int fromIndex, int toIndex) {
		Arrays.sort(array, fromIndex, toIndex, new Comparator<T>() {

			@Override
			public int compare(T arg0, T arg1) {
				if (arg1.getKey() < arg0.getKey()) {
					return -1;
				}
				if (arg1.getKey() > arg0.getKey()) {
					return 1;
				}
				return 0;
			}

		});
	}
}

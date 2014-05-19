package de.uks.workbench.handlers;

import java.util.Arrays;
import java.util.Comparator;

import de.uks.workbench.elements.DefaultElement;
import de.uks.workbench.util.Util;

/**
 * 
 * The abstract handler for the permutation options
 * 
 */
public abstract class TagHandler {

	public abstract DefaultElement[] permData(DefaultElement A[], int V);

	protected void permuteSample(DefaultElement[] sample) {
		for (int i = 0; i < sample.length; i++) {
			int rand = Util.randomGen().nextInt(sample.length);
			Util.switchValueKeys(sample[i], sample[rand]);
		}
	}

	/**
	 * Provides random picked sample of elements from the given array
	 * 
	 * @param array
	 *                The array the sample should get taken from
	 * @param sampleSize
	 *                The size of the sample
	 * @return An array which holds the random picked sample of elements
	 */
	protected DefaultElement[] getRandomSample(DefaultElement[] array, int sampleSize) {
		DefaultElement[] shuffledArray = new DefaultElement[array.length - 1];
		DefaultElement[] sample = new DefaultElement[sampleSize];
		for (int i = 0; i < array.length - 1; i++) {
			shuffledArray[i] = array[i + 1];
		}
		shuffleValues(shuffledArray);
		for (int i = 0; i < sampleSize; i++) {
			sample[i] = shuffledArray[i];
		}
		return sample;
	}

	private void shuffleValues(DefaultElement[] array) {
		// Shuffle array
		for (int i = array.length; i > 1; i--) {
			Util.switchValues(array, i - 1, Util.randomGen().nextInt(i));
		}
	}

	/**
	 * Sorts the given (part) array descending according to the key values of the elements
	 * 
	 * @param array
	 *                The array in which the elements supposed to be sorted descending
	 * @param fromIndex
	 *                The start index (inclusively)
	 * @param toIndex
	 *                The end index (exclusively)
	 */
	protected void sortArrayDescending(DefaultElement[] array, int fromIndex, int toIndex) {
		Arrays.sort(array, fromIndex, toIndex, new Comparator<DefaultElement>() {

			@Override
			public int compare(DefaultElement arg0, DefaultElement arg1) {
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

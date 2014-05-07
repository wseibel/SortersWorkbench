package de.uks.workbench.util;

import java.util.Random;

import de.uks.workbench.interfaces.ISortElement;

/**
 * This class provides some often used utilities
 * 
 */
public class Util {

	public static final int DEFAULT_SEED = 17;

	private static Random randomGen = new Random(DEFAULT_SEED);

	/**
	 * Swaps two elements in the given array
	 * 
	 * @param <array>
	 *                The array in which the elements supposed to be swapped
	 * @param <x>
	 *                The position of the first element to swap
	 * @param <y>
	 *                The position of the second element to swap
	 */
	public static <T extends ISortElement> void switchValues(T[] array, int x, int y) {
		T temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

	/**
	 * Swaps the keys of two elements in the given array without changing the info value of the element
	 * 
	 * @param <x>The position of the first element to swap the keys
	 * @param <y>
	 *                The position of the first element to swap the keys
	 */
	public static <T extends ISortElement> void switchValueKeys(T x, T y) {
		int tempKey = x.getKey();
		x.setKey(y.getKey());
		y.setKey(tempKey);
	}

	/**
	 * @return The static random number generator
	 */
	public static Random randomGen() {
		return randomGen;
	}

	/**
	 * Re-initializes the random number generator with the given seed parameter value
	 * 
	 * @param <seed>
	 *                The seed value for the random number generator
	 */
	public static void resetRandomGen(int seed) {
		randomGen = new Random(seed);
	}

}

package de.uks.workbench.algorithms;

import de.uks.workbench.interfaces.IAlgorithm;
import de.uks.workbench.interfaces.ISortElement;
import de.uks.workbench.util.Util;

/**
 * 
 * Generic version of the LomutoQuicksort.
 * 
 * @param T
 *                the type of the values being sorted
 * 
 */
public class FHeapsort<T extends ISortElement> implements IAlgorithm<T> {

	private static final boolean IS_STABLE = false;

	/**
	 * The start method for the heapsort with Floyd's improvement
	 * 
	 * @param array
	 *                The array which will be sorted
	 * @param s
	 *                The left index of the array which is being sorted
	 * @param n
	 *                The right index of the array which is being sorted
	 */
	public void runSort(T[] array, int s, int n) {
		for (int i = n / 2; i > 1; i--) {
			siftUp(array, i, n);
		}
		for (int i = n; i > 1; i--) {
			siftUp(array, 1, i);
			Util.switchValues(array, i, 1);
		}
	}

	private void siftUp(T[] array, int i, int n) {
		int start = i;
		T x = array[i];
		int j = i * 2;
		while(j <= n){
			if(j < n){
				if(array[j].getKey() < array[j+1].getKey()){
					j++;
				}
			}
			array[i] = array[j];
			i = j;
			j = i*2;
		}
		j = i /2;
		while(j >= start){
			if(array[j].getKey() < x.getKey()){
				array[i] = array[j];
				i = j;
				j = i /2;
			}else {
				break;
			}
		}
		array[i] = x;
	}

	/**
	 * The start method for the heapsort with Floyd's improvement and a build-in counter for key comparisons
	 * 
	 * @param array
	 *                The array which will be sorted
	 * @param s
	 *                The left index of the array which is being sorted
	 * @param n
	 *                The right index of the array which is being sorted
	 * @param keycount
	 *                The initializing value for the key comparison counter
	 * @return The number of key comparisons the algorithm needed
	 */
	public long runSortWithCounter(T[] array, int s, int n, long keycount) {
		for (int i = n / 2; i > 1; i--) {
			keycount = siftUp(array, i, n, keycount);
		}
		for (int i = n; i > 1; i--) {
			keycount = siftUp(array, 1, i, keycount);
			Util.switchValues(array, i, 1);
		}
		return keycount;
	}

	private long siftUp(T[] array, int i, int n, long keycount) {
		int start = i;
		T x = array[i];
		int j = i * 2;
		while(j <= n){
			if(j < n){
				if(array[j].getKey() < array[j+1].getKey()){
					j++;
				}
				keycount++;
			}
			array[i] = array[j];
			i = j;
			j = i*2;
		}
		j = i /2;
		while(j >= start){
			if(array[j].getKey() < x.getKey()){
				array[i] = array[j];
				i = j;
				j = i /2;
				keycount++;
			}else {
				keycount++;
				break;
			}
		}
		array[i] = x;
		return keycount;
	}

	/**
	 * @return the boolean which indicates if the algorithm is stable
	 */
	public boolean getIsStable() {
		return IS_STABLE;
	}
}
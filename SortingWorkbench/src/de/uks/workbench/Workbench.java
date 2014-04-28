package de.uks.workbench;

import java.util.Arrays;

import de.uks.workbench.util.Util;
import de.uks.workbench.values.DefaultValue;

public class Workbench {

	final int MAX_KEY_VALUE = 10;

	public DefaultValue[] DataGen(int N, int M) {
		DefaultValue[] a = new DefaultValue[N + 1];
		int n = N / M;
		// Create a stopper at index 0
		a[0] = new DefaultValue(-1, 0);
		// Create an array with n different numbers
		for (int i = 1; i < N + 1; i++) {
			int key = ((i - 1) % n) + 1;
			a[i] = new DefaultValue(key);
		}
		Arrays.sort(a);
		// Set the position in the info variable
		for (int i = 1; i < N + 1; i++) {
			a[i].setInfo(i);
		}
		return a;
	}

	public DefaultValue[] DataPerm(DefaultValue A[], int V, String Tag) {
		int N = A.length - 1;
		double temp = (double) (100 - V) / 100 * (double) N;
		int sampleSize = (int) temp;
		// Pick a sample of elements randomly
		DefaultValue[] sample = getRandomSample(A, sampleSize);
		// Permute the sample of elements randomly
		permuteSample(sample);
		for(int i = 0; i < sampleSize; i++){
			A[sample[i].getInfo()] = sample[i];
		}
		return A;
	}

	private void permuteSample(DefaultValue[] sample) {
		for (int i = 0; i < sample.length; i++) {
			int rand = Util.randomGen().nextInt(sample.length);
			Util.switchValueKeys(sample[i], sample[rand]);
		}
	}

	private DefaultValue[] getRandomSample(DefaultValue[] array, int sampleSize) {
		DefaultValue[] shuffledArray = new DefaultValue[array.length];
		DefaultValue[] sample = new DefaultValue[sampleSize];
		for (int i = 0; i < array.length - 1; i++) {
			shuffledArray[i] = array[i + 1];
		}
		shuffleValues(shuffledArray);
		for (int i = 0; i < sampleSize; i++) {
			sample[i] = shuffledArray[i];
		}
		return sample;
	}

	private void shuffleValues(DefaultValue[] array) {
		// Shuffle array
		for (int i = array.length; i > 1; i--) {
			Util.switchValues(array, i - 1, Util.randomGen().nextInt(i));
		}
	}

	public boolean checkSort(DefaultValue[] a) {
		for(int i = 1; i<a.length - 1; i++){
			if(a[i].getKey()>a[i+1].getKey()){
				return false;
			}
		}
		return true;
	}
}

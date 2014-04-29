package de.uks.workbench.handlers;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

import de.uks.workbench.interfaces.ISortValue;
import de.uks.workbench.util.Util;

public abstract class TagHandler<T extends ISortValue> {
	
	public abstract T[] permData(T A[], int V);

	protected void permuteSample(T[] sample) {
		for (int i = 0; i < sample.length; i++) {
			int rand = Util.randomGen().nextInt(sample.length);
			Util.switchValueKeys(sample[i], sample[rand]);
		}
	}

	@SuppressWarnings("unchecked")
	protected T[] getRandomSample(T[] array, int sampleSize) {
		T[] shuffledArray = (T[]) Array.newInstance(array.getClass().getComponentType(),
				array.length - 1);
		T[] sample = (T[]) Array.newInstance(array.getClass().getComponentType(),
				sampleSize);
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

	protected void sortArrayDescending(T[] array, int fromIndex, int toIndex) {
		Arrays.sort(array, fromIndex, toIndex, new Comparator<T>(){

			@Override
			public int compare(T arg0, T arg1) {
				if(arg1.getKey() < arg0.getKey()){
					return -1;
				} 
				if(arg1.getKey() > arg0.getKey()){
					return 1;
				}
				return 0;
			}
			
		});
	}
}

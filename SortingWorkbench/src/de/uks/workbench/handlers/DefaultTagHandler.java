package de.uks.workbench.handlers;

import de.uks.workbench.interfaces.ISortElement;

public class DefaultTagHandler<T extends ISortElement> extends TagHandler<T> {
	
	public T[] permData(T A[], int V) {
		int N = A.length - 1;
		double temp = (double) (100 - V) / 100 * (double) N;
		int sampleSize = (int) temp;
		// Pick a sample of elements randomly
		T[] sample = getRandomSample(A, sampleSize);
		// Permute the sample of elements randomly
		permuteSample(sample);
		for (int i = 0; i < sampleSize; i++) {
			A[sample[i].getInfo()] = sample[i];
		}
		return A;
	}

}

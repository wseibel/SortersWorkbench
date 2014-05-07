package de.uks.workbench.handlers;

import de.uks.workbench.interfaces.ISortElement;

/**
 * The handler class for the permutation option "DEFAULT". Picks a sample of elements and permutes them randomly
 * 
 * @param <T>
 *                The type of elements which supposed to be permuted
 * 
 * @see de.uks.workbench.interfaces.PermutationType PermutationTypes
 */
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

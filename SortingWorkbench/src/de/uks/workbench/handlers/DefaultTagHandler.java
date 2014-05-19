package de.uks.workbench.handlers;

import de.uks.workbench.elements.DefaultElement;

/**
 * 
 * The handler class for the permutation option "DEFAULT". Picks a sample of elements and permutes them randomly
 *  
 * @see de.uks.workbench.interfaces.PermutationType PermutationTypes
 */
public class DefaultTagHandler extends TagHandler {

	public DefaultElement[] permData(DefaultElement A[], int V) {
		int N = A.length - 1;
		double temp = (double) (100 - V) / 100 * (double) N;
		int sampleSize = (int) temp;
		// Pick a sample of elements randomly
		DefaultElement[] sample = getRandomSample(A, sampleSize);
		// Permute the sample of elements randomly
		permuteSample(sample);
		for (int i = 0; i < sampleSize; i++) {
			A[sample[i].getInfo()] = sample[i];
		}
		return A;
	}

}

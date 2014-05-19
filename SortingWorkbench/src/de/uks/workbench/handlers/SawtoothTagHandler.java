package de.uks.workbench.handlers;

import de.uks.workbench.elements.DefaultElement;

/**
 * 
 * The handler class for the permutation option "SAWTOOTH". Divides the array in two halves and orders them separately
 *  
 * @see de.uks.workbench.interfaces.PermutationType PermutationTypes
 */
public class SawtoothTagHandler extends TagHandler {

	public DefaultElement[] permData(DefaultElement A[], int V) {
		int N = A.length - 1;
		int half = N / 2;
		int remainder = N % 2;
		DefaultElement[] firstPart = new DefaultElement[half + remainder];
		DefaultElement[] secondPart =new DefaultElement[half];

		// Divide the array in two parts with similar distribution of keys
		for (int i = 0, j = 0, k = 0; i < N; i++) {
			if (i % 2 == 0) {
				firstPart[j++] = A[i + 1];
			} else {
				secondPart[k++] = A[i + 1];
			}
		}

		// Put the two parts back together and adjust the position info
		for (int i = 1; i < N + 1; i++) {
			if (i <= half + remainder) {
				A[i] = firstPart[i - 1];
			} else {
				A[i] = secondPart[(i - 1) - (half + remainder)];
			}
			A[i].setInfo(i);
		}

		return A;
	}
}

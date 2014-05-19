package de.uks.workbench.handlers;

import de.uks.workbench.elements.DefaultElement;

/**
 * 
 * The handler class for the permutation option "Roof". Divides the array in two halves and orders the first half ascending and the second
 * descending
 *  
 * @see de.uks.workbench.interfaces.PermutationType PermutationTypes
 */
public class RoofTagHandler extends TagHandler {

	public DefaultElement[] permData(DefaultElement A[], int V) {
		int N = A.length - 1;
		int half = N / 2;
		int remainder = N % 2;
		DefaultElement[] firstPart = new DefaultElement[half + remainder];
		DefaultElement[] secondPart = new DefaultElement[half];

		for (int i = 0, j = 0, k = 0; i < N; i++) {
			if (i % 2 == 0) {
				firstPart[j++] = A[i + 1];
			} else {
				secondPart[k++] = A[i + 1];
			}
		}

		// Sort half of the array by descending order
		sortArrayDescending(secondPart, 0, secondPart.length);
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

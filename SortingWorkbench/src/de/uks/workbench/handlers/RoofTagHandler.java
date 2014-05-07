package de.uks.workbench.handlers;

import java.lang.reflect.Array;
import de.uks.workbench.interfaces.ISortElement;

/**
 * The handler class for the permutation option "Roof". Divides the array in two halves and orders the first half ascending and the second
 * descending
 * 
 * @param <T>
 *                The type of elements which supposed to be permuted
 */
public class RoofTagHandler<T extends ISortElement> extends TagHandler<T> {

	@SuppressWarnings("unchecked")
	public T[] permData(T A[], int V) {
		int N = A.length - 1;
		int half = N / 2;
		int remainder = N % 2;
		T[] firstPart = (T[]) Array.newInstance(A.getClass().getComponentType(), half + remainder);
		T[] secondPart = (T[]) Array.newInstance(A.getClass().getComponentType(), half);

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

package de.uks.workbench.handlers;

import java.lang.reflect.Array;

import de.uks.workbench.interfaces.ISortValue;

public class NinetyTenTagHandler<T extends ISortValue> extends TagHandler<T> {

	@SuppressWarnings("unchecked")
	public T[] permData(T A[], int V) {
		int N = A.length - 1;
		double ninetyPercent = ((double) N / 100) * 90;
		double tenPercent = ((double) N / 100) * 10;
		int remainder = 1;
		T[] firstPart = (T[]) Array.newInstance(A.getClass().getComponentType(), (int) ninetyPercent + remainder);
		T[] secondPart = (T[]) Array.newInstance(A.getClass().getComponentType(), (int) tenPercent);
		// Put 10% randomly picked values in a new array
		secondPart = getRandomSample(A, secondPart.length);
		permuteSample(secondPart);
		boolean exists = false;
		// Put every other value in another array
		for (int i = 1, j = 0; i < A.length; i++, exists = false) {
			for (T value : secondPart) {
				if (value.getInfo() == A[i].getInfo()) {
					exists = true;
				}
			}
			if (!exists) {
				firstPart[j++] = A[i];
			}
		}
		// Put the values of the 10% array behind the 90% array back in the original array
		for (int i = 1, j = 0, k = 0; i < N + 1; i++) {
			if ((i - 1) < firstPart.length) {
				A[i] = firstPart[j++];
			} else {
				A[i] = secondPart[k++];
			}
			A[i].setInfo(i);
		}

		return A;
	}
}

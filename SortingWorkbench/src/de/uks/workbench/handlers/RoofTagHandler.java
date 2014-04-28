package de.uks.workbench.handlers;

import java.util.Arrays;
import java.util.Comparator;

import de.uks.workbench.interfaces.ISortValue;

public class RoofTagHandler<T extends ISortValue> extends TagHandler<T> {

	public T[] permData(T A[], int V) {
		int N = A.length - 1;
		int middle = N / 2;

		// Sort half of the array by descending order
		Arrays.sort(A, middle, A.length, new Comparator<T>(){

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
		// Adjust the position info
		for (int i = 1; i < N+1; i++) {
			A[i].setInfo(i);
		}
		
		return A;
	}

}

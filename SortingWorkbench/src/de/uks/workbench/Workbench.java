package de.uks.workbench;

import java.util.Arrays;
import java.util.HashMap;

import de.uks.workbench.handlers.DefaultTagHandler;
import de.uks.workbench.handlers.RoofTagHandler;
import de.uks.workbench.handlers.TagHandler;
import de.uks.workbench.util.Util;
import de.uks.workbench.values.DefaultValue;

public class Workbench {

	final int MAX_KEY_VALUE = 10;

	HashMap<String, TagHandler<DefaultValue>> tagHandlers = new HashMap<String, TagHandler<DefaultValue>>();

	public Workbench(){
		
		tagHandlers.put("default", new DefaultTagHandler<DefaultValue>());
		tagHandlers.put("roof", new RoofTagHandler<DefaultValue>());
	}

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
		return tagHandlers.get(Tag).permData(A, V);
	}

	public boolean checkSort(DefaultValue[] a) {
		for (int i = 1; i < a.length - 1; i++) {
			if (a[i].getKey() > a[i + 1].getKey()) {
				return false;
			}
		}
		return true;
	}
}

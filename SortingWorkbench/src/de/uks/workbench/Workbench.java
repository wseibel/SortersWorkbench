package de.uks.workbench;

import java.util.Arrays;
import java.util.HashMap;

import de.uks.workbench.handlers.DefaultTagHandler;
import de.uks.workbench.handlers.NinetyTenTagHandler;
import de.uks.workbench.handlers.RoofTagHandler;
import de.uks.workbench.handlers.SawToothTagHandler;
import de.uks.workbench.handlers.TagHandler;
import de.uks.workbench.interfaces.Result;
import de.uks.workbench.interfaces.PermutationType;
import de.uks.workbench.values.DefaultValue;

public class Workbench {

	final int MAX_KEY_VALUE = 10;

	HashMap<String, TagHandler<DefaultValue>> tagHandlers = new HashMap<String, TagHandler<DefaultValue>>();

	public Workbench() {

		tagHandlers.put(PermutationType.DEFAULT.toString(), new DefaultTagHandler<DefaultValue>());
		tagHandlers.put(PermutationType.ROOF.toString(), new RoofTagHandler<DefaultValue>());
		tagHandlers.put(PermutationType.SAWTOOTH.toString(), new SawToothTagHandler<DefaultValue>());
		tagHandlers.put(PermutationType.NINETY_TEN_SEQUENCE.toString(), new NinetyTenTagHandler<DefaultValue>());
	}

	public DefaultValue[] DataGen(int N, int M) {
		DefaultValue[] a = new DefaultValue[N + 1];
		int n = 0;
		if(M <= N){
			n = N / M;
		}else {
			n = N;
		}
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

	public Result checkSort(DefaultValue[] a, boolean checkStable) {
		for (int i = 1; i < a.length - 1; i++) {
			if (a[i].getKey() > a[i + 1].getKey()) {
				return Result.WRONG_ORDER;
			}
			if (checkStable && a[i].getInfo() >= a[i + 1].getInfo()) {
				return Result.NOT_STABLE;
			}
		}
		return Result.OK;
	}

	public void showValues(DefaultValue[] A) {
		System.out.println("\nValues after sort: ");
		for (DefaultValue value : A) {
			System.out.println("key: " + value.getKey() + ", info: " + value.getInfo());
		}
	}
}

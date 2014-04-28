package de.uks.workbench.util;

import java.util.Random;

import de.uks.workbench.interfaces.ISortValue;

public class Util {
	
	public static final int DEFAULT_SEED = 17;

	private static Random randomGen = new Random(DEFAULT_SEED);

	public static <T extends ISortValue> void switchValues(T[] array, int x, int y) {
		T temp =array[ x];
		array[x] = array[y];
		array[y] = temp;
	}

	public static <T extends ISortValue> void switchValueKeys(T x, T y) {
		int tempKey = x.getKey();
		x.setKey(y.getKey());
		y.setKey(tempKey);
	}

	public static Random randomGen() {
		return randomGen;
	}
	
	public static void resetRandomGen(int seed){
		randomGen = new Random(seed);
	}

}

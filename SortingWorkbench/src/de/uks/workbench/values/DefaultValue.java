package de.uks.workbench.values;

import de.uks.workbench.interfaces.ISortValue;

public class DefaultValue implements ISortValue, Comparable<DefaultValue> {
	
	private int key;
	private int info;
	
	public DefaultValue(int key, int info){
		this.key = key;
		this.info = info;
	}
	
	public DefaultValue(int key){
		this(key, 0);
	}

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public void setKey(int key) {
		this.key = key;		
	}

	@Override
	public int getInfo() {
		return info;
	}

	@Override
	public void setInfo(int info) {
		this.info = info;		
	}

	@Override
	public int compareTo(DefaultValue o) {
		if(this.key < o.getKey()){
			return -1;
		} 
		if(this.key > o.getKey()){
			return 1;
		}
		return 0;
	}
}

package de.uks.workbench.elements;

import de.uks.workbench.interfaces.ISortElement;

/**
 * The default element type
 *
 */
public class DefaultElement implements ISortElement, Comparable<DefaultElement> {

private static final int DEFAULT_KEY = -1;
private static final int DEFAULT_INFO = -1;
	
	private int key;
	private int info;
	
	public DefaultElement(int key, int info){
		this.key = key;
		this.info = info;
	}
	
	public DefaultElement(int key){
		this(key, DEFAULT_INFO);
	}
	
	public DefaultElement(){
		this(DEFAULT_KEY, DEFAULT_INFO);
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
	public int compareTo(DefaultElement o) {
		if(this.key < o.getKey()){
			return -1;
		} 
		if(this.key > o.getKey()){
			return 1;
		}
		return 0;
	}
}

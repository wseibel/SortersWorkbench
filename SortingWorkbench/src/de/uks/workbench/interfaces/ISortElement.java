package de.uks.workbench.interfaces;

/**
 * 
 * This interface has to be implemented for every kind of elements. The elements are used for lists and arrays.
 * 
 * @see de.uks.workbench.elements ElementTypes
 * 
 */
public interface ISortElement {
	public int getKey();

	public void setKey(int key);

	public int getInfo();

	public void setInfo(int info);
}

package server;

import java.io.Serializable;

public class Widget implements Serializable {
	private static final long serialVersionUID = -4298245903822614337L;
	private int minVal;
	private int maxVal;
	private int val;
	private int id;
	private String name;
	
	public Widget(int minVal, int maxVal, int val, int id, String name){
		this.setMinVal(minVal);
		this.setMaxVal(maxVal);
		this.setVal(val);
		this.setId(id);
		this.setName(name);
	}

	public int getMinVal() {
		return minVal;
	}

	public void setMinVal(int minVal) {
		this.minVal = minVal;
	}

	public int getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(int maxVal) {
		this.maxVal = maxVal;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

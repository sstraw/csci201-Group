package server;

import java.io.Serializable;

public abstract class Widget implements Serializable {
	private static final long serialVersionUID = -4298245903822614337L;
	private int val;
	private int id;
	private String name;
	
	public Widget(int val, int id, String name){
		this.setVal(val);
		this.setId(id);
		this.setName(name);
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
	
	public boolean equals(Widget other){
		return (this.name == other.getName() && this.val == other.getVal());
	}
	
	public abstract Widget getRandomInstruction();
	
	public abstract String getInstructionString();
	
}

package client;

import java.io.Serializable;
import java.util.Vector;

public abstract class Widget implements Serializable {
	private static final long serialVersionUID = -4298245903822614337L;
	private Vector<Integer> val;
	private String name;
	
	public Widget(String name){
		this.setName(name);
		this.val = new Vector<Integer>();
	}


	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public Vector<Integer> getValues(){
		return val;
	}
	
	public boolean equals(Widget other){
		return (this.name == other.getName() && this.val.equals(other.getValues()));
	}
	
	public abstract Widget getRandomInstruction();
	
	public abstract String getInstructionString();
	
}

package client;

import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

public interface Widget extends Serializable{
	
	//This instruction needs to return a new instance of the widget that can be used
	//to generate a new instruction.
	public Widget getRandomInstruction();
	
	//Returns a string that is an instruction for how to change this widget, assuming this was
	//generated by the previous function
	public String getInstructionString();
	
	//Compares another widget to check if it is the same one.  This is used to check if the instruction matches the
	//changed widget
	public boolean equals(Object other);
	
	//Updates the widget to a new value given the current one
	public void update(Widget w);
	
	//Gets the name of the widget
	public String getName();
	
}

abstract class SingleButtonPress implements Widget{
	private static final long serialVersionUID = 251334623591367419L;
	protected String name;
	protected int buttons;
	protected int val;
	
	public SingleButtonPress(String name, int buttons, int val){
		this.name = name;
		this.buttons = buttons;
		this.val = val;
	}
	
	public String getName(){
		return name;
	}
	
	public int getVal(){
		return this.val;
	}
	
	public void setVal(int i){
		val = i;
	}
	
	public boolean equals(Object other){
		if (other instanceof SingleButtonPress){
			if (((SingleButtonPress) other).getVal() == this.val){
				if (((SingleButtonPress) other).getName() == this.name){
					return true;
				}
			}
		}
		return false;
	}
	
	public void update(Widget w){
		//This function doesn't matter because the button press means we don't need to store the value
	}
	
}

abstract class SingleValueWidget implements Widget{
	//Base class for any widget which only have one value out of a collection
	private static final long serialVersionUID = -1921593644005787609L;
	protected String name;
	protected int val;
	protected int min;
	protected int max;
	
	public SingleValueWidget(String name, int min, int max, int init_val){
		this.name = name;
		this.val = init_val;
		this.min = min;
		this.max = max;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getVal(){
		return this.val;
	}
	
	public void setVal(int i){
		val = i;
	}
	
	public void update(Widget w){
		if (w instanceof SingleValueWidget){
			SingleValueWidget widget = (SingleValueWidget) w;
			this.val = widget.getVal();
		}
	}
	
	public boolean equals(Object other){
		if (other instanceof SingleValueWidget){
			SingleValueWidget w = (SingleValueWidget) other;
			if (w.getName().equals(this.getName())){
				if (w.getVal() == this.getVal()){
					return true;
				}
			}
		}
		return false;
	}
	
}

class Slider extends SingleValueWidget{
	private static final long serialVersionUID = -2296919106897840561L;
	public Slider(String name, int min, int max, int init_val) {
		super(name, min, max, init_val);
	}

	public Widget getRandomInstruction() {
		Random r = new Random();
		int random_init = r.nextInt(max - min) + min;
		while(random_init == val){
			random_init = r.nextInt(max - min) + min;
		}
		return new Slider(this.name, this.min, this.max, random_init);
	}
	
	public void setVal(int i){
		val = i;
	}
	public String getInstructionString() {
		return String.format("Set %s to %d", this.name, this.val);
	}
	
}

//Anything where a button is pressed
class AnyButton extends SingleButtonPress{
	private static final long serialVersionUID = 6626303430460051073L;
	protected Vector<String> printoutValues;
	public AnyButton(String name, int buttons, int val, Vector<String> printoutValues) {
		super(name, buttons, val);
	}
	public Widget getRandomInstruction() {
		return new AnyButton(name, buttons, (new Random()).nextInt(buttons), printoutValues);
	}
	public String getInstructionString() {
		return printoutValues.get(val);
	}
	
}

//Any button press where the new random instruction cannot be the same button as the current value. Also
//implements the Update to actually change it.
class AnyButtonStored extends AnyButton{
	private static final long serialVersionUID = 6164023689502358712L;
	public AnyButtonStored(String name, int buttons, int val,
			Vector<String> printoutValues) {
		super(name, buttons, val, printoutValues);
	}
	public Widget getRandomInstruction(){
		Random r = new Random();
		int i = r.nextInt(buttons);
		while (i == val){
			i = r.nextInt(buttons);
		}
		return new AnyButton(name, buttons, i, printoutValues);
	}
	public void update(Widget w){
		if (w instanceof AnyButtonStored){
			val = ((AnyButtonStored) w).getVal();
		}
	}
}

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

//Dashboard1_1
class TransmissionWidget extends SingleValueWidget{
	public TransmissionWidget(int init_val) {
		super("Transmission", 0, 2, init_val);
	}

	@Override
	public Widget getRandomInstruction() {
		if (val == 0){
			return new TransmissionWidget(1);
		}
		else{
			return new TransmissionWidget(0);
		}
	}

	@Override
	public String getInstructionString() {
		if (val == 0){
			return "Set Transmission to Automatic";
		}
		else{
			return "Set Transmission to Manual";
		}
	}
	
}

class CrowleyCoke extends SingleButtonPress{
	public CrowleyCoke() {
		super("Crowley Coke", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new CrowleyCoke();
	}
	public String getInstructionString(){
		return "Refill Crowley's Coke Cup";
	}
	
}

class Mosfet extends SingleButtonPress{
	public Mosfet(int init){
		super("Mosfet", 2, init);
	}
	public Widget getRandomInstruction() {
		return new Mosfet((new Random()).nextInt(buttons));
	}
	public String getInstructionString() {
		switch(val){
		case(1):
			return "Set the MOSFET to N-CHANNEL";
		default:
			return "Set the MOSFET to P-CHANNEL";
		}
	}
}

//Dashboard 1_2
class SpaceGate extends SingleButtonPress{
	public SpaceGate(int val) {super("Space Gate", 2, val);}
	public Widget getRandomInstruction() {
		return new SpaceGate((new Random().nextInt(buttons)));
	}

	public String getInstructionString() {
		switch(val){
		case(0):
			return "Open the Space Gate";
		default:
			return "Close the Space Gate";
		}
	}
}
class Nextron extends SingleButtonPress{
	public Nextron(){super("Nextron", 1, 0);}
	public Widget getRandomInstruction(){
		return new Nextron();
	}
	public String getInstructionString(){return "Reset the Nextron";}
}
class FluxCapacitor extends SingleValueWidget{
	public FluxCapacitor(int init_val) {
		super("Flux Capacitor", 0, 2, init_val);
	}
	public Widget getRandomInstruction() {
		if (val == 0){
			return new FluxCapacitor(1);
		}
		else{
			return new FluxCapacitor(0);
		}
	}
	public String getInstructionString() {
		if (val == 0){
			return "Set Flux Capacitor to Clean";
		}
		else{
			return "Set Flux Capacitor to Venting";
		}
	}
}

//Dashboard 1_3
class CoffeeMaker extends SingleButtonPress{
	private static final long serialVersionUID = 1L;
	public CoffeeMaker(){super("Coffee Maker", 1, 0);}
	public Widget getRandomInstruction(){
		return new CoffeeMaker();
	}
	public String getInstructionString(){return "Brew the Coffee";}
}
class Hitchhiker extends SingleValueWidget{
	public Hitchhiker(int init_val) {
		super("Hitchhiker", 0, 2, init_val);
	}
	public Widget getRandomInstruction() {
		if (val == 0){
			return new Hitchhiker(1);
		}
		else{
			return new Hitchhiker(0);
		}
	}
	public String getInstructionString() {
		if (val == 0){
			return "Allow Hitchhikers";
		}
		else{
			return "Deny Hitchhikers";
		}
	}
}
class GPA extends Slider{
	public GPA(int init_val) {
		super("GPA", 0, 3, 0);
	}
	public String getInstructionString(){
		switch(val){
		case(0):
			return "Set GPA to 2.5";
		case(1):
			return "Set GPA to 3.0";
		case(2):
			return "Set GPA to 3.5";
		default:
			return "Set GPA to 4.0";
		}
	}
	
}
//Dashboard 1_4
class SelfDestruct extends SingleButtonPress{
	public SelfDestruct() {
		super("Self Destruct", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new SelfDestruct();
	}
	public String getInstructionString(){
		return "Activate Self Destruct";
	}
}
class Technograph extends SingleValueWidget{
	public Technograph(int init_val) {
		super("Technograph", 0, 2, init_val);
	}
	public Widget getRandomInstruction() {
		if (val == 0){
			return new Technograph(1);
		}
		else{
			return new Technograph(0);
		}
	}
	public String getInstructionString() {
		if (val == 0){
			return "Set Technograph to high";
		}
		else{
			return "Set Technograph to low";
		}
	}
}
class Flamethrower extends SingleButtonPress{
	public Flamethrower(int val) {super("Flamethrower", 2, val);}
	public Widget getRandomInstruction() {
		return new Flamethrower((new Random().nextInt(buttons)));
	}
	public String getInstructionString() {
		switch(val){
		case(0):
			return "Point the Flamethrower";
		default:
			return "Shoot the Flamethrower";
		}
	}
}

//Dashboard 2-1
class Leftovers extends SingleButtonPress{
	public Leftovers() {
		super("Leftovers", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new Leftovers();
	}
	public String getInstructionString(){
		return "Freeze Leftovers";
	}
}
class TurboJumper extends SingleButtonPress{
	public TurboJumper(int val) {super("TurboJumper", 2, val);}
	public Widget getRandomInstruction() {
		return new TurboJumper((new Random().nextInt(buttons)));
	}
	public String getInstructionString() {
		return String.format("Set TurboJumper to %d", val);
	}
}
class IonTissue extends SingleButtonPress{
	public IonTissue(int val) {super("IonTissue", 3, val);}
	public Widget getRandomInstruction() {
		return new IonTissue((new Random().nextInt(buttons)));
	}
	public String getInstructionString() {
		switch(val){
		case(0):
			return "Engorge the IonTissue";
		case(1):
			return "Wipe the IonTissue";
		default:
			return "Uncork the IonTissue";
		}
	}
}
//Dashboard 2-2
class RepulsionLocator extends SingleButtonPress{
	public RepulsionLocator(int val) {super("Repulsion Locator", 4, val);}
	public Widget getRandomInstruction() {
		return new RepulsionLocator((new Random().nextInt(buttons)));
	}
	public String getInstructionString() {
		return String.format("Set Repulsion Locator to %d", val);
	}
}
class EmergencyWhittler extends SingleButtonPress{
	public EmergencyWhittler(int val) {super("Emergency Whittler", 2, val);}
	public Widget getRandomInstruction() {
		return new EmergencyWhittler((new Random().nextInt(buttons)));
	}
	public String getInstructionString() {
		switch(val){
		case(0):
			return "Baste the Emergency Whittler";
		default:
			return "Jiggle the Emergency Whittler";
		}
	}
}
class Accelerator extends SingleButtonPress{
	public Accelerator() {
		super("Accelerator", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new Accelerator();
	}
	public String getInstructionString(){
		return "Start Accelerator";
	}
}
//Dashboard 2-3
class Infratoxin extends SingleButtonPress{
	public Infratoxin() {
		super("Infratoxin", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new Infratoxin();
	}
	public String getInstructionString(){
		return "Initialize Infratoxin";
	}
}
class Monodish extends SingleValueWidget{
	public Monodish(int init_val) {
		super("Monodish", 0, 2, init_val);
	}
	public Widget getRandomInstruction() {
		if (val == 0){
			return new Monodish(1);
		}
		else{
			return new Monodish(0);
		}
	}
	public String getInstructionString() {
		if (val == 0){
			return "Set Monodish to run";
		}
		else{
			return "Set Monodish to analyze";
		}
	}
}
//Dashboard 2-4
class ForeignDignitaries extends SingleButtonPress{
	public ForeignDignitaries() {
		super("Foreign Dignitaries", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new ForeignDignitaries();
	}
	public String getInstructionString(){
		return "Entertain Foreign Dignitaries";
	}
}
class Progyro extends SingleButtonPress{
	public Progyro(int val) {super("Progyro", 3, val);}
	public Widget getRandomInstruction() {
		return new Progyro((new Random().nextInt(buttons)));
	}
	public String getInstructionString() {
		return String.format("Set Progyro to %d", val);
	}
}
class Holowheel extends SingleValueWidget{
	public Holowheel(int init_val) {
		super("Holowheel", 0, 4, init_val);
	}
	public Widget getRandomInstruction() {
		Random r = new Random();
		int i = r.nextInt(max);
		while(i == val){
			i = r.nextInt(max);
		}
		return new Holowheel(i);
	}
	public String getInstructionString() {
		switch(val){
		case(0):
			return "Set Holowheel to Cast";
		case(1):
			return "Set Holowheel to Reel";
		case(2):
			return "Set Holowheel to Roll";
		default:
			return "Set Holowheel to Flip";
		}
	}
}
//Dashboard 3-1
class DirectOrders extends SingleButtonPress{
	public DirectOrders() {
		super("Direct Orders", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new DirectOrders();
	}
	public String getInstructionString(){
		return "Disobey Direct Orders";
	}
}
class Onions extends SingleButtonPress{
	public Onions() {
		super("Onions", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new Onions();
	}
	public String getInstructionString(){
		return "Caramelize Onions";
	}
}
class Flexbolt extends SingleValueWidget{
	public Flexbolt(int init_val) {
		super("Flexbolt", 0, 3, init_val);
	}
	public Widget getRandomInstruction() {
		Random r = new Random();
		int i = r.nextInt(max);
		while(i == val){
			i = r.nextInt(max);
		}
		return new Flexbolt(i);
	}
	public String getInstructionString() {
		switch(val){
		case(0):
			return "Connect Flexbolt";
		case(1):
			return "Spark Flexbolt";
		default:
			return "Cut Flexbolt";
		}
	}
}
class TachyonAdapter extends SingleButtonPress{
	public TachyonAdapter(int val) {super("Tachyon Adapter", 3, val);}
	public Widget getRandomInstruction() {
		return new TachyonAdapter((new Random().nextInt(buttons)));
	}
	public String getInstructionString() {
		return String.format("Set Tachyon Adapter to %d", val-1);
	}
}
//Dashboard 3-2
class Spacehorn extends SingleButtonPress{
	public Spacehorn() {
		super("Spacehorn", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new Spacehorn();
	}
	public String getInstructionString(){
		return "Honk Spacehorn";
	}
}
class RobotUprising extends SingleButtonPress{
	public RobotUprising() {
		super("Robot Uprising", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new RobotUprising();
	}
	public String getInstructionString(){
		return "Quell Robot Uprising";
	}
}
class Powercyclone extends SingleValueWidget{
	public Powercyclone(int init_val) {
		super("Powercyclone", 0, 2, init_val);
	}
	public Widget getRandomInstruction() {
		Random r = new Random();
		int i = r.nextInt(max);
		while(i == val){
			i = r.nextInt(max);
		}
		return new Powercyclone(i);
	}
	public String getInstructionString() {
		switch(val){
		case(0):
			return "Kick powercyclone";
		default:
			return "Release powercyclone";
		}
	}
}
//Dashboard 3-3
class Gigamill extends SingleButtonPress{
	public Gigamill(int val) {super("Gigamill", 4, val);}
	public Widget getRandomInstruction() {
		return new Gigamill((new Random().nextInt(buttons)));
	}
	public String getInstructionString() {
		return String.format("Set Gigamill to %d", val);
	}
}
class GeigerPowerlantern extends SingleButtonPress{
	public GeigerPowerlantern() {
		super("Geiger Powerlantern", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new GeigerPowerlantern();
	}
	public String getInstructionString(){
		return "Light Geiger Powerlantern";
	}
}
class Transmission extends SingleButtonPress{
	public Transmission() {
		super("Transmission", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new Transmission();
	}
	public String getInstructionString(){
		return "Decrypt Transmission";
	}
}
class MillerMonoray extends SingleValueWidget{
	public MillerMonoray(int init_val) {
		super("Miller Monoray", 0, 2, init_val);
	}
	public Widget getRandomInstruction() {
		Random r = new Random();
		int i = r.nextInt(max);
		while(i == val){
			i = r.nextInt(max);
		}
		return new MillerMonoray(i);
	}
	public String getInstructionString() {
		switch(val){
		case(0):
			return "Aim Miller Monoray";
		default:
			return "Fire Miller Monoray";
		}
	}
}
//Dashboard 3-4
class EvasiveManeuvers extends SingleButtonPress{
	public EvasiveManeuvers() {
		super("Evasive Maneuvers", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new EvasiveManeuvers();
	}
	public String getInstructionString(){
		return "Take Evasive Maneuvers";
	}
}
class Recycling extends SingleButtonPress{
	public Recycling() {
		super("Recycling", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new Recycling();
	}
	public String getInstructionString(){
		return "Empty Recycling";
	}
}
class Hextrack extends SingleButtonPress{
	public Hextrack() {
		super("Hextrack", 1, 0);
	}
	public Widget getRandomInstruction(){
		return new Hextrack();
	}
	public String getInstructionString(){
		return "Turn on Hextrack";
	}
}
class Bank extends SingleButtonPress{
	public Bank(int init_val) {
		super("Bank", 3, init_val);
	}
	public Widget getRandomInstruction() {
		return new Bank((new Random().nextInt(buttons)));
	}
	public String getInstructionString() {
		switch(val){
		case(0):
			return "Balance Bank";
		case(1):
			return "Withdraw from Bank";
		default:
			return "Deposit into Bank";
		}
	}
}
class BilgeAirengines extends SingleButtonPress{
	public BilgeAirengines(int val) {super("Bilge Airengines", 2, val);}
	public Widget getRandomInstruction() {
		return new BilgeAirengines((new Random().nextInt(buttons)));
	}
	public String getInstructionString() {
		return String.format("Set Bilge Airengines to %d", val);
	}
}

//Dashboard 4-1
//None.

//public abstract class Widget implements Serializable {
//	private static final long serialVersionUID = -4298245903822614337L;
//	private Vector<Integer> val;
//	private String name;
//	
//	public Widget(String name){
//		this.setName(name);
//		this.val = new Vector<Integer>();
//	}
//
//
//	public String getName(){
//		return name;
//	}
//
//	public void setName(String name){
//		this.name = name;
//	}
//	
//	public Vector<Integer> getValues(){
//		return val;
//	}
//	
//	public boolean equals(Widget other){
//		return (this.name == other.getName() && this.val.equals(other.getValues()));
//	}
//	
//	public abstract Widget getRandomInstruction();
//	
//	public abstract String getInstructionString();
//	
//}

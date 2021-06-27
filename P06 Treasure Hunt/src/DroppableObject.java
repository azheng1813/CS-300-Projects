import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

public class DroppableObject extends DraggableObject{
	private VisibleObject target; // object over which this object can be dropped
	private Action action; // action that results from dropping this object over target
	private DroppableObject obj;
	// initialize new object
	
	public DroppableObject(String name, int x, int y, VisibleObject target, Action action) {
		super(name, x, y);
		this.target = target;
		this.action = action;
	}
	
	// returns action and deactivates objects in response to successful drop
	// When this object is over its target and its target is active: deactivate both this object and the target object, and return action, otherwise return null
	@Override
	protected Action drop() {
		if(obj.isOver(target) && target.isActive()) {
			obj.deactivate();
			this.target.deactivate();
			return action;
		}	
		return null;
	} 

}

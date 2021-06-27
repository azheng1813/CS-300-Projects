import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

public class ClickableObject extends VisibleObject{
	private Action action; // action returned from update when this object is clicked
	private boolean mouseWasPressed; // tracks whether the mouse was pressed during the last update() initializes this new object

	public ClickableObject(String name, int x, int y, Action action) {
		super(name, x, y);
		mouseWasPressed = false;
		this.action = action;
	}
	
	// calls VisibleObject update, then returns action only when mouse is first clicked on this object
	@Override
	public Action update() {
		super.update();
		
		int x = getProcessing().pmouseX;
		int y = getProcessing().pmouseY;
		
		if(getProcessing().mousePressed && isOver(x, y) && !mouseWasPressed) {
			mouseWasPressed = true;
			return this.action;
		}
		
		if(!getProcessing().mousePressed) {
			mouseWasPressed = false;
			return this.action;
		}
		
		return this.action;
	} 

}

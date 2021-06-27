import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

public class DraggableObject extends VisibleObject{
	private boolean mouseWasPressed; // similar to use in ClickableObject
	private boolean isDragging; // true when this object is being dragged by the user
	private int oldMouseX; // horizontal position of mouse during last update
	private int oldMouseY; // vertical position of mouse during last update
	
	public DraggableObject(String name, int x, int y) {	
		super(name, x, y);
		oldMouseX = x; //uncertain
		oldMouseY = y; //uncertain
		isDragging = false;
		mouseWasPressed = false;
	} // initializes new draggable object
	
	// calls VisibleObject update() first, then moves according to mouse drag each time isDragging changes from true to false, the drop() method below will be
	// called once and any action objects returned from that method should then be returned from update()
	@Override
	public Action update() {
		super.update();
		PApplet object = getProcessing();
		if(object.mousePressed && isOver(object.mouseX, object.mouseY) && !mouseWasPressed) {
			isDragging = true;
			oldMouseX = object.mouseX;
			oldMouseY = object.mouseY;
		}
		else if(!object.mousePressed && isDragging) {
			isDragging = false;
			mouseWasPressed = object.mousePressed;
			return this.drop();
		}
		else if(isDragging) {
			move(object.mouseX - oldMouseX, object.mouseY - oldMouseY);
			oldMouseX = object.mouseX;
			oldMouseY = object.mouseY;
		}
		mouseWasPressed = object.mousePressed;
		return null;
	} 
	
	// this method returns null. subclass types will override this drop() method to perform more interesting behavior
	protected Action drop() {
		return null; 
	} 

}

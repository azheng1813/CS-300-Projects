import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

public class Action {
	private String message; // message printed by this action (or null to do nothing)
	private InteractiveObject object;
	
	/*
	 * Set the private object variable to the provided object
	 * @param object reference to a given InteractiveObject object
	 */
	public Action(InteractiveObject object) {
		this.object = object;
	}
	
	/*
	 * Set the private message and object variables to the provided parameters
	 * @param message reference to a given String object
	 * @param object reference to a given InteractiveObject object
	 */
	public Action(String message, InteractiveObject object) {
		this.message = message;
		this.object = object;
	}
	
	
	/*
	 * Set the private message to the provided parameter
	 * @param message refernce to a given String object
	 */
	public Action(String message) {
		this.message = message;
	} // create and initialize this new action
	
	/*
	 * Is the action an interactive object does
	 */
	public void act(ArrayList<InteractiveObject> objects) { 
		if(this.object != null) {
			this.object.activate();
			objects.add(object);
			this.object = null;
		}
		
		
		if(!message.equals(null)) {
			System.out.println(message);
		}
		
	} // when message is not null, message is printed to System.out

}
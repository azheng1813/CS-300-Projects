import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

public class InteractiveObject {
	private final String NAME; // the constant name identifying this interactive object
	private boolean isActive; // active means this interactive object is visible and can be interacted with
	private static PApplet processing = null;
	
	// initializes the name of this object, and sets isActive to true
	public InteractiveObject(String name) {
		this.NAME = name;
		isActive = true;
	} 
	
	// returns true only when contents of name equal NAME
	public boolean hasName(String name) {
		if(name.equals(NAME)) {
			return true;
		}
		return false;
	} 
	
	// returns true only when isActive is true
	public boolean isActive() {
		if(isActive) {
			return true;
		}
		return false;
	} 
	
	// changes isActive to true
	public void activate() {
		isActive = true;
	} 
	
	// changes isActive to false
	public void deactivate() {
		isActive = false;
	} 
	
	// this method returns null subclass types will override this update() method to do more interesting things
	public Action update() { 
		return null; 
	} 
	
	// initializes processing field
	public static void setProcessing(PApplet processing) {
		InteractiveObject.processing = processing;
	} 
	
	// accessor method to retrieve this static field
	protected static PApplet getProcessing() {
		return processing;
	} 
}

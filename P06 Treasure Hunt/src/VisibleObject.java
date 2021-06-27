import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

public class VisibleObject extends InteractiveObject {
	
	private PImage image; // the graphical representation of this object
	private int x; // the horizontal position (in pixels of this object's left side)
	private int y; // the vertical position (in pixels of this object's top side)
	
	// initialize this new VisibleObject
	public VisibleObject(String name, int x, int y) { 
		super(name);
		this.image = getProcessing().loadImage("P6Distributables" + File.separator + "images" + File.separator + name +".png");
		this.x = x;
		this.y = y;
	}
	
	// draws image at its position before returning null
	@Override
	public Action update() { 
		getProcessing().image(image, x, y);
		return null;
	}
	
	// changes x by adding dx to it (and y by dy)
	public void move(int dx, int dy) {
		this.x = x + dx;
		this.y = y + dy;
	} 
	
	// return true only when point x,y is over image
	public boolean isOver(int x, int y) { 
		if(x < (image.width + this.x) && x > (this.x) && y < (image.height + this.y) && y > (this.y)) {
			return true;
		}
		return false;
	} 
	
	// return true only when other's image overlaps this one's
	public boolean isOver(VisibleObject other) { 
		if(this.x < (other.x + other.image.width) && other.x < (this.x + image.width) && this.y < (other.y + other.image.height) && other.y < (this.y + image.height)) {
			return true;
		}
		return false;
	}

}

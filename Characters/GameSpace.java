import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class GameSpace {
	// call this to initialize space with size listed
	// (cellsize just scales the grid -- try with cellsize=20)
	// use width for x axis use height for y
	public GameSpace(int width, int height, int cellsize) {
		images = new Vector<Image>();

		sc = new SpriteCanvas(true,0.2f,0.2f,0.2f);
		sc.setSize(width*cellsize,height*cellsize);
		cs = cellsize;

		JFrame win = new JFrame("Game Space");
		win.getContentPane().setLayout(new BorderLayout());
		win.getContentPane().add(sc,"Center");
		win.pack();
		win.setVisible(true); 
	}

	static public class InvalidFile extends Exception {
		public InvalidFile(String details) {
			super(details+" is not a valid file");
		}
	}

	// call this to get an Image from a file
	// it returns an ID number that can be used to
	// reference the image
	public int loadImage(String filename) throws InvalidFile {
		MediaTracker tr = new MediaTracker(sc);
		Image i = Toolkit.getDefaultToolkit().getImage(filename);
		tr.addImage(i,0);
		try {
			tr.waitForID(0);
		} catch (InterruptedException e) {
			throw new InvalidFile(filename);
		}
		int ret = images.size();
		images.add(i);
		return ret;
	}

	// call this to draw an image
	// x and y are the coordinates of the upper-left corner
	public void drawImage(int imageID, int x, int y) {
		Image im = images.get(imageID);
		sc.addSprite(im,x*cs,y*cs,cs,cs);
	}

	// call this once you've added all of the objects for
	//  one timestep
	public void nextTimeStep() {
		sc.swap();
	}

	private SpriteCanvas sc;
	private Vector<Image> images;
	int cs;
}

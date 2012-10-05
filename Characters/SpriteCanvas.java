import java.awt.*;
import java.util.*;

public class SpriteCanvas extends Canvas {
	public SpriteCanvas(boolean doubleBuffered,float r, float g, float b) {
		super();
		currSprites = new Vector<Sprite>();
		newSprites = new Vector<Sprite>();
		br = r; bg = g; bb = b;
		doubleBuffer = doubleBuffered;
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		if (g==null) return;
		if (!doubleBuffer) {
			buffWidth = getSize().width;
			buffHeight = getSize().height;
			mypaint(g);
		} else {
			if (buffWidth!=getSize().width || buffHeight!=getSize().height
					|| buff==null || buffGr==null) {
				resetBuff();
				mypaint(buffGr);
			}
			g.drawImage(buff,0,0,this);
		}
	}

	public void resetBuff() {
		if (buffGr!=null) buffGr.dispose();
		if (buff!=null) buff.flush();
		buff = null;
		buffGr = null;
		System.gc();
		buffWidth=getSize().width;
		buffHeight=getSize().height;
		buff = createImage(buffWidth,buffHeight);
		buffGr = buff.getGraphics();
	}

	public void mypaint(Graphics g) {
		g.setColor(new Color(br,bg,bb));
		g.fillRect(0,0,buffWidth,buffHeight);
		try {
			for(Sprite s : currSprites) {
				g.drawImage(s.img,s.x,s.y,s.x+s.w,s.y+s.h,
						0,0,s.img.getWidth(null)-1,
						s.img.getHeight(null)-1,this);
			}
		} catch (ConcurrentModificationException e) {
			// do nothing... just leave it as it is
			// it will get updated again soon
		}
	}

	public void addSprite(Image img, int x, int y, int w, int h) {
		Sprite s = new Sprite();
		s.img = img; s.x = x; s.y = y;
		s.w = w; s.h = h;
		newSprites.addElement(s);
	}

	public void swap() {
		currSprites = newSprites;
		newSprites = new Vector<Sprite>();
		if (doubleBuffer) if (buffGr != null) mypaint(buffGr);
		repaint();
	}

	static private class Sprite {
		public int x, y, w, h;
		public Image img;
	}

	private Vector<Sprite> currSprites;
	private Vector<Sprite> newSprites;

	private boolean doubleBuffer;
	private int buffWidth,buffHeight;
	private Image buff;
	private Graphics buffGr;
	private float br,bg,bb;
}

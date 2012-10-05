import java.util.Vector;
import java.util.Iterator;
import java.io.*;
import java.util.Random;

public class PopSim implements ObjectSet, RandInt {
	public static void main(String[] args) {
		PopSim a = new PopSim(args[0]);
		a.run();
	}

	public PopSim(String loadfile) 
	{
		// make the objlist and deadlist
		objlist = new Vector<GameObject>();
		deadlist = new Vector<Boolean>();
		
		// load the file
		try{
		load(loadfile,20);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("KKK");
		}
		catch (IOException e)
		{
			System.out.println("sucks");
		}
	}
	public void addObj(GameObject g)
	{
		objlist.add(g);
		deadlist.add(false);
		
	}
	public void delObj(GameObject g)
	{
		int x = objlist.indexOf(g);
		deadlist.set(x,true);
	}
	public GameObject getObj( int x, int y)
	{
		for ( int i=0; i < objlist.size();i++)
		{
			if (objlist.get(i).getX() == x && objlist.get(i).getY() == y)
			{
				return objlist.get(i);
			}
		}
		return null;
	}
	public int BoardWidth()
	{
		return width;
	}
	public int BoardHeight()
	{
		return height;
	}
	
	//this function is used to draw
	public void draw(String s, int index)
	{
		
		try{
		int temp = gs.loadImage(s);
		gs.drawImage(temp, (objlist.get(index).getX()),(objlist.get(index).getY()));
		//i++;
	    }
		catch (GameSpace.InvalidFile e)
		{}
	}
	public void run() {
		// this code assume that you are keeping the entities in
		// "objlist" and a set of booleans in "deadlist"
		// feel free to modify as you wish
		for(;;) {
			for(int i=0;i<objlist.size();i++)
				if (!deadlist.get(i)) {
					// add code to make objlist.get(i) take its turn
					// use .move(this,this)
					(objlist.get(i)).move(this,this);
				}
			for(int i=0;i<objlist.size();)
				if (!deadlist.get(i)) {
					//draw objlist.get(i);
				if (objlist.get(i) instanceof Mountain)
					{
						draw("mountain.gif",i);
						i++;
						
					
				}
				else if (objlist.get(i) instanceof Plant)
				{
						draw("flowers.gif",i);
						i++;
				}
				else if (objlist.get(i) instanceof Rabbit)
				{
					
					draw("rabbit.gif",i);
					i++;
				}		
				else if (objlist.get(i) instanceof Tiger)
				{
					draw("tiger.gif",i);
					i++;
				}
				else if (objlist.get(i) instanceof Bear)
				{
					draw("bear.gif", i);
					i++;
				}
				else if (objlist.get(i) instanceof Elephant)
				{
					draw("elephant.gif",i);
					i++;
				}
				else if (objlist.get(i) instanceof Human)
				{
					draw("human.gif",i);
					i++;
				}
				
				
				
				} else {
					objlist.remove(i);
					deadlist.remove(i);
				}
			gs.nextTimeStep();
			// sleep for 0.5 seconds:
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
	}

	// call this with a filename and the cellsize (visually on the screen)
	// cellsize should be 20 for this assignment
	public void load(String loadfile, int cellsize)
			throws FileNotFoundException, IOException {
		try {
			BufferedReader in = new BufferedReader(new FileReader(loadfile));
			if (in == null) throw new FileNotFoundException ("file dne");
			String line = in.readLine();
			if (line==null) throw new IOException("invalid file format");
			String[] vals = line.split("\\s+");
			if (vals.length<3) throw new IOException("invalid file format");
			width = Integer.valueOf(vals[0]).intValue();
			height = Integer.valueOf(vals[1]).intValue();
			long rseed = Long.valueOf(vals[2]).longValue();
			// initialize random number generator with rseed
			// feel free to change if you want to store the random
			// number generator elsewhere
			r = new Random(rseed);
			gs = new GameSpace(width,height,cellsize);
			while((line=in.readLine())!=null) {
				vals = line.split("\\s+");
				if (vals.length<1 || line.length()==0) continue;
				GameObject newo;
				if (vals.length<3)
					throw new IOException("invalid file format");
				int x = Integer.valueOf(vals[1]).intValue();
				int y = Integer.valueOf(vals[2]).intValue();
				// there are nicer ways (other than a large switch)
				// but it involves advanced and convoluted uses of
				// RTTI, so the following is fine for this assignment
				switch(vals[0].charAt(0)) {
					case 'P':
						Plant p = new Plant(x,y);
						addObj(p);
						break;
					case 'R':
						Rabbit r = new Rabbit(x,y);
						addObj(r);
						break;
					case 'E':
						Elephant e = new Elephant(x,y);
						addObj(e);
						break;
					case 'T':
						Tiger t = new Tiger(x,y);
						addObj(t);
						break;
					case 'B':
						Bear b = new Bear(x,y);
						addObj(b);
						break;
					case 'H':
						Human h = new Human(x,y);
						addObj(h);
						break;
					case 'M':
						Mountain m = new Mountain(x,y);
						addObj(m);
						break;
					default:
						throw new IOException("invalid file format");
				}
			}
		} catch (NumberFormatException e) {
			throw new IOException("invalid file format");
		}
		  catch (FileNotFoundException e) {
			  System.out.println(e);
		  }
	}

	// picks an integer from [0,n-1]
	// use this (as specified in the assignment) to randomly pick your
	// direction to assure your behavior is the same as the reference
	// solution
	public int randInt(int n) {
		return r.nextInt(n);
	}

	// need to fill in the objlist type
	// feel free to add more (or remove these if you don't need them)
	private Vector<GameObject> objlist;
	private Vector<Boolean> deadlist;
	private GameSpace gs;
	int width,height;
	Random r;
}

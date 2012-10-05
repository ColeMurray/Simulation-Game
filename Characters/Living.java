import java.util.*;
import java.util.Vector;
public abstract class Living extends GameObject
{
	protected int energy;
	public int getEnergy()
	{
		return energy;
	}
	public abstract void spawn ( ObjectSet obj, int direction );
	public int [] adjx = new int [] {-1,0,0,1};
	public int [] adjy = new int [] {0,-1,1,0};
	public Vector <Integer> processCheck ( int direction )
	{
		int newX=0;
		int newY=0;
		//this vector will contain the coordinates for the move
		// coords[0] will be the newX coords[1] will be newY
		Vector <Integer> coords = new Vector<Integer>();
		if (direction == 1)
		{
			newX=-1;
		}
		else if (direction == 2)
		{
			newY=-1;
		}
		else if (direction == 3)
		{
			newY=1;
		}
		else if (direction == 4)
		{
			newX=1;
		}
		
		coords.add(newX);
		coords.add(newY);
		return coords;
	}
		
	
	
	public boolean matches (ObjectSet o, int a, int b)
	{
		if (a < 0 || b < 0 || a >= o.BoardWidth() || b >= o.BoardHeight())
		return true;
		if ( o.getObj(a,b) !=null )
		{
			return true;
		}
		else return false;
	}
	public int checkspace( ObjectSet o, RandInt r)
	{
		Vector <Integer> possible = new Vector <Integer>();
		for ( int dir = 0; dir < 4; dir++)
		{
			if (!matches(o,x+adjx[dir],y+adjy[dir]))
			{
				possible.add(dir+1);
			}
		}

		if (possible.isEmpty())
		{
			return 0;
		}

		return possible.get(r.randInt(possible.size()));
	}
	/* this function consumes it's food and moves our object
	 * onto it's position
	 * */
	public void consume ( ObjectSet obj, int offsetX, int offsetY)
	{
		GameObject food = obj.getObj(x+offsetX,y+offsetY);
		energy+=food.getEnergy();
		obj.delObj( food );
		x+=offsetX;
		y+=offsetY;
	}
		
		
}

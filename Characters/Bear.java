import java.util.*;
public class Bear extends Omnivore
{
	public Bear(int newX, int newY )
	{
		x = newX ;
		y = newY ;
		energy = 10 ;
	}

	
	public void spawn(ObjectSet obj,  int direction )
	{
		Vector <Integer> coords = processCheck(direction);
		Bear newBear = new Bear(x+coords.get(0),y+coords.get(1));
		obj.addObj(newBear);
	}
	public void move ( ObjectSet obj, RandInt r )
	{
		Vector<Integer>movecoords = processCheck(edible(obj,r));
			
			int offsetX= movecoords.get(0);
			int offsetY= movecoords.get(1);
			if (offsetX != 0 || offsetY != 0 )
			// there is something edible
			{
				//System.out.println("bears eatin");
				consume(obj,offsetX,offsetY);
			}
			else
			{
				movecoords.clear();
				movecoords=processCheck(checkspace(obj,r));
				offsetX= movecoords.get(0);
				offsetY= movecoords.get(1);
				if (offsetX!=0 || offsetY!=0)
				{
					x+=offsetX;
					y+=offsetY;
				}
			}
					
		energy-=2;	
		if (energy > 30 ) 
		{
			int direction = checkspace(obj,r);
			if (direction!= 0)
			{
				spawn(obj,direction);
				
			}
			energy-=15;
		}
		
		
		
		die(obj);
	}
}

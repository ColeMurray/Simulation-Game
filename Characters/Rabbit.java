import java.util.*;
public class Rabbit extends Herbavore
{
	public Rabbit ( int newX, int newY )
	{
		x=newX;
		y=newY;
		energy = 5;
	}
	public void spawn ( ObjectSet obj, int direction)
	{
		Vector <Integer> coords = processCheck(direction);
		Rabbit newRabbit = new Rabbit(x+coords.get(0),y+coords.get(1));
		obj.addObj(newRabbit);
	}
	
	public void move( ObjectSet obj, RandInt r )
	{
			Vector<Integer>movecoords = processCheck(edible(obj,r));
			
			int offsetX= movecoords.get(0);
			int offsetY= movecoords.get(1);
			if (offsetX != 0 || offsetY != 0 )
			// there is something edible
			{
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
		if (energy > 15 ) 
		{
			int direction = checkspace(obj,r);
			if (direction!= 0)
			{
				spawn(obj,direction);
				
			}
			energy-=8;
		}
		
		
		if (energy > 15 ) 
		{
			int direction = checkspace(obj,r);
			if (direction!= 0)
			{
				spawn(obj,direction);
				
			}
			energy-=8;
		}
		die(obj);
		
	}
	
			
}

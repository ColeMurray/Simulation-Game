import java.util.*;
import java.util.Vector;
public class Elephant extends Herbavore
{
	public Elephant(int newX,int newY)
	{
		energy = 20;
		x = newX;
		y = newY;
	}
	public void spawn (ObjectSet obj, int direction)
	{
		Vector <Integer> coords = processCheck(direction);
		Elephant newElephant = new Elephant(x+coords.get(0),y+coords.get(1));
		obj.addObj(newElephant);
	}
	public void move(ObjectSet obj, RandInt r)
	{
		for (int i =0; i < 2; i++) //elephants move twice
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
				//System.out.println("Elephant move");
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
					
		}
		
		energy-=4;	
		if (energy > 60 ) 
		{
			int direction = checkspace(obj,r);
			if (direction!= 0)
			{
				spawn(obj,direction);
				
			}
			energy-=40;
		}
		
		
		die(obj);
	}
}
		

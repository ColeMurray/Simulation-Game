import java.util.*;
public class Plant extends Living
{
	public Plant(int newX, int newY )
	{
		energy = 1 ;
		x = newX ;
		y = newY ;
	}
	public void spawn ( ObjectSet obj, int direction )
	{
		Vector <Integer> coords = processCheck(direction);
		if (coords.get(0)==0 && coords.get(1) == 0)
		{
			
			return;
		}
		Plant newPlant = new Plant(x+coords.get(0),y+coords.get(1));
		obj.addObj(newPlant);
	}
		
			
	public void move (ObjectSet obj, RandInt r)
	{
		energy+=3;
		if (energy > 6)
		{
			int val = checkspace(obj,r);
			energy-=2;
			if (val != 0)
			{
				
				spawn(obj,val);
				
			}
			
			
			
		}
	}
}
		
		
		
		

import java.util.*;
public abstract class Herbavore extends Animal
{
	public int edible(ObjectSet obj, RandInt r)
	{
		Vector <Integer> possible = new Vector <Integer>();
		for ( int dir = 0; dir < 4; dir++)
		{
			if (obj.getObj(x+adjx[dir],y+adjy[dir]) instanceof Plant ) 
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
	
}
	
		
